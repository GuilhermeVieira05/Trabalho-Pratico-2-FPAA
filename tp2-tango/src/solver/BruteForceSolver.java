package solver;

import model.Board;
import model.CellValue;

import java.util.ArrayList;
import java.util.List;

public class BruteForceSolver {

    private final RuleValidator validator = new RuleValidator();
    private long combinationsTried;
    private long totalCombinations;

    public boolean solve(Board board) {
        combinationsTried = 0;

        List<int[]> emptyCells = collectEmptyCells(board);
        int n = emptyCells.size();
        totalCombinations = 1L << n;

        for (long mask = 0; mask < totalCombinations; mask++) {
            fill(board, emptyCells, mask);
            combinationsTried++;

            if (validator.isBoardValid(board)) {
                return true;
            }
        }

        clear(board, emptyCells);
        return false;
    }

    public long getCombinationsTried()  { return combinationsTried; }
    public long getTotalCombinations()  { return totalCombinations; }

    // -------------------------------------------------------------------------

    private List<int[]> collectEmptyCells(Board board) {
        List<int[]> list = new ArrayList<>();
        int size = board.getSize();
        for (int r = 0; r < size; r++)
            for (int c = 0; c < size; c++)
                if (board.getCell(r, c).isEmpty())
                    list.add(new int[]{r, c});
        return list;
    }

    private void fill(Board board, List<int[]> cells, long mask) {
        for (int i = 0; i < cells.size(); i++) {
            int[] pos = cells.get(i);
            CellValue val = ((mask >> i) & 1) == 0 ? CellValue.SUN : CellValue.MOON;
            board.setCell(pos[0], pos[1], val);
        }
    }

    private void clear(Board board, List<int[]> cells) {
        for (int[] pos : cells)
            board.setCell(pos[0], pos[1], CellValue.EMPTY);
    }
}
