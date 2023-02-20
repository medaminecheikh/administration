package com.administration.controller;

import com.administration.dto.DregionalRequestDTO;
import com.administration.dto.DregionalResponseDTO;
import com.administration.dto.DregionalUpdateDTO;
import com.administration.service.DregService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DregionalController {
    DregService dregService;

    public DregionalController(DregService dregService) {
        this.dregService = dregService;
    }
    @ApiOperation(value = "Récupérer la liste des Dregionals")
    @GetMapping(path="/dregionals")
    public List<DregionalResponseDTO> allDregionals()
    {

        return dregService.listDregionals();
    }

    @ApiOperation(value = "ajoute Dregional")
    @PostMapping(path="/ajouteDreg")
    public DregionalResponseDTO save(@RequestBody DregionalRequestDTO dregRequestDTO){
        return dregService.addDreg(dregRequestDTO);
    }
    @ApiOperation(value = "Récupérer Dregional")
    @GetMapping(path = "/dreg/{idDregional}")
    public DregionalResponseDTO getDregional(@PathVariable String idDregional){

        return dregService.getDregional(idDregional);
    }

    @ApiOperation(value = "Update Dregional")
    @PutMapping("/update-dreg/")
    @ResponseBody
    public void UpdateDregionalDTO(@RequestBody DregionalUpdateDTO dto) {
        dregService.updateDregionalDTO(dto);
    }

}
