package com.tingshulien.converter.filter;

import com.tingshulien.converter.Cell;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(JUnitPlatform.class)
public class CsvCellFilterTest {

    private static CsvCellFilter emptyFilter;
    private static CsvCellFilter emptyOrBracketOnlyFilter;
    private static CsvCellFilter andNotFilter;

    @BeforeAll
    public static void beforeAll() {
        emptyFilter = new EmptyCellFilter();
        emptyOrBracketOnlyFilter = emptyFilter.or(new BracketQuotationCommaSpaceOnlyFilter());
        andNotFilter = new TruncateColumnFilter().andNot(emptyOrBracketOnlyFilter);
    }

    @Test
    public void givenEmptyWhenEmptyOrBracketOnlyFilterExamineThenReturnTrue() {
        Cell empty = Cell.of(1, 1, "");

        assertThat(emptyOrBracketOnlyFilter.examine(empty), is(equalTo(true)));
    }

    @Test
    public void givenBracketOnlyWhenEmptyOrBracketOnlyFilterExamineThenReturnTrue() {
        Cell bracketOnly = Cell.of(1, 1, "(, '')");

        assertThat(emptyOrBracketOnlyFilter.examine(bracketOnly), is(equalTo(true)));
    }

    @Test
    public void givenTruncateColumnWhenExamineThenReturnTrue() {
        Cell truncate = Cell.of(1, 1, "TRUNCATE table; INSERT INTO table (`id`, `name`)");
        Cell value = Cell.of(2, 1, "(1, 'tim'),");

        assertThat(andNotFilter.examine(truncate), is(equalTo(true)));
        assertThat(andNotFilter.examine(value), is(equalTo(true)));
    }

    @Test
    public void test() {
        Cell test = Cell.of(3, 1, ",,");

        assertThat(andNotFilter.examine(test), is(equalTo(false)));
    }


    @Test
    public void givenEmptyOrBracketOnlyWhenTruncateColumnExamineThenReturnFalse() {
        Cell emptyValue = Cell.of(3, 1, "(, ''),");
        Cell empty = Cell.of(4, 1, "");

        assertThat(andNotFilter.examine(emptyValue), is(equalTo(false)));
        assertThat(andNotFilter.examine(empty), is(equalTo(false)));
    }

    @Test
    public void givenNullFilterWhenOrThenThrowNullPointException() {
        assertThrows(NullPointerException.class, () -> emptyFilter.or(null));
    }

    @Test
    public void givenNullFilterWhenAndNotThenThrowNullPointException() {
        assertThrows(NullPointerException.class, () -> emptyFilter.andNot(null));
    }

    @Test
    public void givenNullCellWhenOrFilterExamineThenThrowNullPointException() {
        assertThrows(NullPointerException.class, () -> emptyOrBracketOnlyFilter.examine(null));
    }

    @Test
    public void givenNullCellWhenAndNotFilterExamineThenThrowNullPointException() {
        assertThrows(NullPointerException.class, () -> andNotFilter.examine(null));
    }
}