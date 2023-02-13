package com.administration.controller;

import com.administration.dto.EttRequestDTO;
import com.administration.dto.EttResponseDTO;
import com.administration.dto.EttUpdateDTO;
import com.administration.service.EttService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EttController {
    EttService ettService;
    public EttController(EttService ettService) {

        this.ettService = ettService;
    }
    @ApiOperation(value = "Récupérer la liste des Etts")
    @GetMapping(path="/etts")
    public List<EttResponseDTO> allEtts()
    {

        return ettService.listEtts();
    }

    @ApiOperation(value = "ajoute Ett")
    @PostMapping(path="/ahouteett")
    public EttResponseDTO save(@RequestBody EttRequestDTO ettRequestDTO){
        return ettService.addEtt(ettRequestDTO);
    }
    @ApiOperation(value = "Récupérer Ett")
    @GetMapping(path = "/ett/{idEtt}")
    public EttResponseDTO getEtt(@PathVariable String idEtt){

        return ettService.getEtt(idEtt);
    }

    @ApiOperation(value = "Update Ett")
    @PutMapping("/update-ett/")
    @ResponseBody
    public void UpdateEttDTO(@RequestBody EttUpdateDTO dto) {
        ettService.updateEttDTO(dto);
    }
    @ApiOperation(value = "Affecter User")
    @PutMapping("/affecterUserToEtt/{idUser}/{idEtt}")
    public void affecterUserToEtt(@PathVariable String idUser,@PathVariable String idEtt){
        ettService.affecterUserToEtt(idUser,idEtt);
    }

}
