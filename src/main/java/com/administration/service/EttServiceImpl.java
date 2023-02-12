package com.administration.service;

import com.administration.dto.EttRequestDTO;
import com.administration.dto.EttResponseDTO;
import com.administration.dto.EttUpdateDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EttServiceImpl implements EttService{
    @Override
    public EttResponseDTO addEtt(EttRequestDTO ettRequestDTO) {
        return null;
    }

    @Override
    public EttResponseDTO getEtt(String id) {
        return null;
    }

    @Override
    public List<EttResponseDTO> listEtts() {
        return null;
    }

    @Override
    public void updateEttDTO(EttUpdateDTO dto) {

    }
}
