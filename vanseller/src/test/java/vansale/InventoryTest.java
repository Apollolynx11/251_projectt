package vansale;

import vansale.Product;
import vansale.Inventory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;

class InventoryTest {
    private Product testProduct;
    
    @BeforeEach
    void setUp() {
        Inventory.getProducts().clear();
        testProduct = new Product("P101", 50, "milk", 
            LocalDate.parse("2025-01-01"), LocalDate.parse("2025-12-01"), 15.50);
    }
    
    @Test
    void testAddAndFindProduct() {
        Inventory.addToInventory(testProduct);
        
        assertEquals(1, Inventory.getInventorySize());
        Product found = Inventory.findProductByID("P101");
        assertNotNull(found);
        assertEquals("milk", found.getName());
    }
    
    @Test
    void testRemoveProduct() {
        Inventory.addToInventory(testProduct);
        assertEquals(1, Inventory.getInventorySize());
        
        Inventory.removeProduct("P101");
        
        assertEquals(0, Inventory.getInventorySize());
        assertNull(Inventory.findProductByID("P101"));
    }
    
}