package com.example.dslIntelliJ.respository;

import com.example.dslIntelliJ.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {
    User findUserByEmail(String email);
}