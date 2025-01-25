package com.kairos.trashtrack;

public class PointsCalculator {
    public static int calculatePoints(String wasteType, double weight) {
        int basePoints = 10;
        switch (wasteType) {
            case "Plastic":
                return (int) (basePoints * weight * 1.5);
            case "Organic":
                return (int) (basePoints * weight);
            case "Metal":
                return (int) (basePoints * weight * 2);
            case "Paper":
                return (int) (basePoints * weight * 1.2);
            case "Glass":
                return (int) (basePoints * weight * 1.3);
            default:
                return 0;
        }
    }
}
