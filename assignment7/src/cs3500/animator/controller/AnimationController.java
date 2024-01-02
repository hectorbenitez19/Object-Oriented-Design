package cs3500.animator.controller;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.Shape;
import cs3500.animator.view.IEditorView;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class AnimationController implements  Features {

  private final AnimationModel m;
  private final IEditorView v;


  public AnimationController(AnimationModel m, IEditorView v) {
    this.m = m;
    this.v = v;
  //  v.addFeatures(this);

  }

  public void go() {
    v.addFeatures(this);
  }

  @Override
  public void addShape(String shapeName, String type) {
    try {
      m.addShape(shapeName, type);
      v.addToNamesComboBox(shapeName);
    } catch (IllegalArgumentException e) {
      JFrame frame = new JFrame();
      JOptionPane.showMessageDialog(frame, "Not a valid shape type or shape name already exists. "
          + "Please try again. "
          + "(Supported shapes: rectangle, square, ellipse)");
    }
  }

  @Override
  public void deleteShape(String shapeName) {
    m.removeShape(shapeName);
    v.deleteFromNamesComboBox(shapeName);

  }

  @Override
  public void addKeyFrame(String shapeName, int t) {
    if (t < 1) {
      JFrame frame = new JFrame();
      JOptionPane.showMessageDialog(frame, "Tick must be greater than 1");
    }
    if (v.keyFramesComboBoxContains(t)) {
      return;
    }
    if (m.getAnimationLog().get(shapeName).isEmpty()) {
      m.newMotion(shapeName, t, 0, 0, 10, 10, 255, 255, 255, t,
          0, 0, 10, 10, 255, 255, 255);
      v.addToKeyFramesComboBox(t);
    } else {
      for (int i = 0; i < m.getAnimationLog().get(shapeName).size(); i++) {
        int startT = m.getAnimationLog().get(shapeName).get(i).getStartTick();
        int startX =
            (int) m.getAnimationLog().get(shapeName).get(i).getStartShape().getPosition().getX();
        int startY =
            (int) m.getAnimationLog().get(shapeName).get(i).getStartShape().getPosition().getY();
        int startW = m.getAnimationLog().get(shapeName).get(i).getStartShape().getSize().getWidth();
        int startH = m.getAnimationLog().get(shapeName).get(i).getStartShape().getSize()
            .getHeight();
        int startR = m.getAnimationLog().get(shapeName).get(i).getStartShape().getColor().getRed();
        int startG = m.getAnimationLog().get(shapeName).get(i).getStartShape().getColor()
            .getGreen();
        int startB = m.getAnimationLog().get(shapeName).get(i).getStartShape().getColor().getBlue();
        int endT = m.getAnimationLog().get(shapeName).get(i).getEndTick();
        int endX =
            (int) m.getAnimationLog().get(shapeName).get(i).getEndShape().getPosition().getX();
        int endY =
            (int) m.getAnimationLog().get(shapeName).get(i).getEndShape().getPosition().getY();
        int endW = m.getAnimationLog().get(shapeName).get(i).getEndShape().getSize().getWidth();
        int endH = m.getAnimationLog().get(shapeName).get(i).getEndShape().getSize().getHeight();
        int endR = m.getAnimationLog().get(shapeName).get(i).getEndShape().getColor().getRed();
        int endG = m.getAnimationLog().get(shapeName).get(i).getEndShape().getColor().getGreen();
        int endB = m.getAnimationLog().get(shapeName).get(i).getEndShape().getColor().getBlue();

        // if the added KF is before the first motion
        if (i == 0 && t < m.getAnimationLog().get(shapeName).get(i).getStartTick()) {

          m.newMotion(shapeName, t, 0, 0, 10, 10, 255, 255, 255, startT,
              startX, startY, startW, startH, startR, startG, startB);
          v.addToKeyFramesComboBox(t);
          break;
        }
        // if the added KF is after the last motion (my idea->  have them add the kf (add
        // new motion with default values) and then they can change the values after ????
        else if (i == m.getAnimationLog().get(shapeName).size() - 1 && t > m.getAnimationLog()
            .get(shapeName).get(i).getEndTick()) {
          m.newMotion(shapeName, endT, endX, endY, endW, endH, endR, endG, endB, t, 0, 0,
              10, 10, 255, 255, 255);
          v.addToKeyFramesComboBox(t);
          break;
        } else if (t >= m.getAnimationLog().get(shapeName).get(i).getStartTick() && t <= m
            .getAnimationLog()
            .get(shapeName).get(i).getEndTick()) {
          Shape s = m.findShapeAtTick(shapeName, t);
          m.removeMotion(shapeName, startT, startX, startY, startW, startH, startR, startG,
              startB,
              endT, endX, endY, endW, endH, endR, endG, endB);
          m.newMotion(shapeName, startT, startX, startY, startW, startH, startR, startG, startB,
              t, (int) s.getPosition().getX(), (int) s.getPosition().getY(), s.getSize().getWidth(),
              s.getSize().getHeight(), s.getColor().getRed(), s.getColor().getGreen(),
              s.getColor().getBlue());
          m.newMotion(shapeName, t, (int) s.getPosition().getX(), (int) s.getPosition().getY(),
              s.getSize().getWidth(),
              s.getSize().getHeight(), s.getColor().getRed(), s.getColor().getGreen(),
              s.getColor().getBlue(), endT, endX, endY, endW, endH, endR, endG, endB);
          v.addToKeyFramesComboBox(t);
          break;
        }
      }
    }

  }


  @Override
  public void modifyKeyFrame(String shapeName, int t, String x, String y, String w, String h,
      String r, String g, String b) {
    int xfield = checkParsing(x);
    int yfield = checkParsing(y);
    int widthfield = checkParsing(w);
    int heightfield = checkParsing(h);
    int redfield = checkParsing(r);
    int greenfield = checkParsing(g);
    int bluefield = checkParsing(b);
    for (int i = 0; i < m.getAnimationLog().get(shapeName).size(); i++) {
      int oldEndTick = m.getAnimationLog().get(shapeName).get(i).getEndTick();
      double oldEndx = m.getAnimationLog().get(shapeName).get(i).getEndShape().getPosition().getX();
      double oldEndy = m.getAnimationLog().get(shapeName).get(i).getEndShape().getPosition().getY();
      int oldEndWidth = m.getAnimationLog().get(shapeName).get(i).getEndShape().getSize()
          .getWidth();
      int oldEndHeight = m.getAnimationLog().get(shapeName).get(i).getEndShape().getSize()
          .getHeight();
      int oldEndR = m.getAnimationLog().get(shapeName).get(i).getEndShape().getColor().getRed();
      int oldEndG = m.getAnimationLog().get(shapeName).get(i).getEndShape().getColor().getGreen();
      int oldEndB = m.getAnimationLog().get(shapeName).get(i).getEndShape().getColor().getBlue();
      int oldStartTick = m.getAnimationLog().get(shapeName).get(i).getStartTick();
      double oldStartx = m.getAnimationLog().get(shapeName).get(i).getStartShape().getPosition()
          .getX();
      double oldStarty = m.getAnimationLog().get(shapeName).get(i).getStartShape().getPosition()
          .getY();
      int oldStartWidth = m.getAnimationLog().get(shapeName).get(i).getStartShape().getSize()
          .getWidth();
      int oldStartHeight = m.getAnimationLog().get(shapeName).get(i).getStartShape().getSize()
          .getHeight();
      int oldStartR = m.getAnimationLog().get(shapeName).get(i).getStartShape().getColor().getRed();
      int oldStartG = m.getAnimationLog().get(shapeName).get(i).getStartShape().getColor()
          .getGreen();
      int oldStartB = m.getAnimationLog().get(shapeName).get(i).getStartShape().getColor()
          .getBlue();
      if (i < m.getAnimationLog().get(shapeName).size() - 1) {
        int fEndT = m.getAnimationLog().get(shapeName).get(i + 1).getEndTick();
        double fEndX =
            m.getAnimationLog().get(shapeName).get(i + 1).getEndShape().getPosition().getX();
        double fEndY =
            m.getAnimationLog().get(shapeName).get(i + 1).getEndShape().getPosition().getY();
        int fEndW = m.getAnimationLog().get(shapeName).get(i + 1).getEndShape().getSize()
            .getWidth();
        int fEndH = m.getAnimationLog().get(shapeName).get(i + 1).getEndShape().getSize()
            .getHeight();
        int fEndR = m.getAnimationLog().get(shapeName).get(i + 1).getEndShape().getColor()
            .getRed();
        int fEndG = m.getAnimationLog().get(shapeName).get(i + 1).getEndShape().getColor()
            .getGreen();
        int fEndB = m.getAnimationLog().get(shapeName).get(i + 1).getEndShape().getColor()
            .getBlue();
        int fStartT = m.getAnimationLog().get(shapeName).get(i + 1).getStartTick();
        double fStartX = m.getAnimationLog().get(shapeName).get(i + 1).getStartShape()
            .getPosition()
            .getX();
        double fStartY = m.getAnimationLog().get(shapeName).get(i + 1).getStartShape()
            .getPosition()
            .getY();
        int fStartW = m.getAnimationLog().get(shapeName).get(i + 1).getStartShape().getSize()
            .getWidth();
        int fStartH = m.getAnimationLog().get(shapeName).get(i + 1).getStartShape().getSize()
            .getHeight();
        int fStartR =
            m.getAnimationLog().get(shapeName).get(i + 1).getStartShape().getColor().getRed();
        int fStartG = m.getAnimationLog().get(shapeName).get(i + 1).getStartShape().getColor()
            .getGreen();
        int fStartB = m.getAnimationLog().get(shapeName).get(i + 1).getStartShape().getColor()
            .getBlue();
        // if start and end tick equal each other
        if (t == m.getAnimationLog().get(shapeName).get(i).getStartTick() && t == m
            .getAnimationLog()
            .get(shapeName).get(i).getEndTick()) {
          m.removeMotion(shapeName, oldStartTick, (int) oldStartx, (int) oldStarty,
              oldStartWidth,
              oldStartHeight, oldStartR, oldStartG, oldStartB,
              oldEndTick, (int) oldEndx, (int) oldEndy, oldEndWidth, oldEndHeight, oldEndR, oldEndG,
              oldEndB);
          if (t == m.getAnimationLog().get(shapeName).get(i + 1).getStartTick()) {
            m.removeMotion(shapeName, fStartT, (int) fStartX, (int) fStartY,
                fStartW,
                fStartH, fStartR, fStartG, fStartB,
                fEndT, (int) fEndX, (int) fEndY, fEndW, fEndH, fEndR, fEndG,
                fEndB);
            m.newMotion(shapeName, t, xfield, yfield, widthfield, heightfield, redfield, greenfield,
                bluefield, fEndT, (int) fEndX, (int) fEndY, fEndW, fEndH, fEndR, fEndG,
                fEndB);
          }
          m.newMotion(shapeName, t, xfield, yfield, widthfield, heightfield, redfield, greenfield,
              bluefield, t, xfield, yfield, widthfield, heightfield, redfield, greenfield,
              bluefield);
          break;
        }
        if (t == m.getAnimationLog().get(shapeName).get(i).getEndTick() &&
            t != m.getAnimationLog().get(shapeName).get(i).getStartTick()) {
          m.removeMotion(shapeName, oldStartTick, (int) oldStartx, (int) oldStarty, oldStartWidth,
              oldStartHeight, oldStartR, oldStartG, oldStartB,
              oldEndTick, (int) oldEndx, (int) oldEndy, oldEndWidth, oldEndHeight, oldEndR, oldEndG,
              oldEndB);
          if (t == m.getAnimationLog().get(shapeName).get(i + 1).getStartTick()) {
            m.removeMotion(shapeName, fStartT, (int) fStartX, (int) fStartY,
                fStartW,
                fStartH, fStartR, fStartG, fStartB,
                fEndT, (int) fEndX, (int) fEndY, fEndW, fEndH, fEndR, fEndG,
                fEndB);
            m.newMotion(shapeName, t, xfield, yfield, widthfield, heightfield, redfield, greenfield,
                bluefield, fEndT, (int) fEndX, (int) fEndY, fEndW, fEndH, fEndR, fEndG,
                fEndB);
          }
          m.newMotion(shapeName, oldStartTick, (int) oldStartx, (int) oldStarty, oldStartWidth,
              oldStartHeight, oldStartR, oldStartG, oldStartB, t, xfield, yfield, widthfield,
              heightfield, redfield, greenfield, bluefield);
          break;
        }
      } else if (i == m.getAnimationLog().get(shapeName).size() - 1) {
        if (t == m.getAnimationLog().get(shapeName).get(i).getEndTick()) {
          m.removeMotion(shapeName, oldStartTick, (int) oldStartx, (int) oldStarty, oldStartWidth,
              oldStartHeight, oldStartR, oldStartG, oldStartB,
              oldEndTick, (int) oldEndx, (int) oldEndy, oldEndWidth, oldEndHeight, oldEndR, oldEndG,
              oldEndB);
          m.newMotion(shapeName, oldStartTick, (int) oldStartx, (int) oldStarty, oldStartWidth,
              oldStartHeight, oldStartR, oldStartG, oldStartB, t, xfield, yfield, widthfield,
              heightfield, redfield, greenfield, bluefield);
          break;
        }
      }

    }
    JFrame frame = new JFrame();
    JOptionPane.showMessageDialog(frame, "Successfully modified!");
  }


  private Integer checkParsing(String item) {
    if (item == null) {
      return null;
    } else {
      return Integer.parseInt(item);
    }
  }


  @Override
  public void deleteKeyFrame(String shapeName, int t) {
    for (int i = 0; i < m.getAnimationLog().get(shapeName).size(); i++) {
      int oldEndTick = m.getAnimationLog().get(shapeName).get(i).getEndTick();
      double oldEndx = m.getAnimationLog().get(shapeName).get(i).getEndShape().getPosition()
          .getX();
      double oldEndy = m.getAnimationLog().get(shapeName).get(i).getEndShape().getPosition()
          .getY();
      int oldEndWidth = m.getAnimationLog().get(shapeName).get(i).getEndShape().getSize()
          .getWidth();
      int oldEndHeight = m.getAnimationLog().get(shapeName).get(i).getEndShape().getSize()
          .getHeight();
      int oldEndR = m.getAnimationLog().get(shapeName).get(i).getEndShape().getColor().getRed();
      int oldEndG = m.getAnimationLog().get(shapeName).get(i).getEndShape().getColor().getGreen();
      int oldEndB = m.getAnimationLog().get(shapeName).get(i).getEndShape().getColor().getBlue();

      int oldStartTick = m.getAnimationLog().get(shapeName).get(i).getStartTick();
      double oldStartx = m.getAnimationLog().get(shapeName).get(i).getStartShape()
          .getPosition()
          .getX();
      double oldStarty = m.getAnimationLog().get(shapeName).get(i).getStartShape()
          .getPosition()
          .getY();
      int oldStartWidth = m.getAnimationLog().get(shapeName).get(i).getStartShape().getSize()
          .getWidth();
      int oldStartHeight = m.getAnimationLog().get(shapeName).get(i).getStartShape().getSize()
          .getHeight();
      int oldStartR = m.getAnimationLog().get(shapeName).get(i).getStartShape().getColor()
          .getRed();
      int oldStartG = m.getAnimationLog().get(shapeName).get(i).getStartShape().getColor()
          .getGreen();
      int oldStartB = m.getAnimationLog().get(shapeName).get(i).getStartShape().getColor()
          .getBlue();
      if (m.getAnimationLog().get(shapeName).size() == 1) {
        if (t == m.getAnimationLog().get(shapeName).get(i).getStartTick() && t == m
            .getAnimationLog().get(shapeName).get(i).getEndTick()) {
          m.removeMotion(shapeName, oldStartTick, (int) oldStartx, (int) oldStarty,
              oldStartWidth,
              oldStartHeight, oldStartR, oldStartG, oldStartB,
              oldEndTick, (int) oldEndx, (int) oldEndy, oldEndWidth, oldEndHeight, oldEndR, oldEndG,
              oldEndB);
          v.deleteFromKeyFramesComboBox(t);
          break;
        }
        if (t == m.getAnimationLog().get(shapeName).get(i).getStartTick()) {
          m.removeMotion(shapeName, oldStartTick, (int) oldStartx, (int) oldStarty,
              oldStartWidth,
              oldStartHeight, oldStartR, oldStartG, oldStartB,
              oldEndTick, (int) oldEndx, (int) oldEndy, oldEndWidth, oldEndHeight, oldEndR, oldEndG,
              oldEndB);
          m.newMotion(shapeName, oldEndTick, (int) oldEndx, (int) oldEndy, oldEndWidth,
              oldEndHeight,
              oldEndR, oldEndG,
              oldEndB, oldEndTick, (int) oldEndx, (int) oldEndy, oldEndWidth, oldEndHeight, oldEndR,
              oldEndG,
              oldEndB);
          v.deleteFromKeyFramesComboBox(t);
          break;
        } else if (t == m.getAnimationLog().get(shapeName).get(i).getEndTick()) {
          m.removeMotion(shapeName, oldStartTick, (int) oldStartx, (int) oldStarty,
              oldStartWidth,
              oldStartHeight, oldStartR, oldStartG, oldStartB,
              oldEndTick, (int) oldEndx, (int) oldEndy, oldEndWidth, oldEndHeight, oldEndR, oldEndG,
              oldEndB);
          m.newMotion(shapeName, oldStartTick, (int) oldStartx, (int) oldStarty,
              oldStartWidth,
              oldStartHeight, oldStartR, oldStartG, oldStartB, oldStartTick, (int) oldStartx,
              (int) oldStarty,
              oldStartWidth,
              oldStartHeight, oldStartR, oldStartG, oldStartB);
          v.deleteFromKeyFramesComboBox(t);
          break;
        }
      }

      if (t == m.getAnimationLog().get(shapeName).get(i).getStartTick()) {
        m.removeMotionAndAdjust(shapeName, oldStartTick, (int) oldStartx, (int) oldStarty,
            oldStartWidth,
            oldStartHeight, oldStartR, oldStartG, oldStartB,
            oldEndTick, (int) oldEndx, (int) oldEndy, oldEndWidth, oldEndHeight, oldEndR, oldEndG,
            oldEndB);
        v.deleteFromKeyFramesComboBox(t);
        break;
      }

      if (t == m.getAnimationLog().get(shapeName).get(i).getEndTick()
          && i == m.getAnimationLog().get(shapeName).size() - 1) {
        m.removeMotion(shapeName, oldStartTick, (int) oldStartx, (int) oldStarty,
            oldStartWidth,
            oldStartHeight, oldStartR, oldStartG, oldStartB,
            oldEndTick, (int) oldEndx, (int) oldEndy, oldEndWidth, oldEndHeight, oldEndR, oldEndG,
            oldEndB);
        v.deleteFromKeyFramesComboBox(t);
        break;
      }

    }

  }

  @Override
  public void displayTicks(String shapeName) {
    if (shapeName == null) {
      System.out.println("bug");
    } else {
      v.addKeyFramesToComboBox(m.getKeyFrameTicks(shapeName));
    }


  }

  @Override
  public void displayVariables(String shapeName, Integer t) {
    int x = 0;
    int y = 0;
    int w = 0;
    int h = 0;
    int r = 0;
    int g = 0;
    int b = 0;

    if (t != null) {

      Shape s = m.findShapeAtTick(shapeName, t);
      if (s == null) {
        v.displayKeyFrameVariables(x, y, w, h, r, g, b);
      } else {
        x = (int) s.getPosition().getX();
        y = (int) s.getPosition().getY();
        w = s.getSize().getWidth();
        h = s.getSize().getHeight();
        r = s.getColor().getRed();
        g = s.getColor().getGreen();
        b = s.getColor().getBlue();
      }

    }
    v.displayKeyFrameVariables(x, y, w, h, r, g, b);

  }

}
