import java.util.Objects;

/**
 * Represents a shape's size in terms of with and height.
 */
public class Size {
  private final int width;
  private final int height;

  /**
   * Constructs a size made up of width and height.
   * @param width the width of the shape
   * @param height the height of the shape
   * @throws IllegalArgumentException if either the width or height is not positive
   */
  Size(int width, int height) {
    if (width < 1 || height < 1) {
      throw new IllegalArgumentException("Width and height must be positive");
    }
    this.width = width;
    this.height = height;
  }

  public int getWidth() {
    return this.width;
  }

  public int getHeight() {
    return this.height;
  }

  @Override
  public String toString() {
    return String.format("%d %d", width,height);
  }

  @Override
  public boolean equals(Object a) {
    if (this == a) {
      return true;
    }
    if (!(a instanceof Size)) {
      return false;
    }

    Size that = (Size) a;

    return ((this.height == that.height) && (this.width == that.width));
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.height, this.width);
  }
}
