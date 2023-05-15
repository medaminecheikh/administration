package com.administration.service.impl;

import com.administration.dto.FoncRequestDTO;
import com.administration.dto.FoncResponseDTO;
import com.administration.dto.FoncUpdateDTO;
import com.administration.entity.Fonction;
import com.administration.entity.Model;
import com.administration.service.mappers.FoncMapper;
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
        foncRepo.save(fonction);
        return foncMapper.FonctionaliteTOFonctionaliteResponseDTO(fonction);
    }

    @Override
    public FoncResponseDTO getFonc(String id) {
        Fonction fonction =foncRepo.findById(id).get();
        return foncMapper.FonctionaliteTOFonctionaliteResponseDTO(fonction);
    }

    @Override
    public FoncResponseDTO getFoncbyNom(String nom) {
        Fonction fonction =foncRepo.findByNomF(nom);
        return foncMapper.FonctionaliteTOFonctionaliteResponseDTO(fonction);
    }

    @Override
    public List<FoncResponseDTO> listFoncs() {
        List<Fonction> fonctions =foncRepo.findAll();
        return fonctions.stream()
                .map(fonctionalite -> foncMapper.FonctionaliteTOFonctionaliteResponseDTO(fonctionalite))
                .collect(Collectors.toList());
    }

    @Override
    public void updateFoncDTO(FoncUpdateDTO dto) {
        Fonction fonction =foncRepo.findById(dto.getIdFonc()).get();
        foncMapper.updateFonctionaliteFromDto(dto, fonction);
        foncRepo.save(fonction);

    }

    @Override
    public void affecterModelToFonc(String idModel, String idFonc) {
        Model model = modelRepo.findById(idModel).orElseThrow(() -> new RuntimeException("Model not found"));
        Fonction fonction = foncRepo.findById(idFonc).orElseThrow(() -> new RuntimeException("Function not found"));
        boolean exist = false;
        for (Fonction fonction1 : model.getFonctions()) {
            if (fonction1.getCodF().equals(idFonc)) {
                exist = true;
                break;
            }
        }
        if (!exist) {
            model.getFonctions().add(fonction);
            modelRepo.save(model);
        } else {
            throw new RuntimeException("This model already has this function");
        }
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

    @Override
    public List<FoncResponseDTO> getFonctionsByNomMenu(String nomMENU) {
      List<Fonction> fonctionList= foncRepo.findByNomMENUAndFon_COD_FIsNotNull(nomMENU, "");
        return fonctionList.stream()
                .map(fonctionalite -> foncMapper.FonctionaliteTOFonctionaliteResponseDTO(fonctionalite))
                .collect(Collectors.toList());
    }
    @Override
    public List<FoncResponseDTO> findFonctionsByNomMenu(String nomMENU) {
        List<Fonction> fonctionList= foncRepo.findByNomMENU(nomMENU);
        return fonctionList.stream()
                .map(fonctionalite -> foncMapper.FonctionaliteTOFonctionaliteResponseDTO(fonctionalite))
                .collect(Collectors.toList());
    }
    @Override
    public void initializeFonctions(List<FoncRequestDTO> fonctions) {
        for (FoncRequestDTO fonc : fonctions) {
            FoncResponseDTO existingFonc = getFoncbyNom(fonc.getNomF());

            if (existingFonc == null) {
                addFonc(fonc);
            }
        }
    }


    @Override
    public FoncResponseDTO addsousFonc(FoncRequestDTO foncRequestDTO) {
        Fonction fonction = foncMapper.FonctionaliteRequestDTOFonctionalite(foncRequestDTO);
        fonction.setIdFonc(UUID.randomUUID().toString());
        fonction.setFon_COD_F(foncRequestDTO.getNomMENU()+foncRequestDTO.getFon_COD_F());
        foncRepo.save(fonction);
        return foncMapper.FonctionaliteTOFonctionaliteResponseDTO(fonction);
    }
}
