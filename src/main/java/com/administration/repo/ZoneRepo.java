package com.administration.repo;

import com.administration.entity.Zone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZoneRepo extends JpaRepository<Zone,String> {
}
