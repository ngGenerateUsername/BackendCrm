package com.CRM.Backend.servicesInterfaces;

import com.CRM.Backend.entities.*;
import com.CRM.Backend.entities.Dto.*;

import java.util.List;

public interface IMsgService {

    public MsgAdmin addMsg(DTOMsgAdminCreate DTOMsgAdminCreate);
    public void deleteMsg(Long id);
    public MsgAdmin retrieveMsg(Long id);
    public MsgAdmin SetstatusMsg(DTOMsgAdminStatus DTOMsgAdminStatus);
    public List<MsgAdmin> AllMsgs();
}
