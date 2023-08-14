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

    @GetMapping("/findall")
    public ResponseEntity<List<InfoFacture>> getAllFactures(@RequestParam(name = "identifiant", defaultValue = "") String identifiant,
                                                            @RequestParam(name = "ref", defaultValue = "") String ref,
                                                            @RequestParam(name = "apl", defaultValue = "") Integer apl,
                                                            @RequestParam(defaultValue = "0") int page,
                                                            @RequestParam(defaultValue = "10") int size) {
        List<InfoFacture> factures = factureService.getAll("%" + identifiant + "%", "%" + ref + "%", apl, page, size);

        return ResponseEntity.ok(factures);
    }

    @GetMapping("/findallfacture")
    public ResponseEntity<List<InfoFacture>> findAllFactures(@RequestParam(name = "identifiant", defaultValue = "") String identifiant,
                                                             @RequestParam(name = "ref", defaultValue = "") String ref,
                                                             @RequestParam(name = "apl", defaultValue = "") Integer apl) {
        List<InfoFacture> factures = factureService.getAllfacture("%" + identifiant + "%", "%" + ref + "%", apl);
        return ResponseEntity.ok(factures);
    }

    @GetMapping("/allfactures")
    public ResponseEntity<List<InfoFacture>> getAllFactures() {
        List<InfoFacture> factures = factureService.getAllFactures();
        return ResponseEntity.ok(factures);
    }

    @GetMapping("/facturebyuser/{idUser}")
    public ResponseEntity<List<InfoFacture>> getFacturesByUser(@PathVariable String idUser) {
        List<InfoFacture> factures = factureService.getByUser(idUser);
        return ResponseEntity.ok(factures);
    }

    @PutMapping("/updatefacture/")
    public ResponseEntity<Void> updateFacture(@RequestBody InfoFacture facture) {
        factureService.updateFacture(facture);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/deletefacture/{idFacture}")
    public ResponseEntity<Void> deleteFacture(@PathVariable String idFacture) {
        factureService.deleteFacture(idFacture);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/affectencaissement/tofacture/{factureId}/{encaissementId}")
    public ResponseEntity<Void> affectEncaissementToFacture(@PathVariable String factureId, @PathVariable String encaissementId
    ) {
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
