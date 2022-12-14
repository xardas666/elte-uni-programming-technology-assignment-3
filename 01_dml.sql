DROP
DATABASE labirintus;

CREATE
DATABASE labirintus;

CREATE TABLE labirintus.score
(
    ID    int NOT NULL AUTO_INCREMENT,
    NAME  text,
    LEVEL int DEFAULT NULL,
    SCORE int DEFAULT NULL,
    PRIMARY KEY (ID)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE labirintus.level
(
    LEVEL        int NOT NULL AUTO_INCREMENT,
    LEVEL_TEXT text DEFAULT NULL,
    PRIMARY KEY (LEVEL)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
