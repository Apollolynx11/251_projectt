/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vansale;

/**
 *
 * @author lelo
 */


import vansale.User;
import vansale.Report;
import vansale.Product;
import vansale.Inventory;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import org.junit.jupiter.api.*;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class ScanTest {

    private Scan scan;
    private Product milk;
    private Product nuts;
    private Product coffee;

      @BeforeAll
    static void setUpFiles() throws IOException {
        // Create login.txt (runs ONCE before all tests)
        PrintWriter login = new PrintWriter(new FileWriter("login.txt"));
        login.println("USER seller1 1234 SalesPerson");
        login.println("USER manager1 9876 MANAGER");
        login.println("USER seller2 14554 SalesPerson");
        login.close();
        
        // Create user_info.txt (runs ONCE before all tests)
        PrintWriter userInfo = new PrintWriter(new FileWriter("user_info.txt"));
        userInfo.println("seller1 VAN001 SellerOne");
        userInfo.println("manager1 VAN002 ManagerOne");
        userInfo.println("seller2 VAN003 SellerTwo");
        userInfo.close();
    }
    
    @BeforeEach
    void setUp() {
        // Clear static data to keep tests independent
        Inventory.getProducts().clear();
        Scan.soldProducts.clear();
        Scan.returnedProducts.clear();
        Scan.recently_sold.clear();
        
        scan = new Scan();
        
        // Initialize products
        milk = new Product("P101", 50, "milk", 
            LocalDate.now(), LocalDate.now().plusYears(1), 15.50);
        nuts = new Product("P102", 100, "nuts", 
            LocalDate.now(), LocalDate.now().plusYears(1), 5.00);
        coffee = new Product("P103", 25, "coffee", 
            LocalDate.now(), LocalDate.now().plusYears(1), 8.75);
    }
    
        // ==================== LOGIN TESTS ====================
    
    @Test
    void testSellerLogin() throws IOException {
        assertTrue(User.login("seller1", "1234"));
    }

    @Test
    void testManagerLogin() throws IOException {
        assertTrue(User.login("manager1", "9876"));
    }

    @Test
    void testInvalidLogin() throws IOException {
        assertFalse(User.login("seller2", "wrong"));
    }
    

    // ==================== LOAD TESTS ====================
    @Test
   
    void testLoadMilk() {
        assertEquals(0, Inventory.getInventorySize(), "Inventory should be empty before loading");

        assertTrue(scan.load(milk));
        // Verify it was actually added to inventory
        assertNotNull(Inventory.findProductByID("P101"));
    }

    @Test
    void testLoadMultipleProducts() {
        assertEquals(0, Inventory.getInventorySize(), "Inventory should be empty before loading");
        assertTrue(scan.load(milk));
        assertTrue(scan.load(nuts));
        assertTrue(scan.load(coffee));
        assertEquals(3, Inventory.getInventorySize());
    }

    // ==================== SELL TESTS ====================
    
    @Test
    void testSellMultipleTimes() {
        scan.load(milk);
        scan.Sold("P101", 10);
        assertEquals(40, Inventory.findProductByID("P101").getQuantity());
        
        scan.Sold("P101", 20);
        assertEquals(20, Inventory.findProductByID("P101").getQuantity());
    }

    @Test
    void testSoldProductsTracking() {
        scan.load(coffee);
        scan.Sold("P103", 10);
        assertEquals(1, Scan.soldProducts.size());
        
        scan.Sold("P103", 5);
        assertEquals(2, Scan.soldProducts.size());
    }

    @Test
    void testRecentlySoldList() {
        scan.load(milk);
        scan.Sold("P101", 10);
        assertEquals(1, Scan.recently_sold.size());
    }

    // ==================== RETURN TESTS ====================
   
    @Test
    void testReturnNonExistent() {
        assertFalse(scan.Return("P999"));
    }

    @Test
    void testReturnMultipleProducts() {
        scan.load(milk);
        scan.load(nuts);
        
        scan.Return("P101");
        scan.Return("P102");
        assertEquals(2, Scan.returnedProducts.size());
    }

    // ==================== DATE TESTS ====================

    @Test
  void testDailyDate() {
        new Report("daily");
        String savedReport = Report.history_report.get(0);
        assertTrue(savedReport.contains(LocalDate.now().toString()));
    }
    
       @Test
  void testCheckDateValid() {
    // We use plusMonths(10) to land in October (Month 10).
    // Logic: 10 > (12 - 3) is True.
    LocalDate ExpirationDate = LocalDate.now().plusMonths(10); 
    assertTrue(scan.cheack_date(ExpirationDate));
}
}
