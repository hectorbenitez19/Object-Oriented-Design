import static org.junit.Assert.assertEquals;

import cs3500.animator.controller.AnimationController;
import cs3500.animator.controller.Features;
import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.AnimationModelImpl.Builder;
import cs3500.animator.model.IViewModel;
import cs3500.animator.model.ViewModel;
import cs3500.animator.util.AnimationReader;
import cs3500.animator.view.EditorView;
import cs3500.animator.view.IEditorView;
import cs3500.animator.view.IView;
import cs3500.animator.view.TextView;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import org.junit.Test;

/**
 * testing class for the animation controller.
 */
public class AnimationControllerTest {


  @Test
  public void testAddShape() throws FileNotFoundException {
    File f = new File("smalldemo.txt");
    Reader r = new FileReader(f);
    AnimationModel m = AnimationReader.parseFile(r, new Builder());
    IViewModel vm = new ViewModel(m);
    IEditorView ev = new EditorView(1, vm);
    IView view = new TextView(vm, "");
    StringBuilder animationText = new StringBuilder();
    Features controller = new AnimationController(m, ev);
    controller.addShape("G", "rectangle");
    view.displayView(animationText);

    assertEquals("canvas 200 70 360 360\n"
        + "shape R rectangle\n"
        + "motion R 1 200 200 50 100 255 0 0   10 200 200 50 100 255 0 0\n"
        + "motion R 10 200 200 50 100 255 0 0   50 300 300 50 100 255 0 0\n"
        + "motion R 50 300 300 50 100 255 0 0   51 300 300 50 100 255 0 0\n"
        + "motion R 51 300 300 50 100 255 0 0   70 300 300 25 100 255 0 0\n"
        + "motion R 70 300 300 25 100 255 0 0   100 200 200 25 100 255 0 0\n"
        + "shape C ellipse\n"
        + "motion C 6 440 70 120 60 0 0 255   20 440 70 120 60 0 0 255\n"
        + "motion C 20 440 70 120 60 0 0 255   50 440 250 120 60 0 0 255\n"
        + "motion C 50 440 250 120 60 0 0 255   70 440 370 120 60 0 170 85\n"
        + "motion C 70 440 370 120 60 0 170 85   80 440 370 120 60 0 255 0\n"
        + "motion C 80 440 370 120 60 0 255 0   100 440 370 120 60 0 255 0\n"
        + "shape G rectangle", animationText.toString());

  }

  @Test
  public void testDeleteShape() throws FileNotFoundException {
    File f = new File("smalldemo.txt");
    Reader r = new FileReader(f);
    AnimationModel m = AnimationReader.parseFile(r, new Builder());
    IViewModel vm = new ViewModel(m);
    IEditorView ev = new EditorView(1, vm);
    IView view = new TextView(vm, "");
    StringBuilder animationText = new StringBuilder();
    Features controller = new AnimationController(m, ev);
    controller.deleteShape("R");
    view.displayView(animationText);

    assertEquals("canvas 200 70 360 360\n"
            + "shape C ellipse\n"
            + "motion C 6 440 70 120 60 0 0 255   20 440 70 120 60 0 0 255\n"
            + "motion C 20 440 70 120 60 0 0 255   50 440 250 120 60 0 0 255\n"
            + "motion C 50 440 250 120 60 0 0 255   70 440 370 120 60 0 170 85\n"
            + "motion C 70 440 370 120 60 0 170 85   80 440 370 120 60 0 255 0\n"
            + "motion C 80 440 370 120 60 0 255 0   100 440 370 120 60 0 255 0"
        , animationText.toString());

  }

  @Test
  public void testAddKeyFrameInMiddle() throws FileNotFoundException {
    File f = new File("smalldemo.txt");
    Reader r = new FileReader(f);
    AnimationModel m = AnimationReader.parseFile(r, new Builder());
    IViewModel vm = new ViewModel(m);
    IEditorView ev = new EditorView(1, vm);
    IView view = new TextView(vm, "");
    StringBuilder animationText = new StringBuilder();
    Features controller = new AnimationController(m, ev);
    controller.addKeyFrame("R", 25);
    view.displayView(animationText);

    assertEquals("canvas 200 70 360 360\n"
            + "shape R rectangle\n"
            + "motion R 1 200 200 50 100 255 0 0   10 200 200 50 100 255 0 0\n"
            + "motion R 10 200 200 50 100 255 0 0   25 237 237 50 100 255 0 0\n"
            + "motion R 25 237 237 50 100 255 0 0   50 300 300 50 100 255 0 0\n"
            + "motion R 50 300 300 50 100 255 0 0   51 300 300 50 100 255 0 0\n"
            + "motion R 51 300 300 50 100 255 0 0   70 300 300 25 100 255 0 0\n"
            + "motion R 70 300 300 25 100 255 0 0   100 200 200 25 100 255 0 0\n"
            + "shape C ellipse\n"
            + "motion C 6 440 70 120 60 0 0 255   20 440 70 120 60 0 0 255\n"
            + "motion C 20 440 70 120 60 0 0 255   50 440 250 120 60 0 0 255\n"
            + "motion C 50 440 250 120 60 0 0 255   70 440 370 120 60 0 170 85\n"
            + "motion C 70 440 370 120 60 0 170 85   80 440 370 120 60 0 255 0\n"
            + "motion C 80 440 370 120 60 0 255 0   100 440 370 120 60 0 255 0"
        , animationText.toString());

  }


  @Test
  public void testAddKeyFrameAtEnd() throws FileNotFoundException {
    File f = new File("smalldemo.txt");
    Reader r = new FileReader(f);
    AnimationModel m = AnimationReader.parseFile(r, new Builder());
    IViewModel vm = new ViewModel(m);
    IEditorView ev = new EditorView(1, vm);
    IView view = new TextView(vm, "");
    StringBuilder animationText = new StringBuilder();
    Features controller = new AnimationController(m, ev);
    controller.addKeyFrame("R", 105);
    view.displayView(animationText);

    assertEquals("canvas 200 70 360 360\n"
            + "shape R rectangle\n"
            + "motion R 1 200 200 50 100 255 0 0   10 200 200 50 100 255 0 0\n"
            + "motion R 10 200 200 50 100 255 0 0   50 300 300 50 100 255 0 0\n"
            + "motion R 50 300 300 50 100 255 0 0   51 300 300 50 100 255 0 0\n"
            + "motion R 51 300 300 50 100 255 0 0   70 300 300 25 100 255 0 0\n"
            + "motion R 70 300 300 25 100 255 0 0   100 200 200 25 100 255 0 0\n"
            + "motion R 100 200 200 25 100 255 0 0   105 0 0 10 10 255 255 255\n"
            + "shape C ellipse\n"
            + "motion C 6 440 70 120 60 0 0 255   20 440 70 120 60 0 0 255\n"
            + "motion C 20 440 70 120 60 0 0 255   50 440 250 120 60 0 0 255\n"
            + "motion C 50 440 250 120 60 0 0 255   70 440 370 120 60 0 170 85\n"
            + "motion C 70 440 370 120 60 0 170 85   80 440 370 120 60 0 255 0\n"
            + "motion C 80 440 370 120 60 0 255 0   100 440 370 120 60 0 255 0"
        , animationText.toString());

  }


  @Test
  public void testAddKeyFrameBeforeBeginning() throws FileNotFoundException {
    File f = new File("smalldemo.txt");
    Reader r = new FileReader(f);
    AnimationModel m = AnimationReader.parseFile(r, new Builder());
    IViewModel vm = new ViewModel(m);
    IEditorView ev = new EditorView(1, vm);
    IView view = new TextView(vm, "");
    StringBuilder animationText = new StringBuilder();
    Features controller = new AnimationController(m, ev);
    controller.addKeyFrame("C", 2);
    view.displayView(animationText);

    assertEquals("canvas 200 70 360 360\n"
            + "shape R rectangle\n"
            + "motion R 1 200 200 50 100 255 0 0   10 200 200 50 100 255 0 0\n"
            + "motion R 10 200 200 50 100 255 0 0   50 300 300 50 100 255 0 0\n"
            + "motion R 50 300 300 50 100 255 0 0   51 300 300 50 100 255 0 0\n"
            + "motion R 51 300 300 50 100 255 0 0   70 300 300 25 100 255 0 0\n"
            + "motion R 70 300 300 25 100 255 0 0   100 200 200 25 100 255 0 0\n"
            + "shape C ellipse\n"
            + "motion C 2 0 0 10 10 255 255 255   6 440 70 120 60 0 0 255\n"
            + "motion C 6 440 70 120 60 0 0 255   20 440 70 120 60 0 0 255\n"
            + "motion C 20 440 70 120 60 0 0 255   50 440 250 120 60 0 0 255\n"
            + "motion C 50 440 250 120 60 0 0 255   70 440 370 120 60 0 170 85\n"
            + "motion C 70 440 370 120 60 0 170 85   80 440 370 120 60 0 255 0\n"
            + "motion C 80 440 370 120 60 0 255 0   100 440 370 120 60 0 255 0"
        , animationText.toString());

  }


  @Test
  public void testAddKeyFrameToNewShape() throws FileNotFoundException {
    File f = new File("smalldemo.txt");
    Reader r = new FileReader(f);
    AnimationModel m = AnimationReader.parseFile(r, new Builder());
    IViewModel vm = new ViewModel(m);
    IEditorView ev = new EditorView(1, vm);
    IView view = new TextView(vm, "");
    StringBuilder animationText = new StringBuilder();
    Features controller = new AnimationController(m, ev);
    controller.addShape("G", "rectangle");
    controller.addKeyFrame("G", 20);
    view.displayView(animationText);

    assertEquals("canvas 200 70 360 360\n"
            + "shape R rectangle\n"
            + "motion R 1 200 200 50 100 255 0 0   10 200 200 50 100 255 0 0\n"
            + "motion R 10 200 200 50 100 255 0 0   50 300 300 50 100 255 0 0\n"
            + "motion R 50 300 300 50 100 255 0 0   51 300 300 50 100 255 0 0\n"
            + "motion R 51 300 300 50 100 255 0 0   70 300 300 25 100 255 0 0\n"
            + "motion R 70 300 300 25 100 255 0 0   100 200 200 25 100 255 0 0\n"
            + "shape C ellipse\n"
            + "motion C 6 440 70 120 60 0 0 255   20 440 70 120 60 0 0 255\n"
            + "motion C 20 440 70 120 60 0 0 255   50 440 250 120 60 0 0 255\n"
            + "motion C 50 440 250 120 60 0 0 255   70 440 370 120 60 0 170 85\n"
            + "motion C 70 440 370 120 60 0 170 85   80 440 370 120 60 0 255 0\n"
            + "motion C 80 440 370 120 60 0 255 0   100 440 370 120 60 0 255 0\n"
            + "shape G rectangle\n"
            + "motion G 20 0 0 10 10 255 255 255   20 0 0 10 10 255 255 255"
        , animationText.toString());

  }


  @Test
  public void testModifyKeyFramesMiddle() throws FileNotFoundException {
    File f = new File("smalldemo.txt");
    Reader r = new FileReader(f);
    AnimationModel m = AnimationReader.parseFile(r, new Builder());

    // m.modifyKeyFrames("R", 50, 300, 300, 50 , 100,0,255,0);
    IViewModel vm = new ViewModel(m);
    IView view = new TextView(vm, "");
    IEditorView ev = new EditorView(1, vm);
    Features controller = new AnimationController(m, ev);
    StringBuilder animationText = new StringBuilder();
    controller.modifyKeyFrame("R", 50, "300", "300", "50", "100", "0", "255", "0");
    view.displayView(animationText);
    assertEquals("canvas 200 70 360 360\n"
            + "shape R rectangle\n"
            + "motion R 1 200 200 50 100 255 0 0   10 200 200 50 100 255 0 0\n"
            + "motion R 10 200 200 50 100 255 0 0   50 300 300 50 100 0 255 0\n"
            + "motion R 50 300 300 50 100 0 255 0   51 300 300 50 100 255 0 0\n"
            + "motion R 51 300 300 50 100 255 0 0   70 300 300 25 100 255 0 0\n"
            + "motion R 70 300 300 25 100 255 0 0   100 200 200 25 100 255 0 0\n"
            + "shape C ellipse\n"
            + "motion C 6 440 70 120 60 0 0 255   20 440 70 120 60 0 0 255\n"
            + "motion C 20 440 70 120 60 0 0 255   50 440 250 120 60 0 0 255\n"
            + "motion C 50 440 250 120 60 0 0 255   70 440 370 120 60 0 170 85\n"
            + "motion C 70 440 370 120 60 0 170 85   80 440 370 120 60 0 255 0\n"
            + "motion C 80 440 370 120 60 0 255 0   100 440 370 120 60 0 255 0",
        animationText.toString());
  }

  @Test
  public void testModifyKeyFramesBeginning() throws FileNotFoundException {
    File f = new File("smalldemo.txt");
    Reader r = new FileReader(f);
    AnimationModel m = AnimationReader.parseFile(r, new Builder());

    //  m.modifyKeyFrames("R", 1, 250, 250, 50 , 100,255, 0, 0);
    IViewModel vm = new ViewModel(m);
    IView view = new TextView(vm, "");
    IEditorView ev = new EditorView(1, vm);
    Features controller = new AnimationController(m, ev);
    StringBuilder animationText = new StringBuilder();
    controller.modifyKeyFrame("R", 1, "250", "250", "50", "100", "255", "0", "0");
    view.displayView(animationText);
    assertEquals("canvas 200 70 360 360\n"
            + "shape R rectangle\n"
            + "motion R 1 250 250 50 100 255 0 0   10 200 200 50 100 255 0 0\n"
            + "motion R 10 200 200 50 100 255 0 0   50 300 300 50 100 255 0 0\n"
            + "motion R 50 300 300 50 100 255 0 0   51 300 300 50 100 255 0 0\n"
            + "motion R 51 300 300 50 100 255 0 0   70 300 300 25 100 255 0 0\n"
            + "motion R 70 300 300 25 100 255 0 0   100 200 200 25 100 255 0 0\n"
            + "shape C ellipse\n"
            + "motion C 6 440 70 120 60 0 0 255   20 440 70 120 60 0 0 255\n"
            + "motion C 20 440 70 120 60 0 0 255   50 440 250 120 60 0 0 255\n"
            + "motion C 50 440 250 120 60 0 0 255   70 440 370 120 60 0 170 85\n"
            + "motion C 70 440 370 120 60 0 170 85   80 440 370 120 60 0 255 0\n"
            + "motion C 80 440 370 120 60 0 255 0   100 440 370 120 60 0 255 0",
        animationText.toString());
  }

  @Test
  public void testModifyKeyFramesEnd() throws FileNotFoundException {
    File f = new File("smalldemo.txt");
    Reader r = new FileReader(f);
    AnimationModel m = AnimationReader.parseFile(r, new Builder());

  //  m.modifyKeyFrames("R", 100, 300, 300, 50, 100, 0, 255, 0);
    IViewModel vm = new ViewModel(m);
    IView view = new TextView(vm, "");
    IEditorView ev = new EditorView(1, vm);
    Features controller = new AnimationController(m, ev);
    StringBuilder animationText = new StringBuilder();
    controller.modifyKeyFrame("R", 100, "300", "300", "50", "100", "0", "255", "0");
    view.displayView(animationText);
    assertEquals("canvas 200 70 360 360\n"
            + "shape R rectangle\n"
            + "motion R 1 200 200 50 100 255 0 0   10 200 200 50 100 255 0 0\n"
            + "motion R 10 200 200 50 100 255 0 0   50 300 300 50 100 255 0 0\n"
            + "motion R 50 300 300 50 100 255 0 0   51 300 300 50 100 255 0 0\n"
            + "motion R 51 300 300 50 100 255 0 0   70 300 300 25 100 255 0 0\n"
            + "motion R 70 300 300 25 100 255 0 0   100 300 300 50 100 0 255 0\n"
            + "shape C ellipse\n"
            + "motion C 6 440 70 120 60 0 0 255   20 440 70 120 60 0 0 255\n"
            + "motion C 20 440 70 120 60 0 0 255   50 440 250 120 60 0 0 255\n"
            + "motion C 50 440 250 120 60 0 0 255   70 440 370 120 60 0 170 85\n"
            + "motion C 70 440 370 120 60 0 170 85   80 440 370 120 60 0 255 0\n"
            + "motion C 80 440 370 120 60 0 255 0   100 440 370 120 60 0 255 0",
        animationText.toString());
  }

  @Test
  public void testDeleteKeyFrameBeginning() throws FileNotFoundException {
    File f = new File("smalldemo.txt");
    Reader r = new FileReader(f);
    AnimationModel m = AnimationReader.parseFile(r, new Builder());
    IViewModel vm = new ViewModel(m);
    IEditorView ev = new EditorView(1, vm);
    IView view = new TextView(vm, "");
    StringBuilder animationText = new StringBuilder();
    Features controller = new AnimationController(m, ev);
    controller.deleteKeyFrame("R", 1);
    view.displayView(animationText);

    assertEquals("canvas 200 70 360 360\n"
            + "shape R rectangle\n"
            + "motion R 10 200 200 50 100 255 0 0   50 300 300 50 100 255 0 0\n"
            + "motion R 50 300 300 50 100 255 0 0   51 300 300 50 100 255 0 0\n"
            + "motion R 51 300 300 50 100 255 0 0   70 300 300 25 100 255 0 0\n"
            + "motion R 70 300 300 25 100 255 0 0   100 200 200 25 100 255 0 0\n"
            + "shape C ellipse\n"
            + "motion C 6 440 70 120 60 0 0 255   20 440 70 120 60 0 0 255\n"
            + "motion C 20 440 70 120 60 0 0 255   50 440 250 120 60 0 0 255\n"
            + "motion C 50 440 250 120 60 0 0 255   70 440 370 120 60 0 170 85\n"
            + "motion C 70 440 370 120 60 0 170 85   80 440 370 120 60 0 255 0\n"
            + "motion C 80 440 370 120 60 0 255 0   100 440 370 120 60 0 255 0"
        , animationText.toString());

  }

  @Test
  public void testDeleteKeyFrameMiddle() throws FileNotFoundException {
    File f = new File("smalldemo.txt");
    Reader r = new FileReader(f);
    AnimationModel m = AnimationReader.parseFile(r, new Builder());
    IViewModel vm = new ViewModel(m);
    IEditorView ev = new EditorView(1, vm);
    IView view = new TextView(vm, "");
    StringBuilder animationText = new StringBuilder();
    Features controller = new AnimationController(m, ev);
    controller.deleteKeyFrame("R", 50);
    view.displayView(animationText);

    assertEquals("canvas 200 70 360 360\n"
            + "shape R rectangle\n"
            + "motion R 1 200 200 50 100 255 0 0   10 200 200 50 100 255 0 0\n"
            + "motion R 10 200 200 50 100 255 0 0   51 300 300 50 100 255 0 0\n"
            + "motion R 51 300 300 50 100 255 0 0   70 300 300 25 100 255 0 0\n"
            + "motion R 70 300 300 25 100 255 0 0   100 200 200 25 100 255 0 0\n"
            + "shape C ellipse\n"
            + "motion C 6 440 70 120 60 0 0 255   20 440 70 120 60 0 0 255\n"
            + "motion C 20 440 70 120 60 0 0 255   50 440 250 120 60 0 0 255\n"
            + "motion C 50 440 250 120 60 0 0 255   70 440 370 120 60 0 170 85\n"
            + "motion C 70 440 370 120 60 0 170 85   80 440 370 120 60 0 255 0\n"
            + "motion C 80 440 370 120 60 0 255 0   100 440 370 120 60 0 255 0"
        , animationText.toString());

  }

  @Test
  public void testDeleteKeyFrameEnd() throws FileNotFoundException {
    File f = new File("smalldemo.txt");
    Reader r = new FileReader(f);
    AnimationModel m = AnimationReader.parseFile(r, new Builder());
    IViewModel vm = new ViewModel(m);
    IEditorView ev = new EditorView(1, vm);
    IView view = new TextView(vm, "");
    StringBuilder animationText = new StringBuilder();
    Features controller = new AnimationController(m, ev);
    controller.deleteKeyFrame("R", 100);
    view.displayView(animationText);

    assertEquals("canvas 200 70 360 360\n"
            + "shape R rectangle\n"
            + "motion R 1 200 200 50 100 255 0 0   10 200 200 50 100 255 0 0\n"
            + "motion R 10 200 200 50 100 255 0 0   50 300 300 50 100 255 0 0\n"
            + "motion R 50 300 300 50 100 255 0 0   51 300 300 50 100 255 0 0\n"
            + "motion R 51 300 300 50 100 255 0 0   70 300 300 25 100 255 0 0\n"
            + "shape C ellipse\n"
            + "motion C 6 440 70 120 60 0 0 255   20 440 70 120 60 0 0 255\n"
            + "motion C 20 440 70 120 60 0 0 255   50 440 250 120 60 0 0 255\n"
            + "motion C 50 440 250 120 60 0 0 255   70 440 370 120 60 0 170 85\n"
            + "motion C 70 440 370 120 60 0 170 85   80 440 370 120 60 0 255 0\n"
            + "motion C 80 440 370 120 60 0 255 0   100 440 370 120 60 0 255 0"
        , animationText.toString());

  }


  @Test
  public void testReceivedAddShapeCommand() throws FileNotFoundException {
    File f = new File("smalldemo.txt");
    Reader r = new FileReader(f);
    AnimationModel m = AnimationReader.parseFile(r, new Builder());
    IViewModel vm = new ViewModel(m);
    IEditorView ev = new EditorView(1, vm);
    IView view = new TextView(vm, "");
    StringBuilder animationText = new StringBuilder();
    featuresMock controller = new mockController();
    controller.addShape("G", "rectangle");
    view.displayView(animationText);

    assertEquals("add shape command received adding shape G shape type: rectangle",
        controller.getCommandPressed());

  }

  @Test
  public void testReceivedDeleteShapeCommand() throws FileNotFoundException {
    File f = new File("smalldemo.txt");
    Reader r = new FileReader(f);
    AnimationModel m = AnimationReader.parseFile(r, new Builder());
    IViewModel vm = new ViewModel(m);
    IEditorView ev = new EditorView(1, vm);
    IView view = new TextView(vm, "");
    StringBuilder animationText = new StringBuilder();
    featuresMock controller = new mockController();
    controller.deleteShape("R");
    view.displayView(animationText);

    assertEquals("delete shape command received deleting shape: R",
        controller.getCommandPressed());

  }

  @Test
  public void testReceivedAddKeyFrameCommand() throws FileNotFoundException {
    File f = new File("smalldemo.txt");
    Reader r = new FileReader(f);
    AnimationModel m = AnimationReader.parseFile(r, new Builder());
    IViewModel vm = new ViewModel(m);
    IEditorView ev = new EditorView(1, vm);
    IView view = new TextView(vm, "");
    StringBuilder animationText = new StringBuilder();
    featuresMock controller = new mockController();
    controller.addKeyFrame("R", 4);
    view.displayView(animationText);

    assertEquals("add keyFrame command received adding keyFrame at: 4 for shape: R",
        controller.getCommandPressed());

  }

  @Test
  public void testReceivedDeleteKeyFrameCommand() throws FileNotFoundException {
    File f = new File("smalldemo.txt");
    Reader r = new FileReader(f);
    AnimationModel m = AnimationReader.parseFile(r, new Builder());
    IViewModel vm = new ViewModel(m);
    IEditorView ev = new EditorView(1, vm);
    IView view = new TextView(vm, "");
    StringBuilder animationText = new StringBuilder();
    featuresMock controller = new mockController();
    controller.deleteKeyFrame("R", 10);
    view.displayView(animationText);

    assertEquals("delete keyFrame command received deleting keyFrame: 10 for shape: R",
        controller.getCommandPressed());

  }


  @Test
  public void testReceivedModifyKeyFrameCommand() throws FileNotFoundException {
    File f = new File("smalldemo.txt");
    Reader r = new FileReader(f);
    AnimationModel m = AnimationReader.parseFile(r, new Builder());
    IViewModel vm = new ViewModel(m);
    IEditorView ev = new EditorView(1, vm);
    IView view = new TextView(vm, "");
    StringBuilder animationText = new StringBuilder();
    featuresMock controller = new mockController();
    controller.modifyKeyFrame("R", 100, "300",
        "300", "50", "100", "0", "255", "0");
    view.displayView(animationText);

    assertEquals("modify keyFrame command received modifying keyFrame: 100 "
            + "for shape: R new x: 300 new y: 300 new width: 50 "
            + "new height: 100 new red: 0 new green: 255 new blue: 0",
        controller.getCommandPressed());

  }

  @Test
  public void testReceivedDisplayTicksCommand() throws FileNotFoundException {
    File f = new File("smalldemo.txt");
    Reader r = new FileReader(f);
    AnimationModel m = AnimationReader.parseFile(r, new Builder());
    IViewModel vm = new ViewModel(m);
    IEditorView ev = new EditorView(1, vm);
    IView view = new TextView(vm, "");
    StringBuilder animationText = new StringBuilder();
    featuresMock controller = new mockController();
    controller.displayTicks("R");
    view.displayView(animationText);

    assertEquals("display ticks command received displaying ticks for shape: R",
        controller.getCommandPressed());

  }


  @Test
  public void testReceivedDisplayVariablesCommand() throws FileNotFoundException {
    File f = new File("smalldemo.txt");
    Reader r = new FileReader(f);
    AnimationModel m = AnimationReader.parseFile(r, new Builder());
    IViewModel vm = new ViewModel(m);
    IEditorView ev = new EditorView(1, vm);
    IView view = new TextView(vm, "");
    StringBuilder animationText = new StringBuilder();
    featuresMock controller = new mockController();
    controller.displayTicks("R");
    view.displayView(animationText);

    assertEquals("display ticks command received displaying ticks for shape: R",
        controller.getCommandPressed());

  }


}
