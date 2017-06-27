package com.tingshulien.converter.filter;

import com.tingshulien.converter.Cell;

public class BracketQuotationCommaSpaceOnlyFilter extends CsvCellFilter {

    @Override
    public boolean examine(Cell cell) {
        return ! cell.getContent().isEmpty()
                && cell.getContent().replaceAll("[(),`\']", "").trim().isEmpty();
    }

}
