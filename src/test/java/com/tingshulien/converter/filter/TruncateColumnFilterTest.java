package com.tingshulien.converter.filter;

import com.tingshulien.converter.Cell;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(JUnitPlatform.class)
class TruncateColumnFilterTest {

    private CsvCellFilter truncateColumnFilter;

    @BeforeEach
    public void before() {
        truncateColumnFilter = new TruncateColumnFilter();
    }

    @Test
    public void givenTruncateStringWhenExamineThenReturnTrue() {
        Cell truncate = Cell.of(1, 1, "truncate table;");

        assertThat(truncateColumnFilter.examine(truncate), is(equalTo(true)));
    }

    @Test
    public void givenQuotedTruncateWhenExamineThenReturnTrue() {
        Cell truncate = Cell.of(1, 1, "\"truncate table ;\"");

        assertThat(truncateColumnFilter.examine(truncate), is(equalTo(true)));
    }

    @Test
    public void givenUppercaseTruncateWhenExamineThenReturnTrue() {
        Cell truncate = Cell.of(1, 1, "TrUnCaTe table ;");

        assertThat(truncateColumnFilter.examine(truncate), is(equalTo(true)));
    }

    @Test
    public void givenNonTruncateStringWhenExamineThenReturnFalse() {
        Cell truncate = Cell.of(1, 1, "drop table;");

        assertThat(truncateColumnFilter.examine(truncate), is(equalTo(false)));
    }

    @Test
    public void givenTruncateColumnWhenExamineThenReturnTrue() {
        Cell truncate = Cell.of(1, 1, "TRUNCATE table; INSERT INTO table (`id`, `name`)");
        Cell comment = Cell.of(1, 2, "comment");
        Cell belowTruncate = Cell.of(2, 1, "(1, 'tim'),");
        Cell bottomOfTruncateColumn = Cell.of(999, 1, "(999, 'elisa'),");

        assertThat(truncateColumnFilter.examine(truncate), is(equalTo(true)));
        assertThat(truncateColumnFilter.examine(comment), is(equalTo(false)));
        assertThat(truncateColumnFilter.examine(belowTruncate), is(equalTo(true)));
        assertThat(truncateColumnFilter.examine(bottomOfTruncateColumn), is(equalTo(true)));
    }

}