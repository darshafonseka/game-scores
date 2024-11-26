package com.rootcode.gamescores.dao.impl;

import com.rootcode.gamescores.dao.UserGameDAO;
import com.rootcode.gamescores.domain.Game;
import com.rootcode.gamescores.domain.User;
import com.rootcode.gamescores.domain.UserGame;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserGameDAOImpl implements UserGameDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void saveUserGame(UserGame userGame) {
        try {
            if (userGame.getId() == null) {
                em.persist(userGame);
            } else {
                em.merge(userGame);
            }
        } catch (Exception e) {
            System.err.println("Error saving userGame: " + e.getMessage());
        }
    }

    @Override
    public Integer findHighestScoreByUserAndGame(Integer userId, Integer gameId) {
        try {
            String query = "SELECT MAX(s.score) FROM UserGame s WHERE s.user.id = :userId AND s.game.id = :gameId";
            return em.createQuery(query, Integer.class)
                    .setParameter("userId", userId)
                    .setParameter("gameId", gameId)
                    .getSingleResult();
        } catch (Exception e) {
            System.err.println("Error finding highest score for given user and game: " + e.getMessage());
            return null;
        }
    }

    @Override
    public List<UserGame> findTopScoresByGame(Integer gameId) {
        try {
            String query = "SELECT s FROM UserGame s WHERE s.game.id = :gameId ORDER BY s.score DESC";
            return em.createQuery(query, UserGame.class)
                    .setParameter("gameId", gameId)
                    .setMaxResults(10)
                    .getResultList();
        } catch (Exception e) {
            System.err.println("Error finding top scores: " + e.getMessage());
            throw new RuntimeException("Failed to find top scores", e);
        }
    }

    @Override
    public UserGame findByUserAndGame(User user, Game game) {
        try {
            String query = "SELECT s FROM UserGame s WHERE s.user = :user AND s.game = :game";
            return em.createQuery(query, UserGame.class)
                    .setParameter("user", user)
                    .setParameter("game", game)
                    .getResultStream()
                    .findFirst()
                    .orElse(null); // Return null if no score is found
        } catch (Exception e) {
            System.err.println("Error finding score for user and game: " + e.getMessage());
            return null; // Return null if there's an error
        }
    }



}
