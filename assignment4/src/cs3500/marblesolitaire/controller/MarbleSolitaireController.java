package cs3500.marblesolitaire.controller;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;

/**
 * the MarbleSolitaireController interface which takes in user inputs via a readable and displays
 * the appropriate messages to the console via the appendable based on the model throws an illegal
 * argument exception if any of the inputs are null and an illegalStateException when the controller
 * has run out of inputs prematurely.
 */
public interface MarbleSolitaireController {

  /**
   * displays the state of the game and events for the game such as moving getting the score and if
   * the game is over throws an illegal argument exception if any of the parameters are null and an
   * illegal state exception when the method prematurely runs out of inputs.
   *
   * @param model A model of the marble solitaire game which holds all of the functionality and
   *              rules.
   * @throws IllegalArgumentException if the model readable or appendable is null.
   * @throws IllegalStateException    if playgame prematurely runs out of inputs.
   */
  void playGame(MarbleSolitaireModel model) throws IllegalArgumentException, IllegalStateException;

}
