package com.administration.controller;

import com.administration.dto.FoncRequestDTO;
import com.administration.dto.FoncResponseDTO;
import com.administration.dto.FoncUpdateDTO;
import com.administration.service.FoncService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FonctionaliteController {

    FoncService foncService;

    public FonctionaliteController(FoncService foncService) {
        this.foncService = foncService;
    }

    @ApiOperation(value = "Récupérer la liste des Foncs")
    @GetMapping(path="/foncs")
    public List<FoncResponseDTO> allFoncs()
    {

        return foncService.listFoncs();
    }

    @ApiOperation(value = "ajoute Fonc")
    @PostMapping(path="/ajoutefonc")
    public FoncResponseDTO save(@RequestBody FoncRequestDTO foncRequestDTO){
        return foncService.addFonc(foncRequestDTO);
    }
    @ApiOperation(value = "Récupérer Fonc")
    @GetMapping(path = "/fonc/{idFonc}")
    public FoncResponseDTO getFonc(@PathVariable String idFonc){

        return foncService.getFonc(idFonc);
    }
    @ApiOperation(value = "Update Fonc")
    @PutMapping("/update-fonc/")
    @ResponseBody
    public void UpdateFoncDTO(@RequestBody FoncUpdateDTO dto) {
        foncService.updateFoncDTO(dto);
    }

    @ApiOperation(value = "Affecter Model")
    @PutMapping("/affecterModelToFonc/{idModel}/{idFonc}")
    public void affecterModelToProfile(@PathVariable String idModel,@PathVariable String idFonc){
        foncService.affecterModelToFonc(idModel,idFonc);
    }
    @ApiOperation(value = "Delete Fonctionalite")
    @DeleteMapping("/DeleteFonctionalite/{idFonc}")
    public void DeleteEtt(@PathVariable String idFonc){
        foncService.deleteFonc(idFonc);
    }

}
