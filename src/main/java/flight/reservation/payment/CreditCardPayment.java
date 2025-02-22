package flight.reservation.payment;

public class CreditCardPayment implements PaymentStrategy {
    private final CreditCard creditCard;

    public CreditCardPayment(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    @Override
    public boolean pay(double amount) {
        if (creditCard.isValid()) {
            double remainingAmount = creditCard.getAmount() - amount;
            if (remainingAmount < 0) {
                System.out.println("Card limit reached - Balance: " + remainingAmount);
                throw new IllegalStateException("Card limit reached");
            }
            creditCard.setAmount(remainingAmount);
            System.out.println("Paid " + amount + " using Credit Card.");
            return true;
        }
        return false;
    }
}
