package vansale;

import vansale.DriverProfile;
import java.io.FileNotFoundException;

class SalesPerson extends User {
    DriverProfile DriverProfile;
    public SalesPerson(String username, String password, String role) {
        super(username, password, role);
    }
    public DriverProfile getDriverProfile() throws FileNotFoundException {
        return user_info(username);
    }
}