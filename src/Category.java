package src;

/**
 * This enum class represents all the categories items of Item.
 * 
 * @author Kandidat 10009
 */
public enum Category {
  FLOOR_LAMINATE(1),
  WINDOWS(2),
  DOORS(3),
  LUMBER(4);

  private int value;

  private Category(int value) {
    this.value = value;
  }

  public int getValue() {
    return value;
  }

  public static Category findByValue(int value) {
    for (Category category : Category.values()) {
      if (category.getValue() == value) {
        return category;
      }
    }
    throw new IllegalArgumentException("Category not found");
  }
}
