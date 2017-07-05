package com.tingshulien.converter;

import static com.google.common.base.Preconditions.checkNotNull;

import com.google.common.io.Files;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Optional;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

public class ApacheCSVParser implements CSVParser {

    @Override
    public Optional<CSVFile> parse(final String path) throws IOException {
        checkNotNull(path, "CSV file path must not be null");

        Reader in = Files.newReader(new File(path), StandardCharsets.UTF_8);

        Iterable<CSVRecord> records = CSVFormat.RFC4180
                .withTrim()
                .parse(in);

        ArrayList<ArrayList<Cell>> rows = new ArrayList<>();

        int rowIndex = 0;
        int columnIndex = 0;

        for (CSVRecord record : records) {

            ArrayList<Cell> row = new ArrayList<>();
            rows.add(row);

            for (String content : record) {
                row.add(Cell.of(rowIndex, columnIndex, content));
                columnIndex++;
            }

            columnIndex = 0;
            rowIndex++;
        }

        return Optional.ofNullable(CSVFile.of(path, rows));
    }

}
