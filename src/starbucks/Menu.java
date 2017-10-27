/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package starbucks;

import org.omg.CosNaming.NamingContextPackage.NotFound;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author natal
 */
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

    public void edit(Category itemToChange) {
        if (itemToChange instanceof Coffee) {
            //TODO:edit coffee
        } else if (itemToChange instanceof Beverage) {
            //TODO: edit beverage
        } else if (itemToChange instanceof Food) {
            //TODO:edit food
        } else if (itemToChange instanceof Extra) {
            //TODO:edit extra
        } else {
            //TODO:handle weird errors
        }


        /*Boolean edited = false;
        System.out.print("Enter the name of the MenuItem you want to edit: ");
        String name = userInput.nextLine();
        if(!items.isEmpty()){
            for(Category item : items){
                if(item.getName().equals(name)){
                        System.out.println("Press write 'null' where you want to let the value unchanged.");
                        item.edit();
                        edited = true;
                }
            }
        }
        if(!edited){
            System.out.println("-- MenuItem: " + name + " was not found in Menu-List. --");
        } else {
            System.out.println("-- MenuItem " + name + " was successfully edited. --");
        }
        String that = userInput.nextLine();*/
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
