package com.CRM.Backend.services;

import com.CRM.Backend.entities.*;
import com.CRM.Backend.repositories.CommandeRepository;
import com.CRM.Backend.repositories.NotifRepository;
import com.CRM.Backend.repositories.ReclamationRepository;
import com.CRM.Backend.servicesInterfaces.ClientServiceFeignClient;
import com.CRM.Backend.servicesInterfaces.INotificationService;
import com.CRM.Backend.servicesInterfaces.IReclamationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service

public class iNotificationServiceImp implements INotificationService {

@Autowired
NotifRepository notifRepository;
    @Override
    public Notif updatenotification( Notif n, Long idnotification) {
         Notif notif = notifRepository.findById(idnotification).get();

        notif.setClickable(false);
        notif.setMsg(n.getMsg());
        notif.setClickable(n.isClickable());
        System.out.println(notif.getIdnotif());
        return notifRepository.save(notif);
    }
}
