package main;

import java.io.*;
import java.util.*;

public class Solver {
    public PuzzleData puzzleData; // Store puzzle input
    public Solution solution; // Store solver output
    public Time timer;

    public Solver(File file) {
        this.puzzleData = readPuzzleFile(file);
        String msgres = new String();
        Time Timer = new Time();
        this.timer = Timer;
        this.solution = new Solution(msgres, puzzleData.Board);
    }

    private PuzzleData readPuzzleFile(File file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            // Read first line: N M P
            String[] firstLine = br.readLine().trim().split("\\s+");
            int N = Integer.parseInt(firstLine[0]);
            int M = Integer.parseInt(firstLine[1]);
            int P = Integer.parseInt(firstLine[2]);

            // Read second line: S (State)
            String S = br.readLine().trim();

            // Read puzzle shapes
            List<Shape> arrayOfShapes = new ArrayList<>();
            String shapeLines = "";
            String line;
            char last = 'A';

            // Read shape block
            while ((line = br.readLine()) != null) {
            	if (last != line.trim().charAt(0)) {
                   	// System.out.println(last);
                   	last = line.trim().charAt(0);
                   	// System.out.println(last);
                   	arrayOfShapes.add(readShape(shapeLines));
                   	shapeLines = "";
                }
                shapeLines += line;
                shapeLines += "-";
                last = line.trim().charAt(0);
            }
            arrayOfShapes.add(readShape(shapeLines));
            return new PuzzleData(N, M, P, S, arrayOfShapes);
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    private Shape readShape(String shapeline) {
    	// debug
//    	System.out.print("Line : ");
//    	System.out.println(shapeline);
    	String[] parts = shapeline.split("-");
    	int max = 1;
    	for (String part : parts) {
    		if (max < part.length()) max = part.length();
    	}
        int length = max;
        int wide = parts.length;
        char symbol = shapeline.charAt(0);
		// Debug
//		System.out.println(wide);
//		System.out.println(length);
		
        List<int[]> dots = new ArrayList<>();
        for (int i = 0; i < wide; i++) {
        	for (int j = 0; j < parts[i].length(); j++) {
        		if (parts[i].charAt(j) == symbol) {
        			// Debug
//        			System.out.print(i);
//        			System.out.println(j);
        			
        			dots.add(new int[]{i, j});
        		}
        	}
        }
    	
    	return new Shape(length, wide, symbol, dots);
    }

	public void solve() {
        if (puzzleData == null) {
            System.out.println("No puzzle data to solve.");
            return;
        }
        timer.startTimer();
        // Solving
        puzzleData.solving(0, 0);
        timer.stopTimer();
        timer.printTime();
        System.out.println("Attempt : " + puzzleData.Attempt);
        puzzleData.printBoard();
        if (puzzleData.availShape.isEmpty() || puzzleData.isBoardSolved()) {
        	solution.result = "Solution computed !";
        } else {
        	solution.result = "Solution not found !";
        }
    }

    public void printPuzzle() {
    	// Debug
        if (puzzleData == null) {
            System.out.println("No puzzle data available.");
            return;
        }

        System.out.println("Board Size: " + puzzleData.Wide + " x " + puzzleData.Length);
        System.out.println("Number of Shapes: " + puzzleData.Puzzle);
        System.out.println("State: " + puzzleData.State);
        System.out.println("Shapes:");
        for (Shape shape : puzzleData.arrayOfShapes) {
        	shape.printShape();
            System.out.println();
            new SetShape(shape).printSet();
        }
    }

    public void printSolution() {
    	System.out.println("Attempt : " + puzzleData.Attempt);
        System.out.println("Solution Output: " + solution.result);
        System.out.println("Solution Board: ");
        puzzleData.printBoard();
        timer.printTime();
    }
}
