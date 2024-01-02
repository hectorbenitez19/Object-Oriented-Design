package cs3500.marblesolitaire.model.hw04;


import cs3500.marblesolitaire.model.hw02.Position;
import java.util.ArrayList;
import java.util.List;

/**
 * subclass of the MarbleAbstract class which creates a triangle shaped board extends some
 * functionality of the abstract class.
 */
public class TriangleSolitaireModelImpl extends MarbleSolitaireModelAbstract {


  /**
   * default constructor for the triangle class that sets the dimensions of the board to 5 and sets
   * the empty position at (0,0).
   */
  public TriangleSolitaireModelImpl() {
    super(5, 0, 0);

  }


  /**
   * custom constructor for the triangle class that takes in an integer that represents the
   * dimensions of the board throws an IllegalArgumentException if the dimensions is less than 1.
   *
   * @param dimension represents the size of the board (NxN grid).
   */
  public TriangleSolitaireModelImpl(int dimension) {
    super(dimension, 0, 0);

  }


  /**
   * custom constructor for the triangle class that takes in the row and column where the empty spot
   * is placed throws an illegal argument exception if the position is invalid.
   *
   * @param row row of the empty spot.
   * @param col column of the empty spot.
   */
  public TriangleSolitaireModelImpl(int row, int col) {
    super(5, row, col);
  }

  /**
   * custom constructor for the triangle class that takes in three integers that represents the
   * dimensions and the position of the empty spot throws an Illegal argument exception if the
   * dimensions is less than 1 and if the position for the empty spot is invalid.
   *
   * @param dimension dimensions of the board.
   * @param row       row of empty spot.
   * @param col       column of empty spot.
   */
  public TriangleSolitaireModelImpl(int dimension, int row, int col) {
    super(dimension, row, col);

  }

  @Override
  protected Position[][] initializeBoard(int dimension, int row, int col) {
    if (dimension < 0) {
      throw new IllegalArgumentException("dimension cannot be negative");
    }

    if ((row < 0 || col < 0) || (row > dimension || col > dimension)) {
      throw new IllegalArgumentException("invalid position");
    }

    this.board = new Position[dimension][dimension];
    for (int i = 0; i < this.board.length; i++) {
      for (int b = 0; b < i + 1; b++) {
        this.board[i][b] = Position.FILLED;
      }
    }

    for (int i = 0; i < this.board.length; i++) {
      for (int b = i + 1; b < this.board.length; b++) {
        this.board[i][b] = Position.INVALID;
      }
    }

    if (board[row][col] == Position.INVALID) {
      throw new IllegalArgumentException("invalid position");
    }

    this.board[row][col] = Position.EMPTY;

    return this.board;
  }


  @Override
  //needed to override isInBoard since the coordinate structure is vastly different than the
  //other boards.
  protected boolean isInBoard(int row, int col) {
    if (row < 0 || col < 0) {
      return false;
    }
    if (row > board.length || col > board.length) {
      return false;
    }

    return board[row][col] != Position.INVALID;

  }


  @Override
  //need to override inRange since diagonal moves are now a possibility for this board.
  protected boolean inRange(int fromRow, int fromCol, int toRow, int toCol) {
    //if the rows are within range of each other and the columns are 2 spots away
    if (fromRow - 2 == toRow || toRow - 2 == fromRow) {
      return true;
    }

    //if the columns are within range and the rows are 2 spots away
    else {
      return (fromCol - 2 == toCol || toCol - 2 == fromCol);
    }
  }

  @Override
  // needed to override makeMove since there are new moves that are exclusive to the triangle
  // board that the old implementation doesnt account for.
  protected void makeMove(int fromRow, int fromCol, int toRow, int toCol) {
    if (fromRow < 0 || fromCol < 0 || (fromRow > board.length || fromCol > board.length)) {
      throw new IllegalArgumentException("invalid from position");
    }

    if (toRow < 0 || toCol < 0 || (toRow > board.length || toCol > board.length)) {
      throw new IllegalArgumentException("invalid to position");
    }
    //can you move down
    if (fromRow + 2 == toRow) {
      //diagonal down left
      if (fromCol == toCol) {
        if (this.board[fromRow + 1][fromCol] == Position.FILLED) {
          this.board[toRow][toCol] = Position.FILLED;
          this.board[fromRow][fromCol] = Position.EMPTY;
          this.board[fromRow + 1][fromCol] = Position.EMPTY;
        } else {
          throw new IllegalArgumentException("not a valid move");
        }
      }

      //diagonal down right
      else if (fromCol + 2 == toCol) {
        if (this.board[fromRow + 1][fromCol + 1] == Position.FILLED) {
          this.board[toRow][toCol] = Position.FILLED;
          this.board[fromRow][fromCol] = Position.EMPTY;
          this.board[fromRow + 1][fromCol + 1] = Position.EMPTY;
        } else {
          throw new IllegalArgumentException("not a valid move");
        }
      }
    }

    //can you move up
    else if (fromRow - 2 == toRow) {
      //diagonal up left
      if (fromCol - 2 == toCol) {
        if (this.board[fromRow - 1][fromCol - 1] == Position.FILLED) {
          this.board[toRow][toCol] = Position.FILLED;
          this.board[fromRow][fromCol] = Position.EMPTY;
          this.board[fromRow - 1][fromCol - 1] = Position.EMPTY;
        } else {
          throw new IllegalArgumentException("not a valid move");
        }

      }
      //diagonal up right
      else if (fromCol == toCol) {
        if (this.board[fromRow - 1][fromCol] == Position.FILLED) {
          this.board[toRow][toCol] = Position.FILLED;
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
        }
      }

      //can you move left
      else if (fromCol - 2 == toCol) {
        if (this.board[fromRow][fromCol - 1] == Position.FILLED) {
          this.board[fromRow][toCol] = Position.FILLED;
          this.board[fromRow][fromCol] = Position.EMPTY;
          this.board[fromRow][fromCol - 1] = Position.EMPTY;
        }
      }
    } else {
      throw new IllegalArgumentException("not a valid move");
    }
  }


  private boolean canMoveDiagonalUpLeft(int row, int col) {
    //diagonal up left
    if (board[row - 2][col - 2] == Position.EMPTY) {
      return board[row - 1][col - 1] == Position.FILLED;
    }
    return false;
  }

  private boolean canMoveDiagonalUpRight(int row, int col) {
    //diagonal up right
    if (board[row - 2][col] == Position.EMPTY) {
      return board[row - 1][col] == Position.FILLED;
    }
    return false;
  }

  private boolean canMoveDiagonalDownRight(int row, int col) {
    //diagonal down right
    if (board[row + 2][col + 2] == Position.EMPTY) {
      return board[row - 1][col + 1] == Position.FILLED;
    }
    return false;
  }

  private boolean canMoveDiagonalDownLeft(int row, int col) {
    //diagonal down left
    if (board[row + 2][col] == Position.EMPTY) {
      return board[row + 1][col] == Position.FILLED;
    }
    return false;
  }


  @Override
  //checks to see if the marble at (row, col) can move
  protected boolean canMove(int row, int col) {

    //top 2 rows
    if (row <= 1) {
      return (canMoveDiagonalDownLeft(row, col) || canMoveDiagonalDownRight(row, col));
    }
    //left side
    else if (col == 0) {
      return (canMoveDiagonalDownRight(row, col) || canMoveRight(row, col)
          || canMoveDiagonalUpRight(row, col));
    }

    //right side
    else if (col == row) {
      return (canMoveDiagonalDownLeft(row, col) || canMoveDiagonalUpLeft(row, col) || canMoveLeft(
          row, col));
    }

    //bottom 2 rows
    else if (row >= board.length - 2) {
      return (canMoveDiagonalUpRight(row, col) || canMoveDiagonalUpLeft(row, col) || canMoveRight(
          row, col) || canMoveLeft(row, col));
    }

    return false;

  }


  @Override
  public String getGameState() {
    String[] output = new String[board.length];
    int spaces = board.length - 1;
    for (int i = 0; i < this.board.length; i++) {
      List<String> rowStrings = new ArrayList<>();
      List<String> rowSpaces = new ArrayList<>();
      String stringSpaces = "";
      String row = "";
      String rowWithSpaces = "";

      for (int b = 0; b < this.board.length; b++) {

        if (this.board[i][b] == Position.INVALID) {
          break;
        } else if (this.board[i][b] == Position.FILLED) {
          rowStrings.add("O");
        } else if (this.board[i][b] == Position.EMPTY) {
          rowStrings.add("_");
        }
      }
      for (int j = 0; j < spaces; j++) {
        rowSpaces.add(" ");
      }
      spaces -= 1;

      stringSpaces = String.join("", rowSpaces);
      row = String.join(" ", rowStrings);
      rowWithSpaces = stringSpaces + row;
      output[i] = rowWithSpaces;
    }
    return String.join("\n", output);
  }

}
