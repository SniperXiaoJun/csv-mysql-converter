package com.tingshulien.converter;

import static com.google.common.base.Preconditions.checkNotNull;

import com.tingshulien.converter.filter.CsvCellFilter;
import java.io.IOException;
import java.util.Optional;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Data
class CSVConverter {

    private static final Logger LOG = LoggerFactory.getLogger(CSVConverter.class);

    final private CSVParser parser;
    final private CsvCellFilter filter;

    String convert(String path) {
        checkNotNull(path, "CSV file path must not be null");

        Optional<CSVFile> optFile = null;

        try {
            optFile = parser.parse(path);
        } catch (IOException e) {
            LOG.error("Parse CSV file error : ", e);
        }

        checkNotNull(optFile, "CSV file optional must not be null");

        if (! optFile.isPresent()) {
            throw new NullPointerException("CSV file optional must not be empty");
        }

        CSVFile file = optFile.get();

        file.filter(filter);

        LOG.info("File data : " + file);

        return file.print();
    }

}
