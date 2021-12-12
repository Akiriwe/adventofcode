package adventofcode.day6.part1;

import adventofcode.util.FileUtils;

import java.util.List;

public class Main {
    private static final String FILE_PATH = "E:\\13 - Projects\\Java\\untitled\\src\\adventofcode\\day6\\input.txt";
    private static final Integer ADULT_LIFE_CYCLE = 6;
    private static  final Integer CHILD_LIFE_CYCLE = 8;
    private static final List<Integer> LIFE_CYCLES = FileUtils.getIntegersSeparatedByCommas(FILE_PATH);

    public static void main(String[] args) {
        simulateLifeCycles(80);
        System.out.println(LIFE_CYCLES);
        System.out.println(LIFE_CYCLES.size());
    }

    private static void simulateLifeCycles(int amountOfDays) {
        for (int i = 0; i < amountOfDays; ++i) {
            int childrenAmount = 0;
            for (int j = 0; j < LIFE_CYCLES.size() - childrenAmount; ++j) {
                if (LIFE_CYCLES.get(j).equals(0)) {
                    LIFE_CYCLES.set(j, ADULT_LIFE_CYCLE);
                    LIFE_CYCLES.add(CHILD_LIFE_CYCLE);
                    ++childrenAmount;
                } else {
                    LIFE_CYCLES.set(j, LIFE_CYCLES.get(j) - 1);
                }
            }
        }
    }
}
