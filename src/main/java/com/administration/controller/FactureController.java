package com.administration.controller;

import com.administration.entity.InfoFacture;
import com.administration.service.IFactureService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class FactureController {
    private final IFactureService factureService;

    @PostMapping("/facture")
    public ResponseEntity<InfoFacture> addFacture(@RequestBody InfoFacture facture) {
        InfoFacture addedFacture = factureService.addFacture(facture);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedFacture);
    }

    @GetMapping("/all")
    public ResponseEntity<List<InfoFacture>> getAllFactures(@RequestParam(name = "identifiant",defaultValue = "") String identifiant,
                                                            @RequestParam(name = "ref",defaultValue = "") String ref,
                                                            @RequestParam(name = "apl",defaultValue = "") Integer apl,
                                                            @RequestParam(defaultValue = "0") int page,
                                                            @RequestParam(defaultValue = "10") int size) {
        List<InfoFacture> factures = factureService.getAll("%"+identifiant+"%", "%"+ref+"%", apl, page, size);
        return ResponseEntity.ok(factures);
    }

    @GetMapping("/facturebyuser/{idUser}")
    public ResponseEntity<List<InfoFacture>> getFacturesByUser(@PathVariable String idUser) {
        List<InfoFacture> factures = factureService.getByUser(idUser);
        return ResponseEntity.ok(factures);
    }

    @PutMapping("/updatefacture/{idFacture}")
    public ResponseEntity<Void> updateFacture(@PathVariable String idFacture, @RequestBody InfoFacture facture) {
        facture.setIdFacture(idFacture);
        factureService.updateFacture(facture);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete/{idFacture}")
    public ResponseEntity<Void> deleteFacture(@PathVariable String idFacture) {
        factureService.deleteFacture(idFacture);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/affectencaissement/{factureId}/{encaissementId}")
    public ResponseEntity<Void> affectEncaissementToFacture(@PathVariable String encaissementId,
                                                            @PathVariable String factureId) {
        factureService.affectEncaissementToFacture(encaissementId, factureId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/removencaissement/{factureId}/{encaissementId}")
    public ResponseEntity<Void> removeEncaissementFromFacture(@PathVariable String encaissementId,
                                                              @PathVariable String factureId) {
        factureService.removeEncaissementFromFacture(encaissementId, factureId);
        return ResponseEntity.ok().build();
    }


}
