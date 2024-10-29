package cs3500.threetrios.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class CardFileParser {
  private final File file;

  public CardFileParser(File file) throws FileNotFoundException {
    this.file = file;
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
          switch (cardValue) {
            case "1":
              values.put(direction, Value.ONE);
            break;
            case "2":
              values.put(direction, Value.TWO);
            break;
            case "3":
              values.put(direction, Value.THREE);
            break;
            case "4":
              values.put(direction, Value.FOUR);
            break;
            case "5":
              values.put(direction, Value.FIVE);
            break;
            case "6":
              values.put(direction, Value.SIX);
            break;
            case "7":
              values.put(direction, Value.SEVEN);
            break;
            case "8":
              values.put(direction, Value.EIGHT);
            break;
            case "9":
              values.put(direction, Value.NINE);
            break;
            case "A":
              values.put(direction, Value.ACE);
            break;
            default:
              throw new IllegalArgumentException("Invalid card value: " + cardValue);
          }
        }
      }
      deck.add(new SimpleCard(cardName, values));
    }
    scanner.close();
    return deck;
  }
}
