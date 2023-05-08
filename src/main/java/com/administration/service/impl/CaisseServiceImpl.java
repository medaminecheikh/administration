package com.administration.service.impl;

import com.administration.dto.CaisseRequestDTO;
import com.administration.dto.CaisseResponseDTO;
import com.administration.dto.CaisseUpdateDTO;
import com.administration.service.ICaisseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CaisseServiceImpl implements ICaisseService {
    @Override
    public CaisseResponseDTO addCaisse(CaisseRequestDTO caisseRequestDTO) {
        return null;
    }

    @Override
    public CaisseResponseDTO getCaisse(String id) {
        return null;
    }

    @Override
    public List<CaisseResponseDTO> listCaisses() {
        return null;
    }

    @Override
    public void updateCaisseDTO(CaisseUpdateDTO dto) {

    }

    @Override
    public void affecterCaisseToUser(String idCaisse, String idUser) {

    }

    @Override
    public void removeUser(String idCaisse) {

    }

    @Override
    public void affecterCaisseToEtt(String idCaisse, String idEtt) {

    }

    @Override
    public void deleteCaisse(String idCaisse) {

    }

    @Override
    public List<CaisseResponseDTO> getCaissesByEttId(String Id) {
        return null;
    }
}
