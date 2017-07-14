package com.tingshulien.converter.filter;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import com.tingshulien.converter.Cell;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
public class EmptyCellFilterTest {

    private static CellFilter filter;

    @BeforeAll
    public static void beforeClass() {
        filter = new EmptyCellFilter();
    }

    @Test
    public void givenEmptyCellWhenExamineThenReturnTrue() {
        Cell emptyCell = Cell.of(1, 1, "");

        assertThat(filter.examine(emptyCell), is(equalTo(true)));
    }

    @Test
    public void givenNotEmptyCellWhenExamineThenReturnFalse() {
        Cell stringCell = Cell.of(1, 1, "not empty");

        assertThat(filter.examine(stringCell), is(equalTo(false)));
    }

    @Test
    public void givenSpaceCellWhenExamineThenReturnTrue() {
        Cell spaceCell = Cell.of(1, 1, "  ");

        assertThat(filter.examine(spaceCell), is(equalTo(true)));
    }

}