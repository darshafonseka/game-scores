package com.rootcode.gamescores.dao.impl;

import com.rootcode.gamescores.dao.GameDAO;
import com.rootcode.gamescores.domain.Game;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.logging.Logger;

@Repository
public class GameDAOImpl implements GameDAO {

    @PersistenceContext
    private EntityManager em;

    Logger logger = Logger.getLogger(getClass().getName());

    @Override
    public Game findById(Integer id) {
        try {
            return em.find(Game.class, id);
        } catch (Exception e) {
            logger.info("Error fetching game: " + e.getMessage());
            return null;
        }
    }

    @Override
    public void saveGame(Game game) {
        try {
            if (game.getId() == null) {
                em.persist(game);
            } else {
                em.merge(game);
            }
        } catch (Exception e) {
            logger.info("Error saving game: " + e.getMessage());
        }
    }
}
