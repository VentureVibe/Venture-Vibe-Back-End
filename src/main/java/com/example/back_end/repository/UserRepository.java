package com.example.back_end.repository;

import com.example.back_end.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    /*@Query("SELECT u FROM User u WHERE u.email = :email")
    User findByEmail(@Param("email") String email);*/
    User findByEmail(String email);
}
