package ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.*;

import main.Solver;

public class MainMenu {
    public MainMenu() {
        MyFrame frame = new MyFrame();
        placeButton(frame.body, frame);
    }

    private static void placeButton(JPanel panel, JFrame frame) {
    	panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

    	panel.add(Box.createVerticalGlue());

    	JButton btnInput = createButton("📂", "Input");
    	JButton btnKeluar = createButton("🚪", "Keluar");

        panel.add(btnInput);
        panel.add(Box.createVerticalStrut(20));
        panel.add(btnKeluar);
        panel.add(Box.createVerticalGlue());

        // Input Action
        btnInput.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File("./input"));

            int response = fileChooser.showOpenDialog(frame);
            if (response == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                if (selectedFile != null && selectedFile.exists()) {
                    try {
                        Solver solver = new Solver(selectedFile);
                        solver.solve();
                        showSolutionPopup(solver);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(frame, 
                            "Error processing the file: " + ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, 
                        "Invalid file selection.", 
                        "Warning", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        btnKeluar.addActionListener(e -> System.exit(0));
    }
    
	private static JButton createButton(String icon, String text) {
	    JButton button = new JButton("<html><div style='text-align: center;'>" + icon + " " + text + "</div></html>");
	    button.setPreferredSize(new Dimension(200, 40));
	    button.setAlignmentX(Component.CENTER_ALIGNMENT);
	    return button;
	}

	
    private static void showSolutionPopup(Solver solver) {
        String solution = solver.solution.result;
        double timeTaken = solver.timer.getTime();

        JFrame popup = new JFrame("Solution Result");
        popup.setSize(500, 400);
        popup.setLocationRelativeTo(null);
        popup.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        popup.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        String text = "<html><div style='text-align: center;'>" +
                "<b>Solution:</b><br>" + solution.replace("\n", "<br>") +
                "<br><br><b>Time Taken:</b> " + (timeTaken) + " ms" +
                "</div></html>";

        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setHorizontalAlignment(SwingConstants.CENTER);

        JScrollPane scrollPane = new JScrollPane(label);
        scrollPane.setBorder(null);

        JButton convertButton = new JButton("Show Image Puzzle");
        convertButton.setFont(new Font("Arial", Font.BOLD, 14));
        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showImagePuzzle(solver);
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(convertButton);

        // Add components
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        popup.add(panel, BorderLayout.CENTER);

        popup.setVisible(true);
    }
    
    private static void showImagePuzzle(Solver solver) {
        JFrame puzzleFrame = new JFrame("Image Puzzle");
        char[][] board = solver.puzzleData.Board;
        puzzleFrame.setSize(400, 400);
        puzzleFrame.setLocationRelativeTo(null);
        puzzleFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel gridPanel = new JPanel();
        int rows = board.length;
        int cols = board[0].length;
        gridPanel.setLayout(new GridLayout(rows, cols, 0, 0));

        Map<Character, Color> colorMap = new HashMap<>();
        Color[] colors = {
            new Color(255, 0, 0),      // A - Red
            new Color(0, 0, 255),      // B - Blue
            new Color(0, 128, 0),      // C - Green
            new Color(255, 165, 0),    // D - Orange
            new Color(255, 0, 255),    // E - Magenta
            new Color(128, 0, 128),    // F - Purple
            new Color(0, 255, 255),    // G - Cyan
            new Color(139, 69, 19),    // H - Brown
            new Color(255, 192, 203),  // I - Pink
            new Color(128, 128, 0),    // J - Olive
            new Color(255, 223, 0),    // K - Gold
            new Color(64, 224, 208),   // L - Turquoise
            new Color(128, 0, 0),      // M - Maroon
            new Color(0, 255, 127),    // N - Spring Green
            new Color(255, 99, 71),    // O - Tomato
            new Color(70, 130, 180),   // P - Steel Blue
            new Color(210, 105, 30),   // Q - Chocolate
            new Color(0, 139, 139),    // R - Dark Cyan
            new Color(184, 134, 11),   // S - Dark Goldenrod
            new Color(154, 205, 50),   // T - Yellow Green
            new Color(75, 0, 130),     // U - Indigo
            new Color(255, 140, 0),    // V - Dark Orange
            new Color(0, 128, 128),    // W - Teal
            new Color(123, 104, 238),  // X - Medium Slate Blue
            new Color(199, 21, 133),   // Y - Medium Violet Red
            new Color(47, 79, 79)      // Z - Dark Slate Gray
        };
        
        char letter = 'A';
        for (Color color : colors) {
            colorMap.put(letter, color);
            letter++;
        }

        for (char[] row : board) {
            for (char c : row) {
                JLabel label = new JLabel(String.valueOf(c), SwingConstants.CENTER);
                label.setFont(new Font("Arial", Font.BOLD, 24));
                label.setForeground(Color.WHITE);
                label.setOpaque(true);
                label.setBackground(colorMap.getOrDefault(c, Color.BLACK)); // Default black
                gridPanel.add(label);
            }
        }

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(e -> {
            String filename;
            File file;
            
            do {
                filename = JOptionPane.showInputDialog("Enter file name:");
                if (filename == null || filename.trim().isEmpty()) {
                    return;
                }
                file = new File("./test/" + filename + ".jpg");
                
                if (file.exists()) {
                    JOptionPane.showMessageDialog(null, "File already exists. Please enter a different name.", "Warning", JOptionPane.WARNING_MESSAGE);
                }
            } while (file.exists());
            
            saveImage(gridPanel, filename + ".jpg");
            saveText(solver, filename + ".txt");
        });
        
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(gridPanel, BorderLayout.CENTER);
        mainPanel.add(saveButton, BorderLayout.SOUTH);
        
        puzzleFrame.add(mainPanel);
        puzzleFrame.setVisible(true);
    }

    private static void saveImage(JPanel panel, String filename) {
        BufferedImage image = new BufferedImage(panel.getWidth(), panel.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = image.createGraphics();
        panel.paint(g2d);
        g2d.dispose();
        try {
            ImageIO.write(image, "jpg", new File("./test/" + filename));
            System.out.println("Image saved as " + filename);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static void saveText(Solver solver, String filename) {
        char[][] board = solver.solution.board;
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("./test/" + filename))) {
            for (char[] row : board) {
                writer.write(new String(row));
                writer.newLine();
            }
            writer.write("Attempt taken : " + solver.puzzleData.Attempt);
            writer.newLine();
            writer.write("Time taken : " + solver.timer.getTime());
            System.out.println("Text saved as " + filename);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
