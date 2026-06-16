package solver;

import model.Board;

/**
 * Contrato comum para os algoritmos de resolucao do Tango.
 */
public interface ISolver {

    /** Tenta resolver o tabuleiro in-place. Retorna true se encontrou solucao. */
    boolean solve(Board board);

    /** Nome do solver, usado em cabecalhos (ex: "Forca Bruta"). */
    String getName();

    /**
     * Estatisticas da ultima execucao, ja formatadas para impressao,
     * incluindo o tempo total decorrido.
     *
     * @param tempoMs tempo de execucao do solve em milissegundos
     */
    String getStatistics(long tempoMs);
}
