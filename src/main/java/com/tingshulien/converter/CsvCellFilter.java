package com.tingshulien.converter;

import lombok.Data;
import lombok.EqualsAndHashCode;

public abstract class CsvCellFilter implements CellFilter {

    public abstract boolean examine(Cell cell);

    public CsvCellFilter and(CsvCellFilter other) {
        return new AndSpecification(this, other);
    }

    public CsvCellFilter andNot(CsvCellFilter other) {
        return new AndNotSpecification(this, other);
    }

    @Data
    @EqualsAndHashCode(callSuper=false)
    private class AndSpecification extends CsvCellFilter {

        final private CsvCellFilter left;
        final private CsvCellFilter right;

        @Override
        public boolean examine(Cell cell){
            return left.examine(cell) && right.examine(cell);
        }

    }

    @Data
    @EqualsAndHashCode(callSuper=false)
    private class  AndNotSpecification extends CsvCellFilter {

        final private CsvCellFilter left;
        final private CsvCellFilter right;

        @Override
        public boolean examine(Cell cell){
            return left.examine(cell) && ! right.examine(cell);
        }

    }

}
