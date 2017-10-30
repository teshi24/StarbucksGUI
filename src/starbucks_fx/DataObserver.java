package starbucks_fx;

import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * abstract observer for DataHolder
 */
abstract public class DataObserver {
    protected DataHolder dh;
    protected Stage primaryStage;

    protected Label nameL, priceL, ingredientsL, optionalL;
    protected TextField name, price, ingredients, optional;
    protected HBox heatContainer;
    protected ToggleGroup heat;
    protected RadioButton hot, cold;

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
