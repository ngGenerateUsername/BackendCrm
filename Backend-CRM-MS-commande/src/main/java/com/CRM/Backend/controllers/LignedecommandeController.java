package com.CRM.Backend.controllers;

import com.CRM.Backend.entities.Dto.DTOLigneCommande;
import com.CRM.Backend.entities.LigneCommande;
import com.CRM.Backend.servicesInterfaces.ILigneCommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/Lignedecommande")
public class LignedecommandeController {
    @Autowired
    ILigneCommandeService iLigneCommandeService;
@PostMapping("AddLDC/{idProduit}/{idcontact}")
    public String addlignecmd(@RequestBody DTOLigneCommande ldc, @PathVariable Long idProduit ,@PathVariable Long idcontact ){
    return  iLigneCommandeService.addLigneCommande(ldc,idProduit,idcontact);
}
    @GetMapping("ALLldc")
    public List<DTOLigneCommande> alllignecmd(){
        return  iLigneCommandeService.getAllLigneCommande();
    }



    @GetMapping("ALLldc/{idconcat}")
    public List<DTOLigneCommande>  alllignecmduser( @PathVariable Long idconcat  ){
        return  iLigneCommandeService.getAllLigneCommandebyuser (idconcat);
      }
    @DeleteMapping("Deleteitempanier/{idLigneCommande}")
    public void  Deleteldc(@PathVariable Long idLigneCommande  ){
        iLigneCommandeService.removeLigneCommande(idLigneCommande);
    }
    @PutMapping("updatecmd")
    public String updateLigneCommande(  @RequestBody DTOLigneCommande dtoLigneCommande ) {
       return iLigneCommandeService.updateLigneCommande(dtoLigneCommande);
    }

}
