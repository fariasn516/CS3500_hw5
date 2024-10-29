package cs3500.threetrios.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class CardFileParser {
  private final File file;

  public CardFileParser(File file) {
    this.file = file;
  }

  public CardFileParser(String fileName) {
    this.file = new File(fileName);
  }

  public List<Card> createDeck() throws FileNotFoundException {
    List<Card> deck = new ArrayList<>();
    Scanner scanner = new Scanner(this.file);
    while (scanner.hasNextLine()) {
      String cardName = scanner.next();
      Map<Direction, Value> values = new HashMap<Direction, Value>();
      for (Direction direction : Direction.values()) {
        if (scanner.hasNext()) {
          String cardValue = scanner.next();
          Value value = findValue(cardValue);
          values.put(direction, value);
        }
      }
      deck.add(new SimpleCard(cardName, values));
    }
    scanner.close();
    return deck;
  }

  private Value findValue(String cardValue) {
    switch (cardValue) {
      case "1":
        return Value.ONE;
      case "2":
        return Value.TWO;
      case "3":
        return Value.THREE;
      case "4":
        return Value.FOUR;
      case "5":
        return Value.FIVE;
      case "6":
        return Value.SIX;
      case "7":
        return Value.SEVEN;
      case "8":
        return Value.EIGHT;
      case "9":
        return Value.NINE;
      case "A":
        return Value.ACE;
      default:
        throw new IllegalArgumentException("Invalid card value: " + cardValue);
    }
  }
}
