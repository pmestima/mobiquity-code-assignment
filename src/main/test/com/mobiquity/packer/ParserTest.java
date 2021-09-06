package com.mobiquity.packer;

import com.mobiquity.dto.PackageDto;
import com.mobiquity.dto.ThingDto;
import com.mobiquity.exception.APIException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class ParserTest {

    @Test
    void parse() throws APIException {
        List<PackageDto> parse = Parser.parse(Collections.singletonList("5 : (0,10,€50)"));

        assertEquals(1, parse.size());
        assertEquals(parse.get(0).getWeightLimit(), 5);
        List<ThingDto> things = parse.get(0).getThings();
        assertEquals(things.get(0).getIndex(), 0);
        assertEquals(things.get(0).getWeight(), 10);
        assertEquals(things.get(0).getCost(), 50);
    }

    @Test
    void testParseForEmptyString() throws APIException {
        assertNull(Parser.parse(""));

        APIException exception = assertThrows(APIException.class, () -> Parser.parse("wrong string"));
        assertEquals("Error parsing line: wrong string", exception.getMessage());
    }

    @Test
    void testParse() throws APIException {
        PackageDto packageDto = Parser.parse("5 : (0,10,€50)");

        assertNotNull(packageDto);
        assertEquals(packageDto.getWeightLimit(), 5);
        List<ThingDto> things = packageDto.getThings();
        assertEquals(things.get(0).getIndex(), 0);
        assertEquals(things.get(0).getWeight(), 10);
        assertEquals(things.get(0).getCost(), 50);
    }
}
