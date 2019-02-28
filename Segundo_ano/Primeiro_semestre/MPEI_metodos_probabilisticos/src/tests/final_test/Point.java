package tests.final_test;

import java.util.Comparator;

public class Point implements Comparable<Point> {
    private int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    @Override
    public int compareTo(Point o) {
        return (o.getX() < o.getY()) ? -1 : 1;
    }
}
