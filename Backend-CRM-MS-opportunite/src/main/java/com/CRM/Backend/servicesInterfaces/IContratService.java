package com.CRM.Backend.servicesInterfaces;

import com.CRM.Backend.entities.*;


import java.util.List;

public interface IContratService {

    List<contrat> Allcontrats();

    contrat retrievecontrat(Long id);

    contrat addcontrat(contrat contrat);

}
