package com.administration.service.impl;

import com.administration.dto.FactureResponseDTO;
import com.administration.dto.FactureUpdateDTO;
import com.administration.entity.Encaissement;
import com.administration.entity.InfoFacture;
import com.administration.entity.Utilisateur;
import com.administration.repo.EncaissRepo;
import com.administration.repo.FactureRepo;
import com.administration.repo.UtilisateurRepo;
import com.administration.service.IFactureService;
import com.administration.service.mappers.FactureMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
@Slf4j
public class FactureServiceImpl implements IFactureService {
    FactureRepo factureRepo;
    FactureMapper factureMapper;
    UtilisateurRepo utilisateurRepo;
    EncaissRepo encaissRepo;

    @Override
    public InfoFacture addFacture(InfoFacture facture) {
        facture.setIdFacture(UUID.randomUUID().toString());
        facture.setDatCreation(new Date());
        factureRepo.save(facture);
        return facture;
    }

    @Override
    public List<InfoFacture> getAll(String identifiant, String ref, int apl, int page, int size) {
        Sort sort = Sort.by("datLimPai");
        Page<InfoFacture> factures = factureRepo.findAllFactures(identifiant, ref, apl, PageRequest.of(page, size, sort));
        return factures.getContent();
    }

    @Override
    public List<InfoFacture> getByUser(String idUser) {
        Utilisateur utilisateur = utilisateurRepo.findById(idUser).orElse(null);
        if (utilisateur != null) {
            /* return utilisateur.getFactures();*/
            return Collections.emptyList();
        } else {
            return Collections.emptyList();
        }

    }

    @Override
    public void updateFacture(FactureUpdateDTO facture) {
        InfoFacture infoFacture = factureRepo.findById(facture.getIdFacture()).orElse(null);
        if (infoFacture != null) {
            factureMapper.updateFactureFromDto(facture, infoFacture);
            factureRepo.save(infoFacture);
        }
    }

    @Override
    public void deleteFacture(String idFacture) {
        factureRepo.findById(idFacture).ifPresent(infoFacture -> factureRepo.delete(infoFacture));


    }

    @Override
    public void affectEncaissementToFacture(String encaissementId, String factureId) {
        Encaissement encaissement = encaissRepo.findById(encaissementId).orElse(null);
        InfoFacture facture = factureRepo.findById(factureId).orElse(null);

        if (encaissement != null && facture != null) {
            encaissement.setFacture(facture);
            encaissRepo.save(encaissement);
            log.info("Affectation passed for encaissementId: {} and factureId: {}", encaissementId, factureId);
        } else {
            log.warn("Encaissement or Facture not found for encaissementId: {} and factureId: {}", encaissementId, factureId);
        }
    }

    @Override
    public void affectUser(String idUser, String idfac) {
       /* Utilisateur utilisateur =utilisateurRepo.findById(idUser).get();
        InfoFacture facture=factureRepo.findById(idfac).get();
        facture.setUser(utilisateur);
        factureRepo.save(facture);*/
    }

    @Override
    public void removeEncaissementFromFacture(String encaissementId, String factureId) {
        Encaissement encaissement = encaissRepo.findById(encaissementId).orElse(null);
        InfoFacture facture = factureRepo.findById(factureId).orElse(null);

        if (encaissement != null && facture != null) {
            List<Encaissement> encaissements = facture.getEncaissements();

            // Remove the operation associated with the encaissement
            encaissements.removeIf(op -> op.equals(encaissement));

            // Save the updated facture
            factureRepo.save(facture);

            // Check if the encaissement is not associated with any other operation
            if (encaissement.getFacture() == null) {
                // Delete the encaissement
                encaissRepo.delete(encaissement);
            }
        }
    }

    @Override
    public List<FactureResponseDTO> getAllFactures() {
        List<InfoFacture> infoFactures = factureRepo.findAll();
        return infoFactures.stream().map(facture ->
                factureMapper.FactureTOFactureResponseDTO(facture)).collect(Collectors.toList());
    }

    @Override
    public List<InfoFacture> getAllfacture(String id, String ref, Integer apl) {

        return factureRepo.getAllFactures(id, ref, apl);
    }

    @Override
    public List<FactureResponseDTO> searchInfoFactures(
            String produitKeyword, String refFactureKeyword, String compteFacturationKeyword,
            String identifiantKeyword, Double montantMax, Pageable pageable) {

        Page<InfoFacture> infoFactures = factureRepo.searchInfoFactures(
                produitKeyword, refFactureKeyword, compteFacturationKeyword,
                identifiantKeyword, montantMax, pageable);

        List<FactureResponseDTO> infoFactureResponseDTOList = infoFactures.getContent()
                .stream()
                .map(infoFacture -> factureMapper.FactureTOFactureResponseDTO(infoFacture))
                .collect(Collectors.toList());

        long count = infoFactures.getTotalElements();
        infoFactureResponseDTOList.forEach(dto -> dto.setTotalElements(count));

        return infoFactureResponseDTOList;
    }

    @Override
    public double calculatePaymentAmount(InfoFacture facture, Date targetDate) {
        double originalAmount = facture.getMontant();
        double discountPercentage = facture.getSolde();

        // Calculate the discounted amount based on the discount percentage
        double discountedAmount = originalAmount * (1 - (discountPercentage / 100));

        Date datCreation = facture.getDatCreation();
        Date datLimPai = facture.getDatLimPai();
        String periode = facture.getPeriode();

        // Calculate the number of years between datCreation and targetDate
        long yearsBetween = calculateYearsBetween(datCreation, targetDate);

        // Calculate the number of payments (based on period) that have occurred within those years
        int numberOfPayments = calculateNumberOfPayments(yearsBetween, periode);

        // Calculate the payment amount based on the number of payments and the discounted amount
        double paymentAmount = discountedAmount / numberOfPayments;

        // Calculate the remaining unpaid amount
        double remainingAmount = discountedAmount - (paymentAmount * numberOfPayments);

        // Check if the targetDate is beyond the datLimPai
        if (targetDate.after(datLimPai)) {
            return 0.0; // No payment is due if the targetDate is beyond the datLimPai
        }

        // If the targetDate is before datLimPai, return the calculated payment amount
        return paymentAmount;
    }

    @Override
    public List<FactureResponseDTO> getMonthlyFactures() {
        List<InfoFacture> infoFactures = factureRepo.findFacturesCreatedInCurrentMonth();
        return infoFactures.stream().map(infoFacture -> factureMapper.FactureTOFactureResponseDTO(infoFacture))
                .collect(Collectors.toList());
    }

    @Override
    public List<FactureResponseDTO> getYearlyFactures() {
        List<InfoFacture> infoFactureList = factureRepo.findFacturesCreatedInCurrentYear();

        return infoFactureList.stream().map(facture -> factureMapper.FactureTOFactureResponseDTO(facture)).collect(Collectors.toList());
    }

    private long calculateYearsBetween(Date date1, Date date2) {
        long millisecondsInYear = 365L * 24 * 60 * 60 * 1000;
        long diffMilliseconds = date2.getTime() - date1.getTime();
        return diffMilliseconds / millisecondsInYear;
    }

    private int calculateNumberOfPayments(long years, String periode) {
        if ("MENSUEL".equalsIgnoreCase(periode)) {
            return (int) (years * 12); // Assuming monthly payments
        } else if ("SEMESTRIEL".equalsIgnoreCase(periode)) {
            return (int) (years * 2); // Assuming semi-annual payments
        } else if ("TRIMESTRIEL".equalsIgnoreCase(periode)) {
            return (int) (years * 3); // Assuming quarterly payments
        } else if ("ANNUEL".equalsIgnoreCase(periode)) {
            return (int) years; // Assuming annual payments
        }
        return 0; // Unknown periode, return 0 payments
    }
}
