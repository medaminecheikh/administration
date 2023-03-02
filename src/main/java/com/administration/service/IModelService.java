package com.administration.service;

import com.administration.dto.ModelRequestDTO;
import com.administration.dto.ModelResponseDTO;
import com.administration.dto.ModelUpdateDTO;

import java.util.List;

public interface IModelService {

    ModelResponseDTO addModel(ModelRequestDTO RequestDTO);
    ModelResponseDTO getModel(String id);
    List<ModelResponseDTO> listModels();
    void updateModelDTO(ModelUpdateDTO dto);
    void deleteModel(String idModel);
}
