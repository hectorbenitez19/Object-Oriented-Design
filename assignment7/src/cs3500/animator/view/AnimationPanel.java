package cs3500.animator.view;

import cs3500.animator.model.IViewModel;
import cs3500.animator.model.Shape;
import cs3500.animator.model.ShapeVisitor;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JPanel;


/**
 * A custom JPanel used in the visualView class that handles drawing the list of shapes at each tick
 * int that animation and render them onto the window.
 */
public class AnimationPanel extends JPanel {

  private final IViewModel model;
  private static int currentTick;
  private static int maxEndTick;
  private boolean loop;

  /**
   * Constructs an Animation panel with a white background and the current tick starting at 1.
   *
   * @param model the read only model that will be used to get the shapes and their animation logs
   */
  public AnimationPanel(IViewModel model) {
    super();
    loop = true;
    this.model = model;
    this.setBackground(Color.WHITE);
    currentTick = 1;
    maxEndTick = this.getMaxEndTick();
  }

  /**
   * Gets the maximum end tick found in the Animation model. This allows the view to know when the
   * animation is over and reset the current tick to zero in order to replay the animation
   * continuously.
   *
   * @return the maximum end tick in the animation
   */
  private int getMaxEndTick() {
    List<Integer> listOfEndTicks = new ArrayList<>();
    for (String shapeName : model.getAnimationLog().keySet()) {
      int endTick = model.getAnimationLog().get(shapeName)
          .get(model.getAnimationLog().get(shapeName).size() - 1).getEndTick();
      listOfEndTicks.add(endTick);
    }
    return Collections.max(listOfEndTicks);
  }

  public void setCurrentTick(int newTick) {
    currentTick = newTick;
  }

  public void setLoop(boolean b) {
    this.loop = b;
  }

  public void enableLooping() {
    if(loop) {
      if (currentTick == maxEndTick) {
        currentTick = 0;
      }
    }
  }

  public boolean getLoop() {
    return this.loop;
  }


  /**
   * Overrides the original paintComponent method so that the animation will paint all the shapes at
   * each tick. This method gets refreshed based on the AnimationTimer, and the current tick gets
   * updated to zero when the animation is over so that it can be replayed continuously.
   *
   * @param g the graphics object to be painted on the panel
   */
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;
    ShapeVisitor<Void> visitor = new RenderVisitor(g2d);
    List<Shape> shapes = model.getShapesAtTick(currentTick);

    for (Shape shape : shapes) {
      if (shape != null) {
        shape.accept(visitor);
      }

     this.enableLooping();
    }
    currentTick++;

  }
}
