package elte.gottfried;

import elte.gottfried.ui.WelcomePanel;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(final Stage primaryStage) {

        StackPane root = new StackPane();
        root.getChildren().add(new WelcomePanel());
        primaryStage.setScene(new Scene(root, GameDataHolder.WINDOW_MAX_WIDTH, GameDataHolder.WINDOW_MAX_LENGTH));
        primaryStage.setTitle("Labyrinth");
        primaryStage.setResizable(false);
        primaryStage.show();

    }

}