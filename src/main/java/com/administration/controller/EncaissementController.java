package com.administration.controller;

import com.administration.entity.Encaissement;
import com.administration.service.IEncaissService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@PreAuthorize("hasAuthority('FO')")
public class EncaissementController {
    private final IEncaissService encaissService;

    @PostMapping("/encaissement")
    public ResponseEntity<Encaissement> addEncaiss(@RequestBody Encaissement encaissement) {
        Encaissement addedEncaissement = encaissService.addEncaiss(encaissement);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedEncaissement);
    }

    @GetMapping("/encaissement/{id}")
    public ResponseEntity<Encaissement> getEncaissById(@PathVariable String id) {
        Encaissement encaissement = encaissService.getEncaissById(id);
        return ResponseEntity.ok(encaissement);
    }

    @GetMapping("/Byfacture/{idFact}")
    public ResponseEntity<List<Encaissement>> getEncaissementByFacture(@PathVariable String idFact) {
        List<Encaissement> encaissements = encaissService.getEncaissementByFacture(idFact);
        return ResponseEntity.ok(encaissements);
    }

    @GetMapping("/Byuser/{idUser}")
    public ResponseEntity<List<Encaissement>> getEncaissementByUser(@PathVariable String idUser) {
        List<Encaissement> encaissements = encaissService.getEncaissementByUser(idUser);
        return ResponseEntity.ok(encaissements);
    }

    @GetMapping("/Bycaisse/{idCaisse}")
    public ResponseEntity<List<Encaissement>> getEncaissementByCaisse(@PathVariable String idCaisse) {
        List<Encaissement> encaissements = encaissService.getEncaissementByCaisse(idCaisse);
        return ResponseEntity.ok(encaissements);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteEncaiss(@PathVariable String id) {
        encaissService.deleteEncaisse(id);
        return ResponseEntity.noContent().build();
    }
}
