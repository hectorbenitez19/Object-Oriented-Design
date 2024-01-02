package cs3500.animator.view;

import cs3500.animator.controller.Features;
import cs3500.animator.model.IViewModel;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;
import javax.swing.JScrollPane;


/**
 * An object of the visual view for the animation model. This view presents itself in the
 * constructor, and is visible in a window frame with scroll bars if the frame is not large enough
 * to hold the animation.
 */
public class VisualView extends JFrame implements IView {

  /**
   * Constructs a visual view with the specified speed.
   *
   * @param model read only version of the animation model
   * @param speed speed in ticks per second
   */
  public VisualView(IViewModel model, int speed) {
    super();
    TimerTask timerTask;
    Timer timer;
    AnimationPanel panel;
    JScrollPane scrollPane;

    timerTask = new AnimationTimer(this);
    timer = new Timer();
    timer.schedule(timerTask, 0, convertToMs(speed));

    this.setTitle("Visual Animation View");
    this.setSize(700, 700);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    panel = new AnimationPanel(model);
    panel.setPreferredSize(new Dimension(model.getCanvasX() + model.getCanvasWidth(),
        model.getCanvasY() + model.getCanvasHeight()));

    scrollPane = new JScrollPane(panel);
    this.add(scrollPane);
    this.makeVisible();
  }


  /**
   * Converts the speed to milliseconds in a simple calculation.
   *
   * @param s the speed in ticks per second
   * @return the converted speed in milliseconds
   */
  private long convertToMs(int s) {
    return (1000 / (long) s);
  }


  /**
   * Makes this view visible to the user.
   */
  private void makeVisible() {
    this.setVisible(true);
  }

  @Override
  public void displayView(Appendable ap) {

  }

  /**
   * Refreshes this view, which ultimately calls the paintComponent method again. This method is
   * used by the Animation timer and refreshes based on the speed given by the user.
   */
  public void refresh() {
    this.repaint();
  }


}
