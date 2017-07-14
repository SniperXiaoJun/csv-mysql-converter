package com.tingshulien.converter;

import com.tingshulien.converter.filter.CellFilter;
import lombok.Data;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Data
class CSVFile {

    private static final String LINE_SEPARATOR = System.lineSeparator();

    private Path path;
    private List<List<Cell>> rows;
    private int rowCount;
    private int columnCount;

    void filter(CellFilter cellFilter) {
        for (List<Cell> row : rows) {
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

        for (List<Cell> row : rows) {

            for (Cell cell : row) {
                if (cell.isFiltered()) {
                    sb.append(cell.getContent());
                }
            }

            validRow = row.stream().anyMatch(Cell::isFiltered);

            if (validRow) {
                sb.append(LINE_SEPARATOR);
            }
        }

        return sb.toString();
    }

    static CSVFile of(String path, List<List<Cell>> rows) {
        CSVFile file = new CSVFile();

        file.path = Paths.get(path);
        file.rows = rows;
        file.rowCount = rows.size();
        file.columnCount = rows
                .stream()
                .mapToInt(List::size)
                .max()
                .orElse(0);

        return file;
    }

}
