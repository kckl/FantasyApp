package model;

import org.json.JSONObject;
import persistence.Writable;


// Represents a player having a name, team and game statistics
public class Player implements Writable {
    private String playerName;
    private Team fantasyTeam;
    private double fieldGoalPct;
    private double freeThrowPct;
    private double points;
    private double rebounds;
    private double assists;

    // EFFECT: creates a new player with their basic information,
    //         and sets all the category statistics to 0
    public Player(String name, Team team) {
        this.playerName = name;
        fantasyTeam = team;
        fieldGoalPct = 0;
        freeThrowPct = 0;
        points = 0;
        rebounds = 0;
        assists = 0;
    }

    // getters
    public String getPlayerName() {
        return playerName;
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


    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("playerName", playerName);
        json.put("fantasyTeam", fantasyTeam);
        json.put("fieldGoalPct", fieldGoalPct);
        json.put("freeThrowPct", freeThrowPct);
        json.put("points", points);
        json.put("rebounds", rebounds);
        json.put("assists", assists);
        return json;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Player player = (Player) o;

        return playerName != null ? playerName.equals(player.playerName) : player.playerName == null;
    }

    @Override
    public int hashCode() {
        return playerName != null ? playerName.hashCode() : 0;
    }

    @Override
    public String toString() {
        return this.playerName;
    }
}
