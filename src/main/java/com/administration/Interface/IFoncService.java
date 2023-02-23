package com.administration.Interface;

import com.administration.dto.FoncRequestDTO;
import com.administration.dto.FoncResponseDTO;
import com.administration.dto.FoncUpdateDTO;

import java.util.List;

public interface IFoncService {

    FoncResponseDTO addFonc(FoncRequestDTO RequestDTO);
    FoncResponseDTO getFonc(String id);
    List<FoncResponseDTO> listFoncs();
    void updateFoncDTO(FoncUpdateDTO dto);
    void affecterModelToFonc(String idModel,String idFonc);
    void deleteFonc(String idFonc);

    void removeModel(String idModel, String idFonc);
}
