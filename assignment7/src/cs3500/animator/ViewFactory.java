package cs3500.animator;


import cs3500.animator.model.IViewModel;
import cs3500.animator.view.EditorView;
import cs3500.animator.view.IView;
import cs3500.animator.view.SVGView;
import cs3500.animator.view.TextView;
import cs3500.animator.view.VisualView;

/**
 * A factory class for the animation views that take in the type of view, the viewModel, the speed
 * in ticks per second and the specified output destination to send the textual animations to.
 */
public class ViewFactory {

  /**
   * Constructs a view of an animation with the specified parameters.
   *
   * @param input  the type of view to use
   * @param m      the read only model that holds tha animations information
   * @param speed  the speed in ticks per second of the animation
   * @param output the output destination to send the textual animations to
   * @return returns the constructed view
   */
  public IView createView(String input, IViewModel m, int speed, String output) {
    switch (input) {
      case "text":
        return new TextView(m, output);
      case "svg":
        return new SVGView(m, speed, output);
      case "visual":
        return new VisualView(m, speed);
      default:
        throw new IllegalArgumentException(input + " not known");
    }

  }

}
