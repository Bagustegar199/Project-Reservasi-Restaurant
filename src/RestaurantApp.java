import java.util.ArrayList;
import java.util.Scanner;

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
        System.out.println("Detail Reservasi:");
        System.out.println("Nama Pelanggan: " + customerName);
        System.out.println("Jumlah Tamu: " + numberOfGuests);
        System.out.println("Waktu Reservasi: " + reservationTime);
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
            System.out.println(menuItem.itemName + " - Rp." + menuItem.itemPrice);
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
        System.out.println(order.quantity + " " + order.menuItem.itemName + "(s) ditambaahkan ke keranjang.");
    }

    public double calculateTotalOrder() {
        double total = 0;
        for (Order order : orders) {
            total += order.calculateTotal();
        }
        return total;
    }

    public void displayReceipt() {
        System.out.println("Struk:");
        for (Order order : orders) {
            System.out.println(order.quantity + " " + order.menuItem.itemName + "(s) - Rp." + order.calculateTotal());
        }
        System.out.println("Total: Rp." + calculateTotalOrder());
    }
}

class ReservationSystem {
    ArrayList<Reservation> reservations;

    public ReservationSystem() {
        reservations = new ArrayList<>();
    }

    public void makeReservation(Reservation reservation) {
        reservations.add(reservation);
        System.out.println("Reservasi berhasil!");
    }

    public void displayAllReservations() {
        System.out.println("Semua Reservasi:");
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
        menu.menuItems.add(new MenuItem("Spaghetti", 70000));
        menu.menuItems.add(new MenuItem("Chicken", 35000));
        menu.menuItems.add(new MenuItem("Pizza", 90000));

        ShoppingCart cart = new ShoppingCart();
        ReservationSystem reservationSystem = new ReservationSystem();

        while (true) {
            System.out.println("\n1. Tampilkan Menu");
            System.out.println("2. Buat Reservasi");
            System.out.println("3. Tampilkan Reservasi");
            System.out.println("4. Tambahlan ke Keranjang");
            System.out.println("5. Tampilkan Struk");
            System.out.println("6. Keluar");

            System.out.print("Pilih Menu: ");
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    menu.displayMenu();
                    break;
                case 2:
                    System.out.print("Masukkan nama anda: ");
                    scanner.nextLine(); // Consume the newline character
                    String customerName = scanner.nextLine();
                    System.out.print("Masukkan jumlah tamu: ");
                    int numberOfGuests = scanner.nextInt();
                    System.out.print("Masukkan waktu reservasi: ");
                    scanner.nextLine(); // Consume the newline character
                    String reservationTime = scanner.nextLine();

                    Reservation reservation = new Reservation(customerName, numberOfGuests, reservationTime);
                    reservationSystem.makeReservation(reservation);
                    break;
                case 3:
                    reservationSystem.displayAllReservations();
                    break;
                case 4:
                    System.out.print("Masukkan nama item untuk ditambahkan ke keranjang: ");
                    scanner.nextLine(); // Consume the newline character
                    String itemName = scanner.nextLine();
                    MenuItem selectedMenuItem = menu.findMenuItem(itemName);

                    if (selectedMenuItem != null) {
                        System.out.print("Masukkan jumlah: ");
                        int quantity = scanner.nextInt();
                        Order order = new Order(selectedMenuItem, quantity);
                        cart.addToCart(order);
                    } else {
                        System.out.println("Item tidak ditemukan dalam menu.");
                    }
                    break;
                case 5:
                    cart.displayReceipt();
                    break;
                case 6:
                    System.out.println("Terima kasih telah menggunakan aplikasi restoran. selamat tinggal!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opsi tidak valid. Silahkan pilih opsi yang valid.");
            }
        }
    }
}
