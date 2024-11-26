package com.rootcode.gamescores.service;

import com.rootcode.gamescores.domain.User;
import com.rootcode.gamescores.domain.UserGame;

import java.util.List;

public interface UserGameService {

    void saveUserScore(Integer userId, Integer gameId, int score);

    Integer getHighestScore(Integer userId, Integer gameId);

    List<UserGame> getTopScores(Integer gameId);

}


