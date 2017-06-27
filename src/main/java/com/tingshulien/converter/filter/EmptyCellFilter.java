package com.tingshulien.converter.filter;

import com.tingshulien.converter.Cell;

public class EmptyCellFilter extends CsvCellFilter {

    @Override
    public boolean examine(Cell cell) {
        return cell.getContent().trim().isEmpty();
    }

}
