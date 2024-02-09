package com.CRM.Backend.controllers;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.CRM.Backend.entities.Devis;
import com.CRM.Backend.services.IDevisServiceImp;




@RestController
@RequestMapping("/devis")
@Validated
@CrossOrigin(origins = "*")
public class DevisController {
  
  @Autowired
    private IDevisServiceImp _devisService;

    @GetMapping("all")
    private ResponseEntity<List<Devis>> getAll()
    {
        return ResponseEntity.status(HttpStatus.OK).body(_devisService.getAll());
    }

    @PostMapping("")
    private ResponseEntity<Devis> addFacture(@RequestBody Devis devis)
    {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(_devisService.addDevis(devis));
        } catch (Exception e) {
            // TODO: handle exception
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }



    //delete facture by id
    @DeleteMapping("delete/{idDevis}")
    private ResponseEntity<String> deleteDevis(@PathVariable Long idDevis)
    {
        return _devisService.deleteDevis(idDevis)?new ResponseEntity<String>("Facture deleted successfully",HttpStatus.OK):
        new ResponseEntity<String>("Facture Not Exist!",HttpStatus.NOT_FOUND);
    }

    //get Client facture
    @GetMapping("client/{idClient}")
    private ResponseEntity<List<Devis>> getClientFacture(@PathVariable Long idDevis)
    {
        return new ResponseEntity<>(_devisService.findClientDevis(idDevis),HttpStatus.OK);
    }

    //get all factures by id commerciale
    @GetMapping("commerciale/{idCommerciale}")
    private ResponseEntity<List<Devis>> getByIdCommerciale(@PathVariable Long idCommerciale)
    {
        return new ResponseEntity<List<Devis>>(_devisService.findByIdCommerciale(idCommerciale),HttpStatus.OK);
    }

    //get details of facture
    @GetMapping("{id}")
    private ResponseEntity<Devis> getDetailsDevis(@PathVariable Long id)
    {
        Devis getDetail=_devisService.findById(id);
        return getDetail!=null?new ResponseEntity<Devis>(getDetail, HttpStatus.OK):
        new ResponseEntity<>(getDetail,HttpStatus.NOT_FOUND);

    }


    @GetMapping("second")
    private ResponseEntity<List<Devis>> getSecondAll()
    {
        if(_devisService.equals(_devisService))
            return new ResponseEntity<List<Devis>>(_devisService.getAll(), HttpStatus.OK);

        return new ResponseEntity<List<Devis>>(_devisService.getAll(), HttpStatus.NOT_EXTENDED);
    }
}















