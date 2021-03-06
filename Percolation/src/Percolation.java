import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * Created by saurabh on 2/24/17.
 */
public class Percolation {

    private int n;
    private int openSiteCount;
    private int lastItemPosition;
    private boolean[] sites;
    private WeightedQuickUnionUF unionUF;

    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException();

        this.n = n;
        int totalItems = (n * n) + 2;
        lastItemPosition = totalItems - 1;

        unionUF = new WeightedQuickUnionUF(totalItems);
        sites = new boolean[totalItems];

        sites[0] = true;
        sites[sites.length - 1] = true;
    }

    public void open(int row, int col) {
        validateIndices(row, col);

        if (isOpen(row, col)) return;

        int current = xyTo1D(row, col);
        sites[current] = true;
        openSiteCount++;

        if (col != 1 && isOpen(row, col - 1)) unionUF.union(current, xyTo1D(row, col - 1));
        if (col != n && isOpen(row, col + 1)) unionUF.union(current, xyTo1D(row, col + 1));

        if (row == 1) {
            unionUF.union(current, 0);
        } else if (isOpen(row - 1, col)) {
            unionUF.union(current, xyTo1D(row - 1, col));
        }

        if (row == n) {
            unionUF.union(current, lastItemPosition);
        } else if (isOpen(row + 1, col)) {
            unionUF.union(current, xyTo1D(row + 1, col));
        }
    }

    public boolean isOpen(int row, int col) {
        validateIndices(row, col);
        return sites[xyTo1D(row, col)];
    }

    public boolean isFull(int row, int col) {
        validateIndices(row, col);
        return unionUF.connected(xyTo1D(row, col), 0);
    }

    public int numberOfOpenSites() {
        return openSiteCount;
    }

    public boolean percolates() {
        return unionUF.connected(0, lastItemPosition);
    }

    private void validateIndices(int row, int col) {
        if (row < 1 || row > n || col < 1 || col > n)
            throw new IndexOutOfBoundsException();
    }

    private int xyTo1D(int row, int col) {
        return (n * (row - 1)) + col;
    }
}
