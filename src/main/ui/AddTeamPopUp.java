package ui;

import model.League;
import model.Team;

import javax.swing.*;
import java.awt.*;

// Represents the add team window
public class AddTeamPopUp extends JFrame {
    private String teamName;
    private League league;

    // EFFECTS: create the add team pop up for user to input team name
    public AddTeamPopUp(League league) {
        teamName = JOptionPane.showInputDialog("Enter Team Name");
        this.league = league;
        addTeam(teamName);
    }

    // REQUIRES: input to be a non-empty string
    // MODIFIES: this
    // EFFECTS: adds a new team to the league unless league is full
    public void addTeam(String teamName) {
        Team newTeam = new Team(teamName);
        if (league.registerTeam(newTeam)) {
            JOptionPane.showMessageDialog(null, teamName + " has been successfully registered.",
                    "Add Team", JOptionPane.PLAIN_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null,
                    teamName + " cannot be registered because the league is full.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
