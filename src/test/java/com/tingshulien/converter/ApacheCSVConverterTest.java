package com.tingshulien.converter;

import com.tingshulien.converter.filter.BracketQuotationCommaSpaceOnlyFilter;
import com.tingshulien.converter.filter.EmptyCellFilter;
import com.tingshulien.converter.filter.TruncateColumnFilter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import java.net.URL;
import java.nio.file.Paths;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(JUnitPlatform.class)
public class ApacheCSVConverterTest {

    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String CSV_FILE = "users.csv";

    private static URL url;
    private static ApacheCSVConverter converter;

    @BeforeAll
    public static void beforeAll() {
        url = ApacheCSVParserTest.class.getClassLoader().getResource(CSV_FILE);
        converter = new ApacheCSVConverter(
            new ApacheCSVParser(),
            new TruncateColumnFilter()
                .andNot(new EmptyCellFilter()
                    .or(new BracketQuotationCommaSpaceOnlyFilter())),
            new LastCommaToSemicolonMapper()
        );
    }

    @Test
    public void givenUsersCsvWhenConvertThenReturnSql() throws Exception {
        assertThat(
                converter.convert(Paths.get(url.toURI()).toString()),
                containsString(
            "TRUNCATE  TABLE `users`;" + LINE_SEPARATOR
            + "INSERT INTO `users` (`id`, `username`, `create_date`) VALUES" + LINE_SEPARATOR
            + "(1,'alex','2017-01-01 00:00:00')," + LINE_SEPARATOR
            + "(2,'andy','2017-01-01 00:00:00')," + LINE_SEPARATOR
            + "(3,'tim','2017-01-01 00:00:00'),"  + LINE_SEPARATOR
            + "(4,'tom','2017-01-01 00:00:00'),"  + LINE_SEPARATOR
            + "(5,'zoe','2017-01-01 00:00:00');"));
    }

}