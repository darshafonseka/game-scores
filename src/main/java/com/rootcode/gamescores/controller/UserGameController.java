package com.rootcode.gamescores.controller;

import com.rootcode.gamescores.domain.UserGame;
import com.rootcode.gamescores.dto.RequestDTO;
import com.rootcode.gamescores.service.UserGameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user/game-score")
@RequiredArgsConstructor
public class UserGameController {

    private final UserGameService userGameService;

    @PostMapping
    public ResponseEntity<String> saveUserGameScore(@RequestBody RequestDTO.SaveGameScoreRequestDTO request) {
        try {
            userGameService.saveUserScore(request.getUserId(), request.getGameId(), request.getScore());
            return ResponseEntity.ok("User Game Score saved successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @GetMapping("/highest")
    public ResponseEntity<Object> getHighestScore(
            @RequestParam Integer userId,
            @RequestParam Integer gameId) {
        try {
            // ideally this should be a post call as its not safe to pass ids in the get call, due to time constraints i wont make it a post
            Integer highestScore = userGameService.getHighestScore(userId, gameId);
            return ResponseEntity.ok(highestScore);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred " + e.getMessage());
        }
    }

    @GetMapping("/top-ten-scores")
    public ResponseEntity<Object> getTopTenScores(
            @RequestParam Integer gameId) {
        try {
            List<UserGame> topScores = userGameService.getTopScores(gameId);
            return ResponseEntity.ok(topScores);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred " + e.getMessage());
        }
    }

}