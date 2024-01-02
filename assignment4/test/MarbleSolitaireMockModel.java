import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;

/**
 * A mock Model of the marbleSolitaire class to test the controller.
 */
public class MarbleSolitaireMockModel implements MarbleSolitaireModel {

  private final StringBuilder log;

  public MarbleSolitaireMockModel(StringBuilder log) {
    this.log = log;
  }


  //make sure that the inputs given to the model are correct and
  // follow the rules the controller established
  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) {
    log.append(
        "FromRow: " + fromRow + " FromCol: " + fromCol + " ToRow: " + toRow + " ToCol: " + toCol
            + "\n");
  }

  //the rest of the methods are just to check if the
  // controller properly goes into each method when it needs to
  @Override
  public boolean isGameOver() {
    log.append("checking if game is over" + "\n");
    return false;
  }

  @Override
  public String getGameState() {
    log.append("displaying the game state" + "\n");
    return null;
  }

  @Override
  public int getScore() {
    log.append("getting the score" + "\n");
    return 0;
  }
}
