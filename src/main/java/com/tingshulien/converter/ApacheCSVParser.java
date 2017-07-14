package com.tingshulien.converter;

import com.google.common.io.Files;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

public class ApacheCSVParser implements CSVParser {

    @Override
    public CSVFile parse(final String path) throws IOException {
        checkNotNull(path, "CSV file path must not be null");

        Reader in = Files.newReader(new File(path), StandardCharsets.UTF_8);

        Iterable<CSVRecord> records = CSVFormat.RFC4180
                .withTrim()
                .parse(in);

        List<List<Cell>> rows = new ArrayList<>();

        int rowIndex = 0;
        int columnIndex = 0;

        for (CSVRecord record : records) {
            List<Cell> row = new ArrayList<>();
            rows.add(row);

            for (String content : record) {
                row.add(Cell.of(rowIndex, columnIndex, content));
                columnIndex++;
            }

            columnIndex = 0;
            rowIndex++;
        }

        return CSVFile.of(path, rows);
    }

}
