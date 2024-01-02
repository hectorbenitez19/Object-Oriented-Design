import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



/**
 * Represents an object of an animation model. It contains various shapes that can move, change
 * dimensions, and change color while the animation is in action, and all motions of a shape must
 * not overlap (from time 1 to 10, a shape can't be moving in two different directions). A shape
 * must also not be able to teleport, or change it's state in an instantaneous moment.
 */
public class AnimationModelImpl implements AnimationModel {


  private final Map<String, Shape> shapes;
  private final Map<String, ArrayList<Motion>> animationLog;

  /**
   * Constructs an object of the blank animation model with no shapes in it.
   */
  public AnimationModelImpl() {

    this.shapes = new HashMap<String, Shape>();
    this.animationLog = new HashMap<String, ArrayList<Motion>>();
  }


  @Override
  public void newMotion(String shapeName, Motion motion) {
    if (motion == null) {
      throw new IllegalArgumentException("The motion cannot be null");
    }
    // checks to make sure the specified shape exists
    if (!shapes.containsKey(shapeName)) {
      throw new IllegalArgumentException("That shape name does not exist");
    }
    // checks that the first motion entered for a shape agrees with it's original state shape
    if (animationLog.get(shapeName).isEmpty()) {
      if (!(motion.getStartShape().equals(shapes.get(shapeName)))) {
        throw new IllegalArgumentException(
            "Starting shape state must agree with its previous shape state");
      }
    }

    // checks that all adjacent motions agree at a common shape state
    for (int i = 0; i < animationLog.get(shapeName).size(); i++) {
      Motion m = animationLog.get(shapeName).get(i);
      // i == animationLog.get(shapeName).size() - 1 <- last motion
      if (motion.getStartTick() >= m.getStartTick() && motion.getStartTick() < m.getEndTick()
          || motion.getEndTick() > m.getStartTick() && motion.getEndTick() < m.getEndTick()) {
        throw new IllegalArgumentException("You cannot overlap motions");
      }
      // if its the first one (and its adjacent) or if its the last one (and its adjacent)
      if ((i == 0 && isAdjacent(m, motion)) || (i == animationLog.get(shapeName).size() - 1
          && isAdjacent(m, motion))) {
        if (motion.getStartTick() == m.getEndTick()) {
          if (!(motion.getStartShape().equals(m.getEndShape()))) {
            throw new IllegalArgumentException(
                "Starting and ending shape states must agree with each other");
          }
        }
        if (motion.getEndTick() == m.getStartTick()) {
          if (!(motion.getEndShape().equals(m.getStartShape()))) {
            throw new IllegalArgumentException(
                "Starting and ending shape states must agree with each other");
          }
        }
      }
      if (i == 0 && !isAdjacent(m, motion)) {
        Motion lastMotion = animationLog.get(shapeName).get(animationLog.get(shapeName).size()-1);
        if (!isAdjacent(lastMotion, motion)) {
        throw new IllegalArgumentException(
            "No gaps allowed");
       }
      }
    }
    ArrayList<Motion> log = animationLog.get(shapeName);
    log = addInOrder(animationLog.get(shapeName), motion);


  }

  private boolean isAdjacent(Motion m1, Motion m2) {
    return m1.getStartTick() == m2.getEndTick() || m1.getEndTick() == m2.getStartTick();
  }


  /**
   * Adds a shape log to the given ArrayList of ShapeLogs in a manner than keeps all ShapeLogs in
   * order of their starting and ending ticks. This method maintains that the timeline of ShapeLogs
   * is consecutive, and it returns the modified ArrayList.
   *
   * @param listOfLogs the already sorted ArrayList of ShapeLogs to be added to
   * @param motion     the ShapeLog to be added to the ArrayList
   * @return the newly modified list of ShapeLogs
   */
  private ArrayList<Motion> addInOrder(ArrayList<Motion> listOfLogs, Motion motion) {
    if (listOfLogs.isEmpty()) {
      listOfLogs.add(motion);
      return listOfLogs;
    }

    for (int i = 0; i < listOfLogs.size(); i++) {
      if (i == listOfLogs.size() - 1) { // if this ShapeLog is the last one in the list
        if (listOfLogs.get(i).getStartTick() >= motion.getEndTick()) {
          listOfLogs.add(i, motion);
          break;
        } else {
          listOfLogs.add(i + 1, motion);
          break;
        }
      }
      if (listOfLogs.get(i).getStartTick() >= motion.getEndTick()) {
        listOfLogs.add(i, motion);
        break;
      } else if (listOfLogs.get(i).getEndTick() <= motion.getStartTick()
          && motion.getEndTick() <= listOfLogs.get(i + 1).getStartTick()) {
        listOfLogs.add(i + 1, motion);
        break;
      }

    }
    return listOfLogs;
  }


  @Override
  public void addShape(Shape shape) {
    if (this.shapes.containsKey(shape.getName())) {
      throw new IllegalArgumentException("This shape name already exists");
    }
    shapes.put(shape.getName(), shape);
    animationLog.put(shape.getName(), new ArrayList<Motion>());
  }

  @Override
  public String outputDescriptions() {
    ArrayList<String> descriptions = new ArrayList<>();

    for (String shapeName : animationLog.keySet()) {
      ArrayList<String> shapeInfo = new ArrayList<>();
      String shapeDeclaration = shapes.get(shapeName).toString();
      shapeInfo.add(shapeDeclaration);

      for (Motion m : animationLog.get(shapeName)) {
        shapeInfo.add(m.toString());
      }
      descriptions.add(String.join("\n", shapeInfo));
    }

    return String.join("\n", descriptions);
  }




}
