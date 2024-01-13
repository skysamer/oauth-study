package com.salary.users.repository;

import com.salary.users.entity.Provider;
import com.salary.users.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    Optional<Users> findBySub(String sub);
    Optional<Users> findByEmailAndProvider(String email, Provider provider);
}
