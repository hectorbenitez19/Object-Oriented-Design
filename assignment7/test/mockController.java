/**
 * mock controller for the animation controller class receives a command an outputs the
 * corresponding string used to test the listener to see if it received the correct command.
 */
public class mockController implements featuresMock {

  private String command;

  public mockController() {
    this.command = "";
  }

  @Override
  public String getCommandPressed() {
    return this.command;
  }

  @Override
  public void addShape(String shapeName, String type) {
    this.command = "add shape command received adding shape " + shapeName + " shape type: " + type;
  }

  @Override
  public void deleteShape(String shapeName) {
    this.command = "delete shape command received deleting shape: " + shapeName;
  }

  @Override
  public void addKeyFrame(String shapeName, int t) {
    this.command =
        "add keyFrame command received adding keyFrame at: " + t + " for shape: " + shapeName;
  }

  @Override
  public void modifyKeyFrame(String shapeName, int t, String x, String y, String w, String h,
      String r, String g, String b) {
    this.command =
        "modify keyFrame command received modifying keyFrame: " + t + " for shape: " + shapeName
            + " new x: " + x + " new y: " + y + " new width: " + w + " new height: " + h
            + " new red: "
            + r +
            " new green: " + g + " new blue: " + b;
  }

  @Override
  public void deleteKeyFrame(String shapeName, int t) {
    this.command =
        "delete keyFrame command received deleting keyFrame: " + t + " for shape: " + shapeName;
  }

  @Override
  public void displayTicks(String shapeName) {
    this.command = "display ticks command received displaying ticks for shape: " + shapeName;
  }

  @Override
  public void displayVariables(String shapeName, Integer t) {
    this.command =
        "display variables command received displaying attributes at keyFrame: " + t
            + " for shape: "
            + shapeName;
  }

  @Override
  public void go() {

  }
}
