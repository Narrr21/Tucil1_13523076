package ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;

import main.Solver;

public class MainMenu {
    public MainMenu() {
        MyFrame frame = new MyFrame();
        placeButton(frame.body, frame);
    }

    private static void placeButton(JPanel panel, JFrame frame) {
    	// Set vertical layout
    	panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

    	// Add top glue to center buttons vertically
    	panel.add(Box.createVerticalGlue());

    	// Create buttons with consistent style
    	JButton btnInput = createButton("📂", "Input");
    	JButton btnKeluar = createButton("🚪", "Keluar");

        panel.add(btnInput);
        panel.add(Box.createVerticalStrut(20));
        panel.add(btnKeluar);
        panel.add(Box.createVerticalGlue());

        // Input Action
        btnInput.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File("./text/input"));

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
    
	// Helper method to create styled buttons
	private static JButton createButton(String icon, String text) {
	    JButton button = new JButton("<html><div style='display: flex; align-items: center; justify-content: center;'>"
	            + "<span style='margin-right: 10px;'>" + icon + "</span>" // Icon on the left
	            + "<span style='flex-grow: 1; text-align: center;'>" + text + "</span>" // Centered text
	            + "</div></html>");
	    button.setPreferredSize(new Dimension(200, 40));
	    button.setAlignmentX(Component.CENTER_ALIGNMENT);
	    return button;
	}

	
    private static void showSolutionPopup(Solver solver) {
        String solution = solver.solution.result;
        char[][] boardState = solver.solution.board;
        long timeTaken = solver.timer.getTime();

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
                "<br><br><b>Time Taken:</b> " + (timeTaken / 1_000_000.0) + " ms" +
                "</div></html>";

        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setHorizontalAlignment(SwingConstants.CENTER);

        // Scroll pane for solution text
        JScrollPane scrollPane = new JScrollPane(label);
        scrollPane.setBorder(null);

        // Button to transform the board into an image puzzle
        JButton convertButton = new JButton("Show Image Puzzle");
        convertButton.setFont(new Font("Arial", Font.BOLD, 14));
        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showImagePuzzle(boardState);
            }
        });

        // Panel for button
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(convertButton);

        // Add components to the frame
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        popup.add(panel, BorderLayout.CENTER);

        // Show popup
        popup.setVisible(true);
    }
    
    private static void showImagePuzzle(char[][] board) {
        JFrame puzzleFrame = new JFrame("Image Puzzle");
        puzzleFrame.setSize(400, 400);
        puzzleFrame.setLocationRelativeTo(null);
        puzzleFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel gridPanel = new JPanel();
        int rows = board.length;
        int cols = board[0].length;
        gridPanel.setLayout(new GridLayout(rows, cols, 2, 2));

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
                label.setForeground(Color.WHITE); // Set text color to white
                label.setOpaque(true);
                label.setBackground(colorMap.getOrDefault(c, Color.BLACK)); // Default to black if not mapped
                gridPanel.add(label);
            }
        }

        puzzleFrame.add(gridPanel);
        puzzleFrame.setVisible(true);
    }
}
