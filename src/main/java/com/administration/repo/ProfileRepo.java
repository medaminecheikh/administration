package com.administration.repo;

import com.administration.entity.Profil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileRepo extends JpaRepository<Profil,String> {


    Profil findByNomP(String profilename);


}
