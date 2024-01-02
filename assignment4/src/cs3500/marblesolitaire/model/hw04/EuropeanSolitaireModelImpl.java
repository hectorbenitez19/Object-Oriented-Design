package cs3500.marblesolitaire.model.hw04;

import cs3500.marblesolitaire.model.hw02.Position;


/**
 * A subclass of the marbelAbstract class that constructs a european marble solitaire board that
 * makes it look like an octagonal shape it extends all main functionality of the abstract class.
 */
public class EuropeanSolitaireModelImpl extends MarbleSolitaireModelAbstract {

  /**
   * default constructor for the european board class that sets the default parameters and calls
   * super for the abstract class base constructor that will initialize the board with armlength of
   * 3 and the position at row 3 column 3.
   */
  public EuropeanSolitaireModelImpl() {
    super(3, 3, 3);


  }

  private static int findCenter(int arm) {
    int newLength = arm + ((arm - 1) * 2);
    return Math.round((newLength - 1) / 2);
  }

  /**
   * custom constructor for the european board class that takes in an integer that represents the
   * arm length of the board throws an IllegalArgumentException if the arm is less than 3 or is an
   * odd number.
   *
   * @param arm represents the arm length of the board.
   */
  public EuropeanSolitaireModelImpl(int arm) {
    super(arm, findCenter(arm), findCenter(arm));

  }


  /**
   * custom constructor for the european board class that takes in two integers that represents the
   * coordinates for the empty spot throws an IllegalArgumentException if the position is invalid.
   *
   * @param row represents the row for the empty spot.
   * @param col represents the column for the empty spot.
   */
  public EuropeanSolitaireModelImpl(int row, int col) {
    super(3, row, col);

  }


  /**
   * custom constructor that takes in three integers that represents the arm length and the position
   * of the empty spot throws an IllegalArgumentException if the arm is less than 3 or is odd and if
   * the position for the empty spot is invalid.
   *
   * @param arm arm length of the board.
   * @param row row for the empty spot.
   * @param col column for the empty spot.
   */
  public EuropeanSolitaireModelImpl(int arm, int row, int col) {
    super(arm, row, col);

  }

  @Override
  protected Position[][] initializeBoard(int arm, int row, int col) {
    int newLength = arm + ((arm - 1) * 2);
    int middle = Math.round((newLength - 1) / 2);
    int armLength = arm - 1;
    this.board = new Position[newLength][newLength];

    if (arm % 2 == 0 || arm < 3) {
      throw new IllegalArgumentException("not a valid arm thickness");
    }

    for (int i = 0; i < this.board.length; i++) {
      for (int b = 0; b < this.board.length; b++) {
        this.board[i][b] = Position.FILLED;
      }
    }

    checkTopPos(armLength);
    checkBottomPos(armLength);

    if (this.board[row][col] == Position.INVALID) {
      throw new IllegalArgumentException(
          "Invalid empty cell position" + "(" + row
              + "," + col + ")");
    }

    this.board[row][col] = Position.EMPTY;

    return this.board;
  }

  //creates the octagonal pattern for the top part of the board
  private void checkTopPos(int armLength) {
    int lowerbound = armLength;
    int upperbound = this.board.length - armLength;
    for (int i = 0; i < armLength; i++) {
      for (int b = 0; b < this.board.length; b++) {
        if (i < armLength && (b < lowerbound || b >= upperbound)) {
          //   System.out.println("(" + i + "," + b + ")");
          this.board[i][b] = Position.INVALID;
        }

      }
      lowerbound -= 1;
      upperbound += 1;
    }
  }


  //creates the octogonal pattern for the bottom part of the board.
  private void checkBottomPos(int armLength) {
    int lowerbound = armLength;
    int upperbound = this.board.length - armLength;
    for (int i = this.board.length - 1; i > this.board.length - (armLength + 1); i--) {
      for (int b = 0; b < this.board.length; b++) {
        if (i >= this.board.length - armLength && (b < lowerbound || b >= upperbound)) {
          //  System.out.println("(" + i + "," + b + ")");
          this.board[i][b] = Position.INVALID;
        }
      }
      lowerbound -= 1;
      upperbound += 1;

    }
  }


  @Override
  //needed to override canMove because there are new spots that will throw an index out of bounds
  //error for the old implementation in the abstract class.
  protected boolean canMove(int row, int col) {
    // top 2 rows
    if (row <= 1 && board[row][col] == Position.FILLED && col >= arm - 1 || col <= board.length - (
        arm - 1)) {
      return (canMoveLeft(row, col) || canMoveRight(row, col) || canMoveDown(row, col));
    }

    //top left octagonal marble
    if (row == arm - 2 && col == 1) {
      return canMoveRight(row, col) || canMoveDown(row, col);
    }

    //top right octagonal marble
    if (row == arm - 2 && col == board.length - 1) {
      return canMoveLeft(row, col) || canMoveDown(row, col);
    }

    //bottom left octagonal marble
    if (row == board.length - (arm - 2) && col == 1) {
      return canMoveRight(row, col) || canMoveUp(row, col);
    }

    //bottom right octagonal marble
    if (row == board.length - (arm - 2) && col == board.length - 1) {
      return canMoveLeft(row, col) || canMoveUp(row, col);
    }

    //left side
    else if (col <= 1 && board[row][col] == Position.FILLED) {
      return (canMoveUp(row, col) || canMoveRight(row, col) || canMoveDown(row, col));
    }

    //bottom 2 rows
    else if (row >= board.length - 2 && board[row][col] == Position.FILLED) {
      return (canMoveUp(row, col) || canMoveRight(row, col) || canMoveLeft(row, col));
    }

    //right side
    else if (col >= board.length - 2 && board[row][col] == Position.FILLED) {
      return (canMoveUp(row, col) || canMoveLeft(row, col) || canMoveDown(row, col));
    }

    //everywhere else
    else if ((row + 2 < this.board.length && col + 2 < this.board.length) || (row - 2 > 0
        && col - 2 > 0)) {
      return (canMoveUp(row, col) || canMoveLeft(row, col) || canMoveRight(row, col) ||
          canMoveDown(row, col));
    }
    return false;

  }


}
