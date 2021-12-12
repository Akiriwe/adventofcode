package adventofcode.day4.part1;

import adventofcode.day4.Bingo;
import adventofcode.day4.Board;
import adventofcode.util.FileUtils;

import java.util.List;

public class Main {
    private static final String FILE_PATH = "E:\\13 - Projects\\Java\\untitled\\src\\adventofcode\\day4\\input.txt";

    public static void main(String[] args) {
        Bingo bingo = FileUtils.getBingo(FILE_PATH);
        List<Integer> numbersToCall = bingo.getNumbersToCall();
        List<Board> boards = bingo.getBoards();

        Board winningBoard = null;
        int wonNumberOfCall = 0;

        for (int i = 0; i < numbersToCall.size() && winningBoard == null; ++i) {
            final int numberToCall = numbersToCall.get(i);
            for (Board board : boards) {
                board.crossNumber(numberToCall);

                if (board.checkIfWon()) {
                    winningBoard = board;
                    wonNumberOfCall = numberToCall;
                    break;
                }
            }
        }

        System.out.println(winningBoard);
        System.out.println(winningBoard.countSumOfUncrossedNumbers() * wonNumberOfCall);
    }
}
