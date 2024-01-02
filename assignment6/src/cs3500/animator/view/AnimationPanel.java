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
 * custom Jpanel used in the visualView class handles drawing the list of shapes at each given tick
 * and render them onto the window.
 */
public class AnimationPanel extends JPanel {

  private IViewModel model;
  private static int currentTick;
  private static int maxEndTick;

  /**
   * constructs an Animation panel.
   * @param model the read only model that will be used to get the shapes and their animation logs
   */
  public AnimationPanel(IViewModel model) {
    super();
    this.model = model;
    this.setBackground(Color.WHITE);
    currentTick = 1;
    maxEndTick = this.getMaxEndTick();
  }


  //gets the max end tick of the animation log
  private int getMaxEndTick() {
    List<Integer> listOfEndTicks = new ArrayList<>();
    for (String shapeName : model.getAnimationLog().keySet()) {
      int endTick = model.getAnimationLog().get(shapeName)
          .get(model.getAnimationLog().get(shapeName).size() - 1).getEndTick();
      listOfEndTicks.add(endTick);
    }
    return Collections.max(listOfEndTicks);
  }


  //the panel should draw the shapes at the current tick and the timer task will call repaint
  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;
    ShapeVisitor<Void> visitor = new RenderVisitor(g2d);
    List<Shape> shapes = model.getShapesAtTick(currentTick);

    for (Shape shape : shapes) {
      if (shape != null) {
        shape.accept(visitor);
      }

      if (currentTick == maxEndTick) {
        currentTick = 0;
      }
    }
    currentTick++;

  }
}
