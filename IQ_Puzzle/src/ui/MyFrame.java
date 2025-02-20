package ui;

import java.awt.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class MyFrame extends JFrame {
    JPanel body;

    public MyFrame() {
        // Frame setup
        setTitle("My Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(30, 0));
        setSize(500, 400);

        // Header Panel
        JPanel head = new JPanel(new BorderLayout());
        head.setPreferredSize(new Dimension(500, 50));

        JButton btnMain = new JButton("MAIN MENU");
        btnMain.setBorderPainted(false);
        btnMain.setContentAreaFilled(false);
        btnMain.setFocusPainted(false);
        btnMain.setOpaque(false);
        btnMain.addActionListener(e -> {
            dispose();
            new MainMenu();
        });

        head.add(btnMain, BorderLayout.CENTER);

        // Body Panel
        this.body = new JPanel(new GridLayout(2, 1, 0, 2));
        body.setPreferredSize(new Dimension(400, 300));
        body.setBackground(Color.LIGHT_GRAY);

        // Footer Panel
        JPanel foot = new JPanel(new BorderLayout());
        foot.setPreferredSize(new Dimension(500, 50));

        JLabel labelFoot = new JLabel("STIMA Keren", SwingConstants.CENTER);
        foot.add(labelFoot, BorderLayout.CENTER);

        // Side Panels (for spacing)
        JPanel left = new JPanel();
        left.setPreferredSize(new Dimension(50, 100));

        JPanel right = new JPanel();
        right.setPreferredSize(new Dimension(50, 100));

        // Adding components to frame
        add(head, BorderLayout.NORTH);
        add(body, BorderLayout.CENTER);
        add(foot, BorderLayout.SOUTH);
        add(left, BorderLayout.WEST);
        add(right, BorderLayout.EAST);

        setVisible(true);
    }
}
