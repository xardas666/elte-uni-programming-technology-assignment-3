package elte.gottfried.ui;

import elte.gottfried.GameDataHolder;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class GameWindow extends Stage {

    public GameWindow() {
        Labyrinth board = new Labyrinth();
        GridPane boardpanel = board.createBoard(GameDataHolder.LEVEL);
        StackPane gameWindowLayout = new StackPane();
        Scene gameWindowScene = new Scene(gameWindowLayout, board.getBoardWeight()-10, board.getBoardHeight()-10);
        setTitle("Labyrinth");
        setScene(gameWindowScene);
        setResizable(false);

        gameWindowLayout.getChildren().add(boardpanel);

        getScene().setOnKeyReleased(event -> {
            switch (event.getCode()) {
                case UP:
                case W:
                    board.mooveCharacter(-1, 0);
                    break;
                case DOWN:
                case S:
                    board.mooveCharacter(1, 0);
                    break;
                case LEFT:
                case A:
                    board.mooveCharacter(0, -1);
                    break;
                case RIGHT:
                case D:
                    board.mooveCharacter(0, 1);
                    break;
                case F1:
                    GameDataHolder.GOD_MODE = !GameDataHolder.GOD_MODE;
                    break;
            }
        });
    }
}
