package flight.reservation.flight;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Schedule {

    private static Schedule instance;
    private List<ScheduledFlight> scheduledFlights;

    // Private constructor to prevent instantiation
    private Schedule() {
        scheduledFlights = new ArrayList<>();
    }

    // Static method to provide access to the single instance
    public static Schedule getInstance() {
        if (instance == null)
            instance = new Schedule();
        else instance.clear();
        return instance;
    }

    public List<ScheduledFlight> getScheduledFlights() {
        return scheduledFlights;
    }

    public void scheduleFlight(Flight flight, Date date) {
        ScheduledFlight scheduledFlight = new ScheduledFlight(flight.getNumber(), flight.getDeparture(), flight.getArrival(), flight.getAircraft(), date);
        scheduledFlights.add(scheduledFlight);
    }

    public void removeFlight(Flight flight) {
        List<ScheduledFlight> tbr = new ArrayList<>();
        for (ScheduledFlight scheduledFlight : scheduledFlights) {
            if (scheduledFlight == flight ||
                    (flight.getArrival() == scheduledFlight.getArrival() &&
                            flight.getDeparture() == scheduledFlight.getDeparture() &&
                            flight.getNumber() == scheduledFlight.getNumber())) {
                tbr.add(scheduledFlight);
            }
        }
        scheduledFlights.removeAll(tbr);
    }

    public void removeScheduledFlight(ScheduledFlight flight) {
        scheduledFlights.remove(flight);
    }

    public ScheduledFlight searchScheduledFlight(int flightNumber) {
        return scheduledFlights.stream()
                .filter(f -> f.getNumber() == flightNumber)
                .findFirst()
                .orElse(null);
    }

    public void clear() {
        scheduledFlights.clear();
    }
}
