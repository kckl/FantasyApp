package ui;

import model.League;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents the settings window
public class SettingsPanel extends JFrame {
    private JFrame settingsFrame;
    private JPanel settingsPanel;
    private JLabel leagueInfo;
    private String newLeagueName;
    private int newLeagueSize;
    private League league;

    // EFFECTS: create the settings window for user to view/edit league settings
    public SettingsPanel(League league) {
        this.league = league;

        settingsFrame = new JFrame("Settings");
        settingsFrame.setSize(FantasyAppUI.WIDTH, FantasyAppUI.HEIGHT);
        settingsFrame.setLocationRelativeTo(null);

        createSettingsPanel();
        displaySettings();
        createEditButton();
        createBackButton();

        settingsFrame.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: create and setup settings panel
    private void createSettingsPanel() {
        settingsPanel = new JPanel();
        settingsPanel.setLayout(new GridLayout(4, 1, 0, 0));

        JLabel homeIcon = new JLabel();
        homeIcon.setIcon(new ImageIcon("data/nbaimage.png"));
        homeIcon.setSize(new Dimension(50, 100));
        settingsPanel.add(homeIcon);
        homeIcon.setHorizontalAlignment(JLabel.CENTER);

        settingsPanel.setBackground(Color.white);
        settingsPanel.setBorder(BorderFactory.createLineBorder(Color.lightGray, 10));

        settingsFrame.add(settingsPanel);

    }

    // EFFECTS: create text panel to display league settings
    private void displaySettings() {
        leagueInfo = new JLabel();
        leagueInfo.setText("<html><h3 style=\"color: #3988cf\"> League Name: "
                + league.toString() + "</h3></html>\"");

        leagueInfo.setText("<html><h3 style=\"color: #3988cf\">League Name: " + league.toString() + " </h3><br/>"
                + "<h3 style=\"color: #3988cf\">League Size: " + league.getLeagueSize() + "<br/></html>");

        settingsPanel.add(leagueInfo);
        leagueInfo.setHorizontalAlignment(JLabel.CENTER);
    }

    // MODIFIES: this
    // EFFECTS: create edit button and add to view team panel
    private void createEditButton() {
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
    private void createBackButton() {
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
        JTextField nameField = new JTextField();
        JTextField sizeField = new JTextField() {
        };
        Object[] message = {
                "League Name:", nameField,
                "League Size:", sizeField,
        };
        int option = JOptionPane.showConfirmDialog(null,
                message, "Edit League Settings", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            newLeagueName = nameField.getText();
            league.changeLeagueName(newLeagueName);

            String tempSize = sizeField.getText();
            newLeagueSize = Integer.parseInt(tempSize);
            league.changeLeagueSize(newLeagueSize);

            settingsFrame.setVisible(false);
            new SettingsPanel(league);
            revalidate();
        }
    }

}
