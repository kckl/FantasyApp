package ui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

// Represents the main frame for the fantasy app
public class FantasyAppUI extends JFrame {
    private static final int WIDTH = 450;
    private static final int HEIGHT = 500;
    private JFrame frame;
    private JPanel homePanel;
    private JMenuBar menuBar;

    // EFFECTS: sets up window for fantasy app
    public FantasyAppUI() {
        frame = new JFrame("NBA Fantasy Helper Application");
        frame.setSize(WIDTH, HEIGHT);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        createMenuBar();
        createHomePanel();
        createStartButton();
        createSettingsButton();


        frame.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: creates a menu bar with menu items
    private void createMenuBar() {
        menuBar = new JMenuBar();

        JMenu saveLoadMenu = new JMenu("Save/Load");
        menuBar.add(saveLoadMenu);

        JMenuItem saveMenuItem = new JMenuItem("Save");
        saveLoadMenu.add(saveMenuItem);

        JMenuItem loadMenuItem = new JMenuItem("Load");
        saveLoadMenu.add(loadMenuItem);

        JMenu settingsMenu = new JMenu("Settings");
        menuBar.add(settingsMenu);

        JMenuItem editMenuItem = new JMenuItem("Edit");
        settingsMenu.add(editMenuItem);

        frame.setJMenuBar(menuBar);
    }

    // MODIFIES: this
    // EFFECTS: create and setup home panel
    public void createHomePanel() {
        homePanel = new JPanel();
        homePanel.setLayout(new BorderLayout());

        JLabel homeIcon = new JLabel();
        homeIcon.setIcon(new ImageIcon("data/nbaimage.png"));
        homeIcon.setSize(new Dimension(50,100));
        homePanel.add(homeIcon, BorderLayout.CENTER);

        homePanel.setBackground(Color.white);
        homePanel.setBorder(BorderFactory.createLineBorder(Color.darkGray, 10));


        frame.add(homePanel);
    }

    // MODIFIES: this
    // EFFECTS: create start button and add to home panel
    public void createStartButton() {
        JButton startButton = new JButton("Start");
        homePanel.add(startButton, "East");
    }

    // MODIFIES: this
    // EFFECTS: create settings button and add to home panel
    public void createSettingsButton() {
        JButton settingsButton = new JButton("Settings");
        homePanel.add(settingsButton, "West");
    }

}

