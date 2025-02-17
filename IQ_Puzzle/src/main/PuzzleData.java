package main;

import java.util.List;

public class PuzzleData {
    public int N, M, P; // Store board dimensions and shape count
    public String S; // Store state
    public List<Shape> arrayOfShapes; // Store puzzle shapes

    public PuzzleData(int N, int M, int P, String S, List<Shape> arrayOfShapes) {
        this.N = N;
        this.M = M;
        this.P = P;
        this.S = S;
        this.arrayOfShapes = arrayOfShapes;
    }
}
