package mastermind.strategy;

import mastermind.gameplay.GuessChecker;
import mastermind.gameplay.Score;

import java.util.ArrayList;
import java.util.List;

public class RememberingGuessChecker implements GuessChecker {
    private final List<ScoreRecord> scoreHistory = new ArrayList<>();

    public boolean shouldTry(String guess) {
        return isGuessConsistentWithHistory(guess);
    }

    private boolean isGuessConsistentWithHistory(String guess) {
        for (ScoreRecord previous : scoreHistory)
            if (!Scorer.scoreGuess(guess, previous.guess).equals(previous.score))
                return false;

        return true;
    }

    public void addScore(String guess, Score score) {
        scoreHistory.add(new ScoreRecord(guess, score));
    }

    private static class ScoreRecord {
        private final String guess;
        private final Score score;

        public ScoreRecord(String guess, Score score) {
            this.guess = guess;
            this.score = score;
        }
    }
}
