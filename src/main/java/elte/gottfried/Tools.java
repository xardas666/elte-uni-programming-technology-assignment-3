package elte.gottfried;

public class Tools {
    public static int generate(int min, int max) {
        return min + (int) (Math.random() * ((max - min) + 1));
    }
}
