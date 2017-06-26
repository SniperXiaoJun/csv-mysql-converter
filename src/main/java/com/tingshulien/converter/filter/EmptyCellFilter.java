package com.tingshulien.converter.filter;

import com.tingshulien.converter.Cell;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmptyCellFilter extends CsvCellFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmptyCellFilter.class);

    @Override
    public boolean examine(Cell cell) {
        if (cell.getContent().trim().length() < 2) {
            LOGGER.info("Possible empty cell is " + cell + " content is [" + cell.getContent() + "]");
        }

        return cell.getContent().trim().isEmpty();
    }

}
