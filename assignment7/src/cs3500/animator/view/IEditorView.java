package cs3500.animator.view;

import cs3500.animator.controller.Features;
import java.util.List;

/**
 * interface for the editor view has methods that refresh the animation panel and also has methods
 * that updates the combo boxes and updates the variable text fields and also adds features that are
 * assigned an action listener to respond to action events.
 */
public interface IEditorView {

  /**
   * refreshes the view calls repaint on the animation panel to update it with the new shape states
   * in the animation.
   */
  void refresh();

  /**
   * sets the action listener of the actions that need to go through the controller which is a
   * feature and lets the controller know when an action event occurs and constructs the necessary
   * information and passes it back to the view.
   *
   * @param features the controller that handles the specified action events
   */
  void addFeatures(Features features);

  /**
   * updates the combo box of shapeNames with an updated list of shapes in the animation and
   * displays the updated list to the user.
   *
   * @param shapeName the new updated list of shapes
   */
  void addToNamesComboBox(String shapeName);

  /**
   * deletes the selected shapeName from the combo box that holds the names of shapes in the
   * animation.
   *
   * @param shapeName the selected name that needs to be deleted
   */
  void deleteFromNamesComboBox(String shapeName);

  /**
   * updates the combo box of keyFrames of the selected shape and displays the updated list to the
   * user.
   *
   * @param tick the updated list of ticks
   */
  void addKeyFramesToComboBox(List<Integer> tick);

  /**
   * displays the attributes of the specified shape at a specified keyFrame and displays it to the
   * user.
   *
   * @param x the X coordinate of the shape
   * @param y the Y coordinate of the shape
   * @param w the Width field of the shape
   * @param h the Height field of the shape
   * @param r the Red field of the shapes color
   * @param g the Green field of the shapes color
   * @param b the Blue field of the shapes color
   */
  void displayKeyFrameVariables(int x, int y, int w, int h, int r, int g, int b);

  void deleteFromKeyFramesComboBox(int t);

  boolean keyFramesComboBoxContains(int t);

  void addToKeyFramesComboBox(int t);
}
