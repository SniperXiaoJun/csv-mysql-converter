package com.tingshulien.converter;

import static com.google.common.base.Preconditions.checkArgument;

import lombok.Data;

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
