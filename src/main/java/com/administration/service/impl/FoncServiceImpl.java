package com.administration.service.impl;

import com.administration.dto.FoncRequestDTO;
import com.administration.dto.FoncResponseDTO;
import com.administration.dto.FoncUpdateDTO;
import com.administration.entity.Fonction;
import com.administration.entity.Model;
import com.administration.mappers.FoncMapper;
import com.administration.repo.FoncRepo;
import com.administration.repo.ModelRepo;
import com.administration.service.IFoncService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class FoncServiceImpl implements IFoncService {
    FoncRepo foncRepo;
     FoncMapper foncMapper;
     ModelRepo modelRepo;


    @Override
    public FoncResponseDTO addFonc(FoncRequestDTO RequestDTO) {
        Fonction fonction = foncMapper.FonctionaliteRequestDTOFonctionalite(RequestDTO);
        fonction.setIdFonc(UUID.randomUUID().toString());
        fonction.setDesF(RequestDTO.getFON_COD_F()+RequestDTO.getDesF());
        foncRepo.save(fonction);
        FoncResponseDTO foncResponseDTO=foncMapper.FonctionaliteTOFonctionaliteResponseDTO(fonction);
        return foncResponseDTO;
    }

    @Override
    public FoncResponseDTO getFonc(String id) {
        Fonction fonction =foncRepo.findById(id).get();
        FoncResponseDTO foncResponseDTO=foncMapper.FonctionaliteTOFonctionaliteResponseDTO(fonction);
        return foncResponseDTO;
    }

    @Override
    public List<FoncResponseDTO> listFoncs() {
        List<Fonction> fonctions =foncRepo.findAll();
        List<FoncResponseDTO> foncResponseDTOS= fonctions.stream()
                .map(fonctionalite -> foncMapper.FonctionaliteTOFonctionaliteResponseDTO(fonctionalite))
                .collect(Collectors.toList());
        return foncResponseDTOS;
    }

    @Override
    public void updateFoncDTO(FoncUpdateDTO dto) {
        Fonction fonction =foncRepo.findById(dto.getIdFonc()).get();
        foncMapper.updateFonctionaliteFromDto(dto, fonction);
        foncRepo.save(fonction);

    }

    @Override
    public void affecterModelToFonc(String idModel, String idFonc) {
        Model model=modelRepo.findById(idModel).get();
        Fonction fonction =foncRepo.findById(idFonc).get();
        boolean exist=false;
        for (Fonction fonction1 :model.getFonctions())
        {
            if (fonction1.getCodF()==idFonc){exist=true;}
        }
        if (exist==false){
        model.getFonctions().add(fonction);
        modelRepo.save(model);}else throw new RuntimeException("This model already exist");


    }

    @Override
    public void deleteFonc(String idFonc) {
        Fonction fonction =foncRepo.findById(idFonc).get();
        if (fonction.getModels().isEmpty()&& fonction.getProfils().isEmpty())
        {
            foncRepo.deleteById(idFonc);
        }else throw new RuntimeException("This fonctionalite has associations");
    }

    @Override
    public void removeModel(String idModel, String idFonc) {
        Model model=modelRepo.findById(idModel).get();
        Fonction fonction1 =foncRepo.findById(idFonc).get();
                model.getFonctions().remove(fonction1);
                modelRepo.save(model);
            }
}
