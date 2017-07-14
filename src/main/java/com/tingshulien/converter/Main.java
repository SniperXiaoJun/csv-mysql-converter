package com.tingshulien.converter;

import com.tingshulien.converter.filter.BracketQuotationCommaSpaceOnlyFilter;
import com.tingshulien.converter.filter.EmptyCellFilter;
import com.tingshulien.converter.filter.TruncateColumnFilter;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    private static final Logger LOG = LoggerFactory.getLogger(Main.class);

    private static ApacheCSVConverter converter = new ApacheCSVConverter(
        new ApacheCSVParser(),
        new TruncateColumnFilter()
            .andNot(new EmptyCellFilter().or(new BracketQuotationCommaSpaceOnlyFilter())),
        new LastCommaToSemicolonMapper()
    );

    public static void main(String[] args) throws IOException {
        String path = args[0];
        
        try {
            String sql = converter.convert(path);

            System.out.print(sql);
            System.exit(0);

            LOG.debug("Successfully convert " + path + " to sql query as below\n" + sql);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            System.exit(1);

            LOG.error("Failed to convert " + path + " to sql query", ex);
        }
    }

}
