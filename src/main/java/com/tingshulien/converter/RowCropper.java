package com.tingshulien.converter;

import lombok.Builder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Optional;

@Builder
public class RowCropper {

    private static final Logger LOGGER = LoggerFactory.getLogger(RowCropper.class);

    private AnchorMatcher matcher;
    private int columnCount;
    private RowFilter rowFilter;
    private RowMapper mapper;

    ArrayList<ArrayList<String>> crop(ArrayList<ArrayList<String>> rows) {
        Optional<Anchor> anchorOptional = getAnchor(rows);

        if (! anchorOptional.isPresent()) {
            throw new NullPointerException("No matched anchor found in the csv");
        }

        Anchor anchor = anchorOptional.get();

        LOGGER.info("Anchor is " + anchor);

        ArrayList<ArrayList<String>> slimRows = slim(rows, anchor.getRowIndex(), anchor.getColumnIndex());

        return mapper.map(slimRows);
    }

    private ArrayList<ArrayList<String>> slim(ArrayList<ArrayList<String>> rows, int anchorRowIndex, int anchorColumnIndex) {
        ArrayList<ArrayList<String>> slimRows = new ArrayList<>();

        int rowIndex = 0;
        int columnIndex = 0;

        for (ArrayList<String> row : rows) {

            if (rowIndex >= anchorRowIndex) {

                ArrayList<String> newRow = new ArrayList<>();
                slimRows.add(newRow);

                for (String cell : row) {
                    if (columnIndex >= anchorColumnIndex && columnIndex < anchorColumnIndex + columnCount) {
                        newRow.add(cell);
                    }

                    columnIndex++;
                }

            }

            rowIndex++;
            columnIndex = 0;
        }

        return slimRows;
    }

    private Optional<Anchor> getAnchor(ArrayList<ArrayList<String>> rows) {
        int rowIndex = 0;
        int columnIndex = 0;

        Anchor anchor = null;

        for (ArrayList<String> row : rows) {
            for (String cell : row) {
                if (matcher.match(cell)) {
                    anchor = Anchor.of(rowIndex, columnIndex);
                    break;
                }
                columnIndex++;
            }
            rowIndex++;
            columnIndex = 0;
        }

        return Optional.ofNullable(anchor);
    }
}
