package flight.reservation.payment;

public class PayPalPayment implements PaymentStrategy {
    private final String email;
    private final String password;

    public PayPalPayment(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Override
    public boolean pay(double amount) {
        if (email.equals(Paypal.DATA_BASE.get(password))) {
            System.out.println("Paid " + amount + " using PayPal.");
            return true;
        }
        throw new IllegalStateException("Invalid PayPal credentials.");
    }
}
