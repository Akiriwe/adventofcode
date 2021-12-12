package adventofcode.day4.part2;

import adventofcode.day4.Bingo;
import adventofcode.day4.Board;
import adventofcode.util.FileUtils;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final String FILE_PATH = "E:\\13 - Projects\\Java\\untitled\\src\\adventofcode\\day4\\input.txt";

    public static void main(String[] args) {
        Bingo bingo = FileUtils.getBingo(FILE_PATH);
        List<Integer> numbersToCall = bingo.getNumbersToCall();
        List<Board> loserBoards = new ArrayList<>(bingo.getBoards());

        Board winningBoard = null;
        int wonNumberOfCall = 0;

        for (Integer numberToCall : numbersToCall) {
            for (int j = 0; j < loserBoards.size(); ++j) {
                Board loserBoard = loserBoards.get(j);
                loserBoard.crossNumber(numberToCall);
                if (loserBoard.checkIfWon()) {
                    winningBoard = loserBoard;
                    wonNumberOfCall = numberToCall;
                    loserBoards.remove(j);
                    --j;
                }
            }
            if (loserBoards.isEmpty()) {
                break;
            }
        }

        System.out.println(winningBoard);
        System.out.println(winningBoard.countSumOfUncrossedNumbers() * wonNumberOfCall);
    }
}
