package com.administration.repo;

import com.administration.entity.ProfilUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfilUserRepo extends JpaRepository<ProfilUser,Long> {
}
