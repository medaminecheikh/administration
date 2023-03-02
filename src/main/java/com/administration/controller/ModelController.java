package com.administration.controller;

import com.administration.dto.ModelRequestDTO;
import com.administration.dto.ModelResponseDTO;
import com.administration.dto.ModelUpdateDTO;
import com.administration.service.IModelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "Gestion Models")
public class ModelController {
    IModelService IModelService;

    public ModelController(IModelService IModelService) {
        this.IModelService = IModelService;
    }
    @ApiOperation(value = "Récupérer la liste des Models")
    @GetMapping(path="/models")
    public List<ModelResponseDTO> allModels()
    {

        return IModelService.listModels();
    }

    @ApiOperation(value = "ajoute Model")
    @PostMapping(path="/ajoutemodel")
    public ModelResponseDTO save(@RequestBody ModelRequestDTO modelRequestDTO){
        return IModelService.addModel(modelRequestDTO);
    }
    @ApiOperation(value = "Récupérer Model")
    @GetMapping(path = "/model/{idModel}")
    public ModelResponseDTO getModel(@PathVariable String idModel){

        return IModelService.getModel(idModel);
    }

    @ApiOperation(value = "Update Model")
    @PutMapping("/update-model/")
    @ResponseBody
    public void UpdateModelDTO(@RequestBody ModelUpdateDTO dto) {
        IModelService.updateModelDTO(dto);
    }

    @ApiOperation(value = "Delete Model")
    @DeleteMapping("/deleteModel/{idModel}")
    public void deleteModel(@PathVariable String idModel){
        IModelService.deleteModel(idModel);
    }

}
