package ui;

import model.League;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

// Represents the main frame for the fantasy app
public class FantasyAppUI extends JFrame {
    private static final String JSON_STORE = "./data/league.json";
    public static final int WIDTH = 450;
    public static final int HEIGHT = 500;
    private JFrame frame;
    private JPanel homePanel;
    private JMenuBar menuBar;
    private League league;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: sets up window for fantasy app
    public FantasyAppUI() {
        league = new League("NBA 2k21 Fantasy", 10);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        frame = new JFrame("NBA Fantasy Helper Application");
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        createMenuBar();
        createHomePanel();
        createAddTeamButton();
        createViewLeagueButton();
        createSettingsButton();

        frame.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: creates a menu bar with menu items
    public void createMenuBar() {
        menuBar = new JMenuBar();

        JMenu saveLoadMenu = new JMenu("Save/Load");
        menuBar.add(saveLoadMenu);

        JMenuItem saveMenuItem = new JMenuItem("Save");
        saveLoadMenu.add(saveMenuItem);
        saveMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveLeague();
            }
        });

        JMenuItem loadMenuItem = new JMenuItem("Load");
        saveLoadMenu.add(loadMenuItem);
        loadMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadLeague();
            }
        });

        frame.setJMenuBar(menuBar);
    }


    // MODIFIES: this
    // EFFECTS: create and setup home panel
    public void createHomePanel() {
        homePanel = new JPanel();
        homePanel.setLayout(new BorderLayout());

        JLabel homeIcon = new JLabel();
        homeIcon.setIcon(new ImageIcon("data/nbaimage.png"));
        homeIcon.setSize(new Dimension(50, 100));
        homePanel.add(homeIcon, BorderLayout.CENTER);

        homePanel.setBackground(Color.white);
        homePanel.setBorder(BorderFactory.createLineBorder(Color.darkGray, 10));


        frame.add(homePanel);
    }

    // MODIFIES: this
    // EFFECTS: create add team button and add to home panel
    public void createAddTeamButton() {
        JButton addTeamButton = new JButton("Add Team");
        homePanel.add(addTeamButton, "East");
        addTeamButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddTeamPopUp(league);
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: create view league button and add to home panel
    public void createViewLeagueButton() {
        JButton viewButton = new JButton("View League");
        homePanel.add(viewButton, "Center");
        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ViewTeamFrame(league);
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: create settings button and add to home panel
    public void createSettingsButton() {
        JButton settingsButton = new JButton("Settings");
        homePanel.add(settingsButton, "West");
    }

    // This method references code from this repo
    // Link: [https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git]
    // MODIFIES: this
    // EFFECTS: loads league from file
    public void loadLeague() {
        try {
            league = jsonReader.read();
            JOptionPane.showMessageDialog(null,
                    "Loaded " + league.getLeagueName() + " from " + JSON_STORE,
                    "Load File", JOptionPane.PLAIN_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Unable to read from file: " + JSON_STORE,
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // This method references code from this repo
    // Link: [https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git]
    // EFFECTS: saves the league to file
    public void saveLeague() {
        try {
            jsonWriter.open();
            jsonWriter.write(league);
            jsonWriter.close();
            JOptionPane.showMessageDialog(null,
                    "Saved " + league.getLeagueName() + " to " + JSON_STORE,
                    "Save File", JOptionPane.PLAIN_MESSAGE);
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Unable to write to file: " + JSON_STORE,
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

