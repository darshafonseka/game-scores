package com.rootcode.gamescores.service;

public interface UserGameService {

    void saveUserScore(Integer userId, Integer gameId, int score);

    Integer getHighestScore(Integer userId, Integer gameId);

}


