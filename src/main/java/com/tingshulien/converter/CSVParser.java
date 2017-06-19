package com.tingshulien.converter;

import java.io.IOException;
import java.util.Optional;

public interface CSVParser {

    Optional<CSVFile> parse(String path) throws IOException;

}
