package starbucks_fx;

public class DataHolder {
    public static String name;
    public static double price;
    public static String priceString;
    public static String ingredients;
    public static String optional;
    public static boolean hot;
    public static boolean ok;

    public static void initVars(){
        name = "";
        price = 0;
        priceString = "";
        ingredients = "";
        optional = "";
        hot = false;
        ok = false;
    }
}
