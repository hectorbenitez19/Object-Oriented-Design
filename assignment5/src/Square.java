
//represent the transformation over time

import java.util.Objects;

/**
 * Represents a Square shape.
 */
public class Square implements Shape {

  private final Size size;
  private final ShapeColor color;
  private final Position2D position;
  private final String name;

  /**
   * Constructs a square.
   *
   * @param name     the name of the square.
   * @param size     the size of the square
   * @param color    the color of the square
   * @param position the position of the square
   */
  public Square(String name, Position2D position, Size size, ShapeColor color) {
    if (size.getHeight() != size.getWidth()) {
      throw new IllegalArgumentException("A Square must have equal width and height");
    }
    this.name = name;
    this.size = size;
    this.color = color;
    this.position = position;
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
  public boolean equals(Object a) {
    if (this == a) {
      return true;
    }
    if (!(a instanceof Square)) {
      return false;
    }

    Square that = (Square) a;

    return (this.size.equals((that).getSize()) &&
        this.position.equals((that).getPosition()) &&
        this.color.equals((that).getColor()) &&
        this.name.equals(that.getName()));

  }

  @Override
  public int hashCode() {
    return Objects.hash(this.size, this.position,this.color,this.name);
  }

  @Override
  public String toString() {
    return "shape " + this.name + " Square";
  }

}
