package org.smartkup.smartkup.repository;

import org.smartkup.smartkup.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Allows us to search the database by email for logins
    Optional<User> findByEmail(String email);
}