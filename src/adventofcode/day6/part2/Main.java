package adventofcode.day6.part2;

import adventofcode.util.FileUtils;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final String FILE_PATH = "E:\\13 - Projects\\Java\\untitled\\src\\adventofcode\\day6\\input.txt";
    private static final Integer ADULT_LIFE_CYCLE = 6;
    private static final Integer CHILD_LIFE_CYCLE = 8;
    private static final List<Long> FISH_POPULATION = new ArrayList<>();

    public static void main(String[] args) {
        for (int i = 0; i <= CHILD_LIFE_CYCLE; ++i) {
            FISH_POPULATION.add(0L);
        }

        organizeInitialPopulation();
        simulateLifeCycles(256);
        long result = 0;
        for (long j : FISH_POPULATION) {
            result += j;
        }
        System.out.println(result);
    }

    private static void organizeInitialPopulation() {
        final List<Integer> initialFish = FileUtils.getIntegersSeparatedByCommas(FILE_PATH);
        for (Integer indexToSet : initialFish) {
            FISH_POPULATION.set(indexToSet, FISH_POPULATION.get(indexToSet) + 1);
        }
    }

    private static void simulateLifeCycles(int amountOfDays) {
        for (int i = 0; i < amountOfDays; ++i) {
            System.out.println("DAY: " + (i + 1));

            long buffer = FISH_POPULATION.get(0);
            for (int j = 0; j < FISH_POPULATION.size() - 1; ++j) {
                FISH_POPULATION.set(j, FISH_POPULATION.get(j + 1));
            }

            FISH_POPULATION.set(ADULT_LIFE_CYCLE, FISH_POPULATION.get(ADULT_LIFE_CYCLE) + buffer);
            FISH_POPULATION.set(CHILD_LIFE_CYCLE, buffer);


            System.out.println(FISH_POPULATION);
        }
    }
}
