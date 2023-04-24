package com.everyonegarden.user.repository;

import com.everyonegarden.user.entity.User;

public interface UserRepository {
    User findByUsername(String username);
}
