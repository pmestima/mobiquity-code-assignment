package com.mobiquity.packer;

import com.mobiquity.exception.APIException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PackerTest {

    @Test
    void pack() throws APIException {
        String expected = "4\n-\n2,7\n8,9\n";
        assertEquals(expected, Packer.pack("./src/main/test/resources/example_input"));
    }
}
