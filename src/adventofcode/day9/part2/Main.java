package adventofcode.day9.part2;

import adventofcode.day9.part2.basins.Basin;
import adventofcode.day9.part2.basins.BasinBuffer;
import adventofcode.day9.part2.basins.BasinSector;
import adventofcode.util.FileUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
                    System.out.println("checking " + line);
                    boolean addedToExistingBasin = checkBasins(line);
                    boolean newBasinCreated = !addedToExistingBasin && checkBufferedBasins(line);

                    if (!newBasinCreated && !addedToExistingBasin) {
                        BUFFER.add(new BasinBuffer(line));
                    }

                    line = new ArrayList<>();
                }
            }
        }

        for (BasinBuffer buffer : BUFFER) {
            BASINS.add(new Basin(buffer.getSectors()));
        }

        System.out.println(BUFFER.size());
        StringBuilder basingSizes = new StringBuilder();
        BASINS.sort(Collections.reverseOrder());
        BASINS.forEach(basin -> basingSizes.append(basin.getSectors().size()).append(" "));
        System.out.println(basingSizes);
        System.out.println(BASINS.get(0).getSectors().size() *
                BASINS.get(1).getSectors().size() *
                BASINS.get(2).getSectors().size());
    }

    private static boolean checkBasins(List<BasinSector> line) {
        boolean addedToExistingBasin = false;

        for (Basin basin : BASINS) {
            for (BasinSector basinSector : basin.getSectors()) {
                for (BasinSector lineSector : line) {
                    if (basinSector.getJ() == lineSector.getJ() &&
                            Math.abs(basinSector.getI() - lineSector.getI()) == 1) {
                        basin.addAll(line);
                        addedToExistingBasin = true;
                        break;
                    }
                }

                if (addedToExistingBasin) {
                    break;
                }
            }

            if (addedToExistingBasin) {
                break;
            }
        }

        return addedToExistingBasin;
    }

    private static boolean checkBufferedBasins(List<BasinSector> line) {
        boolean newBasinCreated = false;
        Basin potentialBasin = null;

        for (BasinSector basinCandidate : line) {
            for (int iBuffer = 0; iBuffer < BUFFER.size(); ++iBuffer) {
                List<BasinSector> bufferedLine = BUFFER.get(iBuffer).getSectors();
                for (BasinSector bufferedSector : bufferedLine) {
                    if (bufferedSector.getJ() == basinCandidate.getJ() &&
                            Math.abs(bufferedSector.getI() - basinCandidate.getI()) == 1) {
                        if (!newBasinCreated) {
                            potentialBasin = new Basin(new ArrayList<>(line));
                            newBasinCreated = true;
                        }

                        potentialBasin.addAll(bufferedLine);
                        BUFFER.remove(iBuffer);
                        --iBuffer;
                    }
                }
            }

        }

        if (newBasinCreated) {
            BASINS.add(potentialBasin);
        }

        return newBasinCreated;
    }
}
