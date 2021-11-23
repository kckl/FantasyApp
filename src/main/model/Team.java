package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// Represents a team having a roster, team name, and max size
public class Team implements Writable {
    private List<Player> roster;
    private String teamName;
    private static final int MAX_TEAM_SIZE = 13;

    // EFFECT: creates a new fantasy team
    public Team(String name) {
        roster = new ArrayList<>();
        teamName = name;
    }

    // getters
    public String getTeamName() {
        return teamName;
    }

    // EFFECTS: returns player at indexed position
    public Player getPlayer(int position) {
        return roster.get(position);
    }

    // EFFECTS: returns a list of player names for roster
    public List<String> getPlayerNames() {
        List<String> playerNames = new ArrayList<>();
        String name;

        for (Player p : roster) {
            name = p.getPlayerName();
            playerNames.add(name);
        }
        return playerNames;
    }

    // MODIFIES: this
    // EFFECTS: renames the team name
    public void changeTeamName(String name) {
        teamName = name;
    }

    // MODIFIES: this
    // EFFECTS: adds a player to the roster if team is not full, and if player is not already on team.
    //          return true if successfully added, else, return false
    public boolean addPlayer(Player p) {
        if (!isFull() && !roster.contains(p)) {
            roster.add(p);
            EventLog.getInstance().logEvent(new Event(p.toString() + " added to " + this.toString() + "'s team"));
            return true;
        }
        return false;
    }

    // EFFECTS: returns true if team is full, otherwise false
    public boolean isFull() {
        return roster.size() >= MAX_TEAM_SIZE;
    }

    // This method references code from this repo
    // Link: [https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git]
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", teamName);
        json.put("roster", roster);
        json.put("players", playersToJson());
        return json;
    }

    // EFFECTS: returns players in this team as a JSON array
    private JSONArray playersToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Player player : roster) {
            jsonArray.put(player.toJson());
        }

        return jsonArray;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Team team = (Team) o;

        return Objects.equals(teamName, team.teamName);
    }

    @Override
    public int hashCode() {
        return teamName != null ? teamName.hashCode() : 0;
    }

    @Override
    public String toString() {
        return this.teamName;
    }

}



