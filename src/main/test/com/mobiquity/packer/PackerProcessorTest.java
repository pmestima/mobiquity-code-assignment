package com.mobiquity.packer;

import com.mobiquity.dto.PackageDto;
import com.mobiquity.dto.ThingDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class PackerProcessorTest {

    @InjectMocks
    private PackerProcessor packerProcessor;

    @Test
    void processEmptyList() {
        PackageDto packageDto1 = new PackageDto(10, Collections.emptyList());

        String process = packerProcessor.process(Arrays.asList(packageDto1));

        Assertions.assertEquals("-\n", process);
    }

    @Test
    void processList() {
        ThingDto thing1 = new ThingDto(0, 10, 5);
        ThingDto thing2 = new ThingDto(1, 9, 5);
        PackageDto packageDto1 = new PackageDto(10, Arrays.asList(thing1, thing2));

        String process = packerProcessor.process(Arrays.asList(packageDto1));

        Assertions.assertEquals("1\n", process);
    }

    @Test
    void extractResultForEmptyList() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method extractResult = PackerProcessor.class.getDeclaredMethod("extractResult", List.class);
        extractResult.setAccessible(true);

        String result = (String) extractResult.invoke(packerProcessor, Collections.emptyList());
        Assertions.assertEquals("-", result);
    }

    @Test
    void extractResultForList() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method extractResult = PackerProcessor.class.getDeclaredMethod("extractResult", List.class);
        extractResult.setAccessible(true);

        ThingDto thing1 = new ThingDto(0, 123D, 456D);
        ThingDto thing2 = new ThingDto(1, 789D, 963D);

        String result = (String) extractResult.invoke(packerProcessor, Arrays.asList(thing1, thing2));
        Assertions.assertEquals("0,1", result);
    }
}
