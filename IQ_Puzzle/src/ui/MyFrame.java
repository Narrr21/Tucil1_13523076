package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MyFrame extends JFrame {
	JPanel body;

	MyFrame(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout(50, 0));
		this.setSize(700, 700);
		ImageIcon image = new ImageIcon("public/logo.jpeg");
		this.setIconImage(image.getImage());
		
		
		JPanel head = new JPanel(new BorderLayout());
        head.setPreferredSize(new Dimension(500, 50)); // Ukuran Tinggi di set 100px
        JButton btnMain = new JButton("MAIN MENU");
        btnMain.setHorizontalAlignment(JButton.CENTER);
        btnMain.setBorderPainted(false);       // Hilangkan border
        btnMain.setContentAreaFilled(false);   // Hilangkan background
        btnMain.setFocusPainted(false);        // Hilangkan efek fokus
        btnMain.setOpaque(false);              // Pastikan transparan
        
        head.add(btnMain);
        btnMain.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	MyFrame.this.dispose();
                new MainMenu();
            }
        });
//        JLabel labelHead = new JLabel("MAIN MENU");
//        labelHead.setHorizontalAlignment(JLabel.CENTER);
//        head.add(labelHead); // Menempatkan label di tengah
        
        // Membuat Body
        JPanel body = new JPanel(new GridLayout(8, 1, 0, 5));
        body.setPreferredSize(new Dimension(300, 300));
        body.setBackground(Color.lightGray);
        this.body = body;

        // Membuat Footer
        JPanel foot = new JPanel(new BorderLayout());
        foot.setPreferredSize(new Dimension(500, 50));
        JLabel labelFoot = new JLabel("Tukang Kayu dari JAVA");
        labelFoot.setHorizontalAlignment(JLabel.CENTER);
        foot.add(labelFoot);
        
        JPanel left = new JPanel(new BorderLayout());
        left.setPreferredSize(new Dimension(50, 100));
        JPanel right = new JPanel(new BorderLayout());
        right.setPreferredSize(new Dimension(50, 100));
        
        // Menambahkan panel-panel ke dalam frame dengan layout BorderLayout
        this.add(head, BorderLayout.NORTH);
        this.add(body, BorderLayout.CENTER);
        this.add(foot, BorderLayout.SOUTH);
        this.add(left, BorderLayout.WEST);
        this.add(right, BorderLayout.EAST);
	
		this.setVisible(true);
	}
}