package com.mobiquity.dto;

public class ThingDto {
    private final int index;
    private final double weight;
    private final double cost;

    public ThingDto(int index, double weight, double cost) {
        this.index = index;
        this.weight = weight;
        this.cost = cost;
    }

    public int getIndex() {
        return index;
    }

    public double getWeight() {
        return weight;
    }

    public Double getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return "ThingDto{" + index + ", " + weight + ", €" + cost + "}";
    }
}
