import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class ProductWriter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> products = new ArrayList<>();
        boolean done = false;

        while (!done) {
            System.out.println("Enter the product's details:");

            String id = SafeInput.getRegExString(scanner, "Enter ID (a String)", "\\d+");
            String name = SafeInput.getNonZeroLenString(scanner, "Enter Name");
            String description = SafeInput.getNonZeroLenString(scanner, "Enter Description (a short sentence)");
            double cost = SafeInput.getRangedDouble(scanner, "Enter Cost", 0, Integer.MAX_VALUE);

            String record = id + ", " + name + ", " + description + ", " + cost;
            products.add(record);

            done = !SafeInput.getYNConfirm(scanner, "Do you want to add another product?");
        }

        String filename = SafeInput.getNonZeroLenString(scanner, "Enter the name of the file to save (e.g., Products.txt)");

        try (FileWriter writer = new FileWriter(filename)) {
            for (String product : products) {
                writer.write(product + "\n");
            }
            System.out.println("File saved successfully!");
        } catch (IOException e) {
            System.out.println("An error occurred while saving the file: " + e.getMessage());
        }

        scanner.close();
    }
}
