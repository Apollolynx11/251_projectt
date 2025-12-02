package vansale;

import java.time.LocalDate;

public class DeliveryNote {

    private static int noteId=100;
    private static int saleId=100;
    private LocalDate date;
    private DriverProfile seller;

    private String customerName;
    private String customerAddress;


    private Scan scan; // reference to Scan

    public DeliveryNote( DriverProfile seller, String customerName, String customerAddress ) {
    noteId++;
    saleId++;
    date = LocalDate.now();
    this.seller = seller;
    this.customerName = customerName;
    this.customerAddress = customerAddress;
    scan = new Scan();


    }

    public void print() {
        System.out.println("=============================================");
        System.out.println("              VAN SALES DELIVERY NOTE        ");
        System.out.println("=============================================");
        System.out.printf("Note ID : %s\tDate: %s%n", noteId, date);
        System.out.printf("Van     : %s\tDriver: %s%n", seller.getVanId(), seller.getName());
        System.out.println("---------------------------------------------");
        System.out.println("Customer Information:");
        System.out.println("Name    : " + customerName);
        System.out.println("Address : " + customerAddress);
        System.out.println("Linked Sale: " + saleId);
        System.out.println("---------------------------------------------");
        System.out.printf("%-8s | %-20s | %5s | %9s%n",
                "Code", "Product", "Sold", "LineTotal");
        System.out.println("---------------------------------------------------------------");

        // Print sold products
        double net = printSoldProducts();
        
        System.out.println("---------------------------------------------------------------");
        System.out.printf("Net Sales Value: %.2f%n%n", net);
        System.out.println("=============================================");

        scan.setRecentlySold();
    }
    
     double printSoldProducts() {
        double net = 0;

        for (int i = 0; i < Scan.recently_sold.size(); i++) {
            Product p = Scan.recently_sold.get(i);

            double lineTotal = p.getPrice() * p.getQuantity();
            net += lineTotal;

            System.out.printf("%-8s | %-20s | %5d | %9.2f%n",
                    p.getProductID(), p.getName(), p.getQuantity(), lineTotal);
        }

        return net;
    }
}