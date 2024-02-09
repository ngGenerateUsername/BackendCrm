package com.CRM.Backend.servicesInterfaces;

import java.util.List;

import com.CRM.Backend.entities.FileContrat;

public interface IFileContratService {

    List<FileContrat> AllFilecontrats();

    FileContrat retrieveFilecontrat(Long id);

    FileContrat addFilecontrat(FileContrat fileContrat);

    List<FileContrat> getFilesForContrat(Long idContrat);

    void deleteFileContrat(Long id);
}
