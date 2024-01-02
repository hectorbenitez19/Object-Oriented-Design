package cs3500.marblesolitaire.controller;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Class that implements the MarbleSolitaireController interface.
 */
public class MarbleSolitaireControllerImpl implements MarbleSolitaireController {

  private final Appendable ap;
  private final Scanner scan;


  /**
   * constructor for the MarbleSolitaireControllerImpl class.
   *
   * @param rd Readable for the scanner to send inputs to the appendable.
   * @param ap appendable to display messages to the console.
   * @throws IllegalArgumentException is thrown when either rd or ap is null.
   */
  public MarbleSolitaireControllerImpl(Readable rd, Appendable ap) throws IllegalArgumentException {
    if (rd == null || ap == null) {
      throw new IllegalArgumentException("Readable and Appendable can't be null");
    } else if (rd.toString().isEmpty()) {
      throw new IllegalStateException("readable is empty");
    }
    this.ap = ap;
    scan = new Scanner(rd);
  }

  private void appendToConsole(String msg) {
    try {
      ap.append(msg);
    } catch (IOException ioe) {
      throw new IllegalStateException("Append failed", ioe);
    }
  }


  private boolean isNumber(String input) {
    try {
      Integer.parseInt(input);
    } catch (NumberFormatException e) {
      return false;
    }
    return true;
  }


  private int stringToNumber(String input) {
    try {
      Integer.parseInt(input);
    } catch (NumberFormatException e) {
      throw new IllegalStateException("input was not a number", e);
    }
    return Integer.parseInt(input);
  }


  private boolean ifQuit(MarbleSolitaireModel model, String input) {
    return input.equals("q") || input.equals("Q");
  }

  private void quit(MarbleSolitaireModel model, String input) {
    if (input.equals("q") || input.equals("Q")) {
      try {
        ap.append("Game quit!\n" + "State of game when quit:" + "\n" + model.getGameState() + "\n"
            + "Score: " + model.getScore());
      } catch (IOException ioe) {
        throw new IllegalStateException("Append failed", ioe);
      }
    }
  }


  private void makeMove(MarbleSolitaireModel model, int fromRow, int fromCol, int toRow,
      int toCol) {
    try {
      model.move(fromRow, fromCol, toRow, toCol);
      appendToConsole(model.getGameState() + "\n" + "Score: " + model.getScore() + "\n");
    } catch (IllegalArgumentException ioe) {

      appendToConsole("Invalid move. Play again. " + ioe.getMessage() + "\n");

    }
  }


  private void writeErrorMessage(List<Integer> move) {
    if (move.size() == 0) {
      appendToConsole("invalid from row input" + "\n");
    } else if (move.size() == 1) {
      appendToConsole("invalid from column input" + "\n");
    } else if (move.size() == 2) {
      appendToConsole("invalid to row input" + "\n");
    } else if (move.size() == 3) {
      appendToConsole("invalid to column input" + "\n");
    }

  }

  @Override
  public void playGame(MarbleSolitaireModel model)
      throws IllegalArgumentException, IllegalStateException {

    if (model == null) {
      throw new IllegalArgumentException("Model cannot be null");
    }

    if (scan.toString().isEmpty()) {
      throw new IllegalStateException("readable is empty");
    }

    List<Integer> move = new ArrayList<Integer>();

    appendToConsole(
        model.getGameState() + "\n" + "Score: "
            + model.getScore() + "\n" + "Make a move" + "\n");

    while (!(model.isGameOver())) {

      if (!scan.hasNext()) {
        throw new IllegalStateException("no more inputs");
      }

      String current = scan.next();

      if (ifQuit(model, current)) {
        quit(model, current);
        move.clear();
        break;
      }

      if (isNumber(current) && stringToNumber(current) >= 0) {
        move.add(stringToNumber(current) - 1);
      }

      if (move.size() == 4) {
        makeMove(model, move.get(0), move.get(1), move.get(2), move.get(3));
        move.clear();
      }

      if (model.isGameOver()) {
        try {
          ap.append("Game over!\n" + model.getGameState() + "\n" + "Score: " + model.getScore());
          move.clear();
        } catch (IOException ioe) {
          throw new IllegalStateException("Append failed", ioe);
        }
        break;
      }

      if (!(isNumber(current) || ifQuit(model, current))) {
        writeErrorMessage(move);
      }

    }

    if (move.size() > 0) {
      throw new IllegalStateException("incomplete move");
    }
  }
}

