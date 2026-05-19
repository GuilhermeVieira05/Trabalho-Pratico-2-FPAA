package model;

public class Cell {
    private final int row;
    private final int col;
    private CellValue value;
    private final boolean fixed;

    public Cell(int row, int col, CellValue value) {
        this.row = row;
        this.col = col;
        this.value = value;
        this.fixed = value != CellValue.EMPTY;
    }

    public int getRow() { return row; }
    public int getCol() { return col; }
    public CellValue getValue() { return value; }
    public boolean isFixed() { return fixed; }
    public boolean isEmpty() { return value == CellValue.EMPTY; }

    public void setValue(CellValue value) {
        if (fixed) throw new IllegalStateException("Cannot modify a fixed cell.");
        this.value = value;
    }
}
