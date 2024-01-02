package cs3500.marblesolitaire.model.hw02;

/**
 * an enumeration class to represent the status of each position on the board in the
 * MarbleSolitareModelImpl class FILLED means that the position is a valid position with a marble
 * occupying it EMPTY means that it is a valid position with no marble occupying it and INVALID
 * means that it is a position that is not on the board and cannot be used.
 */
public enum Position {
  FILLED("O"), EMPTY("_"), INVALID("");

  private final String disp;

  Position(String disp) {
    this.disp = disp;
  }

  @Override
  public String toString() {
    return this.disp;
  }
}
