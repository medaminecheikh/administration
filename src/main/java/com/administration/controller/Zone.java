package com.administration.controller;

import com.administration.dto.ZoneRequestDTO;
import com.administration.dto.ZoneResponseDTO;
import com.administration.dto.ZoneUpdateDTO;
import com.administration.service.ZoneService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Zone {
    ZoneService zoneService;

    public Zone(ZoneService zoneService) {
        this.zoneService = zoneService;
    }

    @ApiOperation(value = "Récupérer la liste des Zones")
    @GetMapping(path="/zones")
    public List<ZoneResponseDTO> allZones()
    {

        return zoneService.listZones();
    }

    @ApiOperation(value = "ajoute Zone")
    @PostMapping(path="/ajoutezone")
    public ZoneResponseDTO save(@RequestBody ZoneRequestDTO zoneRequestDTO){
        return zoneService.addZone(zoneRequestDTO);
    }
    @ApiOperation(value = "Récupérer Zone")
    @GetMapping(path = "/zone/{idZone}")
    public ZoneResponseDTO getZone(@PathVariable String idZone){

        return zoneService.getZone(idZone);
    }

    @ApiOperation(value = "Update Zone")
    @PutMapping("/update-zone/")
    @ResponseBody
    public void UpdateZoneDTO(@RequestBody ZoneUpdateDTO dto) {
        zoneService.updateZoneDTO(dto);
    }

    @ApiOperation(value = "Affecter Dregionale")
    @PutMapping("/affecterDregToZone/{idDreg}/{idZone}")
    public void affecterDregToZone(@PathVariable String idDreg,@PathVariable String idZone){
        zoneService.affecterDregToZone(idDreg,idZone);
    }
    @ApiOperation(value = "remove Dregionale")
    @PutMapping("/removeDreg/{idDreg}")
    public void affecterDregToZone(@PathVariable String idDreg){
        zoneService.removeDreg(idDreg);
    }
    @ApiOperation(value = "Delete Zone")
    @DeleteMapping("/DeleteZone/{idZone}")
    public void DeleteZone(@PathVariable String idZone){
        zoneService.deleteZone(idZone);
    }

}
