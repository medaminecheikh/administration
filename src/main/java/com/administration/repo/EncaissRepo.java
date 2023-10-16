package com.administration.repo;

import com.administration.entity.Encaissement;
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
}
