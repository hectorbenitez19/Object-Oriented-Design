import static org.junit.Assert.assertEquals;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.AnimationModelImpl;
import cs3500.animator.model.AnimationModelImpl.Builder;
import cs3500.animator.model.IViewModel;
import cs3500.animator.model.Motion;
import cs3500.animator.model.Position2D;
import cs3500.animator.model.Shape;
import cs3500.animator.model.ShapeColor;
import cs3500.animator.model.Size;
import cs3500.animator.model.Square;
import cs3500.animator.model.ViewModel;
import cs3500.animator.util.AnimationReader;
import cs3500.animator.view.IView;
import cs3500.animator.view.SVGView;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import org.junit.Test;

/**
 * Testing class for the svg view. Makes sure that the view works and behaves as expected.
 */
public class SVGViewTest {

  @Test
  public void testSVGView() {
    AnimationModel model = new AnimationModelImpl();
    Position2D redSquarePosition = new Position2D(50, 50);
    ShapeColor redSquareColor = new ShapeColor(255, 0, 0);

    Size redSquareSize2 = new Size(150, 150);
    Shape redSquare2 = new Square("R", redSquarePosition, redSquareSize2,
        redSquareColor);


    Size blueSquareSize = new Size(60, 60);
    Position2D blueSquarePosition = new Position2D(20, 90);
    ShapeColor blueSquareColor = new ShapeColor(0, 0, 255);
    Shape blueSquare = new Square("B", blueSquarePosition, blueSquareSize, blueSquareColor);
    Size greenSquareSize = new Size(20, 20);
    Position2D greenSquarePosition = new Position2D(40, 10);
    ShapeColor greenSquareColor = new ShapeColor(0, 255, 0);
    Shape greenSquare = new Square("G", greenSquarePosition, greenSquareSize,
        greenSquareColor);

    model.addShape("R", "square");

    model.newMotion("R", 10, 50, 50, 150, 150, 255, 0, 0,
        15, 55, 50, 150, 150, 255, 0, 0);

    model.addShape("B", "square");


    model.newMotion("B", 1, 20, 90, 60, 60, 0, 0, 255, 10,
        70, 70, 60, 60, 0, 0, 255);

    model.newMotion("R", 1, 50, 50, 100, 100, 255, 0,0, 10,
        50, 50, 150, 150, 255, 0, 0);

    model.addShape("G", "square");
    Position2D greenSquarePosition2 = new Position2D(90, 75);
    Shape greenSquare2 = new
        Square("G", greenSquarePosition2, greenSquareSize, greenSquareColor);
    Motion greenLog1 = new Motion(greenSquare, 15, greenSquare2, 20);
    model.newMotion("G", 15, 40, 10, 20, 20, 0, 255, 0, 20,
        90, 75, 20, 20, 0, 255, 0);

    model.newMotion("R", 15, 55, 50, 150, 150, 255, 0, 0, 20,
        100, 100, 150, 150, 0, 255, 0);
    model.newMotion("B", 10, 70, 70, 60, 60, 0, 0, 255,15, 70, 70,
        30, 30, 0, 0, 255);

    IViewModel vm = new ViewModel(model);
    IView view = new SVGView(vm, 1, "");
    StringBuilder animationText = new StringBuilder();
    view.displayView(animationText);
    assertEquals(
        "<svg width=\"900\" height=\"900\" version=\"1.1\" "
            + "xmlns=\"http://www.w3.org/2000/svg\">\n"
            + "<rect id=\"R\" x=\"50.0\" y=\"50.0\" width=\"100\" height=\"100\" "
            + "fill=\"rgb(255,0,0)\" visibility=\"hidden\">\n"
            + "<set attributeName=\"visibility\" to=\"visible\" begin=\"100ms\" />\n"
            + "<animate  attributeType=\"xml\"  begin=\"100ms\" dur=\"900ms\" "
            + "attributeName=\"width\" from=\"100\" to=\"150\" fill=\"freeze\" />\n"
            + "<animate  attributeType=\"xml\"  begin=\"100ms\" dur=\"900ms\" "
            + "attributeName=\"height\" from=\"100\" to=\"150\" fill=\"freeze\" />\n"
            + "<animate  attributeType=\"xml\"  begin=\"1000ms\" dur=\"500ms\" "
            + "attributeName=\"x\" from=\"50.0\" to=\"55.0\" fill=\"freeze\" />\n"
            + "<animate  attributeType=\"xml\"  begin=\"1500ms\" dur=\"500ms\" "
            + "attributeName=\"x\" from=\"55.0\" to=\"100.0\" fill=\"freeze\" />\n"
            + "<animate  attributeType=\"xml\"  begin=\"1500ms\" dur=\"500ms\" "
            + "attributeName=\"y\" from=\"50.0\" to=\"100.0\" fill=\"freeze\" />\n"
            + "<animate  attributeType=\"xml\"  begin=\"1500ms\" dur=\"500ms\" "
            + "attributeName=\"fill\" from=\"rgb(255,0,0)\" to=\"rgb(0,255,0)\" "
            + "fill=\"freeze\" />\n"
            + "</rect>\n"
            + "<rect id=\"B\" x=\"20.0\" y=\"90.0\" width=\"60\" height=\"60\" "
            + "fill=\"rgb(0,0,255)\" visibility=\"hidden\">\n"
            + "<set attributeName=\"visibility\" to=\"visible\" begin=\"100ms\" />\n"
            + "<animate  attributeType=\"xml\"  begin=\"100ms\" dur=\"900ms\" "
            + "attributeName=\"x\" from=\"20.0\" to=\"70.0\" fill=\"freeze\" />\n"
            + "<animate  attributeType=\"xml\"  begin=\"100ms\" dur=\"900ms\" "
            + "attributeName=\"y\" from=\"90.0\" to=\"70.0\" fill=\"freeze\" />\n"
            + "<animate  attributeType=\"xml\"  begin=\"1000ms\" dur=\"500ms\" "
            + "attributeName=\"width\" from=\"60\" to=\"30\" fill=\"freeze\" />\n"
            + "<animate  attributeType=\"xml\"  begin=\"1000ms\" dur=\"500ms\" "
            + "attributeName=\"height\" from=\"60\" to=\"30\" fill=\"freeze\" />\n"
            + "</rect>\n"
            + "<rect id=\"G\" x=\"40.0\" y=\"10.0\" width=\"20\" height=\"20\" "
            + "fill=\"rgb(0,255,0)\" visibility=\"hidden\">\n"
            + "<set attributeName=\"visibility\" to=\"visible\" begin=\"1500ms\" />\n"
            + "<animate  attributeType=\"xml\"  begin=\"1500ms\" dur=\"500ms\" "
            + "attributeName=\"x\" from=\"40.0\" to=\"90.0\" fill=\"freeze\" />\n"
            + "<animate  attributeType=\"xml\"  begin=\"1500ms\" dur=\"500ms\" "
            + "attributeName=\"y\" from=\"10.0\" to=\"75.0\" fill=\"freeze\" />\n"
            + "</rect>\n"
            + "</svg>", animationText.toString());
  }

  @Test
  public void testTextViewSmallDemoFile() throws FileNotFoundException {
    File f = new File("smalldemo.txt");
    Reader r = new FileReader(f);
    AnimationModel m = AnimationReader.parseFile(r, new Builder());
    IViewModel vm = new ViewModel(m);
    IView view = new SVGView(vm, 1, "");
    StringBuilder animationText = new StringBuilder();
    view.displayView(animationText);
    assertEquals(
        "<svg width=\"900\" height=\"900\" version=\"1.1\" "
            + "xmlns=\"http://www.w3.org/2000/svg\">\n"
            + "<rect id=\"R\" x=\"200.0\" y=\"200.0\" width=\"50\" height=\"100\" "
            + "fill=\"rgb(255,0,0)\" visibility=\"hidden\">\n"
            + "<set attributeName=\"visibility\" to=\"visible\" begin=\"100ms\" />\n"
            + "<animate  attributeType=\"xml\"  begin=\"1000ms\" dur=\"4000ms\" "
            + "attributeName=\"x\" from=\"200.0\" to=\"300.0\" fill=\"freeze\" />\n"
            + "<animate  attributeType=\"xml\"  begin=\"1000ms\" dur=\"4000ms\" "
            + "attributeName=\"y\" from=\"200.0\" to=\"300.0\" fill=\"freeze\" />\n"
            + "<animate  attributeType=\"xml\"  begin=\"5100ms\" dur=\"1900ms\" "
            + "attributeName=\"width\" from=\"50\" to=\"25\" fill=\"freeze\" />\n"
            + "<animate  attributeType=\"xml\"  begin=\"7000ms\" dur=\"3000ms\" "
            + "attributeName=\"x\" from=\"300.0\" to=\"200.0\" fill=\"freeze\" />\n"
            + "<animate  attributeType=\"xml\"  begin=\"7000ms\" dur=\"3000ms\" "
            + "attributeName=\"y\" from=\"300.0\" to=\"200.0\" fill=\"freeze\" />\n"
            + "</rect>\n"
            + "<ellipse id=\"C\" cx=\"440.0\" cy=\"70.0\" rx=\"60.0\" ry=\"30.0\" "
            + "fill=\"rgb(0,0,255)\" visibility=\"hidden\">\n"
            + "<set attributeName=\"visibility\" to=\"visible\" begin=\"600ms\" />\n"
            + "<animate  attributeType=\"xml\"  begin=\"2000ms\" dur=\"3000ms\" "
            + "attributeName=\"cy\" from=\"70.0\" to=\"250.0\" fill=\"freeze\" />\n"
            + "<animate  attributeType=\"xml\"  begin=\"5000ms\" dur=\"2000ms\" "
            + "attributeName=\"cy\" from=\"250.0\" to=\"370.0\" fill=\"freeze\" />\n"
            + "<animate  attributeType=\"xml\"  begin=\"5000ms\" dur=\"2000ms\" "
            + "attributeName=\"fill\" from=\"rgb(0,0,255)\" to=\"rgb(0,170,85)\" "
            + "fill=\"freeze\" />\n"
            + "<animate  attributeType=\"xml\"  begin=\"7000ms\" dur=\"1000ms\" "
            + "attributeName=\"fill\" from=\"rgb(0,170,85)\" to=\"rgb(0,255,0)\" "
            + "fill=\"freeze\" />\n"
            + "</ellipse>\n"
            + "</svg>",
        animationText.toString());

  }


}
