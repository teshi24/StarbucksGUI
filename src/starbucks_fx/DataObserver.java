package starbucks_fx;

import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

/**
 * abstract observer for DataHolder
 */
abstract public class DataObserver {
    protected DataHolder dh;

    protected TextField name, price, ingredients, optional;
    protected HBox heatContainer;
    protected RadioButton hot;
    protected RadioButton cold;

    public void update() {
        name.setText(dh.getName());
        price.setText(dh.getPriceString());
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
