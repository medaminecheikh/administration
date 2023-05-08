package com.administration.service;

import com.administration.dto.FoncRequestDTO;
import com.administration.dto.FoncResponseDTO;
import com.administration.dto.FoncUpdateDTO;

import java.util.List;

public interface IFoncService {

    FoncResponseDTO addFonc(FoncRequestDTO RequestDTO);
    FoncResponseDTO getFonc(String id);
    FoncResponseDTO getFoncbyNom(String nom);
    List<FoncResponseDTO> listFoncs();
    void updateFoncDTO(FoncUpdateDTO dto);
    void affecterModelToFonc(String idModel,String idFonc);
    void deleteFonc(String idFonc);

    void removeModel(String idModel, String idFonc);

     List<FoncResponseDTO> getFonctionsByNomMenu(String nomMENU);

    List<FoncResponseDTO> findFonctionsByNomMenu(String nomMENU);

    void initializeFonctions(List<FoncRequestDTO> fonctions);
    FoncResponseDTO addsousFonc(FoncRequestDTO foncRequestDTO);
}
