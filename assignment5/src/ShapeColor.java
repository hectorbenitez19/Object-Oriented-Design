import java.util.Objects;

/**
 * Represents a color that a shape can be.
 */
public class ShapeColor {

  private final int red;
  private final int green;
  private final int blue;


  /**
   * Constructs a color made up of red, green, and blue components. Each component of the color is
   * on a scale from 0 - 255.
   *
   * @param red   the red component of the color
   * @param green the green component of the color
   * @param blue  the blue component of the color
   * @throws IllegalArgumentException if any color component exceed 255 or is negative.
   */
  public ShapeColor(int red, int green, int blue) {
    if (red > 255 || green > 255 || blue > 255 || red < 0 || green < 0 || blue < 0) {
      throw new IllegalArgumentException("Not a valid color");
    }
    this.red = red;
    this.blue = blue;
    this.green = green;
  }

  public int getRed() {
    return this.red;
  }

  public int getBlue() {
    return this.blue;
  }

  public int getGreen() {
    return this.green;
  }


  @Override
  public boolean equals(Object a) {
    if (this == a) {
      return true;
    }
    if (!(a instanceof ShapeColor)) {
      return false;
    }

    ShapeColor that = (ShapeColor) a;

    return ((this.red == that.red) && (this.blue == that.blue) && (this.green == that.green));
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.red, this.blue, this.green);
  }

  @Override
  public String toString() {
    return String.format("%d %d %d", this.red, this.green, this.blue);
  }

}
