package com.administration.controller;

import com.administration.dto.ZoneRequestDTO;
import com.administration.dto.ZoneResponseDTO;
import com.administration.dto.ZoneUpdateDTO;
import com.administration.service.IZoneService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "Gestion Zones")
public class Zone {
    IZoneService IZoneService;

    public Zone(IZoneService IZoneService) {
        this.IZoneService = IZoneService;
    }

    @ApiOperation(value = "Récupérer la liste des Zones")
    @GetMapping(path="/zones")
    public List<ZoneResponseDTO> allZones()
    {

        return IZoneService.listZones();
    }

    @ApiOperation(value = "ajoute Zone")
    @PostMapping(path="/ajoutezone")
    public ZoneResponseDTO save(@RequestBody ZoneRequestDTO zoneRequestDTO){
        return IZoneService.addZone(zoneRequestDTO);
    }
    @ApiOperation(value = "Récupérer Zone")
    @GetMapping(path = "/zone/{idZone}")
    public ZoneResponseDTO getZone(@PathVariable String idZone){

        return IZoneService.getZone(idZone);
    }

    @ApiOperation(value = "Update Zone")
    @PutMapping("/update-zone/")
    @ResponseBody
    public void UpdateZoneDTO(@RequestBody ZoneUpdateDTO dto) {
        IZoneService.updateZoneDTO(dto);
    }

    @ApiOperation(value = "Affecter Dregionale")
    @PutMapping("/affecterDregToZone/{idDreg}/{idZone}")
    public void affecterDregToZone(@PathVariable String idDreg,@PathVariable String idZone){
        IZoneService.affecterDregToZone(idDreg,idZone);
    }
    @ApiOperation(value = "remove Dregionale")
    @PutMapping("/removeDreg/{idDreg}")
    public void affecterDregToZone(@PathVariable String idDreg){
        IZoneService.removeDreg(idDreg);
    }
    @ApiOperation(value = "Delete Zone")
    @DeleteMapping("/DeleteZone/{idZone}")
    public void DeleteZone(@PathVariable String idZone){
        IZoneService.deleteZone(idZone);
    }

}
