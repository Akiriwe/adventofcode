package adventofcode.day8.part2.decrypt;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class NumberDisplayKey {
    private static final int ONE_LENGTH = 2;
    private static final int FOUR_LENGTH = 4;
    private static final int SEVEN_LENGTH = 3;
    private static final int TWO_THREE_FIVE = 5;
    private static final int ZERO_SIX_NINE_LENGTH = 6;
    private static final int EIGHT_LENGTH = 7;

    private final Character up;
    private final Character upLeft;
    private final Character upRight;
    private final Character center;
    private final Character downLeft;
    private final Character downRight;
    private final Character down;

    public NumberDisplayKey(List<String> encryptedNumbers) {
        //step 1
        List<Character> oneCode = defineUniqueNumber(encryptedNumbers, ONE_LENGTH);

        //step 2
        List<Character> sevenCode = defineUniqueNumber(encryptedNumbers, SEVEN_LENGTH);
        List<Character> upCode = new ArrayList<>(sevenCode);
        upCode.removeAll(oneCode);
        up = upCode.get(0);

        //step 3
        List<Character> fourCode = defineUniqueNumber(encryptedNumbers, FOUR_LENGTH);
        List<Character> upLeftAndCenterCandidates = new ArrayList<>(fourCode);
        upLeftAndCenterCandidates.removeAll(oneCode);

        //step 4
        List<Character> downLeftAndDownCandidates = defineUniqueNumber(encryptedNumbers, EIGHT_LENGTH);
        downLeftAndDownCandidates.removeAll(sevenCode);
        downLeftAndDownCandidates.removeAll(upLeftAndCenterCandidates);

        //step 5
        List<String> sixLengthCandidates = getListOfCodesWithCertainLength(encryptedNumbers, ZERO_SIX_NINE_LENGTH);
        List<Character> nineCode = defineNumberByExistingPattern(sixLengthCandidates, fourCode);
        List<Character> downCode = new ArrayList<>(nineCode);
        downCode.remove(up);
        downCode.removeAll(fourCode);
        down = downCode.get(0);
        List<Character> downLeftCode = new ArrayList<>(downLeftAndDownCandidates);
        downLeftCode.remove(down);
        downLeft = downLeftCode.get(0);

        //step 6
        List<Character> halfCircle = new ArrayList<>(oneCode);
        halfCircle.add(up);
        halfCircle.add(down);
        List<String> fiveLengthCandidates = getListOfCodesWithCertainLength(encryptedNumbers, TWO_THREE_FIVE);
        List<Character> threeCode = defineNumberByExistingPattern(fiveLengthCandidates, halfCircle);
        List<Character> upLeftSector = new ArrayList<>(upLeftAndCenterCandidates);
        upLeftSector.removeAll(threeCode);
        upLeft = upLeftSector.get(0);
        List<Character> centerSector = new ArrayList<>(upLeftAndCenterCandidates);
        centerSector.remove(upLeft);
        center = centerSector.get(0);

        //step 7
        List<Character> twoCodePieces = new ArrayList<>();
        twoCodePieces.add(up);
        twoCodePieces.add(center);
        twoCodePieces.add(downLeft);
        twoCodePieces.add(down);
        List<Character> twoCode = defineNumberByExistingPattern(fiveLengthCandidates, twoCodePieces);
        twoCode.removeAll(twoCodePieces);
        upRight = twoCode.get(0);
        oneCode.remove(upRight);
        downRight = oneCode.get(0);
    }

    public int decodeNumberUsingKey(List<String> numbers) {
        int result = 0;

        for (int i = 0; i < numbers.size(); ++i) {
            String number = numbers.get(i);

            List<Character> codeToDecode = number.chars().mapToObj(c -> (char) c).collect(Collectors.toList());
            NumberCode numberCode = new NumberCode(codeToDecode.contains(up),
                    codeToDecode.contains(upLeft), codeToDecode.contains(upRight),
                    codeToDecode.contains(center), codeToDecode.contains(downLeft),
                    codeToDecode.contains(downRight), codeToDecode.contains(down));
            result += findNumberByCode(numberCode) * Math.pow(10, numbers.size() - i - 1);
        }

        return result;
    }

    @Override
    public String toString() {
        return "NumberDisplayKey{" +
                "up=" + up +
                ", upLeft=" + upLeft +
                ", upRight=" + upRight +
                ", center=" + center +
                ", downLeft=" + downLeft +
                ", downRight=" + downRight +
                ", down=" + down +
                '}';
    }

    private List<Character> defineUniqueNumber(final List<String> candidates, final int uniqueNumberLength) {
        Optional<String> numberCode = candidates.stream()
                .filter(string -> string.length() == uniqueNumberLength)
                .findFirst();
        List<Character> codes = new ArrayList<>();
        numberCode.ifPresent(stringCode ->
                codes.addAll(stringCode.chars().mapToObj(c -> (char) c).collect(Collectors.toList())));

        return codes;
    }

    private List<Character> defineNumberByExistingPattern(final List<String> candidates,
                                                          final List<Character> numberCodeToSort) {
        Optional<List<Character>> numberCode = candidates.stream()
                .map(candidateString -> candidateString.chars().mapToObj(c -> (char) c).collect(Collectors.toList()))
                .filter(candidate -> candidate.containsAll(numberCodeToSort))
                .findFirst();
        List<Character> codes = new ArrayList<>();
        numberCode.ifPresent(codes::addAll);

        return codes;
    }

    private List<String> getListOfCodesWithCertainLength(final List<String> candidates, final int length) {
        return candidates.stream()
                .filter(string -> string.length() == length)
                .collect(Collectors.toList());
    }

    private int findNumberByCode(NumberCode numberCodeToDefine) {
        int number = -1;

        for (NumberCodes code : NumberCodes.values()) {
            if (code.numberCode.equals(numberCodeToDefine)) {
                number = code.representation;
            }
        }

        return number;
    }

    private enum NumberCodes {
        ZERO(new NumberCode(true, true, true, false, true, true, true), 0),
        ONE(new NumberCode(false, false, true, false, false, true, false), 1),
        TWO(new NumberCode(true, false, true, true, true, false, true), 2),
        THREE(new NumberCode(true, false, true, true, false, true, true), 3),
        FOUR(new NumberCode(false, true, true, true, false, true, false), 4),
        FIVE(new NumberCode(true, true, false, true, false, true, true), 5),
        SIX(new NumberCode(true, true, false, true, true, true, true), 6),
        SEVEN(new NumberCode(true, false, true, false, false, true, false), 7),
        EIGHT(new NumberCode(true, true, true, true, true, true, true), 8),
        NINE(new NumberCode(true, true, true, true, false, true, true), 9);

        private final NumberCode numberCode;
        private final int representation;

        NumberCodes(NumberCode numberCode, int representation) {
            this.numberCode = numberCode;
            this.representation = representation;
        }
    }
}
