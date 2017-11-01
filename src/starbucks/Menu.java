package starbucks;

import java.util.ArrayList;

public class Menu {

    private static final Menu menu = new Menu();
    private MenuItemFactory factory = MenuItemFactory.getInstance();
    public static ArrayList<Category> items = new ArrayList<>();

    private Menu() {
    }

    /**
     * get items from file and set them into the static array list 'items'
     *
     * @param products
     */
    public void fillItems(ArrayList<ArrayList<String>> products) {
        items = new ArrayList<>();
        String name;
        Double price;
        String ingredients;
        String optional;
        for (ArrayList<String> product : products) {
            name = product.get(0);
            ingredients = product.get(2);
            optional = product.get(3);
            try {
                price = Double.parseDouble(product.get(1));
                items.add(factory.create(name, price, ingredients, optional));
            } catch (NumberFormatException e) {
                System.out.println("-- Product with name: " + product.get(0) + " could not be added to Menu. --");
            }
        }
    }

    /**
     * prepare array list to write the file
     *
     * @return
     */
    public static ArrayList<String> toStringArray() {
        ArrayList<String> file = new ArrayList<>();
        for (Category item : items) {
            file.add(item.toString());
        }
        return file;
    }

    /**
     * @param toDelete
     */
    public void remove(Category toDelete) {
        items.remove(toDelete);
    }

    /**
     * @return
     */
    public static Menu getInstance() {
        return menu;
    }
}