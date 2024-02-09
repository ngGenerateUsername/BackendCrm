package com.CRM.Backend.controllers;

import com.CRM.Backend.entities.*;
import com.CRM.Backend.services.IFactureServiceImp;
// import lombok.extern.slf4j.Slf4j;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;



// @Slf4j
@RestController
@RequestMapping("/facture")
@Validated
@CrossOrigin(origins = "*")
public class FactureController {
 
    @Autowired
    private IFactureServiceImp _factService;

    @GetMapping("all")
    private ResponseEntity<List<Facture>> getAll()
    {
        return ResponseEntity.status(HttpStatus.OK).body(_factService.getAll());
    }

    @PostMapping("")
    private ResponseEntity<Facture> addFacture(@RequestBody Facture facture)
    {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(_factService.addFacture(facture));
        } catch (Exception e) {
            // TODO: handle exception
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }



    //delete facture by id
    @DeleteMapping("delete/{idFacture}")
    private ResponseEntity<String> deleteFacture(@PathVariable Long idFacture)
    {
        return _factService.deleteFacture(idFacture)?new ResponseEntity<String>("Facture deleted successfully",HttpStatus.OK):
        new ResponseEntity<String>("Facture Not Exist!",HttpStatus.NOT_FOUND);
    }

    //get Client facture
    @GetMapping("client/{idClient}")
    private ResponseEntity<List<Facture>> getClientFacture(@PathVariable Long idFacture)
    {
        return new ResponseEntity<>(_factService.findClientFacture(idFacture),HttpStatus.OK);
    }

    //get all factures by id commerciale
    @GetMapping("commerciale/{idCommerciale}")
    private ResponseEntity<List<Facture>> getByIdCommerciale(@PathVariable Long idCommerciale)
    {
        return new ResponseEntity<>(_factService.findByIdCommerciale(idCommerciale),HttpStatus.OK);
    }

    //get details of facture
    @GetMapping("{id}")
    private ResponseEntity<Facture> getDetailsFacture(@PathVariable Long id)
    {
        Facture getDetail=_factService.findById(id);
        return getDetail!=null?new ResponseEntity<Facture>(getDetail, HttpStatus.OK):
        new ResponseEntity<>(getDetail,HttpStatus.NOT_FOUND);

    }


    @GetMapping("second")
    private ResponseEntity<List<Facture>> getSecondAll()
    {
        if(_factService.equals(_factService))
            return new ResponseEntity<List<Facture>>(_factService.getAll(), HttpStatus.OK);

        return new ResponseEntity<List<Facture>>(_factService.getAll(), HttpStatus.NOT_EXTENDED);
    }
}















