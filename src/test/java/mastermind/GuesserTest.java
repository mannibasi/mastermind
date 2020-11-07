package mastermind;

import mastermind.gameplay.GameEngine;
import mastermind.gameplay.GuessChecker;
import mastermind.gameplay.Score;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class GuesserTest {

    private GuessChecker failingChecker;

    @Before
    public void setUp() {

        failingChecker = new GuessChecker() {
            public boolean shouldTry(String guess) {
                return false;
            }

            public void addScore(String guess, Score score) {
            }
        };
    }

    @Test
    public void whenNoGuessesAreValid_nextGuessIsNil() {
        GameEngine.Guesser guesser = new GameEngine.Guesser(failingChecker);
        assertNull(guesser.getNextGuess());
    }

    @Test
    public void WhenOnlyOneGuessIsValid_nextGuessIsThatOne() {
        GameEngine.Guesser guesser = new GameEngine.Guesser(new SingleChecker("BEEF"));
        assertEquals("BEEF", guesser.getNextGuess());
        assertNull(guesser.getNextGuess());
    }

    @Test
    public void makeGuess() {
        assertEquals("AAAA", GameEngine.Guesser.makeGuess(0));
        assertEquals("AAAB", GameEngine.Guesser.makeGuess(1));
        assertEquals("FFFF", GameEngine.Guesser.makeGuess(GameEngine.MAX_CODES - 1));
        assertNull(GameEngine.Guesser.makeGuess(GameEngine.MAX_CODES));
        assertNull(GameEngine.Guesser.makeGuess(-1));
    }


}

class SingleChecker implements GuessChecker {
    private final String validGuess;

    public SingleChecker(String validGuess) {
        this.validGuess = validGuess;
    }

    public boolean shouldTry(String guess) {
        return guess.equals(validGuess);
    }

    public void addScore(String guess, Score score) {
    }
}
