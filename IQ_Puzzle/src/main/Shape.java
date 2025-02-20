package main;

import java.util.ArrayList;
import java.util.List;

public class Shape {
    public int length;
    public int wide;
    public char symbol;
    public List<int[]> dots;
    
    public Shape(int length, int wide, char symbol, List<int[]> dots) {
        this.length = length;
        this.wide = wide;
        this.symbol = symbol;
        this.dots = dots;
    }

    public void printShape() {
        System.out.println("Shape Size: " + wide + " x " + length);
        int boardLen;
        if (wide > length) {
        	boardLen = wide * 2;
        } else {
        	boardLen = length * 2;
        }
        char[][] display = new char[boardLen][boardLen];
        for (int i = 0; i < boardLen; i++) {
            for (int j = 0; j < boardLen; j++) {
                display[i][j] = ' ';
            }
        }
        boardLen /= 2;
        for (int[] dot : dots) {
        	// Debug
//        	System.out.println(dot[0]);
//        	System.out.println(dot[1]);
            display[boardLen + dot[0]][boardLen + dot[1]] = symbol;
        }
        printDots();
        for (char[] row : display) {
            System.out.println(new String(row));
        }
    }
    
    public void printDots() {
    	System.out.print("Dots : ");
        for (int[] dot : dots) {
        	System.out.print("(" + dot[0] +", " + dot[1] + ")");
        }
        System.out.println(".");
    }
    
    public Shape flip() {
        List<int[]> newDots = new ArrayList<>();
        for (int[] dot : dots) {
            newDots.add(new int[]{-dot[0], dot[1]});
        }
        return new Shape(this.length, this.wide, this.symbol, newDots);
    }

    public Shape rotate() {
        List<int[]> newDots = new ArrayList<>();
        for (int[] dot : dots) {
            newDots.add(new int[]{dot[1], -dot[0]});
        }
        return new Shape(this.length, this.wide, this.symbol, newDots);
    }

}