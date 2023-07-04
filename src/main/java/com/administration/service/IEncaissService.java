package com.administration.service;

import com.administration.entity.Encaissement;

import java.util.List;

public interface IEncaissService {
    Encaissement addEncaiss(Encaissement encaissement);

    Encaissement getEncaissById(String id);

    List<Encaissement> getEncaissementByFacture(String idFact);
    List<Encaissement> getEncaissementByUser(String idUser);
    List<Encaissement> getEncaissementByCaisse(String idCaisse);
    void updateEncaisse(Encaissement dto);
    void deleteEncaisse(String idEncaiss);
    void affectEncaisseToOperation(String idEncaiss,String idOp);
    void affectEncaisseToCaisse(String idEncaiss,String idcai);
    void affectEncaisseToUser(String idEncaiss,String idUser);
    void affectAll(String idEncaiss,String idUser,String idcai);

}
