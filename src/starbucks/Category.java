/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package starbucks;

public interface Category {

    public String toString();

    public String getName();

    public double getPrice();

    public void setName(String name);

    public void setPrice(double price);
}
