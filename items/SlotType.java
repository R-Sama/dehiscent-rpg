package items;

import java.util.List;

public enum SlotType {
  HEAD("Head"), CHEST("Chest"), LEGS("Legs"), ARMS("Arms"), FEET("Feet"), HAND("Hand"), ACCESSORY("Accessory");

  private String value;

  SlotType(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  @Override
  public String toString() {
    return this.getValue();
  }

  public boolean isUnique(List<EquipSlot> slots) {
    int count = 0;
    for (EquipSlot s : slots) {
      if (s.slotType == this) count++;
      if (count > 1) return false;
    }
    return count != 0;
  }

  public boolean isAvailable(List<EquipSlot> slots) {
    for (EquipSlot s : slots) {
      if (s.slotType == this && s.item == null) return true;
    }
    return false;
  }
}
