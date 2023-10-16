package com.administration.controller;

import com.administration.dto.FactureResponseDTO;
import com.administration.dto.FactureUpdateDTO;
import com.administration.entity.InfoFacture;
import com.administration.service.IFactureService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;
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
    public ResponseEntity<List<FactureResponseDTO>> getAllFactures() {
        List<FactureResponseDTO> factures = factureService.getAllFactures();
        return ResponseEntity.ok(factures);
    }

    @GetMapping("/facturebyuser/{idUser}")
    public ResponseEntity<List<InfoFacture>> getFacturesByUser(@PathVariable String idUser) {
        List<InfoFacture> factures = factureService.getByUser(idUser);
        return ResponseEntity.ok(factures);
    }

    @PutMapping("/updatefacture/")
    public ResponseEntity<Void> updateFacture(@RequestBody FactureUpdateDTO facture) {
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

    @GetMapping("/factures/searchPageFactures")
    public List<FactureResponseDTO> searchPageFactures(
            @RequestParam(name = "produit", required = false) String produitKeyword,
            @RequestParam(name = "refFacture", required = false) String refFactureKeyword,
            @RequestParam(name = "compteFacturation", required = false) String compteFacturationKeyword,
            @RequestParam(name = "identifiant", required = false) String identifiantKeyword,
            @RequestParam(name = "montant", required = false) Double montantMax,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "8") int size
    ) {
        Sort sort = Sort.by("datLimPai");
        PageRequest pageable = PageRequest.of(page, size, sort);


        return factureService.searchInfoFactures(
                produitKeyword, refFactureKeyword, compteFacturationKeyword,
                identifiantKeyword,montantMax, pageable);
    }
    @GetMapping("/factures/monthlyFactures")
    public List<FactureResponseDTO> monthlyFactures() {
        return factureService.getMonthlyFactures();
    }
    @GetMapping("/factures/yearlyFactures")
    public List<FactureResponseDTO> yearlyFactures() {
        return factureService.getYearlyFactures();
    }
    @GetMapping("/factures/amountopay")
    public double amountopay(@RequestBody InfoFacture facture,
                                               @RequestParam Date date) {

        return  factureService.calculatePaymentAmount(facture , date);
    }

}
