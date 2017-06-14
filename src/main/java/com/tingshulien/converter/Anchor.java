package com.tingshulien.converter;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor(staticName = "of")
public class Anchor {

    final private int rowIndex;
    final private int columnIndex;

}
