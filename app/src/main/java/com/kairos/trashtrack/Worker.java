package com.kairos.trashtrack;


public class Worker {
    private final String name;
    private final String category;

    public Worker(String name, String category) {
        this.name = name;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getPosition() {
        return null;
    }
}
