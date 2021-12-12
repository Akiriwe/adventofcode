package adventofcode.day1.part2;

import adventofcode.util.FileUtils;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final String FILE_PATH = "E:\\13 - Projects\\Java\\untitled\\out\\production\\untitled\\adventofcode\\day1\\input.txt";

    public static void main(String[] args)  {
        List<Integer> depths = FileUtils.getIntegers(FILE_PATH);
        List<Integer> sums = new ArrayList<>();
        int counter = 0;

        for (int i = 2; i < depths.size(); ++i) {
            sums.add(depths.get(i - 2) + depths.get(i - 1) + depths.get(i));
        }

        for (int i = 1; i < sums.size(); ++i) {
            if (sums.get(i - 1) < sums.get(i)) {
                ++counter;
            }
        }

        System.out.println(counter);
    }
}
