package cs3500.animator.model;

import java.util.ArrayList;
import java.util.Map;

/**
 * This interface represents a model for an animation. It contains the various operations that an
 * animation can perform, such as adding a motion for a shape from one tick to another tick, and all
 * motions of a shape must not overlap (from tick 1 to 10, a shape can't be moving in two different
 * directions). A shape must also not be able to teleport, or change it's state in an instantaneous
 * moment. One object of the model represents one animation.
 */
public interface AnimationModel {

  /**
   * Adds a new motion for the specified shape to this animation model. A motion can only be added
   * as long as its starting and ending ticks don't overlap with existing motions for the shape, and
   * the motion being added must be adjacent to the shape's first or last motion, and the shape
   * states must agree with each other to ensure no teleportation or instantaneous changes.
   *
   * @param shapeName the shape to which the motion will be added
   * @param t1   The start time of this transformation
   * @param x1   The initial x-position of the shape
   * @param y1   The initial y-position of the shape
   * @param w1   The initial width of the shape
   * @param h1   The initial height of the shape
   * @param r1   The initial red color-value of the shape
   * @param g1   The initial green color-value of the shape
   * @param b1   The initial blue color-value of the shape
   * @param t2   The end time of this transformation
   * @param x2   The final x-position of the shape
   * @param y2   The final y-position of the shape
   * @param w2   The final width of the shape
   * @param h2   The final height of the shape
   * @param r2   The final red color-value of the shape
   * @param g2   The final green color-value of the shape
   * @param b2   The final blue color-value of the shape
   * @throws IllegalArgumentException if the shape name doesn't exist in tha
   *                                  animation, or if the motion is invalid as specified above
   */
  void newMotion(String shapeName, int t1, int x1, int y1, int w1,
      int h1, int r1, int g1, int b1, int t2, int x2, int y2, int w2, int h2, int r2, int g2,
      int b2) throws IllegalArgumentException;

  /**
   * Removes a motion from the specified shape from this animation.
   *
   * @param shapeName the name of the shape whose motion will be removed
   * @param t1   The start time of this transformation
   * @param x1   The initial x-position of the shape
   * @param y1   The initial y-position of the shape
   * @param w1   The initial width of the shape
   * @param h1   The initial height of the shape
   * @param r1   The initial red color-value of the shape
   * @param g1   The initial green color-value of the shape
   * @param b1   The initial blue color-value of the shape
   * @param t2   The end time of this transformation
   * @param x2   The final x-position of the shape
   * @param y2   The final y-position of the shape
   * @param w2   The final width of the shape
   * @param h2   The final height of the shape
   * @param r2   The final red color-value of the shape
   * @param g2   The final green color-value of the shape
   * @param b2   The final blue color-value of the shape
   * @throws IllegalArgumentException if the shape name doesn't exist in tha
   *                                  animation, or if the motion is invalid as specified above
   */
  void removeMotionAndAdjust(String shapeName, int t1, int x1, int y1, int w1,
      int h1, int r1, int g1, int b1, int t2, int x2, int y2, int w2, int h2, int r2, int g2,
      int b2) throws IllegalArgumentException;

  void removeMotion(String shapeName, int t1, int x1, int y1, int w1,
      int h1, int r1, int g1, int b1, int t2, int x2, int y2, int w2, int h2, int r2, int g2,
      int b2);


  /**
   * Adds a new shape to the animation. The shape must have a unique name that does not match any of
   * the shapes already existing in the animation.
   *
//   * @param shape the shape to be added to the animation
   * @throws IllegalArgumentException if the shapeName is not unique
   */
  //void addShape(Shape shape) throws IllegalArgumentException;

  void addShape(String name, String type);

  void removeShape(String shapeName) throws IllegalArgumentException;

  /**
   * Gets the animation's map of shapeNames to their shape type.
   *
   * @return the map of shapeNames to their shape type
   */
  Map<String, Shape> getShapes();

  /**
   * Gets the animation's map of shapeNames to their log of motions.
   *
   * @return the map of shapeNames to their log of motions
   */
  Map<String, ArrayList<Motion>> getAnimationLog();

  /**
   * Gets the animation's X component of the canvas.
   *
   * @return the X component of the canvas
   */
  int getCanvasX();

  /**
   * Gets the animation's Y component of the canvas.
   *
   * @return the Y component of the canvas
   */
  int getCanvasY();

  /**
   * Gets the animation's height component of the canvas.
   *
   * @return the height component of the canvas
   */
  int getCanvasHeight();

  /**
   * Gets the animation's width component of the canvas.
   *
   * @return the width component of the canvas
   */
  int getCanvasWidth();

  /**
   * Sets the animation's X component of the canvas.
   *
   * @param x the X component
   */
  void setCanvasX(int x);

  /**
   * Sets the animation's Y component of the canvas.
   *
   * @param y the Y component
   */
  void setCanvasY(int y);

  /**
   * Sets the animation's height component of the canvas.
   *
   * @param h the height component
   */
  void setCanvasHeight(int h);


  /**
   * Sets the animation's width component of the canvas.
   *
   * @param w the width component
   */
  void setCanvasWidth(int w);

  /**
   * Finds a given shape's "shape state" at the given tick. The returned shape's position, color and
   * size are determined through linear interpolation calculations. Throws an exception if the shape
   * state cannot be determined at the tick or if the given shape has no motions.
   *
   * @param shapeName the specific shape to be found
   * @param t         the tick at which the shape will be returned
   * @return the Shape state at the given tick
   * @throws IllegalArgumentException if the shapeName does not exists in the animation, the tick
   *                                  is not within the animationLog's tick boundaries or if the
   *                                  shape has no motions
   */
  Shape findShapeAtTick(String shapeName, int t) throws IllegalArgumentException;

  ArrayList<Integer> getKeyFrameTicks(String shapeName) throws IllegalArgumentException;

//  void modifyKeyFrames(String shapeName, int t, int x, int y, int w, int h, int r, int g, int b);


}
