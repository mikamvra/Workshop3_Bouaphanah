package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    public static List<Product> getProducts(){
        List<Product> products = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/products.csv"));

            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");

                String id = parts[0];
                String name = parts[1];
                double price = Double.parseDouble(parts[2]);
                String category = parts[3];

                Product product = new Product(id, name, price, category);
                products.add(product);
            }

            reader.close();

        } catch (IOException e) {
            System.out.println("There was a problem reading the products file.");
        }
        catch(Exception ex){
            System.out.println("Something went from with the file.");
        }

        return products;
    }
    public static void writeProduct(Product product){
        try{
            File file = new File("src/main/resources/products.csv");
            FileWriter fileWriter = new FileWriter(file, true);
            if (file.length() > 0) {
                fileWriter.write(System.lineSeparator());
            }

            fileWriter.write(String.format("%s|%s|%f|%s", product.getId(), product.getName(),
                    product.getPrice(), product.getCategory()));

            fileWriter.close();
        }
        catch(IOException ex){
            System.out.println("Error writing to file.");
        }
    }
}
