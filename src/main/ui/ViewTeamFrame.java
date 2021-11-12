package ui;

import model.League;
import model.Team;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents the frame for the view team panel
public class ViewTeamFrame extends JFrame {
    private JFrame frame;
    private JPanel viewTeamPanel;
    private League league;

    // EFFECTS: sets up window for view team panel
    public ViewTeamFrame(League league) {
        this.league = league;

        frame = new JFrame("Registered Teams");
        frame.setLayout(new BorderLayout());
        frame.setSize(FantasyAppUI.WIDTH, FantasyAppUI.HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        createViewTeamPanel();
        createTeamsList();
        createSelectButton();
        createBackButton();

        frame.setVisible(true);
    }



    // MODIFIES: this
    // EFFECTS: create and setup view team panel
    public void createViewTeamPanel() {
        viewTeamPanel = new JPanel();
        viewTeamPanel.setLayout(new BoxLayout(viewTeamPanel, BoxLayout.Y_AXIS));

        viewTeamPanel.setBackground(Color.white);
        viewTeamPanel.setBorder(BorderFactory.createLineBorder(Color.darkGray, 10));

        frame.add(viewTeamPanel);

    }

    // MODIFIES: this
    // EFFECTS: create the list of teams registered to league and add to view team panel
    public void createTeamsList() {
        //todo
    }

    // MODIFIES: this
    // EFFECTS: create select button and add to view team panel
    public void createSelectButton() {
        JButton selectButton = new JButton("Select");
        viewTeamPanel.add(selectButton);
    }

    // MODIFIES: this
    // EFFECTS: create back button and add to view team panel
    public void createBackButton() {
        JButton backButton = new JButton("Back");
        viewTeamPanel.add(backButton);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
         // todo
            }
        });

    }
}
