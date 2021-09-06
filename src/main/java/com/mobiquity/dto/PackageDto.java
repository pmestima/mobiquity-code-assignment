package com.mobiquity.dto;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PackageDto {

    private final static Comparator<ThingDto> comparator = Comparator.<ThingDto>comparingDouble(x -> -x.getCost())
        .thenComparingDouble(ThingDto::getWeight);

    private final double weightLimit;
    private final List<ThingDto> things;

    public PackageDto(double weightLimit, List<ThingDto> things) {
        this.weightLimit = weightLimit;
        this.things = things;
    }

    public double getWeightLimit() {
        return weightLimit;
    }

    public List<ThingDto> getThings() {
        return things;
    }

    public List<ThingDto> getSortedThings() {
        return new ArrayList<>(things)
            .stream()
            .sorted(comparator)
            .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "PackageDto{" +
            "weightLimit=" + weightLimit +
            ", things=" + things +
            '}';
    }
}
