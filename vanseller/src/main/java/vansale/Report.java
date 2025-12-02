package vansale;

import vansale.Product;
import java.time.LocalDate;
import java.util.ArrayList;

public class Report {
    private String date;
    private String type; // "Daily" or "Monthly"
    private double totalSales = 0;
    private double totalReturns = 0;
    private double totalCash = 0;
    public static ArrayList<String> history_report = new ArrayList<>();


    // Constructor now takes Scan
    public Report(String type) {
        this.type = type;
        if (type.equalsIgnoreCase("daily")) {
            date = LocalDate.now().toString();
        } else if (type.equalsIgnoreCase("monthly")) {
            date = LocalDate.now().getMonth().toString();
        }

        calculate();
        print_report();
    }

    public void calculate() {
        Scan s = new Scan();
        // Calculate sales from soldProducts
        for (int i =0; i<= s.soldProducts.size()-1; i++) {
            Product p = s.soldProducts.get(i);
            totalSales += p.getPrice() * p.getQuantity();
        }
        // Calculate returns from returnedProducts
        for (int i =0; i<=s.returnedProducts.size()-1; i++) {
            Product p = s.returnedProducts.get(i);
            totalReturns += p.getPrice() *p.getQuantity();
        }

        totalCash = totalSales - totalReturns;
    }

    public void Last_month() {
        double lastMonthSales = 0;
        double lastMonthReturns = 0;
        double lastMonthCash = 0;

        if (history_report.size() > 0) {
            String last_month = history_report.get(history_report.size() - 1);
            String[] lines = last_month.split("\n");
            for (int i = 0; i < lines.length; i++) {
                if (lines[i].startsWith("Total Sales: ")) {
                    lastMonthSales = Double.parseDouble(lines[i].split(": ")[1]);
                    lastMonthReturns = Double.parseDouble(lines[i + 1].split(": ")[1]);
                    lastMonthCash = Double.parseDouble(lines[i + 2].split(": ")[1]);
                    break;
                }
            }
            System.out.println("Sales Percentage: " + ((totalSales - lastMonthSales) / lastMonthSales) * 100 + " % ");
            System.out.println("Return Percentage: " + ((totalReturns - lastMonthReturns) / lastMonthReturns) * 100 + " % ");
            System.out.println("Cash Percentage: " + ((totalCash - lastMonthCash) / lastMonthCash) * 100 + " % ");
        }
    }

    public void print_report() {
        StringBuilder report = new StringBuilder();
        report.append(type + " Report :\n");
        report.append("__________________________________________\n");
        report.append("Issued Date: " + LocalDate.now().toString() + "\n");
        if (type.equalsIgnoreCase("monthly")) {
            report.append("Requested Month " + date + "\n");
        } else {
            report.append("Requested Day " + date + "\n");
        }
        report.append("__________________________________________\n");
        report.append("Total Sales: " + totalSales + "\n");
        report.append("Total Returns: " + totalReturns + "\n");
        report.append("Total Cash: " + totalCash + "\n");
        report.append("__________________________________________");
        System.out.println(report.toString());
        Last_month();
        Save_report(report.toString());
    }

    public void Save_report(String report) {
        history_report.add(report);
    }
}
