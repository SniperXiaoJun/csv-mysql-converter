package com.tingshulien.converter;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

import com.tingshulien.converter.filter.BracketQuotationCommaSpaceOnlyFilter;
import com.tingshulien.converter.filter.EmptyCellFilter;
import com.tingshulien.converter.filter.TruncateColumnFilter;
import java.net.URL;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
public class ApacheCSVConverterTest {

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
    public void givenUsersCsvWhenConvertThenReturnSql() {
        assertThat(converter.convert(url.getPath()), containsString(
            "TRUNCATE  TABLE `users`;\n"
            + "INSERT INTO `users` (`id`, `username`, `create_date`) VALUES\n"
            + "(1,'alex','2017-01-01 00:00:00'),\n"
            + "(2,'andy','2017-01-01 00:00:00'),\n"
            + "(3,'tim','2017-01-01 00:00:00'),\n"
            + "(4,'tom','2017-01-01 00:00:00'),\n"
            + "(5,'zoe','2017-01-01 00:00:00');"));
    }

}