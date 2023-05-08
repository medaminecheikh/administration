package com.administration.repo;

import com.administration.entity.Fonction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoncRepo extends JpaRepository<Fonction,String> {

    @Query("SELECT f FROM Fonction f WHERE f.nomMENU = ?1 AND f.fon_COD_F IS NOT NULL AND f.fon_COD_F != ?2")
    List<Fonction> findByNomMENUAndFon_COD_FIsNotNull(String nomMENU, String empty);
    @Query("SELECT f FROM Fonction f WHERE f.nomMENU = ?1")
    List<Fonction> findByNomMENU(String nomMENU);
    Fonction findByNomF(String name);
}
