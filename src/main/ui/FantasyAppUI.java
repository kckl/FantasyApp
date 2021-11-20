package ui;

import model.Event;
import model.EventLog;
import model.League;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

// Represents the main frame for the fantasy app
public class FantasyAppUI extends JFrame {
    private static final String JSON_STORE = "./data/league.json";
    public static final int WIDTH = 450;
    public static final int HEIGHT = 600;
    protected JFrame mainFrame;
    private JPanel homePanel;
    private JLabel welcome;
    private JMenuBar menuBar;
    private League league;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: sets up window for fantasy app
    public FantasyAppUI() {
        league = new League("NBA 2k21 Fantasy", 10);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        mainFrame = new JFrame("NBA Fantasy Helper Application");
        mainFrame.setSize(WIDTH, HEIGHT);
        mainFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                printLog(EventLog.getInstance());
                mainFrame.setVisible(false);
                mainFrame.dispose();
            }
        });

        createMenuBar();
        createHomePanel();
        welcomeMessage();
        createViewLeagueButton();
        createSettingsButton();

        mainFrame.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: creates a menu bar with menu items and adds to frame
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

        mainFrame.setJMenuBar(menuBar);
    }


    // MODIFIES: this
    // EFFECTS: create and setup home panel
    public void createHomePanel() {
        homePanel = new JPanel();
        homePanel.setLayout(new GridLayout(4,1,0,0));


        JLabel homeIcon = new JLabel();
        homeIcon.setIcon(new ImageIcon("data/nbaimage.png"));
        homeIcon.setSize(new Dimension(50, 100));
        homePanel.add(homeIcon);
        homeIcon.setHorizontalAlignment(JLabel.CENTER);

        homePanel.setBackground(Color.white);
        homePanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 10));

        mainFrame.add(homePanel);
    }

    // MODIFIES: this
    // EFFECTS: displays list of teams registered to league and add to view team panel
    private void welcomeMessage() {
        welcome = new JLabel();
        welcome.setText("<html><h3 style=\"color: #3988cf\">WELCOME TO THE NBA FANTASY HELPER!</h3></html>");

        homePanel.add(welcome);
        welcome.setHorizontalAlignment(JLabel.CENTER);
    }

        // MODIFIES: this
    // EFFECTS: create view league button and add to home panel
    public void createViewLeagueButton() {
        JButton viewButton = new JButton("View League");
        viewButton.setPreferredSize(new Dimension(5, 5));
        homePanel.add(viewButton);
        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ViewLeaguePanel(league);
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: create settings button and add to home panel
    public void createSettingsButton() {
        JButton settingsButton = new JButton("Settings");
        settingsButton.setPreferredSize(new Dimension(900,900));
        settingsButton.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        homePanel.add(settingsButton);
        settingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SettingsPanel(league);
            }
        });
    }

    // This method references code from this repo
    // Link: [https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git]
    // MODIFIES: this
    // EFFECTS: loads league from file
    public void loadLeague() {
        try {
            league = jsonReader.read();
            JOptionPane.showMessageDialog(null,
                    "Loaded " + league.toString() + ".",
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
                    "Saved " + league.toString() + ".",
                    "Save File", JOptionPane.PLAIN_MESSAGE);
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Unable to write to file: " + JSON_STORE,
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // EFFECTS: prints out the event log
    public void printLog(EventLog el)  {
        for (Event next : el) {
            System.out.println(next.toString() + "\n");
        }
    }
}

