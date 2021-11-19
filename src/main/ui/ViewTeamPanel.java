package ui;

import model.Player;
import model.Team;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents the frame for the view team panel

public class ViewTeamPanel extends JFrame {
    private JFrame viewTeamFrame;
    private JPanel viewTeamPanel;
    private Team roster;
    private Player selectedPlayer;
    private JLabel playerNames;
    private JLabel playerStats;

    // EFFECTS: sets up window for view team panel
    public ViewTeamPanel(Team roster) {
        this.roster = roster;

        viewTeamFrame = new JFrame("Team");
        viewTeamFrame.setSize(1000, FantasyAppUI.HEIGHT);
        viewTeamFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        viewTeamFrame.setLocationRelativeTo(null);

        createViewLeaguePanel();
        createAddPlayerButton();
        createRemovePlayerButton();
        createSelectButton();
        createBackButton();
        displayPlayersList();
        displayStatsSection();

        viewTeamFrame.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: create and setup view team panel
    private void createViewLeaguePanel() {
        viewTeamPanel = new JPanel();
        viewTeamPanel.setLayout(new GridLayout(4, 2, 0, 0));

        viewTeamPanel.setBackground(Color.white);
        viewTeamPanel.setBorder(BorderFactory.createLineBorder(Color.lightGray, 10));

        viewTeamFrame.add(viewTeamPanel);

    }

    // MODIFIES: this
    // EFFECTS: displays list of players on the selected team and add to view team panel
    private void displayPlayersList() {
        playerNames = new JLabel();
        if (roster.getPlayerNames().isEmpty()) {
            playerNames.setText("<html><h3 style=\"color: #3988cf\">There are no players on this team.</h3></html>");
        } else {
            playerNames.setText("<html><h3 style=\"color: #3988cf\">Players:</h3><br/ "
                    + roster.getPlayerNames() + "</html>");
        }
        viewTeamPanel.add(playerNames);
        playerNames.setHorizontalAlignment(JLabel.CENTER);
    }

    // MODIFIES: this
    // EFFECTS: create add player button and add to panel
    private void createAddPlayerButton() {
        JButton addTeamButton = new JButton("Add Player");
        viewTeamPanel.add(addTeamButton);
        addTeamButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (roster.isFull()) {
                    JOptionPane.showMessageDialog(null,
                            "This team is full.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    addPlayerPopUp();
                }
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: create remove player button and add to panel
    private void createRemovePlayerButton() {
        JButton removeTeamButton = new JButton("Remove Player");
        viewTeamPanel.add(removeTeamButton);
        removeTeamButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (roster.getPlayerNames().isEmpty()) {
                    JOptionPane.showMessageDialog(null,
                            "There are no players to remove.", "Message",
                            JOptionPane.PLAIN_MESSAGE);
                } else {
                    //  removePlayerPopUp();
                }
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: create select button and add to panel
    private void createSelectButton() {
        JButton selectButton = new JButton("Select Player");
        viewTeamPanel.add(selectButton);
        selectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (roster.getPlayerNames().isEmpty()) {
                    JOptionPane.showMessageDialog(null,
                            "There are no players to select.", "Message",
                            JOptionPane.PLAIN_MESSAGE);
                } else {
                    selectPlayerPopUp();
                }
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: create back button and add to panel
    private void createBackButton() {
        JButton backButton = new JButton("Back");
        viewTeamPanel.add(backButton);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewTeamFrame.dispose();

            }
        });
    }

    // MODIFIES: this
    // EFFECTS: creates section to display player statistics and add to panel
    private void displayStatsSection() {
        playerStats = new JLabel();
        viewTeamPanel.add(playerStats);
        playerStats.setHorizontalAlignment(JLabel.CENTER);
    }

    // REQUIRES: input to be a non-empty string
    // EFFECTS: create the add player pop up for user to input player information
    private void addPlayerPopUp() {
        JTextField nameField = new JTextField();
        JTextField fgField = new JTextField();
        JTextField ftField = new JTextField();
        JTextField ptField = new JTextField();
        JTextField rebField = new JTextField();
        JTextField astField = new JTextField() {
        };
        Object[] message = {
                "Player Name:", nameField, "Field Goal %:", fgField, "Free Throw %:",
                ftField, "Points:", ptField, "Rebounds:", rebField, "Assists:", astField
        };
        parseStatsInput(nameField, fgField, ftField, ptField, rebField, astField, message);
    }

    // REQUIRES: input to be a non-empty string
    // EFFECTS: takes the user input for statistics and convert them to the appropriate field types
    private void parseStatsInput(JTextField nameField, JTextField fgField, JTextField ftField,
                                 JTextField ptField, JTextField rebField, JTextField astField, Object[] message) {
        try {
            int option = JOptionPane.showConfirmDialog(null, message, "Add Player", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                String playerName = nameField.getText().toUpperCase();
                double fieldGoalPct = getDouble(fgField);
                double freeThrowPct = getDouble(ftField);
                double points = getDouble(ptField);
                double rebounds = getDouble(rebField);
                double assists = getDouble(astField);

                addPlayer(playerName, fieldGoalPct, freeThrowPct, points, rebounds, assists);
                viewTeamFrame.setVisible(false);
                new ViewTeamPanel(roster);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please fill in all the required fields.", "Error",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    // EFFECTS: converts a jtext field to a double and returns double
    private double getDouble(JTextField jtext) {
        String temp = jtext.getText();
        return Double.parseDouble(temp);
    }

    // MODIFIES: this
    // EFFECTS: adds a new player to the team unless player is already in team
    private void addPlayer(String addPlayerName, double fg, double ft, double pt, double rb, double ast) {
        Player newPlayer = new Player(addPlayerName, roster);
        if (roster.addPlayer(newPlayer)) {
            setPlayerStats(newPlayer, fg, ft, pt, rb, ast);
            JOptionPane.showMessageDialog(null, addPlayerName + " has been successfully added to team.",
                    "Add Player", JOptionPane.PLAIN_MESSAGE);
            super.update(this.getGraphics());

        } else {
            JOptionPane.showMessageDialog(null,
                    "Add unsuccessful. " + addPlayerName + " is already on team.", "Player Already Added",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    // MODIFIES: this
    // EFFECTS: sets the player stats based on user input
    private void setPlayerStats(Player p, double fg, double ft, double pt, double rb, double ast) {
        p.setFieldGoalPct(fg);
        p.setFreeThrowPct(ft);
        p.setPoints(pt);
        p.setRebounds(rb);
        p.setAssists(ast);

    }

    // REQUIRES: input to be a non-empty string
    // EFFECTS: create the select player pop up for user to input player name
    private void selectPlayerPopUp() {
        try {
            String selectedPlayer = JOptionPane.showInputDialog("Which player would you like to select?").toUpperCase();
            int indexPositionOfTeam = roster.getPlayerNames().indexOf(selectedPlayer);
            getSelectedPlayer(indexPositionOfTeam);
        } catch (NullPointerException e) {
            // expected
        }
    }

    // EFFECTS: gets the selected player based on user's input
    private void getSelectedPlayer(int indexPositionOfTeam) {
        if (!(indexPositionOfTeam == -1)) {
            selectedPlayer = roster.getPlayer(indexPositionOfTeam);
            updatePlayerStatsSection(selectedPlayer);
        } else {
            JOptionPane.showMessageDialog(null,
                    "Player can not be found.", "Player not found",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    // MODIFIES: this
    // EFFECTS: updates the player stats section to display selected player's information
    private void updatePlayerStatsSection(Player p) {
        playerStats.setText("<html><h3 style=\"color: #3988cf\">Stats for " + p.toString() + ": </h3><br/>"
                + " FG% - " + p.getFieldGoalPct() + "<br/>"
                + " FT% - " + p.getFreeThrowPct() + "<br/>"
                + " PTS - " + p.getPoints() + "<br/>"
                + " REB - " + p.getAssists() + "<br/>"
                + " AST - " + p.getRebounds() + "<br/></html>");
        super.update(this.getGraphics());
    }

}
