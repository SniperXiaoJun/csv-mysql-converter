package com.tingshulien.converter;

public class OnlyCurlyBracketSingleQuotationCommaFilter extends CsvCellFilter {

    @Override
    public boolean examine(Cell cell) {
        return cell.getContent().replaceAll("[()\',]", "").trim().isEmpty();
    }

}
