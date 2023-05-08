package com.administration.repo;

import com.administration.entity.Caisse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CaisseRepo extends JpaRepository<Caisse,String> {
}
