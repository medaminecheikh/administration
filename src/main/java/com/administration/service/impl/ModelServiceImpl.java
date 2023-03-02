package com.administration.service.impl;

import com.administration.dto.ModelRequestDTO;
import com.administration.dto.ModelResponseDTO;
import com.administration.dto.ModelUpdateDTO;
import com.administration.entity.Model;
import com.administration.mappers.ModMapper;
import com.administration.repo.ModelRepo;
import com.administration.repo.ProfileRepo;
import com.administration.service.IModelService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class ModelServiceImpl implements IModelService {
    ModelRepo modelRepo;
    ModMapper modMapper;
    ProfileRepo profileRepo;


    @Override
    public ModelResponseDTO addModel(ModelRequestDTO RequestDTO) {
        Model model=modMapper.ModelRequestDTOModel(RequestDTO);
        model.setIdModel(UUID.randomUUID().toString());
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

    @Override
    public void deleteModel(String idModel) {
        Model model=modelRepo.findById(idModel).get();
        if (model.getFonctions().isEmpty()&&model.getProfils().isEmpty())
        {
            modelRepo.deleteById(idModel);
        }else throw new RuntimeException("This model has associations !");
    }


}
