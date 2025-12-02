package vansale;


import java.util.ArrayList;

public class Inventory {
    private static ArrayList<Product> products= new ArrayList<Product>();

    public Inventory() {

    }

    public static void addToInventory(Product p) {
        products.add(p);
    }

    public static int getInventorySize() {
        if(products==null) return 0;
         return products.size();
    }

    public static Product findProductByID(String id) {
        if (getInventorySize() > 0) {
            for (Product p : products) {
                if (p.getProductID().equals(id)) {
                    return p;
                }
            }
        }
        return null;
    }

    public static void removeProduct(String id) {
                products.remove(findProductByID(id));
    }

    public void displayInventory() {
        for (Product p : products) {
            System.out.println(p.getProductDetails());
        }
    }

    public static ArrayList<Product> getProducts() {
        return products;
    }

}