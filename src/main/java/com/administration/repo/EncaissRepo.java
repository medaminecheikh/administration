package com.administration.repo;

import com.administration.entity.Encaissement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EncaissRepo extends JpaRepository<Encaissement,String> {
}
