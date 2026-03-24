import java.util.*;

// Reservation class
class Reservation {
    String guestName;
    String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }
}

public class HotelBookingApp {

    private HashMap<String, Integer> inventory;
    private Queue<Reservation> bookingQueue;
    private Set<String> allocatedRoomIds;
    private HashMap<String, Integer> roomCounters;

    // Constructor
    public HotelBookingApp() {
        inventory = new HashMap<>();
        bookingQueue = new LinkedList<>();
        allocatedRoomIds = new HashSet<>();
        roomCounters = new HashMap<>();
    }

    // Add room type
    public void addRoomType(String type, int count) {
        inventory.put(type, count);
        roomCounters.put(type, 1);
    }

    // Add booking request
    public void addRequest(String guestName, String roomType) {
        bookingQueue.add(new Reservation(guestName, roomType));
    }

    // Process bookings
    public void processBookings() {
        System.out.println("Room Allocation Processing");

        while (!bookingQueue.isEmpty()) {
            Reservation r = bookingQueue.poll();
            String type = r.roomType;

            int available = inventory.getOrDefault(type, 0);

            if (available > 0) {

                // Generate ID like Single-1
                int count = roomCounters.get(type);
                String roomId = type + "-" + count;

                if (!allocatedRoomIds.contains(roomId)) {
                    allocatedRoomIds.add(roomId);

                    // Update counter
                    roomCounters.put(type, count + 1);

                    // Update inventory
                    inventory.put(type, available - 1);

                    // Print output
                    System.out.println("Booking confirmed for Guest: "
                            + r.guestName + ", Room ID: " + roomId);
                }
            }
        }
    }

    // Main method
    public static void main(String[] args) {

        HotelBookingApp app = new HotelBookingApp();

        // Setup inventory
        app.addRoomType("Single", 2);
        app.addRoomType("Suite", 1);

        // Add requests
        app.addRequest("Abhi", "Single");
        app.addRequest("Subha", "Single");
        app.addRequest("Vanmathi", "Suite");

        // Process bookings
        app.processBookings();
    }
}