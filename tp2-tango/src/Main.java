import io.BoardReader;
import io.BoardPrinter;
import model.Board;
import solver.BacktrackingSolver;

public class Main {
    public static void main(String[] args) throws Exception {
        if (args.length < 1) {
            System.err.println("Uso: java Main <caminho-do-puzzle>");
            System.exit(1);
        }

        Board board = new BoardReader(args[0]).read();
        BoardPrinter.printInitial(board);

        BacktrackingSolver solver = new BacktrackingSolver();

        long inicio = System.currentTimeMillis();
        boolean resolvido = solver.solve(board);
        long tempo = System.currentTimeMillis() - inicio;

        if (resolvido) {
            System.out.println();
            BoardPrinter.printSolved(board);
            System.out.println("\n--- Estatisticas do Backtracking ---");
            System.out.println("Nos explorados : " + solver.getNodesExplored());
            System.out.println("Retrocessos    : " + solver.getBacktrackCount());
            System.out.println("Tempo total    : " + tempo + " ms");
        } else {
            System.out.println("\nNenhuma solucao encontrada para este tabuleiro.");
        }
    }
}
