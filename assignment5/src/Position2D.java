import java.util.Objects;

/**
 * This class represents a 2D position that a shape can be positioned at.
 */
public class Position2D {

  private final double x;
  private final double y;

  /**
   * Constructs a 2D position based on x and y values.
   */
  public Position2D(double x, double y) {
    this.x = x;
    this.y = y;
  }

  public double getX() {
    return x;
  }

  public double getY() {
    return y;
  }


  @Override
  public String toString() {
    return String.format("%d %d", (int)this.x, (int)this.y);
  }


  @Override
  public boolean equals(Object a) {
    if (this == a) {
      return true;
    }
    if (!(a instanceof Position2D)) {
      return false;
    }

    Position2D that = (Position2D) a;

    return ((Math.abs(this.x - that.x) < 0.01) && (Math.abs(this.y - that.y) < 0.01));
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.x, this.y);
  }




}
