package princeton.assignment3;

import edu.princeton.cs.algs4.StdDraw;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.nio.file.Files.lines;
import static java.nio.file.Paths.get;

@SuppressWarnings("Duplicates")
public class FastCollinearPoints {

    private final static int MAX_SCALE_VALUE = 32768;
    /**
     * Constant holding radius for points drawing
     */
    private static final double POINT_RADIUS = 0.01;
    /**
     * Constant holding radius for lines drawing
     */
    private static final double LINE_RADIUS = 0.005;

    private static Point[] points;
    private static LineSegment[] lineSegments;

    public static void main(String[] args) throws IOException {
        StdDraw.setXscale(0, MAX_SCALE_VALUE);
        StdDraw.setYscale(0, MAX_SCALE_VALUE);
        StdDraw.setPenRadius(POINT_RADIUS);

        populatePointsArray();

        for (Point point : points) {
            point.draw();
        }

        StdDraw.setPenRadius(LINE_RADIUS);

        new FastCollinearPoints(points);
    }

    private static void populatePointsArray() throws IOException {
        Pattern p = Pattern.compile("\\d+");

        Stream<String> stream = lines(get("/home/serhii/old-system/" +
                "old-system/IdeaProjects/Eckel/Topics/data-structures/princeton/" +
                "src/main/java/princeton/assignment3/input10.txt"));
        List<String> strings = stream.skip(1).collect(Collectors.toList());
        List<Point> pointsList = strings.stream().map(s -> {
            Matcher m = p.matcher(s);
            List<String> list = new ArrayList<>();
            while (m.find()) {
                list.add(m.group(0));
            }
            return new Point(Integer.valueOf(list.get(0)), Integer.valueOf(list.get(1)));
        }).collect(Collectors.toList());

        points = pointsList.toArray(new Point[pointsList.size()]);
    }

    public FastCollinearPoints(Point[] points)  {

        List<LineSegment> listOfLineSegments = new ArrayList<>();

        Arrays.sort(points);
        System.out.println("*********POINTS**********");
        Arrays.stream(points).forEach(System.out::println);
        System.out.println("*******************");


        for (int i = 0; i < points.length; i++) {
            Point p = points[i];

            Point[] adjacentPoints = Arrays.copyOfRange(points, i + 1, points.length);
            Arrays.sort(adjacentPoints, p.slopeOrder());

            System.out.println();
            System.out.println("*********ADJACENT POINTS**********");
            System.out.println("********SORTED BY SLOPES**********");
            Arrays.stream(points).forEach(System.out::println);
            System.out.println("*******************");

        }

        System.out.println("Line segments:");
        listOfLineSegments.forEach(System.out::println);
    }
























    private void addLineSegment(List<LineSegment> lineSegments, Point... points) {
        System.out.println("points[0]: " + points[0]);
        System.out.println("points[1]: " + points[1]);
        System.out.println("points[2]: " + points[2]);
        System.out.println("points[3]: " + points[3]);
        System.out.println("points[0].slopeTo(points[1]) = " + points[0].slopeTo(points[1]));
        System.out.println("points[0].slopeTo(points[2]) = " + points[0].slopeTo(points[2]));
        System.out.println("points[0].slopeTo(points[3]) = " + points[0].slopeTo(points[3]));

        lineSegments.add(new LineSegment(points[0], points[1]));
        lineSegments.add(new LineSegment(points[1], points[2]));
        lineSegments.add(new LineSegment(points[2], points[3]));
    }

    public int numberOfSegments() {
        return lineSegments.length;
    }

    public static LineSegment[] segments() {
        return lineSegments;
    }
}
