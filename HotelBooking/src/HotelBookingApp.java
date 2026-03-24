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

    private HashMap<String, Integer> inventory = new HashMap<>();
    private Queue<Reservation> bookingQueue = new LinkedList<>();

    // Add room type
    public void addRoomType(String type, int count) {
        inventory.put(type, count);
    }

    // Add booking request (synchronized)
    public synchronized void addRequest(Reservation r) {
        bookingQueue.add(r);
    }

    // Process booking (critical section)
    public synchronized void processBooking() {
        if (bookingQueue.isEmpty()) return;

        Reservation r = bookingQueue.poll();
        int available = inventory.getOrDefault(r.roomType, 0);

        if (available > 0) {
            inventory.put(r.roomType, available - 1);
            System.out.println(Thread.currentThread().getName() +
                    " SUCCESS: " + r.guestName + " booked " + r.roomType);
        } else {
            System.out.println(Thread.currentThread().getName() +
                    " FAILED: " + r.guestName + " (No rooms)");
        }
    }

    // Thread class
    class BookingThread extends Thread {
        public void run() {
            while (true) {
                synchronized (HotelBookingApp.this) {
                    if (bookingQueue.isEmpty()) break;
                }
                processBooking();
            }
        }
    }

    public static void main(String[] args) {

        HotelBookingApp app = new HotelBookingApp();

        // Inventory
        app.addRoomType("Single", 2);

        // Requests
        app.addRequest(new Reservation("Abhi", "Single"));
        app.addRequest(new Reservation("Subha", "Single"));
        app.addRequest(new Reservation("Vanmathi", "Single"));

        // Threads
        Thread t1 = app.new BookingThread();
        Thread t2 = app.new BookingThread();

        t1.setName("Thread-1");
        t2.setName("Thread-2");

        t1.start();
        t2.start();
    }
}