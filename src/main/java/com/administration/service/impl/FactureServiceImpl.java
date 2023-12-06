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
import com.administration.service.mappers.EncaissMapper;
import com.administration.service.mappers.FactureMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
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
    EncaissMapper encaissMapper;

    @Override
    public InfoFacture addFacture(InfoFacture facture) {
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
    public FactureResponseDTO affectEncaissementToFacture(String encaissementId, String factureId) {
        try {
            Encaissement encaissement = encaissRepo.findById(encaissementId)
                    .orElseThrow(() -> new EntityNotFoundException("Encaissement not found with id: " + encaissementId));
            InfoFacture facture = factureRepo.findById(factureId)
                    .orElseThrow(() -> new EntityNotFoundException("Facture not found with id: " + factureId));

            encaissement.setFacture(facture);
            encaissRepo.save(encaissement);

            log.info("Affectation passed for encaissementId: {} and factureId: {}", encaissementId, factureId);
            return factureMapper.FactureTOFactureResponseDTO(factureRepo.findById(factureId).get());

        } catch (EntityNotFoundException e) {
            // Entity not found, return 404 Not Found status
            throw e;
        } catch (Exception e) {
            // Log the error for debugging purposes
            log.error("Failed to affect encaissement to facture: {}", e.getMessage());
            // You might want to throw a custom exception here or handle it in another way.
            throw new RuntimeException("Failed to affect encaissement to facture: " + e.getMessage());
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
            String identifiantKeyword, Double montantMax, Double solde, Pageable pageable) {

        Page<InfoFacture> infoFactures = factureRepo.searchInfoFactures(
                produitKeyword, refFactureKeyword, compteFacturationKeyword,
                identifiantKeyword, montantMax, solde, pageable);

        List<FactureResponseDTO> infoFactureResponseDTOList = infoFactures.getContent()
                .stream()
                .map(infoFacture -> factureMapper.FactureTOFactureResponseDTO(infoFacture))
                .collect(Collectors.toList());

        long count = infoFactures.getTotalElements();
        infoFactureResponseDTOList.forEach(dto -> dto.setTotalElements(count));

        return infoFactureResponseDTOList;
    }

    @Override
    public List<FactureResponseDTO> getFinishedFactures(String produitKeyword, String refFactureKeyword,
                                                        String compteFacturationKeyword, String identifiantKeyword,
                                                        Double montantMax, Double solde, PageRequest pageable) {
        Page<InfoFacture> infoFacturePage = factureRepo.searchFinishedFactures(produitKeyword, refFactureKeyword,
                compteFacturationKeyword, identifiantKeyword, montantMax, solde, pageable);
        long count = infoFacturePage.getTotalElements();
        List<FactureResponseDTO> factureResponseDTOS = infoFacturePage.getContent().stream()
                .map(infoFacture -> factureMapper.FactureTOFactureResponseDTO(infoFacture))
                .collect(Collectors.toList());
        factureResponseDTOS.forEach(factureResponseDTO -> factureResponseDTO.setTotalElements(count));
        return factureResponseDTOS;
    }

    @Override
    public List<FactureResponseDTO> searchCoursFactures(String produitKeyword, String refFactureKeyword, String compteFacturationKeyword, String identifiantKeyword, Double montantMax, Double solde, PageRequest pageable) {


        Page<InfoFacture> infoFacturePage = factureRepo.searchCoursFactures(produitKeyword, refFactureKeyword,
                compteFacturationKeyword, identifiantKeyword, montantMax, solde, pageable);
        long count = infoFacturePage.getTotalElements();
        List<FactureResponseDTO> factureResponseDTOS = infoFacturePage.getContent().stream()
                .map(infoFacture -> factureMapper.FactureTOFactureResponseDTO(infoFacture))
                .collect(Collectors.toList());
        factureResponseDTOS.forEach(factureResponseDTO -> factureResponseDTO.setTotalElements(count));
        log.info(factureResponseDTOS.toString());
        return factureResponseDTOS;
    }

    @Override
    public List<FactureResponseDTO> searchRetardFactures(String produitKeyword, String refFactureKeyword, String compteFacturationKeyword, String identifiantKeyword, Double montantMax, Double solde) {
        List<InfoFacture> infoFactures = new ArrayList<>(factureRepo.getAllFacturesby(produitKeyword, refFactureKeyword, compteFacturationKeyword, identifiantKeyword, montantMax, solde));

        List<InfoFacture> facturesRetard = infoFactures.stream()
                .filter(this::factureRetard)
                .toList();
        int totalCount = facturesRetard.size();

        return facturesRetard.stream()
                .map(facture -> {
                    FactureResponseDTO dto = factureMapper.FactureTOFactureResponseDTO(facture);
                    dto.setTotalElements(totalCount);
                    return dto;
                })
                .collect(Collectors.toList());
    }

    boolean factureRetard(InfoFacture facture) {
        if ("COMPLET".equalsIgnoreCase(facture.getPeriode())) {
            var paied = facture.getEncaissements()
                    .stream()
                    .mapToDouble(Encaissement::getMontantEnc)
                    .sum();
            return (facture.getMontant() - (facture.getMontant() * facture.getSolde() / 100)) > paied;
        } else {
            Date datCreation = facture.getDatCreation();
            Date datLimPai = facture.getDatLimPai();

            if (datCreation != null && datLimPai != null) {
                // Assuming that the dates are stored as strings in 'yyyy-MM-dd' format
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

                LocalDate localDatCreation = LocalDate.parse(datCreation.toString(), formatter);
                LocalDate localDatLimPai = LocalDate.parse(datLimPai.toString(), formatter);

                long daysBetween = ChronoUnit.DAYS.between(localDatCreation, localDatLimPai);
                long yearsBetween = ChronoUnit.YEARS.between(localDatCreation, localDatLimPai);
                log.info("Days between datCreation and datLimPai: {}, {}", daysBetween, yearsBetween);

                int numberOfPayments = calculateNumberOfPayments(yearsBetween, facture.getPeriode());

                var totalAmount = (facture.getMontant() - (facture.getMontant() * facture.getSolde() / 100));
                var tranche = totalAmount / numberOfPayments;

                // Assuming currentDate is the current date
                LocalDate currentDate = LocalDate.now();
                long daysFromCreation = ChronoUnit.DAYS.between(localDatCreation, currentDate);

                // Calculate the current period
                int currentPeriod = (int) Math.ceil((double) daysFromCreation / (daysBetween / numberOfPayments));
                var totalPaye = facture.getEncaissements()
                        .stream()
                        .mapToDouble(Encaissement::getMontantEnc)
                        .sum();
                log.info("calculateNumberOfPayments: {}", numberOfPayments);
                log.info("Total Amount: {}", totalAmount);
                log.info("total Paye: {}", totalPaye);
                log.info("Tranche: {}", tranche);
                log.info("Current Period: {}", currentPeriod);
                if (currentPeriod == 0) {
                    currentPeriod += 1;
                }
                return totalPaye < tranche * currentPeriod;
            }

            return true;
        }

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
        List<InfoFacture> creatFactures = factureRepo.findFacturesCreatedInCurrentMonth();

        return creatFactures.stream().map(infoFacture -> factureMapper.FactureTOFactureResponseDTO(infoFacture))
                .collect(Collectors.toList());
    }

    @Override
    public List<FactureResponseDTO> getYearlyFactures() {
        List<InfoFacture> infoFactureend= factureRepo.findFacturesEndInCurrentYear();
        List<InfoFacture> infoFacturecreat = factureRepo.findFacturesCreatedInCurrentYear();
        List<InfoFacture> infoFactureList = new ArrayList<>();
        infoFactureList.addAll(infoFacturecreat);
        infoFactureList.addAll(infoFactureend);
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
