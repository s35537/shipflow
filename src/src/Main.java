public class Main {
    public static void main(String[] args) {
        ShipmentOrder[] orders = new ShipmentOrder[] {
                new DomesticCourierShipment("DOM-100", "Anna Kowalska", 120, 35.0, true, 8.5, false),
                new DomesticCourierShipment("DOM-101", "Piotr Nowak", 420, 40.0, false, 14.0, true),
                new PickupPointShipment("PCK-200", "Marta Zielinska", 55, 22.0, false, "M", true),
                new PickupPointShipment("PCK-201", "Jan Malinowski", 30, 19.0, true, "S", false),
                new InternationalShipment("INT-300", "TechNova Sp. z o.o.", 1350, 110.0, true, "Germany", true, false),
                new InternationalShipment("INT-301", "SoftLine S.A.", 2100, 140.0, false, "Spain", true, true)
        };

        for (ShipmentOrder order : orders) {
            order.processOrder();
            System.out.println(order.buildSummaryLine());
            System.out.println();
        }
    }
}