package adventofcode.day7.part2;

import adventofcode.util.FileUtils;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Main {
    private static final String FILE_PATH = "E:\\13 - Projects\\Java\\untitled\\src\\adventofcode\\day7\\input.txt";
    private static final Map<Integer, Integer> FREQUENCIES = new TreeMap<>();
    private static final List<Integer> HORIZONTAL_POSITIONS = FileUtils.getIntegersSeparatedByCommas(FILE_PATH);
    private static final int FIRST_MOVE_FUEL = 1;

    public static void main(String[] args) {
        for (Integer position : HORIZONTAL_POSITIONS) {
            FREQUENCIES.computeIfPresent(position, (key, value) -> ++value);
            FREQUENCIES.putIfAbsent(position, 1);
        }

        int min = FREQUENCIES.keySet().stream().min(Comparator.naturalOrder()).orElse(0);
        int max = FREQUENCIES.keySet().stream().max(Comparator.naturalOrder()).orElse(0);

        int closestPosition = min;
        int fuelSumToClosestPosition = Integer.MAX_VALUE;

        for (int closestPositionCandidate = min; closestPositionCandidate <= max; ++closestPositionCandidate) {
            int fuelSumToClosestPositionCandidate = 0;
            for (Map.Entry<Integer, Integer> positionWithFrequency : FREQUENCIES.entrySet()) {
                int diffAbs = Math.abs(positionWithFrequency.getKey() - closestPositionCandidate);
                double arithmeticProgression = (FIRST_MOVE_FUEL + diffAbs) / 2.0 * diffAbs;

                fuelSumToClosestPositionCandidate += positionWithFrequency.getValue() * arithmeticProgression;
            }

            if (fuelSumToClosestPositionCandidate < fuelSumToClosestPosition) {
                fuelSumToClosestPosition = fuelSumToClosestPositionCandidate;
                closestPosition = closestPositionCandidate;
            }
        }

        System.out.println(FREQUENCIES);
        System.out.println(closestPosition + " " + fuelSumToClosestPosition);
    }
}
