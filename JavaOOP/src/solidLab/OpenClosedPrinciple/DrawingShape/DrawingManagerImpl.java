package solidLab.OpenClosedPrinciple.DrawingShape;

import solidLab.SingleResponsibility.DrawingShape.interfaces.DrawingManager;
import solidLab.SingleResponsibility.DrawingShape.interfaces.Shape;


public class DrawingManagerImpl implements DrawingManager {


    @Override
    public void draw(Shape shape) {
        if (shape instanceof Circle)
        {
            this.drawCircle((Circle)shape);
        }
            else if (shape instanceof Rectangle)
        {
            this.drawRectangle(((Rectangle)shape));
        }
    }
    public void drawCircle(Circle circle)
    {
        // Draw Circle
    }

    public void drawRectangle(Rectangle rectangle)
    {
        // Draw Rectangle
    }
}
