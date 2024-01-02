package cs3500.animator;

import cs3500.animator.controller.AnimationController;
import cs3500.animator.controller.Features;
import cs3500.animator.controller.IAnimationController;
import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.AnimationModelImpl.Builder;
import cs3500.animator.util.AnimationReader;
import cs3500.animator.view.EditorView;
import cs3500.animator.view.IView;
import cs3500.animator.model.IViewModel;
import cs3500.animator.model.ViewModel;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * The Excellence class has the main method that takes in command line arguments that will be used
 * to construct an animation based on a given input file.
 */
public final class Excellence {

  /**
   * Main method that is the entry point of the animation program. Takes in command line arguments
   * specifying the input file, the type of view and output desired, and the desired speed of the
   * animation.
   *
   * @param args the list of command line arguments to be passed into the program
   */
  public static void main(String[] args) {
    String in = "";
    String out = "";
    String view = "";
    int speed = 1;
    try {
      for (int i = 0; i < args.length - 1; i++) {
        if (args[i].equals("-view")) {
          view = args[i + 1];
        }
        if (args[i].equals("-in")) {
          in = args[i + 1];
        }
        if (args[i].equals("-out")) {
          out = args[i + 1];
        }
        if (args[i].equals("-speed")) {
          speed = Integer.parseInt(args[i + 1]);
        }
      }

      File f = new File(in);
      Reader r = new FileReader(f);
      AnimationModel m = AnimationReader.parseFile(r, new Builder());
      IViewModel vm = new ViewModel(m);

      if (view.equals("edit")) {
        Features controller = new AnimationController(m, new EditorView(speed, vm));
        controller.go();
      } else {
        IView v = new ViewFactory().createView(view, vm, speed, out);
        v.displayView(new StringBuilder());
      }


    } catch (Exception e) {
      showErrorMessage();
      System.exit(-1);

    }
  }

  /**
   * Displays a JOptionPane if an invalid command line argument is passed into main method.
   */
  private static void showErrorMessage() {
    JFrame frame = new JFrame();
    JOptionPane.showMessageDialog(frame, "This is an invalid command line argument.");
  }

}
