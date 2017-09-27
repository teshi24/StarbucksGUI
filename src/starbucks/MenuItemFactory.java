/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package starbucks;

/**
 *
 * @author natal
 */
public final class MenuItemFactory {
    
    private static MenuItemFactory factory = new MenuItemFactory();
    
    private MenuItemFactory(){
        
    }
    
    public Category create(String name, double price, String ingredients, String optional){
        if(ingredients == null && optional == null){
            return new Extra(name, price);
        } else if(optional == null){
            return new Coffee(name, price, ingredients);
        } else if(optional.equals("true") || optional.equals("false")){
            Boolean hot;
            hot = Boolean.parseBoolean(optional);
            return new Beverage(name, price, ingredients, hot);
        } else if(name != null){
            return new Food(name, price, ingredients, optional);
        }
        return null;
    }
    
    public static MenuItemFactory getInstance(){
        return factory;
    }
    
}
