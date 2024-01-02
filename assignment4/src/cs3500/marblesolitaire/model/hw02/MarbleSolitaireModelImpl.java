package cs3500.marblesolitaire.model.hw02;

import cs3500.marblesolitaire.model.hw04.MarbleSolitaireModelAbstract;


/**
 * Model class implementing the MarbleSolitaireModel interface which has two fields one is a nested
 * array of the enumeration position that is either filled empty or invalid and an int to represent
 * the arm thickness of the board default arm thickness is 3.
 */
public class MarbleSolitaireModelImpl extends MarbleSolitaireModelAbstract {


  /**
   * a public constructor for the MarbleSolitaireModelImpl class takes in no parameters and creates
   * the board with an arm thickness of 3 and the empty position in the middle.
   */
  public MarbleSolitaireModelImpl() {
    super(3, 3, 3);

  }

  /**
   * a public constructor for the MarbleSolitaireModelImpl class takes in two ints for the row and
   * column where the empty spot is placed throws an IllegalArgumentException if the position is
   * outside the board.
   */
  public MarbleSolitaireModelImpl(int sRow, int sCol) {
    super(3, sRow, sCol);

  }

  //finds the center of a board with a custom armlength
  private static int findCenter(int arm) {
    return Math.round((arm + ((arm - 1) * 2) - 1) / 2);
  }

  /**
   * a public constructor for the MarbleSolitaireModelImpl class takes in one int for the arm
   * thickness throws an IllegalArgumentException if the arm thickness is a even number or less than
   * 3.
   */
  public MarbleSolitaireModelImpl(int arm) {
    super(arm, findCenter(arm), findCenter(arm));

  }


  /**
   * a public constructor for the MarbleSolitaireModelImpl class takes in three ints for the arm
   * thickness row and column where the empty spot is placed throws an IllegalArgumentException if
   * the position is outside the board the arm thickness is an even number or is less than 3.
   */
  public MarbleSolitaireModelImpl(int arm, int sRow, int sCol) {
    super(arm, sRow, sCol);

  }

  @Override
  protected Position[][] initializeBoard(int arm, int row, int col) {
    this.arm = arm;
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

        isInCorner(i, b, armLength);

      }
    }

    if (this.board[row][col] == Position.INVALID) {
      throw new IllegalArgumentException(
          "Invalid empty cell position" + "(" + row
              + "," + col + ")");
    }

    this.board[row][col] = Position.EMPTY;
    return this.board;
  }



  //checks to see if the position is in one of the four corners and makes the pos invalid if true
  protected void isInCorner(int row, int col, int armLength) {
    if (row < armLength && (col < armLength || col >= this.board.length - armLength)) {
      this.board[row][col] = Position.INVALID;
    } else if (row >= this.board.length - armLength && (col < armLength
        || col >= this.board.length - armLength)) {
      this.board[row][col] = Position.INVALID;
    }
  }




}
