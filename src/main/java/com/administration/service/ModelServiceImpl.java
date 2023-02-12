package com.administration.service;

import com.administration.dto.ModelRequestDTO;
import com.administration.dto.ModelResponseDTO;
import com.administration.dto.ModelUpdateDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModelServiceImpl implements ModelService{
    @Override
    public ModelResponseDTO addModel(ModelRequestDTO RequestDTO) {
        return null;
    }

    @Override
    public ModelResponseDTO getModel(String id) {
        return null;
    }

    @Override
    public List<ModelResponseDTO> listModels() {
        return null;
    }

    @Override
    public void updateModelDTO(ModelUpdateDTO dto) {

    }
}
