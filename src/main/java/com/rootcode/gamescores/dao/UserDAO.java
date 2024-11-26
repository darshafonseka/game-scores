package com.rootcode.gamescores.dao;

import com.rootcode.gamescores.domain.User;

public interface UserDAO {

    User findById(Integer id);
    void saveUser(User user);
}
