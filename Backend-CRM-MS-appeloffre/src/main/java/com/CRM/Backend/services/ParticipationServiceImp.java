package com.CRM.Backend.services;

import com.CRM.Backend.entities.*;
import com.CRM.Backend.repositories.AORepository;
import com.CRM.Backend.repositories.ParticipationRepository;
import com.CRM.Backend.servicesInterfaces.FournisseurServiceFeignClient;
import com.CRM.Backend.servicesInterfaces.NotificationServiceFeignClient;
import com.CRM.Backend.servicesInterfaces.ParticipationService;
import com.CRM.Backend.servicesInterfaces.RoleFournisseurServiceFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service

public class ParticipationServiceImp implements ParticipationService {

    @Autowired
    FournisseurServiceFeignClient fournisseurServiceFeignClient;

    @Autowired
    AORepository aoRepository;

    @Autowired
    ParticipationRepository participationRepository;

    @Autowired
    NotificationServiceFeignClient notificationServiceFeignClient;
    @Autowired
    RoleFournisseurServiceFeignClient roleFournisseurServiceFeignClient;

    @Override
    public String participate(Participation p, Long idcf, Long idao) {
        String msg = "test";
        Fournisseur f = fournisseurServiceFeignClient.FournisseurPerContact(idcf.longValue());
        boolean hasParticipated = false;
        List<Participation> participations = participationRepository.findByAppeloffre_Idao(idao);

        for (Participation participation : participations) {
            if (participation.getIdFournisseur().equals(f.getIdUser())) {
                hasParticipated = true;
                break;
            }
        }
        if (!hasParticipated) {
            p.setIdFournisseur(f.getIdUser());
            p.setPrix(p.getPrix());
            p.setDateLivraisonF(p.getDateLivraisonF());
            p.setAppeloffre(aoRepository.findById(idao).get());
            participationRepository.save(p);

            msg = "particpation avec succeé";
        } else {
            msg = "you have alreadu partipated";
        }

        return msg;

    }

    @Override
    public void setBestFournisseurForTenders(List<Appeloffre>list )  {
        List<Appeloffre> appelsOffres = aoRepository.findAll();
        for (Appeloffre appelOffre : appelsOffres) {
            double lowestScore = Double.MAX_VALUE;
            Long bestFournisseurId = null;
            List<Participation> part = participationRepository.findByAppeloffre_Idao(appelOffre.getIdao());
            for (Participation participation : part) {
                double score = calculateScore(participation.getPrix(), participation.getDateLivraisonF(), appelOffre.getDateLivraisonAO());
                System.out.println("Score for Participation ID " + participation.getId() + ": " + score);
                if (score < lowestScore) {
                    lowestScore = score;
                    bestFournisseurId = participation.getIdFournisseur();
                }
            }
            appelOffre.setIdf(bestFournisseurId);
            aoRepository.save(appelOffre);


            if (appelOffre.getDateCloture().before(new Date())) {

                appelOffre.setEtat(etatAO.cloture);
                aoRepository.save(appelOffre);

                Fournisseur fournisseur = fournisseurServiceFeignClient.FournisseurDetails(bestFournisseurId);
                List<Fournisseur> LIST = roleFournisseurServiceFeignClient.contactsPerFournisseur(fournisseur.getIdUser());

                for (Fournisseur fournisseur1 : LIST) {
                    // Get the contact ID directly
                    Long contactId = fournisseur1.getIdUser();

                    // Log the contact ID (for debugging, optional)
                    System.out.println("Processing contact ID: " + contactId);

                    // Create a notification for the current contact ID
                    Notif notification = new Notif();
                    notification.setClickable(true);

                    // Create notification message
                    notification.setMsg("Félicitations " + fournisseur.getNomFournisseur() +
                            ", vous avez remporté l'appel d'offre de " + appelOffre.getNomprod());
                    notification.setIDETSE(contactId);

                    // Send the notification
                    notificationServiceFeignClient.create(notification);
                }


            }

        }}
    private static double calculateScore(double prix, Date dateLivraisonF,Date datelao) {
        // Example scoring logic: lower price and earlier delivery yield a better score
        // You can adjust the weights as needed

        double priceWeight = 0.5;
        double dateWeight = 0.5;
        long differenceInMillis = datelao.getTime() - dateLivraisonF.getTime();
        int daysToDelivery = (int) TimeUnit.MILLISECONDS.toDays(differenceInMillis);
        System.out.println("haw y7seb  score:");
        System.out.println("Prix: " + prix);
        System.out.println("Date Livraisonf: " + dateLivraisonF);
        System.out.println("Date LivraisonAO: " + datelao);
        System.out.println("nhar s: " + daysToDelivery);
        System.out.println(" Score: " + ((prix * priceWeight) + (daysToDelivery * dateWeight)));
        System.out.println("            ");
        System.out.println(" --------------------------------           ");

        return (prix * priceWeight) + (daysToDelivery * dateWeight);

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

    @Scheduled(cron = "0 * * * * ?") // Runs every minute at the start of the minute
    public void processClosedTenders() {
        // Normalize the current date
        Date currentDate = normalizeDate(new Date());

        // Find tenders that are closed and haven't yet been assigned a best supplier
        List<Appeloffre> closedTenders = aoRepository.findByDateClotureBeforeAndIdfIsNull(currentDate);

        if (!closedTenders.isEmpty()) {
            // Process only closed tenders to set the best supplier and send notifications
            setBestFournisseurForTenders(closedTenders);
        }
    }


}