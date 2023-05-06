package com.administration.controller;

import com.administration.dto.FoncRequestDTO;
import com.administration.dto.FoncResponseDTO;
import com.administration.dto.FoncUpdateDTO;
import com.administration.service.IFoncService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "Gestion Fonctions")
public class FonctionaliteController {

    IFoncService IFoncService;

    public FonctionaliteController(IFoncService IFoncService) {
        this.IFoncService = IFoncService;
    }

    @ApiOperation(value = "Récupérer la liste des Foncs")
    @GetMapping(path="/foncs")
    public List<FoncResponseDTO> allFoncs()
    {

        return IFoncService.listFoncs();
    }

    @ApiOperation(value = "ajoute Fonc")
    @PostMapping(path="/ajoutefonc")
    public FoncResponseDTO save(@RequestBody FoncRequestDTO foncRequestDTO){
        return IFoncService.addFonc(foncRequestDTO);
    }
    @ApiOperation(value = "ajoute Fonc")
    @PostMapping(path="/ajoutesousfonc")
    public FoncResponseDTO savesous(@RequestBody FoncRequestDTO foncRequestDTO){
        return IFoncService.addsousFonc(foncRequestDTO);
    }
    @ApiOperation(value = "Récupérer Fonc")
    @GetMapping(path = "/fonc/{idFonc}")
    public FoncResponseDTO getFonc(@PathVariable String idFonc){

        return IFoncService.getFonc(idFonc);
    }
    @ApiOperation(value = "Update Fonc")
    @PutMapping("/update-fonc/")
    @ResponseBody
    public void UpdateFoncDTO(@RequestBody FoncUpdateDTO dto) {
        IFoncService.updateFoncDTO(dto);
    }

    @ApiOperation(value = "Affecter Model")
    @PutMapping("/affecterModelToFonc/{idModel}/{idFonc}")
    public void affecterModelToProfile(@PathVariable String idModel,@PathVariable String idFonc){
        IFoncService.affecterModelToFonc(idModel,idFonc);
    }
    @ApiOperation(value = "Delete Fonctionalite")
    @DeleteMapping("/DeleteFonctionalite/{idFonc}")
    public void DeleteEtt(@PathVariable String idFonc){
        IFoncService.deleteFonc(idFonc);
    }
    @ApiOperation(value = "Remove Model")
    @PutMapping("/removeModel/{idModel}/{idFonc}")
    public void removeModel(@PathVariable String idModel,@PathVariable String idFonc){
        IFoncService.removeModel(idModel,idFonc);
    }
    @GetMapping("/bymenu/{nomMenu}")
    public List<FoncResponseDTO> getFonctionsByNomMenu(@PathVariable String nomMenu) {
        return IFoncService.getFonctionsByNomMenu(nomMenu);
    }
}
