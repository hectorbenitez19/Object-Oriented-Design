import cs3500.animator.controller.Features;
import java.awt.event.ActionEvent;
import java.util.List;

public class EditorMock implements IEditorMock {

  private String command;

  public EditorMock() {
    this.command = "";
  }

  @Override
  public String getCommandPressed() {
    return this.command;
  }

  @Override
  public void refresh() {
  this.command = "refreshing view";
  }

  @Override
  public void addFeatures(Features features) {
  this.command = "adding feature as action listener";
  }

  @Override
  public void addToNamesComboBox(String shapeName) {
  this.command = "adding shape: " + shapeName + " to the shape names combo box";
  }

  @Override
  public void deleteFromNamesComboBox(String shapeName) {
  this.command = "removing shape: " +shapeName + " from the shape names combo box";
  }

  @Override
  public void addKeyFramesToComboBox(List<Integer> tick) {
    this.command = "displaying ticks:";
    for(Integer i : tick) {
      this.command = this.command  + " " + i;
    }
  }

  @Override
  public void displayKeyFrameVariables(int x, int y, int w, int h, int r, int g, int b) {
  this.command = "displaying keyFrame variables: " + x + " " + y + " " + w + " " + h + " "
      + r + " " + g + " " + b + " ";
  }

  @Override
  public void deleteFromKeyFramesComboBox(int t) {
  this.command = "deleting the keyFrame: " + t + " from the combo box of shape names";
  }

  @Override
  public boolean keyFramesComboBoxContains(int t) {
    this.command = "checking to see if the keyFrame: " + t + " is in the combo box of shape names";
    return false;
  }

  @Override
  public void addToKeyFramesComboBox(int t) {
  this.command = "adding keyFrame: " + t + " to the combo box of keyFrames";
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
      case "pause button":
        this.command = "pause button was pressed";
        break;
      case "resume button":
      case "start button":
        this.command = "start or resume button was pressed";
        break;
      case "rewind button":
        this.command = "rewind button was pressed";
        break;
      case "speed up button":
        this.command = "speed up button was pressed";
        break;
      case "slow down button":
      this.command = "slow down button was pressed";
        break;
      case "loop button":
      this.command = "loop back button was pressed";

    }
  }
}
