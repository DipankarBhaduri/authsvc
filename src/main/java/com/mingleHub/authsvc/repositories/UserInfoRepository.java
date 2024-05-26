package com.mingleHub.authsvc.repositories;

import com.mingleHub.authsvc.dao.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserInfoRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);
}
