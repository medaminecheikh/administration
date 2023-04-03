package com.administration.controller;

import com.administration.dto.DregionalRequestDTO;
import com.administration.dto.DregionalResponseDTO;
import com.administration.dto.DregionalUpdateDTO;
import com.administration.service.IDregService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "Gestion Dregionals")
public class DregionalController {
    IDregService IDregService;

    public DregionalController(IDregService IDregService) {
        this.IDregService = IDregService;
    }
    @ApiOperation(value = "Récupérer la liste des Dregionals")
    @GetMapping(path="/dregionals")
    public List<DregionalResponseDTO> allDregionals()
    {

        return IDregService.listDregionals();
    }

    @ApiOperation(value = "ajoute Dregional")
    @PostMapping(path="/ajouteDreg")
    public DregionalResponseDTO save(@RequestBody DregionalRequestDTO dregRequestDTO){
        return IDregService.addDreg(dregRequestDTO);
    }
    @ApiOperation(value = "Récupérer Dregional")
    @GetMapping(path = "/dreg/{idDregional}")
    public DregionalResponseDTO getDregional(@PathVariable String idDregional){

        return IDregService.getDregional(idDregional);
    }

    @ApiOperation(value = "Update Dregional")
    @PutMapping("/update-dreg/")
    @ResponseBody
    public void UpdateDregionalDTO(@RequestBody DregionalUpdateDTO dto) {
        IDregService.updateDregionalDTO(dto);
    }

    @ApiOperation(value = "Récupérer Dregionals par zone")
    @GetMapping(path = "/dregbyzone/{idZone}")
    public List<DregionalResponseDTO> getDregionalsByZone(@PathVariable String idZone){

        return IDregService.getDregionalsByZoneId(idZone);
    }
}
