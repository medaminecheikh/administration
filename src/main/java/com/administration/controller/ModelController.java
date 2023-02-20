package com.administration.controller;

import com.administration.dto.ModelRequestDTO;
import com.administration.dto.ModelResponseDTO;
import com.administration.dto.ModelUpdateDTO;
import com.administration.service.ModelService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ModelController {
    ModelService modelService;

    public ModelController(ModelService modelService) {
        this.modelService = modelService;
    }
    @ApiOperation(value = "Récupérer la liste des Models")
    @GetMapping(path="/models")
    public List<ModelResponseDTO> allModels()
    {

        return modelService.listModels();
    }

    @ApiOperation(value = "ajoute Model")
    @PostMapping(path="/ajoutemodel")
    public ModelResponseDTO save(@RequestBody ModelRequestDTO modelRequestDTO){
        return modelService.addModel(modelRequestDTO);
    }
    @ApiOperation(value = "Récupérer Model")
    @GetMapping(path = "/model/{idModel}")
    public ModelResponseDTO getModel(@PathVariable String idModel){

        return modelService.getModel(idModel);
    }

    @ApiOperation(value = "Update Model")
    @PutMapping("/update-model/")
    @ResponseBody
    public void UpdateModelDTO(@RequestBody ModelUpdateDTO dto) {
        modelService.updateModelDTO(dto);
    }

    @ApiOperation(value = "Delete Model")
    @DeleteMapping("/deleteModel/{idModel}")
    public void deleteModel(@PathVariable String idModel){
        modelService.deleteModel(idModel);
    }

}
