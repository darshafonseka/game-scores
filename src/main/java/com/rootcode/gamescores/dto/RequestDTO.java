package com.rootcode.gamescores.dto;

import lombok.*;

public class RequestDTO {

    @Getter
    @Setter
    @AllArgsConstructor
    public static class SaveGameScoreRequestDTO {
        private Integer userId;
        private Integer gameId;
        private int score;
        public SaveGameScoreRequestDTO() {}
    }
}
