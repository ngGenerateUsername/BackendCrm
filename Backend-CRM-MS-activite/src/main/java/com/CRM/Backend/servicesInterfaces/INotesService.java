package com.CRM.Backend.servicesInterfaces;
import com.CRM.Backend.entities.Notes;

import java.util.Date;
import java.util.List;

public interface INotesService {

    List<Notes> AllNotes();

    Notes retrieveNote(Long id);

    Notes addNotes(Notes notes);

    List<Notes> getNotesForActivite(Long idActivite);

    void deleteNote(Long idNote);

    List<Notes> getNotesForOpportunite(Long idOpportunite);
}
