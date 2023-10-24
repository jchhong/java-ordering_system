package ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import model.Dish;
import model.Menu;
import model.Order;
import persistence.JsonReader;
import persistence.JsonWriter;

// Represents the dish ordering application
public class OrderApp {
    private static final String JSON_STORE = "./data/myMenu.json";
    private Scanner input;
    private Menu myMenu;
    private Order newOrder;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: runs the ordering app
    public OrderApp() {
        runApp();
    }

    // EFFECTS: process user input
    void runApp() {
        boolean keepRunning = true;
        String command;

        init();

        while (keepRunning) {
            displayActions();
            command = input.next();
            if (command.equals("5")) {
                System.out.println("Do you want to save the Menu? Press \"y\" for \"yes\" and \"n\" for \"no\"");
                String newCommand = input.next();
                if (newCommand.equals("y")) {
                    saveMenu();
                }
                System.out.println("Bye~");
                keepRunning = false;
            } else {
                processCommand(command);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: initialize and create a default menu.
    void init() {
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        myMenu = new Menu();
        myMenu.addDish("Double Cheeseburger", 4.69);
        myMenu.addDish("Chicken Sandwich", 6.49);
        myMenu.addDish("Beef Sandwich", 8.25);
        myMenu.addDish("Salmon Salad", 9.85);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    // EFFECTS: display the main actions
    void displayActions() {
        System.out.println("\nPlease choose your action: ");
        System.out.println("\t1 -> View and Edit Menu");
        System.out.println("\t2 -> Make A New Order");
        System.out.println("\t3 -> Save My Menu");
        System.out.println("\t4 -> Reload My Menu");
        System.out.println("\t5 -> Quit");
    }

    // EFFECTS: process the commands for main actions
    void processCommand(String command) {
        if (command.equals("1")) {
            editMenu();
        } else if (command.equals("2")) {
            makeAnOrder();
        } else if (command.equals("3")) {
            saveMenu();
        } else if (command.equals("4")) {
            loadMenu();
        } else {
            System.out.println("Please choose a valid option.");
        }
    }

    // EFFECTS: edit the Menu including add, remove dish.
    void editMenu() {
        String command;

        while (true) {
            viewDishMenu();

            System.out.println("Please choose your action:\n"
                    + "\t1 -> Add Dish\n"
                    + "\t2 -> Remove Dish\n"
                    + "\t3 -> Back to previous menu\n");
            command = input.next();
            if (command.equals("1")) {
                menuAddDish();
            } else if (command.equals("2")) {
                menuRemoveDish();
            } else if (command.equals("3")) {
                break;
            } else {
                System.out.println("Please choose a valid option.");
            }
        }
    }

    // EFFECTS: print the menu
    void viewDishMenu() {
        System.out.println(myMenu.view());
    }


    // MODIFIES: this
    // EFFECTS: add a new dish to the menu
    void menuAddDish() {
        System.out.println("Please enter the dish name (can not be the same as the existing ones)");
        String name = input.next();
        System.out.println("Please enter the dish price");
        double price = new Double(input.next());
        myMenu.addDish(name, price);
        System.out.println("Successfully added!\n");
    }

    // MODIFIES: this
    // EFFECTS: remove an existing dish from the menu. If not existed, print message.
    void menuRemoveDish() {
        System.out.println("Please enter the dish name");
        String name = input.next();
        if (myMenu.removeDish(name)) {
            System.out.println("Successfully removed!\n");
        } else {
            System.out.println("No such dish in the menu!\n");
        }
    }

    // MODIFIES: this
    // EFFECTS: Create a new order for the given table number. Do the options include
    //          add dish, remove dish, view cart, change note, place order and back
    //          to the previous menu.
    void makeAnOrder() {
        System.out.println("Please enter the table number (please enter an integer)");
        int tableNumber = new Integer(input.next());
        newOrder = new Order(tableNumber);
        String command;

        while (true) {
            viewDishMenu();
            System.out.println("Please choose your action:\n" + "\t1 -> Add Dish\n"
                    + "\t2 -> Remove Dish\n" + "\t3 -> View Cart\n"
                    + "\t4 -> Change Note\n" + "\t5 -> Place Order\n"
                    + "\t6 -> Back to Previous Menu\n");
            command = input.next();

            if (command.equals("6")) {
                break;
            } else if (command.equals("5")) {
                placeOrder();
                break;
            } else {
                processOrderCommands(command);
            }

        }
    }

    // EFFECTS: process commands for making an order
    void processOrderCommands(String command) {
        if (command.equals("1")) {
            orderAddDish();
        } else if (command.equals("2")) {
            orderRemoveDish();
        } else if (command.equals("3")) {
            viewCart();
        } else if (command.equals("4")) {
            changeNote();
        } else {
            System.out.println("Please choose a valid option.");
        }
    }

    // MODIFIES: this
    // EFFECTS: add a dish to the order. If the dish is not in the menu, print message.
    void orderAddDish() {
        System.out.println("Please enter the dish name");
        String dishName = input.next();
        if (newOrder.addDish(myMenu, dishName)) {
            System.out.println("Successfully added!\n");
        } else {
            System.out.println("No such dish in the menu!\n");
        }
    }

    // MODIFIES: this
    // EFFECTS: remove an existing dish from the menu. If not existed, print message.
    void orderRemoveDish() {
        System.out.println("Please enter the dish name");
        String dishName = input.next();
        if (newOrder.removeDish(dishName)) {
            System.out.println("Successfully removed!\n");
        } else {
            System.out.println("No such dish in your cart!\n");
        }
    }

    // EFFECTS: view the dishes that's added into the order.
    void viewCart() {
        System.out.println("Dishes in your cart:");
        List<Dish> orderedList = newOrder.getDishList();
        for (Dish d : orderedList) {
            System.out.println(d.getName() + "\t$" + d.getPrice());
        }
        System.out.println();
    }

    // MODIFIES: this
    // EFFECTS: change the note of the order.
    void changeNote() {
        System.out.println("Please write the note below:");
        String newNote = input.next();
        newOrder.changeNoteTo(newNote);
        System.out.println("Successfully changed note!\n");
    }

    // EFFECTS: place the order by printing the table number, a list of dishes ordered,
    //          subtotal, GST, PST, and total price. Then finish the order and go back
    //          to the main menu.
    void placeOrder() {
        System.out.println(newOrder.view());
    }

    // EFFECTS: saves the menu to file
    private void saveMenu() {
        try {
            jsonWriter.open();
            jsonWriter.write(myMenu);
            jsonWriter.close();
            System.out.println("Saved my Menu to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads menu from file
    private void loadMenu() {
        try {
            myMenu = jsonReader.read();
            System.out.println("Loaded my Menu from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

}
