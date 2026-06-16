import io.BoardReader;
import io.BoardPrinter;
import model.Board;
import solver.ISolver;
import solver.SolverFactory;

import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        if (args.length < 1) {
            System.err.println("Uso: java Main <caminho-do-puzzle> [backtracking|bruteforce|ambos]");
            System.exit(1);
        }

        String path = args[0];
        String modo = args.length >= 2 ? args[1].toLowerCase() : "ambos";

        Board initial = new BoardReader(path).read();
        BoardPrinter.printInitial(initial);

        List<ISolver> solvers = SolverFactory.create(modo);

        for (ISolver solver : solvers) {
            System.out.println("\n========== " + solver.getName().toUpperCase() + " ==========");
            Board board = new BoardReader(path).read();

            long inicio = System.currentTimeMillis();
            boolean resolvido = solver.solve(board);
            long tempo = System.currentTimeMillis() - inicio;

            if (resolvido) {
                BoardPrinter.printSolved(board);
                System.out.println(solver.getStatistics(tempo));
            } else {
                System.out.println("Nenhuma solucao encontrada.");
            }
        }
    }
}
