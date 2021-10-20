package model;

// Represents a player having a name, team and game statistics
public class Player {
    private String name;
    private Team fantasyTeam;
    private double fieldGoalPct;
    private double freeThrowPct;
    private double points;
    private double rebounds;
    private double assists;

    // EFFECT: creates a new player with their basic information,
    //         and sets all the category statistics to 0
    public Player(String name, Team team) {
        this.name = name;
        fantasyTeam = team;
        fieldGoalPct = 0;
        freeThrowPct = 0;
        points = 0;
        rebounds = 0;
        assists = 0;
    }

    // getters
    public String getName() {
        return name;
    }

    public Team getFantasyTeam() {
        return fantasyTeam;
    }

    public double getFieldGoalPct() {
        return fieldGoalPct;
    }

    public double getFreeThrowPct() {
        return freeThrowPct;
    }

    public double getPoints() {
        return points;
    }

    public double getRebounds() {
        return rebounds;
    }

    public double getAssists() {
        return assists;
    }

    // setter
    // EFFECTS: sets the statistics for each category for player
    public void setFieldGoalPct(double fieldGoalPct) {
        this.fieldGoalPct = fieldGoalPct;
    }

    public void setFreeThrowPct(double freeThrowPct) {
        this.freeThrowPct = freeThrowPct;
    }

    public void setPoints(double points) {
        this.points = points;
    }

    public void setRebounds(double rebounds) {
        this.rebounds = rebounds;
    }

    public void setAssists(double assists) {
        this.assists = assists;
    }

}
