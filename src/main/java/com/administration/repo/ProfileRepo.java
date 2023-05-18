package com.administration.repo;

import com.administration.entity.Profil;
import com.administration.entity.Utilisateur;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfileRepo extends JpaRepository<Profil,String> {

    @Query("select a from  Profil a where a.nomP like :kw AND a.Des_P LIKE :desc")
    Page<Profil> findProfilsByNomP(@Param("kw")String Keyword,@Param("desc")String desc, Pageable pageable);
    long count();
    Profil findByNomP(String profilename);

}
