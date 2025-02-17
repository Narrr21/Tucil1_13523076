package ui;

import java.awt.GridLayout;

import javax.swing.*;


public class MainMenu {
    public MainMenu(){
        MyFrame frame = new MyFrame();
        placeButton(frame.body, frame);
    }
    
    private static void placeButton(JPanel panel, JFrame frame) {
    	panel.setLayout(new GridLayout(0, 1));
        // Menambahkan komponen (contoh: JLabel) ke dalam panel
        JButton btnCustom = new JButton("Custom");
        btnCustom.setHorizontalAlignment(JButton.CENTER);
        panel.add(btnCustom);
        
        JButton btnKeluar = new JButton("Keluar");
        panel.add(btnKeluar);
        
        // Action Listeners untuk masing-masing tombol
        btnCustom.addActionListener(e -> {
            frame.dispose();
            //new InterfaceCustom();
        });
        
        btnKeluar.addActionListener(e -> System.exit(0));
    }
}