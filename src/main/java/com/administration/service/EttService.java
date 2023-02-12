package com.administration.service;

import com.administration.dto.*;

import java.util.List;

public interface EttService {

    EttResponseDTO addEtt(EttRequestDTO ettRequestDTO);
    EttResponseDTO getEtt(String id);
    List<EttResponseDTO> listEtts();
    void updateEttDTO(EttUpdateDTO dto);
}
