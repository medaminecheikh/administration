package com.administration.repo;

import com.administration.entity.OperationEncai;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationRepo extends JpaRepository<OperationEncai,String> {
}
