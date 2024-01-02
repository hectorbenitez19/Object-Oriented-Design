package cs3500.animator.view;


import cs3500.animator.controller.Features;
import cs3500.animator.model.IViewModel;
import cs3500.animator.model.Motion;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Text view for the animation model it constructs a view of the animation in text format and
 * appends it to an appendable and any specified output file.
 */
public class TextView implements IView {

  private final IViewModel model;
  private final String output;

  public TextView(IViewModel model, String output) {
    this.model = model;
    this.output = output;
  }

  @Override
  public void displayView(Appendable ap) {
    this.outputDescriptions(ap);
  }

  @Override
  public void refresh() {

  }



  /**
   * Appends the animation's text description to the given appendable. Either prints the output to a
   * specified file or to the console.
   *
   * @param ap the appendable object
   */
  private void outputDescriptions(Appendable ap) {
    List<String> descriptions = new ArrayList<>();
    String canvas =
        "canvas " + model.getCanvasX() + " " + model.getCanvasY() + " " + model.getCanvasWidth() +
            " " + model.getCanvasHeight();
    descriptions.add(canvas);
    for (String shapeName : model.getAnimationLog().keySet()) {
      List<String> shapeInfo = new ArrayList<>();
      String shapeDeclaration = model.getShapes().get(shapeName).toString();
      shapeInfo.add(shapeDeclaration);
      for (Motion m : model.getAnimationLog().get(shapeName)) {
        shapeInfo.add(m.toString());
      }
      descriptions.add(String.join("\n", shapeInfo));
    }
    try {
      ap.append(String.join("\n", descriptions));
    } catch (IOException e) {
      throw new IllegalStateException("Append failed", e);
    }

    if (output.equals("")) {
      System.out.print(String.join("\n", descriptions));
    } else {
      writeToFile(output, ap);
    }
  }


  /**
   * Writes the animation's text description to a file specified in the output string.
   *
   * @param output the file that will be written to
   * @param ap     the appendable object that will be written to the file
   */
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


}
