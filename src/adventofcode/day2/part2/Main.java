package adventofcode.day2.part2;

import adventofcode.util.FileUtils;

import java.util.List;

public class Main {
    private static String FILE_PATH = "E:\\13 - Projects\\Java\\untitled\\src\\adventofcode\\day2\\input.txt";

    public static void main(String[] args) {
        List<String> autoPilotSettings = FileUtils.getStrings(FILE_PATH);
        int horizontal = 0;
        int depth = 0;
        int aim = 0;

        for (String entry : autoPilotSettings) {
            String[] directionAndDistance = entry.split(" ");
            String key = directionAndDistance[0];
            int value = Integer.parseInt(directionAndDistance[1]);

            switch (key) {
                case "forward":
                    horizontal += value;
                    depth += value * aim;
                    break;
                case "up":
                    aim -= value;
                    break;
                case "down":
                    aim += value;
                    break;
                default:
                    throw new RuntimeException("unknown direction");
            }
        }

        System.out.println(horizontal * depth);
    }
}
