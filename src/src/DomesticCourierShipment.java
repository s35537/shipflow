public class DomesticCourierShipment extends ShipmentOrder {

    private double packageWeightKg;
    private boolean weekendDelivery;

    public DomesticCourierShipment(String orderNumber, String customerName, int distanceKm,
                                   double baseFee, boolean insured,
                                   double packageWeightKg, boolean weekendDelivery) {
        super(orderNumber, customerName, distanceKm, baseFee, insured);
        this.packageWeightKg = packageWeightKg;
        this.weekendDelivery = weekendDelivery;
    }

    @Override
    public String getShipmentType() {
        return "Domestic courier";
    }

    @Override
    protected double calculateBasePrice() {
        return getBaseFee() + getDistanceKm() * 1.20;
    }

    @Override
    protected double calculateAdditionalFee() {
        double fee = packageWeightKg * 4.00;
        if (weekendDelivery) {
            fee += 25.0;
        }
        return fee;
    }

    @Override
    protected double applyBusinessDiscount(double price) {
        if (getDistanceKm() >= 300) {
            price *= 0.95;
        }
        return price;
    }
}