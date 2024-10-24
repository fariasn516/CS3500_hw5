package cs3500.threetrios.model;

import java.util.Map;

public class SimpleCard implements Card {
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

  @Override
  public int compareTo(Card other, Direction direction) {
    if (other == null) {
      throw new IllegalArgumentException("Cannot compare null card");
    }
    if (direction == null) {
      throw new IllegalArgumentException("Cannot compare null direction");
    }

    if (direction.equals(Direction.NORTH)) {
      return compareValues(this.getValueFromDirection(direction),
              other.getValueFromDirection(Direction.SOUTH));
    }

    else if (direction.equals(Direction.EAST)) {
      return compareValues(this.getValueFromDirection(direction),
              other.getValueFromDirection(Direction.WEST));
    }

    else if (direction.equals(Direction.SOUTH)) {
      return compareValues(this.getValueFromDirection(direction),
              other.getValueFromDirection(Direction.NORTH));
    }

    return compareValues(this.getValueFromDirection(direction),
            other.getValueFromDirection(Direction.EAST));
  }

  // compares two integer values
  private int compareValues(int value1, int value2) {
    return Integer.compare(value1, value2);
  }

  @Override
  public void flipColor() {
    if (this.color == Color.RED) {
      this.color = Color.BLUE;
    }
    else {
      this.color = Color.RED;
    }
  }

  @Override
  public int getValueFromDirection(Direction direction) {
    if (direction == null) {
      throw new IllegalArgumentException("Direction cannot be null");
    }
    return cardValues.get(direction).getValue();
  }

  @Override
  public Color getColor() {
    return color;
  }

  @Override
  public Card copy() {
    return new SimpleCard(this.color, this.cardValues);
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
