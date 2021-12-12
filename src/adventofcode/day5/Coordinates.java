package adventofcode.day5;

public class Coordinates {
    private short fromX;
    private short fromY;
    private short toX;
    private short toY;

    public Coordinates(short fromX, short fromY, short toX, short toY) {
        this.fromX = fromX;
        this.fromY = fromY;
        this.toX = toX;
        this.toY = toY;
    }

    public short getFromX() {
        return fromX;
    }

    public short getFromY() {
        return fromY;
    }

    public short getToX() {
        return toX;
    }

    public short getToY() {
        return toY;
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "fromX=" + fromX +
                ", fromY=" + fromY +
                ", toX=" + toX +
                ", toY=" + toY +
                '}';
    }
}
