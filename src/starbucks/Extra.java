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
public class Extra implements Category {

    private String name;
    private double price;
    Scanner userInput = new Scanner(System.in);    
    
    public Extra(String name, double price){
        this.name = name;
        this.price = price;
    }

    @Override
    public void print() {
        String format = "%-20s%2s%5s";
        System.out.println(String.format(format, name, "  ", price));
    }

    @Override
    public String toString() {
        String concat = name + "¦" + price;
        return concat;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getPrice() { return price; }

    @Override
    public void edit() {

    }
    
}
