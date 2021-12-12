package adventofcode.day2.part1;

import adventofcode.util.FileUtils;

import java.util.List;

public class Main {
    private static String FILE_PATH = "E:\\13 - Projects\\Java\\untitled\\src\\adventofcode\\day2\\input.txt";

    public static void main(String[] args) {
        List<String> autoPilotSettings = FileUtils.getStrings(FILE_PATH);
        int horizontal = 0;
        int depth = 0;

        for (String entry : autoPilotSettings) {
            String[] directionAndDistance = entry.split(" ");
            String key = directionAndDistance[0];
            int value = Integer.parseInt(directionAndDistance[1]);

            switch (key) {
                case "forward":
                    horizontal += value;
                    break;
                case "up":
                    depth -= value;
                    break;
                case "down":
                    depth += value;
                    break;
                default:
                    throw new RuntimeException("unknown direction");
            }
        }

        System.out.println(horizontal * depth);
    }
}
