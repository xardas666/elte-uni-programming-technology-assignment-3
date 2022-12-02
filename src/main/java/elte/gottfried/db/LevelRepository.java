package elte.gottfried.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LevelRepository extends BaseRepository {

    private final int LEVEL = 1;
    private final int LEVEL_TEXT = 1;

    public List<List<Character>> getLabyrinthByLevel(int level) {
        String levelText = null;
        try {
            setupConnection();

            PreparedStatement stmt = con.prepareStatement("SELECT LEVEL_TEXT FROM LEVEL WHERE LEVEL = ?;");
            stmt.setInt(LEVEL, level);

            ResultSet result = stmt.executeQuery();

            result.next();
            levelText = result.getString(LEVEL_TEXT);


            con.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (levelText != null) {
            String[] tomb = levelText.split("\n");

            List<List<Character>> labyrint = new ArrayList<>();

            for (int i = 0; i < tomb.length; i++) {
                List<Character> sor = new ArrayList<>();

                for (char c : tomb[i].toCharArray()) {
                    if (c == '#') {
                        sor.add(c);
                    } else if (c == '.') {
                        sor.add(c);
                    } else if (c == 'o') {
                        sor.add(c);
                    } else if (c == 'x') {
                        sor.add(c);
                    } else if (c != ' ') {
                        throw new RuntimeException();
                    }
                }
                labyrint.add(sor);
            }

            return labyrint;
        } else {
            return null;
        }
    }
}
