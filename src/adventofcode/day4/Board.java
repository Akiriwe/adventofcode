package adventofcode.day4;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private final List<List<Integer>> boardMatrix;
    private final List<List<Boolean>> crossedNumbers;
    private boolean won = false;

    public Board(List<List<Integer>> boardMatrix) {
        this.boardMatrix = boardMatrix;
        crossedNumbers = new ArrayList<>(boardMatrix.size());
        for (int i = 0; i < boardMatrix.size(); ++i) {
            crossedNumbers.add(new ArrayList<>());
            for (int j = 0; j < boardMatrix.get(i).size(); ++j) {
                crossedNumbers.get(i).add(false);
            }

        }
    }

    public List<List<Integer>> getBoardMatrix() {
        return boardMatrix;
    }

    public void crossNumber(int number) {
        for (int i = 0; i < boardMatrix.size(); ++i) {
            for (int j = 0; j < boardMatrix.get(i).size(); ++j) {
                if (boardMatrix.get(i).get(j).equals(number)) {
                    crossedNumbers.get(i).set(j, true);
                }
            }
        }
    }

    public boolean checkIfWon() {
        if (!this.won) {
            for (List<Boolean> crossedNumber : crossedNumbers) {
                won = !crossedNumber.contains(false);
                if (won) {
                    break;
                }
            }

            if (!won) {
                for (int i = 0, k = 0; k < crossedNumbers.size() && i < crossedNumbers.get(k).size(); ++i, ++k) {
                    boolean winningColumn = true;
                    for (int j = 0; j < 5; ++j) {
                        if (Boolean.FALSE.equals(crossedNumbers.get(j).get(i))) {
                            winningColumn = false;
                            break;
                        }
                    }
                    if (winningColumn) {
                        won = true;
                        break;
                    }
                }
            }
        }

        return won;
    }

    public int countSumOfUncrossedNumbers() {
        int sum = 0;

        for (int i = 0; i < boardMatrix.size(); ++i) {
            for (int j = 0; j < boardMatrix.get(i).size(); ++j) {
                if (crossedNumbers.get(i).get(j).equals(false)) {
                    sum += boardMatrix.get(i).get(j);
                }
            }
        }

        return sum;
    }

    @Override
    public String toString() {
        return "Board{" +
                "boardMatrix=" + boardMatrix +
                ", crossedNumbers=" + crossedNumbers +
                ", won=" + won +
                '}';
    }
}
