
import java.util.LinkedList;
import java.util.Queue;

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

    private Queue<Reservation> bookingQueue;

    // Constructor
    public HotelBookingApp() {
        bookingQueue = new LinkedList<>();
    }

    // Add request
    public void addRequest(String guestName, String roomType) {
        bookingQueue.add(new Reservation(guestName, roomType));
    }

    // Process requests (FIFO)
    public void processRequests() {
        System.out.println("Booking Request Queue");

        while (!bookingQueue.isEmpty()) {
            Reservation r = bookingQueue.poll();
            System.out.println("Processing booking for Guest: "
                    + r.guestName + ", Room Type: " + r.roomType);
        }
    }

    // Main method
    public static void main(String[] args) {

        HotelBookingApp app = new HotelBookingApp();

        // Add requests
        app.addRequest("Abhi", "Single");
        app.addRequest("Subha", "Double");
        app.addRequest("Vanmathi", "Suite");

        // Process queue
        app.processRequests();
    }
}
