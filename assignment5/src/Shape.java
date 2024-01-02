/**
 * This interface represents a Shape to be used in an animation. All shapes will have a Size, a
 * Name, A Color, and a Position on the Animation grid.
 */
public interface Shape {

  /**
   * Gets the the size of this shape.
   * @return the size of this shape
   */
  Size getSize();

  /**
   * Gets the the name of this shape.
   * @return the name of this shape
   */
  String getName();

  /**
   * Gets the the color of this shape.
   * @return the color of this shape
   */
  ShapeColor getColor();

  /**
   * Gets the the position of this shape.
   * @return the position of this shape
   */
  Position2D getPosition();

}
