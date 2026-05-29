package solver;

import model.Board;
import model.CellValue;

public class BalanceRule implements Rule {

    @Override
    public boolean isValidPlacement(Board board, int row, int col) {
        int size = board.getSize();
        return checkLineBalance(board, size, true, row, false)
            && checkLineBalance(board, size, false, col, false);
    }

    @Override
    public boolean isFullyValid(Board board) {
        int size = board.getSize();
        for (int i = 0; i < size; i++) {
            if (!checkLineBalance(board, size, true, i, true))  return false;
            if (!checkLineBalance(board, size, false, i, true)) return false;
        }
        return true;
    }

    private boolean checkLineBalance(Board board, int size, boolean isRow, int idx, boolean exact) {
        int suns = 0, moons = 0;
        for (int i = 0; i < size; i++) {
            CellValue v = isRow ? board.getCell(idx, i).getValue()
                                : board.getCell(i, idx).getValue();
            if (v == CellValue.SUN)  suns++;
            if (v == CellValue.MOON) moons++;
        }
        if (exact) return suns == size / 2 && moons == size / 2;
        return suns <= size / 2 && moons <= size / 2;
    }
}
