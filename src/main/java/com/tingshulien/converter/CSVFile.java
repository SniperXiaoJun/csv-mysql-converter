package com.tingshulien.converter;

import lombok.Data;

import java.util.ArrayList;

@Data
public class CSVFile {

    private static String separator = System.getProperty("line.separator");

    private ArrayList<ArrayList<Cell>> rows;
    private int rowCount;
    private int columnCount;

    public void filter(CellFilter cellFilter) {
        for (ArrayList<Cell> row : rows) {
            for (Cell cell : row) {
                if (cellFilter.examine(cell)) {
                    cell.setFiltered(true);
                }
            }
        }
    }

    public String print() {
        StringBuilder sb = new StringBuilder();
        boolean validRow;

        for (ArrayList<Cell> row : rows) {

            for (Cell cell : row) {
                if (cell.isFiltered()) {
                    sb.append(cell.getContent());
                }
            }

            validRow = row.stream().anyMatch(Cell::isFiltered);

            if (validRow) {
                sb.append(separator);
            }
        }

        return sb.toString();
    }

    public static CSVFile of (ArrayList<ArrayList<Cell>> rows) {
        CSVFile file = new CSVFile();

        file.rows = rows;
        file.rowCount = rows.size();
        file.columnCount = rows
                .stream()
                .mapToInt(ArrayList::size)
                .max()
                .orElse(0);

        return file;
    }

}
