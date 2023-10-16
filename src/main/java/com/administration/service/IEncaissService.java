package com.administration.service;

import com.administration.dto.EncaissResponseDTO;
import com.administration.dto.EncaissUpdateDTO;
import com.administration.entity.Encaissement;

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

    void affectEncaisseToCaisse(String idEncaiss,String idcai);
    void affectEncaisseToUser(String idEncaiss,String idUser);
    void affectAll(String idEncaiss,String idUser,String idcai);

}
