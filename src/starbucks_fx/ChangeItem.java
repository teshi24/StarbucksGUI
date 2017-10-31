package starbucks_fx;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import starbucks.*;
import starbucks.Menu;

import java.io.IOException;

/**
 * GUI: edit dialog
 */
public class ChangeItem extends DataObserver {
    private Modify modify;
    private Stage editStage;
    private BorderPane layout;

    private Category item;
    private Label editTitle;

    /**
     * @param dh
     * @param primaryStage
     * @param layout
     * @param modify
     */
    public ChangeItem(DataHolder dh, Stage primaryStage, BorderPane layout, Modify modify) {
        this.dh = dh;
        this.dh.attach(this);
        this.primaryStage = primaryStage;

        this.layout = layout;
        this.modify = modify;
    }

    /**
     * @param item
     */
    public void showEditStage(Category item) {
        this.item = item;

        Stage editStage = new Stage();
        editStage.getIcons().add(new Image("resources/images/edit.png"));
        editStage.setTitle("Edit");
        editStage.initOwner(primaryStage);
        editStage.initModality(Modality.WINDOW_MODAL);
        editStage.setScene(new Scene(getEditItemView(), 290, 210));
        editStage.setResizable(false);
        this.editStage = editStage;
        editStage.show();
    }

    /**
     * @return
     */
    private VBox getEditItemView() {
        dh.initVars();

        VBox box = new VBox();
        box.getStylesheets().add("resources/css/style.css");

        editTitle = new Label("Edit");
        editTitle.setPadding(new Insets(0, 0, 0, 10));
        editTitle.pseudoClassStateChanged(CssConstants.COLUMN, true);

        sendValues = new Button("Edit");
        sendValues.setOnAction((ActionEvent e) -> {
            sendValues();
        });

        // fill standard attributes
        initName();
        initPrice();

        form = new GridPane();
        form.setHgap(10);
        form.setVgap(5);
        form.setPadding(new Insets(10));
        form.getColumnConstraints().add(new ColumnConstraints(80));

        form.add(nameL, 0, 0);
        form.add(name, 1, 0);
        form.add(priceL, 0, 1);
        form.add(price, 1, 1);

        if (item instanceof Coffee) {
            getCoffeeAttributes();
        } else if (item instanceof Beverage) {
            getBeverageAttributes();
        } else if (item instanceof Extra) {
            getExtraAttributes();
        } else if (item instanceof Food) {
            getFoodAttributes();
        }

        box.setPadding(new Insets(20, 20, 20, 20));
        box.getChildren().addAll(editTitle, form);
        return box;
    }

    protected void initName() {
        super.initName();
        String itemName = item.getName();
        name.setText(itemName);
        dh.setName(itemName);
    }

    protected void initPrice() {
        super.initPrice();
        Double itemPrice = item.getPrice();
        dh.setPrice(itemPrice);
    }

    protected void initIngredients() {
        super.initIngredients();
        String itemIngredients = "";
        if (item instanceof Coffee) {
            Coffee ingrItem = (Coffee) item;
            itemIngredients = ingrItem.getIngredients();
        } else if (item instanceof Food) {
            Food ingrItem = (Food) item;
            itemIngredients = ingrItem.getIngredients();
        } else if (item instanceof Beverage) {
            Beverage ingrItem = (Beverage) item;
            itemIngredients = ingrItem.getIngredients();
        }
        ingredients.setText(itemIngredients);
        dh.setIngredients(itemIngredients);
    }

    protected void initOptional() {
        Food food = (Food) item;
        String itemOptional = food.getDietaryInfo();
        optional.setText(itemOptional);
        dh.setOptional(itemOptional);
    }

    protected void initHeath() {
        super.initHeath();
        Beverage bev = (Beverage) item;
        if (bev.getHot()) {
            hot.setSelected(true);
            dh.setHot(true);
        } else {
            cold.setSelected(true);
            dh.setHot(false);
        }
    }

    protected void getCoffeeAttributes() {
        super.getCoffeeAttributes();
        editTitle.setText("Edit Coffee");
    }

    protected void getFoodAttributes() {
        super.getFoodAttributes();
        editTitle.setText("Edit Food");
    }

    protected void getBeverageAttributes() {
        super.getBeverageAttributes();
        editTitle.setText("Edit Beverage");
    }

    /**
     * get specific attributes
     */
    protected void getExtraAttributes() {
        super.getExtraAttributes();
        editTitle.setText("Edit Extra");
    }

    /**
     * check values and send them to MenuItemFactory to finally create the menu items
     */
    private void sendValues() {
        boolean ok = true;
        boolean nameOk = true;
        int id = Menu.items.indexOf(item);
        Menu.items.remove(id);
        String mes = "Please add the required information to your product: " + System.lineSeparator();
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
            if (item instanceof Coffee || item instanceof Food) {
                if (dh.getIngredients() == null || dh.getIngredients().equals("")) {
                    mes += "ingredients" + System.lineSeparator();
                    ok = false;
                }
                if (item instanceof Food) {
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
        // send values to Factory if the input is ok
        if (ok) {
            MenuItemFactory factory = MenuItemFactory.getInstance();
            Menu.items.add(id, factory.edit(item, dh.getName(), dh.getPrice(), dh.getIngredients(), dh.getOptional()));
            // save changes in file
            File file = File.getInstance();
            try {
                file.save(Menu.toStringArray());
                String toastMsg = "Edit was successful.";
                Toast.makeToast(editStage, toastMsg);
            } catch (IOException e) {
                ErrorMsg.addErrorMsg(editStage, "An file error occurred.");
            }
            editStage.close();
            dh.initVars();
            layout.setCenter(modify.getModifyView());
        } else {
            Menu.items.add(id, item);
            ErrorMsg.addErrorMsg(editStage, mes);
        }
    }
}