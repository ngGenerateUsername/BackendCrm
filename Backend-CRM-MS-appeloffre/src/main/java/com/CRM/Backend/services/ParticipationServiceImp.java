package com.CRM.Backend.services;

import com.CRM.Backend.entities.*;
import com.CRM.Backend.repositories.AORepository;
import com.CRM.Backend.repositories.ParticipationRepository;
import com.CRM.Backend.servicesInterfaces.FournisseurServiceFeignClient;
import com.CRM.Backend.servicesInterfaces.NotificationServiceFeignClient;
import com.CRM.Backend.servicesInterfaces.ParticipationService;
import com.CRM.Backend.servicesInterfaces.RoleFournisseurServiceFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

@Service
public class ParticipationServiceImp implements ParticipationService {

    private static final Logger logger = Logger.getLogger(ParticipationServiceImp.class.getName());

    @Autowired
    private FournisseurServiceFeignClient fournisseurServiceFeignClient;

    @Autowired
    private AORepository aoRepository;

    @Autowired
    private ParticipationRepository participationRepository;

    @Autowired
    private NotificationServiceFeignClient notificationServiceFeignClient;

    @Autowired
    private RoleFournisseurServiceFeignClient roleFournisseurServiceFeignClient;

    @Override
    public String participate(Participation participation, MultipartFile file, Long contactId, Long tenderId) throws IOException {
        String responseMessage;

        // Save the uploaded file temporarily
        File tempFile = File.createTempFile("upload", ".pdf");
        file.transferTo(tempFile);

        try {
            // Extract text from the Python service
            String extractedText = callPythonTextExtractionService(tempFile);

            // Retrieve supplier information based on contact ID
            Fournisseur fournisseur = fournisseurServiceFeignClient.FournisseurPerContact(contactId);

            // Check if the supplier has already participated
            boolean hasParticipated = participationRepository.findByAppeloffre_Idao(tenderId)
                    .stream()
                    .anyMatch(existing -> existing.getIdFournisseur().equals(fournisseur.getIdUser()));

            if (!hasParticipated) {
                // Set participation details
                participation.setIdFournisseur(fournisseur.getIdUser());
                participation.setDescription(extractedText); // Set the extracted text as description
                participation.setAppeloffre(aoRepository.findById(tenderId).orElseThrow());
                participationRepository.save(participation);

                responseMessage = "Participation submitted successfully!";
            } else {
                responseMessage = "You have already participated in this tender.";
            }
        } finally {
            // Clean up temporary file
            tempFile.delete();
        }

        return responseMessage;
    }

    @Override
    public void setBestFournisseurForTenders(List<Appeloffre> tenders) {
        for (Appeloffre tender : tenders) {
            double lowestScore = Double.MAX_VALUE;
            Long bestFournisseurId = null;

            List<Participation> participations = participationRepository.findByAppeloffre_Idao(tender.getIdao());

            for (Participation participation : participations) {
                // Call Python script to compute similarity
                double similarityScore = callPythonSimilarityService(tender.getDescription(), participation.getDescription());
                double score = calculateScore(
                        participation.getPrix(),
                        participation.getDateLivraisonF(),
                        tender.getDateLivraisonAO(),
                        similarityScore);

                if (score < lowestScore) {
                    lowestScore = score;
                    bestFournisseurId = participation.getIdFournisseur();
                }
            }

            tender.setIdf(bestFournisseurId);
            aoRepository.save(tender);

            if (tender.getDateCloture().before(new Date())) {
                closeTenderAndNotifyWinner(tender, bestFournisseurId);
            }
        }
    }

    private String callPythonTextExtractionService(File file) {
        try {
            String url = "http://localhost:5000/extract-text"; // Python service endpoint
            RestTemplate restTemplate = new RestTemplate();

            MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
            body.add("file", new FileSystemResource(file));

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);

            HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

            ResponseEntity<Map> response = restTemplate.postForEntity(url, requestEntity, Map.class);

            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                return (String) response.getBody().get("text");
            } else {
                throw new RuntimeException("Failed to extract text from Python service.");
            }
        } catch (Exception e) {
            logger.severe("Error calling Python text extraction service: " + e.getMessage());
            throw new RuntimeException("Error extracting text via Python service.");
        }
    }


    private double callPythonSimilarityService(String text1, String text2) {
        try {
            String url = "http://localhost:5000/similarity"; // Python similarity endpoint
            RestTemplate restTemplate = new RestTemplate();

            Map<String, String> requestBody = Map.of("text1", text1, "text2", text2);
            ResponseEntity<Map> response = restTemplate.postForEntity(url, requestBody, Map.class);

            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                return Double.parseDouble(response.getBody().get("similarity").toString());
            } else {
                throw new RuntimeException("Failed to compute similarity from Python service.");
            }
        } catch (Exception e) {
            logger.severe("Error calling Python similarity service: " + e.getMessage());
            throw new RuntimeException("Error computing similarity via Python service.");
        }
    }

    private void closeTenderAndNotifyWinner(Appeloffre tender, Long bestFournisseurId) {
        tender.setEtat(etatAO.cloture);
        aoRepository.save(tender);

        Fournisseur bestFournisseur = fournisseurServiceFeignClient.FournisseurDetails(bestFournisseurId);
        List<Fournisseur> contacts = roleFournisseurServiceFeignClient.contactsPerFournisseur(bestFournisseur.getIdUser());

        for (Fournisseur contact : contacts) {
            Notif notification = new Notif();
            notification.setClickable(true);
            notification.setMsg("Congratulations " + bestFournisseur.getNomFournisseur() +
                    ", you have won the tender for " + tender.getNomprod());
            notification.setIDETSE(contact.getIdUser());

            notificationServiceFeignClient.create(notification);
        }

        logger.info("Notifications sent to the winning supplier contacts.");
    }

    private static double calculateScore(double price, Date deliveryDateSupplier, Date deliveryDateTender, double similarityScore) {
        double priceWeight = 0.4;
        double dateWeight = 0.4;
        double similarityWeight = 0.2;

        long timeDifferenceMillis = deliveryDateTender.getTime() - deliveryDateSupplier.getTime();
        int deliveryDays = (int) TimeUnit.MILLISECONDS.toDays(timeDifferenceMillis);

        return (price * priceWeight) + (deliveryDays * dateWeight) - (similarityScore * similarityWeight);
    }

    public Date normalizeDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    @Scheduled(cron = "0 * * * * ?") // Every minute
    public void processClosedTenders() {
        Date currentDate = normalizeDate(new Date());
        List<Appeloffre> closedTenders = aoRepository.findByDateClotureBeforeAndIdfIsNull(currentDate);

        if (!closedTenders.isEmpty()) {
            setBestFournisseurForTenders(closedTenders);
        }
    }


}
