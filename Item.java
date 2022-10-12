import java.util.Date;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Item {
    private final int barcode;
    private final String brand_name;
    private final String food_name;
    private ArrayList<Integer> quantityList /*= new ArrayList<>()*/;
    private ArrayList<Date> exprDateList;

    public Item(int barcode, String brand_name, String food_name, ArrayList<Integer> quantityList, ArrayList<Date> exprDateList) {
        this.barcode = barcode;
        this.brand_name = brand_name;
        this.food_name = food_name;
        this.quantityList = quantityList;
//        this.quantityList = new ArrayList<>();
        this.exprDateList = exprDateList;
    }

    public String getBrandName () {
        return brand_name;
    }

    public String getFoodName () {
        return food_name;
    }

    public String getQuantityList () {
        String listString = quantityList.stream().map(Object::toString).collect(Collectors.joining(", "));
        return listString;
    }

    public String getExprList () {
        String listString = exprDateList.stream().map(Object::toString).collect(Collectors.joining(", "));
        return listString;
    }

    public ArrayList getExprListNotString () {
        return exprDateList;
    }

    public boolean ItemQuantityAdd (int quantity) {
        this.quantityList.add(quantity);
        return true;
    }

    public boolean ItemDateAdd (Date exprDate) {
        this.exprDateList.add(exprDate);
        return true;
    }


}
