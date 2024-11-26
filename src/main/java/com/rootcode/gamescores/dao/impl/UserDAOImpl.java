package com.rootcode.gamescores.dao.impl;

import com.rootcode.gamescores.dao.UserDAO;
import com.rootcode.gamescores.domain.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.logging.Logger;

@Repository
public class UserDAOImpl implements UserDAO {

    @PersistenceContext
    private EntityManager em;

    Logger logger = Logger.getLogger(getClass().getName());

    @Override
    public User findById(Integer id) {
        try {
            return em.find(User.class, id);
        } catch (Exception e) {
            logger.info("Error fetching user: " + e.getMessage());
            return null;
        }
    }

    @Override
    public void saveUser(User user) {
        try {
            if (user.getId() == null) {
                em.persist(user);
            } else {
                em.merge(user);
            }
        } catch (Exception e) {
            logger.info("Error saving user: " + e.getMessage());
        }
    }

}
