package com.ecommerce.services;

import com.ecommerce.models.User;

public interface UserService {
    User findByUserName(String username);
}
