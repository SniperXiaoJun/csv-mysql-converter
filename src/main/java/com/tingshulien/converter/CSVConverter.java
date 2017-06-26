package com.tingshulien.converter;

import com.tingshulien.converter.filter.CsvCellFilter;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkNotNull;

@Data
class CSVConverter {

    private static final Logger LOGGER = LoggerFactory.getLogger(CSVConverter.class);

    final private CSVParser parser;
    final private CsvCellFilter filter;

    String convert(String path) {
        checkNotNull(path, "CSV file path must not be null");

        Optional<CSVFile> optFile = null;

        try {
            optFile = parser.parse(path);
        } catch (IOException e) {
            LOGGER.error("Parse CSV file error : ", e);
        }

        checkNotNull(optFile, "CSV file optional must not be null");

        if (! optFile.isPresent()) {
            throw new NullPointerException("CSV file optional must not be empty");
        }

        CSVFile file = optFile.get();

        file.filter(filter);

        LOGGER.info("File data : " + file);

        return file.print();
    }

}
