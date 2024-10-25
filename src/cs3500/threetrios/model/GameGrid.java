package cs3500.threetrios.model;

import java.util.ArrayList;
import java.util.List;

public class GameGrid implements Grid {
  private final int numRows;
  private final int numCols;
  private final Cell[][] grid;

 public GameGrid(GridFileParser parser) {
   this.numRows = parser.getNumRows();
   this.numCols = parser.getNumCols();
   this.grid = new Cell[numRows][numCols];

   boolean[][] holeLayout = parser.getHoleLayout();
   int cardCellCount = 0;

   for (int i = 0; i < numRows; i++) {
     for (int j = 0; j < numCols; j++) {
       boolean isHole = holeLayout[i][j];
       grid[i][j] = new GameCell(isHole);

       if (!isHole) {
         cardCellCount++;
       }
     }
   }

   // make sure there is an odd number of card cells on grid
   if (cardCellCount % 2 == 0) {
     throw new IllegalArgumentException("The grid must have an odd number of card cells.");
   }
 }

  @Override
  public void placeCard(Card card, int row, int col) {
    if (row < 0 || row >= numRows || col < 0 || col >= numCols) {
      throw new IllegalArgumentException("Row or column out of bounds.");
    }

    if (!validCell(row, col)) {
      throw new IllegalStateException("Cell is not valid for placing a card.");
    }

    grid[row][col].placeCard(card);
  }

  @Override
  public boolean validCell(int row, int col) {
    if (row < 0 || row >= numRows || col < 0 || col >= numCols) {
      throw new IllegalArgumentException("Row or column out of bounds.");
    }

    return !grid[row][col].isHole() && !grid[row][col].hasCard();
  }

  @Override
  public List<Card> getAdjacentCards(int row, int col) {
    if (row < 0 || row >= numRows || col < 0 || col >= numCols) {
      throw new IllegalArgumentException("Row or column out of bounds.");
    }

    if (grid[row][col].isHole()) {
      throw new IllegalArgumentException("Cannot retrieve adjacent cards from a hole.");
    }

    if (!grid[row][col].hasCard()) {
      throw new IllegalArgumentException("No card in the specified cell to find adjacent cards.");
    }

    List<Card> adjacentCards = new ArrayList<>();

    if (row > 0 && grid[row - 1][col].hasCard()) {
      adjacentCards.add(grid[row - 1][col].getCard());
    }
    if (row < numRows - 1 && grid[row + 1][col].hasCard()) {
      adjacentCards.add(grid[row + 1][col].getCard());
    }
    if (col > 0 && grid[row][col - 1].hasCard()) {
      adjacentCards.add(grid[row][col - 1].getCard());
    }
    if (col < numCols - 1 && grid[row][col + 1].hasCard()) {
      adjacentCards.add(grid[row][col + 1].getCard());
    }

    return adjacentCards;
  }

  @Override
  public Cell[][] getCells() {
    Cell[][] gridCopy = new Cell[numRows][numCols];

    for (int i = 0; i < numRows; i++) {
      for (int j = 0; j < numCols; j++) {
        gridCopy[i][j] = grid[i][j];
      }
    }

    return gridCopy;
  }

  @Override
  public Card getCard(int row, int col) {
    if (row < 0 || row >= numRows || col < 0 || col >= numCols) {
      throw new IllegalArgumentException("Row or column out of bounds.");
    }

    if (grid[row][col].isHole()) {
      throw new IllegalArgumentException("Cannot retrieve a card from a hole.");
    }

    if (!grid[row][col].hasCard()) {
      throw new IllegalArgumentException("No card in this cell.");
    }

    return grid[row][col].getCard();
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < numRows; i++) {
      for (int j = 0; j < numCols; j++) {
        builder.append(grid[i][j].toString()).append(" ");
      }
      builder.append("\n");
    }
    return builder.toString();
  }
}

