package com.administration.repo;

import com.administration.entity.Utilisateur;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilisateurRepo extends JpaRepository<Utilisateur,String> {
    @Query("select a from  Utilisateur a where a.login like :kw")
    Page<Utilisateur> findUtilisateurByLogin(@Param("kw")String Keyword, Pageable pageable);
}
