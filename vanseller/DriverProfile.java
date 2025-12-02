package vansale;

public class DriverProfile {
    private String vanId;
    private String name;

    // Constructor
    public DriverProfile(String vanId, String name) {
        this.vanId = vanId;
        this.name = name;
    }

    // Getters
    public String getVanId() {
        return vanId;
    }

    public String getName() {
        return name;
    }

    // Optional: simple details method
    public String getDetails() {
        return "Van ID: " + vanId + "\nDriver Name: " + name;
    }
}
