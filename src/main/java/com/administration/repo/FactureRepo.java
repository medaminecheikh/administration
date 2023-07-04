package com.administration.repo;

import com.administration.entity.InfoFacture;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FactureRepo extends JpaRepository<InfoFacture,String> {
    @Query("select a from  InfoFacture a where a.identifiant like :iden AND a.refFacture LIKE :ref AND a.nAppel =:apl")
    Page<InfoFacture> findAllFactures(@Param("iden")String iden, @Param("ref")String ref, @Param("apl")int apl, Pageable pageable);
}
