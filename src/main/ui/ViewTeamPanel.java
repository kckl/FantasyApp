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
        viewTeamFrame.setSize(800, FantasyAppUI.HEIGHT);
        viewTeamFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        viewTeamFrame.setLocationRelativeTo(null);

        createViewLeaguePanel();
        displayPlayersList();
        createAddPlayerButton();
        createRemovePlayerButton();
        createSelectButton();
        createBackButton();
        displayStatsSection();

        viewTeamFrame.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: create and setup view team panel
    public void createViewLeaguePanel() {
        viewTeamPanel = new JPanel();
        viewTeamPanel.setLayout(new GridLayout(5, 2, 0, 0));

        viewTeamPanel.setBackground(Color.white);
        viewTeamPanel.setBorder(BorderFactory.createLineBorder(Color.darkGray, 10));

        viewTeamFrame.add(viewTeamPanel);

    }

    // MODIFIES: this
    // EFFECTS: displays list of players on the selected team and add to view team panel
    public void displayPlayersList() {
        playerNames = new JLabel();
        if (roster.getPlayerNames().isEmpty()) {
            playerNames.setText("There are no players on this team.");
        } else {
            playerNames.setText("Players: " + roster.getPlayerNames());
        }
        viewTeamPanel.add(playerNames);
    }

    // MODIFIES: this
    // EFFECTS: create add player button and add to panel
    public void createAddPlayerButton() {
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
    public void createRemovePlayerButton() {
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
    public void displayStatsSection() {
        playerStats = new JLabel();
        viewTeamPanel.add(playerStats);
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
        int option = JOptionPane.showConfirmDialog(null, message, "Add Player", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String playerName = nameField.getText().toUpperCase();


            double fieldGoalPct = getDouble(fgField);
            double freeThrowPct = getDouble(ftField);
            double points = getDouble(ptField);
            double rebounds = getDouble(rebField);
            double assists = getDouble(astField);

            addPlayer(playerName, fieldGoalPct, freeThrowPct, points,rebounds, assists);
            viewTeamFrame.setVisible(false);
            new ViewTeamPanel(roster);
            revalidate();

        }
    }

    // EFFECTS: converts a jtext field to a double and returns double
    private double getDouble(JTextField jtext) {
        String temp = jtext.getText();
        return Double.parseDouble(temp);
    }

    // MODIFIES: this
    // EFFECTS: adds a new player to the team unless player is already in team
    public void addPlayer(String addPlayerName, double fg, double ft, double pt, double rb, double ast) {
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
        String selectedPlayer = JOptionPane.showInputDialog("Which player would you like to select?").toUpperCase();
        if ((selectedPlayer != null) && (selectedPlayer.length() > 0)) {
            int indexPositionOfTeam = roster.getPlayerNames().indexOf(selectedPlayer);
            getSelectedPlayer(indexPositionOfTeam);
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
    public void updatePlayerStatsSection(Player p) {
        playerStats.setText("Stats for " + p.getPlayerName()
                + "FG% - " + p.getFieldGoalPct()
                + "FT% - " + p.getFreeThrowPct()
                + "PTS - " + p.getPoints()
                + "REB - " + p.getAssists()
                + "AST - " + p.getRebounds());
        super.update(this.getGraphics());
    }

}
