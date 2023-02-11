package com.administration.repo;

import com.administration.entity.Dregional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DregionalRepo extends JpaRepository<Dregional,String> {
}
