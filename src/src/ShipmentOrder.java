public abstract class ShipmentOrder implements SummaryPrintable {

    private String orderNumber;
    private String customerName;
    private int distanceKm;
    private double baseFee;
    private boolean insured;
    private double lastCalculatedPrice;

    public ShipmentOrder(String orderNumber, String customerName, int distanceKm,
                         double baseFee, boolean insured) {
        this.orderNumber = orderNumber;
        this.customerName = customerName;
        this.distanceKm = distanceKm;
        this.baseFee = baseFee;
        this.insured = insured;
    }

    public final void processOrder() {
        validateOrder();
        validateSpecificRules();

        double price = calculateBasePrice();
        price += calculateAdditionalFee();
        price = applyInsurance(price);
        price = applyBusinessDiscount(price);

        lastCalculatedPrice = price;
        printProcessingResult();
    }

    private void validateOrder() {
        if (orderNumber == null || orderNumber.isBlank()) {
            throw new IllegalArgumentException("Numer zamówienia nie może być pusty.");
        }
        if (distanceKm <= 0) {
            throw new IllegalArgumentException("Odległość dostawy musi być dodatnia.");
        }
    }

    private double applyInsurance(double price) {
        if (insured) {
            price *= 1.07;
        }
        return price;
    }

    private void printProcessingResult() {
        System.out.printf("Przetworzono zamówienie %s | Klient: %s | Typ: %s%n",
                orderNumber, customerName, getShipmentType());
        System.out.printf("Cena końcowa: %.2f PLN%n", lastCalculatedPrice);
    }

    protected void validateSpecificRules() {
    }

    protected double applyBusinessDiscount(double price) {
        return price;
    }

    @Override
    public String buildSummaryLine() {
        return String.format("[%s] %s | %s | %.2f PLN",
                orderNumber, customerName, getShipmentType(), lastCalculatedPrice);
    }

    public String getOrderNumber()         { return orderNumber; }
    public String getCustomerName()        { return customerName; }
    public int getDistanceKm()             { return distanceKm; }
    public double getBaseFee()             { return baseFee; }
    public boolean isInsured()             { return insured; }
    public double getLastCalculatedPrice() { return lastCalculatedPrice; }

    protected abstract double calculateBasePrice();
    protected abstract double calculateAdditionalFee();
    public abstract String getShipmentType();
}