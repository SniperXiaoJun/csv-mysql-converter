package com.tingshulien.converter;

import com.tingshulien.converter.filter.CellFilter;
import lombok.Data;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

@Data
class CSVFile {

    private static String separator = System.getProperty("line.separator");

    private Path path;
    private ArrayList<ArrayList<Cell>> rows;
    private int rowCount;
    private int columnCount;

    void filter(CellFilter cellFilter) {
        for (ArrayList<Cell> row : rows) {
            for (Cell cell : row) {
                if (cellFilter.examine(cell)) {
                    cell.setFiltered(true);
                }
            }
        }
    }

    String print() {
        StringBuilder sb = new StringBuilder();
        boolean validRow;

        sb.append("# ===================================\n")
                .append("# ").append(path.getFileName()).append("\n")
                .append("# ===================================\n\n");

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

    static CSVFile of(String path, ArrayList<ArrayList<Cell>> rows) {
        CSVFile file = new CSVFile();

        file.path = Paths.get(path);
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
