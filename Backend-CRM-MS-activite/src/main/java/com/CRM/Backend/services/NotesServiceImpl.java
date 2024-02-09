package com.CRM.Backend.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.CRM.Backend.entities.Notes;
import com.CRM.Backend.entities.activite;
import com.CRM.Backend.repositories.NotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CRM.Backend.servicesInterfaces.INotesService;

@Service
public class NotesServiceImpl implements INotesService {
    @Autowired
    NotesRepository NotesRepository;

    @Override
    public List<Notes> AllNotes() {
        List<Notes> domains = NotesRepository.findAll();
        return domains;
    }

    @Override
    public Notes addNotes(Notes Notes) {
        try {
            return NotesRepository.save(Notes);
        } catch (Exception e) {
            // log.info("erreur domain add : "+e.getMessage());
            return null;
        }
    }
    public void deleteNote(Long idNote){
        NotesRepository.deleteById(idNote);
    }
    public Notes retrieveNote(Long id) {
        try {
            Notes d = NotesRepository.findById(id).orElse(null);
            return d;
        } catch (Exception e) {
            // log.info("erreur domain retrieve : "+e.getMessage());
            return null;
        }
    }

    @Override
    public List<Notes> getNotesForActivite(Long idActivite) {
        List<Notes> Notess = NotesRepository.findAll();
        List<Notes> nottesTypes = new ArrayList<>();
        for (Notes Notesd : Notess) {
            Long opportunitId = Notesd.getIdActivite();
            if (opportunitId != null && opportunitId.equals(idActivite)) {
                nottesTypes.add(Notesd);
            }
        }
        return nottesTypes;
    }
    @Override
    public List<Notes> getNotesForOpportunite(Long idOpportunite) {
        List<Notes> Notess = NotesRepository.findAll();
        List<Notes> nottesTypes = new ArrayList<>();
        for (Notes Notesd : Notess) {
            Long opportunitId = Notesd.getIdOpportunite();
            if (opportunitId != null && opportunitId.equals(idOpportunite)) {
                nottesTypes.add(Notesd);
            }
        }
        return nottesTypes;
    }


}
