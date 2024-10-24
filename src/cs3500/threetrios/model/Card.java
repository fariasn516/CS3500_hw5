package cs3500.threetrios.model;

public interface Card {


  /**
   * Flips the color of this card in order to change its ownership.
   */
  void flipColor();

  // Below are OBSERVATIONS
  /**
   * Returns the Value at the given Direction
   * @param direction represents the direction that you want the value from
   * @return the Value at the given Direction
   */
  int getValueFromDirection(Direction direction);

  /**
   *
   * @return
   */
  Color getColor();
}
