package vansale;

import vansale.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;

class DeliveryNoteTest {
    private DeliveryNote deliveryNote;
    private DriverProfile seller;
    
    @BeforeEach
    void setUp() {
        Scan.recently_sold.clear();
        seller = new DriverProfile("V001", "seller1");
    }
    
    @Test
    void testPrintWithMultipleProducts() {
        Product milk = new Product("P101", 10, "milk", 
            LocalDate.parse("2025-01-01"), LocalDate.parse("2025-12-01"), 15.50);
        Product nuts = new Product("P102", 10, "nuts", 
            LocalDate.parse("2025-05-15"), LocalDate.parse("2025-12-10"), 5.00);
        
        Scan.recently_sold.add(milk);
        Scan.recently_sold.add(nuts);
        
        deliveryNote = new DeliveryNote(seller, "Customer_A_Store", "Jeddah");
        assertNotNull(deliveryNote);
        deliveryNote.print();
        
        assertEquals(0, Scan.recently_sold.size());
    }
  
}