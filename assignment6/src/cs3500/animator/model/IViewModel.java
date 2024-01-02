package cs3500.animator.model;

import java.util.ArrayList;
import java.util.Map;

/**
 * A interface that holds a read only version of the Animation model and provides the models
 * information to each of the views.
 */
public interface IViewModel {

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
   * returns a list of shape states at the specified tick. Returns each shape's position, color and
   * size that are determined through linear interpolation calculations.
   *
   * @param t the tick at which each shape will be returned
   * @return the list of shape states at the specified tick
   */
  ArrayList<Shape> getShapesAtTick(int t);

}
