package com.tingshulien.converter.filter;

import com.tingshulien.converter.Cell;

import static com.google.common.base.Preconditions.checkNotNull;

public abstract class CsvCellFilter implements CellFilter {

    public abstract boolean examine(Cell cell);

    public CsvCellFilter or(CsvCellFilter other) {
        return new OrSpecification(this, other);
    }

    public CsvCellFilter andNot(CsvCellFilter other) {
        return new AndNotSpecification(this, other);
    }

    private class OrSpecification extends CsvCellFilter {

        final private CsvCellFilter left;
        final private CsvCellFilter right;

        OrSpecification(CsvCellFilter left, CsvCellFilter right) {
            this.left = checkNotNull(left);
            this.right = checkNotNull(right);
        }

        @Override
        public boolean examine(Cell cell){
            cell = checkNotNull(cell, "Cell must not be null");
            return left.examine(cell) || right.examine(cell);
        }

    }

    private class AndNotSpecification extends CsvCellFilter {

        final private CsvCellFilter left;
        final private CsvCellFilter right;

        AndNotSpecification(CsvCellFilter left, CsvCellFilter right) {
            this.left = checkNotNull(left);
            this.right = checkNotNull(right);
        }

        @Override
        public boolean examine(Cell cell) {
            cell = checkNotNull(cell, "Cell must not be null");
            return left.examine(cell) && ! right.examine(cell);
        }

    }

}
