package com.administration.controller;

import com.administration.dto.EttRequestDTO;
import com.administration.dto.EttResponseDTO;
import com.administration.dto.EttUpdateDTO;
import com.administration.service.IEttService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "Gestion ETT")
public class EttController {
    IEttService IEttService;
    public EttController(IEttService IEttService) {

        this.IEttService = IEttService;
    }
    @ApiOperation(value = "Récupérer la liste des Etts")
    @GetMapping(path="/etts")
    public List<EttResponseDTO> allEtts()
    {

        return IEttService.listEtts();
    }

    @ApiOperation(value = "ajoute Ett")
    @PostMapping(path="/ajouteett")
    public EttResponseDTO save(@RequestBody EttRequestDTO ettRequestDTO){
        return IEttService.addEtt(ettRequestDTO);
    }
    @ApiOperation(value = "Récupérer Ett")
    @GetMapping(path = "/ett/{idEtt}")
    public EttResponseDTO getEtt(@PathVariable String idEtt){

        return IEttService.getEtt(idEtt);
    }

    @ApiOperation(value = "Update Ett")
    @PutMapping("/update-ett/")
    @ResponseBody
    public void UpdateEttDTO(@RequestBody EttUpdateDTO dto) {
        IEttService.updateEttDTO(dto);
    }

    @ApiOperation(value = "Affecter Zone")
    @PutMapping("/affecterEttToZone/{idZone}/{idEtt}")
    public void affecterEttToZone(@PathVariable String idZone,@PathVariable String idEtt){
        IEttService.affecterEttToZone(idEtt,idZone);
    }
    @ApiOperation(value = "Affecter Dregional")
    @PutMapping("/affecterEttToDregional/{idEtt}/{idDreg}")
    public void affecterEttToDreg(@PathVariable String idEtt,@PathVariable String idDreg){
        IEttService.affecterEttToDreg(idEtt,idDreg);
    }

    @ApiOperation(value = "remove Zone")
    @PutMapping("/removeZone/{idZone}")
    public void removeZone(@PathVariable String idZone){
        IEttService.removeZone(idZone);
    }

    @ApiOperation(value = "Delete Ett")
    @DeleteMapping("/DeleteEtt/{idEtt}")
    public void DeleteEtt(@PathVariable String idEtt){
        IEttService.deleteEtt(idEtt);
    }

    @ApiOperation(value = "Récupérer Ett par dr")
    @GetMapping(path = "/ettbydr/{drId}")
    public List<EttResponseDTO> getEttbyDr(@PathVariable String drId){

        return IEttService.getEttsByDrId(drId);
    }
}
