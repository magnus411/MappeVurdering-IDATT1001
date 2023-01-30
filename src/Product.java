package src;

/**
 * This class represents a product.
 * 
 * @author kandidat 10009
 *
 */
public class Product {

  private String id;
  private int price;
  private String description;
  private String brandName;
  private double weight;
  private double length;
  private double height;
  private String color;
  private int quantity;

  Category category;

  /**
   * Constructor for creating a product.
   * 
   * @Param id The id of the product.
   *        The id has to consist of both strings and integers, otherwise throw an
   *        exeption.
   *        The id cannot be null or empty, otherwise throw an exception.
   * 
   * @Param price The price of the product.
   * @Param brandName The brand name of the product.
   * @Param weight The weight of the product.
   * @Param length The length of the product.
   * @Param height The height of the product.
   * @Param color The color of the product.
   * @Param quantity The quantity of the product.
   *        The quantity cannot be negative, otherwise throw an exception.
   * 
   * @category The category of the product.
   *           The category has to be between 1 and 4, otherwise throw an
   *           exception
   * 
   */
  public Product(
      String id,
      int price,
      String description,
      String brandName,
      double weight,
      double length,
      double height,
      String color,
      int quantity,
      int category) {

    // The id cannot be null or empty, otherwise throw an exception
    if (id == null || id.isEmpty()) {
      throw new IllegalArgumentException("ID cannot be null or empty");
    }

    // The id has to contain BOTH strings and intigers for it to be valid, otherwise
    // throw an exception
    if (!id.matches(".*[a-zA-Z]+.*") || !id.matches(".*[0-9]+.*")) {
      throw new IllegalArgumentException("ID has to contain both strings and intigers");
    }

    // The category has to be an int between 1 and 5, otherwise throw an exception
    if (category < 1 || category > 4) {
      throw new IllegalArgumentException("Category has to be an int between 1 and 5");
    }

    // The quantity cannot be negative, otherwise throw an exception
    if (quantity < 0) {
      throw new IllegalArgumentException("Quantity cannot be negative");
    }

    // The price cannot be negative, otherwise throw an exception
    if (price < 0) {
      throw new IllegalArgumentException("Price cannot be negative");
    }

    this.id = id;
    this.price = price;
    this.description = description;
    this.brandName = brandName;
    this.weight = weight;
    this.length = length;
    this.height = height;
    this.color = color;
    this.quantity = quantity;
    this.category = Category.findByValue(category);
  }

  /**
   * Constructor for Product.
   *
   * @Param product The product that is being copied.
   */
  public Product(Product product) {
    this(product.getID(),
        product.getPrice(),
        product.getDescription(),
        product.getBrandName(),
        product.getWeight(),
        product.getLength(),
        product.getHeight(),
        product.getColor(),
        product.getQuantity(),
        product.getCategory().getValue());

  }

  public String getID() {
    return id;
  }

  public int getPrice() {
    return price;
  }

  public String getDescription() {
    return description;
  }

  public String getBrandName() {
    return brandName;
  }

  public double getWeight() {
    return weight;
  }

  public double getLength() {
    return length;
  }

  public double getHeight() {
    return height;
  }

  public String getColor() {
    return color;
  }

  public int getQuantity() {
    return quantity;
  }

  public Category getCategory() {
    return category;
  }

  /**
   * This function sets the quantity of a product.
   */
  public void setQuantity(int quantity) {
    if (quantity < 0) {
      throw new IllegalArgumentException("The quantity cannot be negative");
    }
    this.quantity = quantity;

  }

  /**
   * This function sets the price of the product. If the price is less then 0,
   * throw an IllegalArgumentException.
   * 
   * @param price The price of the item.
   */
  public void setPrice(int price) {
    if (price < 0) {
      throw new IllegalArgumentException("Price cannot be negative");
    }
    this.price = price;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public String toString() {
    return "ID: " + id + "\n"
        + "Price: " + price + "\n"
        + "Description: " + description + "\n"
        + "Brand Name: " + brandName + "\n"
        + "Weight: " + weight + "\n"
        + "Length: " + length + "\n"
        + "Height: " + height + "\n"
        + "Color: " + color + "\n"
        + "Quantity: " + quantity + "\n"
        + "Category: " + category + "\n";

  }

}
