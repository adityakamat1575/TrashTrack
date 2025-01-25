package com.kairos.trashtrack;

import java.util.List;

public class FoursquareResponse {
    public List<Venue> results;

    public static class Venue {
        public String name;
        public Location location;

        public static class Location {
            public double lat;
            public double lng;
        }
    }
}