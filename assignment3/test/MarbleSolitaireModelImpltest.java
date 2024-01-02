import org.junit.Test;

import static org.junit.Assert.assertEquals;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelImpl;

/**
 * testing class for the MarbleSolitaireModelImpl class.
 */
public class MarbleSolitaireModelImpltest {

  private MarbleSolitaireModelImpl board1 = new MarbleSolitaireModelImpl();
  private MarbleSolitaireModelImpl board2 = new MarbleSolitaireModelImpl(5);


  @Test
  public void testGameState() {
    assertEquals(
        "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O", board1.getGameState());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSecondConstructorException() {
    MarbleSolitaireModelImpl board3 = new MarbleSolitaireModelImpl(0, 1);
    assertEquals(
        "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O", board3.getGameState());
  }

  @Test
  public void testSecondConstructor() {
    MarbleSolitaireModelImpl board3 = new MarbleSolitaireModelImpl(3, 5);
    assertEquals(
        "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O O O _ O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O", board3.getGameState());
  }



  @Test
  public void testThirdConstructor() {
    assertEquals(
        "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O _ O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O", board2.getGameState());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testThirdConstructorException() {
    MarbleSolitaireModelImpl board5 = new MarbleSolitaireModelImpl(6);
    assertEquals(
        "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O _ O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O", board5.getGameState());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testFourthConstructorInvalidArm() {
    MarbleSolitaireModelImpl board6 = new MarbleSolitaireModelImpl(6, 4, 4);
    assertEquals(
        "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O _ O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O", board6.getGameState());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testFourthConstructorInvalidPos() {
    MarbleSolitaireModelImpl board6 = new MarbleSolitaireModelImpl(5, 0, 2);
    assertEquals(
        "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O _ O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O", board6.getGameState());
  }


  @Test
  public void testFourthConstructor() {
    MarbleSolitaireModelImpl board4 = new MarbleSolitaireModelImpl(3, 3, 1);
    assertEquals(
        "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O _ O O O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O", board4.getGameState());
  }




  @Test
  public void testValidMoveRight() {
    board1.move(3, 1, 3, 3);
    assertEquals(
        "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O _ _ O O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O", board1.getGameState());
  }

  @Test
  public void testValidMoveLeft() {
    board1.move(3, 5, 3, 3);
    assertEquals(
        "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O O _ _ O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O", board1.getGameState());
  }

  @Test
  public void testValidMoveUp() {
    board1.move(5, 3, 3, 3);
    assertEquals(
        "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "    O _ O\n"
            + "    O O O", board1.getGameState());
  }

  @Test
  public void testValidMoveDown() {
    board1.move(1, 3, 3, 3);
    assertEquals(
        "    O O O\n"
            + "    O _ O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O", board1.getGameState());
  }




  @Test(expected = IllegalArgumentException.class)
  public void testInValidMove() {
    board1.move(4, 5, 5, 5);
    assertEquals(
        "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O", board1.getGameState());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInValidFromPos() {
    board1.move(0, 1, 5, 5);
    assertEquals(
        "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O", board1.getGameState());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInValidFromEmpty() {
    board1.move(3, 3, 5, 5);
    assertEquals(
        "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O", board1.getGameState());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInValidMoveOverEmpty() {
    board1.move(3, 4, 3, 2);
    assertEquals(
        "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O", board1.getGameState());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInValidMoveDiagonal() {
    board1.move(1, 2, 3, 3);
    assertEquals(
        "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O", board1.getGameState());
  }


  @Test(expected = IllegalArgumentException.class)
  public void testInValidMoveCorner() {
    board1.move(4, 5, 0, 1);
    assertEquals(
        "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O", board1.getGameState());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInValidMoveOutsideBoard() {
    board1.move(4, 5, -5, 7);
    assertEquals(
        "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O", board1.getGameState());
  }


  @Test(expected = IllegalArgumentException.class)
  public void testInValidMoveEdge() {
    board1.move(4, 6, 4, 4);
    assertEquals(
        "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O", board1.getGameState());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInValidMoveInBoard() {
    board1.move(2, 1, 4, 1);
    assertEquals(
        "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O", board1.getGameState());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInValidMoveNotWithinRange() {
    board1.move(3, 0, 3, 3);
    assertEquals(
        "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O", board1.getGameState());
  }


  @Test
  public void testGetScore() {
    assertEquals(32, board1.getScore());
  }


  @Test
  public void testIsGameOverFalse() {
    assertEquals(false, board1.isGameOver());

  }


  @Test
  public void testIsGameOverTrue() {
    board1.move(5, 3, 3, 3);
    board1.move(4, 1, 4, 3);
    board1.move(4, 4, 4, 2);
    board1.move(2, 3, 4, 3);
    board1.move(3, 5, 3, 3);
    board1.move(3, 2, 3, 4);
    board1.move(5, 2, 3, 2);
    board1.move(2, 2, 4, 2);
    board1.move(2, 1, 4, 1);
    board1.move(6, 4, 4, 4);
    board1.move(2, 5, 2, 3);
    board1.move(1, 3, 3, 3);
    board1.move(3, 3, 5, 3);
    board1.move(6, 3, 4, 3);
    board1.move(0, 4, 2, 4);
    board1.move(3, 4, 1, 4);
    board1.move(0, 2, 2, 2);

    assertEquals(true, board1.isGameOver());

  }


}
