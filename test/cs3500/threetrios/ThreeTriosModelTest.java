package cs3500.threetrios;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cs3500.threetrios.model.Card;
import cs3500.threetrios.model.CardFileParser;
import cs3500.threetrios.model.Color;
import cs3500.threetrios.model.Direction;
import cs3500.threetrios.model.GameGrid;
import cs3500.threetrios.model.Grid;
import cs3500.threetrios.model.GridFileParser;
import cs3500.threetrios.model.HumanPlayer;
import cs3500.threetrios.model.Model;
import cs3500.threetrios.model.Player;
import cs3500.threetrios.model.SimpleCard;
import cs3500.threetrios.model.ThreeTriosModel;
import cs3500.threetrios.model.Value;

public class ThreeTriosModelTest {
  // models
  Model hasSeededRandom;
  Model noSeededRandom;

  // some players
  Player redHumanPlayer;
  Player blueHumanPlayer;

  // some grids
  Grid gridWithNoHoles;
  Grid gridWithHoles;

  // some cards
  Card ratCard;
  Card oxCard;
  Card tigerCard;
  Card rabbitCard;
  Card dragonCard;
  Card snakeCard;
  Card horseCard;
  Card goatCard;
  Card monkeyCard;
  Card roosterCard;
  Card dogCard;
  Card pigCard;

  // CardFileParsers
  CardFileParser fileParser;

  // GridFileParsers
  GridFileParser gridFileParser;

  @Before
  public void init() {
    // initializing cards
    this.ratCard = new SimpleCard("rat", Value.ACE, Value.ONE, Value.TWO, Value.THREE);
    this.oxCard = new SimpleCard("ox", Value.ACE, Value.ONE, Value.TWO, Value.THREE);
    this.tigerCard = new SimpleCard("tiger", Value.TWO, Value.ONE, Value.FIVE, Value.SIX);
    this.rabbitCard = new SimpleCard("rabbit", Value.FOUR, Value.NINE, Value.ACE, Value.THREE);
    this.dragonCard = new SimpleCard("dragon", Value.ACE, Value.ACE, Value.TWO, Value.ONE);
    // purposely making snake card's values equal to the rat cards
    this.snakeCard = new SimpleCard("snake", Value.ACE, Value.ONE, Value.TWO, Value.THREE);
    this.horseCard = new SimpleCard("horse", Value.TWO, Value.EIGHT, Value.TWO, Value.THREE);
    this.goatCard = new SimpleCard("goat", Value.ACE, Value.SIX, Value.FOUR, Value.SEVEN);
    this.monkeyCard = new SimpleCard("monkey", Value.ACE, Value.ACE, Value.ACE, Value.ACE);
    this.roosterCard = new SimpleCard("rooster", Value.ONE, Value.ONE, Value.ONE, Value.ONE);
    this.dogCard = new SimpleCard("dog", Value.NINE, Value.EIGHT, Value.SEVEN, Value.SIX);
    this.pigCard = new SimpleCard("pig", Value.ACE, Value.FOUR, Value.THREE, Value.TWO);
  }

  // testing the model
  // exceptions to be thrown
  // when random is null
  @Test (expected = IllegalArgumentException.class)
  public void shouldThrowIllegalArgumentRandomNull() {
    Model model = new ThreeTriosModel(null);
  }

  // test the startGame method

  // test the placingPhase method

  // test the battlingPhase method

  // test the findCardByName method

  // test the getOppositeDirection method

  // test the takeTurn method

  // test the isGameOver method

  // test the winner method

  // test the getGrid method

  // test the getCurrentPlayer method



  // testing the HumanPlayer class
  // exceptions to be thrown
  // when cards in hand is null
  @Test (expected = IllegalArgumentException.class)
  public void shouldThrowIllegalArgumentHandNull(){
    Player newPlayer = new HumanPlayer(null, Color.RED);
  }

  // when color is null
  @Test (expected = IllegalArgumentException.class)
  public void shouldThrowIllegalArgumentColorNull(){
    this.monkeyCard.createCardColor(Color.RED);
    this.dragonCard.createCardColor(Color.RED);
    List<Card> cards = List.of(this.monkeyCard, this.dragonCard);

    Player newPlayer = new HumanPlayer(cards, null);
  }

  // when not all cards adhere to the player's color
  @Test (expected = IllegalArgumentException.class)
  public void shouldThrowIllegalArgumentHandNotCorrectColor(){
    this.monkeyCard.createCardColor(Color.RED);
    this.dragonCard.createCardColor(Color.BLUE);
    List<Card> cards = List.of(this.monkeyCard, this.dragonCard);

    Player newPlayer = new HumanPlayer(cards, Color.RED);
  }

  // test the removeFromHand method
  // when the card does not exist in the hand
  // when the card exists in the hand

  // test the removeFromOwnership method
  // when there is nothing to remove
  // when there is something to remove

  // test the addToOwnerShip method

  // test the getOwnedCardsOnBoard method

  // test the getCardsInHand method

  // test the getNumberOwnedCards method

  // test the getColor method

  // test the getAllOwnedCards method

  // test the toString method



  // testing the GameGrid class
  // exceptions to be thrown


  // testing the GameCell class
  // exceptions to be thrown from constructor



  // testing the SimpleCard class
  // exceptions to be thrown from constructor
  // when the map of directions and values is null
  @Test (expected = IllegalArgumentException.class)
  public void shouldThrowIllegalArgumentMapNull() {
    Card simpleCard = new SimpleCard("simple", null);
  }

  // when the name is null
  @Test (expected = IllegalArgumentException.class)
  public void shouldThrowIllegalArgumentNameNull() {
    Card simpleCard = new SimpleCard(null, Value.ACE, Value.ONE, Value.TWO, Value.THREE);
  }

  // when not all directions are accounted
  @Test (expected = IllegalArgumentException.class)
  public void shouldThrowIllegalArgumentNotAllDirections() {
    Map<Direction, Value> values = new HashMap<>();
    values.put(Direction.NORTH, Value.FOUR);
    Card simpleCard = new SimpleCard("simple", values);
  }

  // test the compareTo method
  // when this card's value is greater than that card's value with the given direction
  @Test
  public void shouldReturnOne() {
    int compare = this.pigCard.compareTo(this.dogCard, Direction.NORTH);
    Assert.assertEquals(1, compare);
  }

  // when this card's value is equal to that card's value with the given direction
  @Test
  public void shouldReturnZero() {
    int compare = this.ratCard.compareTo(this.monkeyCard, Direction.NORTH);
    Assert.assertEquals(0, compare);
  }

  // when this card's value is less than that card's value with the given direction
  @Test
  public void shouldReturnNegativeOne() {
    int compare = this.ratCard.compareTo(this.monkeyCard, Direction.SOUTH);
    Assert.assertEquals(-1, compare);
  }

  // test the createCardColor method
  // creating a blue card
  @Test
  public void shouldCreateBlueCard() {
    this.ratCard.createCardColor(Color.BLUE);
    Assert.assertEquals(Color.BLUE, this.ratCard.getColor());
  }

  // creating a red card
  @Test
  public void shouldCreateRedCard() {
    this.ratCard.createCardColor(Color.RED);
    Assert.assertEquals(Color.RED, this.ratCard.getColor());
  }

  // test the flipColor method
  // flipping from red to blue
  @Test
  public void shouldFlipColorToBlue() {
    this.ratCard.createCardColor(Color.RED);
    this.ratCard.flipColor();
    Assert.assertEquals(Color.BLUE, this.ratCard.getColor());
  }

  // flipping from blue to red
  @Test
  public void shouldFlipColorToRed() {
    this.ratCard.createCardColor(Color.BLUE);
    this.ratCard.flipColor();
    Assert.assertEquals(Color.RED, this.ratCard.getColor());
  }

  // test the getValueFromDirection method
  // makes sure that illegalArgumentException is thrown when direction is null
  @Test (expected = IllegalArgumentException.class)
  public void shouldThrowIllegalDirectionNull() {
    this.oxCard.getValueFromDirection(null);
  }

  // gets the value from North direction
  @Test
  public void shouldGetDirectionNorth() {
    int value = this.ratCard.getValueFromDirection(Direction.NORTH);
    Assert.assertEquals(10, value);
  }

  // gets the value from South direction
  @Test
  public void shouldGetDirectionSouth() {
    int value = this.tigerCard.getValueFromDirection(Direction.SOUTH);
    Assert.assertEquals(1, value);
  }

  // gets the value from North direction
  @Test
  public void shouldGetDirectionEast() {
    int value = this.rabbitCard.getValueFromDirection(Direction.EAST);
    Assert.assertEquals(10, value);
  }

  // gets the value from North direction
  @Test
  public void shouldGetDirectionWest() {
    int value = this.dragonCard.getValueFromDirection(Direction.WEST);
    Assert.assertEquals(1, value);
  }

  // test the getColor method
  // throws an exception when the color has not been initialized
  @Test (expected = IllegalStateException.class)
  public void shouldThrowIllegalColorNotInitialized() {
    this.snakeCard.getColor();
  }

  // gets the color
  @Test
  public void shouldGetColor() {
    this.dogCard.createCardColor(Color.BLUE);
    Assert.assertEquals(Color.BLUE, this.dogCard.getColor());
  }

  // test the getName method
  // makes sure that changing the returned name doesn't mutate anything
  @Test
  public void shouldNotMutateGetName() {
    String name = this.ratCard.getName();
    name += "bleh";

    Assert.assertEquals("rat", this.ratCard.getName());
  }

  // gets the name
  @Test
  public void shouldReturnGetName() {
    String name = this.ratCard.getName();
    Assert.assertEquals("rat", this.ratCard.getName());
  }

  // test the copy method
  // makes sure that the returned card is the same as the original
  @Test
  public void shouldReturnCopyOfCard() {
    Card copiedCard = this.snakeCard.copy();
    boolean isEqual = this.snakeCard.equals(copiedCard);
    Assert.assertTrue(isEqual);
  }

  // makes sure that changing something in the copied card doesn't change anything in the original
  @Test
  public void shouldNotMutateOriginalCopyOfCard() {
    Card copiedCard = this.snakeCard.copy();
    copiedCard.createCardColor(Color.BLUE);
    boolean isEqual = this.snakeCard.equals(copiedCard);
    Assert.assertFalse(isEqual);
  }

  // test the toString method
  // prints out a card
  @Test
  public void printOutCard() {
    String result = this.horseCard.toString();
    Assert.assertEquals("horse 2 8 2 3 ", result);
  }

  // print out another card
  @Test
  public void printOutCard2() {
    String result = this.roosterCard.toString();
    Assert.assertEquals("rooster 1 1 1 1 ", result);
  }

  // print out a card with an A
  @Test
  public void printOutCardWithA() {
    String result = this.goatCard.toString();
    Assert.assertEquals("goat A 6 4 7 ", result);
  }



  // testing the CardFileParser class
  // test the createDeck method
  @Test
  public void createLessDeck() throws FileNotFoundException {
    String path = ".idea/CardConfiguration/LessCards";
    CardFileParser parser = new CardFileParser(path);
    List<Card> result = parser.createDeck();

    Card sleepyCat =
            new SimpleCard("SleepyCat", Value.ONE, Value.TWO, Value.THREE, Value.FOUR);
    Card creepyCat =
            new SimpleCard("CreepyCat", Value.FIVE, Value.SIX, Value.SEVEN, Value.EIGHT);
    Card happyCat =
            new SimpleCard("HappyCat", Value.NINE, Value.ACE, Value.ONE, Value.TWO);
    Card angryCat =
            new SimpleCard("AngryCat", Value.THREE, Value.FOUR, Value.FIVE, Value.SIX);
    Card weepyCat =
            new SimpleCard("WeepyCat", Value.SEVEN, Value.EIGHT, Value.NINE, Value.ACE);
    Card crazyCat =
            new SimpleCard("CrazyCat", Value.SIX, Value.SIX, Value.SEVEN, Value.ONE);
    List<Card> expected = List.of(sleepyCat, creepyCat, happyCat, angryCat, weepyCat, crazyCat);

    Assert.assertEquals(expected, result);
  }



  // testing the GridFileParser class

  // testing createGridFromFile from Disconnected file
  @Test
  public void testDisconnectedFile() throws FileNotFoundException {
    File file = new File(".idea/GridConfiguration/Disconnected");
    GridFileParser parser = new GridFileParser(file);
    GameGrid result = parser.createGridFromFile();

    int expectedNumRows = 3;
    int expectedNumCols = 4;
    boolean[][] expectedHoleLayout = {
            {true, false, false, true},
            {true, true, false, false},
            {true, false, true, true},
    };

    Assert.assertEquals(expectedNumRows, result.getNumRows());
    Assert.assertEquals(expectedNumCols, result.getNumCols());
    Assert.assertArrayEquals(expectedHoleLayout, result.getHoleLayout());
  }

  // testing createGridFromFile from HasHoles file
  @Test
  public void testHasHolesFile() throws FileNotFoundException {
    File file = new File(".idea/GridConfiguration/HasHoles");
    GridFileParser parser = new GridFileParser(file);
    GameGrid result = parser.createGridFromFile();

    int expectedNumRows = 3;
    int expectedNumCols = 4;
    boolean[][] expectedHoleLayout = {
            {false, false, false, false},
            {true, false, false, false},
            {false, false, true, true}
    };

    Assert.assertEquals(expectedNumRows, result.getNumRows());
    Assert.assertEquals(expectedNumCols, result.getNumCols());
    Assert.assertArrayEquals(expectedHoleLayout, result.getHoleLayout());
  }

  // testing createGridFromFile from NoHoles file
  @Test
  public void testNoHolesFile() throws FileNotFoundException {
    File file = new File(".idea/GridConfiguration/NoHoles");
    GridFileParser parser = new GridFileParser(file);
    GameGrid result = parser.createGridFromFile();

    int expectedNumRows = 3;
    int expectedNumCols = 3;
    boolean[][] expectedHoleLayout = {
            {false, false, false},
            {false, false, false},
            {false, false, false}
    };

    Assert.assertEquals(expectedNumRows, result.getNumRows());
    Assert.assertEquals(expectedNumCols, result.getNumCols());
    Assert.assertArrayEquals(expectedHoleLayout, result.getHoleLayout());
  }
}
