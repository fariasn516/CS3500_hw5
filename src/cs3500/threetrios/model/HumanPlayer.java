package cs3500.threetrios.model;

import java.util.List;

public class HumanPlayer implements Player{
  private final List<Card> cardsInHand;
  private List<Card> ownedCards;
  private final Color color;

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
  }

  private boolean checkCorrectColor(List<Card> cards) {
    for (Card card : cards) {
      if (card.getColor() != this.color) {
        return false;
      }
    }
    return true;
  }

  public void removeFromHand(int index) {
    this.cardsInHand.remove(index);
  }

  public List<Card> removeFromOwnerShip() {

  }
}
