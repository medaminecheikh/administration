package com.administration.service;

import com.administration.dto.CaisseRequestDTO;
import com.administration.dto.CaisseResponseDTO;
import com.administration.dto.CaisseUpdateDTO;

import java.util.List;

public interface ICaisseService {
    CaisseResponseDTO addCaisse(CaisseRequestDTO caisseRequestDTO);
    CaisseResponseDTO getCaisse(String id);
    List<CaisseResponseDTO> listCaisses();
    void updateCaisseDTO(CaisseUpdateDTO dto);
    void affecterCaisseToUser(String idCaisse, String idUser);
    void removeUser(String idUser);
    void affecterCaisseToEtt(String idCaisse,String idEtt);

    void deleteCaisse(String idCaisse);
    List<CaisseResponseDTO> getCaissesByEttId(String Id);

}
