package cs3500.threetrios.model;

import java.util.ArrayList;
import java.util.List;

public class HumanPlayer implements Player{
  private final List<Card> cardsInHand;
  private final List<Card> ownedCards;
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
    this.ownedCards = new ArrayList<>();
  }

  /**
   * Makes sure that all the cards given are the correct color.
   * @param cards represents the cards in hand
   * @return whether the cards are all the correct color
   */
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
    for (int cards = 0; cards < this.ownedCards.size(); cards++) {
      if (this.ownedCards.get(cards).getColor() != this.color) {
        removedCards.add(this.ownedCards.remove(cards));
      }
    }
    return removedCards;
  }

  @Override
  public void addToOwnership(List<Card> cards) {
    this.ownedCards.addAll(cards);
  }

  @Override
  public List<Card> cardsInHand() {
    return new ArrayList<>(this.cardsInHand);
  }

  @Override
  public int getNumberCardsOwned() {
    return this.ownedCards.size() + this.cardsInHand.size();
  }
}
