package ui;

import javax.swing.*;
import java.awt.*;

public class FantasyAppUI extends JPanel {
    private FantasyApp fa;
    private static final int WIDTH = 100;
    private static final int HEIGHT = 30;
    private JDesktopPane desktop;

    public FantasyAppUI() {
        fa = new FantasyApp();
        desktop = new JDesktopPane();
        setLayout(new BorderLayout());

        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.GRAY);
        setVisible(true);

    }

}
