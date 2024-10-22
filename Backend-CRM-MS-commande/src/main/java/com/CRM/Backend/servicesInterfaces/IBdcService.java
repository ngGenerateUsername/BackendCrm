package com.CRM.Backend.servicesInterfaces;

import com.CRM.Backend.entities.BonDeCommande;

import java.util.List;

public interface IBdcService {
    public String addbdc(Long idcmd);
    public List<BonDeCommande> getallbdc(Long idetse);
    public BonDeCommande getbdcbyid(Long idbdc);
}
