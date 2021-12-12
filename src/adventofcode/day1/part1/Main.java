package adventofcode.day1.part1;

import adventofcode.util.FileUtils;

import java.util.List;

public class Main {
    private static final String FILE_PATH = "E:\\13 - Projects\\Java\\untitled\\out\\production\\untitled\\adventofcode\\day1\\input.txt";

    public static void main(String[] args) {
        List<Integer> depths = FileUtils.getIntegers(FILE_PATH);
        int counter = 0;

        for (int i = 1; i < depths.size(); ++i) {
            if (depths.get(i - 1) < depths.get(i)) {
                ++counter;
            }
        }

        System.out.println(counter);
    }
}
