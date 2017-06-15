package com.tingshulien.converter;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@Data
public class CSVConverter {

    private static final Logger LOGGER = LoggerFactory.getLogger(CSVConverter.class);

    final private CSVParser parser;
    final private CsvCellFilter filter;

    String convert(String path) {
        CSVFile file = null;

        try {
            file = parser.parse(path);
        } catch (IOException e) {
            LOGGER.error("Parse CSV file error : ", e);
        }

        file.filter(filter.and(new NotEmptyCellFilter()));

        LOGGER.info("File data : " + file);

        return file.print();
    }

}
