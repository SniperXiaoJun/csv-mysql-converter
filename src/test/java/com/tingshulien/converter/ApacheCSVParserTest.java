package com.tingshulien.converter;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

import java.net.URL;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
public class ApacheCSVParserTest {

    private static final String CSV_FILE = "users.csv";

    private static URL url;
    private static ApacheCSVParser parser;

    @BeforeAll
    public static void beforeAll() {
        url = ApacheCSVParserTest.class.getClassLoader().getResource(CSV_FILE);
        parser = new ApacheCSVParser();
    }

    @Test
    public void givenUsersCsvThenParseReturnAllRows() throws Exception {
        CSVFile csvFile = parser.parse(url.getPath());

        assertThat(csvFile.print(), containsString("users.csv"));
    }

}