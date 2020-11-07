package mastermind;

import mastermind.gamePlay.GameEngine;
import mastermind.gamePlay.Score;
import mastermind.strategy.RememberingGuessChecker;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RememberingGuessCheckerTest {

    private RememberingGuessChecker checker;

    @Before
    public void setUp() {
        checker = new RememberingGuessChecker();
    }

    @Test
    public void withNoGuesses_everyGuessPasses() {
        for (int i = 0; i < GameEngine.MAX_CODES; i++)
            assertTrue(checker.shouldTry(GameEngine.Guesser.makeGuess(i)));
    }

    @Test
    public void anyGuessThatDoesNotMatch_willFailAgain() {
        checker.addScore("AAAA", new Score(0, 0));
        checker.addScore("BBBB", new Score(1, 1));
        assertGuesses("~AAAA", "~BBBB");
    }

    @Test
    public void ifThereAreNoAs_onlyGuessesWithAsWillFail() {
        checker.addScore("AAAA", new Score(0, 0));
        assertGuesses("~ABCD", "~BBAB", "~CCCA", "BBBB");
    }

    @Test
    public void ifThereAreNoAsOrBs_onlyGuesesWithAsAndBsWillFail() {
        checker.addScore("AAAA", new Score(0, 0));
        checker.addScore("BBBB", new Score(0, 0));
        assertGuesses("~CCCA", "~CCCB", "CCCD");
    }

    @Test
    public void ifTheresOneInTheRightPosition_thenOnlyThoseThatHaveJustOneInPositionWillPass() {
        checker.addScore("ABCD", new Score(1, 0));
        assertGuesses("AEEE", "EBEE", "EECE", "EEED", "~DCBA", "~EEEE", "~ABCD", "~ABEE");
    }

    @Test
    public void ifTheresOneOutOfPosition_thenOnlyThoseThatHaveJustOneInAnotherPositionWillPass() {
        checker.addScore("ABCD", new Score(0, 1));
        assertGuesses("EAEE", "BEEE", "ECEE", "DEEE", "EEEA", "~AEEE", "~EECE", "~EEAB");
    }

    @Test
    public void thereIsOneA() {
        checker.addScore("AAAA", new Score(1, 0));
        assertGuesses("ABBB");
    }

    public void assertGuesses(String... guesses) {
        for (String guess : guesses) {
            if (guess.startsWith("~")) {
                String myGuess = guess.substring(1);
                assertFalse(guess, checker.shouldTry(myGuess));
            } else {
                assertTrue(guess, checker.shouldTry(guess));
            }
        }
    }
}
