package io;

import model.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BoardReader {
    private final String filePath;

    public BoardReader(String filePath) {
        this.filePath = filePath;
    }

    public Board read() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            int size = Integer.parseInt(br.readLine().trim());
            Board board = new Board(size);

            for (int r = 0; r < size; r++) {
                String[] tokens = br.readLine().trim().split("\\s+");
                for (int c = 0; c < size; c++) {
                    CellValue val = parseCell(tokens[c]);
                    board.initCell(r, c, val);
                }
            }

            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;
                String[] parts = line.split("\\s+");
                String dir = parts[0];
                int row = Integer.parseInt(parts[1]);
                int col = Integer.parseInt(parts[2]);
                ConstraintType type = parts[3].equals("=") ? ConstraintType.EQUAL : ConstraintType.OPPOSITE;

                int row2 = dir.equals("H") ? row : row + 1;
                int col2 = dir.equals("H") ? col + 1 : col;
                board.addConstraint(new Constraint(row, col, row2, col2, type));
            }

            return board;
        }
    }

    private CellValue parseCell(String token) {
        switch (token.toUpperCase()) {
            case "S": return CellValue.SUN;
            case "L": return CellValue.MOON;
            default:  return CellValue.EMPTY;
        }
    }
}
