package com.tingshulien.converter;

import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(JUnitPlatform.class)
public class CellTest {

    private Cell cell;

    @Test
    public void whenGetRowIndexThenReturnIndex() {
        cell = Cell.of(0, 1, "test");

        assertThat(cell.getRowIndex() ,is(equalTo(0)));
    }

    @Test
    public void whenGetColumnIndexThenReturnIndex() {
        cell = Cell.of(0, 1, "test");

        assertThat(cell.getColumnIndex() ,is(equalTo(1)));
    }

    @Test
    public void whenGetContentThenReturnMessage() {
        cell = Cell.of(0, 1, "test");

        assertThat(cell.getContent() ,is(equalTo("test")));
    }

    @Test
    public void whenGetFilterThenReturnFalse() {
        cell = Cell.of(0, 1, "test");

        assertThat(cell.isFiltered() ,is(equalTo(false)));
    }

    @Test
    public void whenFilterIsSetTrueThenReturnTrue() {
        cell = Cell.of(0, 1, "test");

        cell.setFiltered(true);

        assertThat(cell.isFiltered() ,is(equalTo(true)));
    }

    @Test
    public void whenGetNegativeRowIndexThenThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> Cell.of(-1, 1, "test"));
    }

    @Test
    public void whenGetNegativeColumnIndexThenThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> Cell.of(1, -1, "test"));
    }

}