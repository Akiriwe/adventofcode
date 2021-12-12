package adventofcode.day8.part2;

import adventofcode.day8.part2.decrypt.NumberDisplayKey;
import adventofcode.util.FileUtils;

import java.util.Arrays;
import java.util.List;

public class Main {
    private static final String FILE_PATH = "E:\\13 - Projects\\Java\\untitled\\src\\adventofcode\\day8\\input.txt";
    private static final List<String> ENTRIES = FileUtils.getStrings(FILE_PATH);

    public static void main(String[] args) {
        int sum = 0;
        for (String entry : ENTRIES) {
            String delimiter = " \\| ";
            String[] parts = entry.split(delimiter);
            List<String> encryptedCodes = Arrays.asList(parts[0].split(" "));
            List<String> codesToDecrypt = Arrays.asList(parts[1].split(" "));
            NumberDisplayKey numberDisplayKey = new NumberDisplayKey(encryptedCodes);
            sum += numberDisplayKey.decodeNumberUsingKey(codesToDecrypt);
        }

        System.out.println(sum);
    }
}
