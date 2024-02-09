package com.CRM.Backend.controllers;

import java.util.List;

import com.CRM.Backend.entities.*;
import com.CRM.Backend.repositories.FileContratRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import com.CRM.Backend.servicesInterfaces.IFileContratService;


@RestController
@RequestMapping("/ticket")
@CrossOrigin(origins = "*", maxAge = 3600)
public class fileContratController {

    @Autowired
    IFileContratService fileContratService;


    @GetMapping("/AllFileContrats")
    //@PreAuthorize("hasRole('ADMIN') or hasRole('PROPRIETAIRE') or hasRole('COMMERCIAL') or hasRole('RESPONSABLEcontrat')")
    @ResponseBody
    public List<FileContrat> Allcontrats() {
        return fileContratService.AllFilecontrats();
    }

    @GetMapping("/DetailsFileContrat")
    //@PreAuthorize("hasAuthority('[ROLE_ADMIN]')")
    @ResponseBody
    public FileContrat retrieveFilecontrat(@Param("id") Long id) {
        return fileContratService.retrieveFilecontrat(id);
    }

    @PostMapping("/addFileContrat")
    //@PreAuthorize("hasRole('ADMIN') or hasRole('PROPRIETAIRE') or hasRole('COMMERCIAL') or hasRole('RESPONSABLEcontrat')")
    @ResponseBody
    public FileContrat addFileContrat(@RequestBody FileContrat fileContrat) {
        return fileContratService.addFilecontrat(fileContrat);
    }
    @GetMapping("/filesForContrat/{idContrat}")
    @ResponseBody
    public List<FileContrat> getFilesForContrat(@PathVariable("idContrat") Long idContrat) {
        return fileContratService.getFilesForContrat(idContrat);
    }
    @DeleteMapping("/deleteFileContrat/{id}")
    public void deleteFileContrat(@PathVariable("id") Long id) {
        fileContratService.deleteFileContrat(id);
    }

}
