package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MyFrame extends JFrame {
	JPanel body;

	MyFrame(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout(50, 0));
		this.setSize(700, 700);
		
		
		JPanel head = new JPanel(new BorderLayout());
        head.setPreferredSize(new Dimension(500, 50));
        JButton btnMain = new JButton("MAIN MENU");
        btnMain.setHorizontalAlignment(JButton.CENTER);
        btnMain.setBorderPainted(false);
        btnMain.setContentAreaFilled(false);
        btnMain.setFocusPainted(false);
        btnMain.setOpaque(false);
        
        head.add(btnMain);
        btnMain.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	MyFrame.this.dispose();
                new MainMenu();
            }
        });
        
        JPanel body = new JPanel(new GridLayout(8, 1, 0, 5));
        body.setPreferredSize(new Dimension(300, 300));
        body.setBackground(Color.lightGray);
        this.body = body;

        JPanel foot = new JPanel(new BorderLayout());
        foot.setPreferredSize(new Dimension(500, 50));
        JLabel labelFoot = new JLabel("STIMA Keren");
        labelFoot.setHorizontalAlignment(JLabel.CENTER);
        foot.add(labelFoot);
        
        JPanel left = new JPanel(new BorderLayout());
        left.setPreferredSize(new Dimension(50, 100));
        JPanel right = new JPanel(new BorderLayout());
        right.setPreferredSize(new Dimension(50, 100));
        
        this.add(head, BorderLayout.NORTH);
        this.add(body, BorderLayout.CENTER);
        this.add(foot, BorderLayout.SOUTH);
        this.add(left, BorderLayout.WEST);
        this.add(right, BorderLayout.EAST);
	
		this.setVisible(true);
	}
}