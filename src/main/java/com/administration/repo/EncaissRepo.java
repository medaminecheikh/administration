package com.administration.repo;

import com.administration.entity.Encaissement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface EncaissRepo extends JpaRepository<Encaissement,String> {
    List<Encaissement> findByDateEncBetween(Date startDate, Date endDate);

    @Query("SELECT e FROM Encaissement e WHERE e.caisse.idCaisse = :caisseId " +
            "AND e.dateEnc BETWEEN :startDate AND :endDate")
    List<Encaissement> findEncaissementsForCaisseInCurrentMonth(
            @Param("caisseId") String caisseId,
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate
    );

    @Query("SELECT i FROM Encaissement i WHERE " +
            "(:produit IS NULL OR LOWER(i.produit) LIKE LOWER(CONCAT('%', :produit, '%'))) " +
            "AND (:identifiant IS NULL OR LOWER(i.refFacture) LIKE LOWER(CONCAT('%', :identifiant, '%'))) " +
            "AND (:modePaiement IS NULL OR LOWER(i.modePaiement) LIKE LOWER(CONCAT('%', :modePaiement, '%'))) " +
            "AND (:typeIdent IS NULL OR LOWER(i.identifiant) LIKE LOWER(CONCAT('%', :typeIdent, '%'))) " +
            "AND (:refFacture IS NULL OR LOWER(i.identifiant) LIKE LOWER(CONCAT('%', :refFacture, '%'))) " +
            "AND (:montantEnc IS NULL OR i.montantEnc <= :montantEnc) ")
    Page<Encaissement> searchEncaiss(
            @Param("produit") String produit,
            @Param("identifiant") String identifiant,
            @Param("modePaiement") String modePaiement,
            @Param("typeIdent") String typeIdent,
            @Param("montantEnc") Double montantEnc,
            @Param("refFacture") String refFacture,
            Pageable pageable);
    @Query("SELECT i FROM Encaissement i WHERE " +
            "(:produit IS NULL OR LOWER(i.produit) LIKE LOWER(CONCAT('%', :produit, '%'))) " +
            "AND (:identifiant IS NULL OR LOWER(i.refFacture) LIKE LOWER(CONCAT('%', :identifiant, '%'))) " +
            "AND (:etatEncaissement IS NULL OR LOWER(i.etatEncaissement) LIKE LOWER(CONCAT('%', :etatEncaissement, '%'))) " +
            "AND (:typeIdent IS NULL OR LOWER(i.identifiant) LIKE LOWER(CONCAT('%', :typeIdent, '%'))) " +
            "AND (:refFacture IS NULL OR LOWER(i.identifiant) LIKE LOWER(CONCAT('%', :refFacture, '%'))) " +
            "AND (:montantEnc IS NULL OR i.montantEnc <= :montantEnc) " +
            "AND YEAR(i.dateEnc) = YEAR(CURRENT_DATE) " )      // Filter for the current year
    Page<Encaissement> searchEncaissThisYear(
            @Param("produit") String produit,
            @Param("identifiant") String identifiant,
            @Param("etatEncaissement") String etatEncaissement,
            @Param("typeIdent") String typeIdent,
            @Param("montantEnc") Double montantEnc,
            @Param("refFacture") String refFacture,
            Pageable pageable);
    @Query("SELECT i FROM Encaissement i WHERE " +
            " YEAR(i.dateEnc) = YEAR(CURRENT_DATE) " )
    List<Encaissement> getEncaissementYearly();
    @Query("SELECT i FROM Encaissement i WHERE " +
            "(:produit IS NULL OR LOWER(i.produit) LIKE LOWER(CONCAT('%', :produit, '%'))) " +
            "AND (:identifiant IS NULL OR LOWER(i.refFacture) LIKE LOWER(CONCAT('%', :identifiant, '%'))) " +
            "AND (:etatEncaissement IS NULL OR LOWER(i.etatEncaissement) LIKE LOWER(CONCAT('%', :etatEncaissement, '%'))) " +
            "AND (:typeIdent IS NULL OR LOWER(i.identifiant) LIKE LOWER(CONCAT('%', :typeIdent, '%'))) " +
            "AND (:refFacture IS NULL OR LOWER(i.identifiant) LIKE LOWER(CONCAT('%', :refFacture, '%'))) " +
            "AND (:montantEnc IS NULL OR i.montantEnc <= :montantEnc) " +
            "AND MONTH(i.dateEnc) = MONTH(CURRENT_DATE) " +  // Filter for the current month
            "AND YEAR(i.dateEnc) = YEAR(CURRENT_DATE) " +    // Filter for the current year
            "AND WEEK(i.dateEnc) = WEEK(CURRENT_DATE)")      // Filter for the current week
    Page<Encaissement> searchEncaissThisWeek(
            @Param("produit") String produit,
            @Param("identifiant") String identifiant,
            @Param("etatEncaissement") String etatEncaissement,
            @Param("typeIdent") String typeIdent,
            @Param("montantEnc") Double montantEnc,
            @Param("refFacture") String refFacture,
            Pageable pageable);
    @Query("SELECT i FROM Encaissement i WHERE " +
            "(:produit IS NULL OR LOWER(i.produit) LIKE LOWER(CONCAT('%', :produit, '%'))) " +
            "AND (:identifiant IS NULL OR LOWER(i.refFacture) LIKE LOWER(CONCAT('%', :identifiant, '%'))) " +
            "AND (:etatEncaissement IS NULL OR LOWER(i.etatEncaissement) LIKE LOWER(CONCAT('%', :etatEncaissement, '%'))) " +
            "AND (:typeIdent IS NULL OR LOWER(i.identifiant) LIKE LOWER(CONCAT('%', :typeIdent, '%'))) " +
            "AND (:refFacture IS NULL OR LOWER(i.identifiant) LIKE LOWER(CONCAT('%', :refFacture, '%'))) " +
            "AND (:montantEnc IS NULL OR i.montantEnc <= :montantEnc) " +
            "AND MONTH(i.dateEnc) = MONTH(CURRENT_DATE) " +  // Filter for the current month
            "AND YEAR(i.dateEnc) = YEAR(CURRENT_DATE)")      // Filter for the current week
    Page<Encaissement> searchEncaissThisMonth(
            @Param("produit") String produit,
            @Param("identifiant") String identifiant,
            @Param("etatEncaissement") String etatEncaissement,
            @Param("typeIdent") String typeIdent,
            @Param("montantEnc") Double montantEnc,
            @Param("refFacture") String refFacture,
            Pageable pageable);

}
