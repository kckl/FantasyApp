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

    // EFFECTS: sets up window for view league panel
    public ViewLeaguePanel(League league) {
        this.league = league;

        viewLeagueFrame = new JFrame("League");
        viewLeagueFrame.setSize(FantasyAppUI.WIDTH, FantasyAppUI.HEIGHT);
        viewLeagueFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
    private void createViewLeaguePanel() {
        viewLeaguePanel = new JPanel();
        viewLeaguePanel.setLayout(new GridLayout(6, 1, 0, 0));

        JLabel homeIcon = new JLabel();
        homeIcon.setIcon(new ImageIcon("data/nbaimage.png"));
        homeIcon.setSize(new Dimension(50, 100));
        viewLeaguePanel.add(homeIcon);
        homeIcon.setHorizontalAlignment(JLabel.CENTER);

        viewLeaguePanel.setBackground(Color.white);
        viewLeaguePanel.setBorder(BorderFactory.createLineBorder(Color.lightGray, 10));

        viewLeagueFrame.add(viewLeaguePanel);

    }

    // MODIFIES: this
    // EFFECTS: displays list of teams registered to league and add to view team panel
    private void displayTeamsList() {
        teamNames = new JLabel();
        if (league.getTeamNames().isEmpty()) {
            teamNames.setText("<html><h3 style=\"color: #3988cf\">There are no teams registered.</h3></html>");
        } else {
            teamNames.setText("<html><h3 style=\"color: #3988cf\">Registered Teams:</h3><br/> "
                    + league.getTeamNames() + "</html>");
        }
        viewLeaguePanel.add(teamNames);
        teamNames.setHorizontalAlignment(JLabel.CENTER);

    }

    // MODIFIES: this
    // EFFECTS: create add team button and add to panel
    private void createAddTeamButton() {
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
                }
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: create remove team button and add to panel
    private void createRemoveTeamButton() {
        JButton removeTeamButton = new JButton("Remove Team");
        viewLeaguePanel.add(removeTeamButton);
        removeTeamButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (league.getTeamNames().isEmpty()) {
                    JOptionPane.showMessageDialog(null,
                            "There are no teams to remove.", "Message",
                            JOptionPane.PLAIN_MESSAGE);
                }
            }
        });
    }


    // MODIFIES: this
    // EFFECTS: create select button and add to view team panel
    private void createSelectButton() {
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
    // EFFECTS: create back button and add to panel
    private void createBackButton() {
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
        String addTeamName = JOptionPane.showInputDialog("Enter Team Name");
        if ((addTeamName != null) && (addTeamName.length() > 0)) {
            registerTeam(addTeamName);
        }
    }

    // MODIFIES: this
    // EFFECTS: adds a new team to the league unless team name has already been used
    private void registerTeam(String addTeamName) {
        Team newTeam = new Team(addTeamName);
        if (league.registerTeam(newTeam)) {
            JOptionPane.showMessageDialog(null, addTeamName + " has been successfully registered.",
                    "Add Team", JOptionPane.PLAIN_MESSAGE);
            viewLeagueFrame.setVisible(false);
            new ViewLeaguePanel(league);
            revalidate();
        } else {
            JOptionPane.showMessageDialog(null,
                    addTeamName + " has already been used. Please choose a different team name.", "Team Name Used",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

// not fully implemented as not required

//    // REQUIRES: input to be a non-empty string
//    // EFFECTS: create the remove team pop up for user to input team name
//    private void removeTeamPopUp() {
//        String removeTeamName = JOptionPane.showInputDialog("Which team would you like to remove?");
//        if ((removeTeamName != null) && (removeTeamName.length() > 0)) {
//            removeTeam(removeTeamName);
//        }
//    }
//

//    // MODIFIES: this
//    // EFFECTS: removes a team from the league
//    private void removeTeam(String removeTeamName) {
//        if (league.removeTeam(removeTeamName)) {
//            JOptionPane.showMessageDialog(null, removeTeamName + " has been successfully removed.",
//                    "Remove Team", JOptionPane.PLAIN_MESSAGE);
//            viewLeagueFrame.setVisible(false);
//            new ViewLeaguePanel(league);
//            revalidate();
//        } else {
//            JOptionPane.showMessageDialog(null,
//                    removeTeamName + " can not be found.", "Team not found",
//                    JOptionPane.WARNING_MESSAGE);
//        }
//    }

    // REQUIRES: input to be a non-empty string
    // EFFECTS: create the select team pop up for user to input team name
    private void selectTeamPopUp() {
        String selectedTeam = JOptionPane.showInputDialog("Which team would you like to select?");
        if ((selectedTeam != null) && (selectedTeam.length() > 0)) {
            int indexPositionOfTeam = league.getTeamNames().indexOf(selectedTeam);
            getSelectedTeam(indexPositionOfTeam);
        }
    }

    // EFFECTS: gets the selected team based on user's input
    private void getSelectedTeam(int indexPositionOfTeam) {
        if (!(indexPositionOfTeam == -1)) {
            Team selectedTeam = league.getTeam(indexPositionOfTeam);
            viewLeagueFrame.setVisible(false);
            new ViewTeamPanel(selectedTeam);
            revalidate();
        } else {
            JOptionPane.showMessageDialog(null,
                    "Team can not be found.", "Team not found",
                    JOptionPane.WARNING_MESSAGE);
        }
    }
}

