package cs3500.threetrios.model;

import java.util.HashMap;
import java.util.Map;

public class GameGrid implements Grid {
  private final int numRows;
  private final int numCols;
  private final boolean[][] holeLayout;
  private final Cell[][] grid;

  public GameGrid(int numRows, int numCols, boolean[][] holeLayout) {
    this.numRows = numRows;
    this.numCols = numCols;
    this.holeLayout = holeLayout;
    this.grid = new Cell[numRows][numCols];

    for (int i = 0; i < numRows; i++) {
      for (int j = 0; j < numCols; j++) {
        boolean isHole = holeLayout[i][j];
        grid[i][j] = new GameCell(isHole);
      }
    }
  }

  public GameGrid(int numRows, int numCols, Cell[][] grid) {
    this.numRows = numRows;
    this.numCols = numCols;
    this.grid = grid;
    this.holeLayout = new boolean[numRows][numCols];
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
  public Map<String, Direction> getAdjacentCardsWithDirections(int row, int col) {
    Map<String, Direction> adjacentCardsWithDirections = new HashMap<>();

    if (row > 0 && grid[row - 1][col].hasCard()) {
      adjacentCardsWithDirections.put(grid[row - 1][col].getCard().getName(), Direction.NORTH);
    }

    if (row < numRows - 1 && grid[row + 1][col].hasCard()) {
      adjacentCardsWithDirections.put(grid[row + 1][col].getCard().getName(), Direction.SOUTH);
    }

    if (col > 0 && grid[row][col - 1].hasCard()) {
      adjacentCardsWithDirections.put(grid[row][col - 1].getCard().getName(), Direction.WEST);
    }

    if (col < numCols - 1 && grid[row][col + 1].hasCard()) {
      adjacentCardsWithDirections.put(grid[row][col + 1].getCard().getName(), Direction.EAST);
    }

    return adjacentCardsWithDirections;
  }

  public int getCardCellCount() {
    int cardCellCount = 0;
    for (int row = 0; row < numRows; row++) {
      for (int col = 0; col < numCols; col++) {
        if (!grid[row][col].isHole()) {
          cardCellCount++;
        }
      }
    }
    return cardCellCount;
  }

  @Override
  public int getNumRows() {
   return this.numRows;
  }

  @Override
  public int getNumCols() {
    return this.numCols;
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
    StringBuilder stringRep = new StringBuilder();
    for (int row = 0; row < numRows; row++) {
      for (int col = 0; col < numCols; col++) {
        Cell cell = grid[row][col];
        if (cell.isHole()) {
          stringRep.append(" ");
        }
        else if (cell.hasCard()) {
          Card card = cell.getCard();
          stringRep.append(card.getColor() == Color.RED ? "R" : "B");
        }
        else {
          stringRep.append("_");
        }
      }
      stringRep.append("\n");
    }
    return stringRep.toString();
  }
}

