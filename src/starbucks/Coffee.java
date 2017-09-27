/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package starbucks;

import java.util.Scanner;

/**
 *
 * @author natal
 */
public class Coffee implements Category{
    
    private String name;
    private double price;
    private String ingredients;
    Scanner userInput = new Scanner(System.in);    
    
    public Coffee(String name, double price, String ingredients){
        this.name = name;
        this.price = price;
        this.ingredients = ingredients;
    }

    @Override
    public void print() {
        String format = "%-20s%2s%5s%2s%-50s";
        System.out.println(String.format(format, name, "  ", price, " ", ingredients));
    }

    @Override
    public String toString() {
        String concat = name + "¦" + price + "¦" + ingredients;
        return concat;
    }
    
    @Override
    public String getName() {
        return name;
    }

    @Override
    public void edit() {
        String input;
        String format = "%-13s%-50s%-18s";
        System.out.print(String.format(format, "Name: ", name, " New name: "));
        input = userInput.nextLine();
        if(!input.equals("null")){
            name = input;
        }
        Boolean pOk = false;
        while(!pOk){
            System.out.print(String.format(format, "Price: ", price, " New Price: "));
            input = userInput.nextLine();
            if(!input.equals("null")){
                try {
                    price = Double.parseDouble(input);
                    pOk = true;
                } catch (NumberFormatException e){
                    System.out.println("-- Price could not be changed. Input was not nummeric. --");
                }
            } else {
                pOk = true;
            }            
        }
        System.out.print(String.format(format, "Ingredients: ", ingredients, " New ingredients: "));
        input = userInput.nextLine();
        if(!input.equals("null")){
            ingredients = input;
        }
    }


}
