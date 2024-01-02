import static org.junit.Assert.assertEquals;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.AnimationModelImpl;
import cs3500.animator.model.AnimationModelImpl.Builder;
import cs3500.animator.model.Motion;
import cs3500.animator.model.Position2D;
import cs3500.animator.model.Shape;
import cs3500.animator.model.ShapeColor;
import cs3500.animator.model.Size;
import cs3500.animator.model.Square;
import cs3500.animator.util.AnimationReader;
import cs3500.animator.model.IViewModel;
import cs3500.animator.view.IView;
import cs3500.animator.view.TextView;
import cs3500.animator.model.ViewModel;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import org.junit.Test;

/**
 * A class meant for testing the behavior of the textual view of Animations. Ensures that the
 * view works correctly and outputs what is expected.
 */
public class TextViewTest {

  @Test
  public void testHomeMadeTextView() {
    AnimationModel model = new AnimationModelImpl();

    Size redSquareSize = new Size(100, 100);
    Position2D redSquarePosition = new Position2D(50, 50);
    ShapeColor redSquareColor = new ShapeColor(255, 0, 0);
    Shape redSquare = new Square("R", redSquarePosition, redSquareSize, redSquareColor);
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
    Size blueSquareSize = new Size(60, 60);
    Position2D blueSquarePosition = new Position2D(20, 90);
    ShapeColor blueSquareColor = new ShapeColor(0, 0, 255);
    Shape blueSquare = new Square("B", blueSquarePosition, blueSquareSize, blueSquareColor);
    Size greenSquareSize = new Size(20, 20);
    Position2D greenSquarePosition = new Position2D(40, 10);
    ShapeColor greenSquareColor = new ShapeColor(0, 255, 0);
    Shape greenSquare = new Square("G", greenSquarePosition, greenSquareSize,
        greenSquareColor);

    model.addShape(redSquare2);
    Motion log2 = new Motion(redSquare2, 10, redSquare3, 15);
    Motion log1 = new Motion(redSquare, 1, redSquare2, 10);
    Motion log3 = new Motion(redSquare3, 15, redSquare4, 20);
    model.newMotion("R", log2);

    model.addShape(blueSquare);
    Position2D blueSquarePosition2 = new Position2D(70, 70);
    Size blueSquareSize2 = new Size(30, 30);
    Shape blueSquare2 = new Square("B", blueSquarePosition2, blueSquareSize, blueSquareColor);
    Shape blueSquare3 = new
        Square("B", blueSquarePosition2, blueSquareSize2, blueSquareColor);
    Motion bluelog1 = new Motion(blueSquare, 1, blueSquare2, 10);
    Motion bluelog2 = new Motion(blueSquare2, 10, blueSquare3, 15);
    model.newMotion("B", bluelog1);

    model.newMotion("R", log1);

    model.addShape(greenSquare);
    Position2D greenSquarePosition2 = new Position2D(90, 75);
    Shape greenSquare2 = new
        Square("G", greenSquarePosition2, greenSquareSize, greenSquareColor);
    Motion greenLog1 = new Motion(greenSquare, 15, greenSquare2, 20);
    model.newMotion("G", greenLog1);

    model.newMotion("R", log3);
    model.newMotion("B", bluelog2);

    IViewModel vm = new ViewModel(model);
    IView view = new TextView(vm, "");
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
        + "motion G 15 40 10 20 20 0 255 0   20 90 75 20 20 0 255 0", animationText.toString());
  }
  
  @Test
  public void testTextViewSmallDemoFile() throws FileNotFoundException {
    File f = new File("/Users/gracemayer/Documents/Summer 1 2020/Object-Oriented Design/IntelliJ "
        + "Projects/HW6/smalldemo.txt");
    Reader r = new FileReader(f);
    AnimationModel m = AnimationReader.parseFile(r, new Builder());
    IViewModel vm = new ViewModel(m);
    TextView view = new TextView(vm, "");
    StringBuilder animationText = new StringBuilder();
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
        + "motion C 80 440 370 120 60 0 255 0   100 440 370 120 60 0 255 0",
        animationText.toString());

  }

  @Test
  public void testTextViewToh3File() throws FileNotFoundException {
    File f = new File("/Users/gracemayer/Documents/Summer 1 2020/Object-Oriented Design/IntelliJ "
        + "Projects/HW6/toh-3.txt");
    Reader r = new FileReader(f);
    AnimationModel m = AnimationReader.parseFile(r, new Builder());
    IViewModel vm = new ViewModel(m);
    TextView view = new TextView(vm, "");
    StringBuilder animationText = new StringBuilder();
    view.displayView(animationText);
    assertEquals("canvas 145 50 410 220\n"
            + "shape disk1 rectangle\n"
            + "motion disk1 1 190 180 20 30 0 49 90   1 190 180 20 30 0 49 90\n"
            + "motion disk1 1 190 180 20 30 0 49 90   25 190 180 20 30 0 49 90\n"
            + "motion disk1 25 190 180 20 30 0 49 90   35 190 50 20 30 0 49 90\n"
            + "motion disk1 35 190 50 20 30 0 49 90   36 190 50 20 30 0 49 90\n"
            + "motion disk1 36 190 50 20 30 0 49 90   46 490 50 20 30 0 49 90\n"
            + "motion disk1 46 490 50 20 30 0 49 90   47 490 50 20 30 0 49 90\n"
            + "motion disk1 47 490 50 20 30 0 49 90   57 490 240 20 30 0 49 90\n"
            + "motion disk1 57 490 240 20 30 0 49 90   89 490 240 20 30 0 49 90\n"
            + "motion disk1 89 490 240 20 30 0 49 90   99 490 50 20 30 0 49 90\n"
            + "motion disk1 99 490 50 20 30 0 49 90   100 490 50 20 30 0 49 90\n"
            + "motion disk1 100 490 50 20 30 0 49 90   110 340 50 20 30 0 49 90\n"
            + "motion disk1 110 340 50 20 30 0 49 90   111 340 50 20 30 0 49 90\n"
            + "motion disk1 111 340 50 20 30 0 49 90   121 340 210 20 30 0 49 90\n"
            + "motion disk1 121 340 210 20 30 0 49 90   153 340 210 20 30 0 49 90\n"
            + "motion disk1 153 340 210 20 30 0 49 90   163 340 50 20 30 0 49 90\n"
            + "motion disk1 163 340 50 20 30 0 49 90   164 340 50 20 30 0 49 90\n"
            + "motion disk1 164 340 50 20 30 0 49 90   174 190 50 20 30 0 49 90\n"
            + "motion disk1 174 190 50 20 30 0 49 90   175 190 50 20 30 0 49 90\n"
            + "motion disk1 175 190 50 20 30 0 49 90   185 190 240 20 30 0 49 90\n"
            + "motion disk1 185 190 240 20 30 0 49 90   217 190 240 20 30 0 49 90\n"
            + "motion disk1 217 190 240 20 30 0 49 90   227 190 50 20 30 0 49 90\n"
            + "motion disk1 227 190 50 20 30 0 49 90   228 190 50 20 30 0 49 90\n"
            + "motion disk1 228 190 50 20 30 0 49 90   238 490 50 20 30 0 49 90\n"
            + "motion disk1 238 490 50 20 30 0 49 90   239 490 50 20 30 0 49 90\n"
            + "motion disk1 239 490 50 20 30 0 49 90   249 490 180 20 30 0 49 90\n"
            + "motion disk1 249 490 180 20 30 0 49 90   257 490 180 20 30 0 255 0\n"
            + "motion disk1 257 490 180 20 30 0 255 0   302 490 180 20 30 0 255 0\n"
            + "shape disk2 rectangle\n"
            + "motion disk2 1 167 210 65 30 6 247 41   1 167 210 65 30 6 247 41\n"
            + "motion disk2 1 167 210 65 30 6 247 41   57 167 210 65 30 6 247 41\n"
            + "motion disk2 57 167 210 65 30 6 247 41   67 167 50 65 30 6 247 41\n"
            + "motion disk2 67 167 50 65 30 6 247 41   68 167 50 65 30 6 247 41\n"
            + "motion disk2 68 167 50 65 30 6 247 41   78 317 50 65 30 6 247 41\n"
            + "motion disk2 78 317 50 65 30 6 247 41   79 317 50 65 30 6 247 41\n"
            + "motion disk2 79 317 50 65 30 6 247 41   89 317 240 65 30 6 247 41\n"
            + "motion disk2 89 317 240 65 30 6 247 41   185 317 240 65 30 6 247 41\n"
            + "motion disk2 185 317 240 65 30 6 247 41   195 317 50 65 30 6 247 41\n"
            + "motion disk2 195 317 50 65 30 6 247 41   196 317 50 65 30 6 247 41\n"
            + "motion disk2 196 317 50 65 30 6 247 41   206 467 50 65 30 6 247 41\n"
            + "motion disk2 206 467 50 65 30 6 247 41   207 467 50 65 30 6 247 41\n"
            + "motion disk2 207 467 50 65 30 6 247 41   217 467 210 65 30 6 247 41\n"
            + "motion disk2 217 467 210 65 30 6 247 41   225 467 210 65 30 0 255 0\n"
            + "motion disk2 225 467 210 65 30 0 255 0   302 467 210 65 30 0 255 0\n"
            + "shape disk3 rectangle\n"
            + "motion disk3 1 145 240 110 30 11 45 175   1 145 240 110 30 11 45 175\n"
            + "motion disk3 1 145 240 110 30 11 45 175   121 145 240 110 30 11 45 175\n"
            + "motion disk3 121 145 240 110 30 11 45 175   131 145 50 110 30 11 45 175\n"
            + "motion disk3 131 145 50 110 30 11 45 175   132 145 50 110 30 11 45 175\n"
            + "motion disk3 132 145 50 110 30 11 45 175   142 445 50 110 30 11 45 175\n"
            + "motion disk3 142 445 50 110 30 11 45 175   143 445 50 110 30 11 45 175\n"
            + "motion disk3 143 445 50 110 30 11 45 175   153 445 240 110 30 11 45 175\n"
            + "motion disk3 153 445 240 110 30 11 45 175   161 445 240 110 30 0 255 0\n"
            + "motion disk3 161 445 240 110 30 0 255 0   302 445 240 110 30 0 255 0",
        animationText.toString());

  }




}
