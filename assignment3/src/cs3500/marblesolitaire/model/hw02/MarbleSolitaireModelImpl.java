package cs3500.marblesolitaire.model.hw02;

import java.util.ArrayList;
import java.util.List;


/**
 * Model class implementing the MarbleSolitaireModel interface which has two fields one is a nested
 * array of the enumeration position that is either filled empty or invalid and an int to represent
 * the arm thickness of the board default arm thickness is 3.
 */
public class MarbleSolitaireModelImpl implements MarbleSolitaireModel {


  private final Position[][] board;
  private final int arm;

  /**
   * a public constructor for the MarbleSolitaireModelImpl class takes in no parameters and creates
   * the board with an arm thickness of 3 and the empty position in the middle.
   */
  public MarbleSolitaireModelImpl() {
    this.board = new Position[7][7];
    this.arm = 3;

    for (int i = 0; i < this.board.length; i++) {
      for (int b = 0; b < this.board.length; b++) {
        this.board[i][b] = Position.FILLED;

        isIncorner(i, b, arm - 1);

      }
    }
    this.board[3][3] = Position.EMPTY;
  }

  /**
   * a public constructor for the MarbleSolitaireModelImpl class takes in two ints for the row and
   * column where the empty spot is placed throws an IllegalArgumentException if the position is
   * outside the board.
   */
  public MarbleSolitaireModelImpl(int sRow, int sCol) {
    this.board = new Position[7][7];
    this.arm = 3;

    for (int i = 0; i < this.board.length; i++) {
      for (int b = 0; b < this.board.length; b++) {
        this.board[i][b] = Position.FILLED;

        isIncorner(i, b, arm - 1);

      }
    }

    if (this.board[sRow][sCol] == Position.INVALID) {
      throw new IllegalArgumentException(
          "Invalid empty cell position" + "(" + Integer.toString(sRow)
              + "," + Integer.toString(sCol) + ")");
    }

    this.board[sRow][sCol] = Position.EMPTY;
  }


  /**
   * a public constructor for the MarbleSolitaireModelImpl class takes in one int for the arm
   * thickness throws an IllegalArgumentException if the arm thickness is a even number or less than
   * 3.
   */
  public MarbleSolitaireModelImpl(int arm) {
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

        isIncorner(i, b, armLength);

      }
    }
    this.board[middle][middle] = Position.EMPTY;
  }


  /**
   * a public constructor for the MarbleSolitaireModelImpl class takes in three ints for the arm
   * thickness row and column where the empty spot is placed throws an IllegalArgumentException if
   * the position is outside the board the arm thickness is an even number or is less than 3.
   */
  public MarbleSolitaireModelImpl(int arm, int sRow, int sCol) {
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

        isIncorner(i, b, armLength);

      }
    }

    if (this.board[sRow][sCol] == Position.INVALID) {
      throw new IllegalArgumentException(
          "Invalid empty cell position" + "(" + sRow
              + "," + sCol + ")");
    }

    this.board[sRow][sCol] = Position.EMPTY;
  }

  //checks to see if the position is in one of the four corners and makes the pos invalid if true
  private void isIncorner(int row, int col, int armLength) {
    if (row < armLength && (col < armLength || col >= this.board[col].length - armLength)) {
      this.board[row][col] = Position.INVALID;
    } else if (row >= this.board[row].length - armLength && (col < armLength
        || col >= this.board[col].length - armLength)) {
      this.board[row][col] = Position.INVALID;
    }
  }


  //checks if the position (r, c) is in the board or not
  private boolean isInBoard(int row, int col) {
    // top left corner
    if (row < arm - 1 && col < arm - 1) {
      return false;
    }

    // top right corner
    else if (row < arm - 1 && col > this.board[0].length - (arm - 1)) {
      return false;
    }

    // bottom left corner
    else if (row > this.board.length - (arm - 1) && col < (arm - 1)) {
      return false;
    }

    // bottom right corner if false then the position is in none of the corners
    else if (row > this.board.length - (arm - 1) && col > this.board.length - (arm - 1)) {
      return false;
    }

    //beyond the array
    else {
      return (row < this.board.length && row >= 0) && (col < this.board.length
          && col >= 0);
    }
  }


  //checks if both positions are within range of each other
  private boolean inRange(int fromRow, int fromCol, int toRow, int toCol) {
    //if the rows are within range of each other and the columns are 2 spots away
    if ((fromRow <= toRow && fromRow >= toRow - 2) && (fromCol - toCol == 2
        || toCol - fromCol == 2)) {
      return true;
    }

    //if the columns are within range and the rows are 2 spots away
    else {
      return (fromCol <= toCol && fromCol >= toCol - 2) && (fromRow - toRow == 2
          || toRow - fromRow == 2);
    }
  }

  // checks to see which move is valid and make it
  private void makeMove(int fromRow, int fromCol, int toRow, int toCol) {

    if (fromCol == toCol) {
      //can you move down
      if (fromRow + 2 == toRow) {
        if (this.board[fromRow + 1][fromCol] == Position.FILLED) {
          this.board[toRow][fromCol] = Position.FILLED;
          this.board[fromRow][fromCol] = Position.EMPTY;
          this.board[fromRow + 1][fromCol] = Position.EMPTY;
        } else {
          throw new IllegalArgumentException("not a valid move");
        }

      }

      //can you move up
      else if (fromRow - 2 == toRow) {
        if (this.board[fromRow - 1][fromCol] == Position.FILLED) {
          this.board[toRow][fromCol] = Position.FILLED;
          this.board[fromRow][fromCol] = Position.EMPTY;
          this.board[fromRow - 1][fromCol] = Position.EMPTY;
        } else {
          throw new IllegalArgumentException("not a valid move");
        }

      }
    } else if (fromRow == toRow) {
      //can you move right
      if (fromCol + 2 == toCol) {
        if (this.board[fromRow][fromCol + 1] == Position.FILLED) {
          this.board[fromRow][toCol] = Position.FILLED;
          this.board[fromRow][fromCol] = Position.EMPTY;
          this.board[fromRow][fromCol + 1] = Position.EMPTY;
        } else {
          throw new IllegalArgumentException("not a valid move");
        }
      }

      //can you move left
      else if (fromCol - 2 == toCol) {
        if (this.board[fromRow][fromCol - 1] == Position.FILLED) {
          this.board[fromRow][toCol] = Position.FILLED;
          this.board[fromRow][fromCol] = Position.EMPTY;
          this.board[fromRow][fromCol - 1] = Position.EMPTY;
        } else {
          throw new IllegalArgumentException("not a valid move");
        }
      }
    } else {
      throw new IllegalArgumentException("not a valid move");
    }
  }


  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) {
    // fromRow and fromCol being any position outside the board
    if (!(isInBoard(fromRow, fromCol))) {
      throw new IllegalArgumentException("not a valid marble position");
    }

    // toRow and toCol being any position outside the board
    else if (!(isInBoard(toRow, toCol))) {
      throw new IllegalArgumentException("not a valid marble position");
    }

    //fromRow and fromCol are in one of the corners
    else if (!(inRange(fromRow, fromCol, toRow, toCol))) {
      throw new IllegalArgumentException("positions aren't within range");
    } else if (board[toRow][toCol] == Position.INVALID) {
      throw new IllegalArgumentException("not a valid marble position");
    }

    //is there a marble at position toRow toCol
    else if (this.board[toRow][toCol] == Position.FILLED) {
      throw new IllegalArgumentException("toRow and toCol is already occupied");
    }

    //if the positions and move is valid make the move
    else {
      makeMove(fromRow, fromCol, toRow, toCol);
    }

  }


  private boolean canMoveDown(int row, int col) {
    if (this.board[row + 2][col] == Position.EMPTY) {
      return this.board[row + 1][col] == Position.FILLED;
    }
    return false;
  }


  private boolean canMoveUp(int row, int col) {
    if (this.board[row - 2][col] == Position.EMPTY) {
      return this.board[row - 1][col] == Position.FILLED;
    }
    return false;
  }


  private boolean canMoveLeft(int row, int col) {
    if (this.board[row][col - 2] == Position.EMPTY) {
      return this.board[row][col - 1] == Position.FILLED;
    }
    return false;
  }


  private boolean canMoveRight(int row, int col) {
    if (this.board[row][col + 2] == Position.EMPTY) {
      return this.board[row][col + 1] == Position.FILLED;
    }
    return false;
  }

  //checks to see if the marble at (row, col) can move
  private boolean canMove(int row, int col) {
    // top rows
    if (row <= 1 && board[row][col] == Position.FILLED) {
      return (canMoveLeft(row, col) || canMoveRight(row, col) || canMoveDown(row, col));
    }

    //left side
    else if (col <= 1 && board[row][col] == Position.FILLED) {
      return (canMoveUp(row, col) || canMoveRight(row, col) || canMoveDown(row, col));
    }

    //bottom rows
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


  @Override
  public boolean isGameOver() {
    for (int i = 0; i < this.board.length; i++) {
      for (int b = 0; b < this.board.length; b++) {
        if (this.board[i][b] == Position.FILLED) {
          if (canMove(i, b)) {
            return false;
          }
        }
      }
    }

    return true;
  }


  @Override
  public String getGameState() {
    String[] output = new String[board.length];

    for (int i = 0; i < this.board.length; i++) {
      List<String> rowStrings = new ArrayList<>();
      String row = "";
      for (int b = 0; b < this.board.length; b++) {
        if (this.board[i][b] == Position.INVALID && b < this.arm - 1) {
          rowStrings.add(" ");
        } else if (this.board[i][b] == Position.INVALID && b > this.board.length - (arm - 1)) {
          break;
        } else if (this.board[i][b] == Position.FILLED) {
          rowStrings.add("O");
        } else if (this.board[i][b] == Position.EMPTY) {
          rowStrings.add("_");
        }

      }

      row = String.join(" ", rowStrings);
      output[i] = row;
    }

    return String.join("\n", output);
  }

  @Override
  public int getScore() {
    int score = 0;
    for (int i = 0; i < this.board.length; i++) {
      for (int b = 0; b < this.board.length; b++) {
        if (board[i][b] == Position.FILLED) {
          score += 1;
        }
      }
    }

    return score;
  }
}
