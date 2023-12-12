package com.administration.repo;

import com.administration.entity.ProfilUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfilUserRepo extends JpaRepository<ProfilUser,Long> {
    @Modifying
    @Query("DELETE FROM ProfilUser pu WHERE pu.utilisateur.idUser = :userId AND pu.profil.idProfil = :profilId")
    void deleteByUserIdAndProfilId(@Param("userId") String userId, @Param("profilId") String profilId);
}
