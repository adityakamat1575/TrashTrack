package com.kairos.trashtrack;

public class User {

    private String userId;
    private int points;
    private int level;
    private int challengesCompleted;

    public User(String userId, int points, int level, int challengesCompleted) {
        this.userId = userId;
        this.points = points;
        this.level = level;
        this.challengesCompleted = challengesCompleted;
    }

    public String getUserId() {
        return userId;
    }

    public int getPoints() {
        return points;
    }

    public void addPoints(int points) {
        this.points += points;
    }

    public int getLevel() {
        return level;
    }

    public void levelUp() {
        if (this.points >= level * 100) {
            this.level++;
        }
    }

    public int getChallengesCompleted() {
        return challengesCompleted;
    }

    public void completeChallenge() {
        this.challengesCompleted++;
    }
}
