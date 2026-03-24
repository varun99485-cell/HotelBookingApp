// Version 3.1 - Centralized Room Inventory Management

import java.util.HashMap;
import java.util.Map;

// Inventory Class (Encapsulation of Inventory Logic)
class RoomInventory {

    private HashMap<String, Integer> inventory;

    // Constructor - Initialize inventory
    public RoomInventory() {
        inventory = new HashMap<>();

        // Register room types with initial availability
        inventory.put("Single Room", 10);
        inventory.put("Double Room", 5);
        inventory.put("Suite Room", 2);
    }

    // Get availability of a specific room type
    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    // Update availability (controlled update)
    public void updateAvailability(String roomType, int newCount) {
        if (inventory.containsKey(roomType)) {
            inventory.put(roomType, newCount);
        } else {
            System.out.println("Room type not found: " + roomType);
        }
    }

    // Display full inventory
    public void displayInventory() {
        System.out.println("===== Current Room Inventory =====");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + " -> Available: " + entry.getValue());
        }
        System.out.println("----------------------------------");
    }
}

// Main Class
public class HotelBookingApp {

    public static void main(String[] args) {

        // Initialize inventory system
        RoomInventory inventory = new RoomInventory();

        // Display initial inventory
        inventory.displayInventory();

        // Retrieve availability (O(1) lookup)
        System.out.println("\nChecking availability for Double Room:");
        System.out.println("Available: " + inventory.getAvailability("Double Room"));

        // Update availability
        System.out.println("\nUpdating Suite Room availability...");
        inventory.updateAvailability("Suite Room", 1);

        // Display updated inventory
        System.out.println("\nAfter Update:");
        inventory.displayInventory();

        System.out.println("\nApplication Terminated.");
    }
}