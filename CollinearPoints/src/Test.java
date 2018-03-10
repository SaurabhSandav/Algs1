import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by saurabh on 3/7/17.
 */
public class Test {

    public static final String INPUT_6 = "input6.txt";
    public static final String INPUT_8 = "input8.txt";
    public static final String INPUT_9 = "input9.txt";
    public static final String INPUT_10 = "input10.txt";
    public static final String INPUT_20 = "input20.txt";
    public static final String INPUT_40 = "input40.txt";
    public static final String INPUT_400 = "input400.txt";
    public static final String INPUT_1000 = "input1000.txt";
    public static final String INPUT_2000 = "input2000.txt";
    public static final String INPUT_5000 = "input5000.txt";
    public static final String INPUT_10000 = "input10000.txt";
    public static final String RS1423 = "rs1423.txt";
    public static final String KW1260 = "kw1260.txt";
    public static final String MYSTERY10089 = "mystery10089.txt";
    public static final String GRID_4X4 = "grid4x4.txt";
    public static final String GRID_5X5 = "grid5x5.txt";
    public static final String GRID_6X6 = "grid6x6.txt";

    public static void main(String[] args) {
        In in = null;
        in = new In("resources/" + KW1260);
        //in = new In("resources/" + RS1423);
        //in = new In("resources/" + INPUT_8);
        //in = new In("resources/" + INPUT_9);
        //in = new In("resources/" + INPUT_10);
        //in = new In("resources/" + INPUT_40);
        //in = new In("resources/" + INPUT_10000);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        Stopwatch stopwatch = new Stopwatch();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        //BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        int i = 0;
        for (LineSegment segment : collinear.segments()) {
            StdOut.print(++i + " ");
            StdOut.println(segment);
            segment.draw();
        }
        StdOut.println(stopwatch.elapsedTime());
        StdDraw.show();
    }
}
