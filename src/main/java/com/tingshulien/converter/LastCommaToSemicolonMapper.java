package com.tingshulien.converter;

public class LastCommaToSemicolonMapper implements Mapper {

    @Override
    public String map(String sql) {
        if (sql.trim().endsWith(",")) {
            sql = sql.replaceAll("[,]$", ";");
        }

        return sql;
    }
}
