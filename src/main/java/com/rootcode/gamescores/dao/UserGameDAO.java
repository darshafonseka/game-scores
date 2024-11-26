package com.rootcode.gamescores.dao;

import com.rootcode.gamescores.domain.Game;
import com.rootcode.gamescores.domain.User;
import com.rootcode.gamescores.domain.UserGame;

import java.util.List;

public interface UserGameDAO {

    void saveUserGame(UserGame userGame);
    Integer findHighestScoreByUserAndGame(Integer userId, Integer gameId);
    List<UserGame> findTopScoresByGame(Integer gameId);
    UserGame findByUserAndGame(User user, Game game);
}
