package elte.gottfried.ui;

import elte.gottfried.db.Score;
import elte.gottfried.db.ScoreRepository;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import java.util.List;

public class ResultsPanel extends GridPane {

    public ResultsPanel() {
        ScoreRepository scoreRepository = new ScoreRepository();
        List<Score> scores = scoreRepository.getScoreresultset();

        for (int i = 0; i < scores.size(); i++) {
            String text = (i + 1) + ", " + scores.get(i).getName() + ": Level - " + scores.get(i).getLevel() + " - " + scores.get(i).getScore();
            add(new Label(text), 0, i);
        }
    }
    
    public void refresh(){
        ScoreRepository scoreRepository = new ScoreRepository();
        List<Score> scores = scoreRepository.getScoreresultset();

        if(scores.size() > getChildren().size()){
            getChildren().removeAll();
            for (int i = 0; i < scores.size(); i++) {
                String text = (i + 1) + ", " + scores.get(i).getName() + ": Level - " + scores.get(i).getLevel() + " - " + scores.get(i).getScore();
                add(new Label(text), 0, i);
            }
        }
    }
}
