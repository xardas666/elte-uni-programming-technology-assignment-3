package elte.gottfried.ui;

import elte.gottfried.Tools;

public class Dragon {

    private final int up = 1;
    private final int down = 2;
    private final int left = 3;
    private final int right = 4;
    private int x;
    private int y;
    private int direction = 1;

    public Dragon() {
        step();
    }

    public boolean move(boolean nextStepWouldBeAWall) {
        if (nextStepWouldBeAWall) {
            int newDirection = Tools.generate(1, 4);
            while (newDirection == direction) {
                newDirection = Tools.generate(1, 4);
            }
            direction = newDirection;
            step();
            return false;
        } else {
            step();
            return true;
        }
    }

    public void step() {
        switch (direction) {
            case up:
                this.x = -1;
                this.y = 0;
                break;
            case down:
                this.x = 1;
                this.y = 0;
                break;
            case left:
                this.y = -1;
                this.x = 0;
                break;
            case right:
                this.y = 1;
                this.x = 0;
                break;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
