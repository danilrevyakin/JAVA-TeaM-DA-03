package view.exam;

import javafx.scene.shape.Rectangle;

public class ShapeFactory {
    public Rectangle getRoundedRectangle(double width, double height, double arcWidth, double arcHeight) {
        Rectangle rectangle = new Rectangle();
        rectangle.setWidth(width);
        rectangle.setHeight(height);
        rectangle.setArcWidth(arcWidth);
        rectangle.setArcHeight(arcHeight);
        return rectangle;
    }
}
