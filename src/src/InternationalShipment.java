public class InternationalShipment extends ShipmentOrder {

    private String destinationCountry;
    private boolean customsDocumentsRequired;
    private boolean expressDelivery;

    public InternationalShipment(String orderNumber, String customerName, int distanceKm,
                                 double baseFee, boolean insured,
                                 String destinationCountry,
                                 boolean customsDocumentsRequired, boolean expressDelivery) {
        super(orderNumber, customerName, distanceKm, baseFee, insured);
        this.destinationCountry = destinationCountry;
        this.customsDocumentsRequired = customsDocumentsRequired;
        this.expressDelivery = expressDelivery;
    }

    @Override
    public String getShipmentType() {
        return "International";
    }

    @Override
    protected double calculateBasePrice() {
        return getBaseFee() + getDistanceKm() * 2.10;
    }

    @Override
    protected double calculateAdditionalFee() {
        double fee = 0;
        if (customsDocumentsRequired) {
            fee += 45.0;
        }
        if (expressDelivery) {
            fee += 80.0;
        }
        return fee;
    }

    @Override
    protected void validateSpecificRules() {
        if (destinationCountry == null || destinationCountry.isBlank()) {
            throw new IllegalArgumentException("Kraj docelowy nie może być pusty.");
        }
    }

    @Override
    protected double applyBusinessDiscount(double price) {
        if (!expressDelivery && getDistanceKm() > 1000) {
            price *= 0.97;
        }
        return price;
    }
}