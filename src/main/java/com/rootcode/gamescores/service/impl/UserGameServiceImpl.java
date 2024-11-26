package com.rootcode.gamescores.service.impl;

import com.rootcode.gamescores.dao.GameDAO;
import com.rootcode.gamescores.dao.UserDAO;
import com.rootcode.gamescores.dao.UserGameDAO;
import com.rootcode.gamescores.domain.Game;
import com.rootcode.gamescores.domain.User;
import com.rootcode.gamescores.domain.UserGame;
import com.rootcode.gamescores.service.UserGameService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserGameServiceImpl implements UserGameService {

    private final UserGameDAO userGameDAO;
    private final UserDAO userDAO;
    private final GameDAO gameDAO;

    // This method is used to save a user's score for a given game
    @Transactional
    public void saveUserScore(Integer userId, Integer gameId, int score) {
        // Validate the user and game exist
        User user = userDAO.findById(userId);
        if (user == null) {
            throw new RuntimeException("User Not Found");
        }
        Game game = gameDAO.findById(gameId);
        if (game == null) {
            throw new RuntimeException("Game Not Found");
        }

        // check if there's already a score for the user in this game
        UserGame existingUserGameScore = userGameDAO.findByUserAndGame(user, game);

        if (existingUserGameScore != null) {
            // Assumption - If the user already has a score for this game, update it if the new score is higher only
            if (existingUserGameScore.getScore() < score) {
                existingUserGameScore.setScore(score);
                userGameDAO.saveUserGame(existingUserGameScore);
            }
        } else {
            UserGame newUserGameScore = new UserGame();
            newUserGameScore.setUser(user);
            newUserGameScore.setGame(game);
            newUserGameScore.setScore(score);
            userGameDAO.saveUserGame(newUserGameScore);
        }
    }

    // This methof is used to fetch the highest score of the given user's given game
    public Integer getHighestScore(Integer userId, Integer gameId) {
            // get the highest score for the user and game
            User user = userDAO.findById(userId);
            if (user == null) {
                throw new RuntimeException("User Not Found");
            }
            Game game = gameDAO.findById(gameId);
            if (game == null) {
                throw new RuntimeException("Game Not Found");
            }
            Integer highestScore = userGameDAO.findHighestScoreByUserAndGame(userId, gameId);

            if (highestScore == null) {
                throw new RuntimeException("No score found for the given user and givne game.");
            }
            return highestScore;
        }

     // This method is used to get the the top 10 scores for a given game id
    public List<UserGame> getTopScores(Integer gameId) {

        Game game = gameDAO.findById(gameId);
        if (game == null) {
            throw new RuntimeException("Game Not Found");
        }

        List<UserGame> userGameList = userGameDAO.findTopScoresByGame(gameId);
        if(userGameList.isEmpty()) {
            throw new RuntimeException("No Top Scores found for the given game");
        }
        return userGameDAO.findTopScoresByGame(gameId);
    }

}

