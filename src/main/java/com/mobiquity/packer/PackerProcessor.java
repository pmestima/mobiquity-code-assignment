package com.mobiquity.packer;

import com.mobiquity.dto.PackageDto;
import com.mobiquity.dto.ThingDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PackerProcessor {

    public String process(List<PackageDto> packages) {
        StringBuilder result = new StringBuilder();
        for (PackageDto aPackage : packages) {
            List<ThingDto> sortedThings = aPackage.getSortedThings();

            double totalWeight = 0D;

            List<ThingDto> packedThings = new ArrayList<>(sortedThings.size());
            for (ThingDto sortedThing : sortedThings) {
                if (totalWeight + sortedThing.getWeight() <= aPackage.getWeightLimit()) {
                    packedThings.add(sortedThing);
                    totalWeight += sortedThing.getWeight();
                }
            }

            String extractResult = extractResult(packedThings);
            result.append(extractResult)
                .append("\n");
        }

        return result.toString();
    }

    private String extractResult(List<ThingDto> packedThings) {
        if (packedThings.size() == 0) {
            return "-";
        }

        return packedThings.stream()
            .map(ThingDto::getIndex)
            .map(String::valueOf)
            .collect(Collectors.joining(","));
    }
}
