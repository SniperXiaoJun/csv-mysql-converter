package com.tingshulien.converter.backup;

import java.util.ArrayList;

public class RowMapper {

    public enum Predefined {

        DEFAULT(RowMapper.DEFAULT);

        private final RowMapper mapper;

        Predefined(RowMapper mapper) {
            this.mapper = mapper;
        }

        public RowMapper getMapper() {
            return mapper;
        }
    }

    public static final RowMapper DEFAULT = new RowMapper();

    public RowMapper() {
    }

    ArrayList<ArrayList<String>> map(ArrayList<ArrayList<String>> rows) {
        return rows;
    }

}
