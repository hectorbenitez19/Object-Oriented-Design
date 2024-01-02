package cs3500.animator.model;

/**
 * A factory class that is used to generate objects of concrete shapes based on the type of shape
 * desired.
 */
public class ShapeFactory {

  /**
   * Used to get the correct instance of a shape based on the type specified by shapeType. The
   * passed in parameters help create the shape.
   *
   * @param shapeType the type of shape to be created
   * @param name      the name of the shape
   * @param p         the position of the shape
   * @param s         the size of the shape
   * @param c         the color of the shape
   * @return the correct instance of the shape
   */
  public Shape createShape(String shapeType, String name, Position2D p, Size s, ShapeColor c) {

    switch (shapeType) {
      case "square":
        return new Square(name, p, s, c);
      case "rectangle":
        return new Rectangle(name, p, s, c);
      case "ellipse":
        return new Ellipse(name, p, s, c);
      default:
        throw new IllegalArgumentException("This type doesn't exist");
    }
  }

}
