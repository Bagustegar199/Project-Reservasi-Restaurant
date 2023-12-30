import java.util.ArrayList;
import java.util.Scanner;
//apayaaaaaa
class MenuItem {
    String itemName;
    double itemPrice;

    public MenuItem(String itemName, double itemPrice) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
    }
}

class Reservation {
    String customerName;
    int numberOfGuests;
    String reservationTime;

    public Reservation(String customerName, int numberOfGuests, String reservationTime) {
        this.customerName = customerName;
        this.numberOfGuests = numberOfGuests;
        this.reservationTime = reservationTime;
    }

    public void displayReservationDetails() {
        System.out.println("Reservation Details:");
        System.out.println("Customer Name: " + customerName);
        System.out.println("Number of Guests: " + numberOfGuests);
        System.out.println("Reservation Time: " + reservationTime);
    }
}

class RestaurantMenu {
    ArrayList<MenuItem> menuItems;

    public RestaurantMenu() {
        menuItems = new ArrayList<>();
    }

    public void displayMenu() {
        System.out.println("Menu:");
        for (MenuItem menuItem : menuItems) {
            System.out.println(menuItem.itemName + " - $" + menuItem.itemPrice);
        }
    }

    public MenuItem findMenuItem(String itemName) {
        for (MenuItem menuItem : menuItems) {
            if (menuItem.itemName.equalsIgnoreCase(itemName)) {
                return menuItem;
            }
        }
        return null;
    }
}

class Order {
    MenuItem menuItem;
    int quantity;

    public Order(MenuItem menuItem, int quantity) {
        this.menuItem = menuItem;
        this.quantity = quantity;
    }

    public double calculateTotal() {
        return menuItem.itemPrice * quantity;
    }
}

class ShoppingCart {
    ArrayList<Order> orders;

    public ShoppingCart() {
        orders = new ArrayList<>();
    }

    public void addToCart(Order order) {
        orders.add(order);
        System.out.println(order.quantity + " " + order.menuItem.itemName + "(s) added to the cart.");
    }

    public double calculateTotalOrder() {
        double total = 0;
        for (Order order : orders) {
            total += order.calculateTotal();
        }
        return total;
    }

    public void displayReceipt() {
        System.out.println("Receipt:");
        for (Order order : orders) {
            System.out.println(order.quantity + " " + order.menuItem.itemName + "(s) - $" + order.calculateTotal());
        }
        System.out.println("Total: $" + calculateTotalOrder());
    }
}

class ReservationSystem {
    ArrayList<Reservation> reservations;

    public ReservationSystem() {
        reservations = new ArrayList<>();
    }

    public void makeReservation(Reservation reservation) {
        reservations.add(reservation);
        System.out.println("Reservation successful!");
    }

    public void displayAllReservations() {
        System.out.println("All Reservations:");
        for (Reservation reservation : reservations) {
            reservation.displayReservationDetails();
            System.out.println("--------------");
        }
    }
}

public class RestaurantApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        RestaurantMenu menu = new RestaurantMenu();
        menu.menuItems.add(new MenuItem("Spaghetti Bolognese", 12.99));
        menu.menuItems.add(new MenuItem("Chicken Alfredo", 15.99));
        menu.menuItems.add(new MenuItem("Margherita Pizza", 10.99));

        ShoppingCart cart = new ShoppingCart();
        ReservationSystem reservationSystem = new ReservationSystem();

        while (true) {
            System.out.println("\n1. Display Menu");
            System.out.println("2. Make Reservation");
            System.out.println("3. Display Reservations");
            System.out.println("4. Add to Cart");
            System.out.println("5. Display Receipt");
            System.out.println("6. Exit");

            System.out.print("Choose an option: ");
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    menu.displayMenu();
                    break;
                case 2:
                    System.out.print("Enter your name: ");
                    scanner.nextLine(); // Consume the newline character
                    String customerName = scanner.nextLine();
                    System.out.print("Enter number of guests: ");
                    int numberOfGuests = scanner.nextInt();
                    System.out.print("Enter reservation time: ");
                    scanner.nextLine(); // Consume the newline character
                    String reservationTime = scanner.nextLine();

                    Reservation reservation = new Reservation(customerName, numberOfGuests, reservationTime);
                    reservationSystem.makeReservation(reservation);
                    break;
                case 3:
                    reservationSystem.displayAllReservations();
                    break;
                case 4:
                    System.out.print("Enter item name to add to cart: ");
                    scanner.nextLine(); // Consume the newline character
                    String itemName = scanner.nextLine();
                    MenuItem selectedMenuItem = menu.findMenuItem(itemName);

                    if (selectedMenuItem != null) {
                        System.out.print("Enter quantity: ");
                        int quantity = scanner.nextInt();
                        Order order = new Order(selectedMenuItem, quantity);
                        cart.addToCart(order);
                    } else {
                        System.out.println("Item not found in the menu.");
                    }
                    break;
                case 5:
                    cart.displayReceipt();
                    break;
                case 6:
                    System.out.println("Thank you for using the Restaurant App. Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Please choose a valid option.");
            }
        }
    }
}
