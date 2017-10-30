/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package starbucks;

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

    public Category edit(Category item, String name, Double price, String ingredients, String optional){
        item.setName(name);
        item.setPrice(price);
        if (item instanceof Coffee) {
            ((Coffee) item).setIngredients(ingredients);
        }
        if (item instanceof Beverage) {
            ((Beverage) item).setIngredients(ingredients);
            boolean hot = Boolean.parseBoolean(optional);
            ((Beverage) item).setHot(hot);
        }
        if (item instanceof Food) {
            ((Food) item).setIngredients(ingredients);
            ((Food) item).setDietaryInfo(optional);
        }
        return item;
    }
    
    public static MenuItemFactory getInstance(){
        return factory;
    }
    
}
