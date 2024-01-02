import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

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
    animationModel.addShape(redSquare);
    assertEquals("shape R Square", animationModel.outputDescriptions());
  }

  @Test
  public void testAddMultipleShapes() {
    animationModel.addShape(redSquare);
    animationModel.addShape(blueSquare);
    animationModel.addShape(greenSquare);
    assertEquals("shape R Square\n"
        + "shape B Square\n"
        + "shape G Square", animationModel.outputDescriptions());

  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddShapeThatAlreadyExists() {
    animationModel.addShape(redSquare);
    animationModel.addShape(blueSquare);
    animationModel.addShape(redSquare);
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
      Motion m = new Motion(redSquare, 1, redSquare2, 1);
      fail("Invalid constructor should have thrown exception");
    } catch (IllegalArgumentException e) {
      assertEquals("You can't have a motion that changes a shape in an "
          + "instant", e.getMessage());
    }

  }

  @Test
  public void testInvalidMotionDifferentShapeNames() {
    Shape redSquare2 = new Square("R", blueSquarePosition, blueSquareSize,
        blueSquareColor);
    try {
      Motion m = new Motion(redSquare, 1, redSquare2, 1);
      fail("Invalid constructor should have thrown exception");
    } catch (IllegalArgumentException e) {
      assertEquals("You can't have a motion that changes a shape in an "
          + "instant", e.getMessage());
    }

  }

  @Test
  public void testNewMotion() {
    animationModel.addShape(redSquare);
    Size redSquareSize2 = new Size(150, 150);
    Shape redSquare2 = new Square("R", redSquarePosition, redSquareSize2,
        redSquareColor);

    Motion motion1 = new Motion(redSquare, 1, redSquare2, 10);

    animationModel.newMotion("R", motion1);

    assertEquals("shape R Square\n"
            + "motion R 1 50 50 100 100 255 0 0   10 50 50 150 150 255 0 0",
        animationModel.outputDescriptions());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidNewMotionShapeNonExistent() {
    animationModel.addShape(redSquare);
    Size redSquareSize2 = new Size(150, 150);
    Shape redSquare2 = new Square("R", redSquarePosition, redSquareSize2,
        redSquareColor);

    Motion motion1 = new Motion(redSquare, 1, redSquare2, 10);

    animationModel.newMotion("B", motion1);
  }

  @Test
  public void testMultipleNewMotions() {
    animationModel.addShape(redSquare);

    Size redSquareSize2 = new Size(150, 150);
    Shape redSquare2 = new Square("R", redSquarePosition, redSquareSize2,
        redSquareColor);

    Position2D redSquarePosition2 = new Position2D(55, 50);
    Shape redSquare3 = new Square("R", redSquarePosition2, redSquareSize2,
        redSquareColor);

    Position2D redSquarePosition3 = new Position2D(100, 100);
    ShapeColor redSquareColor2 = new ShapeColor(0, 255, 0);
    Shape redSquare4 = new Square("R", redSquarePosition3, redSquareSize2,
        redSquareColor2);

    Motion motion1 = new Motion(redSquare, 1, redSquare2, 10);
    Motion motion2 = new Motion(redSquare2, 10, redSquare3, 15);
    Motion motion3 = new Motion(redSquare3, 15, redSquare4, 20);

    animationModel.newMotion("R", motion1);
    animationModel.newMotion("R", motion2);
    animationModel.newMotion("R", motion3);

    assertEquals("shape R Square\n"
            + "motion R 1 50 50 100 100 255 0 0   10 50 50 150 150 255 0 0\n"
            + "motion R 10 50 50 150 150 255 0 0   15 55 50 150 150 255 0 0\n"
            + "motion R 15 55 50 150 150 255 0 0   20 100 100 150 150 0 255 0",
        animationModel.outputDescriptions());
  }

  @Test
  public void testMultipleNewMotionsNonConsecutiveOrder() {
    Size redSquareSize2 = new Size(150, 150);
    Shape redSquare2 = new Square("R", redSquarePosition, redSquareSize2,
        redSquareColor);

    Position2D redSquarePosition2 = new Position2D(55, 50);
    Shape redSquare3 = new Square("R", redSquarePosition2, redSquareSize2,
        redSquareColor);

    Position2D redSquarePosition3 = new Position2D(100, 100);
    ShapeColor redSquareColor2 = new ShapeColor(0, 255, 0);
    Shape redSquare4 = new Square("R", redSquarePosition3, redSquareSize2,
        redSquareColor2);

    animationModel.addShape(redSquare2);
    Motion log2 = new Motion(redSquare2, 10, redSquare3, 15);
    Motion log1 = new Motion(redSquare, 1, redSquare2, 10);
    Motion log3 = new Motion(redSquare3, 15, redSquare4, 20);

    animationModel.newMotion("R", log2);
    animationModel.newMotion("R", log1);
    animationModel.newMotion("R", log3);

    assertEquals("shape R Square\n"
            + "motion R 1 50 50 100 100 255 0 0   10 50 50 150 150 255 0 0\n"
            + "motion R 10 50 50 150 150 255 0 0   15 55 50 150 150 255 0 0\n"
            + "motion R 15 55 50 150 150 255 0 0   20 100 100 150 150 0 255 0",
        animationModel.outputDescriptions());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMotionWithGap() {
    Size redSquareSize2 = new Size(150, 150);
    Shape redSquare2 = new Square("R", redSquarePosition, redSquareSize2,
        redSquareColor);

    Position2D redSquarePosition2 = new Position2D(55, 50);
    Shape redSquare3 = new Square("R", redSquarePosition2, redSquareSize2,
        redSquareColor);

    Position2D redSquarePosition3 = new Position2D(100, 100);
    ShapeColor redSquareColor2 = new ShapeColor(0, 255, 0);
    Shape redSquare4 = new Square("R", redSquarePosition3, redSquareSize2,
        redSquareColor2);

    animationModel.addShape(redSquare);
    Motion log1 = new Motion(redSquare, 1, redSquare2, 10);
    Motion log3 = new Motion(redSquare2, 15, redSquare4, 20);

    animationModel.newMotion("R", log1);
    animationModel.newMotion("R", log3);
  }

  @Test
  public void testValidMotionTwoDifferentShapes() {
    Size redSquareSize2 = new Size(150, 150);
    Shape redSquare2 = new Square("R", redSquarePosition, redSquareSize2,
        redSquareColor);

    Position2D redSquarePosition2 = new Position2D(55, 50);
    Shape redSquare3 = new Square("R", redSquarePosition2, redSquareSize2,
        redSquareColor);

    Position2D redSquarePosition3 = new Position2D(100, 100);
    ShapeColor redSquareColor2 = new ShapeColor(0, 255, 0);
    Shape redSquare4 = new Square("R", redSquarePosition3, redSquareSize2,
        redSquareColor2);

    animationModel.addShape(redSquare2);
    Motion log2 = new Motion(redSquare2, 10, redSquare3, 15);
    Motion log1 = new Motion(redSquare, 1, redSquare2, 10);
    Motion log3 = new Motion(redSquare3, 15, redSquare4, 20);

    animationModel.newMotion("R", log2);
    animationModel.newMotion("R", log1);
    animationModel.newMotion("R", log3);

    assertEquals("shape R Square\n"
            + "motion R 1 50 50 100 100 255 0 0   10 50 50 150 150 255 0 0\n"
            + "motion R 10 50 50 150 150 255 0 0   15 55 50 150 150 255 0 0\n"
            + "motion R 15 55 50 150 150 255 0 0   20 100 100 150 150 0 255 0",
        animationModel.outputDescriptions());

    animationModel.addShape(blueSquare);

    Position2D blueSquarePosition2 = new Position2D(70, 70);
    Shape blueSquare2 = new Square("B", blueSquarePosition2, blueSquareSize, blueSquareColor);

    Motion bluelog1 = new Motion(blueSquare, 1, blueSquare2, 10);
    animationModel.newMotion("B", bluelog1);

    assertEquals("shape R Square\n"
            + "motion R 1 50 50 100 100 255 0 0   10 50 50 150 150 255 0 0\n"
            + "motion R 10 50 50 150 150 255 0 0   15 55 50 150 150 255 0 0\n"
            + "motion R 15 55 50 150 150 255 0 0   20 100 100 150 150 0 255 0\n"
            + "shape B Square\n"
            + "motion B 1 20 90 60 60 0 0 255   10 70 70 60 60 0 0 255",
        animationModel.outputDescriptions());
  }


  @Test
  public void testValidMotionTwoDifferentShapes2() {
    Size redSquareSize2 = new Size(150, 150);
    Shape redSquare2 = new Square("R", redSquarePosition, redSquareSize2,
        redSquareColor);

    Position2D redSquarePosition2 = new Position2D(55, 50);
    Shape redSquare3 = new Square("R", redSquarePosition2, redSquareSize2,
        redSquareColor);

    Position2D redSquarePosition3 = new Position2D(100, 100);
    ShapeColor redSquareColor2 = new ShapeColor(0, 255, 0);
    Shape redSquare4 = new Square("R", redSquarePosition3, redSquareSize2,
        redSquareColor2);

    animationModel.addShape(redSquare2);
    Motion log2 = new Motion(redSquare2, 10, redSquare3, 15);
    Motion log1 = new Motion(redSquare, 1, redSquare2, 10);
    Motion log3 = new Motion(redSquare3, 15, redSquare4, 20);

    animationModel.newMotion("R", log2);
    assertEquals("shape R Square\n"
            + "motion R 10 50 50 150 150 255 0 0   15 55 50 150 150 255 0 0",
        animationModel.outputDescriptions());

    animationModel.addShape(blueSquare);
    Position2D blueSquarePosition2 = new Position2D(70, 70);
    Shape blueSquare2 = new Square("B", blueSquarePosition2, blueSquareSize, blueSquareColor);

    Motion bluelog1 = new Motion(blueSquare, 1, blueSquare2, 10);
    animationModel.newMotion("B", bluelog1);

    assertEquals("shape R Square\n"
            + "motion R 10 50 50 150 150 255 0 0   15 55 50 150 150 255 0 0\n"
            + "shape B Square\n"
            + "motion B 1 20 90 60 60 0 0 255   10 70 70 60 60 0 0 255",
        animationModel.outputDescriptions());

    animationModel.newMotion("R", log1);

    assertEquals("shape R Square\n"
            + "motion R 1 50 50 100 100 255 0 0   10 50 50 150 150 255 0 0\n"
            + "motion R 10 50 50 150 150 255 0 0   15 55 50 150 150 255 0 0\n"
            + "shape B Square\n"
            + "motion B 1 20 90 60 60 0 0 255   10 70 70 60 60 0 0 255",
        animationModel.outputDescriptions());

    animationModel.newMotion("R", log3);

    assertEquals("shape R Square\n"
            + "motion R 1 50 50 100 100 255 0 0   10 50 50 150 150 255 0 0\n"
            + "motion R 10 50 50 150 150 255 0 0   15 55 50 150 150 255 0 0\n"
            + "motion R 15 55 50 150 150 255 0 0   20 100 100 150 150 0 255 0\n"
            + "shape B Square\n"
            + "motion B 1 20 90 60 60 0 0 255   10 70 70 60 60 0 0 255",
        animationModel.outputDescriptions());
  }


  @Test
  public void testValidMotionThreeDifferentShapes() {
    Size redSquareSize2 = new Size(150, 150);
    Shape redSquare2 = new Square("R", redSquarePosition, redSquareSize2,
        redSquareColor);

    Position2D redSquarePosition2 = new Position2D(55, 50);
    Shape redSquare3 = new Square("R", redSquarePosition2, redSquareSize2,
        redSquareColor);

    Position2D redSquarePosition3 = new Position2D(100, 100);
    ShapeColor redSquareColor2 = new ShapeColor(0, 255, 0);
    Shape redSquare4 = new Square("R", redSquarePosition3, redSquareSize2,
        redSquareColor2);

    animationModel.addShape(redSquare2);
    Motion log2 = new Motion(redSquare2, 10, redSquare3, 15);
    Motion log1 = new Motion(redSquare, 1, redSquare2, 10);
    Motion log3 = new Motion(redSquare3, 15, redSquare4, 20);

    animationModel.newMotion("R", log2);
    assertEquals("shape R Square\n"
            + "motion R 10 50 50 150 150 255 0 0   15 55 50 150 150 255 0 0",
        animationModel.outputDescriptions());

    animationModel.addShape(blueSquare);
    Position2D blueSquarePosition2 = new Position2D(70, 70);
    Size blueSquareSize2 = new Size(30, 30);
    Shape blueSquare2 = new Square("B", blueSquarePosition2, blueSquareSize, blueSquareColor);
    Shape blueSquare3 = new
        Square("B", blueSquarePosition2, blueSquareSize2, blueSquareColor);

    Motion bluelog1 = new Motion(blueSquare, 1, blueSquare2, 10);
    Motion bluelog2 = new Motion(blueSquare2, 10, blueSquare3, 15);
    animationModel.newMotion("B", bluelog1);

    assertEquals("shape R Square\n"
            + "motion R 10 50 50 150 150 255 0 0   15 55 50 150 150 255 0 0\n"
            + "shape B Square\n"
            + "motion B 1 20 90 60 60 0 0 255   10 70 70 60 60 0 0 255",
        animationModel.outputDescriptions());

    animationModel.newMotion("R", log1);

    assertEquals("shape R Square\n"
            + "motion R 1 50 50 100 100 255 0 0   10 50 50 150 150 255 0 0\n"
            + "motion R 10 50 50 150 150 255 0 0   15 55 50 150 150 255 0 0\n"
            + "shape B Square\n"
            + "motion B 1 20 90 60 60 0 0 255   10 70 70 60 60 0 0 255",
        animationModel.outputDescriptions());

    animationModel.addShape(greenSquare);

    Position2D greenSquarePosition2 = new Position2D(90, 75);
    Shape greenSquare2 = new
        Square("G", greenSquarePosition2, greenSquareSize, greenSquareColor);
    Motion greenlog1 = new Motion(greenSquare, 15, greenSquare2, 20);

    animationModel.newMotion("G", greenlog1);

    assertEquals("shape R Square\n"
            + "motion R 1 50 50 100 100 255 0 0   10 50 50 150 150 255 0 0\n"
            + "motion R 10 50 50 150 150 255 0 0   15 55 50 150 150 255 0 0\n"
            + "shape B Square\n"
            + "motion B 1 20 90 60 60 0 0 255   10 70 70 60 60 0 0 255\n"
            + "shape G Square\n"
            + "motion G 15 40 10 20 20 0 255 0   20 90 75 20 20 0 255 0",
        animationModel.outputDescriptions());

    animationModel.newMotion("R", log3);
    animationModel.newMotion("B", bluelog2);

    assertEquals("shape R Square\n"
            + "motion R 1 50 50 100 100 255 0 0   10 50 50 150 150 255 0 0\n"
            + "motion R 10 50 50 150 150 255 0 0   15 55 50 150 150 255 0 0\n"
            + "motion R 15 55 50 150 150 255 0 0   20 100 100 150 150 0 255 0\n"
            + "shape B Square\n"
            + "motion B 1 20 90 60 60 0 0 255   10 70 70 60 60 0 0 255\n"
            + "motion B 10 70 70 60 60 0 0 255   15 70 70 30 30 0 0 255\n"
            + "shape G Square\n"
            + "motion G 15 40 10 20 20 0 255 0   20 90 75 20 20 0 255 0",
        animationModel.outputDescriptions());
  }


  @Test(expected = IllegalArgumentException.class)
  public void testMotionIsNull() {
    Size redSquareSize2 = new Size(150, 150);
    Shape redSquare2 = new Square("R", redSquarePosition, redSquareSize2,
        redSquareColor);

    Position2D redSquarePosition2 = new Position2D(55, 50);
    Shape redSquare3 = new Square("R", redSquarePosition2, redSquareSize2,
        redSquareColor);

    Position2D redSquarePosition3 = new Position2D(100, 100);
    ShapeColor redSquareColor2 = new ShapeColor(0, 255, 0);
    Shape redSquare4 = new Square("R", redSquarePosition3, redSquareSize2,
        redSquareColor2);

    animationModel.addShape(redSquare);

    animationModel.newMotion("R", null);

  }

  @Test(expected = IllegalArgumentException.class)
  public void testAnimationLogEmpty() {
    Size redSquareSize2 = new Size(150, 150);
    Shape redSquare2 = new Square("R", redSquarePosition, redSquareSize2,
        redSquareColor);

    Position2D redSquarePosition2 = new Position2D(55, 50);
    Shape redSquare3 = new Square("R", redSquarePosition2, redSquareSize2,
        redSquareColor);

    Position2D redSquarePosition3 = new Position2D(100, 100);
    ShapeColor redSquareColor2 = new ShapeColor(0, 255, 0);
    Shape redSquare4 = new Square("R", redSquarePosition3, redSquareSize2,
        redSquareColor2);

    animationModel.addShape(redSquare);
    Motion log2 = new Motion(redSquare2, 10, redSquare3, 15);
    Motion log1 = new Motion(redSquare, 1, redSquare2, 10);

    animationModel.newMotion("R", log2);

  }

  @Test(expected = IllegalArgumentException.class)
  public void testNewMotionOverlap() {
    Size redSquareSize2 = new Size(150, 150);
    Shape redSquare2 = new Square("R", redSquarePosition, redSquareSize2,
        redSquareColor);

    Position2D redSquarePosition2 = new Position2D(55, 50);
    Shape redSquare3 = new Square("R", redSquarePosition2, redSquareSize2,
        redSquareColor);

    Position2D redSquarePosition3 = new Position2D(100, 100);
    ShapeColor redSquareColor2 = new ShapeColor(0, 255, 0);
    Shape redSquare4 = new Square("R", redSquarePosition3, redSquareSize2,
        redSquareColor2);

    animationModel.addShape(redSquare);
    Motion log2 = new Motion(redSquare, 5, redSquare2, 15);
    Motion log1 = new Motion(redSquare, 1, redSquare2, 10);

    animationModel.newMotion("R", log1);
    animationModel.newMotion("R", log2);

  }

  @Test(expected = IllegalArgumentException.class)
  public void testNewMotionMatchingShapeStates() {
    Size redSquareSize2 = new Size(150, 150);
    Shape redSquare2 = new Square("R", redSquarePosition, redSquareSize2,
        redSquareColor);

    Position2D redSquarePosition2 = new Position2D(55, 50);
    Shape redSquare3 = new Square("R", redSquarePosition2, redSquareSize2,
        redSquareColor);

    Position2D redSquarePosition3 = new Position2D(100, 100);
    ShapeColor redSquareColor2 = new ShapeColor(0, 255, 0);
    Shape redSquare4 = new Square("R", redSquarePosition3, redSquareSize2,
        redSquareColor2);

    animationModel.addShape(redSquare);
    Motion log2 = new Motion(redSquare, 10, redSquare2, 15);
    Motion log1 = new Motion(redSquare, 1, redSquare2, 10);

    animationModel.newMotion("R", log1);
    animationModel.newMotion("R", log2);

  }

  @Test(expected = IllegalArgumentException.class)
  public void testNewMotionMatchingShapeStates2() {
    Size redSquareSize2 = new Size(150, 150);
    Shape redSquare2 = new Square("R", redSquarePosition, redSquareSize2,
        redSquareColor);

    Position2D redSquarePosition2 = new Position2D(55, 50);
    Shape redSquare3 = new Square("R", redSquarePosition2, redSquareSize2,
        redSquareColor);

    Position2D redSquarePosition3 = new Position2D(100, 100);
    ShapeColor redSquareColor2 = new ShapeColor(0, 255, 0);
    Shape redSquare4 = new Square("R", redSquarePosition3, redSquareSize2,
        redSquareColor2);

    animationModel.addShape(redSquare);
    Motion log2 = new Motion(redSquare, 10, redSquare2, 15);
    Motion log1 = new Motion(redSquare, 1, redSquare2, 10);

    animationModel.newMotion("R", log2);
    animationModel.newMotion("R", log1);

  }


  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMotionDifferentStartAndEndShapes() {
    Size redSquareSize2 = new Size(150, 150);
    Shape redSquare2 = new Square("R", redSquarePosition, redSquareSize2,
        redSquareColor);

    animationModel.addShape(redSquare);

    Motion log1 = new Motion(redSquare, 1, blueSquare, 10);

    animationModel.newMotion("R", log1);

  }


  @Test
  public void testGetPosition() {
    Size redSquareSize2 = new Size(150, 150);
    Shape redSquare2 = new Square("R", redSquarePosition, redSquareSize2,
        redSquareColor);

    assertEquals(redSquarePosition, redSquare2.getPosition());

    assertEquals(50, redSquare2.getPosition().getX(),0);
    assertEquals(50, redSquare2.getPosition().getY(),0);

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

    animationModel.addShape(redSquare);

    Motion log1 = new Motion(redSquare, 1, redSquare2, 10);

    animationModel.newMotion("R", log1);

    assertEquals(1,log1.getStartTick());

    assertEquals(10,log1.getEndTick());

  }


  @Test
  public void testGetShapes() {
    Size redSquareSize2 = new Size(150, 150);
    Shape redSquare2 = new Square("R", redSquarePosition, redSquareSize2,
        redSquareColor);

    animationModel.addShape(redSquare);

    Motion log1 = new Motion(redSquare, 1, redSquare2, 10);

    animationModel.newMotion("R", log1);

    assertEquals(redSquare,log1.getStartShape());

    assertEquals(redSquare2,log1.getEndShape());

  }




}
