package org.example;

import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public  static Scanner input = new Scanner(System.in);
    public  static FileManager file = new FileManager();
    public static ShoppingCart cart = new ShoppingCart();
    public static void main(String[] args) {
        List<Product> products = FileManager.getProducts();

        while (true){
            System.out.println("\033[38;2;255;105;180m~~Store Menu~~\033[0m");
            System.out.println("1. Display Products");
            System.out.println("2. Display Cart");
            System.out.println("3. EXIT");

            String choice =  input.nextLine();
            switch (choice){
                case "1" -> productScreen(products);
               case "2" -> showCart();
               case "3" -> System.exit(0);
                    default -> System.out.println("Invalid Option.");

            }
        }
    }
    public static void productScreen(List<Product> products){
        for (Product p : products) {
            System.out.println(p.toString());
        }
        while(true) {
            System.out.println("\033[38;2;255;105;180mOption:\033[0m");
            System.out.println("1. Add to Cart");
            System.out.println("2. Search");
            System.out.println("3. Return to Store Menu");
            String choice = input.nextLine();

            if (choice.equals("1")) {
                System.out.println("Enter ID to add: ");
                String id = input.nextLine();

                boolean found = false;
                for (Product p : products) {
                    if (p.getId().equalsIgnoreCase(id)) {
                        cart.add(p);
                        found = true;
                        break;
                    }
                }
                if (!found) System.out.println("Product not found.");

            } else if (choice.equals("2")) {
                System.out.println("\033[38;2;255;105;180mSearch by: \033[0m");
                System.out.println("1. Name");
                System.out.println("2. Price");
                System.out.println("3. Department");
                String searchChoice = input.nextLine();

                System.out.print("\033[38;2;255;105;180mEnter search term: \033[0m");
                String searchTerm = input.nextLine();

                for (Product p : products) {
                    boolean match = false;
                    if (searchChoice.equals("1") && p.getName().toLowerCase().contains(searchTerm.toLowerCase())) {
                        match = true;
                    } else if (searchChoice.equals("2") && String.valueOf(p.getPrice()).contains(searchTerm)) {
                        match = true;
                    } else if (searchChoice.equals("3") && p.getCategory().toLowerCase().contains(searchTerm.toLowerCase())) {
                        match = true;
                    }

                    if (match) {
                        System.out.println(p);
                    }
                }
            } else if (choice.equals("3")) {
                return;
            }else{
                System.out.println("Invalid Option, Please Try Again!");
            }
        }


    }
    public static void showCart() {
        while (true) {
            System.out.println("\033[38;2;255;105;180m~~ Your Cart ~~\033[0m");
            List<Product> items = cart.getCart();

            if (items.isEmpty()) {
                System.out.println("Your cart is empty.");
            } else {
                for (Product p : items) {
                    System.out.println(p);
                }
                System.out.printf("Total: $%.2f\n", cart.getTotal());
            }

            System.out.println("\033[38;2;255;105;180mOptions:\033[0m");
            System.out.println("1. Check Out");
            System.out.println("2. Remove Product");
            System.out.println("3. Back to Menu");
            String choice = input.nextLine();

            if (choice.equals("1")) {
                System.out.println("Thank you for your purchase!");
                cart.clear();
                return;
            } else if (choice.equals("2")) {
                System.out.print("Enter Product ID to remove: ");
                String id = input.nextLine();
                cart.remove(id);
                System.out.println("Item removed if it existed.");
            } else if (choice.equals("3")) {
                return;
            }
        }
    }


}
