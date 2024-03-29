package com.administration.controller;

import com.administration.dto.FactureResponseDTO;
import com.administration.dto.FactureUpdateDTO;
import com.administration.entity.Encaissement;
import com.administration.entity.InfoFacture;
import com.administration.service.IFactureService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@RestController
@AllArgsConstructor
@Slf4j
public class FactureController {
    private final IFactureService factureService;

    @PostMapping("/facture")
    public ResponseEntity<FactureResponseDTO> addFacture(@RequestBody InfoFacture facture) {
        try {
            FactureResponseDTO addedFacture = factureService.addFacture(facture);
            return ResponseEntity.status(HttpStatus.CREATED).body(addedFacture);
        } catch (RuntimeException e) {
            log.error(e.getMessage());
            // Handle the exception appropriately, for example, log the error
            // and return a ResponseEntity with an appropriate status code and message
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
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
    public ResponseEntity<?> affectEncaissementToFacture(@PathVariable String factureId, @PathVariable String encaissementId) {
        try {
            FactureResponseDTO factureResponseDTO = factureService.affectEncaissementToFacture(encaissementId, factureId);
            return ResponseEntity.ok(factureResponseDTO);
        } catch (EntityNotFoundException e) {
            // Entity not found, return 404 Not Found status
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            // Other errors, return 500 Internal Server Error status
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/affectlistencaissement/tofacture/{factureId}")
    public ResponseEntity<?> affectlistEncaissementToFacture(@PathVariable String factureId, @RequestBody List<Encaissement> encaissements) {
        try {
            List<FactureResponseDTO> factureResponseDTO = new ArrayList<>();
            for (Encaissement encaissement : encaissements) {
                factureResponseDTO.add(factureService.affectEncaissementToFacture(encaissement.getIdEncaissement(), factureId));
            }
            return ResponseEntity.ok().body(factureResponseDTO);
        } catch (EntityNotFoundException e) {
            log.error("Facture not found for id: {}", factureId, e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            log.error("Error processing encaissements for factureId: {}", factureId, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
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
            @RequestParam(name = "solde", required = false) Double solde,
            @RequestParam(name = "status", required = false) String status,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "8") int size
    ) {
        Sort sort = Sort.by("datLimPai");
        PageRequest pageable = PageRequest.of(page, size, sort);

        if (Objects.equals(status, "TERMINER")) {
            return factureService.getFinishedFactures(
                    produitKeyword, refFactureKeyword, compteFacturationKeyword,
                    identifiantKeyword, montantMax, solde, pageable);
        } else if (Objects.equals(status, "COURS")) {
            return factureService.searchCoursFactures(
                    produitKeyword, refFactureKeyword, compteFacturationKeyword,
                    identifiantKeyword, montantMax, solde, pageable);
        } else if (Objects.equals(status, "RETARD")) {
            return factureService.searchRetardFactures(
                    produitKeyword, refFactureKeyword, compteFacturationKeyword,
                    identifiantKeyword, montantMax, solde);
        } else {
            return factureService.searchInfoFactures(
                    produitKeyword, refFactureKeyword, compteFacturationKeyword,
                    identifiantKeyword, montantMax, solde, pageable);
        }
    }

    @GetMapping("/factures/getallbypageFactures")
    public List<FactureResponseDTO> getallbypageFactures(@RequestParam(name = "produit",defaultValue = "", required = false) String produitKeyword,
                                                         @RequestParam(name = "refFacture",defaultValue = "", required = false) String refFactureKeyword,
                                                         @RequestParam(name = "compteFacturation",defaultValue = "", required = false) String compteFacturationKeyword,
                                                         @RequestParam(name = "identifiant",defaultValue = "", required = false) String identifiantKeyword,
                                                         @RequestParam(name = "montant",defaultValue = "", required = false) Double montantMax,
                                                         @RequestParam(name = "solde", required = false) Double solde,
                                                         @RequestParam(name = "status", required = false) String status,
                                                         @RequestParam(name = "page", defaultValue = "0") int page,
                                                         @RequestParam(name = "size", defaultValue = "8") int size) {
        Sort sort = Sort.by("datLimPai");
        PageRequest pageable = PageRequest.of(page, size, sort);
        List<FactureResponseDTO> responseDTOS=factureService.searchInfoFactures(
                produitKeyword, refFactureKeyword, compteFacturationKeyword,
                identifiantKeyword, montantMax, solde, pageable);
        log.info(responseDTOS.toString());
        return responseDTOS;
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

        return factureService.calculatePaymentAmount(facture, date);
    }

}
