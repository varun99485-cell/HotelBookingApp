import java.io.*;
import java.util.*;

// Reservation class (Serializable)
class Reservation implements Serializable {
    String guestName;
    String roomId;

    public Reservation(String guestName, String roomId) {
        this.guestName = guestName;
        this.roomId = roomId;
    }
}

public class HotelBookingApp implements Serializable {

    private HashMap<String, Integer> inventory;
    private List<Reservation> bookingHistory;

    private static final String FILE_NAME = "hotel_data.ser";

    // Constructor
    public HotelBookingApp() {
        inventory = new HashMap<>();
        bookingHistory = new ArrayList<>();
    }

    // Add data
    public void addRoomType(String type, int count) {
        inventory.put(type, count);
    }

    public void addBooking(String guestName, String roomId) {
        bookingHistory.add(new Reservation(guestName, roomId));
    }

    // Save data (Serialization)
    public void saveData() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME));
            oos.writeObject(this);
            oos.close();
            System.out.println("Data saved successfully.");
        } catch (Exception e) {
            System.out.println("Error saving data.");
        }
    }

    // Load data (Deserialization)
    public static HotelBookingApp loadData() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME));
            HotelBookingApp app = (HotelBookingApp) ois.readObject();
            ois.close();
            System.out.println("Data loaded successfully.");
            return app;
        } catch (Exception e) {
            System.out.println("No previous data found. Starting fresh.");
            return new HotelBookingApp();
        }
    }

    // Display data
    public void displayData() {
        System.out.println("\nInventory:");
        for (String type : inventory.keySet()) {
            System.out.println(type + " -> " + inventory.get(type));
        }

        System.out.println("\nBooking History:");
        for (Reservation r : bookingHistory) {
            System.out.println("Guest: " + r.guestName + ", Room ID: " + r.roomId);
        }
    }

    // Main method
    public static void main(String[] args) {

        // Load previous data
        HotelBookingApp app = HotelBookingApp.loadData();

        // Add sample data
        app.addRoomType("Single", 2);
        app.addBooking("Abhi", "Single-1");

        // Display current state
        app.displayData();

        // Save before exit
        app.saveData();
    }
}