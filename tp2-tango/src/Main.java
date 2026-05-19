import io.BoardReader;
import io.BoardPrinter;
import model.Board;

public class Main {
    public static void main(String[] args) throws Exception {
        if (args.length < 1) {
            System.err.println("Uso: java Main <caminho-do-puzzle>");
            System.exit(1);
        }

        Board board = new BoardReader(args[0]).read();
        BoardPrinter.printInitial(board);
    }
}
