package cs3500.animator.model;

import java.util.Objects;

/**
 * A shape class representing the Rectangle shape.
 */
public class Rectangle implements Shape {

  private final Size size;
  private final ShapeColor color;
  private final Position2D position;
  private final String name;

  /**
   * Constructs a Rectangle.
   *
   * @param name     the name of the rectangle
   * @param size     the size of the rectangle
   * @param color    the color of the rectangle
   * @param position the position of the rectangle
   */
  public Rectangle(String name, Position2D position, Size size, ShapeColor color) {
    this.name = name;
    this.size = size;
    this.color = color;
    this.position = position;
  }

  @Override
  public Shape getCorrectShape(String name, Position2D p, Size s, ShapeColor c) {
    return new Rectangle(name, p, s, c);
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
    return visitor.visitRectangle(this);
  }

  @Override
  public boolean equals(Object a) {
    if (this == a) {
      return true;
    }
    if (!(a instanceof Rectangle)) {
      return false;
    }

    Rectangle that = (Rectangle) a;

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
    return "shape " + this.name + " rectangle";
  }
}
