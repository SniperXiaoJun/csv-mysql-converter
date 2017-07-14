package com.tingshulien.converter;

import com.tingshulien.converter.filter.CsvCellFilter;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.google.common.base.Preconditions.checkNotNull;

@Data
class ApacheCSVConverter implements CSVConverter {

    private static final Logger LOG = LoggerFactory.getLogger(ApacheCSVConverter.class);

    final private CSVParser parser;
    final private CsvCellFilter filter;
    final private Mapper mapper;

    public String convert(String path) {
        checkNotNull(path, "CSV file path must not be null");

        CSVFile csvFile = null;

        try {
            csvFile = parser.parse(path);
        } catch (Exception ex) {
            LOG.error("Parse CSV file error : ", ex);
        }

        checkNotNull(csvFile, "CSV file optional must not be null");

        csvFile.filter(filter);

        LOG.info("File data : " + csvFile);

        return mapper.map(csvFile.print());
    }

}
