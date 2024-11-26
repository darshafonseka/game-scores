package com.rootcode.gamescores.dao;

import com.rootcode.gamescores.domain.Game;

public interface GameDAO {

    Game findById(Integer id);
    void saveGame(Game game);
}
