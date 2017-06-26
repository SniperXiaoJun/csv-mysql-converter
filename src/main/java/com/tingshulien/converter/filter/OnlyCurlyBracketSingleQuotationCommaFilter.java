package com.tingshulien.converter.filter;

import com.tingshulien.converter.Cell;

public class OnlyCurlyBracketSingleQuotationCommaFilter extends CsvCellFilter {

    @Override
    public boolean examine(Cell cell) {
        return cell.getContent().replaceAll("[()\',]", "").trim().isEmpty();
    }

}
