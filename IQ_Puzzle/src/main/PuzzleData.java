package main;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PuzzleData {
    public int Wide, Length, Puzzle; // Store board dimensions and shape count
    public String State; // Store state
    public List<Shape> arrayOfShapes; // Store puzzle shapes
    public List<Shape> availShape;
    public char[][] Board;
    public int Attempt;

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
    }
    
    public void printBoard() {
    	// Debug
    	System.out.println("Board : ");
        for (char[] row : Board) {
            System.out.println(new String(row));
        }
    }
    
    public Boolean solving(int x, int y) {
        System.out.println("Central X : " + x + " Y : " + y);
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
                if (isValidSet(set, x, y)) {
	                printBoard();
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
        set.printShape();
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
        return true;
    }
}
