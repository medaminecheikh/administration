package com.administration.service.impl;

import com.administration.dto.TracageResponse;
import com.administration.entity.Tracage;
import com.administration.entity.Utilisateur;
import com.administration.repo.TracageRepo;
import com.administration.repo.UtilisateurRepo;
import com.administration.service.ITracageService;
import com.administration.service.mappers.TracageMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class ITracageServiceImpl implements ITracageService {
    private TracageRepo tracageRepo;
    private UtilisateurRepo utilisateurRepo;
    private TracageMapper tracageMapper;

    @Override
    public List<TracageResponse> getTraceByAll(String idUser, Date  time, String ip, String browser, String op) {
        List<Tracage> tracages = tracageRepo.findAll();

            return tracages.stream().map(tracage -> {
               return tracageMapper.TracageTOTracageResponseDTO(tracage);}).collect(Collectors.toList());

    }

    @Override
    public List<Tracage> getTraceByPage(String idUser, Timestamp time, String ip, String browser, String op, Pageable pageable) {

        Page<Tracage> traces = tracageRepo.getTraceByPage(idUser, time, ip, browser, op, pageable);
        return traces.getContent();
    }

    @Override
    public void addTrace(Tracage tracage) {

        tracage.setTime(Date.from(Instant.now()));

        Utilisateur utilisateur = utilisateurRepo.findById(tracage.getUtilisateur().getIdUser())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        tracage.setUtilisateur(utilisateur);

        tracageRepo.save(tracage);
    }

    @Override
    public List<TracageResponse> getByencaissement() {
        List<Tracage> tracages = tracageRepo.findByObjectIgnoreCase("ENCAISSEMENT");
        return tracages.stream().map(tracage -> {
            return tracageMapper.TracageTOTracageResponseDTO(tracage);
        }).collect(Collectors.toList());
    }

    @Override
    public List<TracageResponse> getByfacture() {
        List<Tracage> tracages = tracageRepo.findByObjectIgnoreCase("FACTURE");
        return tracages.stream().map(tracage -> {
            return tracageMapper.TracageTOTracageResponseDTO(tracage);
        }).collect(Collectors.toList());
    }

    @Override
    public List<TracageResponse> getBycaisse() {
        List<Tracage> tracages = tracageRepo.findByObjectIgnoreCase("CAISSE");
        return tracages.stream().map(tracage -> {
            return tracageMapper.TracageTOTracageResponseDTO(tracage);
        }).collect(Collectors.toList());
    }
}
