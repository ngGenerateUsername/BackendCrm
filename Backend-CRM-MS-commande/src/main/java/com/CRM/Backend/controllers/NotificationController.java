package com.CRM.Backend.controllers;

import com.CRM.Backend.entities.Notif;
import com.CRM.Backend.repositories.NotifRepository;
import com.CRM.Backend.servicesInterfaces.ICommandeService;
import feign.Body;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notif")
@CrossOrigin(origins = "*", maxAge = 3600)
public class NotificationController {

    @Autowired
    NotifRepository notifRepository;




}
