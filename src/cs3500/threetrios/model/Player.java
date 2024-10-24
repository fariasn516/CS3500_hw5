package cs3500.threetrios.model;

import java.util.List;

public interface Player {
  /**
   * Removes the card from hand of the player's cards given the index
   * @param index represents the index of the card that is going to be removed
   */
  void removeFromHand(int index);

  /**
   * Removes the cards of the incorrect color from ownership and returns the list of cards that
   * were removed.
   * @return the list of cards that were removed
   */
  List<Card> removeFromOwnerShip();

  /**
   * Adds the given list of cards to ownership.
   * @param cards represents the list of cards that are going to be added to ownership
   */
  void addToOwnership(List<Card> cards);

  // Below are OBSERVATIONS
  /**
   * Returns the cards that are in the player's hand. NON-MUTABLE!!!
   * @return a new list of cards that represent the player's hand
   */
  List<Card> cardsInHand();

  /**
   * Returns the number of cards that is owned by the player.
   * @return the number of cards owned by the player
   */
  int getNumberCardsOwned();
}
