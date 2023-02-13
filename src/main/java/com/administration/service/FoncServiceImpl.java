package com.administration.service;

import com.administration.dto.FoncRequestDTO;
import com.administration.dto.FoncResponseDTO;
import com.administration.dto.FoncUpdateDTO;
import com.administration.repo.FoncRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoncServiceImpl implements FoncService{
    FoncRepo foncRepo;

    public FoncServiceImpl(FoncRepo foncRepo) {
        this.foncRepo = foncRepo;
    }

    @Override
    public FoncResponseDTO addFonc(FoncRequestDTO RequestDTO) {
        return null;
    }

    @Override
    public FoncResponseDTO getFonc(String id) {
        return null;
    }

    @Override
    public List<FoncResponseDTO> listFoncs() {
        return null;
    }

    @Override
    public void updateFoncDTO(FoncUpdateDTO dto) {

    }
}
