/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package starbucks;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author natal
 */
public class Menu {
    
    public static ArrayList<Category> items = new ArrayList<>();
    private static final Menu menu = new Menu();
    private MenuItemFactory factory = MenuItemFactory.getInstance();
    Scanner userInput = new Scanner(System.in);
    
    private Menu(){
        
    }
    
    public void fillItems(ArrayList<ArrayList<String>> products){
        String name;
        Double price;
        String ingredients;
        String optional;
        for(ArrayList<String> product : products){
            name = product.get(0);
            ingredients = product.get(2);
            optional = product.get(3);
            try{
                price = Double.parseDouble(product.get(1));
                items.add(factory.create(name, price, ingredients, optional));
            } catch(NumberFormatException e){
                System.out.println("-- Product with name: " + product.get(0) + " could not be added to Menu. --");
            }
        }
    }
    
    public ArrayList<String> toStringArray(){
        ArrayList<String> file = new ArrayList<>();
        for(Category item: items){
            file.add(item.toString());
        }
        return file;
    }
    
    public void print(){
        if(!items.isEmpty()){
            String isInMenu = "";
            for(Category item : items){
                if(item instanceof Coffee){
                    isInMenu += "C";
                }
                if(item instanceof Beverage){
                    isInMenu += "B";
                }
                if(item instanceof Extra){
                    isInMenu += "E";
                }
                if(item instanceof Food){
                    isInMenu += "F";
                }
            }   
            System.out.println("******************************************************************************************");
            System.out.println("*                                   Starbucks Menu                                       *");
            System.out.println("******************************************************************************************");
            if(isInMenu.contains("C")){
                System.out.println("------------------------------------------------------------------------------------------");
                System.out.println("- Coffees                                                                                -");
                System.out.println("------------------------------------------------------------------------------------------");
                System.out.println("");
                String format = "%-22s%-7s%11s";
                System.out.println(String.format(format, "Name", "Price", "Ingredients"));
                System.out.println("");        
                for(Category item : items){
                    if(item instanceof Coffee){
                        item.print();
                    }
                }
            }
            if(isInMenu.contains("B")){
                System.out.println("");        
                System.out.println("------------------------------------------------------------------------------------------");
                System.out.println("- Beverage                                                                               -");
                System.out.println("------------------------------------------------------------------------------------------");
                System.out.println("");      
                String format = "%-22s%-7s%-50s%5s";
                System.out.println(String.format(format, "Name", "Price", "Ingredients", "Temperature"));            
                System.out.println("");        
                for(Category item : items){
                    if(item instanceof Beverage){
                        item.print();
                    }
                }
            }
            if(isInMenu.contains("E")){
                System.out.println("");        
                System.out.println("------------------------------------------------------------------------------------------");
                System.out.println("- Extra                                                                                  -");
                System.out.println("------------------------------------------------------------------------------------------");
                System.out.println("");        
                String format = "%-22s%5s";
                System.out.println(String.format(format, "Name", "Price"));             
                System.out.println("");        
                for(Category item : items){
                    if(item instanceof Extra){
                        item.print();
                    }
                }
            }
            if(isInMenu.contains("F")){
                System.out.println("");        
                System.out.println("------------------------------------------------------------------------------------------");
                System.out.println("- Food                                                                                   -");
                System.out.println("------------------------------------------------------------------------------------------");
                System.out.println("");      
                String format = "%-22s%-7s%-50s%12s";
                System.out.println(String.format(format, "Name", "Price", "Ingredients", "Dietary Info"));             
                System.out.println("");        
                for(Category item : items){
                    if(item instanceof Food){
                        item.print();
                    }
                }
            }
            System.out.println("");
            System.out.println("******************************************************************************************");
        } else {
            System.out.println("");
            System.out.println("-- No product in Starbucks menu yet. --");
        }
        String that = userInput.nextLine();
        
    }
    
    public void add(){
        Boolean nameOk   = true;
        Boolean doubleOk = false;
        double price     = 0;
        System.out.print("Name:                     ");
        String name = userInput.nextLine();
        for(Category item : items){
            if(item.getName().equals(name)){
                nameOk = false;
            }
        }
        if(nameOk){
            while(!doubleOk){
                try{
                    System.out.print("Price:                    ");
                    String priceStr = userInput.nextLine();
                    price = Double.parseDouble(priceStr);
                    doubleOk = true;
                } catch(NumberFormatException e){
                    System.out.println("-- Please enter a valid Price. --");
                }
            }
            System.out.println("-- Opional values, if you want to leave a value enter 'null' --");
            System.out.print("Ingredients:              ");
            String ingredients = userInput.nextLine();
            if(ingredients.equals("null")){
                ingredients = null;
            }
            System.out.print("Dietary Info or hot/cold: ");
            String optional = userInput.nextLine();
            switch (optional) {
                case "hot": 
                    optional = "true";
                    break;
                case "cold":
                    optional = "false";
                    break;
                case "null":
                    optional = null;
                    break;
            }
            Category item = factory.create(name, price, ingredients, optional);
            if(item != null){
                items.add(item);
                System.out.println("-- The MenuItem was added to the Menu. --");
            }
        } else {
            System.out.println("-- In the menu there is already a MenuItem with name: " + name + ". --");
        }
        String that = userInput.nextLine();
    }
    
    public void edit(){
        Boolean edited = false;
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
        String that = userInput.nextLine();
    }
    
    public void remove(){
        Category toDelete = null;
        Boolean found = false;
        System.out.print("Enter the name of the MenuItem you want to delete: ");
        String name = userInput.nextLine();
        if(!items.isEmpty()){
            for(Category item : items){
                if(item.getName().equals(name)){
                    toDelete = item;
                    found = true;
                }
            }
        }
        if(!found){
            System.out.println("-- MenuItem: " + name + " was not found in Menu-List. --");
        } else {
            items.remove(toDelete);
            System.out.println("-- MenuItem: " + name + " successfully deleted. --");
        }
        String that = userInput.nextLine();
    }
    
    public static Menu getInstance(){
        if(menu == null) {
            Menu menu = new Menu();
        }
        return menu;
    }
}
