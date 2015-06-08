package map;

import map.cells.Cell;
import map.cells.BlankCell;

public class Quadrant {

  private int size;
  private Cell[][] cells;

  public Quadrant(int size) {
    this.size = size;
    cells = new BlankCell[16][16];
    for (int y = 0; y < size; y++) {
      for (int x = 0; x < size; x++) {
        cells[y][x] = new BlankCell();
      }
    }
  }

  public Cell getCell(int x, int y) {
    return cells[y][x];
  }

  public void printCell(int x, int y) {
    System.out.print(cells[y][x].mapIcon);
  }
}
