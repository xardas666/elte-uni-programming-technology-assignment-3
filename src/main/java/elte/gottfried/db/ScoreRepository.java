package elte.gottfried.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ScoreRepository extends BaseRepository {

    private final int NAME = 1;
    private final int LEVEL = 2;
    private final int SCORE = 3;

    public List<Score> getScoreresultset() {
        try {
            setupConnection();

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT NAME, LEVEL, SCORE FROM SCORE ORDER BY LEVEL DESC, SCORE ASC LIMIT 10;");

            List<Score> scores = new ArrayList<>();
            while (rs.next()) {
                scores.add(new Score(rs.getString(NAME), rs.getInt(LEVEL), rs.getInt(SCORE)));
            }

            con.close();

            return scores;
        } catch (Exception e) {
            System.out.println(e.getStackTrace().toString());
        }

        return null;
    }

    public void addScore(String name, long level, long score) {
        try {
            setupConnection();

            PreparedStatement stmt = con.prepareStatement("INSERT INTO SCORE (NAME, LEVEL, SCORE) VALUES (?,?,?); ");
            stmt.setString(NAME, name);
            stmt.setInt(LEVEL, (int) level);
            stmt.setInt(SCORE, (int) score);

            stmt.execute();

            con.close();

        } catch (Exception e) {
            System.out.println(e.getStackTrace().toString());
        }
    }


}