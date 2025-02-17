package main;

import java.io.*;
import java.util.*;

public class Solver {
    private PuzzleData puzzleData; // Store puzzle input
    private Solution solution; // Store solver output

    public Solver(File file) {
        this.puzzleData = readPuzzleFile(file);
        this.solution = new Solution(); // Placeholder, modify as needed
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
            for (int i = 0; i < P; i++) {
                String shapeLines = "";
                String line;

                // Read shape block
                while ((line = br.readLine()) != null) {
                    if (line.isEmpty()) break; // Skip empty lines
                    shapeLines += line;
                    shapeLines += "\n";
                }
                arrayOfShapes.add(readShape(shapeLines));
            }

            return new PuzzleData(N, M, P, S, arrayOfShapes);
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    private Shape readShape(String shapeline) {
    	// debug
        int length = 1;
        int wide = 1;
        char symbol = 'G';
        List<int[]> dots = new ArrayList<>();
        dots.add(new int[]{0, 0});
    	
    	return new Shape(length, wide, symbol, dots);
    }

	public void solve() {
        if (puzzleData == null) {
            System.out.println("No puzzle data to solve.");
            return;
        }

        // Solving
        System.out.println("Solving puzzle...");
        solution.result = "Solution computed!"; // Output
    }

    public void printPuzzle() {
    	// Debug
        if (puzzleData == null) {
            System.out.println("No puzzle data available.");
            return;
        }

        System.out.println("Board Size: " + puzzleData.N + " x " + puzzleData.M);
        System.out.println("Number of Shapes: " + puzzleData.P);
        System.out.println("State: " + puzzleData.S);
        System.out.println("Shapes:");
        for (Shape shape : puzzleData.arrayOfShapes) {
        	shape.printShape();
            System.out.println();
        }
    }

    public void printSolution() {
    	// Debug
        if (solution == null) {
            System.out.println("No solution available.");
            return;
        }

        System.out.println("Solution Output: " + solution.result);
    }
}
