package com.tingshulien.converter;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class AnchorMatcher {

    public enum Predefined {

        TRUNCATE(AnchorMatcher.TRUNCATE);

        private final AnchorMatcher finder;

        Predefined(AnchorMatcher finder) {
            this.finder = finder;
        }

        public AnchorMatcher getFinder() {
            return finder;
        }
    }

    public static final AnchorMatcher TRUNCATE = new AnchorMatcher();

    public AnchorMatcher() {
    }

    private Predicate<String> truncate = row -> row.trim().toLowerCase().replaceAll("\"", "").startsWith("truncate");

    public boolean match(String row) {
        return truncate.test(row);
    }


}
