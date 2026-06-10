package rules;

import model.Board;

public interface Rule {
    boolean isValidPlacement(Board board, int row, int col);
    boolean isFullyValid(Board board);
}
