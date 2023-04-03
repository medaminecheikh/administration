package com.administration.service;

import com.administration.dto.*;

import java.util.List;

public interface IEttService {

    EttResponseDTO addEtt(EttRequestDTO ettRequestDTO);
    EttResponseDTO getEtt(String id);
    List<EttResponseDTO> listEtts();
    void updateEttDTO(EttUpdateDTO dto);
    void affecterEttToZone(String idEtt, String idZone);
    void removeZone(String idEtt);
    void affecterEttToDreg(String idEtt,String idDreg);

    void deleteEtt(String idEtt);
    List<EttResponseDTO> getEttsByDrId(String drId);

}
