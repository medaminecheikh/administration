package com.administration.service;

import com.administration.dto.EncaissResponseDTO;
import com.administration.dto.EncaissUpdateDTO;
import com.administration.dto.FactureResponseDTO;
import com.administration.entity.Encaissement;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface IEncaissService {
    Encaissement addEncaiss(Encaissement encaissement);

    EncaissResponseDTO getEncaissById(String id);

    List<EncaissResponseDTO> getEncaissementByFacture(String idFact);
    List<Encaissement> getEncaissementByUser(String idUser);
    List<EncaissResponseDTO> getEncaissementByCaisse(String idCaisse);
    List<EncaissResponseDTO> getEncaissementsForCaisseInCurrentMonth(String caisseId);
    void updateEncaisse(EncaissUpdateDTO dto);
    void deleteEncaisse(String idEncaiss);

    EncaissResponseDTO affectEncaisseToCaisse(String idEncaiss, String idcai);
    void affectEncaisseToUser(String idEncaiss,String idUser);
    void affectAll(String idEncaiss,String idUser,String idcai);

    List<EncaissResponseDTO> getAllEncaissement();

    List<EncaissResponseDTO> searchEncaiss(String produit, String identifiant, String modePaiement, String typeIdent, Double montantEnc, String refFacture, PageRequest pageable);
    List<EncaissResponseDTO> searchEncaissWeek(String produit, String identifiant, String etatEncaissement, String typeIdent, Double montantEnc, String refFacture, PageRequest pageable);
    List<EncaissResponseDTO> searchEncaissMonth(String produit, String identifiant, String etatEncaissement, String typeIdent, Double montantEnc, String refFacture, PageRequest pageable);
    List<EncaissResponseDTO> searchEncaissYear(String produit, String identifiant, String etatEncaissement, String typeIdent, Double montantEnc, String refFacture, PageRequest pageable);

    List<EncaissResponseDTO> encaissYear();

}
