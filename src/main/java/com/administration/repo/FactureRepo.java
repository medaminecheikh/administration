package com.administration.repo;

import com.administration.entity.InfoFacture;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface FactureRepo extends JpaRepository<InfoFacture, String> {
    @Query("SELECT a FROM InfoFacture a WHERE a.identifiant LIKE :iden AND a.refFacture LIKE :ref AND CONCAT('', a.nAppel) LIKE CONCAT('%', :apl)")
    Page<InfoFacture> findAllFactures(@Param("iden") String iden, @Param("ref") String ref, @Param("apl") int apl, Pageable pageable);

    @Query("SELECT a FROM InfoFacture a WHERE a.identifiant LIKE :iden AND a.refFacture LIKE :ref AND CONCAT('', a.nAppel) LIKE CONCAT('%', :apl)")
    List<InfoFacture> getAllFactures(@Param("iden") String iden, @Param("ref") String ref, @Param("apl") int apl);

    @Query("SELECT i FROM InfoFacture i WHERE " +
            "(:produitKeyword IS NULL OR LOWER(i.produit) LIKE LOWER(CONCAT('%', :produitKeyword, '%'))) " +
            "AND (:refFactureKeyword IS NULL OR LOWER(i.refFacture) LIKE LOWER(CONCAT('%', :refFactureKeyword, '%'))) " +
            "AND (:compteFacturationKeyword IS NULL OR LOWER(i.compteFacturation) LIKE LOWER(CONCAT('%', :compteFacturationKeyword, '%'))) " +
            "AND (:identifiantKeyword IS NULL OR LOWER(i.identifiant) LIKE LOWER(CONCAT('%', :identifiantKeyword, '%'))) " +
            "AND (:montantMax IS NULL OR i.montant <= :montantMax) " +
            "AND (:solde IS NULL OR i.solde <= :solde)")
    Page<InfoFacture> searchInfoFactures(
            @Param("produitKeyword") String produitKeyword,
            @Param("refFactureKeyword") String refFactureKeyword,
            @Param("compteFacturationKeyword") String compteFacturationKeyword,
            @Param("identifiantKeyword") String identifiantKeyword,
            @Param("montantMax") Double montantMax,
            @Param("solde") Double solde,
            Pageable pageable);
    @Query("SELECT i FROM InfoFacture i WHERE " +
            "(:produitKeyword IS NULL OR LOWER(i.produit) LIKE LOWER(CONCAT('%', :produitKeyword, '%'))) " +
            "AND (:refFactureKeyword IS NULL OR LOWER(i.refFacture) LIKE LOWER(CONCAT('%', :refFactureKeyword, '%'))) " +
            "AND (:compteFacturationKeyword IS NULL OR LOWER(i.compteFacturation) LIKE LOWER(CONCAT('%', :compteFacturationKeyword, '%'))) " +
            "AND (:identifiantKeyword IS NULL OR LOWER(i.identifiant) LIKE LOWER(CONCAT('%', :identifiantKeyword, '%'))) " +
            "AND (:montantMax IS NULL OR i.montant <= :montantMax) " +
            "AND i.datLimPai <= CURRENT_DATE "+
            "AND (:solde IS NULL OR i.solde <= :solde)")
    Page<InfoFacture> searchFinishedFactures(
            @Param("produitKeyword") String produitKeyword,
            @Param("refFactureKeyword") String refFactureKeyword,
            @Param("compteFacturationKeyword") String compteFacturationKeyword,
            @Param("identifiantKeyword") String identifiantKeyword,
            @Param("montantMax") Double montantMax,
            @Param("solde") Double solde,
            Pageable pageable);

    @Query("SELECT i FROM InfoFacture i WHERE " +
            "(:produitKeyword IS NULL OR LOWER(i.produit) LIKE LOWER(CONCAT('%', :produitKeyword, '%'))) " +
            "AND (:refFactureKeyword IS NULL OR LOWER(i.refFacture) LIKE LOWER(CONCAT('%', :refFactureKeyword, '%'))) " +
            "AND (:compteFacturationKeyword IS NULL OR LOWER(i.compteFacturation) LIKE LOWER(CONCAT('%', :compteFacturationKeyword, '%'))) " +
            "AND (:identifiantKeyword IS NULL OR LOWER(i.identifiant) LIKE LOWER(CONCAT('%', :identifiantKeyword, '%'))) " +
            "AND (:montantMax IS NULL OR i.montant <= :montantMax) " +
            "AND (:solde IS NULL OR i.solde <= :solde)")
    List<InfoFacture> getAllFacturesby(
            @Param("produitKeyword") String produitKeyword,
            @Param("refFactureKeyword") String refFactureKeyword,
            @Param("compteFacturationKeyword") String compteFacturationKeyword,
            @Param("identifiantKeyword") String identifiantKeyword,
            @Param("montantMax") Double montantMax,
            @Param("solde") Double solde);
    @Query("SELECT i FROM InfoFacture i WHERE " +
            "(:produitKeyword IS NULL OR LOWER(i.produit) LIKE LOWER(CONCAT('%', :produitKeyword, '%'))) " +
            "AND (:refFactureKeyword IS NULL OR LOWER(i.refFacture) LIKE LOWER(CONCAT('%', :refFactureKeyword, '%'))) " +
            "AND (:compteFacturationKeyword IS NULL OR LOWER(i.compteFacturation) LIKE LOWER(CONCAT('%', :compteFacturationKeyword, '%'))) " +
            "AND (:identifiantKeyword IS NULL OR LOWER(i.identifiant) LIKE LOWER(CONCAT('%', :identifiantKeyword, '%'))) " +
            "AND (:montantMax IS NULL OR i.montant <= :montantMax) " +
            "AND i.datLimPai >= CURRENT_DATE "+
            "AND (:solde IS NULL OR i.solde <= :solde)")
    Page<InfoFacture> searchCoursFactures(
            @Param("produitKeyword") String produitKeyword,
            @Param("refFactureKeyword") String refFactureKeyword,
            @Param("compteFacturationKeyword") String compteFacturationKeyword,
            @Param("identifiantKeyword") String identifiantKeyword,
            @Param("montantMax") Double montantMax,
            @Param("solde") Double solde,
            Pageable pageable);

    @Query("SELECT f FROM InfoFacture f WHERE FUNCTION('YEAR', f.datCreation) = FUNCTION('YEAR', CURRENT_DATE) " +
            "AND FUNCTION('MONTH', f.datCreation) = FUNCTION('MONTH', CURRENT_DATE)")
    List<InfoFacture> findFacturesCreatedInCurrentMonth();

    @Query("SELECT f FROM InfoFacture f WHERE YEAR(f.datCreation) = YEAR(CURRENT_DATE)")
    List<InfoFacture> findFacturesCreatedInCurrentYear();

    @Query("SELECT f FROM InfoFacture f WHERE FUNCTION('YEAR', f.datLimPai) = FUNCTION('YEAR', CURRENT_DATE) " +
            "AND FUNCTION('MONTH', f.datLimPai) = FUNCTION('MONTH', CURRENT_DATE)")
    List<InfoFacture> findFacturesEndInCurrentMonth();

    @Query("SELECT f FROM InfoFacture f WHERE YEAR(f.datLimPai) = YEAR(CURRENT_DATE)")
    List<InfoFacture> findFacturesEndInCurrentYear();


}

