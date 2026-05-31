package solver;

import model.Board;
import model.Constraint;

public class ConstraintRule implements Rule {

    @Override
    public boolean isValidPlacement(Board board, int row, int col) {
        return board.getConstraints().stream()
            .filter(c -> (c.getRow1() == row && c.getCol1() == col)
                      || (c.getRow2() == row && c.getCol2() == col))
            .allMatch(c -> c.isSatisfied(
                board.getCell(c.getRow1(), c.getCol1()),
                board.getCell(c.getRow2(), c.getCol2())));
    }

    @Override
    public boolean isFullyValid(Board board) {
        return board.getConstraints().stream()
            .allMatch(c -> c.isSatisfied(
                board.getCell(c.getRow1(), c.getCol1()),
                board.getCell(c.getRow2(), c.getCol2())));
    }
}
