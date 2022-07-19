import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;


// Assumptions: all user input is free of errors
public class Main {
    public static void main (String[] args) {
        Scanner scan = new Scanner(System.in);
        Database fridge = new Database();
        boolean quit = false;
        while (quit == false) {
            System.out.println("Hi, welcome to FridgeAppV1. Please type the keyword of what would you like to do.");
            System.out.println("Would you like to input an item (input), remove an item (remove), print out current items in the fridge (print), or quit the program (quit)?");
            String reader = scan.nextLine();
            if (reader.equalsIgnoreCase("input")) {
                System.out.println("What is the item's barcode (ex: 027000382493, Hunt's Ketchup)");
                int barcodeInitial =  Integer.parseInt(scan.nextLine());
                if (fridge.check(barcodeInitial) == false) { // this means that the user would like to create a new item
                    System.out.println("You are creating a new item."); //; might need to go back and add switch statement to get a user out of the pathway if they didn't intend to create a new item
                    System.out.println("What is the item's brand name? (ex: Hunt's)");
                    String brand_name = scan.nextLine();
                    System.out.println("What is the item's name? (ex: Ketchup)");
                    String food_name = scan.nextLine();
                    System.out.println("What is the item's quantity? (ex: 1)");
                    int quantity =  Integer.parseInt(scan.nextLine());
                    ArrayList<Integer> firstEntry = new ArrayList<Integer>();
                    firstEntry.add(quantity);
                    System.out.println("What is the item's expiration date? (ex: 01-01-2022 (dd-MM-yyyy)");
                    String exprDateStr = scan.nextLine();
                    try {
                        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                        Date exprDate = formatter.parse(exprDateStr);
                        ArrayList<Date> firstExprDate = new ArrayList<Date>();
                        firstExprDate.add(exprDate);
                        Item newItem = new Item(barcodeInitial, brand_name, food_name, firstEntry, firstExprDate); // * how to add to arraylist
                        fridge.add(barcodeInitial, newItem);
                    } catch (java.text.ParseException e) {
                        System.out.print("ParseException. Please input the correct format for date (dd-MM-yyyy)"); //might need to figure out how to input stuff again if incorrect format
                    }
                } else {// this means the user would like to add to an existing item, just need quantity & date, will
                    System.out.println("You are adding to an existing item.");
                    System.out.println("What is the item's quantity? (ex: 1)");
                    int quantity =  Integer.parseInt(scan.nextLine());
                    boolean addNewQ = fridge.addQuantity(barcodeInitial, quantity);
                    System.out.println("What is the item's expiration date? (ex: 01-01-2022 (dd-MM-yyyy)");
                    String exprDateStr = scan.nextLine();
                    try {
                        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                        Date exprDate = formatter.parse(exprDateStr);
                        boolean addNewD = fridge.addDate(barcodeInitial, exprDate);
                    } catch (java.text.ParseException e) {
                        System.out.print("ParseException. Please input the correct format for date (dd-MM-yyyy)"); //might need to figure out how to input stuff again if incorrect format
                    }
                }
            } else if (reader.equalsIgnoreCase("remove")) {
                System.out.println("Which item would you like to remove (please enter a barcode number)");
                int barcode =  Integer.parseInt(scan.nextLine());
                fridge.remove(barcode);
            } else if (reader.equalsIgnoreCase("print")) {
                System.out.println("Would you like to print an individual (in) item or all the items in your fridge (all)");
                String itemChoice = scan.nextLine(); // need to add if for in & all
                System.out.println("What is the item's barcode (ex: 027000382493, Hunt's Ketchup)");
                int barcodeInitial =  Integer.parseInt(scan.nextLine());
                fridge.printIn(barcodeInitial);
            } else if (reader.equalsIgnoreCase("quit")) {
                quit = true;
            } else {
                System.out.println("The response was invalid. Please choose one of the keywords available");
            }
                System.out.println("");
        }
    }
}
