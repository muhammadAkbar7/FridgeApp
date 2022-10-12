import java.util.*;
import java.io.*;

public class Database {
    private final Map<Integer, Item> map;

    public Database () {
        this.map = new HashMap<Integer, Item>();
    }

    public boolean add (int barcode, Item newItem) {
        if (this.map.containsKey(barcode)) { // check if i can switch this.map to just map
            return false;
        } else {
            this.map.put(barcode, newItem); // check if i can switch this.map to just map
            return true;
        }
    }

    public boolean remove (int barcode) {
        if (this.map.containsKey(barcode)) {
            map.remove(barcode);
            System.out.println("This item has been removed!");
            return true;
        } else {
            System.out.println("There dosen't seem to be the item you were looking for in the fridge. Please try again."); // might need to test to make a try again feature
            return false;
        }
    }

    public boolean check (int barcode) {
        return this.map.containsKey(barcode);
    }

    public boolean addQuantity (int barcode, int quantity) {
        if (!this.map.containsKey(barcode)) {
            return false;
        } else {
            this.map.get(barcode).ItemQuantityAdd(quantity);
            return true;
        }
    }

    public boolean addDate (int barcode, Date exprDate) {
        if (!this.map.containsKey(barcode)) {
            return false;
        } else {
            this.map.get(barcode).ItemDateAdd(exprDate);
            return true;
        }
    }

    public void printIn (int barcode) {
        if (!this.map.containsKey(barcode)){
            System.out.println("No item found.");
        }
        else {
            System.out.println("Item's Barcode: " + barcode +
                    "\nItem's Brand Name: " + this.map.get(barcode).getBrandName() +
                    "\nItem's Food Name: " + this.map.get(barcode).getFoodName() +
                    "\nItems' Quantities: " + this.map.get(barcode).getQuantityList() +
                    "\nItem's Expiration Dates: " + this.map.get(barcode).getExprList());
        }
    }

    public void printAll () {
        for (Integer iterator: map.keySet()) {
            System.out.println("Item's Barcode: " + iterator +
                    "\nItem's Brand Name: " + this.map.get(iterator).getBrandName() +
                    "\nItem's Food Name: " + this.map.get(iterator).getFoodName() +
                    "\nItems' Quantities: " + this.map.get(iterator).getQuantityList() +
                    "\nItem's Expiration Dates: " + this.map.get(iterator).getExprList());
        }
    }

    public void writer(String fileName) throws IOException {
        FileWriter fileWriter = new FileWriter(fileName);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        for (Integer iterator: map.keySet()) {
            printWriter.print("Item's Barcode: " + iterator +
                    "\nItem's Brand Name: " + this.map.get(iterator).getBrandName() +
                    "\nItem's Food Name: " + this.map.get(iterator).getFoodName() +
                    "\nItems' Quantities: " + this.map.get(iterator).getQuantityList() +
                    "\nItem's Expiration Dates: " + this.map.get(iterator).getExprList());
            printWriter.close();
        }
    }

    public void itemExpr () {
        Date objDate = new Date();
        Scanner scan = new Scanner(System.in);
        for (Integer iterator: map.keySet()) {
            ArrayList exprListDates = this.map.get(iterator).getExprListNotString();
            for (int i = 0; i < exprListDates.size(); i++) {
                if(objDate.compareTo((Date) exprListDates.get(i)) > 0) {
                    System.out.println("You have the following expired item: ");
                    printIn(iterator);
                    System.out.println("Would you like to remove it? ('y' for yes)");
                    String response = scan.nextLine();
                    if (response.equalsIgnoreCase("y")) {
                        remove(iterator);
                    }
                }
            }
        }
    }



    // need to add item & barcode

}
