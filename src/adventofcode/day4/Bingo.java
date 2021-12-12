package adventofcode.day4;

import java.util.List;

public class Bingo {
    private final List<Integer> numbersToCall;
    private final List<Board> boards;

    public Bingo(List<Integer> numbersToCall, List<Board> boards) {
        this.numbersToCall = numbersToCall;
        this.boards = boards;
    }

    public List<Integer> getNumbersToCall() {
        return numbersToCall;
    }

    public List<Board> getBoards() {
        return boards;
    }

    @Override
    public String toString() {
        return "BingoDto{" +
                "numbersToCall=" + numbersToCall +
                ", boards=" + boards +
                '}';
    }
}
