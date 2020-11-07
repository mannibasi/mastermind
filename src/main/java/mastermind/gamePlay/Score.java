package mastermind.gamePlay;

import java.util.Objects;

public class Score {
    private final int inPosition;
    private final int inCode;

    public Score(int inPosition, int inCode) {
        this.inPosition = inPosition;
        this.inCode = inCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score = (Score) o;
        return inPosition == score.inPosition && inCode == score.inCode;
    }

    @Override
    public int hashCode() {
        return Objects.hash(inPosition, inCode);
    }

    @Override
    public String toString() {
        return String.format("(%d, %d)", inPosition, inCode);
    }

    public int inPosition() {
        return inPosition;
    }

    public int inCode() {
        return inCode;
    }
}
