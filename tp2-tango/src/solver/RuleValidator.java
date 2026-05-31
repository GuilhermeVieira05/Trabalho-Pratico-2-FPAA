package solver;

import model.Board;
import java.util.List;

public class RuleValidator {

    private final List<Rule> rules = List.of(
        new AdjacencyRule(),
        new BalanceRule(),
        new ConstraintRule()
    );

    public boolean isValidPlacement(Board board, int row, int col) {
        return rules.stream().allMatch(r -> r.isValidPlacement(board, row, col));
    }

    public boolean isBoardValid(Board board) {
        int size = board.getSize();
        for (int r = 0; r < size; r++)
            for (int c = 0; c < size; c++)
                if (board.getCell(r, c).isEmpty()) return false;
        return rules.stream().allMatch(r -> r.isFullyValid(board));
    }
}
