import map.*;
import map.cells.*;
import core.Player;
import java.util.Scanner;

public class Dehiscent {

  public static void main(String[] args) {
    Map overworld = createMap();   
    Player p = new Player();
    Scanner in = new Scanner(System.in);

    for (;;) {
      overworld.printKnownMap(p);

      Cell currentCell = overworld.fetchCell(p.getPosition());
      p = currentCell.event(p);

      Cell previousCell = currentCell;
      while(currentCell == previousCell) {
        System.out.println("What will you do?");
        String decision = in.nextLine().toLowerCase();
        if (decision.equals("w")) {
          p.goNorth();
        } else if (decision.equals("a")) {
          p.goWest();
        } else if (decision.equals("s")) {
          p.goSouth();
        } else if (decision.equals("d")) {
          p.goEast();
        } else if (decision.startsWith("view")) {
          if (decision.contains("map")) {
            overworld.printKnownMap(p);
          }
          if (decision.contains("position")) {
            System.out.println("You're at " + p.getPosition().toString());
          }
        } else if (decision.startsWith("explore")) { 
          p = currentCell.explore(p);
        } else if (decision.startsWith("quit")) {
          System.out.println("Saving isn't implemented yet! Are you sure you want to quit? ('yes' to quit)");
          if (in.nextLine().toLowerCase().startsWith("yes")) {
            System.exit(0);
          }
        }
        currentCell = overworld.fetchCell(p.getPosition());
      }
    }
  }

  public static Map createMap() {
    Map overworld = new Map();
    overworld.setCell(0, 0, new OneTest());
    overworld.setCell(-1, 0, new TwoTest());
    overworld.setCell(-1, -1, new ThreeTest());
    overworld.setCell(0, -1, new FourTest());
    return overworld;
  }
}
