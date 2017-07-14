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
public class BracketQuotationCommaSpaceOnlyFilterTest {

    private static CsvCellFilter filter;

    @BeforeAll
    public static void beforeAll() {
        filter = new BracketQuotationCommaSpaceOnlyFilter();
    }

    @Test
    public void givenOnlySingleQuotationWhenExamineThenReturnTrue() {
        Cell singleQuotation = Cell.of(1, 1, "'");

        assertThat(filter.examine(singleQuotation), is(equalTo(true)));
    }

    @Test
    public void givenOnlyLeftCurlyBracketWhenExamineThenReturnTrue() {
        Cell leftBracket = Cell.of(1, 1, "(");

        assertThat(filter.examine(leftBracket), is(equalTo(true)));
    }

    @Test
    public void givenOnlyRightCurlyBracketWhenExamineThenReturnTrue() {
        Cell rightBracket = Cell.of(1, 1, ")");

        assertThat(filter.examine(rightBracket), is(equalTo(true)));
    }

    @Test
    public void givenOnlyCommaWhenExamineThenReturnTrue() {
        Cell brackets = Cell.of(1, 1, ",");

        assertThat(filter.examine(brackets), is(equalTo(true)));
    }

    @Test
    public void givenBothBracketWhenExamineThenReturnTrue() {
        Cell quotation = Cell.of(1, 1, "`");

        assertThat(filter.examine(quotation), is(equalTo(true)));
    }

    @Test
    public void givenAllWhenExamineThenReturnTrue() {
        Cell brackets = Cell.of(1, 1, "('','')");

        assertThat(filter.examine(brackets), is(equalTo(true)));
    }

    @Test
    public void givenTrimmedSpacesWhenExamineThenReturnTrue() {
        Cell brackets = Cell.of(1, 1, " ('','') ");

        assertThat(filter.examine(brackets), is(equalTo(true)));
    }

    @Test
    public void givenAllWithSpacesBetweenWhenExamineThenReturnTrue() {
        Cell brackets = Cell.of(1, 1, " ('', '') ");

        assertThat(filter.examine(brackets), is(equalTo(true)));
    }

    @Test
    public void givenSpacesWhenExamineThenReturnTrue() {
        Cell spaces = Cell.of(1, 1, "  ");

        assertThat(filter.examine(spaces), is(equalTo(true)));
    }

    @Test
    public void givenEmptyWhenExamineThenReturnFalse() {
        Cell empty = Cell.of(1, 1, "");

        assertThat(filter.examine(empty), is(equalTo(false)));
    }

    @Test
    public void givenAdditionalStringWhenExamineThenReturnFalse() {
        Cell additionalChar = Cell.of(1, 1, "('a',)");

        assertThat(filter.examine(additionalChar), is(equalTo(false)));
    }

}