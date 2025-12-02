package vansale;

import java.io.FileNotFoundException;

class SalesPerson extends User {
    VanSalesPerson vanSalesPerson;
    public SalesPerson(String username, String password, String role) {
        super(username, password, role);
    }
    public VanSalesPerson getVanSalesPerson() throws FileNotFoundException {
        return user_info(username);
    }
}