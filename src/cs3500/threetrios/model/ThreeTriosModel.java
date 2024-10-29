package cs3500.threetrios.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class ThreeTriosModel implements Model {
  private Player bluePlayer; // the blue player
  private Player redPlayer; // the red player
  private Player currentPlayer;
  private Grid grid; // the grid for the game
  private boolean started; // determines whether the game has started or not
  private boolean gameEnded; // determines whether the game has ended or not
  private final Random rand; // helps with shuffling of the cards if needed

  /**
   * Constructor for the model, does not take in anything.
   */
  public ThreeTriosModel() {
    this.rand = new Random();
  }

  /**
   * Constructor for the model and takes in a random.
   *
   * @param rand represents the random used for shuffling
   */
  public ThreeTriosModel(Random rand) {
    this.rand = rand;
  }

  @Override
  public void startGame(List<Card> cards, boolean shuffle, Grid grid) {
    if (started) {
      throw new IllegalStateException("Game already started!");
    }
    if (gameEnded) {
      throw new IllegalStateException("Game already ended!");
    }
    if (cards == null) {
      throw new IllegalArgumentException("Cards cannot be null");
    }
    if (allCardsNotUnique(cards)) {
      throw new IllegalArgumentException("Cards must be unique!");
    }
    if (hasEvenCardCells(grid)) {
      throw new IllegalArgumentException("Grid must have an odd number of card cells.");
    }
    if (invalidCardCount(cards, grid)) {
      throw new IllegalArgumentException("Card count must be one more than card cell count.");
    }

    if (shuffle) {
      Collections.shuffle(cards, this.rand);
    }
    this.started = true;
    this.grid = grid;
    this.dealCards(cards);
    this.currentPlayer = redPlayer;
  }

  /**
   * Makes sure that all the cards have a unique identifier.
   *
   * @param cards represents the list of cards that is attempting to be used as the deck
   * @return true if all cards have a unique identifier, and false if not
   */
  private boolean allCardsNotUnique(List<Card> cards) {
    for (int i = 0; i < cards.size() - 1; i++) {
      for (int j = i + 1; j < cards.size(); j++) {
        if (cards.get(i).getName().equals(cards.get(j).getName())) {
          return true;
        }
      }
    }
    return false;
  }

  private boolean hasEvenCardCells(Grid grid) {
    return grid.getCardCellCount() % 2 == 0;
  }

  private boolean invalidCardCount(List<Card> cards, Grid grid) {
    return cards.size() != grid.getCardCellCount() + 1;
  }

  /**
   * Deals the cards to the two players and creates the two players.
   *
   * @param cards represent the deck of cards to be dealt
   */
  private void dealCards(List<Card> cards) {
    List<Card> blueHand = new ArrayList<>();
    List<Card> redHand = new ArrayList<>();
    for (int allCards = 0; allCards < cards.size() - 1; allCards++) {
      if (allCards % 2 == 0) {
        cards.get(allCards).createCardColor(Color.BLUE);
        blueHand.add(cards.get(allCards));
      } else {
        cards.get(allCards).createCardColor(Color.RED);
        redHand.add(cards.get(allCards));
      }
    }

    this.bluePlayer = new HumanPlayer(blueHand, Color.BLUE);
    this.redPlayer = new HumanPlayer(redHand, Color.RED);
  }

  @Override
  public void placingPhase(Card card, int row, int col) {
    currentPlayer.removeFromHand(card);
    grid.placeCard(card, row, col);
  }

  @Override
  public void battlingPhase(int row, int col) {
    Card placedCard = grid.getCard(row, col);
    Map<String, Direction> adjacentCards = grid.getAdjacentCardsWithDirections(row, col);

    for (Map.Entry<String, Direction> card : adjacentCards.entrySet()) {
      String adjCardName = card.getKey();
      // direction of adjacent card relative to placed card
      Direction direction = card.getValue();
      // direction of adjacent card whose value will be used for battle
      Direction oppositeDirection = getOppositeDirection(direction);

      Card adjCard = findCardByName(adjCardName);

      if (adjCard.getColor() != placedCard.getColor()) {
        if (placedCard.getValueFromDirection(direction) > adjCard.getValueFromDirection(oppositeDirection)) {
          adjCard.flipColor();
        }
      }
    }
  }

  private Card findCardByName(String name) {
    Cell[][] cells = grid.getCells();

    for (int row = 0; row < grid.getNumRows(); row++) {
      for (int col = 0; col < grid.getNumCols(); col++) {
        Cell cell = cells[row][col];

        if (!cell.isHole() && cell.hasCard()) {
          Card card = cell.getCard();
          if (card.getName().equals(name)) {
            return card;
          }
        }
      }
    }
    throw new IllegalArgumentException("Card not found in grid: " + name);
  }

  private Direction getOppositeDirection(Direction direction) {
    switch (direction) {
      case NORTH:
        return Direction.SOUTH;
      case SOUTH:
        return Direction.NORTH;
      case EAST:
        return Direction.WEST;
      case WEST:
        return Direction.EAST;
      default:
        throw new IllegalArgumentException("Invalid direction");
    }
  }

  @Override
  public void takeTurn(Card card, int row, int col) {
    if (!started) {
      throw new IllegalStateException("Game has not started yet!");
    }
    if (gameEnded) {
      throw new IllegalStateException("Game has already ended.");
    }
    if (!currentPlayer.getCardsInHand().contains(card)) {
      throw new IllegalArgumentException("Card does not belong to current player.");
    }

    placingPhase(card, row, col);

    battlingPhase(row, col);

    if (!isGameOver()) {
      currentPlayer = (currentPlayer == bluePlayer) ? redPlayer : bluePlayer;
    }
  }

  @Override
  public boolean isGameOver() {
    if (!started) {
      throw new IllegalStateException("Game not started!");
    }

    gameEnded = allCardCellsFilled();
    return gameEnded;
  }

  private boolean allCardCellsFilled() {
    for (Cell[] row : grid.getCells()) {
      for (Cell cell : row) {
        if (!cell.isHole() && !cell.hasCard()) {
          return false;
        }
      }
    }
    return true;
  }

  @Override
  public String winner() {
    if (!this.started) {
      throw new IllegalStateException("Game not started!");
    }
    if (!this.gameEnded) {
      throw new IllegalStateException("Game has not ended!");
    }
    if (this.bluePlayer.getNumberCardsOwned() > this.redPlayer.getNumberCardsOwned()) {
      return "Blue Player";
    }
    else {
      return "Red Player";
    }
  }
}
