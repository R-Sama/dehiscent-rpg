package core;

import items.Item;
import items.SaleItem;
import npcs.Merchant;

public class TransactionResolver {

  public static void ResolveTransaction(Player p, Merchant m) {
    String decision = "";
    while (!decision.equals("leave")) {
      decision = IO.getDecision("What would you like to do? View wares, buy, sell or leave?");
      if (decision.startsWith("view")) {
        IO.println(m.inventoryToString());
      } else if (decision.startsWith("inspect") || decision.startsWith("i ")) {
        String itemName = decision.substring(decision.indexOf(" ")).trim();
        if (!m.attemptToInspect(itemName)) {
          p.attemptToInspect(itemName);
        }
      } else if (decision.startsWith("buy") || decision.startsWith("b ")) {
        String itemName = decision.substring(decision.indexOf(" ")).trim();
        buy(p, m, itemName);
      } else if (decision.startsWith("sell") || decision.startsWith("s ")) {
        String itemName = decision.substring(decision.indexOf(" ")).trim();
        sell(p, m, itemName);
      }
    }
  }

  public static void buy(Player p, Merchant m, String itemName) {
    SaleItem itemToBuy = m.findSaleItem(itemName);
    buy(p, m, itemName, "Want to buy a " + itemName + " for " + itemToBuy.getSalePrice() + "? ");
  }

  public static void sell(Player p, Merchant m, String itemName) {
    SaleItem itemToBuy = m.findSaleItem(itemName);
    sell(p, m, itemName, "Want to buy a " + itemName + " for " + itemToBuy.getSalePrice() + "? ");
  }

  public static void buy(Player p, Merchant m, String itemName, String confirmationMessage) {
    SaleItem itemToBuy = m.findSaleItem(itemName);
    String d = IO.getDecision(confirmationMessage);
    if (d.startsWith("y")) {
      if (itemToBuy != null && p.getGold() >= itemToBuy.getSalePrice()) {
        p.obtain(itemToBuy.getItemForSale());
        m.lose(itemToBuy);
        p.subGold(itemToBuy.getSalePrice());
        m.addGold(itemToBuy.getSalePrice());
      }
    }
  }

  public static void sell(Player p, Merchant m, String itemName, String confirmationMessage) {
    Item itemToSell = p.findItem(itemName);
    int adjustedPrice = m.getAdjustedBuyingPrice(itemToSell);
    String d = IO.getDecision(confirmationMessage);
    if (d.startsWith("y")) {
      if (itemToSell != null && m.getGold() >= itemToSell.getValue()) {
        p.lose(itemToSell);
        m.obtain(itemToSell);
        p.addGold(adjustedPrice);
        m.subGold(adjustedPrice);
      }
    }
  }

}
