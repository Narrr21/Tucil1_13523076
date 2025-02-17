package ui;

import java.awt.*;
import java.io.File;
import javax.swing.*;

import main.Solver;

public class MainMenu {
    public MainMenu(){
        MyFrame frame = new MyFrame();
        placeButton(frame.body, frame);
    }
    
    private static void placeButton(JPanel panel, JFrame frame) {
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        panel.add(Box.createVerticalGlue());

        JButton btnInput = new JButton("Input Text");
        JButton btnKeluar = new JButton("Keluar");

        Dimension buttonSize = new Dimension(400, 40);
        btnInput.setPreferredSize(buttonSize);
        btnKeluar.setPreferredSize(buttonSize);
        
        btnInput.setMaximumSize(buttonSize); 
        btnKeluar.setMaximumSize(buttonSize);

        btnInput.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnKeluar.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(btnInput);
        panel.add(Box.createVerticalStrut(20)); // Space
        panel.add(btnKeluar);
        
        panel.add(Box.createVerticalGlue());

        // Input
        btnInput.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File(".")); 
            
            int response = fileChooser.showOpenDialog(null);
            if (response == JFileChooser.APPROVE_OPTION) {
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                // Debug
                System.out.println("File Selected: " + file.getName());

                Solver solver = new Solver(file);
                
                // Debug
                solver.printPuzzle();
                
                // Solve
                solver.solve();

                // Debug
                solver.printSolution();
            }
        });

        // Exit
        btnKeluar.addActionListener(e -> System.exit(0));
    }
}
