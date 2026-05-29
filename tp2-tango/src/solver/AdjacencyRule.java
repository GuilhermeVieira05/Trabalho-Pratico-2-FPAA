package solver;

import model.Board;
import model.CellValue;

public class AdjacencyRule implements Rule {

    @Override
    public boolean isValidPlacement(Board board, int row, int col) {
        return checkLine(extractRow(board, row))
            && checkLine(extractCol(board, col));
    }

    @Override
    public boolean isFullyValid(Board board) {
        int size = board.getSize();
        for (int i = 0; i < size; i++) {
            if (!checkLine(extractRow(board, i))) return false;
            if (!checkLine(extractCol(board, i))) return false;
        }
        return true;
    }

    private boolean checkLine(CellValue[] line) {
        int count = 1;
        for (int i = 1; i < line.length; i++) {
            if (line[i] != CellValue.EMPTY && line[i] == line[i - 1]) {
                count++;
                if (count > 2) return false;
            } else {
                count = 1;
            }
        }
        return true;
    }

    private CellValue[] extractRow(Board board, int row) {
        int size = board.getSize();
        CellValue[] line = new CellValue[size];
        for (int c = 0; c < size; c++)
            line[c] = board.getCell(row, c).getValue();
        return line;
    }

    private CellValue[] extractCol(Board board, int col) {
        int size = board.getSize();
        CellValue[] line = new CellValue[size];
        for (int r = 0; r < size; r++)
            line[r] = board.getCell(r, col).getValue();
        return line;
    }
}
