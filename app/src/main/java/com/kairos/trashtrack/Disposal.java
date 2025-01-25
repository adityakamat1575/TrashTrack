package com.kairos.trashtrack;

public class Disposal {
    private String type;
    private double weight;
    private String timestamp;

    public Disposal(String type, double weight, String timestamp) {
        this.type = type;
        this.weight = weight;
        this.timestamp = timestamp;
    }

    public String getType() {
        return type;
    }

    public double getWeight() {
        return weight;
    }

    public String getTimestamp() {
        return timestamp;
    }
}

