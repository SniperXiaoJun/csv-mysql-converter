package com.tingshulien.converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class Main {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);
    private static CSVParser parser = new ApacheCSVParser();

    public static void main(String[] args) throws IOException {
        String path = args[0];

        CSVFile file = parser.parse(path);



//        Reader in = new FileReader(path);
//
//        Iterable<CSVRecord> records = CSVFormat.RFC4180
//                .withTrim()
//                .parse(in);
//
//        ArrayList<ArrayList<String>> rows = new ArrayList<>();
//
//        for (CSVRecord record : records) {
//            ArrayList<String> row = new ArrayList<>();
//            rows.add(row);
//
//            for (String cell : record) {
//                row.add(cell);
//            }
//        }
//
//        RowCropper cropper = RowCropper.builder()
//                .columnCount(1)
//                .matcher(AnchorMatcher.TRUNCATE)
//                .rowFilter(RowFilter.EXCLUDE_ONLY_COMMA_BRACKET)
//                .mapper(RowMapper.DEFAULT)
//                .build();
//
//        ArrayList<ArrayList<String>> croppedRows = cropper.crop(rows);
//
//
//        StringBuilder sb = new StringBuilder();
//
//        croppedRows.forEach(row -> sb.append(row.get(0)).append("\n"));
//
//        LOGGER.info("Cropped Rows is " + sb.toString());
    }

}
