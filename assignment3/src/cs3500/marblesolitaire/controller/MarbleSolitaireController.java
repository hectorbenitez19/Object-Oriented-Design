package cs3500.marblesolitaire.controller;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;

/**
 * Interface for the controller of the marbleSolitaire game.
 */
public interface MarbleSolitaireController {

  void playGame(MarbleSolitaireModel model) throws IllegalArgumentException, IllegalStateException;

}
