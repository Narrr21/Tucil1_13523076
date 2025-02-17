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
        char[][] display = new char[wide][length];
        for (int i = 0; i < wide; i++) {
            for (int j = 0; j < length; j++) {
                display[i][j] = ' ';
            }
        }
        for (int[] dot : dots) {
            display[dot[0]][dot[1]] = symbol;
        }
        for (char[] row : display) {
            System.out.println(new String(row));
        }
    }
}