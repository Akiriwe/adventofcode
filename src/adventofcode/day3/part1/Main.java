package adventofcode.day3.part1;

import adventofcode.util.FileUtils;

import java.util.List;

public class Main {
    private static final String FILE_PATH = "E:\\13 - Projects\\Java\\untitled\\src\\adventofcode\\day3\\input.txt";

    public static void main(String[] args) {
        List<List<Character>> codes = FileUtils.getCharacterMatrix(FILE_PATH);
        int length = codes.size();
        int[] zerosAmount = new int[codes.get(0).size()];

        for (List<Character> code : codes) {
            for (int j = 0; j < code.size(); ++j) {
                if (code.get(j) == '0') {
                    ++zerosAmount[j];
                }
            }
        }

        int gammaRate = 0;
        int epsilonRate = 0;
        for (int i = 0; i < zerosAmount.length; ++i) {
            if (zerosAmount[i] < length / 2) {
                gammaRate += Math.pow(2, zerosAmount.length - i - 1.0);
            } else {
                epsilonRate += Math.pow(2, zerosAmount.length - i - 1.0);
            }
        }


        System.out.println(gammaRate * epsilonRate);
    }
}
