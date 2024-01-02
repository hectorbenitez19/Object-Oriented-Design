import cs3500.animator.model.Motion;
import cs3500.animator.model.Shape;
import java.util.ArrayList;
import java.util.Map;

/**
 * mock class for the animation model used for testing method callbacks.
 */
public class mockModel implements IMockModel {

  private String command;

  /**
   * constructs the mock model with an empty command string.
   */
  public mockModel() {
    this.command = "";
  }

  @Override
  public String getCommandPressed() {
    return this.command;
  }

  @Override
  public void newMotion(String shapeName, int t1, int x1, int y1, int w1, int h1, int r1, int g1,
      int b1, int t2, int x2, int y2, int w2, int h2, int r2, int g2, int b2)
      throws IllegalArgumentException {
    this.command =
        "adding a new motion for shape: " + shapeName + " startTick: " + t1 + " startx: " + x1
            + " starty: " + y1 + " startw: " + w1 +
            " starth: " + h1 + " startr: " + r1 + " startg: " + g1 + " startb: " + b1 +
            " endTick: " + t2 + " endx: " + x2 + " endy: " + y2 + " endw: " + w2 +
            " endh: " + h2 + " endr: " + r2 + " endg: " + g2 + " endb: " + b2;
  }

  @Override
  public void removeMotionAndAdjust(String shapeName, int t1, int x1, int y1, int w1, int h1,
      int r1, int g1, int b1, int t2, int x2, int y2, int w2, int h2, int r2, int g2, int b2)
      throws IllegalArgumentException {
    this.command = "removing a motion and adjusting for shape: " + shapeName + " startTick: " + t1
        + " startx: " + x1 + " starty: " + y1 + " startw: " + w1 +
        " starth: " + h1 + " startr: " + r1 + " startg: " + g1 + " startb: " + b1 +
        " endTick: " + t2 + " endx: " + x2 + " endy: " + y2 + " endw: " + w2 +
        " endh: " + h2 + " endr: " + r2 + " endg: " + g2 + " endb: " + b2;
  }

  @Override
  public void removeMotion(String shapeName, int t1, int x1, int y1, int w1, int h1, int r1, int g1,
      int b1, int t2, int x2, int y2, int w2, int h2, int r2, int g2, int b2) {
    this.command =
        "removing a motion for shape: " + shapeName + " startTick: " + t1 + " startx: " + x1
            + " starty: " + y1 + " startw: " + w1 +
            " starth: " + h1 + " startr: " + r1 + " startg: " + g1 + " startb: " + b1 +
            " endTick: " + t2 + " endx: " + x2 + " endy: " + y2 + " endw: " + w2 +
            " endh: " + h2 + " endr: " + r2 + " endg: " + g2 + " endb: " + b2;
  }

  @Override
  public void addShape(String name, String type) {
    this.command = "adding a new shape named:" + name + " shape type: " + type;
  }

  @Override
  public void removeShape(String shapeName) throws IllegalArgumentException {
    this.command = "removing shape: " + shapeName;
  }

  @Override
  public Map<String, Shape> getShapes() {
    this.command = "getting all the shape names in the animation";
    return null;
  }

  @Override
  public Map<String, ArrayList<Motion>> getAnimationLog() {
    this.command = "getting the list of motions for all the shapes";

    return null;
  }

  @Override
  public int getCanvasX() {
    this.command = "getting the x coordinate of the canvas";
    return 0;
  }

  @Override
  public int getCanvasY() {
    this.command = "getting the y coordinate of the canvas";
    return 0;
  }

  @Override
  public int getCanvasHeight() {
    this.command = "getting the height of the canvas";
    return 0;
  }

  @Override
  public int getCanvasWidth() {
    this.command = "getting the width of the canvas";
    return 0;
  }

  @Override
  public void setCanvasX(int x) {
    this.command = "setting the canvas x coordinate to be " + x;
  }

  @Override
  public void setCanvasY(int y) {
    this.command = "setting the canvas y coordinate to be " + y;
  }

  @Override
  public void setCanvasHeight(int h) {
    this.command = "setting the canvas height to be " + h;
  }

  @Override
  public void setCanvasWidth(int w) {
    this.command = "setting the canvas width to be " + w;
  }

  @Override
  public Shape findShapeAtTick(String shapeName, int t) throws IllegalArgumentException {
    this.command = "finding the shape state of shape: " + shapeName + " at tick: " + t;
    return null;
  }

  @Override
  public ArrayList<Integer> getKeyFrameTicks(String shapeName) throws IllegalArgumentException {
    this.command = "getting all of the keyFrames for the shape: " + shapeName;
    return null;
  }
}
