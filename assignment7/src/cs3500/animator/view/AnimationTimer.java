package cs3500.animator.view;

import java.util.TimerTask;

/**
 * A custom TimerTask class that takes in a VisualView and handles refreshing that view every time
 * the timer says to.
 */
public class AnimationTimer extends TimerTask {

  private final  IView view;

  /**
   * Constructs the animation timer objects with the visual view as a parameter.
   *
   * @param view the visual view that this timer is connected to
   */
  public AnimationTimer(IView view) {
    this.view = view;
  }


  @Override
  public void run() {
    view.refresh();
  }
}
