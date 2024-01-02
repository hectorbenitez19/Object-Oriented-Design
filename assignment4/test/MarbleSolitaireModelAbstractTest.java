import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModelImpl;
import cs3500.marblesolitaire.model.hw04.MarbleSolitaireModelAbstract;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModelImpl;
import org.junit.Test;

/**
 * testing class for the marble solitaire abstract class tests the other two board types european
 * and triangle boards and tests the functionality of the board.
 */
public class MarbleSolitaireModelAbstractTest {

  MarbleSolitaireModelAbstract euroBoard = new EuropeanSolitaireModelImpl();
  MarbleSolitaireModelAbstract euroBoard2 = new EuropeanSolitaireModelImpl(5);
  MarbleSolitaireModelAbstract triangleBoard = new TriangleSolitaireModelImpl();
  MarbleSolitaireModelAbstract triangleBoard2 = new TriangleSolitaireModelImpl(7);
  MarbleSolitaireModelAbstract triangleBoard3 = new TriangleSolitaireModelImpl(8);

  @Test
  public void testGameStateEuro() {

    assertEquals("    O O O\n"
        + "  O O O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "  O O O O O\n"
        + "    O O O", euroBoard.getGameState());
  }

  @Test
  public void testEuroConstructorPos() {
    MarbleSolitaireModelAbstract euroBoard = new EuropeanSolitaireModelImpl(3, 3);

    assertEquals("    O O O\n"
        + "  O O O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "  O O O O O\n"
        + "    O O O", euroBoard.getGameState());
  }

  @Test
  public void testEuroConstructorArm() {
    MarbleSolitaireModelAbstract euroBoard = new EuropeanSolitaireModelImpl(7);

    assertEquals("            O O O O O O O\n"
        + "          O O O O O O O O O\n"
        + "        O O O O O O O O O O O\n"
        + "      O O O O O O O O O O O O O\n"
        + "    O O O O O O O O O O O O O O O\n"
        + "  O O O O O O O O O O O O O O O O O\n"
        + "O O O O O O O O O O O O O O O O O O O\n"
        + "O O O O O O O O O O O O O O O O O O O\n"
        + "O O O O O O O O O O O O O O O O O O O\n"
        + "O O O O O O O O O _ O O O O O O O O O\n"
        + "O O O O O O O O O O O O O O O O O O O\n"
        + "O O O O O O O O O O O O O O O O O O O\n"
        + "O O O O O O O O O O O O O O O O O O O\n"
        + "  O O O O O O O O O O O O O O O O O\n"
        + "    O O O O O O O O O O O O O O O\n"
        + "      O O O O O O O O O O O O O\n"
        + "        O O O O O O O O O O O\n"
        + "          O O O O O O O O O\n"
        + "            O O O O O O O", euroBoard.getGameState());
  }

  @Test
  public void testEuroConstructorAllThreeInputs() {
    MarbleSolitaireModelAbstract euroBoard = new EuropeanSolitaireModelImpl(7, 3, 3);

    assertEquals("            O O O O O O O\n"
        + "          O O O O O O O O O\n"
        + "        O O O O O O O O O O O\n"
        + "      _ O O O O O O O O O O O O\n"
        + "    O O O O O O O O O O O O O O O\n"
        + "  O O O O O O O O O O O O O O O O O\n"
        + "O O O O O O O O O O O O O O O O O O O\n"
        + "O O O O O O O O O O O O O O O O O O O\n"
        + "O O O O O O O O O O O O O O O O O O O\n"
        + "O O O O O O O O O O O O O O O O O O O\n"
        + "O O O O O O O O O O O O O O O O O O O\n"
        + "O O O O O O O O O O O O O O O O O O O\n"
        + "O O O O O O O O O O O O O O O O O O O\n"
        + "  O O O O O O O O O O O O O O O O O\n"
        + "    O O O O O O O O O O O O O O O\n"
        + "      O O O O O O O O O O O O O\n"
        + "        O O O O O O O O O O O\n"
        + "          O O O O O O O O O\n"
        + "            O O O O O O O", euroBoard.getGameState());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEuroInvalidArm() {
    MarbleSolitaireModelAbstract euroBoard = new EuropeanSolitaireModelImpl(2);
    assertEquals("", euroBoard.getGameState());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEuroInvalidPosition() {
    MarbleSolitaireModelAbstract euroBoard = new EuropeanSolitaireModelImpl(0, 1);
    assertEquals("", euroBoard.getGameState());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEuroInvalidPositionAndArm() {
    MarbleSolitaireModelAbstract euroBoard = new EuropeanSolitaireModelImpl(2, 0, 1);
    assertEquals("", euroBoard.getGameState());
  }


  @Test
  public void testGameStateEuro2() {
    assertEquals(
        "        O O O O O\n"
            + "      O O O O O O O\n"
            + "    O O O O O O O O O\n"
            + "  O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O _ O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "  O O O O O O O O O O O\n"
            + "    O O O O O O O O O\n"
            + "      O O O O O O O\n"
            + "        O O O O O", euroBoard2.getGameState());
  }

  @Test
  public void testGameStateTriangle() {
    assertEquals("    _\n"
        + "   O O\n"
        + "  O O O\n"
        + " O O O O\n"
        + "O O O O O", triangleBoard.getGameState());

  }

  @Test(expected = IllegalArgumentException.class)
  public void testTriangleInvalidDimensions() {
    MarbleSolitaireModelAbstract triangleBoard2 = new TriangleSolitaireModelImpl(-1);
    assertEquals("    _\n"
        + "   O O\n"
        + "  O O O\n"
        + " O O O O\n"
        + "O O O O O", triangleBoard2.getGameState());

  }

  @Test(expected = IllegalArgumentException.class)
  public void testTriangleInvalidPos() {
    MarbleSolitaireModelAbstract triangleBoard2 = new TriangleSolitaireModelImpl(0, 3);
    assertEquals("    _\n"
        + "   O O\n"
        + "  O O O\n"
        + " O O O O\n"
        + "O O O O O", triangleBoard2.getGameState());

  }

  @Test(expected = IllegalArgumentException.class)
  public void testTriangleInvalidPosAndDimension() {
    MarbleSolitaireModelAbstract triangleBoard2 =
        new TriangleSolitaireModelImpl(-1, 0, 3);
    assertEquals("    _\n"
        + "   O O\n"
        + "  O O O\n"
        + " O O O O\n"
        + "O O O O O", triangleBoard2.getGameState());

  }

  @Test
  public void testTriangleConstructorPos() {
    MarbleSolitaireModelAbstract triangleBoard2 = new TriangleSolitaireModelImpl(2, 2);
    assertEquals("    O\n"
        + "   O O\n"
        + "  O O _\n"
        + " O O O O\n"
        + "O O O O O", triangleBoard2.getGameState());

  }


  @Test
  public void testGameStateTriangle2() {
    assertEquals("      _\n"
        + "     O O\n"
        + "    O O O\n"
        + "   O O O O\n"
        + "  O O O O O\n"
        + " O O O O O O\n"
        + "O O O O O O O", triangleBoard2.getGameState());

  }

  @Test
  public void testGameStateTriangle3() {
    MarbleSolitaireModelAbstract triangleBoardCustom =
        new TriangleSolitaireModelImpl(8, 3, 1);
    assertEquals(
        "       O\n"
            + "      O O\n"
            + "     O O O\n"
            + "    O _ O O\n"
            + "   O O O O O\n"
            + "  O O O O O O\n"
            + " O O O O O O O\n"
            + "O O O O O O O O", triangleBoardCustom.getGameState());

  }

  @Test
  public void triangleMoveUpLeft() {
    triangleBoard.move(2, 2, 0, 0);

    assertEquals("    O\n"
        + "   O _\n"
        + "  O O _\n"
        + " O O O O\n"
        + "O O O O O", triangleBoard.getGameState());
  }

  @Test(expected = IllegalArgumentException.class)
  public void triangleInvalidFromPos() {
    triangleBoard.move(0, 0, 2, 2);

    assertEquals("    O\n"
        + "   O _\n"
        + "  O O _\n"
        + " O O O O\n"
        + "O O O O O", triangleBoard.getGameState());
  }

  @Test(expected = IllegalArgumentException.class)
  public void triangleInvalidToPos() {
    triangleBoard.move(0, 0, 2, 3);

    assertEquals("    O\n"
        + "   O _\n"
        + "  O O _\n"
        + " O O O O\n"
        + "O O O O O", triangleBoard.getGameState());
  }

  @Test
  public void triangleMoveUpRight() {
    triangleBoard.move(2, 0, 0, 0);

    assertEquals("    O\n"
        + "   _ O\n"
        + "  _ O O\n"
        + " O O O O\n"
        + "O O O O O", triangleBoard.getGameState());
  }

  @Test(expected = IllegalArgumentException.class)
  public void triangleInvalidMoveEmptyInMiddle() {
    triangleBoard.move(2, 0, 0, 0);
    triangleBoard.move(0, 0, 2, 0);

    assertEquals("    O\n"
        + "   _ O\n"
        + "  _ O O\n"
        + " O O O O\n"
        + "O O O O O", triangleBoard.getGameState());
  }

  @Test(expected = IllegalArgumentException.class)
  public void triangleInvalidMoveRight() {
    triangleBoard.move(2, 2, 0, 0);
    triangleBoard.move(4, 0, 4, 2);

    assertEquals("    O\n"
        + "   O _\n"
        + "  _ _ O\n"
        + " O O O O\n"
        + "O O O O O", triangleBoard.getGameState());
  }

  @Test(expected = IllegalArgumentException.class)
  public void triangleInvalidMoveLeft() {
    MarbleSolitaireModelAbstract triangleBoard2 = new TriangleSolitaireModelImpl(3, 1);
    triangleBoard2.move(4, 3, 4, 1);

    assertEquals("    O\n"
        + "   O O\n"
        + "  O O O\n"
        + " O O _ _\n"
        + "O O O O O", triangleBoard2.getGameState());
  }

  @Test(expected = IllegalArgumentException.class)
  public void triangleInvalidMoveDownLeft() {
    MarbleSolitaireModelAbstract triangleBoard2 = new TriangleSolitaireModelImpl(3, 1);
    triangleBoard2.move(2, 1, 4, 1);

    assertEquals("    O\n"
        + "   O _\n"
        + "  O _ O\n"
        + " O O O O\n"
        + "O O O O O", triangleBoard2.getGameState());
  }

  @Test(expected = IllegalArgumentException.class)
  public void triangleInvalidMoveDownRight() {
    MarbleSolitaireModelAbstract triangleBoard2 = new TriangleSolitaireModelImpl(3, 2);
    triangleBoard2.move(2, 1, 3, 2);

    assertEquals("    O\n"
        + "   _ O\n"
        + "  O _ O\n"
        + " O O O O\n"
        + "O O O O O", triangleBoard2.getGameState());
  }


  @Test
  public void triangleMoveRight() {
    triangleBoard.move(2, 2, 0, 0);
    triangleBoard.move(2, 0, 2, 2);

    assertEquals("    O\n"
        + "   O _\n"
        + "  _ _ O\n"
        + " O O O O\n"
        + "O O O O O", triangleBoard.getGameState());
  }

  @Test
  public void triangleMoveLeft() {
    MarbleSolitaireModelAbstract triangleBoard2 = new TriangleSolitaireModelImpl(3, 1);
    triangleBoard2.move(3, 3, 3, 1);

    assertEquals("    O\n"
        + "   O O\n"
        + "  O O O\n"
        + " O O _ _\n"
        + "O O O O O", triangleBoard2.getGameState());
  }

  @Test
  public void triangleMoveDownLeft() {
    MarbleSolitaireModelAbstract triangleBoard2 = new TriangleSolitaireModelImpl(3, 1);
    triangleBoard2.move(1, 1, 3, 1);

    assertEquals("    O\n"
        + "   O _\n"
        + "  O _ O\n"
        + " O O O O\n"
        + "O O O O O", triangleBoard2.getGameState());
  }

  @Test
  public void triangleMoveDownRight() {
    MarbleSolitaireModelAbstract triangleBoard2 = new TriangleSolitaireModelImpl(3, 2);
    triangleBoard2.move(1, 0, 3, 2);

    assertEquals("    O\n"
        + "   _ O\n"
        + "  O _ O\n"
        + " O O O O\n"
        + "O O O O O", triangleBoard2.getGameState());
  }


  @Test
  public void testTriangleGameOverTrue() {
    triangleBoard.move(2, 2, 0, 0);
    triangleBoard.move(4, 2, 2, 2);
    triangleBoard.move(4, 0, 4, 2);
    triangleBoard.move(4, 3, 4, 1);
    triangleBoard.move(3, 1, 1, 1);
    triangleBoard.move(2, 0, 4, 0);
    triangleBoard.move(4, 0, 4, 2);
    triangleBoard.move(0, 0, 2, 0);
    triangleBoard.move(2, 2, 0, 0);
    triangleBoard.move(4, 4, 2, 2);

    assertTrue(triangleBoard.isGameOver());
  }

  @Test
  public void testTriangleGameOverFalse() {

    assertFalse(triangleBoard.isGameOver());
  }

  @Test
  public void testEuroBoardGameOverTrue() {
    euroBoard.move(5, 3, 3, 3);
    euroBoard.move(4, 1, 4, 3);
    euroBoard.move(4, 4, 4, 2);
    euroBoard.move(2, 3, 4, 3);
    euroBoard.move(3, 5, 3, 3);
    euroBoard.move(3, 2, 3, 4);
    euroBoard.move(5, 2, 3, 2);
    euroBoard.move(2, 2, 4, 2);
    euroBoard.move(2, 1, 4, 1);
    euroBoard.move(6, 4, 4, 4);
    euroBoard.move(2, 5, 2, 3);
    euroBoard.move(1, 3, 3, 3);
    euroBoard.move(3, 3, 5, 3);
    euroBoard.move(6, 3, 4, 3);
    euroBoard.move(0, 4, 2, 4);
    euroBoard.move(3, 4, 1, 4);
    euroBoard.move(0, 2, 2, 2);
    euroBoard.move(5, 1, 3, 1);
    euroBoard.move(1, 5, 1, 3);
    euroBoard.move(3, 0, 3, 2);
    euroBoard.move(4, 3, 4, 1);
    euroBoard.move(4, 0, 4, 2);
    euroBoard.move(5, 5, 3, 5);
    euroBoard.move(3, 6, 3, 4);
    euroBoard.move(4, 4, 2, 4);
    euroBoard.move(3, 2, 5, 2);
    euroBoard.move(0, 3, 2, 3);
    euroBoard.move(2, 3, 2, 5);
    euroBoard.move(2, 6, 2, 4);
    euroBoard.move(6, 2, 4, 2);

    assertTrue(euroBoard.isGameOver());

  }


  @Test
  public void testEuroIsGameOverFalse() {

    assertEquals(false, euroBoard.isGameOver());
  }


  @Test
  public void testEuroBoardMoveRight() {
    MarbleSolitaireModelAbstract customEuroBoard = new EuropeanSolitaireModelImpl(1, 3);
    customEuroBoard.move(1, 1, 1, 3);

    assertEquals("    O O O\n"
        + "  _ _ O O O\n"
        + "O O O O O O O\n"
        + "O O O O O O O\n"
        + "O O O O O O O\n"
        + "  O O O O O\n"
        + "    O O O", customEuroBoard.getGameState());
  }

  @Test
  public void testEuroBoardMoveLeft() {
    MarbleSolitaireModelAbstract customEuroBoard = new EuropeanSolitaireModelImpl(1, 3);
    customEuroBoard.move(1, 5, 1, 3);

    assertEquals("    O O O\n"
        + "  O O O _ _\n"
        + "O O O O O O O\n"
        + "O O O O O O O\n"
        + "O O O O O O O\n"
        + "  O O O O O\n"
        + "    O O O", customEuroBoard.getGameState());
  }

  @Test
  public void testEuroBoardMoveUp() {
    MarbleSolitaireModelAbstract customEuroBoard = new EuropeanSolitaireModelImpl(1, 3);
    customEuroBoard.move(1, 5, 1, 3);
    customEuroBoard.move(3, 4, 1, 4);

    assertEquals("    O O O\n"
        + "  O O O O _\n"
        + "O O O O _ O O\n"
        + "O O O O _ O O\n"
        + "O O O O O O O\n"
        + "  O O O O O\n"
        + "    O O O", customEuroBoard.getGameState());
  }

  @Test
  public void testEuroBoardMoveDown() {
    MarbleSolitaireModelAbstract customEuroBoard = new EuropeanSolitaireModelImpl(1, 3);
    customEuroBoard.move(1, 5, 1, 3);
    customEuroBoard.move(3, 4, 1, 4);
    customEuroBoard.move(0, 4, 2, 4);

    assertEquals("    O O _\n"
        + "  O O O _ _\n"
        + "O O O O O O O\n"
        + "O O O O _ O O\n"
        + "O O O O O O O\n"
        + "  O O O O O\n"
        + "    O O O", customEuroBoard.getGameState());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEuroBoardInvalidFromPos() {
    MarbleSolitaireModelAbstract customEuroBoard = new EuropeanSolitaireModelImpl(1, 3);
    customEuroBoard.move(0, 0, 1, 3);

    assertEquals("    O O _\n"
        + "  O O O _ _\n"
        + "O O O O O O O\n"
        + "O O O O _ O O\n"
        + "O O O O O O O\n"
        + "  O O O O O\n"
        + "    O O O", customEuroBoard.getGameState());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEuroBoardInvalidToPos() {
    MarbleSolitaireModelAbstract customEuroBoard = new EuropeanSolitaireModelImpl(1, 3);
    customEuroBoard.move(3, 3, 0, 0);

    assertEquals("    O O _\n"
        + "  O O O _ _\n"
        + "O O O O O O O\n"
        + "O O O O _ O O\n"
        + "O O O O O O O\n"
        + "  O O O O O\n"
        + "    O O O", customEuroBoard.getGameState());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEuroBoardInvalidDiagonalMove() {
    MarbleSolitaireModelAbstract customEuroBoard = new EuropeanSolitaireModelImpl(1, 3);
    customEuroBoard.move(2, 3, 1, 3);

    assertEquals("    O O _\n"
        + "  O O O _ _\n"
        + "O O O O O O O\n"
        + "O O O O _ O O\n"
        + "O O O O O O O\n"
        + "  O O O O O\n"
        + "    O O O", customEuroBoard.getGameState());
  }


  @Test(expected = IllegalArgumentException.class)
  public void testEuroBoardInvalidMoveRight() {
    MarbleSolitaireModelAbstract customEuroBoard = new EuropeanSolitaireModelImpl(1, 3);
    customEuroBoard.move(1, 2, 1, 4);

    assertEquals("    O O O\n"
        + "  _ _ O O O\n"
        + "O O O O O O O\n"
        + "O O O O O O O\n"
        + "O O O O O O O\n"
        + "  O O O O O\n"
        + "    O O O", customEuroBoard.getGameState());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEuroBoardInvalidMoveEmptyInMiddle() {
    MarbleSolitaireModelAbstract customEuroBoard = new EuropeanSolitaireModelImpl(1, 3);
    customEuroBoard.move(1, 2, 1, 4);
    customEuroBoard.move(1, 3, 1, 1);

    assertEquals("    O O O\n"
        + "  _ _ O O O\n"
        + "O O O O O O O\n"
        + "O O O O O O O\n"
        + "O O O O O O O\n"
        + "  O O O O O\n"
        + "    O O O", customEuroBoard.getGameState());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEuroBoardInvalidMoveLeft() {
    MarbleSolitaireModelAbstract customEuroBoard = new EuropeanSolitaireModelImpl(1, 3);
    customEuroBoard.move(1, 4, 1, 2);

    assertEquals("    O O O\n"
        + "  O O O _ _\n"
        + "O O O O O O O\n"
        + "O O O O O O O\n"
        + "O O O O O O O\n"
        + "  O O O O O\n"
        + "    O O O", customEuroBoard.getGameState());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEuroBoardInvalidMoveUp() {
    MarbleSolitaireModelAbstract customEuroBoard = new EuropeanSolitaireModelImpl(1, 3);
    customEuroBoard.move(1, 5, 1, 3);
    customEuroBoard.move(3, 3, 1, 3);

    assertEquals("    O O O\n"
        + "  O O O O _\n"
        + "O O O O _ O O\n"
        + "O O O O _ O O\n"
        + "O O O O O O O\n"
        + "  O O O O O\n"
        + "    O O O", customEuroBoard.getGameState());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEuroBoardInvalidMoveDown() {
    MarbleSolitaireModelAbstract customEuroBoard = new EuropeanSolitaireModelImpl(1, 3);
    customEuroBoard.move(1, 5, 1, 3);
    customEuroBoard.move(3, 4, 1, 4);
    customEuroBoard.move(0, 3, 2, 3);

    assertEquals("    O O _\n"
        + "  O O O _ _\n"
        + "O O O O O O O\n"
        + "O O O O _ O O\n"
        + "O O O O O O O\n"
        + "  O O O O O\n"
        + "    O O O", customEuroBoard.getGameState());
  }


}
