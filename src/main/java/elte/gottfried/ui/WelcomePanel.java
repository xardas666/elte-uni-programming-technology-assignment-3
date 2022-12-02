package elte.gottfried.ui;


import elte.gottfried.GameDataHolder;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class WelcomePanel extends GridPane {

    ResultsPanel resultsPanel;
    Button newGame;
    Button exit;
    ImageView imageView;
    public static GameWindow gameWindow;

    public WelcomePanel() {
        imageView = new ImageView();

        resultsPanel = new ResultsPanel();
        newGame = new Button("New Game (Level " + GameDataHolder.LEVEL + ")");
        exit = new Button("Exit");

        exit.setOnAction((event -> {
            Platform.exit();
            System.exit(0);
        }));

        newGame.setOnAction(event -> {
            gameWindow = new GameWindow();
            gameWindow.show();
            Stage stage = (Stage) exit.getScene().getWindow();
            stage.close();
        });

        newGame.setMinWidth(GameDataHolder.WINDOW_MAX_WIDTH);
        exit.setMinWidth(GameDataHolder.WINDOW_MAX_WIDTH);
        resultsPanel.setMinWidth(GameDataHolder.WINDOW_MAX_WIDTH);
        resultsPanel.setStyle(" -fx-font-size: 14px; -fx-alignment: center");

        imageView.setImage(LabyrinthIcon.WELCOME.getImage());
        imageView.setDisable(true);
        imageView.setFitHeight(100);
        imageView.setFitWidth(GameDataHolder.WINDOW_MAX_WIDTH);

        add(imageView,0,0);
        add(newGame, 0, 1);
        add(resultsPanel, 0, 2);
        add(exit, 0, 3);
    }
}
