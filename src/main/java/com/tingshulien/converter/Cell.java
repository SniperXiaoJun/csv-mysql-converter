package com.tingshulien.converter;

import lombok.Data;

import static com.google.common.base.Preconditions.checkArgument;

@Data
public class Cell {

    final private int rowIndex;
    final private int columnIndex;
    final private String content;
    private boolean filtered;

    public static Cell of(final int rowIndex, final int columnIndex, final String content) {
        checkArgument(rowIndex >= 0);
        checkArgument(columnIndex >= 0);

        return new Cell(rowIndex, columnIndex, content);
    }

}
