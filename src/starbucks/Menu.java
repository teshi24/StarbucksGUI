/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package starbucks;

import java.util.ArrayList;

public class Menu {

    public static ArrayList<Category> items = new ArrayList<>();
    private static final Menu menu = new Menu();
    private MenuItemFactory factory = MenuItemFactory.getInstance();

    private Menu() {

    }

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

    public static ArrayList<String> toStringArray() {
        ArrayList<String> file = new ArrayList<>();
        for (Category item : items) {
            file.add(item.toString());
        }
        return file;
    }

    public void remove(Category toDelete) {
        try {
            items.remove(toDelete);
        } catch (Exception e) {
            return;
            //TODO: add catch Clause
        }
    }

    public static Menu getInstance() {
        if (menu == null) {
            Menu menu = new Menu();
        }
        return menu;
    }
}
