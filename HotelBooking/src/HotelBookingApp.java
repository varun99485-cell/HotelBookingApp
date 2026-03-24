import java.util.*;

public class HotelBookingApp {

    // Inventory: roomType -> available count
    private HashMap<String, Integer> inventory;

    // Active bookings: roomId -> roomType
    private HashMap<String, String> activeBookings;

    // Stack for rollback (LIFO)
    private Stack<String> rollbackStack;

    // Constructor
    public HotelBookingApp() {
        inventory = new HashMap<>();
        activeBookings = new HashMap<>();
        rollbackStack = new Stack<>();
    }

    // Add room type
    public void addRoomType(String type, int count) {
        inventory.put(type, count);
    }

    // Confirm booking (simple allocation)
    public void confirmBooking(String roomId, String roomType) {
        if (inventory.containsKey(roomType) && inventory.get(roomType) > 0) {
            inventory.put(roomType, inventory.get(roomType) - 1);
            activeBookings.put(roomId, roomType);
            System.out.println("Booking confirmed: " + roomId);
        }
    }

    // Cancel booking with rollback
    public void cancelBooking(String roomId) {

        // Validate existence
        if (!activeBookings.containsKey(roomId)) {
            System.out.println("Cancellation failed: Invalid Room ID " + roomId);
            return;
        }

        // Get room type
        String roomType = activeBookings.get(roomId);

        // Push to rollback stack
        rollbackStack.push(roomId);

        // Remove booking
        activeBookings.remove(roomId);

        // Restore inventory
        inventory.put(roomType, inventory.get(roomType) + 1);

        System.out.println("Booking cancelled: " + roomId);
    }

    // Show rollback stack
    public void showRollbackStack() {
        System.out.println("Rollback Stack: " + rollbackStack);
    }

    // Main method
    public static void main(String[] args) {

        HotelBookingApp app = new HotelBookingApp();

        // Setup inventory
        app.addRoomType("Single", 2);

        // Confirm bookings
        app.confirmBooking("Single-1", "Single");
        app.confirmBooking("Single-2", "Single");

        // Cancel booking
        app.cancelBooking("Single-2");

        // Invalid cancellation
        app.cancelBooking("Single-3");

        // Show rollback
        app.showRollbackStack();
    }
}