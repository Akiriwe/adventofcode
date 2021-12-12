package adventofcode.day9.part1;

import adventofcode.util.FileUtils;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final String FILE_PATH = "E:\\13 - Projects\\Java\\untitled\\src\\adventofcode\\day9\\input.txt";
    private static final List<List<Integer>> ENTRIES = FileUtils.getIntegerMatrix(FILE_PATH);
    private static final Integer RISK_LEVEL = 1;

    public static void main(String[] args) {
        int sum = 0;

        for (int i = 0; i < ENTRIES.size(); ++i) {
            for (int j = 0; j < ENTRIES.get(i).size(); ++j) {
                List<Integer> neighbors = new ArrayList<>();

                if (i != 0) {
                    neighbors.add(ENTRIES.get(i - 1).get(j));
                }
                if (i != ENTRIES.size() - 1) {
                    neighbors.add(ENTRIES.get(i + 1).get(j));
                }

                if (j != 0) {
                    neighbors.add(ENTRIES.get(i).get(j - 1));
                }
                if (j != ENTRIES.get(i).size() - 1) {
                    neighbors.add(ENTRIES.get(i).get(j + 1));
                }

                boolean lowPoint = true;

                for (int neighbor : neighbors) {
                    if (ENTRIES.get(i).get(j) >= neighbor) {
                        lowPoint = false;
                        break;
                    }
                }

                if (lowPoint) {
                    System.out.println("Found new local low point, i - " + i + ", j - " + j + ", height - " +
                            ENTRIES.get(i).get(j));
                    sum += ENTRIES.get(i).get(j) + RISK_LEVEL;
                }
            }
        }

        System.out.println(sum);
    }
}
