import java.util.*;

// Custom Exception
class InvalidBookingException extends Exception {
    public InvalidBookingException(String message) {
        super(message);
    }
}

public class HotelBookingApp {

    private HashMap<String, Integer> inventory;

    // Constructor
    public HotelBookingApp() {
        inventory = new HashMap<>();
    }

    // Add room type
    public void addRoomType(String type, int count) {
        inventory.put(type, count);
    }

    // Validate booking
    public void validateBooking(String roomType) throws InvalidBookingException {

        // Check if room type exists
        if (!inventory.containsKey(roomType)) {
            throw new InvalidBookingException("Invalid room type: " + roomType);
        }

        // Check availability
        if (inventory.get(roomType) <= 0) {
            throw new InvalidBookingException("No rooms available for: " + roomType);
        }
    }

    // Process booking safely
    public void bookRoom(String guestName, String roomType) {
        try {
            validateBooking(roomType);

            // Update inventory only if valid
            inventory.put(roomType, inventory.get(roomType) - 1);

            System.out.println("Booking successful for " + guestName + " (" + roomType + ")");

        } catch (InvalidBookingException e) {
            System.out.println("Booking failed: " + e.getMessage());
        }
    }

    // Main method
    public static void main(String[] args) {

        HotelBookingApp app = new HotelBookingApp();

        // Setup inventory
        app.addRoomType("Single", 1);
        app.addRoomType("Double", 0);

        // Valid booking
        app.bookRoom("Abhi", "Single");

        // Invalid: no availability
        app.bookRoom("Subha", "Double");

        // Invalid: wrong type
        app.bookRoom("Vanmathi", "Suite");
    }
}