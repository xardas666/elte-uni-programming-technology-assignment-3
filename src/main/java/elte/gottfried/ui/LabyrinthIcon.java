package elte.gottfried.ui;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public enum LabyrinthIcon {

    WELCOME("welcome.png"),
    DRAGON("dragon.png"),
    PLAYER("player.png"),
    WALL("wall.jpg"),
    DARK("dark.jpg"),
    PATH("path.jpg"),
    DOOR("door.png");

    String imageName;

    LabyrinthIcon(String imageName) {
        this.imageName = imageName;
    }

    public Image getImage() {
        try {
            return new Image(new FileInputStream(imageName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
