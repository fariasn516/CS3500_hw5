package cs3500.threetrios.model;

import java.util.ArrayList;
import java.util.List;

public class HumanPlayer implements Player{
  private final List<SimpleCard> cardsInHand;
  private List<SimpleCard> ownedCards;
  private final Color color;

  public HumanPlayer(List<SimpleCard> cardsInHand, Color color) {
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

  private boolean checkCorrectColor(List<SimpleCard> cards) {
    for (SimpleCard card : cards) {
      if (card.getColor() != this.color) {
        return false;
      }
    }
    return true;
  }

  public void removeFromHand(int index) {
    this.cardsInHand.remove(index);
  }

  public List<SimpleCard> removeFromOwnerShip() {
    ArrayList<SimpleCard> removedCards = new ArrayList<>();
    for (int cards = 0; cards < this.cardsInHand.size(); cards++) {
      if (this.ownedCards.get(cards).getColor() != this.color) {
        removedCards.add(this.ownedCards.remove(cards));
      }
    }
    return removedCards;
  }
}
