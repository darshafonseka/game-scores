CREATE DATABASE game_scores;
USE game_scores;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_name_UNIQUE` (`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `game_scores`.`game` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `game_name` VARCHAR(45) NOT NULL,
  `description` VARCHAR(100) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `game_name_UNIQUE` (`game_name` ASC));


CREATE TABLE `game_scores`.`user_game` (
  `id` INT NOT NULL,
  `user_id` INT NOT NULL,
  `game_id` INT NOT NULL,
  `score` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `user_fk_idx` (`user_id` ASC),
  INDEX `game_fk_idx` (`game_id` ASC),
  CONSTRAINT `user_fk`
    FOREIGN KEY (`user_id`)
    REFERENCES `game_scores`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `game_fk`
    FOREIGN KEY (`game_id`)
    REFERENCES `game_scores`.`game` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

ALTER TABLE `game_scores`.`user_game`
CHANGE COLUMN `id` `id` INT(11) NOT NULL AUTO_INCREMENT ;

## Added an index on email assuming you search users by email address. this is done to optimize queries
CREATE INDEX idx_email ON user (email);

## stored procs and views could have been used for more optimizations but debugging would have been difficults
## from server side caching could have been introduced if time permitted
