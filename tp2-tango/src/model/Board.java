package model;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private final int size;
    private final Cell[][] grid;
    private final List<Constraint> constraints;

    public Board(int size) {
        this.size = size;
        this.grid = new Cell[size][size];
        this.constraints = new ArrayList<>();
        for (int r = 0; r < size; r++)
            for (int c = 0; c < size; c++)
                grid[r][c] = new Cell(r, c, CellValue.EMPTY);
    }

    public int getSize() { return size; }

    public Cell getCell(int row, int col) { return grid[row][col]; }

    public void setCell(int row, int col, CellValue value) {
        grid[row][col].setValue(value);
    }

    public void initCell(int row, int col, CellValue value) {
        grid[row][col] = new Cell(row, col, value);
    }

    public List<Constraint> getConstraints() { return constraints; }

    public void addConstraint(Constraint constraint) {
        constraints.add(constraint);
    }
}
