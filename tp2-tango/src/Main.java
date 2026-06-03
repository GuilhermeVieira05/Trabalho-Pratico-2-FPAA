import io.BoardReader;
import io.BoardPrinter;
import model.Board;
import solver.BacktrackingSolver;
import solver.BruteForceSolver;

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

        if (modo.equals("bruteforce") || modo.equals("ambos")) {
            System.out.println("\n========== FORCA BRUTA ==========");
            Board board = new BoardReader(path).read();
            BruteForceSolver bf = new BruteForceSolver();

            long inicio = System.currentTimeMillis();
            boolean resolvido = bf.solve(board);
            long tempo = System.currentTimeMillis() - inicio;

            if (resolvido) {
                BoardPrinter.printSolved(board);
                System.out.println("\n--- Estatisticas da Forca Bruta ---");
                System.out.println("Combinacoes testadas : " + bf.getCombinationsTried());
                System.out.println("Total possivel       : " + bf.getTotalCombinations() + " (2^" + log2(bf.getTotalCombinations()) + ")");
                System.out.println("Tempo total          : " + tempo + " ms");
            } else {
                System.out.println("Nenhuma solucao encontrada.");
            }
        }

        if (modo.equals("backtracking") || modo.equals("ambos")) {
            System.out.println("\n========== BACKTRACKING ==========");
            Board board = new BoardReader(path).read();
            BacktrackingSolver bt = new BacktrackingSolver();

            long inicio = System.currentTimeMillis();
            boolean resolvido = bt.solve(board);
            long tempo = System.currentTimeMillis() - inicio;

            if (resolvido) {
                BoardPrinter.printSolved(board);
                System.out.println("\n--- Estatisticas do Backtracking ---");
                System.out.println("Nos explorados : " + bt.getNodesExplored());
                System.out.println("Retrocessos    : " + bt.getBacktrackCount());
                System.out.println("Tempo total    : " + tempo + " ms");
            } else {
                System.out.println("Nenhuma solucao encontrada.");
            }
        }
    }

    private static int log2(long n) {
        return n <= 0 ? 0 : 63 - Long.numberOfLeadingZeros(n);
    }
}
