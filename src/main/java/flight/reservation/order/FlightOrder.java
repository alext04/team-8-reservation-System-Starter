package flight.reservation.order;

import flight.reservation.Customer;
import flight.reservation.flight.ScheduledFlight;
import flight.reservation.payment.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class FlightOrder extends Order {
    private final List<ScheduledFlight> flights;
    static List<String> noFlyList = Arrays.asList("Peter", "Johannes");

    public FlightOrder(List<ScheduledFlight> flights) {
        this.flights = flights;
    }

    public static List<String> getNoFlyList() {
        return noFlyList;
    }

    public List<ScheduledFlight> getScheduledFlights() {
        return flights;
    }

    private boolean isOrderValid(Customer customer, List<String> passengerNames, List<ScheduledFlight> flights) {
        boolean valid = true;
        valid = valid && !noFlyList.contains(customer.getName());
        valid = valid && passengerNames.stream().noneMatch(passenger -> noFlyList.contains(passenger));
        valid = valid && flights.stream().allMatch(scheduledFlight -> {
            try {
                return scheduledFlight.getAvailableCapacity() >= passengerNames.size();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
                return false;
            }
        });
        return valid;
    }

    public boolean processOrderWithCreditCard(CreditCard creditCard) throws IllegalStateException {
        if (!(creditCard != null && creditCard.isValid())) {
        throw new IllegalStateException("Payment information is not set or not valid.");
    }
        PaymentStrategy Payment = new CreditCardPayment(creditCard);
        boolean paid = processOrder(Payment);
        return paid;
    }


    public boolean processOrderWithPayPal(String email, String password) throws IllegalStateException {
        PaymentStrategy Payment = new PayPalPayment(email, password);
        boolean paid = processOrder(Payment);
        return paid;
    }

    public boolean processOrder(PaymentStrategy paymentStrategy) {
        if (isClosed()) {
            return true; 
        }
        boolean isPaid = paymentStrategy.pay(getPrice());
        if (isPaid) {
            setClosed();
        }
        return isPaid;
    }
}
