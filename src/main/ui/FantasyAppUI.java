package ui;

import javax.swing.*;
import java.awt.*;

public class FantasyAppUI extends JFrame {
    private FantasyApp fa;
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 700;
    private JDesktopPane desktop;
    private JFrame frame;

    public FantasyAppUI() {
        super("NBA Fantasy Helper");
        initializeGraphics();
    }

    // MODIFIES: this
    // EFFECTS:  draws the JFrame window where this Fantasy App will operate
    private void initializeGraphics() {
        setSize(WIDTH, HEIGHT);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }
}
