package model;

// represent a menu with a collection of dishes. Waiters
// can view, add, and remove a dish from the menu.
public class Menu extends DishCollection {

    // EFFECTS: Construct a menu with empty list of dishes
    public Menu() {
        super();
    }

    //EFFECTS: view the menu. Print the list of dishes including
    //         their name, and price.
    public String view() {
        String menuOutline = "****** MENU ******" + "\n";
        for (Dish d :  dishList) {
            menuOutline = menuOutline + d.getName() + "\t" + "$" + d.getPrice() + "\n";
        }
        return menuOutline;
    }

}