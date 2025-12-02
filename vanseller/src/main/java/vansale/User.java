package vansale;

import vansale.SalesPerson;
import vansale.DriverProfile;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;
import vansale.Manager;

// Parent Class
public class User {
    protected String username;
    protected String password;
    protected String role;

    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
    public static boolean login(String username, String password) throws IOException {
        User u = authorization(username,password);
        if (u != null) {
            return true;
        }
        return false;
    }

    // Added 'throws IOException' to handle file errors without try-catch
    public static User authorization(String u, String p ) throws IOException {
        File file = new File ("login.txt");
        Scanner reader = new Scanner(file);
        String data;

        // Read file line by line
        while (reader.hasNext()) {
           data = reader.next();

            // Check for USER line
            if (data.equals("USER")) {
                String fName = reader.next();
                String fPass = reader.next();
                String fRole = reader.next();

                // Validate credentials
                if (u.equals(fName) && p.equals(fPass)) {
                    reader.close(); // Close file

                    // Return correct object
                    if (fRole.equalsIgnoreCase("MANAGER")) {
                         return new Manager(u, p, fRole);

                    } else if (fRole.equalsIgnoreCase("SalesPerson")) {
                        return new SalesPerson(u, p, fRole);
                    }
                }
            }

        }
        reader.close();
        System.out.println("User not found");
        return null;
    }
    public DriverProfile user_info(String username) throws FileNotFoundException {
        Scanner input = new Scanner(new File("user_info.txt"));
        while(input.hasNext()){
            String userName = input.next();
            if(userName.equalsIgnoreCase(username)){
                return new DriverProfile(input.next(), input.next());
            }
            else {
                input.nextLine();
            }
        }
        return null;
    }

    public String getRole() {
        return role;
    }
}