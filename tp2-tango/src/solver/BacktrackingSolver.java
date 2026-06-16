package solver;

import model.Board;
import model.CellValue;
import validation.RuleValidator;

public class BacktrackingSolver implements ISolver {

    private final RuleValidator validator = new RuleValidator();
    private int nodesExplored;
    private int backtrackCount;

    public boolean solve(Board board) {
        nodesExplored = 0;
        backtrackCount = 0;
        return backtrack(board);
    }

    @Override
    public String getName() {
        return "Backtracking";
    }

    @Override
    public String getStatistics(long tempoMs) {
        return "\n--- Estatisticas do Backtracking ---\n"
             + "Nos explorados : " + nodesExplored + "\n"
             + "Retrocessos    : " + backtrackCount + "\n"
             + "Tempo total    : " + tempoMs + " ms";
    }

    private boolean backtrack(Board board) {
        int[] next = findNextEmpty(board);
        if (next == null) {
            return validator.isBoardValid(board);
        }

        int row = next[0];
        int col = next[1];

        for (CellValue candidate : new CellValue[]{CellValue.SUN, CellValue.MOON}) {
            nodesExplored++;
            board.setCell(row, col, candidate);

            if (validator.isValidPlacement(board, row, col)) {
                if (backtrack(board)) {
                    return true;
                }
            }

            board.setCell(row, col, CellValue.EMPTY);
            backtrackCount++;
        }

        return false;
    }

    private int[] findNextEmpty(Board board) {
        int size = board.getSize();
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                if (board.getCell(r, c).isEmpty()) {
                    return new int[]{r, c};
                }
            }
        }
        return null;
    }
}
