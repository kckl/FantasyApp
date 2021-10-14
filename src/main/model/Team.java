package model;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private List<Player> roster;
    private String teamName;
    private List<String> playerNames;
    private static int MAX_TEAM_SIZE = 13;

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
        playerNames = new ArrayList<>();
        String name;

        for (Player p : roster) {
            name = p.getName();
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
    // EFFECTS: adds a player to the roster if the player is not already on the roster, and if team is not full.
    //          return true if successfully added, else, return false
    //          (?) check player is not already on another team
    public boolean addPlayer(Player p) {
        if ((!roster.contains(p)) && (roster.size() < MAX_TEAM_SIZE)) {
            roster.add(p);
            return true;
        }
        return false;
    }
}



