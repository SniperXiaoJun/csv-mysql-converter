package com.tingshulien.converter;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;

@Data
@RequiredArgsConstructor(staticName = "of")
public class CSVFile {

    final private ArrayList<ArrayList<Cell>> rows;
    private Anchor anchor;

    @Data
    @RequiredArgsConstructor(staticName = "of")
    public class Cell {

        final private int rowIndex;
        final private int columnIndex;
        final private String content;
        private boolean filtered;

    }

}
