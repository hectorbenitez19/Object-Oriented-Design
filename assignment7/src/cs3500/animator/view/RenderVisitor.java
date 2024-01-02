package cs3500.animator.view;

import cs3500.animator.model.Ellipse;
import cs3500.animator.model.Rectangle;
import cs3500.animator.model.ShapeVisitor;
import cs3500.animator.model.Square;
import java.awt.Color;
import java.awt.Graphics2D;


/**
 * A shape visitor class that renders the shape that the visitor visits. Takes in a Graphics2D to
 * render the shape onto and returns the type void since nothing needs to be returned.
 */
public class RenderVisitor implements ShapeVisitor<Void> {

  private final Graphics2D g;


  /**
   * Constructs a render visitor object with the given Graphics2D object.
   *
   * @param g the graphics object to render the shapes onto
   */
  public RenderVisitor(Graphics2D g) {
    this.g = g;
  }


  @Override
  public Void visitRectangle(Rectangle r) {

    g.setColor(new Color(r.getColor().getRed(), r.getColor().getGreen(), r.getColor().getBlue()));
    g.fillRect((int) r.getPosition().getX(), (int) r.getPosition().getY(), r.getSize().getWidth(),
        r.getSize().getHeight());
    return null;
  }

  @Override
  public Void visitEllipse(Ellipse e) {

    g.setColor(new Color(e.getColor().getRed(), e.getColor().getGreen(), e.getColor().getBlue()));
    g.fillOval((int) e.getPosition().getX(), (int) e.getPosition().getY(), e.getSize().getWidth(),
        e.getSize().getHeight());
    return null;
  }

  @Override
  public Void visitSquare(Square s) {
    g.setColor(new Color(s.getColor().getRed(), s.getColor().getGreen(), s.getColor().getBlue()));
    g.fillRect((int) s.getPosition().getX(), (int) s.getPosition().getY(), s.getSize().getWidth(),
        s.getSize().getHeight());
    return null;
  }
}
