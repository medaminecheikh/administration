package com.administration.service;

import com.administration.dto.ModelRequestDTO;
import com.administration.dto.ModelResponseDTO;
import com.administration.dto.ModelUpdateDTO;
import com.administration.entity.Model;
import com.administration.entity.Profile;
import com.administration.mappers.ModMapper;
import com.administration.repo.ModelRepo;
import com.administration.repo.ProfileRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ModelServiceImpl implements ModelService{
    ModelRepo modelRepo;
    ModMapper modMapper;
    ProfileRepo profileRepo;

    public ModelServiceImpl(ModelRepo modelRepo, ModMapper modMapper, ProfileRepo profileRepo) {
        this.modelRepo = modelRepo;
        this.modMapper = modMapper;
        this.profileRepo = profileRepo;
    }

    @Override
    public ModelResponseDTO addModel(ModelRequestDTO RequestDTO) {
        Model model=modMapper.ModelRequestDTOModel(RequestDTO);
        model.setNomModel(UUID.randomUUID().toString());
        modelRepo.save(model);
        ModelResponseDTO modelResponseDTO=modMapper.ModelTOModelResponseDTO(model);
        return modelResponseDTO;
    }

    @Override
    public ModelResponseDTO getModel(String id) {
        Model model =modelRepo.findById(id).get();
        ModelResponseDTO modelResponseDTO=modMapper.ModelTOModelResponseDTO(model);
        return modelResponseDTO;
    }

    @Override
    public List<ModelResponseDTO> listModels() {
        List<Model> models=modelRepo.findAll();
        List<ModelResponseDTO> modelResponseDTOList=models.stream()
                .map(model -> modMapper.ModelTOModelResponseDTO(model))
                .collect(Collectors.toList());
        return modelResponseDTOList;
    }

    @Override
    public void updateModelDTO(ModelUpdateDTO dto) {
        Model model=modelRepo.findById(dto.getIdModel()).get();
        modMapper.updateModelFromDto(dto,model);
        modelRepo.save(model);

    }


}
