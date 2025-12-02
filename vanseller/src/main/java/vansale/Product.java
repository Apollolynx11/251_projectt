package vansale;

import vansale.Inventory;
import java.time.LocalDate;

public class Product {
    private String id;
    private int quantity;
    private String name;
    private LocalDate manufactureDate;
    private LocalDate expiryDate;
    private double price;




    public Product(String id, int quantity, String name,
                   LocalDate manufactureDate,
                   LocalDate expiryDate,
                   double price
                  ) {
        this.id = id;
        this.name = name;
        this.manufactureDate = manufactureDate;
        this.expiryDate = expiryDate;
        this.price = price;
        this.quantity = quantity;

    }



    // --- Getters ---
    public String getProductID() { return id; }
    public String getName() { return name; }
    public LocalDate getManufactureDate() { return manufactureDate; }
    public LocalDate getExpiryDate() { return expiryDate; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }
    public void setProductID(String id) { this.id = id; }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        if (quantity == 0){
            Inventory.removeProduct(id);
        }
    }

    public String getProductDetails() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", mfg=" + manufactureDate +
                ", exp=" + expiryDate +
                ", price=" + price +
                ", stock=" + quantity +
                '}';
    }
}