import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

/**
 * Created by saurabh on 3/6/17.
 */
public class FastCollinearPoints {

    private ArrayList<LineSegment> segments;
    private HashMap<Double, ArrayList<Point>> pointSlopes;

    public FastCollinearPoints(Point[] points) {

        segments = new ArrayList<>();
        pointSlopes = new HashMap<>();

        Point[] originPoints = Arrays.copyOf(points, points.length);
        Point[] newPoints = Arrays.copyOf(points, points.length);

        Arrays.sort(originPoints, Comparator.naturalOrder());

        checkCornerCases(originPoints);

        for (int i = 0; i < originPoints.length; i++) {
            Point origin = originPoints[i];

            Arrays.sort(newPoints, i, newPoints.length, origin.slopeOrder());

            for (int indexStart = i, indexEnd = indexStart + 1; indexStart < newPoints.length - 2; indexStart++) {

                while (indexEnd != newPoints.length && origin.slopeOrder().compare(newPoints[indexStart], newPoints[indexEnd]) == 0) {
                    indexEnd++;
                }

                if ((indexEnd - indexStart) >= 3)
                    addSegment(origin, newPoints, indexStart, indexEnd);

                indexStart = indexEnd - 1;
            }

        }

    }

    private void addSegment(Point smallest, Point[] newPoints, int indexStart, int indexEnd) {

        Arrays.sort(newPoints, indexStart, indexEnd, Comparator.naturalOrder());
        Point largest = newPoints[indexEnd - 1];

        double slope = smallest.slopeTo(largest);

        if (!pointSlopes.containsKey(slope)) {

            ArrayList<Point> points = new ArrayList<>();
            points.add(largest);
            pointSlopes.put(slope, points);
            segments.add(new LineSegment(smallest, largest));

        } else if (!pointSlopes.get(slope).contains(largest)) {
            pointSlopes.get(slope).add(largest);
            segments.add(new LineSegment(smallest, largest));
        }
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

        for (int i = 0; i < points.length - 1; i++) {
            Point point = points[i];

            if (point == null)
                throw new NullPointerException("Point null");

            if (point.compareTo(points[i + 1]) == 0)
                throw new IllegalArgumentException("Repeated Point");
        }

        if (points[points.length - 1] == null)
            throw new NullPointerException("Point null");
    }

}
