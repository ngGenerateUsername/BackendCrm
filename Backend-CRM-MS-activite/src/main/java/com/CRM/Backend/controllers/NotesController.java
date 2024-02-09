package com.CRM.Backend.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import com.CRM.Backend.entities.Notes;
import com.CRM.Backend.entities.activite;
import com.CRM.Backend.servicesInterfaces.INotesService;

@RestController
@RequestMapping("/Notes")
@CrossOrigin(origins = "*", maxAge = 3600)
public class NotesController {
    @Autowired
    INotesService NotesService;

    @GetMapping("/AllNotess")

    @ResponseBody
    public List<Notes> AllNotes() {
        return NotesService.AllNotes();
    }

    @GetMapping("/DetailsNote")
    @ResponseBody
    public Notes retrieveNotes(@Param("id") Long id) {
        return NotesService.retrieveNote(id);
    }

    @PostMapping("/addNote")

    @ResponseBody
    public Notes addNotes(@RequestBody Notes Notes) {
        return NotesService.addNotes(Notes);
    }

    @GetMapping("/NotesForActivite/{idActivite}")
    @ResponseBody
    public List<Notes> getNotesForActivite(@PathVariable("idActivite") Long idActivite) {
        return NotesService.getNotesForActivite(idActivite);
    }
    @GetMapping("/NotesForOpportunite/{idOpportunite}")
    @ResponseBody
    public List<Notes> getNotesForOpportunite(@PathVariable("idOpportunite") Long idOpportunite) {
        return NotesService.getNotesForOpportunite(idOpportunite);
    }
@DeleteMapping("/deleteNote/{idNote}")
@ResponseBody
    public void deleteNote(@PathVariable("idNote") Long idNote){
        NotesService.deleteNote(idNote);
}
}
