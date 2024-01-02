import static org.junit.Assert.assertEquals;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.AnimationModelImpl.Builder;
import cs3500.animator.model.IViewModel;
import cs3500.animator.model.ViewModel;
import cs3500.animator.util.AnimationReader;

import cs3500.animator.view.IView;
import cs3500.animator.view.TextView;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;

import java.util.List;
import org.junit.Test;

/**
 * testing class for the editor view uses a mock of the class to check the method callbacks.
 */
public class editorViewTest {


  @Test
  public void testReceivedRefreshCommand() throws FileNotFoundException {
    File f = new File("smalldemo.txt");
    Reader r = new FileReader(f);
    AnimationModel m = AnimationReader.parseFile(r, new Builder());
    IViewModel vm = new ViewModel(m);
    IEditorMock mock = new EditorMock();
    IView view = new TextView(vm, "");
    StringBuilder animationText = new StringBuilder();
    view.displayView(animationText);
    mock.refresh();
    assertEquals("refreshing view",
        mock.getCommandPressed());

  }

  @Test
  public void testReceivedAddFeaturesCommand() throws FileNotFoundException {
    File f = new File("smalldemo.txt");
    Reader r = new FileReader(f);
    AnimationModel m = AnimationReader.parseFile(r, new Builder());
    IViewModel vm = new ViewModel(m);
    IEditorMock mock = new EditorMock();
    IView view = new TextView(vm, "");
    StringBuilder animationText = new StringBuilder();
    view.displayView(animationText);
    featuresMock controller = new mockController();
    mock.addFeatures(controller);
    assertEquals("adding feature as action listener",
        mock.getCommandPressed());

  }

  @Test
  public void testReceivedAddNamesCommand() throws FileNotFoundException {
    File f = new File("smalldemo.txt");
    Reader r = new FileReader(f);
    AnimationModel m = AnimationReader.parseFile(r, new Builder());
    IViewModel vm = new ViewModel(m);
    IEditorMock mock = new EditorMock();
    IView view = new TextView(vm, "");
    StringBuilder animationText = new StringBuilder();
    view.displayView(animationText);
    mock.addToNamesComboBox("G");
    assertEquals("adding shape: G to the shape names combo box",
        mock.getCommandPressed());

  }

  @Test
  public void testReceivedDeleteNamesCommand() throws FileNotFoundException {
    File f = new File("smalldemo.txt");
    Reader r = new FileReader(f);
    AnimationModel m = AnimationReader.parseFile(r, new Builder());
    IViewModel vm = new ViewModel(m);
    IEditorMock mock = new EditorMock();
    IView view = new TextView(vm, "");
    StringBuilder animationText = new StringBuilder();
    view.displayView(animationText);
    mock.deleteFromNamesComboBox("R");
    assertEquals("removing shape: R from the shape names combo box",
        mock.getCommandPressed());

  }


  @Test
  public void testReceivedAddKeyFramesCommand() throws FileNotFoundException {
    File f = new File("smalldemo.txt");
    Reader r = new FileReader(f);
    AnimationModel m = AnimationReader.parseFile(r, new Builder());
    IViewModel vm = new ViewModel(m);
    IEditorMock mock = new EditorMock();
    IView view = new TextView(vm, "");
    StringBuilder animationText = new StringBuilder();
    view.displayView(animationText);

    List<Integer> expected = new ArrayList<>();
    expected.add(2);
    expected.add(7);
    expected.add(10);
    expected.add(13);
    mock.addKeyFramesToComboBox(expected);
    assertEquals("displaying ticks: 2 7 10 13",
        mock.getCommandPressed());

  }

  @Test
  public void testReceivedComboBoxContainsCommand() throws FileNotFoundException {
    File f = new File("smalldemo.txt");
    Reader r = new FileReader(f);
    AnimationModel m = AnimationReader.parseFile(r, new Builder());
    IViewModel vm = new ViewModel(m);
    IEditorMock mock = new EditorMock();
    IView view = new TextView(vm, "");
    StringBuilder animationText = new StringBuilder();
    view.displayView(animationText);

    mock.keyFramesComboBoxContains(3);
    assertEquals("checking to see if the keyFrame: 3 is in the combo box of shape names",
        mock.getCommandPressed());

  }

  @Test
  public void testReceivedAddToKeyFramesCommand() throws FileNotFoundException {
    File f = new File("smalldemo.txt");
    Reader r = new FileReader(f);
    AnimationModel m = AnimationReader.parseFile(r, new Builder());
    IViewModel vm = new ViewModel(m);
    IEditorMock mock = new EditorMock();
    IView view = new TextView(vm, "");
    StringBuilder animationText = new StringBuilder();
    view.displayView(animationText);

    List<Integer> expected = new ArrayList<>();
    expected.add(2);
    expected.add(7);
    expected.add(10);
    expected.add(13);
    mock.addToKeyFramesComboBox(6);
    assertEquals("adding keyFrame: 6 to the combo box of keyFrames",
        mock.getCommandPressed());

  }

  @Test
  public void testReceivedPauseButtonCommand() throws FileNotFoundException {
    File f = new File("smalldemo.txt");
    Reader r = new FileReader(f);
    AnimationModel m = AnimationReader.parseFile(r, new Builder());
    IViewModel vm = new ViewModel(m);
    IEditorMock mock = new EditorMock();
    IView view = new TextView(vm, "");
    StringBuilder animationText = new StringBuilder();
    view.displayView(animationText);

    ActionEvent pauseEvent = new ActionEvent(animationText,1,"pause button");
    mock.actionPerformed(pauseEvent);
    assertEquals("pause button was pressed",
        mock.getCommandPressed());

  }

  @Test
  public void testReceivedResumeButtonCommand() throws FileNotFoundException {
    File f = new File("smalldemo.txt");
    Reader r = new FileReader(f);
    AnimationModel m = AnimationReader.parseFile(r, new Builder());
    IViewModel vm = new ViewModel(m);
    IEditorMock mock = new EditorMock();
    IView view = new TextView(vm, "");
    StringBuilder animationText = new StringBuilder();
    view.displayView(animationText);

    ActionEvent resumeEvent = new ActionEvent(animationText,1,"resume button");
    mock.actionPerformed(resumeEvent);
    assertEquals("start or resume button was pressed",
        mock.getCommandPressed());

  }

  @Test
  public void testReceivedStartButtonCommand() throws FileNotFoundException {
    File f = new File("smalldemo.txt");
    Reader r = new FileReader(f);
    AnimationModel m = AnimationReader.parseFile(r, new Builder());
    IViewModel vm = new ViewModel(m);
    IEditorMock mock = new EditorMock();
    IView view = new TextView(vm, "");
    StringBuilder animationText = new StringBuilder();
    view.displayView(animationText);

    ActionEvent startEvent = new ActionEvent(animationText,1,"start button");
    mock.actionPerformed(startEvent);
    assertEquals("start or resume button was pressed",
        mock.getCommandPressed());

  }

  @Test
  public void testReceivedRewindButtonCommand() throws FileNotFoundException {
    File f = new File("smalldemo.txt");
    Reader r = new FileReader(f);
    AnimationModel m = AnimationReader.parseFile(r, new Builder());
    IViewModel vm = new ViewModel(m);
    IEditorMock mock = new EditorMock();
    IView view = new TextView(vm, "");
    StringBuilder animationText = new StringBuilder();
    view.displayView(animationText);

    ActionEvent rewindEvent = new ActionEvent(animationText,1,"rewind button");
    mock.actionPerformed(rewindEvent);
    assertEquals("rewind button was pressed",
        mock.getCommandPressed());

  }

  @Test
  public void testReceivedSpeedUpButtonCommand() throws FileNotFoundException {
    File f = new File("smalldemo.txt");
    Reader r = new FileReader(f);
    AnimationModel m = AnimationReader.parseFile(r, new Builder());
    IViewModel vm = new ViewModel(m);
    IEditorMock mock = new EditorMock();
    IView view = new TextView(vm, "");
    StringBuilder animationText = new StringBuilder();
    view.displayView(animationText);

    ActionEvent speedUpEvent = new ActionEvent(animationText,1,"speed up button");
    mock.actionPerformed(speedUpEvent);
    assertEquals("speed up button was pressed",
        mock.getCommandPressed());

  }

  @Test
  public void testReceivedSlowDownButtonCommand() throws FileNotFoundException {
    File f = new File("smalldemo.txt");
    Reader r = new FileReader(f);
    AnimationModel m = AnimationReader.parseFile(r, new Builder());
    IViewModel vm = new ViewModel(m);
    IEditorMock mock = new EditorMock();
    IView view = new TextView(vm, "");
    StringBuilder animationText = new StringBuilder();
    view.displayView(animationText);

    ActionEvent slowDownEvent = new ActionEvent(animationText,1,"slow down button");
    mock.actionPerformed(slowDownEvent);
    assertEquals("slow down button was pressed",
        mock.getCommandPressed());

  }

  @Test
  public void testReceivedLoopBackButtonCommand() throws FileNotFoundException {
    File f = new File("smalldemo.txt");
    Reader r = new FileReader(f);
    AnimationModel m = AnimationReader.parseFile(r, new Builder());
    IViewModel vm = new ViewModel(m);
    IEditorMock mock = new EditorMock();
    IView view = new TextView(vm, "");
    StringBuilder animationText = new StringBuilder();
    view.displayView(animationText);

    ActionEvent loopEvent = new ActionEvent(animationText,1,"loop button");
    mock.actionPerformed(loopEvent);
    assertEquals("loop back button was pressed",
        mock.getCommandPressed());

  }





}
