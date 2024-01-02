package cs3500.animator.model;

/**
 * This interface represents a Shape to be used in an animation. All shapes will have a Size, a
 * Name, A Color, and a Position on the Animation grid.
 */
public interface Shape {

  /**
   * Outputs the shape with the necessary information. Used by the AnimationBuilder to get the
   * correct instance of a shape.
   *
   * @param name the name of the shape
   * @param p    the position of the shape
   * @param s    the size of the shape
   * @param c    the color of the shape
   * @return the shape with the given fields
   */
  Shape getCorrectShape(String name, Position2D p, Size s, ShapeColor c);


  /**
   * Gets the the size of this shape.
   *
   * @return the size of this shape
   */
  Size getSize();

  /**
   * Gets the the name of this shape.
   *
   * @return the name of this shape
   */
  String getName();

  /**
   * Gets the the color of this shape.
   *
   * @return the color of this shape
   */
  ShapeColor getColor();

  /**
   * Gets the the position of this shape.
   *
   * @return the position of this shape
   */
  Position2D getPosition();


  /**
   * Accepts a shape visitor that visits the shape and return the type R that is defined by that
   * specific shape visitor.
   *
   * @param visitor shape visitor that returns a generic type R
   * @param <R>     A generic type that the shape visitor specifies and returns
   * @return the type R that is specified by the visitor
   */
  <R> R accept(ShapeVisitor<R> visitor);


  @Override
  String toString();

  @Override
  boolean equals(Object o);

  @Override
  int hashCode();

}
