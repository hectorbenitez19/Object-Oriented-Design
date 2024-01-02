package cs3500.animator.view;

import cs3500.animator.model.IViewModel;
import java.awt.Dimension;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;
import javax.swing.JScrollPane;


/**
 * visual view for the animation model.
 */
public class VisualView extends JFrame implements IView {

  /**
   * constructs a visual view.
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
    timer.schedule(timerTask,0, convertToMs(speed));

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

  public void displayView(Appendable ap) {
    //needs to be empty because the visual view automatically displays itself but this view
    // implements an interface so it needs this method
  }

  private long convertToMs(int s) {
    return (1000 / (long) s);
  }




  private void makeVisible() {
    this.setVisible(true);
  }

  public void refresh() {
    this.repaint();
  }

}
