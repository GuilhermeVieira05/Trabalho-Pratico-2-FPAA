package solver;

import java.util.List;

/**
 * Cria os solvers a serem executados a partir do modo informado.
 * Centraliza a escolha do algoritmo, evitando if/else espalhado no Main.
 */
public final class SolverFactory {

    private SolverFactory() {}

    /**
     * Retorna os solvers correspondentes ao modo.
     *
     * @param modo "bruteforce", "backtracking" ou "ambos"
     * @return lista com 1 solver (modos especificos) ou 2 (modo "ambos")
     * @throws IllegalArgumentException se o modo for desconhecido
     */
    public static List<ISolver> create(String modo) {
        switch (modo) {
            case "bruteforce":
                return List.of(new BruteForceSolver());
            case "backtracking":
                return List.of(new BacktrackingSolver());
            case "ambos":
                return List.of(new BruteForceSolver(), new BacktrackingSolver());
            default:
                throw new IllegalArgumentException("Modo invalido: " + modo
                        + " (use backtracking, bruteforce ou ambos)");
        }
    }
}
