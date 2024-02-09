package com.CRM.Backend.servicesInterfaces;

import com.CRM.Backend.entities.*;
import com.CRM.Backend.entities.Dto.*;

import java.util.List;

public interface ITicketService {

    public ticket addticket(DtoTicket ticket);
    public void deleteticket(Long id);
    public ticket updateticket(ticket ticket);
    public ticket retrieveticket(Long id);
    public ticket notesTicket(DtoNotesTicket DtoNotesTicket);
    public ticket SetstatusTicket(DtoTicketChangeStatus DtoTicketChangeStatus);
    public ticket SetprioriteTicket(DtoTicketChangePriorite DtoTicketChangePriorite);
    public ticket SetTypeTicket(DtoTicketChangeType DtoTicketChangeType);

    public ticket affecteResponsable(DtoAffectRespTicket DtoAffectRespTicket);

    public List<ticket> Alltickets();
    public List<ticket> AllticketsStatus(String status) ;
    public List<ticket> AllticketsType(String type);
    public List<ticket> AllticketsPerContact(Long idCreateur);
    public List<ticket> AllticketsPerEntreprise(Long idEntreprise);
    public List<ticket> AllticketsPerRespTicket(Long idResponsable);
    public List<ticket> AllticketsPerClient(Long idResponsable);
}
