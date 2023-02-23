package com.administration.repo;

import com.administration.entity.Fonction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoncRepo extends JpaRepository<Fonction,String> {
}
