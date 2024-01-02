package cs3500.animator.controller;


/**
 * Interface for features which handles all of the modifications of the editor view that involves
 * mutating the data in the animation model.
 */
public interface Features {

  /**
   * adds a shape to the model which is then sent to the view and displayed on the combo box that
   * holds the shape names of the shapes in the animation.
   *
   * @param shapeName the name of the new shape
   * @param type      the type of the new shape
   */
  void addShape(String shapeName, String type);

  /**
   * deletes a shape from the model and updates the combo box that holds all the shape names.
   *
   * @param shapeName the name of the shape to be deleted
   */
  void deleteShape(String shapeName);

  /**
   * adds a keyframe to a specified shape at a specified tick.
   *
   * @param shapeName the name of the shape to add a new keyFrame to
   * @param t         the specified tick of the new keyFrame
   */
  void addKeyFrame(String shapeName, int t);

  /**
   * modifies an existing keyFrame for an existing shape.
   *
   * @param shapeName the name of the shape
   * @param t         the keyFrame to be modified
   * @param x         the new x field
   * @param y         the new y field
   * @param w         the new width field
   * @param h         the new height field
   * @param r         the new red field
   * @param g         the new green field
   * @param b         the new blue field
   */
  void modifyKeyFrame(String shapeName, int t, String x, String y, String w, String h, String r,
      String g,
      String b);

  /**
   * deletes a keyFrame for a specified shape at a specified tick.
   *
   * @param shapeName the name of the shape
   * @param t         the tick to be deleted
   */
  void deleteKeyFrame(String shapeName, int t);

  /**
   * displays the ticks of a specified shape to the combo box that holds all the keyframes for a
   * selected shape.
   *
   * @param shapeName the name of the shape
   */
  void displayTicks(String shapeName);

  /**
   * displays all of the attributes of a shape at a specified tick.
   *
   * @param shapeName the name of the shape
   * @param t         the tick of the shape state
   */
  void displayVariables(String shapeName, Integer t);

void go();

}
