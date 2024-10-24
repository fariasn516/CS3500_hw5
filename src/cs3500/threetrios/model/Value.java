package cs3500.threetrios.model;

public enum Value {
  ONE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), ACE(10);
  private final int value;

  Value(int value) {
    this.value = value;
  }

  /**
   * Observer that returns the integer value of this Value.
   * @return the integer value of this Value.
   */
  public int getValue() {
    return value;
  }
}
