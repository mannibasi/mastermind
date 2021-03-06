package mastermind.strategy;

import mastermind.gameplay.GameEngine;
import mastermind.gameplay.Score;

public class Scorer {
    private final boolean[] codePositionUsed;
    private final boolean[] guessPositionUsed;
    private final String code;
    private final String guess;

    public Scorer(String code, String guess) {
        this.code = code;
        this.guess = guess;
        codePositionUsed = new boolean[GameEngine.POSITIONS];
        guessPositionUsed = new boolean[GameEngine.POSITIONS];
    }

    public static Score scoreGuess(String code, String guess) {
        return new Scorer(code, guess).scoreIt();
    }

    public Score scoreIt() {
        return new Score(countLettersInPosition(), countLettersOutOfPosition());
    }

    private int countLettersInPosition() {
        int inPosition = 0;
        for (int i = 0; i < code.length(); i++)
            if (isInPosition(i))
                inPosition++;
        return inPosition;
    }

    private boolean isInPosition(int i) {
        if (code.charAt(i) == guess.charAt(i)) {
            codePositionUsed[i] = true;
            guessPositionUsed[i] = true;
            return true;
        }
        else
            return false;
    }

    private int countLettersOutOfPosition() {
        int outOfPosition = 0;
        for (int ic = 0; ic < code.length(); ic++)
            if (isOutOfPosition(ic))
                outOfPosition++;
        return outOfPosition;
    }

    private boolean isOutOfPosition(int ic) {
        for (int ig = 0; ig < guess.length(); ig++)
            if (!codePositionUsed[ic] && !guessPositionUsed[ig] && ig != ic && guess.charAt(ig) == code.charAt(ic)) {
                codePositionUsed[ic] = true;
                guessPositionUsed[ig] = true;
                return true;
            }
        return false;
    }
}
