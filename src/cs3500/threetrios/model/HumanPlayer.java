package cs3500.threetrios.model;

import java.util.ArrayList;
import java.util.List;

public class HumanPlayer implements Player {
  private final List<Card> cardsInHand;
  private final List<Card> ownedCardsOnGrid;
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
    this.ownedCardsOnGrid = new ArrayList<>();
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
  public void removeFromHand(Card card) {
    if (!cardsInHand.contains(card)) {
      throw new IllegalArgumentException("The card is not in the player's hand.");
    }
    cardsInHand.remove(card);
  }

  @Override
  public List<Card> removeFromOwnership() {
    ArrayList<Card> removedCards = new ArrayList<>();
    for (int cards = 0; cards < this.ownedCardsOnGrid.size(); cards++) {
      if (this.ownedCardsOnGrid.get(cards).getColor() != this.color) {
        removedCards.add(this.ownedCardsOnGrid.remove(cards));
      }
    }
    return removedCards;
  }

  @Override
  public void addToOwnership(List<Card> cards) {
    this.ownedCardsOnGrid.addAll(cards);
  }

  @Override
  public List<Card> getOwnedCardsOnGrid() {
    return new ArrayList<>(this.ownedCardsOnGrid);
  }

  @Override
  public List<Card> getCardsInHand() {
    return new ArrayList<>(this.cardsInHand);
  }

  @Override
  public int getNumberCardsOwned() {
    return this.ownedCardsOnGrid.size() + this.cardsInHand.size();
  }

  @Override
  public Color getColor() {
    return this.color;
  }

  @Override
  public List<Card> getAllOwnedCards() {
    List<Card> allOwnedCards = new ArrayList<>(this.cardsInHand);
    allOwnedCards.addAll(this.ownedCardsOnGrid);
    return allOwnedCards;
  }
}
