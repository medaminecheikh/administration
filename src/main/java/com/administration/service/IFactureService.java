package com.administration.service;

import com.administration.entity.InfoFacture;

import java.util.List;

public interface IFactureService {

    InfoFacture addFacture(InfoFacture facture);
    List<InfoFacture> getAll(String identifiant,String ref,int apl,int page, int size);
    List<InfoFacture> getByUser(String idUser);
    void updateFacture(InfoFacture facture);
    void deleteFacture(String idFacture);
    void affectOp(String idop, String idfac);
    void affectUser(String idUser, String idfac);


}
