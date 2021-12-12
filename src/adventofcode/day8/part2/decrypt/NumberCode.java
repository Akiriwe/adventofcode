package adventofcode.day8.part2.decrypt;

import java.util.Objects;

public class NumberCode {
    private final boolean up;
    private final boolean upLeft;
    private final boolean upRight;
    private final boolean center;
    private final boolean downLeft;
    private final boolean downRight;
    private final boolean down;

    public NumberCode(boolean up, boolean upLeft, boolean upRight,
                      boolean center, boolean downLeft, boolean downRight, boolean down) {
        this.up = up;
        this.upLeft = upLeft;
        this.upRight = upRight;
        this.center = center;
        this.downLeft = downLeft;
        this.downRight = downRight;
        this.down = down;
    }

    public boolean isUp() {
        return up;
    }

    public boolean isUpLeft() {
        return upLeft;
    }

    public boolean isUpRight() {
        return upRight;
    }

    public boolean isCenter() {
        return center;
    }

    public boolean isDownLeft() {
        return downLeft;
    }

    public boolean isDownRight() {
        return downRight;
    }

    public boolean isDown() {
        return down;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NumberCode that = (NumberCode) o;
        return up == that.up && upLeft == that.upLeft && upRight == that.upRight && center == that.center &&
                downLeft == that.downLeft && downRight == that.downRight && down == that.down;
    }

    @Override
    public int hashCode() {
        return Objects.hash(up, upLeft, upRight, center, downLeft, downRight, down);
    }

    @Override
    public String toString() {
        return representSingleLine(up) +
                representDoubleLines(upLeft, upRight) +
                representSingleLine(center) +
                representDoubleLines(downLeft, downRight) +
                representSingleLine(down);
    }

    private String representSingleLine(boolean isNotEmpty) {
        return (isNotEmpty ? " **** " : " .... ") + System.lineSeparator();
    }

    private String representDoubleLines(boolean leftSide, boolean rightSide) {
        StringBuilder lines = new StringBuilder();

        if (leftSide && rightSide) {
            lines.append("*    *").append(System.lineSeparator());
            lines.append("*    *").append(System.lineSeparator());
        }

        if (leftSide && !rightSide) {
            lines.append("*    .").append(System.lineSeparator());
            lines.append("*    .").append(System.lineSeparator());
        }

        if (!leftSide && rightSide) {
            lines.append(".    *").append(System.lineSeparator());
            lines.append(".    *").append(System.lineSeparator());
        }

        return lines.toString();
    }
}
