package adventofcode.day5;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class CoordinateGraph {
    private final List<List<Short>> graph;

    public CoordinateGraph(short xLength, short yLength) {
        graph = new ArrayList<>();
        for (int i = 0; i < yLength; ++i) {
            List<Short> row = new ArrayList<>();
            for (int j = 0; j < xLength; ++j) {
                row.add((short) 0);
            }
            graph.add(row);
        }
    }

    public void markVents(Coordinates coordinates) {
        if (coordinates.getFromX() == coordinates.getToX() || coordinates.getFromY() == coordinates.getToY()) {
            int fromX = Math.min(coordinates.getFromX(), coordinates.getToX());
            int toX = Math.max(coordinates.getFromX(), coordinates.getToX());
            int fromY = Math.min(coordinates.getFromY(), coordinates.getToY());
            int toY = Math.max(coordinates.getFromY(), coordinates.getToY());
            for (int y = fromY; y <= toY; ++y) {
                for (int x = fromX; x <= toX; ++x) {
                    graph.get(y).set(x, (short) (graph.get(y).get(x) + 1));
                }
            }
        } else {
            short x = coordinates.getFromX();
            short y = coordinates.getFromY();
            boolean increaseX = coordinates.getFromX() < coordinates.getToX();
            boolean increaseY = coordinates.getFromY() < coordinates.getToY();
            while (increaseY ? y <= coordinates.getToY() : y >= coordinates.getToY()) {
                graph.get(y).set(x, (short) (graph.get(y).get(x) + 1));

                y = (short) (increaseY ? y + 1 : y - 1);
                x = (short) (increaseX ? x + 1 : x - 1);
            }
        }
    }

    public short returnAmountOfSpotsMoreThan2() {
        short counter = 0;

        for (List<Short> y : graph) {
            for (Short x : y) {
                if (x > 1) {
                    ++counter;
                }
            }
        }

        return counter;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        for (List<Short> y : graph) {
            for (Short x : y) {
                stringBuilder.append(x).append(" ");
            }
            stringBuilder.append(System.lineSeparator());
        }

        return stringBuilder.toString();
    }
}
