package starbucks_fx;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import starbucks.File;
import starbucks.Menu;
import starbucks.MenuItemFactory;

import java.io.IOException;

/**
 * GUI: add an item to the menu
 */
public class AddItem extends DataObserver {
    private VBox bigPicture;
    private Label choose;
    private FlowPane choice;

    /**
     * @param dh
     * @param primaryStage
     */
    public AddItem(DataHolder dh, Stage primaryStage) {
        this.dh = dh;
        this.dh.attach(this);

        this.primaryStage = primaryStage;
    }

    /**
     * get whole view
     *
     * @return Scene with view for menu point 'add item'
     */
    public Node getAddItemView() {
        dh.initVars();

        bigPicture = new VBox();
        bigPicture.setPadding(new Insets(10, 10, 10, 10));
        bigPicture.setSpacing(10);

        choice = new FlowPane();
        choice.setPadding(new Insets(0, 10, 0, 10));
        choice.setHgap(10);
        choice.setVgap(15);

        choose = new Label();

        Button addCoffee = new Button("coffee");
        Button addFood = new Button("food");
        Button addBeverage = new Button("beverage");
        Button addExtra = new Button("extra");

        addCoffee.setMinSize(50, 40);
        addFood.setMinSize(50, 40);
        addBeverage.setMinSize(50, 40);
        addExtra.setMinSize(50, 40);

        addCoffee.setOnAction((ActionEvent e) -> showForm(0));
        addFood.setOnAction((ActionEvent e) -> showForm(1));
        addBeverage.setOnAction((ActionEvent e) -> showForm(2));
        addExtra.setOnAction((ActionEvent e) -> showForm(3));

        choice.getChildren().addAll(addCoffee, addFood, addBeverage, addExtra);
        choose.setPadding(new Insets(0, 10, 0, 10));
        choose.setText("Choose the item you want to add.");
        bigPicture.getChildren().addAll(choose, choice);

        return bigPicture;
    }

    /**
     * make form visible
     *
     * @param category int <br> 0 = coffee <br> 1 = food <br> 2 = beverage <br> 3 = extra
     */
    private void showForm(int category) {
        if (form != null) {
            bigPicture.getChildren().remove(2);
        }
        getAddItemForm(category);
        choose.setText(choose.getText() + System.lineSeparator() + "Change the item type if needed.");
        bigPicture.getChildren().add(form);
    }

    /**
     * prepare form
     *
     * @param category int <br> 0 = coffee <br> 1 = food <br> 2 = beverage <br> 3 = extra
     * @return form, depending on category
     */
    private GridPane getAddItemForm(int category) {
        // get form only if category is ok
        if (category >= 0 && category < 4) {
            form = new GridPane();
            form.setAlignment(Pos.TOP_LEFT);
            form.setHgap(10);
            form.setVgap(5);
            form.setPadding(new Insets(10, 10, 10, 10));
            form.getColumnConstraints().add(new ColumnConstraints(100));

            // fill standard attributes
            initName();
            initPrice();

            form.add(nameL, 0, 0);
            form.add(name, 1, 0);
            form.add(priceL, 0, 1);
            form.add(price, 1, 1);

            sendValues = new Button("add item");
            sendValues.setMinSize(50, 40);
            sendValues.setOnAction((ActionEvent e) -> sendValues(category));

            // fill specific attributes
            switch (category) {
                case 0:
                    getCoffeeAttributes();
                    break;
                case 1:
                    getFoodAttributes();
                    break;
                case 2:
                    getBeverageAttributes();
                    break;
                case 3:
                    getExtraAttributes();
                    break;
                default:
                    return null;
            }
            return form;
        }
        return null;
    }

    /**
     * get specific attributes
     */
    protected void getCoffeeAttributes() {
        super.getCoffeeAttributes();
        setChooseText("coffee");
    }

    /**
     * get specific attributes
     */
    protected void getFoodAttributes() {
        super.getFoodAttributes();
        setChooseText("food");
    }

    /**
     * get specific attributes
     */
    protected void getBeverageAttributes() {
        super.getBeverageAttributes();
        setChooseText("beverage");
    }

    /**
     * get specific attributes
     */
    protected void getExtraAttributes() {
        super.getExtraAttributes();
        setChooseText("extra");
    }

    private void setChooseText(String item) {
        choose.setText("You are adding some " + item + " to your menu.");
    }

    protected void initIngredients() {
        super.initIngredients();
        ingredients.setText(dh.getIngredients());
    }

    protected void initOptional() {
        super.initOptional();
        optional.setText(dh.getOptional());
    }

    protected void initHeath() {
        super.initHeath();
        if (dh.isHot()) {
            hot.setSelected(true);
        } else {
            cold.setSelected(true);
        }
    }

    /**
     * check values and send them to MenuItemFactory to finally create the menu items
     */
    protected void sendValues(int category) {
        String mes = super.sendValues(category, "");
        // send values to Factory if the input is ok
        if (null == mes) {
            MenuItemFactory factory = MenuItemFactory.getInstance();
            Menu.items.add(factory.create(dh.getName(), dh.getPrice(), dh.getIngredients(), dh.getOptional()));
            // save changes in file
            File file = File.getInstance();
            try {
                file.save(Menu.toStringArray());
                String toastMsg = "Add was successful.";
                Toast.makeToast(primaryStage, toastMsg);
            } catch (IOException e) {
                ErrorMsg.addErrorMsg(primaryStage, "An file error occurred.");
            }
            dh.initVars();
        } else {
            ErrorMsg.addErrorMsg(primaryStage, mes);
        }
    }
}