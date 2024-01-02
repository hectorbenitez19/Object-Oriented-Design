package cs3500.animator.model;

/**
 * An interface for visitors that visits instances of Shapes and returns a generic type R that is
 * specified in specific classes that are Shape visitors.
 *
 * @param <R> Generic type R that is specified in shape visitor classes
 */
public interface ShapeVisitor<R> {

  /**
   * Visits the rectangle shape and returns the return type R that is specified by the shape
   * visitor.
   *
   * @param r the shape Rectangle
   * @return returns the generic type R specified by the shape visitor
   */
  R visitRectangle(Rectangle r);


  /**
   * Visits the Ellipse shape and returns the return type R that is specified by the shape visitor.
   *
   * @param e the shape Ellipse
   * @return returns the generic type R specified by the shape visitor
   */
  R visitEllipse(Ellipse e);

  /**
   * Visits the Square shape and returns the return type R that is specified by the shape visitor.
   *
   * @param s the shape Square
   * @return returns the generic type R specified by the shape visitor
   */
  R visitSquare(Square s);


}
