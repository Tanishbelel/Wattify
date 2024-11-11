import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Sma {
    private String name;
    private boolean isOn;

    public Sma(String name, boolean isOn) {
        this.name = name;
        this.isOn = isOn;
    }

    public String getName() {
        return name;
    }

    public boolean isOn() {
        return isOn;
    }

    public void toggleStatus() {
        this.isOn = !this.isOn;
    }

    @Override
    public String toString() {
        return name + " is currently " + (isOn ? "On" : "Off");
    }
}

public class SmartControl {
    private Map<String, Sma> appliances = new HashMap<>();

    public SmartControl() {
        appliances.put("living-room-lights", new Sma("Living Room Lights", true));
        appliances.put("kitchen-appliances", new Sma("Kitchen Appliances", false));
        appliances.put("ac-unit", new Sma("AC Unit", true));
    }

    public void displayAppliances() {
        appliances.values().forEach(System.out::println);
    }

    public void toggleAppliance(String applianceName) {
        Sma appliance = appliances.get(applianceName);
        if (appliance != null) {
            appliance.toggleStatus();
            System.out.println(applianceName + " toggled. " + appliance);
        } else {
            System.out.println("Appliance not found.");
        }
    }

    public static void main(String[] args) {
        SmartControl control = new SmartControl();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Smart Control");
        while (true) {
            System.out.println("\nAvailable appliances:");
            control.displayAppliances();
            System.out.print("\nEnter appliance name to toggle or 'exit' to quit: ");
            String input = scanner.nextLine().trim();

            if ("exit".equalsIgnoreCase(input)) {
                break;
            }

            control.toggleAppliance(input);
        }

        scanner.close();
        System.out.println("Smart Control session ended.");
    }
}
