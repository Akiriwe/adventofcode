package adventofcode.day3.part2;

import adventofcode.util.FileUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    private static final String FILE_PATH = "E:\\13 - Projects\\Java\\untitled\\src\\adventofcode\\day3\\input.txt";

    public static void main(String[] args) {
        List<List<Character>> codes = FileUtils.getCharacterMatrix(FILE_PATH);
        List<Character> oxygenCodes = getCode(new ArrayList<>(codes), false);
        System.out.println(oxygenCodes);
        List<Character> co2Codes = getCode(new ArrayList<>(codes), true);
        System.out.println(co2Codes);

        int oxygenRate = getDecimal(oxygenCodes);
        int co2Rate = getDecimal(co2Codes);

        System.out.println(oxygenRate * co2Rate);
    }

    private static int getDecimal(List<Character> binary) {
        int value = 0;
        for (int i = 0; i < binary.size(); ++i) {
            if (binary.get(i) == '1') {
                value += Math.pow(2, binary.size() - i - 1.0);
            }
        }

        return value;
    }

    private static List<Character> getCode(List<List<Character>> codes, boolean isLeast) {
        for (int j = 0; j < codes.get(0).size() && codes.size() > 1; ++j) {
            int zerosAmount = 0;
            final int valueToFilter = j;

            for (List<Character> code : codes) {
                if (code.get(j) == '0') {
                    ++zerosAmount;
                }
            }

            char charToFilter;
            if (isLeast) {
                charToFilter = zerosAmount <= codes.size() / 2 ? '0' : '1';
            } else {
                charToFilter = zerosAmount > codes.size() / 2 ? '0' : '1';
            }
            codes = codes.stream()
                    .filter(value -> value.get(valueToFilter) == charToFilter)
                    .collect(Collectors.toList());
        }

        return codes.get(0);
    }
}
