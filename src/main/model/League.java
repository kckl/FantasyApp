package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// Represents a league having a list of teams
public class League implements Writable {
    private List<Team> league;
    private String leagueName;
    private int leagueSize;
    private List<String> teamNames;

    // REQUIRES: leagueName to be non-empty string
    // EFFECTS: creates a new fantasy league and sets max league size to 0
    public League(String name, int size) {
        league = new ArrayList<>();
        leagueName = name;
        leagueSize = size;
    }

    // getters
    public String getLeagueName() {
        return leagueName;
    }

    public int getLeagueSize() {
        return leagueSize;
    }

    // EFFECTS: returns team at indexed position
    public Team getTeam(int position) {
        return league.get(position);
    }

    // EFFECTS: returns a list of team names for registered teams
    public List<String> getTeamNames() {
        teamNames = new ArrayList<>();
        String team;

        for (Team t : league) {
            team = t.getTeamName();
            teamNames.add(team);
        }
        return teamNames;
    }

    // MODIFIES: this
    // EFFECTS: renames the league name
    public void changeLeagueName(String name) {
        leagueName = name;
    }

    // MODIFIES: this
    // EFFECTS: changes the size of the league
    public void changeLeagueSize(int size) {
        leagueSize = size;
    }


    // MODIFIES: this
    // EFFECTS: registers a team to the league if league is not full, and if team is not already in league.
    //          return true if team successfully registered, else, return false
    public boolean registerTeam(Team t) {
        if (!isFull() && !league.contains(t)) {
            league.add(t);
            return true;
        }
        return false;
    }

    // MODIFIES: this
    // EFFECTS: removes inputted team from the league
    //          return true if team successfully removed, else, return false
    public boolean removeTeam(String name) {
        for (Team t : league) {
            if (name == t.getTeamName()) {
                league.remove(t);
                return true;
            }
        }
        return false;
    }


    // EFFECTS: returns true if league is full, otherwise false
    public boolean isFull() {
        return league.size() >= leagueSize;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", leagueName);
        json.put("size", leagueSize);
        json.put("teams", teamsToJson());
        return json;
    }

    // EFFECTS: returns teams in this league as a JSON array
    private JSONArray teamsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Team team : league) {
            jsonArray.put(team.toJson());
        }

        return jsonArray;
    }


}


