package solidLab.SingleResponsibility.DrawingShape;

import solidLab.SingleResponsibility.DrawingShape.interfaces.DrawingManager;
import solidLab.SingleResponsibility.DrawingShape.interfaces.DrawingRepository;
import solidLab.SingleResponsibility.DrawingShape.interfaces.Rengerer;
import solidLab.SingleResponsibility.DrawingShape.interfaces.Shape;


public class DrawingManagerImpl implements DrawingManager {

    private final DrawingRepository drawingRepository;
    private final Rengerer renderer;

    public DrawingManagerImpl(DrawingRepository drawingRepository, Rengerer renderer) {
        this.drawingRepository = drawingRepository;
        this.renderer = renderer;
    }


    @Override
    public void draw(Shape shape) {
        shape.draw(this.renderer, this.drawingRepository);
    }
}
