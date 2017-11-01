package starbucks_fx;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import starbucks.Category;
import starbucks.Menu;

/**
 * abstract observer for DataHolder
 */
abstract public class DataObserver {
    protected DataHolder dh;
    protected Stage primaryStage;
    protected GridPane form;

    protected Label nameL, priceL, ingredientsL, optionalL;
    protected TextField name, price, ingredients, optional;
    protected HBox heatContainer;
    protected ToggleGroup heat;
    protected RadioButton hot, cold;
    protected Button sendValues;

    protected void getCoffeeAttributes() {
        initIngredients();
        form.add(ingredientsL, 0, 2);
        form.add(ingredients, 1, 2);
        form.add(sendValues, 1, 3);

        heat = null;
        optional = null;
    }

    protected void getFoodAttributes() {
        initIngredients();
        initOptional();

        form.add(ingredientsL, 0, 2);
        form.add(ingredients, 1, 2);
        form.add(optionalL, 0, 3);
        form.add(optional, 1, 3);
        form.add(sendValues, 1, 4);

        heat = null;
    }

    protected void getBeverageAttributes() {
        initIngredients();
        initHeath();

        form.add(ingredientsL, 0, 2);
        form.add(ingredients, 1, 2);
        form.add(optionalL, 0, 3);
        form.add(heatContainer, 1, 3);
        form.add(sendValues, 1, 4);
    }

    protected void getExtraAttributes() {
        form.add(sendValues, 1, 2);

        heat = null;
        ingredients = null;
        optional = null;
    }

    protected void initName() {
        nameL = new Label("name:");
        name = new TextField();
        name.setText(dh.getName());
        name.textProperty().addListener((observable, oldValue, newValue) -> {
            String text = name.getText().toString();
            if (!text.contains("|") && !text.contains("¦")) {
                dh.setName(text);
            } else {
                name.setText(dh.getName());
                ErrorMsg.addErrorMsg(primaryStage, ErrorMsg.getCharNotAllowed());
            }
        });
    }

    protected void initPrice() {
        priceL = new Label("price:");
        price = new TextField();
        price.setText(dh.getPriceString());
        price.setEditable(false);
        price.setOnMouseClicked(e -> {
            Price p = new Price(primaryStage);
            p.enterPrice(dh);
        });
    }

    protected void initIngredients() {
        ingredientsL = new Label("ingredients:");
        ingredients = new TextField();
        ingredients.textProperty().addListener((observable, oldValue, newValue) -> {
            String text = ingredients.getText().toString();
            if (!text.contains("|") && !text.contains("¦")) {
                dh.setIngredients(text);
            } else {
                ingredients.setText(dh.getIngredients());
                ErrorMsg.addErrorMsg(primaryStage, ErrorMsg.getCharNotAllowed());
            }
        });
    }

    protected void initOptional() {
        optionalL = new Label("dietary info:");
        optional = new TextField();
        optional.textProperty().addListener((observable, oldValue, newValue) -> {
            String text = optional.getText().toString();
            if (!text.contains("|") && !text.contains("¦")) {
                dh.setOptional(text);
            } else {
                optional.setText(dh.getOptional());
                ErrorMsg.addErrorMsg(primaryStage, ErrorMsg.getCharNotAllowed());
            }
        });
    }

    protected void initHeath() {
        optionalL = new Label("temperature:");
        optional = new TextField();
        heat = new ToggleGroup();
        hot = new RadioButton("hot");
        hot.setToggleGroup(heat);
        cold = new RadioButton("cold");
        cold.setToggleGroup(heat);

        heat.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                if (cold.isSelected()) {
                    dh.setHot(false);
                } else {
                    dh.setHot(true);
                }
            }
        });

        heatContainer = new HBox(hot, cold);
        heatContainer.setSpacing(10);
    }

    /**
     * call this.sendValues(category, mes) in implementation
     *
     * @param category
     */
    abstract protected void sendValues(int category);

    /**
     * check values and send them to MenuItemFactory to finally create the menu items
     */
    protected String sendValues(int category, String mes) {
        boolean ok = true;
        boolean nameOk = true;
        mes = "Please add the required information to your product: " + System.lineSeparator();
        // check inserted values
        if (dh.getName() == null || dh.getName().equals("")) {
            mes += "name" + System.lineSeparator();
            ok = false;
        } else {
            for (Category c : Menu.items) {
                if (c.getName().equals(dh.getName())) {
                    mes = "This name does already exist. Please enter another.";
                    ok = false;
                    nameOk = false;
                }
            }
        }
        if (nameOk) {
            if (dh.getPriceString() == null || dh.getPrice() == 0) {
                mes += "price" + System.lineSeparator();
                ok = false;
            }
            if (category < 3) {
                if (dh.getIngredients() == null || dh.getIngredients().equals("")) {
                    mes += "ingredients" + System.lineSeparator();
                    ok = false;
                }
                if (category == 1) {
                    if (dh.getOptional() == null || dh.getIngredients().equals("")) {
                        mes += "dietary info";
                        ok = false;
                    }
                }
            }
            if (ok) {
                if (ingredients == null) {
                    dh.setIngredients(null);
                }
                if (optional == null) {
                    dh.setOptional(null);
                }
                if (heat != null) {
                    if (dh.isHot()) {
                        dh.setOptional("true");
                    } else {
                        dh.setOptional("false");
                    }
                }
            }
        }
        if (ok) {
            return null;
        }
        return mes;
    }

    public void update() {
        if (name != null) {
            name.setText(dh.getName());
        }
        if (price != null) {
            price.setText(dh.getPriceString());
        }
        if (ingredients != null) {
            ingredients.setText(dh.getIngredients());
        }
        if (optional != null) {
            optional.setText(dh.getOptional());
        }
        if (heatContainer != null) {
            if (dh.isHot()) {
                hot.setSelected(true);
            } else {
                cold.setSelected(true);
            }
        }
    }
}
