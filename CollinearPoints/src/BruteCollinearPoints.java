import java.util.ArrayList;

/**
 * Created by saurabh on 3/6/17.
 */
public class BruteCollinearPoints {

    private ArrayList<LineSegment> segments;

    public BruteCollinearPoints(Point[] points) {
        checkCornerCases(points);

        segments = new ArrayList<>();

        if (points.length < 4)
            return;

        for (int i = 0; i < points.length; i++) {
            Point ptI = points[i];

            for (int j = i + 1; j < points.length; j++) {
                Point ptJ = points[j];

                for (int k = j + 1; k < points.length; k++) {
                    Point ptK = points[k];

                    if (ptI.slopeTo(ptJ) != ptJ.slopeTo(ptK))
                        continue;

                    for (int l = k + 1; l < points.length; l++) {
                        Point ptL = points[l];

                        if (ptI.slopeTo(ptJ) == ptK.slopeTo(ptL)) {

                            LineSegment lineSegment = new LineSegment(
                                    findSmallest(ptI, ptJ, ptK, ptL),
                                    findGreatest(ptI, ptJ, ptK, ptL)
                            );

                            if (!segments.contains(lineSegment)) segments.add(lineSegment);
                        }
                    }
                }
            }
        }

    }

    private Point findGreatest(Point ptI, Point ptJ, Point ptK, Point ptL) {
        Point greatest = ptI;
        if (greatest.compareTo(ptJ) > 0) greatest = ptJ;
        if (greatest.compareTo(ptK) > 0) greatest = ptK;
        if (greatest.compareTo(ptL) > 0) greatest = ptL;
        return greatest;
    }

    private Point findSmallest(Point ptI, Point ptJ, Point ptK, Point ptL) {
        Point smallest = ptI;
        if (smallest.compareTo(ptJ) < 0) smallest = ptJ;
        if (smallest.compareTo(ptK) < 0) smallest = ptK;
        if (smallest.compareTo(ptL) < 0) smallest = ptL;
        return smallest;
    }

    public int numberOfSegments() {
        return segments.size();
    }

    public LineSegment[] segments() {
        return segments.toArray(new LineSegment[segments.size()]);
    }

    private void checkCornerCases(Point[] points) {

        if (points == null)
            throw new NullPointerException("Argument null");

        for (int i = 0; i < points.length; i++) {
            Point point = points[i];

            if (point == null)
                throw new NullPointerException("Point null");

            for (int j = i + 1; j < points.length; j++) {
                if (point.compareTo(points[j]) == 0)
                    throw new IllegalArgumentException("Repeated Point");
            }
        }
    }

}
