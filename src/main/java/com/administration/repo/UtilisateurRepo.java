package com.administration.repo;

import com.administration.entity.Utilisateur;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilisateurRepo extends JpaRepository<Utilisateur,String> {
    @Query("SELECT a FROM Utilisateur a WHERE a.login LIKE %:kw% AND a.nomU LIKE %:nom% AND a.prenU LIKE %:pren% AND a.estActif = COALESCE(:estActif, a.estActif)")
    Page<Utilisateur> findUtilisateurByLogin(@Param("kw") String keyword, @Param("nom") String nom, @Param("pren") String prenom,@Param("estActif") Integer estActif, Pageable pageable);

    long count();

    Utilisateur findByLogin(String username);


}
