package com.CRM.Backend.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
@Entity
@Getter
@Setter
@AllArgsConstructor
@Data
@Builder
public class Notif implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idnotif;
    private String msg;
    private Long IDETSE;

    private Long idProduit;



    private boolean clickable = true ;

    public boolean isClickable() {
        return clickable;
    }

    public void setClickable(boolean clickable) {
        this.clickable = clickable;
    }
    public Long getIDproduit() {
        return idProduit;
    }

    public void setIDproduit(Long IDproduit) {
        this.idProduit = IDproduit;
    }

    public Long getIDETSE() {
        return IDETSE;
    }

    public void setIDETSE(Long IDETSE) {
        this.IDETSE = IDETSE;
    }


    public Long getIdnotif() {
        return idnotif;
    }

    public void setIdnotif(Long idnotif) {
        this.idnotif = idnotif;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Notif() {
    }
}
