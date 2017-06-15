package com.tingshulien.converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TruncateColumnFilter extends CsvCellFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(TruncateColumnFilter.class);

    private int truncateRowIndex = 0;
    private int truncateColumnIndex = 0;
    private boolean truncateExists;

    @Override
    public boolean examine(Cell cell) {
        boolean ok = false;

        String content = cell.getContent();

        if (content.toLowerCase().contains("truncate")) {
            truncateRowIndex = cell.getRowIndex();
            truncateColumnIndex = cell.getColumnIndex();
            truncateExists = true;
            LOGGER.info("Truncate cell : " + cell);
        }

        if (truncateExists) {
            ok = cell.getRowIndex() >= truncateRowIndex
                    && cell.getColumnIndex() == truncateColumnIndex;
        }

        return ok;
    }

}
