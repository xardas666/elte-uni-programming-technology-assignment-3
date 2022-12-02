package elte.gottfried.ui;

import elte.gottfried.GameDataHolder;
import elte.gottfried.Tools;
import elte.gottfried.db.LevelRepository;
import elte.gottfried.db.ScoreRepository;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Labyrinth {

    ArrayList<ArrayList<ImageView>> boardArray;
    int maxImageSize=15;
    GridPane board = new GridPane();
    int playerActualX, playerActualY;
    int dragonX, dragonY;
    List<List<Character>> labyrint;
    boolean dragonJustStarted = true;
    boolean dragonHasBeenStarted = false;
    boolean isEndOfGame = false;
    long startTime;
    long endTime;
    Thread thread;
    Dragon dragon;
    LevelRepository levelRepository = new LevelRepository();

    public GridPane createBoard(int level) {

        startTime = System.nanoTime();

        board = new GridPane();
        board.setGridLinesVisible(true);

        labyrint = new ArrayList<>();
        labyrint = levelRepository.getLabyrinthByLevel(level);

        int y = labyrint.get(0).size();
        int x = labyrint.size();

        boardArray = new ArrayList<>();

        for (int i = 0; i < x; i++) {
            boardArray.add(i, new ArrayList<>());
            for (int j = 0; j < y; j++) {
                ImageView textField = createField();
                boardArray.get(i).add(j, textField);
                board.add(textField, j, 2 + i);
            }
        }

        setStartingPosition();
        mooveCharacter(0, 0);
        return board;
    }

    /*
  o-start
  .-path
  #-wall
  x-exit
   */
    private synchronized void setMazeColoring() throws FileNotFoundException {
        for (int x = 0; x < boardArray.size(); x++) {
            for (int y = 0; y < boardArray.get(x).size(); y++) {
                ImageView field = boardArray.get(x).get(y);
                Character character = labyrint.get(x).get(y);
                if ((x >= playerActualX - 3 && y >= playerActualY - 3) && (x <= playerActualX + 3 && y <= playerActualY + 3)) {
                    if (character == '#') {
                        field.setImage(LabyrinthIcon.WALL.getImage());
                    }
                    if (character == '.' || character == 'o' ) {
                        field.setImage(LabyrinthIcon.PATH.getImage());
                    }
                    if (character == 'x') {
                        field.setImage(LabyrinthIcon.DOOR.getImage());
                    }
                } else {
                    field.setImage(LabyrinthIcon.DARK.getImage());
                }
                if ((dragonX >= playerActualX - 3 && dragonY >= playerActualY - 3) && (dragonX <= playerActualX + 3 && dragonY <= playerActualY + 3)) {
                    ImageView fieldDragon = boardArray.get(dragonX).get(dragonY);
                    fieldDragon.setImage(LabyrinthIcon.DRAGON.getImage());
                }
            }
        }
        ImageView field = boardArray.get(playerActualX).get(playerActualY);
        field.setImage(LabyrinthIcon.PLAYER.getImage());
    }

    private ImageView createField() {
        ImageView field = new ImageView();
        field.setDisable(true);
        field.setFitHeight(maxImageSize);
        field.setFitWidth(maxImageSize);
        return field;
    }


    private synchronized boolean outOfArrayOrWall(int x, int y, int actualX, int actualY) {
        if ((actualX + x >= 0 && actualY + y >= 0) && (actualX + x < labyrint.size() && actualY + y < labyrint.get(0).size())) {
            return labyrint.get(actualX + x).get(actualY + y).equals('#');
        } else {
            return true;
        }
    }


    private void setStartingPosition() {
        Character character = 'o';
        for (int x = 0; x < labyrint.size(); x++) {
            for (int y = 0; y < labyrint.get(x).size(); y++) {
                if (labyrint.get(x).get(y).equals(character)) {
                    playerActualY = y;
                    playerActualX = x;
                }
            }
        }
    }

    public void mooveCharacter(int x, int y) {
        movePlayer(x, y);
        dragonStart();
        try {
            setMazeColoring();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        endOfGameCheck();
    }

    private void movePlayer(int x, int y) {
        if (!outOfArrayOrWall(x, y, playerActualX, playerActualY)) {
            playerActualX = playerActualX + x;
            playerActualY = playerActualY + y;
        }
    }

    public synchronized void dragonStart() {
        if(!dragonHasBeenStarted){
            thread = new Thread(() -> {
                Runnable updater = () -> {
                    moveDragon();
                    try {
                        setMazeColoring();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    endOfGameCheck();
                };
                while (!isEndOfGame) {
                    try {
                        Platform.runLater(updater);
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        throw new RuntimeException();
                    }

                }
            });
            thread.setDaemon(true);
            thread.start();
            dragonHasBeenStarted = true;
        }
    }

    private void moveDragon() {
        if (dragonJustStarted) {
            dragonX = Tools.generate(10, 20);
            dragonY = Tools.generate(10, 20);
            dragonJustStarted = false;
            while (!outOfArrayOrWall(dragonX, dragonY, dragonX, dragonY)) {
                dragonX = Tools.generate(10, 20);
                dragonY = Tools.generate(10, 20);
            }
            dragon = new Dragon();
        } else {
            boolean dragonStepDone = false;
            while (!dragonStepDone) {
                dragonStepDone = dragon.move(outOfArrayOrWall(dragon.getX(), dragon.getY(), dragonX, dragonY));
            }
            dragonX = dragonX + dragon.getX();
            dragonY = dragonY + dragon.getY();
        }
    }


    private synchronized void endOfGameCheck() {
        if (!isEndOfGame) {
            Character fieldNew = labyrint.get(playerActualX).get(playerActualY);
            if (fieldNew.equals('x')) {
                isEndOfGame = true;
                endTime = System.nanoTime();
                endOfGameWin();
            }
            if ((dragonX >= playerActualX - 1 && dragonY >= playerActualY - 1) && (dragonX <= playerActualX + 1 && dragonY <= playerActualY + 1) && !GameDataHolder.GOD_MODE) {
                isEndOfGame = true;
                endOfGameLost();
            }
        }
    }

    private synchronized void endOfGameWin() {
        TextInputDialog a = new TextInputDialog();
        a.setTitle("Siker!");
        a.setContentText("Megnyerted a játékot!");

        Optional<String> result = a.showAndWait();
        if (result.isPresent()) {
            ScoreRepository scoreRepository = new ScoreRepository();
            scoreRepository.addScore(result.get(), GameDataHolder.LEVEL, (endTime - startTime) / 1000000);
            GameDataHolder.LEVEL = GameDataHolder.LEVEL == 10 ? 10 : GameDataHolder.LEVEL++;
            Stage primaryStage = new Stage();
            StackPane root = new StackPane();
            root.getChildren().add(new WelcomePanel());
            primaryStage.setScene(new Scene(root, GameDataHolder.WINDOW_MAX_WIDTH, GameDataHolder.WINDOW_MAX_LENGTH));
            primaryStage.setTitle("Labyrinth");
            primaryStage.setResizable(false);
            primaryStage.show();
        }



        thread.stop();
        WelcomePanel.gameWindow.close();
    }

    private synchronized void endOfGameLost() {
        Alert a = new Alert(Alert.AlertType.WARNING);
        a.setTitle("GAMEOWER!");
        a.setContentText("Megevett a sárkány!");

        Optional<ButtonType> result = a.showAndWait();
        if (result.isPresent()) {
            WelcomePanel.gameWindow.close();
            thread.stop();

            Stage primaryStage = new Stage();
            StackPane root = new StackPane();
            root.getChildren().add(new WelcomePanel());
            primaryStage.setScene(new Scene(root, GameDataHolder.WINDOW_MAX_WIDTH, GameDataHolder.WINDOW_MAX_LENGTH));
            primaryStage.setTitle("Labyrinth");
            primaryStage.setResizable(false);
            primaryStage.show();
        }
    }

    public int getBoardWeight(){
        return boardArray.get(0).size()*maxImageSize;
    }

    public int getBoardHeight(){
        return boardArray.size()*maxImageSize;
    }
}