package com.tingshulien.converter;

import java.io.IOException;

public class TruncateCSVParser implements CSVParser {

    private ApacheCSVParser apacheCSVParser;
    private Anchor anchor;

    @Override
    public CSVFile parse(String path) throws IOException {
        CSVFile file = apacheCSVParser.parse(path);



        return null;
    }
}
