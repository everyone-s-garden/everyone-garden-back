package com.everyonegarden.user.repository;

import com.everyonegarden.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findBySocialId(String socialId);
}