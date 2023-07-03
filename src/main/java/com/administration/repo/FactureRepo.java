package com.administration.repo;

import com.administration.entity.InfoFacture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FactureRepo extends JpaRepository<InfoFacture,String> {
}
