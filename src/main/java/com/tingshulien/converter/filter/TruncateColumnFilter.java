package com.tingshulien.converter.filter;

import com.tingshulien.converter.Cell;

public class TruncateColumnFilter extends CsvCellFilter {

    private int truncateRowIndex = 0;
    private int truncateColumnIndex = 0;
    private boolean truncateExists;

    @Override
    public boolean examine(Cell cell) {
        boolean ok = false;

        String content = cell.getContent();

        if (truncateExists) {
            ok = cell.getRowIndex() > truncateRowIndex
                    && cell.getColumnIndex() == truncateColumnIndex;

            return ok;
        }

        if (content.toLowerCase().contains("truncate")) {
            truncateRowIndex = cell.getRowIndex();
            truncateColumnIndex = cell.getColumnIndex();
            truncateExists = true;
            ok = true;
        }

        return ok;
    }

}
