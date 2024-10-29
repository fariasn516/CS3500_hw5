package cs3500.threetrios;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import cs3500.threetrios.model.Card;
import cs3500.threetrios.model.CardFileParser;
import cs3500.threetrios.model.Color;
import cs3500.threetrios.model.Direction;
import cs3500.threetrios.model.Grid;
import cs3500.threetrios.model.GridFileParser;
import cs3500.threetrios.model.Model;
import cs3500.threetrios.model.Player;
import cs3500.threetrios.model.SimpleCard;
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
    this.pigCard = new SimpleCard("pig", Value.FIVE, Value.FOUR, Value.THREE, Value.TWO);
  }

  // testing the model
  // exceptions to be thrown

  // testing the HumanPlayer class
  // exceptions to be thrown

  // testing the GameGrid class
  // exceptions to be thrown

  // testing the SimpleCard class
  // exceptions to be thrown
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
    int compare = this.ratCard.compareTo(this.dogCard, Direction.NORTH);
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

  }

  // testing the CardFileParser class

  // testing the GridFileParser class


}
