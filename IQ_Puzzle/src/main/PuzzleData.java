package main;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class PuzzleData {
    public int Wide, Length, Puzzle; // Store board dimensions and shape count
    public String State; // Store state
    public List<Shape> arrayOfShapes; // Store puzzle shapes
    public List<Shape> availShape;
    public char[][] Board;
    public int Attempt;
    
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

    public PuzzleData(int N, int M, int P, String S, List<Shape> arrayOfShapes) {
        this.Wide = M;
        this.Length = N;
        this.Puzzle = P;
        this.State = S;
        this.arrayOfShapes = arrayOfShapes;
        this.availShape = arrayOfShapes;
        this.Board = new char[M][N];
        this.Attempt = 0;
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                Board[i][j] = ' ';
            }
        }
        char letter = 'A';
        for (Color color : colors) {
            colorMap.put(letter, color);
            letter++;
        }
    }
    
    public void printBoard() {
        final String RESET = "\u001B[0m";
        final String BLACK_BACKGROUND = "\u001B[40m";
        
        // Debug
        System.out.println("Board :");
        for (char[] row : Board) {
            for (char cell : row) {
                if (cell == ' ') {
                    System.out.print(BLACK_BACKGROUND + "   " + RESET);
                } else {
                    Color color = colorMap.get(cell);
                    if (color != null) {
                        String ANSI_COLOR = String.format("\u001B[38;2;%d;%d;%dm", 
                            color.getRed(), color.getGreen(), color.getBlue());
                        System.out.print(ANSI_COLOR + " " + cell + " " + RESET);
                    } else {
                        System.out.print(" " + cell + " ");
                    }
                }
            }
            System.out.println();
        }
    }
    
    public Boolean solving(int x, int y) {
//        System.out.println("Central X : " + x + " Y : " + y);
        if (isBoardSolved()) {
        	System.out.println("Done");
        	return true;
        }
        if (y >= Length) return solving(x + 1, 0);
        if (x >= Wide) return false;
        if (Board[x][y] != ' ') return solving(x, y + 1);

        for (Shape shape : new ArrayList<>(availShape)) {
            SetShape sets = new SetShape(shape);
            Iterator<Shape> iterator = sets.availSet.iterator();

            while (iterator.hasNext()) {
                Shape set = iterator.next();
                Attempt++;
                if (isValidSet(set, x, y)) {
//	                printBoard();
                    availShape.remove(shape);
                    if (solving(x, y + 1)) {
                        return true;
                    }
                    removeShape(set);
                    availShape.add(shape);
                }
            }
        }
        return false;
    }
    
    public Boolean isValidSet(Shape set, int x, int y) {
        int shiftX = 0, shiftY = 0;
        
        for (int[] dot : set.dots) {
            int newX = x + dot[0];
            int newY = y + dot[1];

            if (newX < 0) shiftX = Math.max(shiftX, -newX);
            if (newY < 0) shiftY = Math.max(shiftY, -newY);
            if (newX >= Wide) shiftX = Math.min(shiftX, Wide - 1 - newX);
            if (newY >= Length) shiftY = Math.min(shiftY, Length - 1 - newY);
        }

        int adjustedX = x + shiftX;
        int adjustedY = y + shiftY;

        for (int[] dot : set.dots) {
            int newX = adjustedX + dot[0];
            int newY = adjustedY + dot[1];

            if (newX < 0 || newY < 0 || newX >= Wide || newY >= Length || Board[newX][newY] != ' ') {
                return false;
            }
        }

        placeShape(set, adjustedX, adjustedY);
//        set.printShape();
        return true;
    }
    
    public void placeShape(Shape shape, int x, int y) {
    	for (int[] dot: shape.dots) {
    		Board[x + dot[0]][y + dot[1]] = shape.symbol;
    	}
    }
    
    public void removeShape(Shape shape) {
        for (int i = 0; i < Wide; i++) {
            for (int j = 0; j < Length; j++) {
            	if (Board[i][j] == shape.symbol) Board[i][j] = ' ';
            }
        }
    }
    
    public Boolean isBoardSolved() {
        for (int i = 0; i < Wide; i++) {
            for (int j = 0; j < Length; j++) {
                if (Board[i][j] == ' ') return false;
            }
        }
        if (availShape.isEmpty()) {
        	return true;
        } else {
        	return false;
        }
        
    }
}
