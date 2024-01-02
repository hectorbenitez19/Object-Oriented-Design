package cs3500.animator;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.AnimationModelImpl.Builder;
import cs3500.animator.util.AnimationReader;
import cs3500.animator.view.IView;
import cs3500.animator.model.IViewModel;
import cs3500.animator.model.ViewModel;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * The Excellence class has the main method that takes in command line arguments that will be used
 * to construct an animation.
 */
public final class Excellence {

  /**
   * main method that is the entry point of the animation program.
   *
   * @param args the list of command line arguments to be passed into the program
   * @throws IOException throws an IOException if any of the given command line arguments are
   *                     invalid.
   */
  public static void main(String[] args) throws IOException {
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

      IView v = new ViewFactory().createView(view, vm, speed, out);
      v.displayView(new StringBuilder());


    } catch (Exception e) {
      showErrorMessage();
      System.exit(-1);

    }
  }

  //displays a JOptionPane if an invalid command line argument is passed into main
  private static void showErrorMessage() {
    JFrame frame = new JFrame();
    JOptionPane.showMessageDialog(frame, "This is an invalid command line argument.");
  }

}
