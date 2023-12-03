package com.administration.controller;

import com.administration.dto.EncaissResponseDTO;
import com.administration.dto.EncaissUpdateDTO;
import com.administration.dto.FactureResponseDTO;
import com.administration.entity.Encaissement;
import com.administration.service.IEncaissService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@AllArgsConstructor

public class EncaissementController {
    private  IEncaissService encaissService;

    @PostMapping("/encaissement")
    public ResponseEntity<Encaissement> addEncaiss(@RequestBody Encaissement encaissement) {
        Encaissement addedEncaissement = encaissService.addEncaiss(encaissement);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedEncaissement);
    }

    @GetMapping("/encaissement/{id}")
    public ResponseEntity<EncaissResponseDTO> getEncaissById(@PathVariable String id) {
        EncaissResponseDTO encaissement = encaissService.getEncaissById(id);
        return ResponseEntity.ok(encaissement);
    }

    @GetMapping("/Byfacture/{idFact}")
    public ResponseEntity<List<EncaissResponseDTO>> getEncaissementByFacture(@PathVariable String idFact) {
        List<EncaissResponseDTO> encaissements = encaissService.getEncaissementByFacture(idFact);
        return ResponseEntity.ok(encaissements);
    }

    @GetMapping("/Byuser/{idUser}")
    public ResponseEntity<List<Encaissement>> getEncaissementByUser(@PathVariable String idUser) {
        List<Encaissement> encaissements = encaissService.getEncaissementByUser(idUser);
        return ResponseEntity.ok(encaissements);
    }

    @GetMapping("/Bycaisse/{idCaisse}")
    public ResponseEntity<List<EncaissResponseDTO>> getEncaissementByCaisse(@PathVariable String idCaisse) {
        List<EncaissResponseDTO> encaissements = encaissService.getEncaissementByCaisse(idCaisse);
        return ResponseEntity.ok(encaissements);
    }
    @GetMapping("/Allencaissement")
    public ResponseEntity<List<EncaissResponseDTO>> getAllEncaissement() {
        List<EncaissResponseDTO> encaissements = encaissService.getAllEncaissement();
        return ResponseEntity.ok(encaissements);
    }
    @GetMapping("/encaissement/searchPageEncaissement")
    public List<EncaissResponseDTO> searchPageEncaiss(
            @RequestParam(name = "produit", required = false, defaultValue = "") String produit,
            @RequestParam(name = "identifiant", required = false, defaultValue = "") String identifiant,
            @RequestParam(name = "modePaiement", required = false, defaultValue = "") String modePaiement,
            @RequestParam(name = "typeIdent", required = false, defaultValue = "") String typeIdent,
            @RequestParam(name = "montantEnc", required = false, defaultValue = "") Double montantEnc,
            @RequestParam(name = "refFacture", required = false, defaultValue = "") String refFacture,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size
    ) {
        Sort sort = Sort.by("dateEnc");
        PageRequest pageable = PageRequest.of(page, size, sort);

            return encaissService.searchEncaiss(
                    produit, identifiant, modePaiement,
                    typeIdent,montantEnc,refFacture, pageable);
    }
    @GetMapping("/encaissement/searchYearEncaissement")
    public List<EncaissResponseDTO> searchYearEncaiss(
            @RequestParam(name = "produit", required = false, defaultValue = "") String produit,
            @RequestParam(name = "identifiant", required = false, defaultValue = "") String identifiant,
            @RequestParam(name = "etatEncaissement", required = false, defaultValue = "") String etatEncaissement,
            @RequestParam(name = "typeIdent", required = false, defaultValue = "") String typeIdent,
            @RequestParam(name = "montantEnc", required = false, defaultValue = "") Double montantEnc,
            @RequestParam(name = "refFacture", required = false, defaultValue = "") String refFacture,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size
    ) {
        Sort sort = Sort.by("dateEnc");
        PageRequest pageable = PageRequest.of(page, size, sort);

            return encaissService.searchEncaissYear(
                    produit, identifiant, etatEncaissement,
                    typeIdent,montantEnc,refFacture, pageable);
    }
    @GetMapping("/encaissement/searchMonthEncaissement")
    public List<EncaissResponseDTO> searchMonthEncaiss(
            @RequestParam(name = "produit", required = false, defaultValue = "") String produit,
            @RequestParam(name = "identifiant", required = false, defaultValue = "") String identifiant,
            @RequestParam(name = "etatEncaissement", required = false, defaultValue = "") String etatEncaissement,
            @RequestParam(name = "typeIdent", required = false, defaultValue = "") String typeIdent,
            @RequestParam(name = "montantEnc", required = false, defaultValue = "") Double montantEnc,
            @RequestParam(name = "refFacture", required = false, defaultValue = "") String refFacture,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size
    ) {
        Sort sort = Sort.by("dateEnc");
        PageRequest pageable = PageRequest.of(page, size, sort);

            return encaissService.searchEncaissMonth(
                    produit, identifiant, etatEncaissement,
                    typeIdent,montantEnc,refFacture, pageable);
    }
    @GetMapping("/encaissement/searchWeekEncaissement")
    public List<EncaissResponseDTO> searchWeekEncaiss(
            @RequestParam(name = "produit", required = false, defaultValue = "") String produit,
            @RequestParam(name = "identifiant", required = false, defaultValue = "") String identifiant,
            @RequestParam(name = "etatEncaissement", required = false, defaultValue = "") String etatEncaissement,
            @RequestParam(name = "typeIdent", required = false, defaultValue = "") String typeIdent,
            @RequestParam(name = "montantEnc", required = false, defaultValue = "") Double montantEnc,
            @RequestParam(name = "refFacture", required = false, defaultValue = "") String refFacture,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size
    ) {
        Sort sort = Sort.by("dateEnc");
        PageRequest pageable = PageRequest.of(page, size, sort);

            return encaissService.searchEncaissWeek(
                    produit, identifiant, etatEncaissement,
                    typeIdent,montantEnc,refFacture, pageable);
    }


    @DeleteMapping("/encaissements/delete/{id}")
    public ResponseEntity<Void> deleteEncaiss(@PathVariable String id) {
        encaissService.deleteEncaisse(id);
        return ResponseEntity.noContent().build();
    }
    @PostMapping("/affectEncaisseToCaisse/{idEncaiss}/{idCai}")
    public ResponseEntity<?> affectEncaisseToCaisse(@PathVariable("idEncaiss") String idEncaiss,
                                                    @PathVariable("idCai") String idCai) {

        try {
            EncaissResponseDTO encaissResponseDTO = encaissService.affectEncaisseToCaisse(idEncaiss, idCai);
            return ResponseEntity.ok(encaissResponseDTO);
        } catch (EntityNotFoundException e) {
            // Entity not found, return 404 Not Found status
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            // Other errors, return 500 Internal Server Error status
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

    }
    @GetMapping("/encaissements/current-month-for-caisse")
    public List<EncaissResponseDTO> getEncaissementsForCaisseInCurrentMonth(@RequestParam String caisseId) {
        return encaissService.getEncaissementsForCaisseInCurrentMonth(caisseId);
    }
    @PutMapping("/encaissementupdate")
    public ResponseEntity<?> updateEncaiss(@RequestBody EncaissUpdateDTO encaissement) {
        try {
            encaissService.updateEncaisse(encaissement);
            // Return a JSON object with a success message
            return ResponseEntity.status(HttpStatus.OK).body(Map.of("message", "Update successful"));
        } catch (EntityNotFoundException e) {
            // Entity not found, return 404 Not Found status
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

}
