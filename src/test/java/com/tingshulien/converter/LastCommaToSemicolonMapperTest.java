package com.tingshulien.converter;

import org.junit.jupiter.api.BeforeAll;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(JUnitPlatform.class)
public class LastCommaToSemicolonMapperTest {

    private static Mapper mapper;

    @BeforeAll
    public static void beforeAll() {
        mapper = new LastCommaToSemicolonMapper();
    }

    public void giveLastCommaWhenMapThenReturnSemicolon() {
        String lastCommaSentence = "Last char is ,";

        assertThat(mapper.map(lastCommaSentence), is(equalTo("Last char is ;")));
    }

    public void giveLastCommaWithSpacesWhenMapThenReturnTrimmedSemicolon() {
        String lastCommaSentence = "  Last char is ,  ";

        assertThat(mapper.map(lastCommaSentence), is(equalTo("Last char is ;")));
    }
}