package model;

import java.util.ArrayList;
import java.util.List;

public class League {
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
    // EFFECTS: registers a team to the league if league is not full.
    //          return true if team successfully registered, else, return false
    public boolean registerTeam(Team t) {
        if (league.size() < leagueSize) {
            league.add(t);
            return true;
        }
        return false;
    }

}
