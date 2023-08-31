package com.administration.repo;

import com.administration.entity.InfoFacture;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
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
            "AND (:identifiantKeyword IS NULL OR LOWER(i.identifiant) LIKE LOWER(CONCAT('%', :identifiantKeyword, '%')))")
    Page<InfoFacture> searchInfoFactures(
            @Param("produitKeyword") String produitKeyword,
            @Param("refFactureKeyword") String refFactureKeyword,
            @Param("compteFacturationKeyword") String compteFacturationKeyword,
            @Param("identifiantKeyword") String identifiantKeyword,
            Pageable pageable);
}
