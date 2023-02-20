package com.administration.service;

import com.administration.dto.FoncRequestDTO;
import com.administration.dto.FoncResponseDTO;
import com.administration.dto.FoncUpdateDTO;
import com.administration.entity.Fonctionalite;
import com.administration.entity.Model;
import com.administration.mappers.FoncMapper;
import com.administration.repo.FoncRepo;
import com.administration.repo.ModelRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class FoncServiceImpl implements FoncService{
    FoncRepo foncRepo;
     FoncMapper foncMapper;
     ModelRepo modelRepo;

    public FoncServiceImpl(FoncRepo foncRepo, FoncMapper foncMapper, ModelRepo modelRepo) {
        this.foncRepo = foncRepo;
        this.foncMapper = foncMapper;
        this.modelRepo = modelRepo;
    }

    @Override
    public FoncResponseDTO addFonc(FoncRequestDTO RequestDTO) {
        Fonctionalite fonctionalite= foncMapper.FonctionaliteRequestDTOFonctionalite(RequestDTO);
        fonctionalite.setCodF(UUID.randomUUID().toString());
        fonctionalite.setDesF(RequestDTO.getFON_COD_F()+RequestDTO.getDesF());
        foncRepo.save(fonctionalite);
        FoncResponseDTO foncResponseDTO=foncMapper.FonctionaliteTOFonctionaliteResponseDTO(fonctionalite);
        return foncResponseDTO;
    }

    @Override
    public FoncResponseDTO getFonc(String id) {
        Fonctionalite fonctionalite=foncRepo.findById(id).get();
        FoncResponseDTO foncResponseDTO=foncMapper.FonctionaliteTOFonctionaliteResponseDTO(fonctionalite);
        return foncResponseDTO;
    }

    @Override
    public List<FoncResponseDTO> listFoncs() {
        List<Fonctionalite> fonctionalites=foncRepo.findAll();
        List<FoncResponseDTO> foncResponseDTOS=fonctionalites.stream()
                .map(fonctionalite -> foncMapper.FonctionaliteTOFonctionaliteResponseDTO(fonctionalite))
                .collect(Collectors.toList());
        return foncResponseDTOS;
    }

    @Override
    public void updateFoncDTO(FoncUpdateDTO dto) {
        Fonctionalite fonctionalite=foncRepo.findById(dto.getCodF()).get();
        foncMapper.updateFonctionaliteFromDto(dto,fonctionalite);
        foncRepo.save(fonctionalite);

    }

    @Override
    public void affecterModelToFonc(String idModel, String idFonc) {
        Model model=modelRepo.findById(idModel).get();
        Fonctionalite fonctionalite=foncRepo.findById(idFonc).get();
        boolean exist=false;
        for (Fonctionalite fonctionalite1:model.getFonctionalites())
        {
            if (fonctionalite1.getCodF()==idFonc){exist=true;}
        }
        if (exist==false){
        model.getFonctionalites().add(fonctionalite);
        modelRepo.save(model);}else throw new RuntimeException("This model already exist");


    }

    @Override
    public void deleteFonc(String idFonc) {
        Fonctionalite fonctionalite=foncRepo.findById(idFonc).get();
        if (fonctionalite.getModels().isEmpty()&&fonctionalite.getProfiles().isEmpty())
        {
            foncRepo.deleteById(idFonc);
        }else throw new RuntimeException("This fonctionalite has associations");
    }

    @Override
    public void removeModel(String idModel, String idFonc) {
        Model model=modelRepo.findById(idModel).get();
        Fonctionalite fonctionalite1=foncRepo.findById(idFonc).get();
                model.getFonctionalites().remove(fonctionalite1);
                modelRepo.save(model);
            }
}
