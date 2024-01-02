import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.AnimationModelImpl;
import cs3500.animator.model.AnimationModelImpl.Builder;
import cs3500.animator.model.Motion;
import cs3500.animator.model.Position2D;
import cs3500.animator.model.Shape;
import cs3500.animator.model.ShapeColor;
import cs3500.animator.model.Size;
import cs3500.animator.model.Square;
import cs3500.animator.model.IViewModel;
import cs3500.animator.util.AnimationReader;
import cs3500.animator.view.IView;
import cs3500.animator.view.TextView;
import cs3500.animator.model.ViewModel;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;

/**
 * testing class for the AnimationModelImpl class.
 */
public class AnimationModelImplTest {

  Size redSquareSize = new Size(100, 100);
  Position2D redSquarePosition = new Position2D(50, 50);
  ShapeColor redSquareColor = new ShapeColor(255, 0, 0);
  Shape redSquare = new Square("R", redSquarePosition, redSquareSize, redSquareColor);


  Size blueSquareSize = new Size(60, 60);
  Position2D blueSquarePosition = new Position2D(20, 90);
  ShapeColor blueSquareColor = new ShapeColor(0, 0, 255);
  Shape blueSquare = new Square("B", blueSquarePosition, blueSquareSize, blueSquareColor);

  Size greenSquareSize = new Size(20, 20);
  Position2D greenSquarePosition = new Position2D(40, 10);
  ShapeColor greenSquareColor = new ShapeColor(0, 255, 0);
  Shape greenSquare = new Square("G", greenSquarePosition, greenSquareSize,
      greenSquareColor);


  AnimationModelImpl animationModel = new AnimationModelImpl();


  @Test
  public void testInvalidSizeConstructor() {
    try {
      new Size(0, 0);
      fail("Invalid constructor should have thrown exception");
    } catch (IllegalArgumentException e) {
      assertEquals("Width and height must be positive", e.getMessage());
    }
    try {
      new Size(-1, 5);
      fail("Invalid constructor should have thrown exception");
    } catch (IllegalArgumentException e) {
      assertEquals("Width and height must be positive", e.getMessage());
    }
    try {
      new Size(4, -5);
      fail("Invalid constructor should have thrown exception");
    } catch (IllegalArgumentException e) {
      assertEquals("Width and height must be positive", e.getMessage());
    }
  }

  @Test
  public void testInvalidColorConstructor() {
    try {
      new ShapeColor(256, 0, 0);
      fail("Invalid constructor should have thrown exception");
    } catch (IllegalArgumentException e) {
      assertEquals("Not a valid color", e.getMessage());
    }
    try {
      new ShapeColor(200, 300, 20);
      fail("Invalid constructor should have thrown exception");
    } catch (IllegalArgumentException e) {
      assertEquals("Not a valid color", e.getMessage());
    }
    try {
      new ShapeColor(250, 0, 290);
      fail("Invalid constructor should have thrown exception");
    } catch (IllegalArgumentException e) {
      assertEquals("Not a valid color", e.getMessage());
    }
    try {
      new ShapeColor(-4, -6, 290);
      fail("Invalid constructor should have thrown exception");
    } catch (IllegalArgumentException e) {
      assertEquals("Not a valid color", e.getMessage());
    }
  }

  @Test
  public void testOverriddenEqualsShape() {
    Shape redSquare2 = new Square("R", redSquarePosition, redSquareSize, redSquareColor);
    assertEquals(redSquare, redSquare2);
  }

  @Test
  public void testOverriddenEqualsColor() {
    ShapeColor color = new ShapeColor(255, 0, 0);
    assertEquals(redSquareColor, color);
  }

  @Test
  public void testOverriddenEqualsPosition() {
    Position2D p = new Position2D(50, 50);
    assertEquals(redSquarePosition, p);
  }

  @Test
  public void testOverriddenEqualsSize() {
    Size s = new Size(100, 100);
    assertEquals(redSquareSize, s);
  }

  @Test
  public void testAddShape() {
    animationModel.addShape("R", "square");
    IViewModel vm = new ViewModel(animationModel);
    TextView view = new TextView(vm, "");
    StringBuilder animationText = new StringBuilder();
    view.displayView(animationText);
    assertEquals("canvas 100 100 100 100\n"
        + "shape R Square", animationText.toString());
  }

  @Test
  public void testEmptyAnimation() {
    IViewModel vm = new ViewModel(animationModel);
    TextView view = new TextView(vm, "");
    StringBuilder animationText = new StringBuilder();
    view.displayView(animationText);
    assertEquals("canvas 100 100 100 100", animationText.toString());
  }

  @Test
  public void testAddMultipleShapes() {
    animationModel.addShape("R", "square");
    animationModel.addShape("B", "square");
    animationModel.addShape("G", "square");
    IViewModel vm = new ViewModel(animationModel);
    TextView view = new TextView(vm, "");
    StringBuilder animationText = new StringBuilder();
    view.displayView(animationText);
    assertEquals("canvas 100 100 100 100\n"
        + "shape R Square\n"
        + "shape B Square\n"
        + "shape G Square", animationText.toString());

  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddShapeThatAlreadyExists() {
    animationModel.addShape("R", "square");
    animationModel.addShape("B", "square");
    animationModel.addShape("R", "square");
  }

  @Test
  public void testMotionGetStartAndEndShape() {
    Size redSquareSize2 = new Size(150, 150);
    Shape redSquare2 = new Square("R", redSquarePosition, redSquareSize2,
        redSquareColor);

    Motion m = new Motion(redSquare, 1, redSquare2, 2);
    assertEquals(redSquare, m.getStartShape());
    assertEquals(redSquare2, m.getEndShape());
  }

  @Test
  public void testMotionToString() {
    Size redSquareSize2 = new Size(150, 150);
    Shape redSquare2 = new Square("R", redSquarePosition, redSquareSize2,
        redSquareColor);

    Motion m = new Motion(redSquare, 1, redSquare2, 2);
    assertEquals("motion R 1 50 50 100 100 255 0 0   2 50 50 150 150 255 0 0",
        m.toString());
  }

  @Test
  public void testInvalidMotionSameTicks() {
    Shape redSquare2 = new Square("R", blueSquarePosition, blueSquareSize,
        blueSquareColor);
    try {
      new Motion(redSquare, 1, redSquare2, 1);
      fail("Invalid constructor should have thrown exception");
    } catch (IllegalArgumentException e) {
      assertEquals("You can't have a motion that changes a shape in an "
          + "instant", e.getMessage());
    }

  }

  @Test
  public void testInvalidMotionDifferentShapeNames() {
    Shape redSquare2 = new Square("red", blueSquarePosition, blueSquareSize,
        blueSquareColor);
    try {
      new Motion(redSquare, 1, redSquare2, 5);
      fail("Invalid constructor should have thrown exception");
    } catch (IllegalArgumentException e) {
      assertEquals("A motion must change the state of the same shape name", e.getMessage());
    }

  }

  @Test
  public void testNewMotion() {
    animationModel.addShape("R", "square");

    animationModel.newMotion("R", 1, 50, 50, 100, 100, 255, 0, 0,
        10, 50, 50, 150, 150, 255, 0, 0);
    IViewModel vm = new ViewModel(animationModel);
    TextView view = new TextView(vm, "");
    StringBuilder animationText = new StringBuilder();
    view.displayView(animationText);
    assertEquals("canvas 100 100 100 100\n"
        + "shape R Square\n"
        + "motion R 1 50 50 100 100 255 0 0   10 50 50 150 150 255 0 0", animationText.toString());

  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidNewMotionShapeNonExistent() {
    animationModel.addShape("R", "square");

    animationModel.newMotion("B", 1, 50, 50, 100, 100, 255, 0, 0,
        10, 50, 50, 150, 150, 255, 0, 0);
  }

  @Test
  public void testMultipleNewMotions() {
    animationModel.addShape("R", "square");

    animationModel.newMotion("R", 1, 50, 50, 100, 100, 255, 0, 0,
        10, 50, 50, 150, 150, 255, 0, 0);
    animationModel.newMotion("R", 10, 50, 50, 150, 150, 255, 0, 0,
        15, 55, 50, 150, 150, 255, 0, 0);
    animationModel.newMotion("R", 15, 55, 50, 150, 150, 255, 0, 0,
        20, 100, 100, 150, 150, 0, 255, 0);
    IViewModel vm = new ViewModel(animationModel);
    TextView view = new TextView(vm, "");
    StringBuilder animationText = new StringBuilder();
    view.displayView(animationText);
    assertEquals("canvas 100 100 100 100\n"
            + "shape R Square\n"
            + "motion R 1 50 50 100 100 255 0 0   10 50 50 150 150 255 0 0\n"
            + "motion R 10 50 50 150 150 255 0 0   15 55 50 150 150 255 0 0\n"
            + "motion R 15 55 50 150 150 255 0 0   20 100 100 150 150 0 255 0",
        animationText.toString());

  }

  @Test
  public void testMultipleNewMotionsNonConsecutiveOrder() {
    animationModel.addShape("R", "square");

    animationModel.newMotion("R", 10, 50, 50, 150, 150, 255, 0, 0,
        15, 55, 50, 150, 150, 255, 0, 0);
    animationModel.newMotion("R", 1, 50, 50, 100, 100, 255, 0, 0,
        10,50, 50, 150, 150, 255, 0, 0);
    animationModel.newMotion("R",15, 55, 50, 150, 150, 255, 0, 0,
        20, 100, 100, 150, 150, 0, 255, 0);
    IViewModel vm = new ViewModel(animationModel);
    TextView view = new TextView(vm, "");
    StringBuilder animationText = new StringBuilder();
    view.displayView(animationText);
    assertEquals("canvas 100 100 100 100\n"
            + "shape R Square\n"
            + "motion R 1 50 50 100 100 255 0 0   10 50 50 150 150 255 0 0\n"
            + "motion R 10 50 50 150 150 255 0 0   15 55 50 150 150 255 0 0\n"
            + "motion R 15 55 50 150 150 255 0 0   20 100 100 150 150 0 255 0",
        animationText.toString());

  }


  @Test
  public void testValidMotionTwoDifferentShapes() {
    animationModel.addShape("R", "square");

    animationModel.newMotion("R", 10, 50, 50, 150, 150, 255, 0,0,
        15, 55, 50, 150, 150, 255, 0, 0);
    animationModel.newMotion("R", 1, 50, 50, 100, 100, 255, 0, 0,
        10, 50, 50, 150, 150, 255, 0,0);
    animationModel.newMotion("R", 15, 55, 50, 150, 150, 255, 0, 0,
        20, 100, 100, 150, 150, 0, 255, 0);

    animationModel.addShape("B", "square");

    animationModel.newMotion("B", 1, 20, 90, 60, 60, 0, 0, 255,
        10, 70, 70, 60, 60, 0, 0, 255);

    IViewModel vm = new ViewModel(animationModel);
    TextView view = new TextView(vm, "");
    StringBuilder animationText = new StringBuilder();
    view.displayView(animationText);
    assertEquals("canvas 100 100 100 100\n"
            + "shape R Square\n"
            + "motion R 1 50 50 100 100 255 0 0   10 50 50 150 150 255 0 0\n"
            + "motion R 10 50 50 150 150 255 0 0   15 55 50 150 150 255 0 0\n"
            + "motion R 15 55 50 150 150 255 0 0   20 100 100 150 150 0 255 0\n"
            + "shape B Square\n"
            + "motion B 1 20 90 60 60 0 0 255   10 70 70 60 60 0 0 255",
        animationText.toString());
  }


  @Test
  public void testValidMotionTwoDifferentShapesDifferentOrder() {
    animationModel.addShape("R", "square");

    animationModel.newMotion("R", 10, 50, 50, 150, 150, 255, 0, 0,
        15, 55, 50, 150, 150, 255, 0, 0);
    animationModel.addShape("B", "square");

    animationModel.newMotion("B", 1, 20, 90, 60, 60, 0, 0, 255,
        10, 70, 70, 60, 60, 0, 0, 255);
    animationModel.newMotion("R", 1, 50, 50, 100, 100, 255, 0, 0,
        10, 50, 50, 150, 150, 255, 0, 0);
    animationModel.newMotion("R", 15, 55, 50, 150, 150, 255, 0, 0,
        20, 100, 100, 150, 150, 0, 255, 0);

    IViewModel vm = new ViewModel(animationModel);
    TextView view = new TextView(vm, "");
    StringBuilder animationText = new StringBuilder();
    view.displayView(animationText);
    assertEquals("canvas 100 100 100 100\n"
            + "shape R Square\n"
            + "motion R 1 50 50 100 100 255 0 0   10 50 50 150 150 255 0 0\n"
            + "motion R 10 50 50 150 150 255 0 0   15 55 50 150 150 255 0 0\n"
            + "motion R 15 55 50 150 150 255 0 0   20 100 100 150 150 0 255 0\n"
            + "shape B Square\n"
            + "motion B 1 20 90 60 60 0 0 255   10 70 70 60 60 0 0 255",
        animationText.toString());
  }


  @Test
  public void testValidMotionsThreeDifferentShapes() {
    animationModel.addShape("R", "square");

    animationModel.newMotion("R", 10, 50, 50, 150, 150, 255, 0, 0,
        15, 55, 50, 150, 150, 255, 0, 0);
    animationModel.addShape("B", "square");

    animationModel.newMotion("B", 1, 20, 90, 60, 60, 0, 0, 255,
        10, 70, 70, 60, 60, 0, 0, 255);
    animationModel.newMotion("R", 1, 50, 50, 100, 100, 255, 0, 0,
        10, 50, 50, 150, 150, 255, 0, 0);
    animationModel.addShape("G", "square");

    animationModel.newMotion("G", 15, 40, 10, 20, 20, 0, 255,0,
        20, 90, 75, 20, 20, 0, 255, 0);
    animationModel.newMotion("R", 15, 55, 50, 150, 150, 255, 0, 0,
        20, 100, 100, 150, 150, 0, 255, 0);
    animationModel.newMotion("B", 10, 70, 70, 60, 60, 0, 0, 255,
        15, 70, 70, 30, 30, 0, 0, 255);

    IViewModel vm = new ViewModel(animationModel);
    TextView view = new TextView(vm, "");
    StringBuilder animationText = new StringBuilder();
    view.displayView(animationText);
    assertEquals("canvas 100 100 100 100\n"
            + "shape R Square\n"
            + "motion R 1 50 50 100 100 255 0 0   10 50 50 150 150 255 0 0\n"
            + "motion R 10 50 50 150 150 255 0 0   15 55 50 150 150 255 0 0\n"
            + "motion R 15 55 50 150 150 255 0 0   20 100 100 150 150 0 255 0\n"
            + "shape B Square\n"
            + "motion B 1 20 90 60 60 0 0 255   10 70 70 60 60 0 0 255\n"
            + "motion B 10 70 70 60 60 0 0 255   15 70 70 30 30 0 0 255\n"
            + "shape G Square\n"
            + "motion G 15 40 10 20 20 0 255 0   20 90 75 20 20 0 255 0",
        animationText.toString());
  }



  @Test(expected = IllegalArgumentException.class)
  public void testNewMotionOverlap() {
    animationModel.addShape("R", "square");

    animationModel.newMotion("R", 5, 50, 50, 100, 100, 255, 0, 0,
        15, 50, 50, 150, 150, 255, 0, 0);
    animationModel.newMotion("R", 1, 50, 50, 100, 100, 255, 0, 0,
        10, 50, 50, 150, 150, 255, 0, 0);

  }

  @Test(expected = IllegalArgumentException.class)
  public void testNewMotionMatchingShapeStates() {
    animationModel.addShape("R", "square");

    animationModel.newMotion("R", 1, 50, 50, 100, 100, 255, 0, 0,
        10, 50, 50, 150, 150, 255, 0, 0);
    animationModel.newMotion("R", 10, 50, 50, 100, 100, 255, 0, 0,
        15, 50, 50, 150, 150, 255, 0, 0);

  }

  @Test(expected = IllegalArgumentException.class)
  public void testNewMotionMatchingShapeStatesDifferentOrder() {
    animationModel.addShape("R", "square");

    animationModel.newMotion("R", 10, 50, 50, 100, 100, 255, 0, 0,
        15, 50, 50, 150, 150, 255, 0, 0);
    animationModel.newMotion("R", 1, 50, 50, 100, 100, 255, 0, 0,
        10, 50, 50, 150, 150, 255, 0, 0);

  }


  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMotionDifferentStartAndEndShapes() {
    animationModel.addShape("R", "square");

    new Motion(redSquare, 1, blueSquare, 10);

  }


  @Test
  public void testGetPosition() {
    Size redSquareSize2 = new Size(150, 150);
    Shape redSquare2 = new Square("R", redSquarePosition, redSquareSize2,
        redSquareColor);

    assertEquals(redSquarePosition, redSquare2.getPosition());

    assertEquals(50, redSquare2.getPosition().getX(), 0);
    assertEquals(50, redSquare2.getPosition().getY(), 0);

  }

  @Test
  public void testGetSize() {
    Size redSquareSize2 = new Size(150, 150);
    Shape redSquare2 = new Square("R", redSquarePosition, redSquareSize2,
        redSquareColor);

    assertEquals(150, redSquare2.getSize().getWidth());

    assertEquals(150, redSquare2.getSize().getHeight());
  }


  @Test
  public void testGetColor() {

    assertEquals(255, redSquare.getColor().getRed());

    assertEquals(255, blueSquare.getColor().getBlue());

    assertEquals(255, greenSquare.getColor().getGreen());

  }

  @Test
  public void testGetTicks() {
    Size redSquareSize2 = new Size(150, 150);
    Shape redSquare2 = new Square("R", redSquarePosition, redSquareSize2,
        redSquareColor);

    animationModel.addShape("R", "square");

    Motion log1 = new Motion(redSquare, 1, redSquare2, 10);

    animationModel.newMotion("R", 1, 50, 50, 100, 100, 255, 0, 0,
        10, 50, 50, 150, 150, 255, 0, 0);

    assertEquals(1, log1.getStartTick());

    assertEquals(10, log1.getEndTick());

  }


  @Test
  public void testGetShapes() {
    Size redSquareSize2 = new Size(150, 150);
    Shape redSquare2 = new Square("R", redSquarePosition, redSquareSize2,
        redSquareColor);

    animationModel.addShape("R", "square");

    Motion log1 = new Motion(redSquare, 1, redSquare2, 10);

    animationModel.newMotion("R", 1, 50, 50, 100, 100, 255, 0, 0,
        10, 50, 50, 150, 150, 255, 0, 0);

    assertEquals(redSquare, log1.getStartShape());

    assertEquals(redSquare2, log1.getEndShape());

  }

  @Test
  public void testGetKeyFrameTicks() throws FileNotFoundException {
    File f = new File("smalldemo.txt");
    Reader r = new FileReader(f);
    AnimationModel m = AnimationReader.parseFile(r, new Builder());

    List<Integer> expected1 = Arrays.asList(1, 10, 50, 51, 70, 100);
    assertEquals(expected1, m.getKeyFrameTicks("R"));

    List<Integer> expected2 = Arrays.asList(6, 20, 50, 70, 80, 100);
    assertEquals(expected2, m.getKeyFrameTicks("C"));

  }

  @Test
  public void testRemoveMotionMiddle() throws FileNotFoundException {
    File f = new File("smalldemo.txt");
    Reader r = new FileReader(f);
    AnimationModel m = AnimationReader.parseFile(r, new Builder());

    m.removeMotionAndAdjust("R",10,200,200,50,100,255,0,0,50,300,300,50,100,255,0,0);
    IViewModel vm = new ViewModel(m);
    IView view = new TextView(vm, "");
    StringBuilder animationText = new StringBuilder();
    view.displayView(animationText);
    assertEquals("canvas 200 70 360 360\n"
            + "shape R rectangle\n"
            + "motion R 1 200 200 50 100 255 0 0   50 300 300 50 100 255 0 0\n"
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
  public void testRemoveMotionAndAdjustBeginning() throws FileNotFoundException {
    File f = new File("smalldemo.txt");
    Reader r = new FileReader(f);
    AnimationModel m = AnimationReader.parseFile(r, new Builder());

    m.removeMotionAndAdjust("R",1,200,200,50,100,255,0,0,10,200,200,50,100,255,0,0);
    IViewModel vm = new ViewModel(m);
    IView view = new TextView(vm, "");
    StringBuilder animationText = new StringBuilder();
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
            + "motion C 80 440 370 120 60 0 255 0   100 440 370 120 60 0 255 0",
        animationText.toString());



  }

  @Test
  public void testRemoveAndAdjustMotionEnd() throws FileNotFoundException {
    File f = new File("smalldemo.txt");
    Reader r = new FileReader(f);
    AnimationModel m = AnimationReader.parseFile(r, new Builder());

    m.removeMotionAndAdjust("R",70,300,300,25,100,255,0,0,100,200,200,25,100,255,0,0);
    IViewModel vm = new ViewModel(m);
    IView view = new TextView(vm, "");
    StringBuilder animationText = new StringBuilder();
    view.displayView(animationText);
    assertEquals("canvas 200 70 360 360\n"
            + "shape R rectangle\n"
            + "motion R 1 200 200 50 100 255 0 0   10 200 200 50 100 255 0 0\n"
            + "motion R 10 200 200 50 100 255 0 0   50 300 300 50 100 255 0 0\n"
            + "motion R 50 300 300 50 100 255 0 0   51 300 300 50 100 255 0 0\n"
            + "motion R 51 300 300 50 100 255 0 0   100 200 200 25 100 255 0 0\n"
            + "shape C ellipse\n"
            + "motion C 6 440 70 120 60 0 0 255   20 440 70 120 60 0 0 255\n"
            + "motion C 20 440 70 120 60 0 0 255   50 440 250 120 60 0 0 255\n"
            + "motion C 50 440 250 120 60 0 0 255   70 440 370 120 60 0 170 85\n"
            + "motion C 70 440 370 120 60 0 170 85   80 440 370 120 60 0 255 0\n"
            + "motion C 80 440 370 120 60 0 255 0   100 440 370 120 60 0 255 0",
        animationText.toString());



  }

  @Test
  public void testReceivedNewMotionCommand() {
    IMockModel mock = new mockModel();

    mock.newMotion("R",1,200,200,50,100,
        255,0,0,10,200,200,50,100,255,0,0);

    assertEquals("adding a new motion for shape: R startTick: 1 startx: 200 "
            + "starty: 200 startw: 50 starth: 100 startr: 255 startg: 0 startb: 0 "
            + "endTick: 10 endx: 200 endy: 200 endw: 50 endh: 100 endr: 255 endg: 0 endb: 0",
        mock.getCommandPressed());

  }

  @Test
  public void testReceivedRemoveMotionAndAdjustCommand() {
    IMockModel mock = new mockModel();

    mock.removeMotionAndAdjust("R",1,200,200,50,100,
        255,0,0,10,200,200,50,100,255,0,0);

    assertEquals("removing a motion and adjusting for shape: R startTick: 1"
            + " startx: 200 starty: 200 startw: 50 starth: 100 startr: 255 startg: 0 startb: 0 "
            + "endTick: 10 endx: 200 endy: 200 endw: 50 endh: 100 endr: 255 endg: 0 endb: 0",
        mock.getCommandPressed());

  }

  @Test
  public void testReceivedRemoveMotionCommand() {
    IMockModel mock = new mockModel();

    mock.removeMotion("R",1,200,200,50,100,
        255,0,0,10,200,200,50,100,255,0,0);

    assertEquals("removing a motion for shape: R startTick: 1"
            + " startx: 200 starty: 200 startw: 50 starth: 100 startr: 255 startg: 0 startb: 0 "
            + "endTick: 10 endx: 200 endy: 200 endw: 50 endh: 100 endr: 255 endg: 0 endb: 0",
        mock.getCommandPressed());

  }

  @Test
  public void testReceivedAddShapeCommand() {
    IMockModel mock = new mockModel();

    mock.addShape("R","rectangle");

    assertEquals("adding a new shape named:R shape type: rectangle",
        mock.getCommandPressed());

  }

  @Test
  public void testReceivedRemoveShapeCommand() {
    IMockModel mock = new mockModel();

    mock.removeShape("R");

    assertEquals("removing shape: R",
        mock.getCommandPressed());

  }

  @Test
  public void testReceivedGetShapeCommand() {
    IMockModel mock = new mockModel();

    mock.getShapes();

    assertEquals("getting all the shape names in the animation",
        mock.getCommandPressed());

  }

  @Test
  public void testReceivedGetAnimationLogCommand() {
    IMockModel mock = new mockModel();

    mock.getAnimationLog();

    assertEquals("getting the list of motions for all the shapes",
        mock.getCommandPressed());

  }

  @Test
  public void testReceivedGetCanvasXCommand() {
    IMockModel mock = new mockModel();

    mock.getCanvasX();

    assertEquals("getting the x coordinate of the canvas",
        mock.getCommandPressed());

  }

  @Test
  public void testReceivedGetCanvasYCommand() {
    IMockModel mock = new mockModel();

    mock.getCanvasY();

    assertEquals("getting the y coordinate of the canvas",
        mock.getCommandPressed());

  }

  @Test
  public void testReceivedGetCanvasWidthCommand() {
    IMockModel mock = new mockModel();

    mock.getCanvasWidth();

    assertEquals("getting the width of the canvas",
        mock.getCommandPressed());

  }

  @Test
  public void testReceivedGetCanvasHeightCommand() {
    IMockModel mock = new mockModel();

    mock.getCanvasHeight();

    assertEquals("getting the height of the canvas",
        mock.getCommandPressed());

  }


  @Test
  public void testReceivedSetCanvasXCommand() {
    IMockModel mock = new mockModel();

    mock.setCanvasX(200);

    assertEquals("setting the canvas x coordinate to be 200",
        mock.getCommandPressed());

  }

  @Test
  public void testReceivedSetCanvasYCommand() {
    IMockModel mock = new mockModel();

    mock.setCanvasY(350);

    assertEquals("setting the canvas y coordinate to be 350",
        mock.getCommandPressed());

  }

  @Test
  public void testReceivedSetCanvasWidthCommand() {
    IMockModel mock = new mockModel();

    mock.setCanvasWidth(30);

    assertEquals("setting the canvas width to be 30",
        mock.getCommandPressed());

  }

  @Test
  public void testReceivedSetCanvasHeightCommand() {
    IMockModel mock = new mockModel();

    mock.setCanvasHeight(45);

    assertEquals("setting the canvas height to be 45",
        mock.getCommandPressed());

  }

  @Test
  public void testReceivedFindShapesAtTickCommand() {
    IMockModel mock = new mockModel();

    mock.findShapeAtTick("R", 4);

    assertEquals("finding the shape state of shape: R at tick: 4",
        mock.getCommandPressed());

  }

  @Test
  public void testReceivedGetKeyFramesTicksCommand() {
    IMockModel mock = new mockModel();

    mock.getKeyFrameTicks("B");

    assertEquals("getting all of the keyFrames for the shape: B",
        mock.getCommandPressed());

  }
















}
