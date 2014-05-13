package pkginterface;

import java.awt.Point;

public class Line {

    private final Point point1;
    private final Point point2;

    public Line(Point point1, Point point2) {
        this.point1 = point1;
        this.point2 = point2;
    }

    public void belongToLine(Point p) {
        //y = y1 + [(y2 - y1) / (x2 - x1)]·(x - x1)
        int y = point1.y + ((point2.y - point1.y) / (point2.x - point1.x)) * (p.x - point1.x);

        if (y >= p.y - 2 && y <= p.y + 2) {
            System.out.println("true");
            return;
        }
        System.out.println("false");
    }

    public Point getPoint1() {
        return point1;
    }

    public Point getPoint2() {
        return point2;
    }

}
