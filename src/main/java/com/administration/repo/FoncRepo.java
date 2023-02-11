package com.administration.repo;

import com.administration.entity.Fonctionalite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoncRepo extends JpaRepository<Fonctionalite,String> {
}
