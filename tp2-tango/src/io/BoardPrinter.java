package io;

import model.*;

import java.util.List;

public class BoardPrinter {

    private static final String SUN   = "\u2600 ";
    private static final String MOON  = "\uD83C\uDF19";
    private static final String EMPTY = ". ";

    public static void printInitial(Board board) {
        System.out.println("=== Tabuleiro Inicial ===");
        print(board);
    }

    public static void printSolved(Board board) {
        System.out.println("=== Tabuleiro Resolvido ===");
        print(board);
    }

    private static void print(Board board) {
        int size = board.getSize();
        List<Constraint> constraints = board.getConstraints();
        String separator = buildSeparator(size);

        for (int r = 0; r < size; r++) {
            System.out.println(separator);
            StringBuilder row = new StringBuilder("|");
            for (int c = 0; c < size; c++) {
                Cell cell = board.getCell(r, c);
                String symbol = cellSymbol(cell);
                row.append(" ").append(symbol).append(" ");
                String hConstraint = getConstraintSymbol(constraints, r, c, r, c + 1);
                row.append(hConstraint).append("|");
            }
            System.out.println(row);

            if (r < size - 1) {
                StringBuilder vRow = new StringBuilder("|");
                for (int c = 0; c < size; c++) {
                    String vConstraint = getConstraintSymbol(constraints, r, c, r + 1, c);
                    vRow.append("  ").append(vConstraint).append("  |");
                }
                System.out.println(vRow);
            }
        }
        System.out.println(separator);
    }

    private static String cellSymbol(Cell cell) {
        switch (cell.getValue()) {
            case SUN:  return SUN;
            case MOON: return MOON;
            default:   return EMPTY;
        }
    }

    private static String getConstraintSymbol(List<Constraint> constraints,
                                               int r1, int c1, int r2, int c2) {
        for (Constraint ct : constraints) {
            if (ct.getRow1() == r1 && ct.getCol1() == c1
                    && ct.getRow2() == r2 && ct.getCol2() == c2) {
                return ct.getType() == ConstraintType.EQUAL ? "=" : "\u00D7";
            }
        }
        return " ";
    }

    private static String buildSeparator(int size) {
        StringBuilder sb = new StringBuilder("+");
        for (int i = 0; i < size; i++) sb.append("-----+");
        return sb.toString();
    }
}
