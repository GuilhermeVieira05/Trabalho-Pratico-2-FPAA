package io;

import model.*;

import java.util.List;

public class BoardPrinter {

    private static final String SUN   = "S";
    private static final String MOON  = "L";
    private static final String EMPTY = ".";

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

        for (int r = 0; r < size; r++) {
            System.out.println(buildSeparator(size, r == 0 ? null : constraints, r - 1));

            StringBuilder row = new StringBuilder("|");
            for (int c = 0; c < size; c++) {
                String symbol = cellSymbol(board.getCell(r, c));
                row.append("  ").append(symbol).append("  ");
                if (c < size - 1) {
                    String hc = getConstraintSymbol(constraints, r, c, r, c + 1);
                    row.append(hc.equals(" ") ? "|" : hc);
                } else {
                    row.append("|");
                }
            }
            System.out.println(row);
        }
        System.out.println(buildSeparator(size, null, -1));
    }

    private static String buildSeparator(int size, List<Constraint> constraints, int aboveRow) {
        StringBuilder sb = new StringBuilder("+");
        for (int c = 0; c < size; c++) {
            String vc = (constraints != null)
                    ? getConstraintSymbol(constraints, aboveRow, c, aboveRow + 1, c)
                    : " ";
            if (vc.equals(" ")) {
                sb.append("-----+");
            } else {
                sb.append("--").append(vc).append("--+");
            }
        }
        return sb.toString();
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
                return ct.getType() == ConstraintType.EQUAL ? "=" : "x";
            }
        }
        return " ";
    }

}
