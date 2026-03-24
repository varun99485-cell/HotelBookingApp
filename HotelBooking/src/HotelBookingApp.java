// Version 2.1 - Refactored Room Initialization

// Abstract Class
abstract class Room {
    private String roomType;
    private int numberOfBeds;
    private double size;
    private double price;

    // Constructor
    public Room(String roomType, int numberOfBeds, double size, double price) {
        this.roomType = roomType;
        this.numberOfBeds = numberOfBeds;
        this.size = size;
        this.price = price;
    }

    // Getters (Encapsulation)
    public String getRoomType() {
        return roomType;
    }

    public int getNumberOfBeds() {
        return numberOfBeds;
    }

    public double getSize() {
        return size;
    }

    public double getPrice() {
        return price;
    }

    // Abstract method
    public abstract void displayRoomDetails();
}

// Concrete Class - Single Room
class SingleRoom extends Room {

    public SingleRoom() {
        super("Single Room", 1, 150.0, 2000.0);
    }

    @Override
    public void displayRoomDetails() {
        System.out.println("Room Type: " + getRoomType());
        System.out.println("Beds: " + getNumberOfBeds());
        System.out.println("Size: " + getSize() + " sq ft");
        System.out.println("Price: ₹" + getPrice());
    }
}

// Concrete Class - Double Room
class DoubleRoom extends Room {

    public DoubleRoom() {
        super("Double Room", 2, 250.0, 3500.0);
    }

    @Override
    public void displayRoomDetails() {
        System.out.println("Room Type: " + getRoomType());
        System.out.println("Beds: " + getNumberOfBeds());
        System.out.println("Size: " + getSize() + " sq ft");
        System.out.println("Price: ₹" + getPrice());
    }
}

// Concrete Class - Suite Room
class SuiteRoom extends Room {

    public SuiteRoom() {
        super("Suite Room", 3, 500.0, 8000.0);
    }

    @Override
    public void displayRoomDetails() {
        System.out.println("Room Type: " + getRoomType());
        System.out.println("Beds: " + getNumberOfBeds());
        System.out.println("Size: " + getSize() + " sq ft");
        System.out.println("Price: ₹" + getPrice());
    }
}

// Main Class
public class HotelBookingApp {

    public static void main(String[] args) {

        // Polymorphism: Using Room reference
        Room singleRoom = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suiteRoom = new SuiteRoom();

        // Static availability variables
        int singleRoomAvailability = 10;
        int doubleRoomAvailability = 5;
        int suiteRoomAvailability = 2;

        System.out.println("===== Room Details & Availability =====\n");

        // Display Single Room
        singleRoom.displayRoomDetails();
        System.out.println("Available: " + singleRoomAvailability);
        System.out.println("--------------------------------------");

        // Display Double Room
        doubleRoom.displayRoomDetails();
        System.out.println("Available: " + doubleRoomAvailability);
        System.out.println("--------------------------------------");

        // Display Suite Room
        suiteRoom.displayRoomDetails();
        System.out.println("Available: " + suiteRoomAvailability);
        System.out.println("--------------------------------------");

        System.out.println("\nApplication Terminated.");
    }
}