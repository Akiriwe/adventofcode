package adventofcode.day8.part1;

import adventofcode.util.FileUtils;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    private static final String FILE_PATH = "E:\\13 - Projects\\Java\\untitled\\src\\adventofcode\\day8\\input.txt";
    private static final List<String> ENTRIES = FileUtils.getOutputEntry(FILE_PATH);
    private static final List<Integer> UNIQUE_LENGTHS = Arrays.asList(2, 4, 3, 7); // 1, 4, 7, 8

    public static void main(String[] args) {
        final AtomicInteger counter = new AtomicInteger(0);
        ENTRIES.forEach(entry -> increaseIfContainsUniqueLength(entry, counter));

        System.out.println(counter);
    }

    private static void increaseIfContainsUniqueLength(String entry, AtomicInteger counter) {
        if (UNIQUE_LENGTHS.contains(entry.length())) {
            counter.incrementAndGet();
        }
    }
}
