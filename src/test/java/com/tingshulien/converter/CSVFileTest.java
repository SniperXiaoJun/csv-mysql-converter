package com.tingshulien.converter;

import com.tingshulien.converter.filter.CellFilter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;

@RunWith(JUnitPlatform.class)
public class CSVFileTest {

    private static final String PATH = "./test.csv";

    private CSVFile file;

    @BeforeEach
    public void before() {
        file = CSVFile.of(PATH, rows());
    }

    private List<List<Cell>> rows() {
        List<List<Cell>> rows = new ArrayList<>();
        List<Cell> firstRow = new ArrayList<>();
        rows.add(firstRow);

        firstRow.add(Cell.of(0, 0, "This "));
        firstRow.add(Cell.of(0, 1, "is "));
        firstRow.add(Cell.of(0, 2, "first "));
        firstRow.add(Cell.of(0, 3, "row!"));

        return rows;
    }

    @Test
    public void whenInstantiateThenPathIsSet() {
        assertThat(file.getPath(), is(equalTo(Paths.get(PATH))));
    }

    @Test
    public void whenInstantiateThenNotPrintRows() {
        assertThat(file.print(), not(containsString("This is first row!")));
    }

    @Test
    public void whenInstantiateAndAllFilteredTrueThenPrintAllRows() {
        CellFilter filterAlwaysReturnTrue = cell -> true;

        file.filter(filterAlwaysReturnTrue);

        assertThat(file.print(), containsString("This is first row!"));
    }

    @Test
    public void whenInstantiateAndAllFilteredFalseThenNotPrintRows() {
        CellFilter filterAlwaysReturnFalse = cell -> false;

        file.filter(filterAlwaysReturnFalse);

        assertThat(file.print(), not(containsString("This is first row!")));
    }

}