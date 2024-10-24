package cs3500.threetrios.model;

import java.util.ArrayList;
import java.util.List;

public class HumanPlayer implements Player{
  private final List<Card> cardsInHand;
  private List<Card> ownedCards;
  private final Color color;

  /**
   * Constructor for the HumanPlayer class.
   * @param cardsInHand represents the list of playable cards that are in the player's hand
   * @param color represents the color of this player
   */
  public HumanPlayer(List<Card> cardsInHand, Color color) {
    if (cardsInHand == null) {
      throw new IllegalArgumentException("Cards cannot be null");
    }
    if (color == null) {
      throw new IllegalArgumentException("Color cannot be null");
    }
    this.color = color;
    if (!checkCorrectColor(cardsInHand)) {
      throw new IllegalArgumentException("Some cards are not the correct color");
    }
    this.cardsInHand = cardsInHand;
    this.ownedCards.addAll(this.cardsInHand);
  }

  // makes sure that all the cards in the hand is the correct color
  private boolean checkCorrectColor(List<Card> cards) {
    for (Card card : cards) {
      if (card.getColor() != this.color) {
        return false;
      }
    }
    return true;
  }

  @Override
  public void removeFromHand(int index) {
    this.cardsInHand.remove(index);
  }

  @Override
  public List<Card> removeFromOwnerShip() {
    ArrayList<Card> removedCards = new ArrayList<>();
    for (int cards = 0; cards < this.cardsInHand.size(); cards++) {
      if (this.ownedCards.get(cards).getColor() != this.color) {
        removedCards.add(this.ownedCards.remove(cards));
      }
    }
    return removedCards;
  }

  public void addToOwnership(List<Card> cards) {
    this.ownedCards.addAll(cards);
  }

  public List<Card> cardsInHand() {
    return new ArrayList<>(this.cardsInHand);
  }

  public int getNumberCardsOwned() {
    return this.ownedCards.size();
  }
}
