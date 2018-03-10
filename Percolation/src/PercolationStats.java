import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

/**
 * Created by saurabh on 2/24/17.
 */
public class PercolationStats {

    private int trials;
    private double[] openSites;

    public PercolationStats(int n, int trials) {
        // perform trials independent experiments on an n-by-n grid
        if (n <= 0 || trials <= 0)
            throw new IllegalArgumentException();

        this.trials = trials;
        openSites = new double[trials];

        for (int i = 0; i < trials; i++) {

            Percolation perc = new Percolation(n);

            while (!perc.percolates()) {

                int row = StdRandom.uniform(1, n + 1);
                int col = StdRandom.uniform(1, n + 1);

                if (perc.isOpen(row, col)) continue;
                perc.open(row, col);

            }

            openSites[i] = (double) perc.numberOfOpenSites() / (n * n);
        }
    }

    public double mean() {
        // sample mean of percolation threshold
        return StdStats.mean(openSites);
    }

    public double stddev() {
        // sample standard deviation of percolation threshold
        if (trials == 1) return Double.NaN;
        return StdStats.stddev(openSites);
    }

    public double confidenceLo() {
        // low  endpoint of 95% confidence interval
        return mean() - ((1.96 * stddev()) / Math.sqrt(trials));
    }

    public double confidenceHi() {
        // high endpoint of 95% confidence interval
        return mean() + ((1.96 * stddev()) / Math.sqrt(trials));
    }

    public static void main(String[] args) {
        // test client (described below)
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);

        PercolationStats stats = new PercolationStats(n, trials);
        System.out.println("mean                    = " + stats.mean());
        System.out.println("stddev                  = " + stats.stddev());
        System.out.println("95% confidence interval = [" + stats.confidenceLo() + ", " + stats.confidenceHi() + "]");
    }

}
