package cs3500.animator.view;


import cs3500.animator.controller.Features;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Interface for all of the views for the Animation model. Contains one method called
 * displayView(Appendable ap) that is used to call the correct method that will render the correct
 * view. The Text and SVG view will build upon the appendable object passed in.
 */
public interface IView {

  /**
   * Displays the view to the user.
   *
   * @param ap appendable that the text and SVG view appends to
   */
  void displayView(Appendable ap);

  void refresh();

}
