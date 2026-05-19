package model;

public class Constraint {
    private final int row1, col1, row2, col2;
    private final ConstraintType type;

    public Constraint(int row1, int col1, int row2, int col2, ConstraintType type) {
        this.row1 = row1;
        this.col1 = col1;
        this.row2 = row2;
        this.col2 = col2;
        this.type = type;
    }

    public int getRow1() { return row1; }
    public int getCol1() { return col1; }
    public int getRow2() { return row2; }
    public int getCol2() { return col2; }
    public ConstraintType getType() { return type; }

    public boolean isSatisfied(Cell a, Cell b) {
        if (a.isEmpty() || b.isEmpty()) return true;
        if (type == ConstraintType.EQUAL) {
            return a.getValue() == b.getValue();
        } else {
            return a.getValue() != b.getValue();
        }
    }
}
