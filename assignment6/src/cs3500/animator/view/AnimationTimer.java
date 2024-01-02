package cs3500.animator.view;

import java.util.TimerTask;

/**
 * custom TimerTask class that takes in a VisualView and handles refreshing that view every time the
 * timer says to.
 */
public class AnimationTimer extends TimerTask {

  private VisualView view;

  public AnimationTimer(VisualView view) {
    this.view = view;
  }


  @Override
  public void run() {
    view.refresh();
  }
}
