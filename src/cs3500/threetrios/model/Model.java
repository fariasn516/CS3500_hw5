package cs3500.threetrios.model;

import java.util.List;

public interface Model {

  /**
   * Initializes the game with the given deck of cards, shuffle option, and grid configuration.
   * Deals the cards to players and sets the starting player.
   *
   * @param cards   the list of cards for the game.
   * @param shuffle true if the cards should be shuffled.
   * @param grid    the grid to use for the game.
   * @throws IllegalStateException    if the game has already started or ended.
   * @throws IllegalArgumentException if the cards list is null, grid has an even number of card
   *                                  cells, or the number of cards is incorrect.
   */
  public void startGame(List<Card> cards, boolean shuffle, Grid grid);

  /**
   * Plays the placing phase of the turn by placing a card on the specified grid cell.
   *
   * @param card the card to place on the grid.
   * @param row  the row on the grid where the card is placed.
   * @param col  the column on the grid where the card is placed.
   * @throws IllegalStateException    if the game has not started or has already ended.
   * @throws IllegalArgumentException if the cell is not a valid placement cell.
   */
  public void placingPhase(Card card, int row, int col);

  /**
   * Plays the battling phase of the turn after a card has been placed. Determines who wins the
   * battle between the placed card and its adjacent cards based on their direction based
   * attack values.
   *
   * @param row the row of the placed card.
   * @param col the column of the placed card.
   */
  public void battlingPhase(int row, int col);

  /**
   * Plays a complete turn for the current player. This includes placing a card on the grid,
   * battling with adjacent cards, and switching turns.
   *
   * @param card the card to place on the grid.
   * @param row  the row where the card is placed.
   * @param col  the column where the card is placed.
   * @throws IllegalStateException    if the game has not started or has ended.
   * @throws IllegalArgumentException if the given card is not in the player's hand.
   */
  public void takeTurn(Card card, int row, int col);

  /**
   * Checks whether the game is over by finding if all card cells are filled. Also, updates
   * the gameEnded field.
   *
   * @return true if the game is over
   * @throws IllegalStateException if the game has not started.
   */
  public boolean isGameOver();

  /**
   * Returns the winner of the game based on the number of cards each player owns on the grid.
   *
   * @return a string representing the winner: "Blue Player" or "Red Player".
   * @throws IllegalStateException if the game has not started or is not yet over.
   */
  public String winner();

  // Below are OBSERVATIONS

  /**
   *
   * @return the current grid state
   */
  public Grid getGrid();

  /**
   *
   * @return the current player
   */
  public Player getCurrentPlayer();

}
