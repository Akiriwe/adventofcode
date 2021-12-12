package adventofcode.day5.part1and2;

import adventofcode.day5.CoordinateGraph;
import adventofcode.day5.Coordinates;
import adventofcode.util.FileUtils;

import java.util.List;

public class Main {
    private static final String FILE_PATH = "E:\\13 - Projects\\Java\\untitled\\src\\adventofcode\\day5\\input.txt";

    public static void main(String[] args) {
        List<Coordinates> coordinates = FileUtils.getCoordinates(FILE_PATH);
        CoordinateGraph graph = new CoordinateGraph((short) 1000, (short)  1000);

        for (Coordinates coordinate : coordinates) {
            graph.markVents(coordinate);
        }

          System.out.println(graph);
        System.out.println(graph.returnAmountOfSpotsMoreThan2());
    }
}
