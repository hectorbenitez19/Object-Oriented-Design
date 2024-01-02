package cs3500.animator.model;

import java.util.ArrayList;
import java.util.Map;


/**
 * View Model class that implements the IViewModel interface. This class holds a read only version
 * of the Animation model that is used to pass the models information to each view without giving
 * the original version of it.
 */
public class ViewModel extends AnimationModelImpl implements IViewModel {

  private final AnimationModel model;

  public ViewModel(AnimationModel model) {
    this.model = model;
  }

  @Override
  public Map<String, Shape> getShapes() {
    return model.getShapes();
  }

  @Override
  public Map<String, ArrayList<Motion>> getAnimationLog() {
    return model.getAnimationLog();
  }

  @Override
  public int getCanvasX() {
    return model.getCanvasX();
  }

  @Override
  public int getCanvasY() {
    return model.getCanvasY();
  }

  @Override
  public int getCanvasHeight() {
    return model.getCanvasHeight();
  }

  @Override
  public int getCanvasWidth() {
    return model.getCanvasWidth();
  }

  @Override
  public ArrayList<Shape> getShapesAtTick(int t) {
    ArrayList<Shape> shapes = new ArrayList<>();
    for (String shapeName : model.getAnimationLog().keySet()) {
      shapes.add(model.findShapeAtTick(shapeName, t));
    }

    return shapes;
  }
}
