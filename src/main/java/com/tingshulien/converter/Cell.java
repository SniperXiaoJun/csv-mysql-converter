package com.tingshulien.converter;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor(staticName = "of")
public class Cell {

    final private int rowIndex;
    final private int columnIndex;
    final private String content;
    private boolean filtered;

}
