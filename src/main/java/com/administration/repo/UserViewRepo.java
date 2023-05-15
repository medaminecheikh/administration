package com.administration.repo;

import com.administration.entity.UserView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserViewRepo extends JpaRepository<UserView,String> {
    @Query("SELECT u FROM UserView u WHERE u.login = :login")
    UserView getByLogin(String login);
    List<UserView> findByEtt(String ett);

}
