package com.tingshulien.converter;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

public class ApacheCSVParser implements CSVParser {

    @Override
    public CSVFile parse(String path) throws IOException {
        Reader in = new FileReader(path);

        Iterable<CSVRecord> records = CSVFormat.RFC4180
                .withTrim()
                .parse(in);

        ArrayList<ArrayList<CSVFile.Cell>> rows = new ArrayList<>();

        int rowIndex = 0;
        int columnIndex = 0;

        for (CSVRecord record : records) {

            ArrayList<CSVFile.Cell> row = new ArrayList<>();
            rows.add(row);

            for (String content : record) {
                row.add(CSVFile.Cell.of(rowIndex, columnIndex, content));
                columnIndex++;
            }

            rowIndex++;
        }

        return CSVFile.of(rows);
    }

}