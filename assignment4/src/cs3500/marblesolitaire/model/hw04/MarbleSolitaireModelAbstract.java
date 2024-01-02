package cs3500.marblesolitaire.model.hw04;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.Position;
import java.util.ArrayList;
import java.util.List;


/**
 * abstract class that handles all of the main functionality of a marble solitaire game initializes
 * a board and a arm length or dimensions for any board type uses the abstract method
 * initializeBoard that is overwritten in every subclass which initializes the board based on its
 * own implementation.
 */
public abstract class MarbleSolitaireModelAbstract implements MarbleSolitaireModel {

  protected Position[][] board;
  protected int arm;

  /**
   * default constructor for the marble solitaire abstract class it takes in three integers which
   * represents the armLength/dimensions of the board and the position of the empty spot and passes
   * it to the abstract method initialize board which sets up the board based on each subclasses
   * implementation and will throw an illegalArgumentException if the three integers violate any of
   * the boards rules/constraints.
   *
   * @param arm armlength or dimension of the board.
   * @param row row of the empty spot.
   * @param col column of the empty spot.
   */
  public MarbleSolitaireModelAbstract(int arm, int row, int col) {
    this.board = initializeBoard(arm, row, col);
    this.arm = arm;
  }

  //initializes the board based on each subclasses own personal implementation by overriding
  //this method in the subclass if an input is not set then it will replace it with the default
  // value.
  protected abstract Position[][] initializeBoard(int arm, int row, int col);


  //checks if the position (r, c) is in the board or not
  protected boolean isInBoard(int row, int col) {
    if (row < 0 || row > board.length) {
      return false;
    } else if (col < 0 || col > board.length) {
      return false;
    }

    return board[row][col] != Position.INVALID;

  }


  //checks if both positions are within range of each other
  protected boolean inRange(int fromRow, int fromCol, int toRow, int toCol) {
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
  protected void makeMove(int fromRow, int fromCol, int toRow, int toCol) {

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
      throw new IllegalArgumentException("not a valid from position");
    }

    // toRow and toCol being any position outside the board
    else if (!(isInBoard(toRow, toCol))) {
      throw new IllegalArgumentException("not a valid to position");
    }

    //fromRow and fromCol are in one of the corners
    else if (!(inRange(fromRow, fromCol, toRow, toCol))) {
      throw new IllegalArgumentException("positions aren't within range");
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


  protected boolean canMoveDown(int row, int col) {
    if (this.board[row + 2][col] == Position.EMPTY) {
      return this.board[row + 1][col] == Position.FILLED;
    }
    return false;
  }


  protected boolean canMoveUp(int row, int col) {
    if (this.board[row - 2][col] == Position.EMPTY) {
      return this.board[row - 1][col] == Position.FILLED;
    }
    return false;
  }


  protected boolean canMoveLeft(int row, int col) {
    if (this.board[row][col - 2] == Position.EMPTY) {
      return this.board[row][col - 1] == Position.FILLED;
    }
    return false;
  }


  protected boolean canMoveRight(int row, int col) {
    if (this.board[row][col + 2] == Position.EMPTY) {
      return this.board[row][col + 1] == Position.FILLED;
    }
    return false;
  }

  //checks to see if the marble at (row, col) can move
  protected boolean canMove(int row, int col) {
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
        if (this.board[i][b] == Position.INVALID && b < arm - 1) {
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
