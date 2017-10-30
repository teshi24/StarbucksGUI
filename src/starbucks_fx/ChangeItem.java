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

public class ChangeItem extends DataObserver {
    private Modify modify;
    private Stage editStage;
    private BorderPane layout;
    private Label editTitle;
    private GridPane form;
    private Button editItem;

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
        Stage editStage = new Stage();
        dh.initVars();

        editStage.getIcons().add(new Image("resources/images/edit.png"));
        editStage.setTitle("Edit");

        editStage.initOwner(primaryStage);
        editStage.initModality(Modality.WINDOW_MODAL);
        editStage.setScene(getEditScene(item));
        editStage.setResizable(false);
        this.editStage = editStage;
        editStage.show();
    }

    /**
     * @param item
     * @return
     */
    private Scene getEditScene(Category item) {
        VBox box = new VBox();
        box.getStylesheets().add("resources/css/style.css");
        form = new GridPane();
        form.setHgap(10);
        form.setVgap(5);
        form.setPadding(new Insets(10));
        form.getColumnConstraints().add(new ColumnConstraints(80));
        editTitle = new Label("Edit");
        editTitle.setPadding(new Insets(0, 0, 0, 10));
        editTitle.pseudoClassStateChanged(CssConstants.COLUMN, true);

        // fill standard attributes
        initName(item);
        initPrice(item);

        form.add(nameL, 0, 0);
        form.add(name, 1, 0);
        form.add(priceL, 0, 1);
        form.add(price, 1, 1);
        editItem = new Button("Edit");
        editItem.setOnAction((ActionEvent e) -> {
            sendValues(item);

            File file = File.getInstance();
            try {
                file.save(Menu.toStringArray());
                String toastMsg = "Edit was successful.";
                Toast.makeToast(primaryStage, toastMsg);
            } catch (IOException ex) {
                ErrorMsg.addErrorMsg(primaryStage, "A file error occurred.");
            }
            layout.setCenter(modify.getModifyView());
        });

        if (item instanceof Coffee) {
            getCoffeeAttributes(item);
        } else if (item instanceof Beverage) {
            getBeverageAttributes(item);
        } else if (item instanceof Extra) {
            getExtraAttributes(item);
        } else if (item instanceof Food) {
            getFoodAttributes(item);
        }

        box.setPadding(new Insets(20, 20, 20, 20));
        box.getChildren().addAll(editTitle, form);
        return (new Scene(box, 290, 210));
    }

    /**
     * @param item
     */
    private void initName(Category item) {
        nameL = new Label("name:");
        name = new TextField();
        String itemName = item.getName();
        name.setText(itemName);
        dh.setName(itemName);
        name.textProperty().addListener((observable, oldValue, newValue) -> {
            String text = name.getText().toString();
            if (!text.contains("|") && !text.contains("¦")) {
                dh.setName(text);
            } else {
                name.setText(dh.getName());
                ErrorMsg.addErrorMsg(editStage, ErrorMsg.getCharNotAllowed());
            }
        });
    }

    /**
     * @param item
     */
    private void initPrice(Category item) {
        priceL = new Label("price:");
        price = new TextField();
        Double itemPrice = item.getPrice();
        dh.setPrice(itemPrice);
        price.setText(dh.getPriceString());
        price.setEditable(false);
        price.setOnMouseClicked(e -> {
            Price p = new Price(editStage);
            p.enterPrice(dh);
        });
    }

    /**
     * @param item
     */
    private void initIngredients(Category item) {
        ingredientsL = new Label("ingredients:");
        ingredients = new TextField();
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
        ingredients.textProperty().addListener((observable, oldValue, newValue) -> {
            String text = ingredients.getText().toString();
            if (!text.contains("|") && !text.contains("¦")) {
                dh.setIngredients(text);
            } else {
                ingredients.setText(dh.getIngredients());
                ErrorMsg.addErrorMsg(editStage, ErrorMsg.getCharNotAllowed());
            }
        });
    }

    /**
     * @param item
     */
    private void initOptional(Category item) {
        Food food = (Food) item;
        optionalL = new Label("dietary info:");
        optional = new TextField();
        String itemOptional = food.getDietaryInfo();
        optional.setText(itemOptional);
        dh.setOptional(itemOptional);
        optional.textProperty().addListener((observable, oldValue, newValue) -> {
            String text = optional.getText().toString();
            if (!text.contains("|") && !text.contains("¦")) {
                dh.setOptional(text);
            } else {
                optional.setText(dh.getOptional());
                ErrorMsg.addErrorMsg(editStage, ErrorMsg.getCharNotAllowed());
            }
        });
    }

    /**
     * @param item
     */
    private void initHeath(Category item) {
        Beverage bev = (Beverage) item;
        optionalL = new Label("heat:");
        optional = new TextField();
        heat = new ToggleGroup();
        hot = new RadioButton("hot");
        hot.setToggleGroup(heat);
        cold = new RadioButton("cold");
        cold.setToggleGroup(heat);
        if (bev.getHot()) {
            hot.setSelected(true);
            dh.setHot(true);
        } else {
            cold.setSelected(true);
            dh.setHot(false);
        }
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
     * get specific attributes
     */
    private void getCoffeeAttributes(Category item) {
        editTitle.setText("Edit Coffee");
        initIngredients(item);
        form.add(ingredientsL, 0, 2);
        form.add(ingredients, 1, 2);
        form.add(editItem, 1, 3);

        heat = null;
        optional = null;
    }

    /**
     * get specific attributes
     */
    private void getFoodAttributes(Category item) {
        editTitle.setText("Edit Food");
        initIngredients(item);
        initOptional(item);

        form.add(ingredientsL, 0, 2);
        form.add(ingredients, 1, 2);
        form.add(optionalL, 0, 3);
        form.add(optional, 1, 3);
        form.add(editItem, 1, 4);

        heat = null;
    }

    /**
     * get specific attributes
     */
    private void getBeverageAttributes(Category item) {
        editTitle.setText("Edit Beverage");
        initHeath(item);
        initIngredients(item);

        form.add(ingredientsL, 0, 2);
        form.add(ingredients, 1, 2);
        form.add(optionalL, 0, 3);
        form.add(heatContainer, 1, 3);
        form.add(editItem, 1, 4);
    }

    /**
     * get specific attributes
     */
    private void getExtraAttributes(Category item) {
        editTitle.setText("Edit Extra");
        form.add(editItem, 1, 2);

        heat = null;
        ingredients = null;
        optional = null;
    }

    /**
     * check values and send them to MenuItemFactory to finally create the menu items
     */
    private void sendValues(Category item) {
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
        } else {
            Menu.items.add(id, item);
            ErrorMsg.addErrorMsg(editStage, mes);
        }
    }
}