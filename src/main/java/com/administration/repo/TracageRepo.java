package com.administration.repo;

import com.administration.entity.Tracage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;

import javax.persistence.TemporalType;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Repository
public interface TracageRepo extends JpaRepository<Tracage,Long> {
    @Query("SELECT t FROM Tracage t " +
            "WHERE (:idUser IS NULL OR t.utilisateur.idUser = :idUser) " +
            "AND (:time IS NULL OR t.time = :time) " +
            "AND (:ip IS NULL OR t.ip = :ip) " +
            "AND (:browser IS NULL OR t.browser = :browser) " +
            "AND (:op IS NULL OR t.typeOp = :op) ")
    List<Tracage> getTraceByAll(
            @Param("idUser") String idUser,
            @Param("time") Date time,
            @Param("ip") String ip,
            @Param("browser") String browser,
            @Param("op") String op
    );



    @Query("SELECT t FROM Tracage t " +
            "WHERE (:idUser IS NULL OR t.utilisateur.idUser = :idUser) " +
            "AND (:time IS NULL OR t.time = :time) " +
            "AND (:ip IS NULL OR t.ip = :ip) " +
            "AND (:browser IS NULL OR t.browser = :browser) " +
            "AND (:op IS NULL OR t.typeOp = :op)")
    Page<Tracage> getTraceByPage(
            @Param("idUser") String idUser,
            @Param("time") Timestamp time,
            @Param("ip") String ip,
            @Param("browser") String browser,
            @Param("op") String op, Pageable pageable
    );

    List<Tracage> findByObjectIgnoreCase(String object);
    @Query("SELECT t FROM Tracage t WHERE  t.utilisateur.idUser = :idUser" )
    List<Tracage> findTracageByIdUser(@Param("idUser") String idUser);
}
