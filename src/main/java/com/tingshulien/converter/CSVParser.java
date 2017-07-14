package com.tingshulien.converter;

import java.io.IOException;

public interface CSVParser {

    CSVFile parse(String path) throws IOException;

}
