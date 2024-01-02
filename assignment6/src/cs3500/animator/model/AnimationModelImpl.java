package cs3500.animator.model;

import cs3500.animator.util.AnimationBuilder;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * Represents an object of an animation model. It contains various shapes that can move, change
 * dimensions, and change color through Motions which can be added to the model, and all motions of
 * a shape must not overlap (from tick 1 to 10, a shape can't be moving in two different
 * directions). A shape must also not be able to teleport, or change it's state in an instantaneous
 * moment. Shapes added to the model are kept in two different Maps, keyed by their unique shape
 * name. One Map maps shape name to their list of motions, and the other maps shape name to their
 * shape type.
 */
public class AnimationModelImpl implements AnimationModel {


  private final Map<String, Shape> shapes;
  private final Map<String, ArrayList<Motion>> animationLog;

  // For the canvas coordinates
  private int x;
  private int y;
  private int width;
  private int height;

  /**
   * Constructs an object of the blank animation model with no shapes in it to start. The canvas
   * parameters are set to be 100 by default if there is no file to be read from.
   */
  public AnimationModelImpl() {

    this.shapes = new LinkedHashMap<>();
    this.animationLog = new LinkedHashMap<>();
    this.x = 100;
    this.y = 100;
    this.width = 100;
    this.height = 100;
  }


  @Override
  public void newMotion(String shapeName, Motion motion) {
    if (motion == null) {
      throw new IllegalArgumentException("The motion cannot be null");
    }
    // checks to make sure the specified shape exists
    if (!shapes.containsKey(shapeName)) {
      throw new IllegalArgumentException("That shape name does not exist");
    }

    // checks that all adjacent motions agree at a common shape state
    for (int i = 0; i < animationLog.get(shapeName).size(); i++) {
      Motion m = animationLog.get(shapeName).get(i);
      if (motion.getStartTick() >= m.getStartTick() && motion.getStartTick() < m.getEndTick()
          || motion.getEndTick() > m.getStartTick() && motion.getEndTick() < m.getEndTick()) {
        throw new IllegalArgumentException("You cannot overlap motions");
      }
      if ((i == 0 && isAdjacent(m, motion)) || (i == animationLog.get(shapeName).size() - 1
          && isAdjacent(m, motion))) {
        if (motion.getStartTick() == m.getEndTick()) {
          if (!(motion.getStartShape().equals(m.getEndShape()))) {
            throw new IllegalArgumentException(
                "Starting and ending shape states must agree with each other");
          }
        }
        if (motion.getEndTick() == m.getStartTick()) {
          if (!(motion.getEndShape().equals(m.getStartShape()))) {
            throw new IllegalArgumentException(
                "Starting and ending shape states must agree with each other");
          }
        }
      }
      if (i == 0 && !isAdjacent(m, motion)) {
        Motion lastMotion = animationLog.get(shapeName).get(animationLog.get(shapeName).size() - 1);
        if (!isAdjacent(lastMotion, motion)) {
          throw new IllegalArgumentException(
              "No gaps allowed");
        }
      }
    }

    ArrayList<Motion> log = animationLog.get(shapeName);
    log = addInOrder(animationLog.get(shapeName), motion);


  }

  /**
   * Determines whether the two motions given are adjacent, or back-to-back according to their
   * ticks.
   *
   * @param m1 the first motion being compared
   * @param m2 the second motion being compared
   * @return a boolean representing whether they are adjacent
   */
  private boolean isAdjacent(Motion m1, Motion m2) {
    return m1.getStartTick() == m2.getEndTick() || m1.getEndTick() == m2.getStartTick();
  }


  /**
   * Adds a shape log to the given ArrayList of Motions in a manner than keeps all Motions in order
   * of their starting and ending ticks. This method maintains that the timeline of Motions is
   * consecutive, and it returns the modified ArrayList.
   *
   * @param listOfLogs the already sorted ArrayList of Motions to be added to
   * @param motion     the Motions to be added to the ArrayList
   * @return the newly modified list of Motions
   */
  private ArrayList<Motion> addInOrder(ArrayList<Motion> listOfLogs, Motion motion) {
    if (listOfLogs.isEmpty()) {
      listOfLogs.add(motion);
      return listOfLogs;
    }

    for (int i = 0; i < listOfLogs.size(); i++) {
      if (i == listOfLogs.size() - 1) { // if this Motion is the last one in the list
        if (listOfLogs.get(i).getStartTick() >= motion.getEndTick()) {
          listOfLogs.add(i, motion);
          break;
        } else {
          listOfLogs.add(i + 1, motion);
          break;
        }
      }
      if (listOfLogs.get(i).getStartTick() >= motion.getEndTick()) {
        listOfLogs.add(i, motion);
        break;
      } else if (listOfLogs.get(i).getEndTick() <= motion.getStartTick()
          && motion.getEndTick() <= listOfLogs.get(i + 1).getStartTick()) {
        listOfLogs.add(i + 1, motion);
        break;
      }

    }
    return listOfLogs;
  }


  @Override
  public void addShape(Shape shape) {
    if (this.shapes.containsKey(shape.getName())) {
      throw new IllegalArgumentException("This shape name already exists");
    }
    shapes.put(shape.getName(), shape);
    animationLog.put(shape.getName(), new ArrayList<>());
  }


  @Override
  public Shape findShapeAtTick(String shapeName, int t) {
    if (!this.animationLog.containsKey(shapeName)) {
      throw new IllegalArgumentException("This shape name does not exist");
    }
    if (t < this.animationLog.get(shapeName).get(0).getStartTick() || t > this.animationLog
        .get(shapeName).get(this.animationLog.get(shapeName).size() - 1).getEndTick()) {
      return null;
    }
    for (int i = 0; i < this.animationLog.get(shapeName).size(); i++) {
      if (t >= this.animationLog.get(shapeName).get(i).getStartTick() && t <= this.animationLog
          .get(shapeName).get(i).getEndTick()) {
        return tweenShape(this.animationLog.get(shapeName).get(i), t, shapeName);
      }
    }
    throw new IllegalArgumentException("Shape could not be found at this tick");
  }

  /**
   * Takes in a motion, a tick inside that motion, and shapeName to determine what the shape should
   * look like at the given tick. Uses linear interpolation to calculate position, size and color.
   *
   * @param m         the motion that the tick is in for that shape
   * @param t         the tick at which the shape will be returned
   * @param shapeName the shape's name
   * @return the shape state at the given tick
   */
  private Shape tweenShape(Motion m, int t, String shapeName) {
    Shape shape = m.getStartShape();
    Position2D newPosition = m.getStartShape().getPosition();
    Size newSize = m.getStartShape().getSize();
    ShapeColor newColor = m.getStartShape().getColor();
    if (!m.getStartShape().getPosition().equals(m.getEndShape().getPosition())) {
      double newX = tweenShapeHelper(m.getStartShape().getPosition().getX(),
          m.getEndShape().getPosition().getX(), m.getStartTick(), m.getEndTick(), t);
      double newY = tweenShapeHelper(m.getStartShape().getPosition().getY(),
          m.getEndShape().getPosition().getY(), m.getStartTick(), m.getEndTick(), t);
      newPosition = new Position2D(newX, newY);
    }
    if (!m.getStartShape().getSize().equals(m.getEndShape().getSize())) {
      int newW = tweenShapeHelper(m.getStartShape().getSize().getWidth(),
          m.getEndShape().getSize().getWidth(), m.getStartTick(), m.getEndTick(), t);
      int newH = tweenShapeHelper(m.getStartShape().getSize().getHeight(),
          m.getEndShape().getSize().getHeight(), m.getStartTick(), m.getEndTick(), t);
      newSize = new Size(newW, newH);
    }
    if (!m.getStartShape().getColor().equals(m.getEndShape().getColor())) {
      int newR = tweenShapeHelper(m.getStartShape().getColor().getRed(),
          m.getEndShape().getColor().getRed(), m.getStartTick(), m.getEndTick(), t);
      int newG = tweenShapeHelper(m.getStartShape().getColor().getGreen(),
          m.getEndShape().getColor().getGreen(), m.getStartTick(), m.getEndTick(), t);
      int newB = tweenShapeHelper(m.getStartShape().getColor().getBlue(),
          m.getEndShape().getColor().getBlue(), m.getStartTick(), m.getEndTick(), t);
      newColor = new ShapeColor(newR, newG, newB);
    }

    return shape.getCorrectShape(shapeName, newPosition, newSize, newColor);

  }

  /**
   * A helper to the tweenShape method. Calculates the tweened values using the linear interpolation
   * formula.
   *
   * @param a  the a variable in the formula
   * @param b  the b variable in the formula
   * @param ta the tick at the beginning of the motion
   * @param tb the tick at the end of the motion
   * @param t  the tick that the shape is wanted at
   * @return an int representing the tweened variable
   */
  private int tweenShapeHelper(double a, double b, int ta, int tb, int t) {
    return (int) ((a * ((double) (tb - t) / (double) (tb - ta))) + (b * ((double) (t - ta)
        / (double) (tb - ta))));

  }


  @Override
  public void setCanvasX(int x) {
    this.x = x;
  }

  @Override
  public void setCanvasY(int y) {
    this.y = y;
  }

  @Override
  public void setCanvasHeight(int h) {
    this.height = h;
  }

  @Override
  public void setCanvasWidth(int w) {
    this.width = w;
  }

  @Override
  public Map<String, Shape> getShapes() {
    return new LinkedHashMap<>(this.shapes);
  }

  @Override
  public Map<String, ArrayList<Motion>> getAnimationLog() {
    return new LinkedHashMap<>(this.animationLog);
  }

  @Override
  public int getCanvasX() {
    return this.x;
  }

  @Override
  public int getCanvasY() {
    return this.y;
  }

  @Override
  public int getCanvasHeight() {
    return this.height;
  }

  @Override
  public int getCanvasWidth() {
    return this.width;
  }


  /**
   * A helper class that will build up an instance of the animation model according to the given
   * parameters given in the methods. This class is used by the Animation Reader class for the
   * purpose of parsing a file and turning it into an animation according to the text in the file.
   */
  public static final class Builder implements AnimationBuilder<AnimationModel> {

    private final AnimationModelImpl model;

    /**
     * Constructs a builder with an empty animation that will be built upon.
     */
    public Builder() {
      this.model = new AnimationModelImpl();
    }

    @Override
    public AnimationModel build() {
      return model;
    }

    @Override
    public AnimationBuilder<AnimationModel> setBounds(int x, int y, int width, int height) {
      model.setCanvasX(x);
      model.setCanvasY(y);
      model.setCanvasWidth(width);
      model.setCanvasHeight(height);

      return this;
    }

    @Override
    public AnimationBuilder<AnimationModel> declareShape(String name, String type) {
      Position2D defaultPosition = new Position2D(0, 0);
      Size defaultSize = new Size(10, 10);
      ShapeColor defaultColor = new ShapeColor(0, 0, 0);
      Shape s = new ShapeFactory().createShape(type, name, defaultPosition, defaultSize,
          defaultColor);

      model.addShape(s);

      return this;
    }

    @Override
    public AnimationBuilder<AnimationModel> addMotion(String name, int t1, int x1, int y1, int w1,
        int h1, int r1, int g1, int b1, int t2, int x2, int y2, int w2, int h2, int r2, int g2,
        int b2) {
      Position2D startPosition = new Position2D(x1, y1);
      Size startSize = new Size(w1, h1);
      ShapeColor startColor = new ShapeColor(r1, g1, b1);
      Position2D endPosition = new Position2D(x2, y2);
      Size endSize = new Size(w2, h2);
      ShapeColor endColor = new ShapeColor(r2, g2, b2);
      Shape s = model.getShapes().get(name);
      Shape startShape = s.getCorrectShape(name, startPosition, startSize,
          startColor);
      Shape endShape = s.getCorrectShape(name, endPosition, endSize,
          endColor);
      Motion m = new Motion(startShape, t1, endShape, t2);
      model.newMotion(name, m);
      return this;
    }

    @Override
    public AnimationBuilder<AnimationModel> addKeyframe(String name, int t, int x, int y, int w,
        int h, int r, int g, int b) {
      return null;
    }

  }


}
