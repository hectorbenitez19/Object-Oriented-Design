package cs3500.marblesolitaire;


import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelImpl;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModelImpl;
import cs3500.marblesolitaire.model.hw04.MarbleSolitaireModelAbstract;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModelImpl;

import java.io.InputStreamReader;


import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * main method for the game marble solitaire accepts command line arguments and sends them to the
 * controller if it is invalid then it will output a message to the console.
 */
public final class MarbleSolitaire {

  /**
   * main method that takes in command line arguments and if it is valid it will be sent to the
   * controller if not then it will output an error message.
   *
   * @param args command line arguments.
   */
  public static void main(String[] args) {
    Scanner scan = new Scanner(String.join(" ", args));
    MarbleSolitaireModelAbstract board;
    MarbleSolitaireControllerImpl controller = new MarbleSolitaireControllerImpl(
        new InputStreamReader(System.in), System.out);
    int size = -1;
    int row = -1;
    int col = -1;
    String boardtype = "";

    while (scan.hasNext()) {
      String current = scan.next();

      if (current.equals("-hole")) {
        try {
          row = scan.nextInt();
          col = scan.nextInt();
        } catch (NoSuchElementException e) {
          System.out.println("invalid row col inputs");
        }

      }
      if (current.equals("-size")) {
        try {
          size = scan.nextInt();
        } catch (NoSuchElementException e) {
          System.out.println("invalid size input");
        }

      }

      if (current.equals("english")) {
        boardtype = "english";
      }
      if (current.equals("triangle")) {
        boardtype = "triangle";
      }
      if (current.equals("european")) {
        boardtype = "european";
      }
    }

    if (boardtype.equals("english")) {
      if (!(row == -1 && col == -1)) {
        board = new MarbleSolitaireModelImpl(row, col);
        controller.playGame(board);
      }
      if ((size != -1)) {
        board = new MarbleSolitaireModelImpl(size);
        controller.playGame(board);
      } else {
        board = new MarbleSolitaireModelImpl();
        controller.playGame(board);
      }
    }

    if (boardtype.equals("triangle")) {
      if (!(row == -1 && col == -1)) {
        board = new TriangleSolitaireModelImpl(row, col);
        controller.playGame(board);
      }
      if ((size != -1)) {
        board = new TriangleSolitaireModelImpl(size);
        controller.playGame(board);
      } else {
        board = new TriangleSolitaireModelImpl();
        controller.playGame(board);
      }
    }

    if (boardtype.equals("european")) {
      if (!(row == -1 && col == -1)) {
        board = new EuropeanSolitaireModelImpl(row, col);
        controller.playGame(board);
      }
      if ((size != -1)) {
        board = new EuropeanSolitaireModelImpl(size);
        controller.playGame(board);
      } else {
        board = new EuropeanSolitaireModelImpl();
        controller.playGame(board);
      }
    }


  }
}
