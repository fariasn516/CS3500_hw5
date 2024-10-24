package cs3500.threetrios.model;

import java.util.Map;

public class SimpleCard implements Card{
  private Color color;
  private final Map<Direction, Value> cardValues;

  /**
   * Constructor for SimpleCard.
   * @param color represents the Color that this card is
   * @param cardValues represents the Value that this card has at each Direction
   */
  public SimpleCard(Color color, Map<Direction, Value> cardValues) {
    if (color == null) {
      throw new IllegalArgumentException("Color cannot be null");
    }
    if (cardValues == null) {
      throw new IllegalArgumentException("Values cannot be null");
    }
    if (!validValues(cardValues)) {
      throw new IllegalArgumentException("All directions must be accounted for");
    }
    this.color = color;
    this.cardValues = cardValues;
  }

  // checks if every Direction has a corresponding Value.
  private boolean validValues(Map<Direction, Value> values) {
    for (Direction direction : Direction.values()) {
      if (!values.containsKey(direction)) {
        return false;
      }
    }
    return true;
  }


  public void flipColor() {
    if (this.color == Color.RED) {
      this.color = Color.BLUE;
    }
    this.color = Color.RED;
  }

  @Override
  public int getValueFromDirection(Direction direction) {
    return cardValues.get(direction).getValue();
  }

  @Override
  public Color getColor() {
    return color;
  }

  @Override
  public int hashCode() {
    return this.color.hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof SimpleCard)) {
      return false;
    }
    for (Direction direction : Direction.values()) {
      if (!(getValueFromDirection(direction)
              == ((SimpleCard) obj).getValueFromDirection(direction))) {
        return false;
      }
    }
    return true;
  }

  @Override
  public String toString() {
    StringBuilder stringRep = new StringBuilder();
    for (Direction direction : Direction.values()) {
      stringRep.append(direction.toString()).append(": ")
              .append(getValueFromDirection(direction)).append(" ");
    }
    return stringRep.toString();
  }
}
