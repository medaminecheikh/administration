package com.administration.repo;

import com.administration.entity.Ett;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EttRepo extends JpaRepository<Ett,String> {
}
