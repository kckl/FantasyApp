package ui;

import model.League;
import model.Team;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents the frame for the view league panel
public class ViewLeaguePanel extends JFrame {
    private JFrame viewLeagueFrame;
    private JPanel viewLeaguePanel;
    private JLabel teamNames;
    private League league;
    private String teamName;

    // EFFECTS: sets up window for view league panel
    public ViewLeaguePanel(League league) {
        this.league = league;

        viewLeagueFrame = new JFrame("Registered Teams");
        viewLeagueFrame.setSize(FantasyAppUI.WIDTH, FantasyAppUI.HEIGHT);
        viewLeagueFrame.setLocationRelativeTo(null);

        createViewLeaguePanel();
        displayTeamsList();
        createAddTeamButton();
        createRemoveTeamButton();
        createSelectButton();
        createBackButton();

        viewLeagueFrame.setVisible(true);
    }


    // MODIFIES: this
    // EFFECTS: create and setup view league panel
    public void createViewLeaguePanel() {
        viewLeaguePanel = new JPanel();
        viewLeaguePanel.setLayout(new GridLayout(5, 1, 0, 0));

        viewLeaguePanel.setBackground(Color.white);
        viewLeaguePanel.setBorder(BorderFactory.createLineBorder(Color.darkGray, 10));

        viewLeagueFrame.add(viewLeaguePanel);

    }

    // MODIFIES: this
    // EFFECTS: displays list of teams registered to league and add to view team panel
    public void displayTeamsList() {
        teamNames = new JLabel();
        teamNames.setText("Registered Teams: " + league.getTeamNames());
        viewLeaguePanel.add(teamNames);

    }

    // MODIFIES: this
    // EFFECTS: create add team button and add to home panel
    public void createAddTeamButton() {
        JButton addTeamButton = new JButton("Add Team");
        viewLeaguePanel.add(addTeamButton);
        addTeamButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (league.isFull()) {
                    JOptionPane.showMessageDialog(null,
                            "The league is full.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    addTeamPopUp();
//                    viewLeagueFrame.setVisible(false);
//                    new ViewLeaguePanel(league);
//                    revalidate();
                }
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: create remove team button and add to home panel
    public void createRemoveTeamButton() {
        JButton removeTeamButton = new JButton("Remove Team");
        viewLeaguePanel.add(removeTeamButton);
        removeTeamButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (league.getTeamNames().isEmpty()) {
                    JOptionPane.showMessageDialog(null,
                            "There are no teams to remove.", "Message",
                            JOptionPane.PLAIN_MESSAGE);
                } else {
                    removeTeamPopUp();
                }
            }
        });
    }


    // MODIFIES: this
    // EFFECTS: create select button and add to view team panel
    public void createSelectButton() {
        JButton selectButton = new JButton("Select Team");
        viewLeaguePanel.add(selectButton);
        selectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (league.getTeamNames().isEmpty()) {
                    JOptionPane.showMessageDialog(null,
                            "There are no teams to select.", "Message",
                            JOptionPane.PLAIN_MESSAGE);
                } else {
                    selectTeamPopUp();
                }
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: create back button and add to view team panel
    public void createBackButton() {
        JButton backButton = new JButton("Back");
        viewLeaguePanel.add(backButton);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewLeagueFrame.dispose();

            }
        });

    }

    // REQUIRES: input to be a non-empty string
    // EFFECTS: create the add team pop up for user to input team name
    private void addTeamPopUp() {
        teamName = JOptionPane.showInputDialog("Enter Team Name");
        if ((teamName != null) && (teamName.length() > 0)) {
            registerTeam(teamName);
        }
    }

    // MODIFIES: this
    // EFFECTS: adds a new team to the league unless team name has already been used
    public void registerTeam(String teamName) {
        Team newTeam = new Team(teamName);
        if (league.registerTeam(newTeam)) {
            JOptionPane.showMessageDialog(null, teamName + " has been successfully registered.",
                    "Add Team", JOptionPane.PLAIN_MESSAGE);
            viewLeagueFrame.setVisible(false);
            new ViewLeaguePanel(league);
            revalidate();
        } else {
            JOptionPane.showMessageDialog(null,
                    teamName + " has already been used. Please choose a different team name.", "Team Name Used",
                    JOptionPane.WARNING_MESSAGE);
        }
    }


    // REQUIRES: input to be a non-empty string
    // EFFECTS: create the remove team pop up for user to input team name
    private void removeTeamPopUp() {
        teamName = JOptionPane.showInputDialog("Which team would you like to remove?");
        if ((teamName != null) && (teamName.length() > 0)) {
            removeTeam(teamName);
        }
    }

    // MODIFIES: this
    // EFFECTS: removes a new team from the league
    private void removeTeam(String teamName) {
        if (league.removeTeam(teamName)) {
            JOptionPane.showMessageDialog(null, teamName + " has been successfully removed.",
                    "Remove Team", JOptionPane.PLAIN_MESSAGE);
            viewLeagueFrame.setVisible(false);
            new ViewLeaguePanel(league);
            revalidate();
        } else {
            JOptionPane.showMessageDialog(null,
                    teamName + " can not be found.", "Team not found",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    // REQUIRES: input to be a non-empty string
    // EFFECTS: create the select team pop up for user to input team name
    private void selectTeamPopUp() {
        teamName = JOptionPane.showInputDialog("Which team would you like to select?");
        if ((teamName != null) && (teamName.length() > 0)) {
            //stub
        }
    }
}
