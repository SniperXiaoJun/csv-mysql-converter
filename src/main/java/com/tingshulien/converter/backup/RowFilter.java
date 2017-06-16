package com.tingshulien.converter.backup;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class RowFilter {

    public enum Predifined {

        DEFAULT(RowFilter.DEFAULT),
        EXCLUDE_ONLY_COMMA_BRACKET(RowFilter.EXCLUDE_ONLY_COMMA_BRACKET),
        INCULDE_ONLY_ANCHOR_COLUM(RowFilter.EXCLUDE_ONLY_COMMA_BRACKET);

        private final RowFilter rowFilter;

        Predifined(RowFilter rowFilter) {
            this.rowFilter = rowFilter;
        }
    }

    public static final RowFilter DEFAULT = new RowFilter();

    public static final RowFilter EXCLUDE_ONLY_COMMA_BRACKET = DEFAULT.excludeOnlyCommaBracket();

    public static final RowFilter INCULDE_ONLY_ANCHOR_COLUM = DEFAULT.excludeOnlyCommaBracket();

    private List<Predicate<String>> predicates = new ArrayList<>();

    public RowFilter() {
        predicates.add(row -> row
                .trim()
                .isEmpty());
    }

    private RowFilter excludeOnlyCommaBracket() {
        predicates.add(row -> row
                .toLowerCase()
                .replaceAll("[^(,]", "")
                .isEmpty());
        return this;
    }



    public boolean redundent(String row) {
        boolean ok = true;

        for (Predicate<String> pre : predicates) {
            ok = ok && pre.test(row);
            if (! ok) {
                break;
            }
        }

        return ok;
    }

}
