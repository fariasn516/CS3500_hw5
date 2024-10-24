package cs3500.threetrios.model;

public interface Player {
  /**
   * Removes the card from hand of the player's cards given the index
   * @param index represents the index of the card that is going to be removed
   */
  void removeFromHand(int index);
}
