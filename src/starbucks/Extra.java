/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package starbucks;

public class Extra implements Category {

    private String name;
    private double price;

    public Extra(String name, double price) {
        this.name = name;
        this.price = price;
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
    public double getPrice() {
        return price;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setPrice(double price) {
        this.price = price;
    }
}
