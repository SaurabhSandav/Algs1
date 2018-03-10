import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

/**
 * Created by saurabh on 3/13/17.
 */
public class Board {

    private int n;
    private int[][] blocks;

    public Board(int[][] blocks) {
        this.n = blocks.length;

        this.blocks = new int[blocks.length][];

        for (int i = 0; i < n; i++)
            this.blocks[i] = Arrays.copyOf(blocks[i], blocks[i].length);

    }

    public int dimension() {
        return n;
    }

    public int hamming() {

        int wrongPositions = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                if (blocks[i][j] != xyTo1D(i, j) && blocks[i][j] != 0)
                    wrongPositions++;
            }
        }

        return wrongPositions;
    }

    public int manhattan() {

        int totalDistance = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                if (xyTo1D(i, j) != blocks[i][j] && blocks[i][j] != 0) {

                    int vertDist = Math.abs(i - getRow(blocks[i][j]));
                    int horizDist = Math.abs(j - getColumn(blocks[i][j]));

                    totalDistance += (vertDist + horizDist);
                }
            }
        }

        return totalDistance;
    }

    public boolean isGoal() {

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                if (i == n - 1 && j == n - 1)
                    break;

                if (blocks[i][j] != xyTo1D(i, j))
                    return false;
            }
        }

        return true;
    }

    public Board twin() {

        Board board = new Board(blocks);

        int index1, index2;

        do {
            index1 = StdRandom.uniform(n * n) + 1;
            index2 = StdRandom.uniform(n * n) + 1;
        }
        while (index1 == index2 ||
                board.blocks[getRow(index1)][getColumn(index1)] == 0 ||
                board.blocks[getRow(index2)][getColumn(index2)] == 0);

        int temp = board.blocks[getRow(index1)][getColumn(index1)];
        board.blocks[getRow(index1)][getColumn(index1)] = board.blocks[getRow(index2)][getColumn(index2)];
        board.blocks[getRow(index2)][getColumn(index2)] = temp;

        return board;
    }

    public boolean equals(Object other) {

        if (other == this) return true;
        if (other == null) return false;
        if (other.getClass() != this.getClass()) return false;

        Board board = (Board) other;

        if (this.dimension() != board.dimension()) return false;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (blocks[i][j] != board.blocks[i][j])
                    return false;
            }
        }

        return true;
    }

    public Iterable<Board> neighbors() {
        Stack<Board> stack = new Stack<>();

        int emptyRowIndex = 0, emptyColumnIndex = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                if (blocks[i][j] == 0) {
                    emptyRowIndex = i;
                    emptyColumnIndex = j;
                    break;
                }
            }
        }

        if ((emptyRowIndex - 1) >= 0)
            stack.push(getBoard(emptyRowIndex, emptyColumnIndex, emptyRowIndex - 1, emptyColumnIndex));

        if ((emptyRowIndex + 1) < n)
            stack.push(getBoard(emptyRowIndex, emptyColumnIndex, emptyRowIndex + 1, emptyColumnIndex));

        if ((emptyColumnIndex - 1) >= 0)
            stack.push(getBoard(emptyRowIndex, emptyColumnIndex, emptyRowIndex, emptyColumnIndex - 1));

        if ((emptyColumnIndex + 1) < n)
            stack.push(getBoard(emptyRowIndex, emptyColumnIndex, emptyRowIndex, emptyColumnIndex + 1));

        return stack;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(n).append("\n");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                s.append(String.format("%2d ", blocks[i][j]));
            }
            s.append("\n");
        }
        return s.toString();
    }

    private int xyTo1D(int row, int col) {
        return (n * row) + col + 1;
    }

    private int getRow(int index) {
        return ((int) Math.ceil((double) index / n)) - 1;
    }

    private int getColumn(int index) {
        int remainder = index % n;
        int col = remainder == 0 ? n : remainder;
        return col - 1;
    }

    private Board getBoard(int indexRow1, int indexCol1, int indexRow2, int indexCol2) {
        Board board = new Board(blocks);
        swap(board.blocks, indexRow1, indexCol1, indexRow2, indexCol2);
        return board;
    }

    private void swap(int[][] newBlocks, int indexRow1, int indexCol1, int indexRow2, int indexCol2) {

        int temp = newBlocks[indexRow1][indexCol1];
        newBlocks[indexRow1][indexCol1] = newBlocks[indexRow2][indexCol2];
        newBlocks[indexRow2][indexCol2] = temp;

    }


    public static void main(String[] args) {

    }
}
