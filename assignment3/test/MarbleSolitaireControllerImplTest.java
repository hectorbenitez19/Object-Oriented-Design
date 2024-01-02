import static org.junit.Assert.assertEquals;

import cs3500.marblesolitaire.controller.MarbleSolitaireController;
import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireMockModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelImpl;
import java.io.StringReader;
import org.junit.Test;


/**
 * testing class for the MarbleSolitaireControllerImpl class.
 */
public class MarbleSolitaireControllerImplTest {


  @Test
  public void testAbruptEnd() {
    MarbleSolitaireModel model = new MarbleSolitaireModelImpl();
    StringBuilder gameLog = new StringBuilder();

    MarbleSolitaireController c = new MarbleSolitaireControllerImpl(new StringReader("4 2 4 4"),
        gameLog);

    try {
      c.playGame(model);
    } catch (IllegalStateException ie) {
      return;
    }
    assertEquals(
        "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 32\n"
            + "Make a move\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O _ _ O O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 31" + "\n", gameLog.toString());
  }


  @Test
  public void testMockModel() {
    StringBuilder gameLog = new StringBuilder();
    MarbleSolitaireModel mock = new MarbleSolitaireMockModel(gameLog);

    MarbleSolitaireController c = new MarbleSolitaireControllerImpl(new StringReader("2 4 5 6"),
        gameLog);
    try {
      c.playGame(mock);
    } catch (IllegalStateException ie) {
      return;
    }

    assertEquals("displaying the game state\n"
        + "getting the score\n"
        + "null\n"
        + "Score: 0\n"
        + "Make a move\n"
        + "checking if game is over\n"
        + "checking if game is over\n"
        + "checking if game is over\n"
        + "checking if game is over\n"
        + "checking if game is over\n"
        + "checking if game is over\n"
        + "checking if game is over\n"
        + "FromRow: 1 FromCol: 3 ToRow: 4 ToCol: 5\n"
        + "displaying the game state\n"
        + "getting the score\n"
        + "null\n"
        + "Score: 0\n"
        + "checking if game is over\n"
        + "checking if game is over\n", gameLog.toString());

  }

  @Test
  public void testEmptyReadable() {
    MarbleSolitaireModel model = new MarbleSolitaireModelImpl();
    StringBuilder gameLog = new StringBuilder();

    MarbleSolitaireController c =
        new MarbleSolitaireControllerImpl(new StringReader(""), gameLog);

    try {
      c.playGame(model);
    } catch (IllegalStateException ie) {
      return;
    }

    assertEquals("    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O\n"
        + "Score: 32\n"
        + "Make a move\n", gameLog.toString());
  }

  @Test
  public void testOnlyInvalid() {
    MarbleSolitaireModel model = new MarbleSolitaireModelImpl();
    StringBuilder gameLog = new StringBuilder();

    MarbleSolitaireController c = new MarbleSolitaireControllerImpl(new StringReader("-4 * e w"),
        gameLog);

    try {
      c.playGame(model);
    } catch (IllegalStateException ie) {
      return;
    }
    assertEquals("    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O\n"
        + "Score: 32\n"
        + "Make a move\n"
        + "invalid from row input\n"
        + "invalid from row input\n"
        + "invalid from row input\n", gameLog.toString());
  }

  @Test
  public void testInvalidInput() {
    MarbleSolitaireModel model = new MarbleSolitaireModelImpl();
    StringBuilder gameLog = new StringBuilder();

    MarbleSolitaireController c =
        new MarbleSolitaireControllerImpl(new StringReader("4 a 6 4 4"), gameLog);

    try {
      c.playGame(model);
    } catch (IllegalStateException ie) {
      return;
    }
    assertEquals("    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O\n"
        + "Score: 32\n"
        + "Make a move\n"
        + "invalid from column input\n"
        + "    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O O _ _ O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O\n"
        + "Score: 31\n", gameLog.toString());
  }

  @Test
  public void testMultipleInvalidInputs() {
    MarbleSolitaireModel model = new MarbleSolitaireModelImpl();
    StringBuilder gameLog = new StringBuilder();

    MarbleSolitaireController c = new MarbleSolitaireControllerImpl(
        new StringReader("# 4 a 6 @ 4 p 4"), gameLog);

    try {
      c.playGame(model);
    } catch (IllegalStateException ie) {
      return;
    }
    assertEquals("    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O\n"
        + "Score: 32\n"
        + "Make a move\n"
        + "invalid from row input\n"
        + "invalid from column input\n"
        + "invalid to row input\n"
        + "invalid to column input\n"
        + "    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O O _ _ O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O\n"
        + "Score: 31\n", gameLog.toString());
  }


  @Test
  public void testInvalidMoveLeft() {
    MarbleSolitaireModel model = new MarbleSolitaireModelImpl();
    StringBuilder gameLog = new StringBuilder();

    MarbleSolitaireController c = new MarbleSolitaireControllerImpl(new StringReader("4 3 4 1"),
        gameLog);

    try {
      c.playGame(model);
    } catch (IllegalStateException ie) {
      return;
    }
    assertEquals("    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O\n"
        + "Score: 32\n"
        + "Make a move\n"
        + "Invalid move. Play again. toRow and toCol is already occupied", gameLog.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullReadable() {
    MarbleSolitaireModel model = new MarbleSolitaireModelImpl();
    StringBuilder gameLog = new StringBuilder();

    MarbleSolitaireController c = new MarbleSolitaireControllerImpl(null,
        gameLog);

    c.playGame(model);
    assertEquals("invalid move", gameLog.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullAppendable() {
    MarbleSolitaireModel model = new MarbleSolitaireModelImpl();
    StringBuilder gameLog = new StringBuilder();

    MarbleSolitaireController c = new MarbleSolitaireControllerImpl(new StringReader("4 3 4 1"),
        null);

    c.playGame(model);
    assertEquals("invalid move", gameLog.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullModel() {
    MarbleSolitaireModel model = null;
    StringBuilder gameLog = new StringBuilder();

    MarbleSolitaireController c = new MarbleSolitaireControllerImpl(new StringReader("4 3 4 1"),
        gameLog);

    c.playGame(model);
    assertEquals("invalid move", gameLog.toString());
  }

  @Test
  public void testInvalidMoveRight() {
    MarbleSolitaireModel model = new MarbleSolitaireModelImpl();
    StringBuilder gameLog = new StringBuilder();

    MarbleSolitaireController c = new MarbleSolitaireControllerImpl(new StringReader("4 3 4 5"),
        gameLog);

    try {
      c.playGame(model);
    } catch (IllegalStateException ie) {
      return;
    }
    assertEquals("    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O\n"
        + "Score: 32\n"
        + "Make a move\n"
        + "Invalid move. Play again. toRow and toCol is already occupied", gameLog.toString());
  }

  @Test
  public void testInvalidMoveUp() {
    MarbleSolitaireModel model = new MarbleSolitaireModelImpl();
    StringBuilder gameLog = new StringBuilder();

    MarbleSolitaireController c = new MarbleSolitaireControllerImpl(new StringReader("4 3 2 3"),
        gameLog);

    try {
      c.playGame(model);
    } catch (IllegalStateException ie) {
      return;
    }
    assertEquals("    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O\n"
        + "Score: 32\n"
        + "Make a move\n"
        + "Invalid move. Play again. toRow and toCol is already occupied", gameLog.toString());
  }

  @Test
  public void testInvalidMoveDown() {
    MarbleSolitaireModel model = new MarbleSolitaireModelImpl();
    StringBuilder gameLog = new StringBuilder();

    MarbleSolitaireController c = new MarbleSolitaireControllerImpl(new StringReader("4 3 6 3"),
        gameLog);

    try {
      c.playGame(model);
    } catch (IllegalStateException ie) {
      return;
    }
    assertEquals("    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O\n"
        + "Score: 32\n"
        + "Make a move\n"
        + "Invalid move. Play again. toRow and toCol is already occupied", gameLog.toString());
  }

  @Test
  public void testInvalidMoveDiagonal() {
    MarbleSolitaireModel model = new MarbleSolitaireModelImpl();
    StringBuilder gameLog = new StringBuilder();

    MarbleSolitaireController c = new MarbleSolitaireControllerImpl(new StringReader("2 3 4 4"),
        gameLog);

    try {
      c.playGame(model);
    } catch (IllegalStateException ie) {
      return;
    }
    assertEquals("    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O\n"
        + "Score: 32\n"
        + "Make a move\n"
        + "Invalid move. Play again. toRow and toCol is already occupied", gameLog.toString());
  }


  @Test
  public void testValidMoveLeft() {
    MarbleSolitaireModel model = new MarbleSolitaireModelImpl();
    StringBuilder gameLog = new StringBuilder();

    MarbleSolitaireController c = new MarbleSolitaireControllerImpl(new StringReader("4 6 4 4"),
        gameLog);

    try {
      c.playGame(model);
    } catch (IllegalStateException ie) {
      return;
    }
    assertEquals("    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O\n"
        + "Score: 32\n"
        + "Make a move\n"
        + "    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O O _ _ O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O\n"
        + "Score: 31\n", gameLog.toString());
  }

  @Test
  public void testValidMoveRight() {
    MarbleSolitaireModel model = new MarbleSolitaireModelImpl();
    StringBuilder gameLog = new StringBuilder();

    MarbleSolitaireController c = new MarbleSolitaireControllerImpl(new StringReader("4 2 4 4"),
        gameLog);

    try {
      c.playGame(model);
    } catch (IllegalStateException ie) {
      return;
    }
    assertEquals("    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O\n"
        + "Score: 32\n"
        + "Make a move\n"
        + "    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O _ _ O O O O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O\n"
        + "Score: 31\n", gameLog.toString());
  }

  @Test
  public void testValidMoveUp() {
    MarbleSolitaireModel model = new MarbleSolitaireModelImpl();
    StringBuilder gameLog = new StringBuilder();

    MarbleSolitaireController c = new MarbleSolitaireControllerImpl(new StringReader("6 4 4 4"),
        gameLog);

    try {
      c.playGame(model);
    } catch (IllegalStateException ie) {
      return;
    }
    assertEquals("    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O\n"
        + "Score: 32\n"
        + "Make a move\n"
        + "    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "    O _ O\n"
        + "    O O O\n"
        + "Score: 31\n", gameLog.toString());
  }

  @Test
  public void testValidMoveDown() {
    MarbleSolitaireModel model = new MarbleSolitaireModelImpl();
    StringBuilder gameLog = new StringBuilder();

    MarbleSolitaireController c = new MarbleSolitaireControllerImpl(new StringReader("2 4 4 4"),
        gameLog);

    try {
      c.playGame(model);
    } catch (IllegalStateException ie) {
      return;
    }
    assertEquals("    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O\n"
        + "Score: 32\n"
        + "Make a move\n"
        + "    O O O\n"
        + "    O _ O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O\n"
        + "Score: 31\n", gameLog.toString());
  }

  @Test
  public void testGameQuitAfterMove() {
    MarbleSolitaireModel model = new MarbleSolitaireModelImpl();
    StringBuilder gameLog = new StringBuilder();

    MarbleSolitaireController c =
        new MarbleSolitaireControllerImpl(new StringReader("4 2 4 4 q"), gameLog);

    c.playGame(model);
    assertEquals("    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O\n"
        + "Score: 32\n"
        + "Make a move\n"
        + "    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O _ _ O O O O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O\n"
        + "Score: 31\n"
        + "Game quit!\n"
        + "State of game when quit:\n"
        + "    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O _ _ O O O O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O\n"
        + "Score: 31", gameLog.toString());
  }

  @Test
  public void testGameQuitAfterInvalidMove() {
    MarbleSolitaireModel model = new MarbleSolitaireModelImpl();
    StringBuilder gameLog = new StringBuilder();

    MarbleSolitaireController c =
        new MarbleSolitaireControllerImpl(new StringReader("4 2 5 4 q"), gameLog);

    c.playGame(model);
    assertEquals("    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O\n"
        + "Score: 32\n"
        + "Make a move\n"
        + "Invalid move. Play again. toRow and toCol is already occupied\n"
        + "Game quit!\n"
        + "State of game when quit:\n"
        + "    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O\n"
        + "Score: 32", gameLog.toString());
  }

  @Test
  public void testGameQuit() {
    MarbleSolitaireModel model = new MarbleSolitaireModelImpl();
    StringBuilder gameLog = new StringBuilder();

    MarbleSolitaireController c =
        new MarbleSolitaireControllerImpl(new StringReader("q"), gameLog);

    c.playGame(model);
    assertEquals("    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O\n"
        + "Score: 32\n"
        + "Make a move\n"
        + "Game quit!\n"
        + "State of game when quit:\n"
        + "    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O\n"
        + "Score: 32", gameLog.toString());
  }


  @Test
  public void testGameQuitCapitalQ() {
    MarbleSolitaireModel model = new MarbleSolitaireModelImpl();
    StringBuilder gameLog = new StringBuilder();

    MarbleSolitaireController c =
        new MarbleSolitaireControllerImpl(new StringReader("Q"), gameLog);

    c.playGame(model);
    assertEquals("    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O\n"
        + "Score: 32\n"
        + "Make a move\n"
        + "Game quit!\n"
        + "State of game when quit:\n"
        + "    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O\n"
        + "Score: 32", gameLog.toString());
  }


  @Test
  public void testGameOver() {
    MarbleSolitaireModel model = new MarbleSolitaireModelImpl();
    StringBuilder gameLog = new StringBuilder();

    String gameover = "6 4 4 4 5 2 5 4 5 5 5 3 3 4 5 4 4 6 4 4 4 3 4 5 6 3 4 3"
        + " 3 3 5 3 3 2 5 2 7 5 5 5 3 6 3 4 2 4 4 4 4 4 6 4 7 4 5 4 1 5 3 5 4 5 2 5 1 3 3 3";

    MarbleSolitaireController c = new MarbleSolitaireControllerImpl(new StringReader(gameover),
        gameLog);

    c.playGame(model);
    assertEquals("    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O\n"
        + "Score: 32\n"
        + "Make a move\n"
        + "    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "    O _ O\n"
        + "    O O O\n"
        + "Score: 31\n"
        + "    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O O O O O\n"
        + "O _ _ O O O O\n"
        + "    O _ O\n"
        + "    O O O\n"
        + "Score: 30\n"
        + "    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O O O O O\n"
        + "O _ O _ _ O O\n"
        + "    O _ O\n"
        + "    O O O\n"
        + "Score: 29\n"
        + "    O O O\n"
        + "    O O O\n"
        + "O O O _ O O O\n"
        + "O O O _ O O O\n"
        + "O _ O O _ O O\n"
        + "    O _ O\n"
        + "    O O O\n"
        + "Score: 28\n"
        + "    O O O\n"
        + "    O O O\n"
        + "O O O _ O O O\n"
        + "O O O O _ _ O\n"
        + "O _ O O _ O O\n"
        + "    O _ O\n"
        + "    O O O\n"
        + "Score: 27\n"
        + "    O O O\n"
        + "    O O O\n"
        + "O O O _ O O O\n"
        + "O O _ _ O _ O\n"
        + "O _ O O _ O O\n"
        + "    O _ O\n"
        + "    O O O\n"
        + "Score: 26\n"
        + "    O O O\n"
        + "    O O O\n"
        + "O O O _ O O O\n"
        + "O O O _ O _ O\n"
        + "O _ _ O _ O O\n"
        + "    _ _ O\n"
        + "    O O O\n"
        + "Score: 25\n"
        + "    O O O\n"
        + "    O O O\n"
        + "O O _ _ O O O\n"
        + "O O _ _ O _ O\n"
        + "O _ O O _ O O\n"
        + "    _ _ O\n"
        + "    O O O\n"
        + "Score: 24\n"
        + "    O O O\n"
        + "    O O O\n"
        + "O _ _ _ O O O\n"
        + "O _ _ _ O _ O\n"
        + "O O O O _ O O\n"
        + "    _ _ O\n"
        + "    O O O\n"
        + "Score: 23\n"
        + "    O O O\n"
        + "    O O O\n"
        + "O _ _ _ O O O\n"
        + "O _ _ _ O _ O\n"
        + "O O O O O O O\n"
        + "    _ _ _\n"
        + "    O O _\n"
        + "Score: 22\n"
        + "    O O O\n"
        + "    O O O\n"
        + "O _ _ O _ _ O\n"
        + "O _ _ _ O _ O\n"
        + "O O O O O O O\n"
        + "    _ _ _\n"
        + "    O O _\n"
        + "Score: 21\n"
        + "    O O O\n"
        + "    O _ O\n"
        + "O _ _ _ _ _ O\n"
        + "O _ _ O O _ O\n"
        + "O O O O O O O\n"
        + "    _ _ _\n"
        + "    O O _\n"
        + "Score: 20\n"
        + "    O O O\n"
        + "    O _ O\n"
        + "O _ _ _ _ _ O\n"
        + "O _ _ _ O _ O\n"
        + "O O O _ O O O\n"
        + "    _ O _\n"
        + "    O O _\n"
        + "Score: 19\n"
        + "    O O O\n"
        + "    O _ O\n"
        + "O _ _ _ _ _ O\n"
        + "O _ _ _ O _ O\n"
        + "O O O O O O O\n"
        + "    _ _ _\n"
        + "    O _ _\n"
        + "Score: 18\n"
        + "    O O _\n"
        + "    O _ _\n"
        + "O _ _ _ O _ O\n"
        + "O _ _ _ O _ O\n"
        + "O O O O O O O\n"
        + "    _ _ _\n"
        + "    O _ _\n"
        + "Score: 17\n"
        + "    O O _\n"
        + "    O _ O\n"
        + "O _ _ _ _ _ O\n"
        + "O _ _ _ _ _ O\n"
        + "O O O O O O O\n"
        + "    _ _ _\n"
        + "    O _ _\n"
        + "Score: 16\n"
        + "    _ O _\n"
        + "    _ _ O\n"
        + "O _ O _ _ _ O\n"
        + "O _ _ _ _ _ O\n"
        + "O O O O O O O\n"
        + "    _ _ _\n"
        + "    O _ _\n"
        + "Score: 15\n"
        + "Game over!\n"
        + "    _ O _\n"
        + "    _ _ O\n"
        + "O _ O _ _ _ O\n"
        + "O _ _ _ _ _ O\n"
        + "O O O O O O O\n"
        + "    _ _ _\n"
        + "    O _ _\n"
        + "Score: 15", gameLog.toString());
  }

  @Test(expected = IllegalStateException.class)
  public void testIncompleteMove() {
    MarbleSolitaireModel model = new MarbleSolitaireModelImpl();
    StringBuilder gameLog = new StringBuilder();

    MarbleSolitaireController c = new MarbleSolitaireControllerImpl(new StringReader("4 2 4"),
        gameLog);

    c.playGame(model);
    assertEquals("invalid move", gameLog.toString());
  }

  @Test
  public void testQuitWithIncompleteMove() {
    MarbleSolitaireModel model = new MarbleSolitaireModelImpl();
    StringBuilder gameLog = new StringBuilder();

    MarbleSolitaireController c = new MarbleSolitaireControllerImpl(new StringReader("4 2 q 4"),
        gameLog);

    c.playGame(model);
    assertEquals("    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O\n"
        + "Score: 32\n"
        + "Make a move\n"
        + "Game quit!\n"
        + "State of game when quit:\n"
        + "    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O\n"
        + "Score: 32", gameLog.toString());
  }

}
