/**
 * Represents a log of motion for a shape in an animation. A log includes the starting and ending
 * ticks for that motion, and the state of the shape to begin with, and the state of the shape at
 * the end.
 */
public class Motion {

  private final Shape startShape;
  private final Shape endShape;
  private final int startTick;
  private final int endTick;

  /**
   * Constructs a log of motion for a shape with beginning and ending ticks and beginning and ending
   * shape states.
   *
   * @param startShape the starting state of the shape
   * @param startTick  the start tick of the motion
   * @param endShape   the ending state of the shape
   * @param endTick    the ending tick of the motion
   * @throws IllegalArgumentException if the two shape states are different on the same tick or if
   *                                  the shape names are different among the two shapes
   */
  public Motion(Shape startShape, int startTick, Shape endShape, int endTick) {
    if ((startTick == endTick) && !startShape.equals(endShape)) {
      throw new IllegalArgumentException("You can't have a motion that changes a shape in an "
          + "instant");
    }
    if (!startShape.getName().equals(endShape.getName())) {
      throw new IllegalArgumentException("A motion must change the state of the same shape name");
    }
    this.startShape = startShape;
    this.endShape = endShape;
    this.startTick = startTick;
    this.endTick = endTick;
  }

  public int getStartTick() {
    return this.startTick;
  }

  public int getEndTick() {
    return this.endTick;
  }


  public Shape getStartShape() {
    return this.startShape;
  }

  public Shape getEndShape() {
    return this.endShape;
  }

  @Override
  public String toString() {
    return "motion " + startShape.getName() + " " + startTick + " " + startShape.getPosition()
        .toString() + " " + startShape.getSize().toString() + " " + startShape.getColor().toString()
        + "   " +
        endTick + " " + endShape.getPosition().toString() + " " + endShape.getSize().toString()
        + " " + endShape.getColor().toString();
  }

}
