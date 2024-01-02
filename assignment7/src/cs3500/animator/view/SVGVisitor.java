package cs3500.animator.view;

import cs3500.animator.model.Ellipse;
import cs3500.animator.model.Rectangle;
import cs3500.animator.model.ShapeVisitor;
import cs3500.animator.model.Square;

/**
 * A shape visitor that visits a shape and returns a string representing the type of shape it
 * visited.
 */
public class SVGVisitor implements ShapeVisitor<String> {

  @Override
  public String visitRectangle(Rectangle r) {
    return "rect";
  }

  @Override
  public String visitEllipse(Ellipse e) {
    return "ellipse";
  }

  @Override
  public String visitSquare(Square s) {
    return "rect";
  }
}
