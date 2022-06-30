package Abstractions.PointInRectangle;

public class Rectangle {
    private Point A;
    private Point C;

    public Rectangle(Point A, Point C) {
        this.A = A;
        this.C = C;
    }

    public boolean contains(Point x) {
        return x.greaterThanOrEqual(A) && x.lessOrEqual(C);


    }
}
