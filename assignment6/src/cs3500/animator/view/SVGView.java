package cs3500.animator.view;

import cs3500.animator.model.IViewModel;
import cs3500.animator.model.Shape;
import cs3500.animator.model.ShapeVisitor;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * The SVG view class handles displaying the animation in a SVG formatted file.
 */
public class SVGView implements IView {

  // A read only model
  private final IViewModel model;

  // The output file name. If none is specified, it defaults to "" and the view will print to
  // console.
  private final String output;

  // in ticks per second
  private final int speed;

  /**
   * constructs an SVG view.
   * @param model read only model with the necessary information
   * @param speed the speed in ticks per second
   * @param output the output destination of the svg text
   */
  public SVGView(IViewModel model, int speed, String output) {
    this.model = model;
    this.output = output;
    this.speed = speed;

  }

  @Override
  public void displayView(Appendable ap) {
    try {
      this.renderSVG(ap);
    } catch (IOException e) {
      throw new IllegalStateException(e.getMessage());
    }
  }


  //handles appending the animation into a svg formatted text file
  private void renderSVG(Appendable ap) throws IOException {
    ShapeVisitor<String> visitor = new SVGVisitor();
    try {
      ap.append(
          "<svg width=\"900\" height=\"900\" version=\"1.1\" xmlns=\"http://www.w3"
              + ".org/2000/svg\">\n");

      for (String shapeName : model.getAnimationLog().keySet()) {
        Shape shape = model.getAnimationLog().get(shapeName).get(0).getStartShape();
        String tagName = shape.accept(visitor);
        switch (tagName) {
          case "rect":
            animateRectangle(ap, shape, shapeName);
            break;
          case "ellipse":
            animateEllipse(ap, shape, shapeName);
            break;
          default:
            throw new IllegalStateException("Unexpected value: " + tagName);
        }
      }
      ap.append("</svg>");
    } catch (IOException e) {
      e.getMessage();
    }

    if (output.equals("")) {
      System.out.println(ap.toString());
    } else {
      writeToFile(output, ap);
    }
  }

  //writes the log of animations to a specified file in svg format
  private void writeToFile(String output, Appendable ap) {
    try {
      File f = new File(output);
      FileWriter writer = new FileWriter(f);
      writer.write(ap.toString());
      writer.close();
      System.out.print("Successfully wrote to file");
    } catch (IOException e) {
      e.getMessage();
    }
  }

  //converts ticks per second into ms
  private long secondsToMs(int t) {
    return (t * 100) / (long) speed;
  }

  //creates the rect and animate svg tags for the specified rectangle
  private void animateRectangle(Appendable ap, Shape shape, String shapeName) throws IOException {
    ap.append(
        "<rect id=\"" + shape.getName() + "\" x=\"" + shape.getPosition()
            .getX()
            + "\" y=\"" + shape.getPosition().getY() +
            "\" width=\"" + shape.getSize().getWidth() + "\" height=\"" + shape.getSize()
            .getHeight()
            + "\" fill=\"rgb(" + shape.getColor().getRed() + "," +
            shape.getColor().getGreen() + "," + shape.getColor().getBlue()
            + ")\" visibility=\"hidden\">\n");
    ap.append("<set attributeName=\"visibility\" to=\"visible\" begin=\"" +
        secondsToMs(model.getAnimationLog().get(shapeName).get(0).getStartTick()) + "ms\" />\n");

    List<String> animateTags = new ArrayList<String>();

    for (int i = 0; i < model.getAnimationLog().get(shapeName).size(); i++) {
      Shape startShape = model.getAnimationLog().get(shapeName).get(i).getStartShape();
      Shape endShape = model.getAnimationLog().get(shapeName).get(i).getEndShape();
      int startTick = model.getAnimationLog().get(shapeName).get(i).getStartTick();
      int endTick = model.getAnimationLog().get(shapeName).get(i).getEndTick();

      if (startShape.getPosition().getX() != endShape.getPosition().getX()) {
        String animateXTag = writeAnimateTag(secondsToMs(startTick) + "",
            (secondsToMs(endTick) - secondsToMs(startTick)) + "", "x",
            startShape.getPosition().getX() + "", endShape.getPosition().getX() + "");
        animateTags.add(animateXTag);
      }

      if (startShape.getPosition().getY() != endShape.getPosition().getY()) {
        String animateYTag = writeAnimateTag(secondsToMs(startTick) + "",
            (secondsToMs(endTick) - secondsToMs(startTick)) + "", "y",
            startShape.getPosition().getY() + "", endShape.getPosition().getY() + "");
        animateTags.add(animateYTag);
      }

      if (startShape.getSize().getWidth() != endShape.getSize().getWidth()) {
        String animateWidthTag = writeAnimateTag(secondsToMs(startTick) + "",
            (secondsToMs(endTick) - secondsToMs(startTick)) + "", "width",
            startShape.getSize().getWidth() + "", endShape.getSize().getWidth() + "");
        animateTags.add(animateWidthTag);
      }

      if (startShape.getSize().getHeight() != endShape.getSize().getHeight()) {
        String animateHeightTag = writeAnimateTag(secondsToMs(startTick) + "",
            (secondsToMs(endTick) - secondsToMs(startTick)) + "", "height",
            startShape.getSize().getHeight() + "", endShape.getSize().getHeight() + "");
        animateTags.add(animateHeightTag);
      }

      if (!(startShape.getColor().equals(endShape.getColor()))) {
        String animateColorTag = writeAnimateColorTag(startShape, endShape, startTick, endTick);
        animateTags.add(animateColorTag);
      }
    }

    String rectAnimates = String.join("\n", animateTags);
    ap.append(rectAnimates);
    ap.append("\n</rect>\n");
  }

  //creates the ellipse and animate svg tags for the specified Ellipse
  private void animateEllipse(Appendable ap, Shape shape, String shapeName) throws IOException {
    ap.append(
        "<ellipse id=\"" + shape.getName() + "\" cx=\"" + shape.getPosition()
            .getX()
            + "\" cy=\"" + shape.getPosition().getY() +
            "\" rx=\"" + (double) shape.getSize().getWidth() / 2.0 + "\" ry=\""
            + (double) shape.getSize()
            .getHeight() / 2.0
            + "\" fill=\"rgb(" + shape.getColor().getRed() + "," +
            shape.getColor().getGreen() + "," + shape.getColor().getBlue()
            + ")\" visibility=\"hidden\">\n"); // visibility="visible">

    ap.append("<set attributeName=\"visibility\" to=\"visible\" begin=\"" +
        secondsToMs(model.getAnimationLog().get(shapeName).get(0).getStartTick()) + "ms\" />\n");

    List<String> animateTags = new ArrayList<String>();

    for (int i = 0; i < model.getAnimationLog().get(shapeName).size(); i++) {
      Shape startShape = model.getAnimationLog().get(shapeName).get(i).getStartShape();
      Shape endShape = model.getAnimationLog().get(shapeName).get(i).getEndShape();
      int startTick = model.getAnimationLog().get(shapeName).get(i).getStartTick();
      int endTick = model.getAnimationLog().get(shapeName).get(i).getEndTick();

      if (startShape.getPosition().getX() != endShape.getPosition().getX()) {
        String animateXTag = writeAnimateTag(secondsToMs(startTick) + "",
            (secondsToMs(endTick) - secondsToMs(startTick)) + "", "cx",
            startShape.getPosition().getX() + "", endShape.getPosition().getX() + "");
        animateTags.add(animateXTag);
      }

      if (startShape.getPosition().getY() != endShape.getPosition().getY()) {
        String animateYTag = writeAnimateTag(secondsToMs(startTick) + "",
            (secondsToMs(endTick) - secondsToMs(startTick)) + "", "cy",
            startShape.getPosition().getY() + "", endShape.getPosition().getY() + "");
        animateTags.add(animateYTag);
      }

      if (startShape.getSize().getWidth() != endShape.getSize().getWidth()) {
        String animateWidthTag = writeAnimateTag(secondsToMs(startTick) + "",
            (secondsToMs(endTick) - secondsToMs(startTick)) + "", "rx",
            (double) startShape.getSize().getWidth() / 2.0 + "",
            (double) endShape.getSize().getWidth() / 2.0 + "");
        animateTags.add(animateWidthTag);
      }

      if (startShape.getSize().getHeight() != endShape.getSize().getHeight()) {
        String animateHeightTag = writeAnimateTag(secondsToMs(startTick) + "",
            (secondsToMs(endTick) - secondsToMs(startTick)) + "", "ry",
            (double) startShape.getSize().getHeight() / 2.0 + "",
            (double) endShape.getSize().getHeight() / 2.0 + "");
        animateTags.add(animateHeightTag);
      }

      if (!(startShape.getColor().equals(endShape.getColor()))) {
        String animateColorTag = writeAnimateColorTag(startShape, endShape, startTick, endTick);
        animateTags.add(animateColorTag);
      }
    }
    String ellipseAnimates = String.join("\n", animateTags);
    ap.append(ellipseAnimates);
    ap.append("\n</ellipse>\n");
  }

  //constructs an animate tag
  private String writeAnimateTag(String begin, String dur, String attributeName, String from,
      String to) {
    return "<animate  attributeType=\"xml\"  begin=\"" + begin + "ms\" dur=\""
        + dur + "ms\" attributeName=\"" + attributeName + "\""
        + " from=\"" + from + "\" to=\"" + to + "\" fill=\"freeze\" />";
  }

  //constructs the rgb change for the animate tags
  private String writeAnimateColorTag(Shape start, Shape end, int t1, int t2) {
    String from = "rgb(" + start.getColor().getRed() + "," +
        start.getColor().getGreen() + "," + start.getColor().getBlue()
        + ")";
    String to = "rgb(" + end.getColor().getRed() + "," +
        end.getColor().getGreen() + "," + end.getColor().getBlue()
        + ")";
    return writeAnimateTag(secondsToMs(t1) + "",
        (secondsToMs(t2) - secondsToMs(t1)) + "", "fill",
        from, to);

  }

}
