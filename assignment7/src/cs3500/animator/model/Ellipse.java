package cs3500.animator.model;

import java.util.Objects;

/**
 * A shape class representing the Ellipse shape. An ellipse has a Position, Size and Color and is
 * used in the Animation model.
 */
public class Ellipse implements Shape {

  private final Size size;
  private final ShapeColor color;
  private final Position2D position;
  private final String name;

  /**
   * Constructs a Ellipse shape.
   *
   * @param name     the name of the ellipse
   * @param size     the size of the ellipse
   * @param color    the color of the ellipse
   * @param position the position of the ellipse
   */
  public Ellipse(String name, Position2D position, Size size, ShapeColor color) {
    this.name = name;
    this.size = size;
    this.color = color;
    this.position = position;
  }

  @Override
  public Shape getCorrectShape(String name, Position2D p, Size s, ShapeColor c) {
    return new Ellipse(name, p, s, c);
  }

  @Override
  public Size getSize() {
    return this.size;
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public ShapeColor getColor() {
    return this.color;
  }

  @Override
  public Position2D getPosition() {
    return this.position;
  }


  @Override
  public <R> R accept(ShapeVisitor<R> visitor) {
    return visitor.visitEllipse(this);
  }

  @Override
  public boolean equals(Object a) {
    if (this == a) {
      return true;
    }
    if (!(a instanceof Ellipse)) {
      return false;
    }

    Ellipse that = (Ellipse) a;

    return (this.size.equals((that).getSize()) &&
        this.position.equals((that).getPosition()) &&
        this.color.equals((that).getColor()) &&
        this.name.equals(that.getName()));

  }

  @Override
  public int hashCode() {
    return Objects.hash(this.size, this.position, this.color, this.name);
  }

  @Override
  public String toString() {
    return "shape " + this.name + " ellipse";
  }
}
