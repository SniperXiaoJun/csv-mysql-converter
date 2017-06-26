package com.tingshulien.converter.filter;

import com.tingshulien.converter.Cell;
import lombok.Data;
import lombok.EqualsAndHashCode;

import static com.google.common.base.Preconditions.checkNotNull;

public abstract class CsvCellFilter implements CellFilter {

    public abstract boolean examine(Cell cell);

    public CsvCellFilter and(CsvCellFilter other) {
        checkNotNull(other, "Cell filter must not be null");
        return new AndSpecification(this, other);
    }

    public CsvCellFilter andNot(CsvCellFilter other) {
        checkNotNull(other, "Cell filter must not be null");
        return new AndNotSpecification(this, other);
    }

    @Data
    @EqualsAndHashCode(callSuper=false)
    private class AndSpecification extends CsvCellFilter {

        final private CsvCellFilter left;
        final private CsvCellFilter right;

        @Override
        public boolean examine(Cell cell){
            checkNotNull(cell, "Cell must not be null");
            return left.examine(cell) && right.examine(cell);
        }

    }

    @Data
    @EqualsAndHashCode(callSuper=false)
    private class  AndNotSpecification extends CsvCellFilter {

        final private CsvCellFilter left;
        final private CsvCellFilter right;

        @Override
        public boolean examine(Cell cell) {
            checkNotNull(cell, "Cell must not be null");
            return left.examine(cell) && ! right.examine(cell);
        }

    }

}
