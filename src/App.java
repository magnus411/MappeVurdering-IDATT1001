package src;

import java.util.NoSuchElementException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * A class that contains the main method and is responsible for the user
 * interface.
 * 
 * @author Kandidat 10009
 *
 */
public class App {

  private static final ProductRegister register = new ProductRegister();
  private static final Scanner inputStr = new Scanner(System.in);
  private static final Scanner inputInt = new Scanner(System.in);

  /**
   * The main method.
   * 
   * @param args
   */
  public static void main(String[] args) {
    choices();
  }

  /**
   * This method is responsible for the user interface. Here the user can choose
   * what they want to do.
   */
  private static void choices() {
    boolean running = true;
    while (running) {
      System.out.println("--------------------------------");
      System.out.println("Welcome to the product register!");
      System.out.println("--------------------------------");
      System.out.println("Please choose an option:");
      System.out.println("1. Show all products");
      System.out.println("2. Create a new product");
      System.out.println("3. Remove a product");
      System.out.println("4. Search for a product");
      System.out.println("5. Search for products using keywords");
      System.out.println("6. Change the price of a product");
      System.out.println("7. Change the description of a product");
      System.out.println("8. Add quantity of a product");
      System.out.println("9. Remove quantity of a product");
      System.out.println("10. Exit");
      System.out.println("--------------------------------");
      System.out.println("Please enter your choice:");

      try {
        int choice = inputInt.nextInt();
        switch (choice) {
          case 1 ->
            printAll();
          case 2 -> createNewProduct();
          case 3 -> removeProduct();
          case 4 -> searchForProduct();
          case 5 -> searchByKeywords();
          case 6 -> changePrice();
          case 7 -> changeDescription();
          case 8 -> increaseProductInventory();
          case 9 -> decreaseProductInventory();
          case 10 -> System.exit(0);
          default -> {
            System.out.println("Please enter a valid choice");

          }
        }
      } catch (InputMismatchException e) {
        System.out.println("Please enter a valid choice");
        inputInt.nextLine();
      }
    }

  }

  /**
   * This method the allows user to serach for a product using a ID.
   */
  private static void searchForProduct() {
    System.out.println("--------------------------------");
    System.out.println("Search for a product");
    System.out.println("--------------------------------");
    System.out.println("Please enter the product's ID:");
    String name = inputStr.nextLine();

    try {
      System.out.println(register.getProductDeepCopyByID(name));
    } catch (NoSuchElementException e) {
      System.out.println(e.getMessage());
      searchForProduct();
    }
  }

  /**
   * This method the allows user to serach for a product using a set of keywords,
   * separated by a comma.
   */
  private static void searchByKeywords() {
    System.out.println("--------------------------------");
    System.out.println("Search for a product");
    System.out.println("--------------------------------");
    System.out.println("Please enter the some keywords separated by a comma:");

    try {
      String keywords = inputStr.nextLine();
      formatList(register.searchByKeywords(keywords));

    } catch (NoSuchElementException e) {
      System.out.println(e.getMessage());
      searchByKeywords();
    }

  }

  /**
   * This method allows the user to change the price of a product using a ID and
   * the new price.
   */
  private static void changePrice() {
    System.out.println("--------------------------------");
    System.out.println("Change the price of a product");
    System.out.println("--------------------------------");
    System.out.println("Please enter the product's ID:");

    try {
      String name = inputStr.nextLine();
      System.out.println("Please enter the new price:");
      int price = inputInt.nextInt();
      register.changePrice(name, price);
      System.out.println("The price was changed to" + price);

    } catch (InputMismatchException e) {
      System.out.println("Please enter a valid price");
      changePrice();

    } catch (NoSuchElementException e) {
      System.out.println(e.getMessage());

    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
      changePrice();
    }

  }

  /**
   * This method allows the user to change the description of a product using a ID
   * and the new description.
   */
  private static void changeDescription() {
    System.out.println("--------------------------------");
    System.out.println("Change the description of a product");
    System.out.println("--------------------------------");
    System.out.println("Please enter the product's ID:");

    try {
      String name = inputStr.nextLine();
      System.out.println("Please enter the new description:");
      String description = inputStr.nextLine();

      register.changeDescription(name, description);
      System.out.println("The description was changed");

    } catch (NoSuchElementException e) {
      System.out.println(e.getMessage());
    }

  }

  /**
   * This method allows the user to add quantity of a product using a ID and the
   * quantity to add.
   */
  private static void increaseProductInventory() {
    System.out.println("--------------------------------");
    System.out.println("Add quantity of a product");
    System.out.println("--------------------------------");
    System.out.println("Please enter the product's ID:");

    try {
      String name = inputStr.nextLine();
      System.out.println("Please enter the quantity to add:");
      int quantity = inputStr.nextInt();
      register.increaseProductInventory(name, quantity);

      System.out.println("The quantity was added");
    } catch (NoSuchElementException e) {
      System.out.println(e.getMessage());
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
      increaseProductInventory();
    }
  }

  /**
   * This method allows the user to remove quantity of a product using a ID and
   * the quantity to remove.
   */
  private static void decreaseProductInventory() {
    System.out.println("--------------------------------");
    System.out.println("Remove quantity of a product");
    System.out.println("--------------------------------");
    System.out.println("Please enter the product's ID:");

    try {
      String name = inputStr.nextLine();
      System.out.println("Please enter the quantity to remove:");
      int quantity = inputStr.nextInt();
      register.decreaseProductInventory(name, quantity);

      System.out.println("The quantity was removed");
      System.out.println("--------------------------------");

    } catch (NoSuchElementException e) {
      System.out.println(e.getMessage());

    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
      decreaseProductInventory();
    }
  }

  /**
   * Formats a list of strings to be printed in a more readable way.
   * 
   */
  private static void formatList(List<String> list) {
    for (String item : list) {
      System.out.println("--------------------------------");
      System.out.println(item);
    }
  }

  /**
   * This method prints all the products in the register.
   */
  private static void printAll() {

    System.out.println("--------------------------------");
    System.out.println("All products:");
    try {
      List<String> products = register.printAllProducts();
      formatList(products);

    } catch (NoSuchElementException e) {
      System.out.println(e.getMessage());
    }

  }

  /**
   * This method allows the user to remove a product using a ID.
   */
  private static void removeProduct() {
    System.out.println("--------------------------------");
    System.out.println("Remove a product");
    System.out.println("--------------------------------");
    System.out.println("Please enter the product's ID:");

    try {
      String name = inputStr.nextLine();
      register.removeProduct(name);

      System.out.println("The product was removed");
      System.out.println("--------------------------------");

    } catch (NoSuchElementException e) {
      System.out.println(e.getMessage());
    }

  }

  /**
   * This method allows the user to create a new product.
   */
  private static void createNewProduct() {
    System.out.println("--------------------------------");
    System.out.println("Create a new product");
    System.out.println("--------------------------------");
    try {

      // ID
      System.out.println("Please enter the product's ID:");
      String id = inputStr.nextLine();

      // Price
      System.out.println("Please enter the product's price:");
      int price = inputInt.nextInt();

      // Description
      System.out.println("Please enter the product's description:");
      String description = inputStr.nextLine();

      // Brand name
      System.out.println("Please enter the product's brand name:");
      String brandName = inputStr.nextLine();

      // Weight
      System.out.println("Please enter the product's weight:");
      double weight = inputInt.nextDouble();

      // Length
      System.out.println("Please enter the product's length:");
      double length = inputInt.nextDouble();

      // Height
      System.out.println("Please enter the product's height:");
      double height = inputInt.nextDouble();

      // Color
      System.out.println("Please enter the product's color:");
      String color = inputStr.nextLine();

      // Quantity
      System.out.println("Please enter the product's quantity:");
      int quantity = inputInt.nextInt();

      // Category
      System.out.println("Please enter the product's category:");
      System.out.println("1 = Electronics, 2 = Clothing, 3 = Furniture, 4 = Food, 5 = Other");
      int category = inputInt.nextInt();

      register.createProduct(id, price, description, brandName, weight, length, height, color, quantity, category);

      System.out.println("--------------------------------");
      System.out.println("Product created!");
      System.out.println("--------------------------------");

    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
      createNewProduct();

    } catch (InputMismatchException e) {
      System.out.println("Please enter a valid input");
      createNewProduct();
    }

  }

}
