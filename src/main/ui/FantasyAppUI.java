package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents the main frame for the fantasy application
public class FantasyAppUI extends JFrame implements ActionListener {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 500;
    private JDesktopPane desktop;
    private JFrame frame;
    private JMenuBar menuBar;

    // EFFECTS: sets up window for fantasy app
    public FantasyAppUI() {

        frame = new JFrame();
        desktop = new JDesktopPane();

        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(desktop);                     // adds the desktop to the frame
        frame.setTitle("NBA Fantasy Helper");

        createMenuBar();

        frame.setVisible(true);
    }

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

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
