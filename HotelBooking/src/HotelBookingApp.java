import java.util.HashMap;
import java.util.Map;

// Room class
class Room {
    int beds;
    int size;
    double price;
    int availability;

    public Room(int beds, int size, double price, int availability) {
        this.beds = beds;
        this.size = size;
        this.price = price;
        this.availability = availability;
    }
}

public class HotelBookingApp {

    private HashMap<String, Room> inventory;

    // Constructor
    public HotelBookingApp() {
        inventory = new HashMap<>();
    }

    // Add rooms (setup)
    public void addRoom(String type, int beds, int size, double price, int availability) {
        inventory.put(type, new Room(beds, size, price, availability));
    }

    // Search (READ-ONLY)
    public void searchRooms() {
        System.out.println("Room Search\n");

        for (Map.Entry<String, Room> entry : inventory.entrySet()) {
            String type = entry.getKey();
            Room r = entry.getValue();

            // Show only available rooms
            if (r.availability > 0) {
                System.out.println(type + " Room:");
                System.out.println("Beds: " + r.beds);
                System.out.println("Size: " + r.size + " sqft");
                System.out.println("Price per night: " + r.price);
                System.out.println("Available: " + r.availability);
                System.out.println();
            }
        }
    }

    // Main method
    public static void main(String[] args) {

        HotelBookingApp system = new HotelBookingApp();

        // Add rooms (matching your output)
        system.addRoom("Single", 1, 250, 1500.0, 5);
        system.addRoom("Double", 2, 400, 2500.0, 3);
        system.addRoom("Suite", 3, 750, 5000.0, 2);

        // Perform search
        system.searchRooms();
    }
}