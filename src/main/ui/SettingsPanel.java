package ui;

import model.League;
import sun.jvm.hotspot.types.JIntField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents the settings window
public class SettingsPanel extends JFrame {
    private JFrame settingsFrame;
    private JPanel settingsPanel;
    private JLabel leagueName;
    private JLabel leagueSize;
    private String newLeagueName;
    private int newLeagueSize;
    private League league;

    // EFFECTS: create the settings window for user to view/edit league settings
    public SettingsPanel(League league) {
        this.league = league;

        settingsFrame = new JFrame("Settings");
        settingsFrame.setSize(FantasyAppUI.WIDTH, FantasyAppUI.HEIGHT);
        settingsFrame.setLocationRelativeTo(null);
        settingsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        createSettingsPanel();
        displaySettings();
        createEditButton();
        createBackButton();

        settingsFrame.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: create and setup settings panel
    public void createSettingsPanel() {
        settingsPanel = new JPanel();
        settingsPanel.setLayout(new GridLayout(4, 1, 0, 0));

        settingsPanel.setBackground(Color.white);
        settingsPanel.setBorder(BorderFactory.createLineBorder(Color.darkGray, 10));

        settingsFrame.add(settingsPanel);

    }

    // EFFECTS: create text panel to display league settings
    public void displaySettings() {
        leagueName = new JLabel();
        leagueName.setText("League Name: " + league.getLeagueName());

        leagueSize = new JLabel();
        leagueSize.setText("League Size: " + league.getLeagueSize());

        settingsPanel.add(leagueName);
        settingsPanel.add(leagueSize);
    }

    // MODIFIES: this
    // EFFECTS: create edit button and add to view team panel
    public void createEditButton() {
        JButton editButton = new JButton("Edit");
        settingsPanel.add(editButton);
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editLeaguePopUp();

            }
        });

    }

    // MODIFIES: this
    // EFFECTS: create back button and add to view team panel
    public void createBackButton() {
        JButton backButton = new JButton("Back");
        settingsPanel.add(backButton);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                settingsFrame.dispose();

            }
        });

    }

    // MODIFIES: this
    // EFFECTS: creates edit league pop up for user to input new league settings
    private void editLeaguePopUp() {
        // todo: requires size to be int
        JTextField nameField = new JTextField();
        JTextField sizeField = new JTextField() {
        };
        Object[] message = {
                "League Name:", nameField,
                "League Size:", sizeField,
        };
        int option = JOptionPane.showConfirmDialog(null,
                message, "Edit League Settings",
                JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            newLeagueName = nameField.getText();
            league.changeLeagueName(newLeagueName);

            String tempSize = sizeField.getText();
            newLeagueSize = Integer.parseInt(tempSize);
            league.changeLeagueSize(newLeagueSize);
        }
        pack();
    }

}
