package com.mobiquity.packer;

import com.mobiquity.dto.PackageDto;
import com.mobiquity.dto.ThingDto;
import com.mobiquity.exception.APIException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class Parser {

    public static List<PackageDto> parseFile(String filePath) throws APIException {
        File readFile = new File(filePath);
        try {
            List<String> lines = FileUtils.readLines(readFile, Charset.defaultCharset());
            return parse(lines);
        } catch (IOException e) {
            throw new APIException("Error reading file", e);
        }
    }

    public static List<PackageDto> parse(List<String> lines) throws APIException {
        List<PackageDto> packages = new ArrayList<>(lines.size());
        for (String line : lines) {
            PackageDto packageDto = parse(line);
            if (packageDto != null) {
                packages.add(packageDto);
            }
        }
        return packages;
    }

    public static PackageDto parse(String line) throws APIException {
        if (StringUtils.isBlank(line)) {
            return null;
        }

        try {
            String[] limitAndData = line.split(" : ");
            String[] packagesData = limitAndData[1].split(" ");

            double weightLimit = Double.parseDouble(limitAndData[0]);
            List<ThingDto> things = new ArrayList<>(packagesData.length);
            for (String packageData : packagesData) {
                String[] split = packageData.split(",");
                int index = Integer.parseInt(split[0].substring(1));
                double weight = Double.parseDouble(split[1]);
                double cost = Double.parseDouble(split[2].substring(1, split[2].length() - 1));

                things.add(new ThingDto(index, weight, cost));
            }
            return new PackageDto(weightLimit, things);
        } catch (Exception ex) {
            throw new APIException("Error parsing line: " + line, ex);
        }
    }

}
