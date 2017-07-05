package com.tingshulien.converter;

import com.tingshulien.converter.filter.BracketQuotationCommaSpaceOnlyFilter;
import com.tingshulien.converter.filter.EmptyCellFilter;
import com.tingshulien.converter.filter.TruncateColumnFilter;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    private static CSVConverter converter = new CSVConverter(new ApacheCSVParser(), new TruncateColumnFilter().andNot(new EmptyCellFilter().or(new BracketQuotationCommaSpaceOnlyFilter())));

    public static void main(String[] args) throws IOException {
        String path = args[0];
        
        try {
            String fileContent = converter.convert(path);

            LOGGER.info("Last word is " + fileContent.substring(fileContent.length()));

            if (fileContent.trim().endsWith(",")) {
                fileContent = fileContent.replaceAll("[,]$", ";");
            }

            LOGGER.info("File content : " + "[" + fileContent + "]");

            System.out.println(fileContent);
            System.exit(0);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            System.exit(1);
        }
    }

}
