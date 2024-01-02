
/**
 * This interface represents a model for an animation. It contains the various operations that an
 * animation can perform, such as moving, changing dimensions, and changing color while the
 * animation is in action, and all motions of a shape must not overlap (from time 1 to 10, a shape
 * can't be moving in two different directions). A shape must also not be able to teleport, or
 * change it's state in an instantaneous moment. One object of the model represents one animation.
 */
public interface AnimationModel {

  /**
   * Adds a new motion for the specified shape to this animation model. A motion can only be added
   * as long as its starting and ending ticks don't overlap with existing motions for the shape, and
   * the motion being added must be adjacent to the shape's first or last motion, and the shape
   * states must agree with each other to ensure no teleportation or instantaneous changes.
   *
   * @param shapeName the shape to which the motion will be added
   * @param motion    the motion added
   * @throws IllegalArgumentException if the motion is null, if the shape name doesn't exist in tha
   *                                  animation, or if the motion is invalid as specified above
   */
  void newMotion(String shapeName, Motion motion);


  /**
   * Adds a new shape to the animation. The shape must have a unique name that does not match any of
   * the shapes already existing in the animation.
   *
   * @param shape the shape to be added to the animation
   * @throws IllegalArgumentException if the shapeName is not unique
   */
  void addShape(Shape shape);

  /**
   * Outputs the motion logs for all shapes in the animation in String format.
   *
   * @return the motion descriptions for all shapes in the animation
   */
  String outputDescriptions();



}
