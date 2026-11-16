
import java.util.ArrayList;
import java.util.Scanner;

public class HotelReservationSystem {

    // Room class
    static class Room {
        int roomNumber;
        String category;
        int price;
        boolean available;

        Room(int roomNumber, String category, int price) {
            this.roomNumber = roomNumber;
            this.category = category;
            this.price = price;
            this.available = true;
        }
    }

    // Reservation class
    static class Reservation {
        int bookingId;
        String customerName;
        Room room;

        Reservation(int bookingId, String customerName, Room room) {
            this.bookingId = bookingId;
            this.customerName = customerName;
            this.room = room;
        }
    }

    // Main method
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        ArrayList<Room> rooms = new ArrayList<>();
        ArrayList<Reservation> bookings = new ArrayList<>();

        // Add hotel rooms
        rooms.add(new Room(101, "Standard", 1000));
        rooms.add(new Room(102, "Deluxe", 2000));
        rooms.add(new Room(103, "Suite", 3000));

        int bookingId = 1;
        int choice;

        do {
            System.out.println("\n================================");
            System.out.println("    HOTEL RESERVATION SYSTEM");
            System.out.println("================================");
            System.out.println("1. View Available Rooms");
            System.out.println("2. Book a Room");
            System.out.println("3. Cancel Booking");
            System.out.println("4. View Bookings");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            while (!sc.hasNextInt()) {
                System.out.print("Enter a valid number: ");
                sc.next();
            }

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                // View rooms
                case 1:
                    System.out.println("\n--- AVAILABLE ROOMS ---");

                    for (Room r : rooms) {
                        System.out.println(
                            "Room: " + r.roomNumber +
                            " | Category: " + r.category +
                            " | Price: Rs." + r.price +
                            " | Status: " +
                            (r.available ? "Available" : "Booked")
                        );
                    }
                    break;

                // Book room
                case 2:
                    System.out.print("Enter customer name: ");
                    String name = sc.nextLine();

                    if (name.trim().isEmpty()) {
                        System.out.println("Name cannot be empty!");
                        break;
                    }

                    System.out.print("Enter room number: ");

                    while (!sc.hasNextInt()) {
                        System.out.print("Enter a valid room number: ");
                        sc.next();
                    }

                    int number = sc.nextInt();

                    Room selectedRoom = null;

                    for (Room r : rooms) {
                        if (r.roomNumber == number && r.available) {
                            selectedRoom = r;
                            break;
                        }
                    }

                    if (selectedRoom != null) {

                        System.out.println(
                            "Room Price: Rs." + selectedRoom.price
                        );

                        System.out.print(
                            "Confirm payment? (1 = Yes, 2 = No): "
                        );

                        while (!sc.hasNextInt()) {
                            System.out.print("Enter 1 or 2: ");
                            sc.next();
                        }

                        int payment = sc.nextInt();

                        if (payment == 1) {

                            selectedRoom.available = false;

                            Reservation booking =
                                new Reservation(
                                    bookingId,
                                    name,
                                    selectedRoom
                                );

                            bookings.add(booking);

                            System.out.println("\nPayment successful!");
                            System.out.println("Booking successful!");
                            System.out.println(
                                "Your Booking ID: " + bookingId
                            );

                            bookingId++;

                        } else {
                            System.out.println("Booking cancelled.");
                        }

                    } else {
                        System.out.println(
                            "Room not available or invalid room number!"
                        );
                    }
                    break;

                // Cancel booking
                case 3:
                    System.out.print("Enter booking ID: ");

                    while (!sc.hasNextInt()) {
                        System.out.print("Enter a valid booking ID: ");
                        sc.next();
                    }

                    int id = sc.nextInt();

                    boolean found = false;

                    for (int i = 0; i < bookings.size(); i++) {

                        Reservation b = bookings.get(i);

                        if (b.bookingId == id) {

                            b.room.available = true;
                            bookings.remove(i);

                            System.out.println(
                                "Booking cancelled successfully!"
                            );

                            found = true;
                            break;
                        }
                    }

                    if (!found) {
                        System.out.println("Booking ID not found!");
                    }
                    break;

                // View bookings
                case 4:
                    System.out.println("\n--- BOOKING DETAILS ---");

                    if (bookings.isEmpty()) {
                        System.out.println("No bookings available.");
                    } else {

                        for (Reservation b : bookings) {

                            System.out.println(
                                "Booking ID: " + b.bookingId
                            );

                            System.out.println(
                                "Customer: " + b.customerName
                            );

                            System.out.println(
                                "Room Number: " + b.room.roomNumber
                            );

                            System.out.println(
                                "Category: " + b.room.category
                            );

                            System.out.println(
                                "Price: Rs." + b.room.price
                            );

                            System.out.println("----------------------");
                        }
                    }
                    break;

                // Exit
                case 5:
                    System.out.println(
                        "Thank you for using Hotel Reservation System!"
                    );
                    break;

                default:
                    System.out.println("Invalid choice! Try again.");
            }

        } while (choice != 5);

        sc.close();
    }
}
