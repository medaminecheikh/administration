package com.administration.service;

import com.administration.dto.DregionalRequestDTO;
import com.administration.dto.DregionalResponseDTO;
import com.administration.dto.DregionalUpdateDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DregServiceImpl implements DregService{
    @Override
    public DregionalResponseDTO addDreg(DregionalRequestDTO dregionalRequestDTO) {
        return null;
    }

    @Override
    public DregionalResponseDTO getDregional(String id) {
        return null;
    }

    @Override
    public List<DregionalResponseDTO> listDregionals() {
        return null;
    }

    @Override
    public void updateDregionalDTO(DregionalUpdateDTO dto) {

    }
}
