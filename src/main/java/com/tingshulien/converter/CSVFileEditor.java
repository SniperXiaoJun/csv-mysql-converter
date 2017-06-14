package com.tingshulien.converter;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CSVFileEditor {

    private List<CellFilter> cellFilter;




}
