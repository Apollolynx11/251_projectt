package vansellersystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;
import static java.time.LocalDate.parse;

public class VanSellerSystem {
    public static void main(String[] args) throws IOException {
        File file = new File("input.txt");

        if(!file.exists()){
            System.out.println("File does not exist");
            System.exit(0);
        }
        Scanner input = new Scanner(file);
        //_________________________________________________________________________________________________________________________
        //Login function
        String word = input.next();
        if (word.equalsIgnoreCase("Login")) {
            String User_name = input.next();
            String Password = input.next();
            boolean Flag = User.login(User_name, Password);
            if (Flag) {
                System.out.println("Login Successful");
                User u = User.authorization(User_name, Password);
                if (u.getRole().equalsIgnoreCase("SalesPerson")) {
                    while (input.hasNext()) {
                        String key = input.next();
                        //_________________________________________________________________________________________________________________________
                        //Scan function
                        if (key.equalsIgnoreCase("SCAN")) {
                            String op = input.next();
                            String id = input.next();
                            //_______________________________________________
                            //load
                            if (op.equalsIgnoreCase("LOAD")) {
                                Scan s = new Scan();
                               int qty = input.nextInt();
                               if (qty > 0) {
                                Product p = new Product(id, qty, input.next(), LocalDate.parse(input.next()), LocalDate.parse(input.next()), input.nextDouble());
                                boolean flag = s.load(p);
                                if (flag) {
                                    System.out.println("Product " + id + " has been loaded");
                                }
                               }else {
                                    System.out.println("Product " + id + " loaded failed , please check quantity");
                                }
                            }
                            //_______________________________________________
                            //return
                            else if (op.equalsIgnoreCase("RETURN")) {
                                Scan s = new Scan();
                                boolean flag = s.Return(id);
                                if (flag) {
                                    System.out.println("Product " + id + " has been Returned");
                                } else {
                                    System.out.println("Product " + id + " Return operation failed ");
                                }
                            }
                            //_______________________________________________
                            //sell
                            else if (op.equalsIgnoreCase("sell")) {
                                Scan s = new Scan();
                                int quantity = input.nextInt();
                                boolean flag = s.Sold(id, quantity);
                                if (flag) {
                                    String cus_name = input.next();
                                    String address = input.next();
                                    System.out.println("Product " + id + " has been Sold");
                                    DeliveryNote dn = new DeliveryNote(u.user_info(u.username), cus_name, address);
                                    dn.print();
                                } else {
                                    System.out.println("Product " + id + " Sell operation failed");
                                }
                            }
                        }
                            //_________________________________________________________________________________________________________________________
                            //invalid function
                            else if (key.equalsIgnoreCase("logout")) {
                                System.out.println("Goodbye " + u.user_info(u.username).getName());
                                break;
                            }


                        //_________________________________________________________________________________________________________________________
                        //generate report function
                        else if (key.equalsIgnoreCase("Generate_Report")) {
                            String type = input.next();
                            Report r = new Report(type);

                            //_________________________________________________________________________________________________________________________
                            //invalid function
                        } else {
                            input.nextLine();
                        }
                    }
                } else if (u.getRole().equalsIgnoreCase("Manager")){
                    while (input.hasNext()) {
                        String key = input.next();
                        if (key.equalsIgnoreCase("Generate_Report")) {
                            String type = input.next();
                            Report r = new Report(type);
                        } else if (key.equalsIgnoreCase("logout")) {
                            System.out.println("Goodbye " + u.user_info(u.username).getName());
                            break;
                        }
                    }

                }


            }
            else {
                System.out.println("Invalid attempt of login");
                }
        }
    }
}