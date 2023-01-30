package src;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * A class that represents a product register. This class is responsible for
 * handeling all the different products, and have methods to fetch, add, change
 * and remove products for the register.
 * 
 * @author Kanditat 10009
 *
 */
public class ProductRegister {

  // Declaring a private final list of products.
  private final List<Product> productRegister;

  ProductRegister() {
    this.productRegister = new ArrayList<Product>();
    populateProductRegister(5);
  }

  /**
   * Return a list of all products in the register, where each product is
   * represented as a string.
   * 
   * @return A list of strings.
   */
  public List<String> printAllProducts() {
    // I chose not to deepcopy since im only fetiching and retring data

    return productRegister
        .stream()
        .map(Product::toString)
        .collect(Collectors.toCollection(ArrayList::new));
  }

  /**
   * This function creates a new product and adds it to the product register.
   * 
   * @param id          The product's id.
   * @param price       The price of the product
   * @param description A string that describes the product.
   * @param brandName   The brand name of the product.
   * @param weight      the weight of the product in kg
   * @param length      The length of the product
   * @param height      The height of the product in cm.
   * @param color       String
   * @param quantity    The amount of the product in stock
   * @param category    1 = Electronics, 2 = Clothing, 3 = Furniture, 4 = Food, 5
   *                    = Other
   */
  public void createProduct(
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

    // The ID has to be unice for the product to be created
    if (productRegister
        .stream()
        .anyMatch(p -> p.getID()
            .equals(id))) {
      throw new IllegalArgumentException("ID already exists");
    }

    Product product = new Product(
        id,
        price,
        description,
        brandName,
        weight,
        length,
        height,
        color,
        quantity,
        category);

    productRegister.add(product);

  }

  /**
   * Create a new Product object and pass the old Product object to the
   * constructor.
   * 
   * @param product The product to be copied.
   * @return A new Product object with the same values as the original Product
   *         object.
   */
  public Product deepCopy(Product product) {
    return new Product(product);
  }

  /**
   * It takes a string as input, and returns a product that matches the ID.
   * 
   * @param id The id of the product you want to search for.
   * @return A deep copy of the product.
   */
  public Product getProductDeepCopyByID(String id) {
    return deepCopy(searchById(id));
  }

  /**
   * It takes a string of keywords, splits them into a list, then filters the
   * product register for
   * products that contain any of the keywords in their description, and returns a
   * deep copy of the
   * filtered list.
   * 
   * @param keywords a comma-separated list of keywords
   * @return A list of products that match the keywords
   */
  public List<String> searchByKeywords(String keywords) {

    List<String> keywordList = Arrays.asList(keywords.split("\\s*,\\s*"));

    List<String> data = productRegister
        .stream()
        .filter(
            product -> keywordList.stream()
                .filter(e -> product.getDescription().toLowerCase()
                    .contains(e.toLowerCase()))
                .findAny()
                .isPresent())
        .map(e -> deepCopy(e))
        .map(e -> e.toString())
        .collect(Collectors.toCollection(ArrayList::new));

    if (data.size() > 0) {
      return data;
    } else {
      throw new NoSuchElementException("No products matches keywords " + keywords);
    }

  }

  /**
   * This function takes a product id and a quantity, and increases the quantity
   * of
   * the product with the given id by the given quantity.
   * 
   * @param id       The id of the product to be updated.
   * @param quantity The amount to increase the quantity by.
   */
  public void increaseProductInventory(String id, int quantity) {
    Product product = searchById(id);
    product.setQuantity(product.getQuantity() + quantity);
  }

  /**
   * Decrease the quantity of a product by a given amount.
   * 
   * @param id       The id of the product to be updated.
   * @param quantity the number of products to be added to the inventory
   */
  public void decreaseProductInventory(String id, int quantity) {
    Product product = searchById(id);
    product.setQuantity(product.getQuantity() - quantity);
  }

  /**
   * Remove the product with the given id from the product register.
   * 
   * @param id The id of the product to be removed.
   */
  public void removeProduct(String id) {
    productRegister.remove(searchById(id));

  }

  /**
   * This function changes the price of a product.
   * 
   * @param id    The id of the product to change the price of.
   * @param price The new price of the product.
   */
  public void changePrice(String id, int price) {
    Product product = searchById(id);
    product.setPrice(price);
  }

  /**
   * This function changes the description of a product.
   * 
   * @param id          The id of the product to change.
   * @param description The new description of the product.
   */
  public void changeDescription(String id, String description) {
    Product product = searchById(id);
    product.setDescription(description);
  }

  /**
   * A method that populates the product register with a given amount of products.
   * 
   * @param amount The amount of products to be added to the register.
   */
  private void populateProductRegister(int amount) {
    String[] RandomDescriptions = { "Green", "Brown", "Yellow", "White", "Window", "Door", "Lumber", "Floor Laminate",
        "New", "Cool", "Amazing" };

    Random ran = new Random();

    for (int i = 0; i < amount; i++) {
      createProduct(
          "ID" + i,
          (int) (Math.random() * 10000),
          RandomDescriptions[ran.nextInt(RandomDescriptions.length)] + ","
              + RandomDescriptions[ran.nextInt(RandomDescriptions.length)],
          "Brand" + i,
          Math.random() * 100,
          Math.random() * 100,
          Math.random() * 100,
          "Color" + i,
          (int) (Math.random() * 100),
          (int) (Math.random() * 4) + 1);
    }
  }

  /**
   * It takes a string as input, and returns a product that matches the ID.
   * 
   * @param id The id of the product you want to search for.
   * @return A the product.
   */
  private Product searchById(String id) {
    Optional<Product> data = productRegister
        .stream()
        .filter(product -> product.getID().equalsIgnoreCase(id))
        .findFirst();

    if (data.isPresent()) {
      return data.get();
    } else {
      throw new NoSuchElementException("No product with id " + id + " exists");
    }

  }

}
