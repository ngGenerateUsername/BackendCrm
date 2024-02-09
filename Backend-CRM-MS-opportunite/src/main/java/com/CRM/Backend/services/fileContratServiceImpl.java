package com.CRM.Backend.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.CRM.Backend.entities.*;
import com.CRM.Backend.repositories.FileContratRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CRM.Backend.servicesInterfaces.IFileContratService;

@Service
public class fileContratServiceImpl implements IFileContratService {
    @Autowired
    FileContratRepository fileContratRepository;

    @Override
    public FileContrat addFilecontrat(FileContrat filecontrat) {
        try {
            return fileContratRepository.save(filecontrat);
        } catch (Exception e) {
            // log.info("erreur domain add : "+e.getMessage());
            return null;
        }
    }

    @Override

    public FileContrat retrieveFilecontrat(Long id) {
        try {
            FileContrat d = fileContratRepository.findById(id).orElse(null);
            return d;
        } catch (Exception e) {
            // log.info("erreur domain retrieve : "+e.getMessage());
            return null;
        }
    }

    @Override
    public List<FileContrat> AllFilecontrats() {
        List<FileContrat> domains = fileContratRepository.findAll();
        return domains;
    }

    @Override
    public List<FileContrat> getFilesForContrat(Long idContrat) {
        List<FileContrat> files = fileContratRepository.findAll();
        List<FileContrat> filesForContrat = new ArrayList<>();
        for (FileContrat file : files) {
            Long contratId = file.getIdContrat();
            if (contratId != null && contratId.equals(idContrat)) {
                filesForContrat.add(file);
            }
        }
        return filesForContrat;
    }
    @Override
    public void deleteFileContrat(Long id) {
        fileContratRepository.deleteById(id);
    }

}
