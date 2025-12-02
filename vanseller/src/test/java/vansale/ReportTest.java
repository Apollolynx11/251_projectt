package vansale;

import vansale.Report;
import vansale.Product;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;

public class ReportTest {

    @Test
  
public void testReportCalculation() {
    // Clear  lists
    Scan.soldProducts.clear();
    Scan.returnedProducts.clear();
    Report.history_report.clear();

    // Add Data directly 
    Scan.soldProducts.add(new Product("P1", 10, "Milk", LocalDate.now(), LocalDate.MAX, 10.0));
    Scan.soldProducts.add(new Product("P2", 5, "Nuts", LocalDate.now(), LocalDate.MAX, 20.0));

    //report
    new Report("daily");

    //Verify
    String output = Report.history_report.get(0);
    assertTrue(output.contains("Total Sales: 200.0"));
    assertTrue(output.contains("Total Cash: 200.0"));
}
}