import static org.junit.Assert.assertEquals;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.AnimationModelImpl;
import cs3500.animator.model.IViewModel;
import cs3500.animator.model.Motion;
import cs3500.animator.model.Position2D;
import cs3500.animator.model.Shape;
import cs3500.animator.model.ShapeColor;
import cs3500.animator.model.Size;
import cs3500.animator.model.Square;
import cs3500.animator.model.ViewModel;
import cs3500.animator.view.IView;
import cs3500.animator.view.TextView;
import org.junit.Test;

/**
 * testing class for the svg view.
 */
public class SVGViewTest {

  @Test
  public void testView() {
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


}
