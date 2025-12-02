package vansale;
import vansale.Product;
import vansale.Inventory;
import java.time.LocalDate;
import java.util.ArrayList;

import static vansale.Inventory.addToInventory;
import static vansale.Inventory.findProductByID;


public class Scan {
    public static ArrayList<Product> soldProducts = new ArrayList();
    public static ArrayList<Product> returnedProducts = new ArrayList();
    public static ArrayList<Product> recently_sold = new ArrayList();


    public Scan() {
    }

    public boolean load(Product p) {
        if (Inventory.getInventorySize() != 0) {
            boolean check = p.getProductID().equals(findProductByID(p.getProductID()));
            if (check == false) {
                Inventory.addToInventory(p);
                return true;
            } else {
                System.out.println("Product already scaned found");
                return false;
            }

        } else {
            Inventory.addToInventory(p);
            return true;

        }
    }
    public boolean Return(String id){
        Product pr = findProductByID(id);
        if (pr != null) {
            Product p = operations(id, pr.getQuantity());
            if (p != null) {
                returnedProducts.add(p);
                Inventory.removeProduct(p.getProductID());
                return true;
            }
        }else {
            System.out.println("Product not found");

        }
        return false;
    }
    public boolean Sold(String id, int quantity){
        Product inventoryItem = operations(id,quantity);

        if(inventoryItem != null){

            Product soldCopy = new Product(
                    inventoryItem.getProductID(),
                    quantity,
                    inventoryItem.getName(),
                    inventoryItem.getManufactureDate(),
                    inventoryItem.getExpiryDate(),
                    inventoryItem.getPrice()
            );

            soldProducts.add(soldCopy);
            soldCopy.setQuantity(quantity);
            recently_sold.add(soldCopy);
            inventoryItem.setQuantity(inventoryItem.getQuantity() - quantity);
            return true;
        }
        return false;
    }

    public Product operations (String id,int quantity){
        Product p = findProductByID(id);
        if(p!=null){
            if(cheack_quantity(quantity, p.getQuantity())){
                if(cheack_date(p.getExpiryDate())) {
                    return p;
                }
            }

        }
        return null;
    }

    public boolean cheack_date (LocalDate exp_date) {
        if ( exp_date.getMonthValue()>LocalDate.now().getMonthValue()-3)//to check it's befor 3 month of exp date
           {
               return true;
        }
        System.out.println("Expiration date less than 3 months!");
        return false;
    }
    public boolean cheack_quantity(int input_q ,int product_q) {
        if (input_q <= 0 || input_q > product_q) {
            System.out.println("Invalid quantity.");
            return false;
        }
      return true;
    }
    public void setRecentlySold () {
        recently_sold.clear();
    }



}