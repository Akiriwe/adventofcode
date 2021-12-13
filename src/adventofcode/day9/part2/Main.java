package adventofcode.day9.part2;

import adventofcode.day9.part2.basins.Basin;
import adventofcode.day9.part2.basins.BasinBuffer;
import adventofcode.day9.part2.basins.BasinSector;
import adventofcode.util.FileUtils;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Main {
    private static final String FILE_PATH = "E:\\13 - Projects\\Java\\untitled\\src\\adventofcode\\day9\\input.txt";
    private static final List<List<Integer>> ENTRIES = FileUtils.getIntegerMatrix(FILE_PATH);
    private static final List<BasinBuffer> BUFFER = new ArrayList<>();
    private static final List<Basin> BASINS = new ArrayList<>();

    public static void main(String[] args) {

        for (int i = 0; i < ENTRIES.size(); ++i) {
            List<BasinSector> line = new ArrayList<>();
            for (int j = 0; j < ENTRIES.get(i).size(); ++j) {
                if (ENTRIES.get(i).get(j) != 9) {
                    line.add(new BasinSector(i, j, ENTRIES.get(i).get(j)));
                }

                if (!line.isEmpty() && (ENTRIES.get(i).get(j) == 9 || j == ENTRIES.get(i).size() - 1)) {
                    String msg = "checking " + line;
                    //checking [BasinSector{i=2, j=18, height=8}, BasinSector{i=2, j=19, height=7}, BasinSector{i=2, j=20, height=8}, BasinSector{i=2, j=21, height=6}, BasinSector{i=2, j=22, height=7}, BasinSector{i=2, j=23, height=6}, BasinSector{i=2, j=24, height=7}]
                    System.out.println(msg);

                    BUFFER.add(new BasinBuffer(line));
                    line = new ArrayList<>();
                }
            }

            checkBasins();

            for (BasinBuffer buffer : BUFFER) {
                BASINS.add(new Basin(buffer.getSectors()));
            }

            BUFFER.clear();

            BASINS.forEach(System.out::println);
            System.out.println();
        }

        for (BasinBuffer buffer : BUFFER) {
            BASINS.add(new Basin(buffer.getSectors()));
        }

        StringBuilder basingSizes = new StringBuilder();
        BASINS.sort(Collections.reverseOrder());
        BASINS.forEach(basin -> basingSizes.append(basin.getSectors().size()).append(" "));
        System.out.println(basingSizes);
        System.out.println(BASINS);

        System.out.println(BASINS.get(0).getSectors().size() *
                BASINS.get(1).getSectors().size() *
                BASINS.get(2).getSectors().size());
    }

    private static void checkBasins() {
        List<Pair<Basin, List<BasinSector>>> sectorsToAdd = new ArrayList<>();

        for (Basin basin : BASINS) {
            for (BasinSector basinSector : basin.getSectors()) {
                for (Iterator<BasinBuffer> bufferIterator = BUFFER.iterator(); bufferIterator.hasNext(); ) {
                    BasinBuffer basinBuffer = bufferIterator.next();
                    List<BasinSector> sectors = basinBuffer.getSectors();
                    for (BasinSector bufferSector : sectors) {
                        if (basinSector.getJ() == bufferSector.getJ() &&
                                Math.abs(basinSector.getI() - bufferSector.getI()) == 1) {
                            sectorsToAdd.add(new Pair<>(basin, sectors));
                            bufferIterator.remove();
                            break;
                        }
                    }
                }
            }
        }

        System.out.println("BASINS TO ENHANCE");
        sectorsToAdd.forEach(pair -> System.out.println(pair.getKey() + " : " + pair.getValue()));
        sectorsToAdd.forEach(pair -> pair.getKey().addAll(pair.getValue()));
    }
}
