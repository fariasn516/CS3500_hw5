package cs3500.threetrios.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ThreeTriosModel implements Model {
  private Player bluePlayer; // the blue player
  private Player redPlayer; // the red player
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
   * @param rand represents the random used for shuffling
   */
  public ThreeTriosModel(Random rand) {
    this.rand = rand;
  }

  @Override
  public void startGame(List<Card> cards, boolean shuffle, Grid grid) {
    if (this.started) {
      throw new IllegalStateException("Game already started!");
    }
    if (this.gameEnded) {
      throw new IllegalStateException("Game already ended!");
    }
    if (cards == null) {
      throw new IllegalArgumentException("Cards cannot be empty or null");
    }


    /////////////
    // NEED A WAY TO CHECK THAT THE NUMBER OF EMPTY CELLS IS ODD
    // NEED A WAY TO CHECK THAT THE NUMBER OF CARDS IS EMPTY CELLS + 1
    ////////////



    if (!allCardsUnique(cards)) {
      throw new IllegalArgumentException("You need to have the cards unique!");
    }
    if (shuffle) {
      Collections.shuffle(cards, this.rand);
    }
    this.started = true;
    this.grid = grid;
    this.dealCards(cards);
  }

  /**
   * Makes sure that all the cards have a unique identifier.
   * @param cards represents the list of cards that is attempting to be used as the deck
   * @return true if all cards have a unique identifier, and false if not
   */
  private boolean allCardsUnique(List<Card> cards) {
    for (int i = 0; i < cards.size() - 1; i++) {
      for (int j = i + 1; j < cards.size(); j++) {
        if (cards.get(i).getName().equals(cards.get(j).getName())) {
          return false;
        }
      }
    }
    return true;
  }

  /**
   * Deals the cards to the two players and creates the two players.
   * @param cards represent the deck of cards to be dealt
   */
  private void dealCards(List<Card> cards) {
    List<Card> blueHand = new ArrayList<>();
    List<Card> redHand = new ArrayList<>();
    for (int allCards = 0; allCards < cards.size() - 1; allCards++) {
      if (allCards % 2 == 0) {
        cards.get(allCards).createCardColor(Color.BLUE);
        blueHand.add(cards.get(allCards));
      }
      else {
        cards.get(allCards).createCardColor(Color.RED);
        redHand.add(cards.get(allCards));
      }
    }

    this.bluePlayer = new HumanPlayer(blueHand, Color.BLUE);
    this.redPlayer = new HumanPlayer(redHand, Color.RED);
  }

  @Override
  public String winner() {
    if (!this.started) {
      throw new IllegalStateException("Game not started!");
    }
    if (!this.gameEnded) {
      throw new IllegalStateException("Game ended!");
    }
    if (this.bluePlayer.getNumberCardsOwned() > this.redPlayer.getNumberCardsOwned()) {
      return "Blue Player";
    }
    return "Red Player";
  }

}
