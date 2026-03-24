import java.util.*;

// Service class
class Service {
    String name;
    double cost;

    public Service(String name, double cost) {
        this.name = name;
        this.cost = cost;
    }
}

public class HotelBookingApp {

    // Map: Reservation ID -> List of Services
    private HashMap<String, List<Service>> serviceMap;

    // Constructor
    public HotelBookingApp() {
        serviceMap = new HashMap<>();
    }

    // Add service
    public void addService(String reservationId, String serviceName, double cost) {
        serviceMap.putIfAbsent(reservationId, new ArrayList<>());
        serviceMap.get(reservationId).add(new Service(serviceName, cost));
    }

    // Display total cost only
    public void displayTotalCost(String reservationId) {
        System.out.println("Add-On Service Selection");
        System.out.println("Reservation ID: " + reservationId);

        double total = 0;
        List<Service> services = serviceMap.get(reservationId);

        if (services != null) {
            for (Service s : services) {
                total += s.cost;
            }
        }

        System.out.println("Total Add-On Cost: " + total);
    }

    // Main method
    public static void main(String[] args) {

        HotelBookingApp app = new HotelBookingApp();

        // Given reservation
        String reservationId = "Single-1";

        // Add services (sum = 1500.0)
        app.addService(reservationId, "Breakfast", 500);
        app.addService(reservationId, "Spa", 1000);

        // Display result
        app.displayTotalCost(reservationId);
    }
}