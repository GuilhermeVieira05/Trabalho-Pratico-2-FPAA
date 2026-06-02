package solver;

import model.Board;
import model.CellValue;

public class BacktrackingSolver {

    private final RuleValidator validator = new RuleValidator();
    private int nodesExplored;
    private int backtrackCount;

    /**
     * Ponto de entrada: reinicia as estatísticas e dispara a recursão.
     * Retorna true se uma solução foi encontrada; o tabuleiro fica resolvido in-place.
     */
    public boolean solve(Board board) {
        nodesExplored = 0;
        backtrackCount = 0;
        return backtrack(board);
    }

    public int getNodesExplored()  { return nodesExplored; }
    public int getBacktrackCount() { return backtrackCount; }

    // -------------------------------------------------------------------------
    // Recursão principal
    // -------------------------------------------------------------------------

    private boolean backtrack(Board board) {
        // Condição de parada: sem células vazias → verifica solução completa
        int[] next = findNextEmpty(board);
        if (next == null) {
            return validator.isBoardValid(board);
        }

        int row = next[0];
        int col = next[1];

        // Tenta SUN primeiro, depois MOON
        for (CellValue candidate : new CellValue[]{CellValue.SUN, CellValue.MOON}) {
            nodesExplored++;
            board.setCell(row, col, candidate);

            // Poda: valida as regras parciais antes de aprofundar a recursão
            if (validator.isValidPlacement(board, row, col)) {
                if (backtrack(board)) {
                    return true; // propagando a solução encontrada
                }
            }

            // Retrocesso: desfaz a atribuição e contabiliza
            board.setCell(row, col, CellValue.EMPTY);
            backtrackCount++;
        }

        // Nenhum candidato viável neste ramo → sinaliza fracasso ao chamador
        return false;
    }

    // -------------------------------------------------------------------------
    // Seleção da variável: primeira célula vazia em ordem de leitura
    // -------------------------------------------------------------------------

    private int[] findNextEmpty(Board board) {
        int size = board.getSize();
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                if (board.getCell(r, c).isEmpty()) {
                    return new int[]{r, c};
                }
            }
        }
        return null; // tabuleiro completamente preenchido
    }
}
