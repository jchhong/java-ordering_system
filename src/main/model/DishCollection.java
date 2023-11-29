package model;

import java.util.ArrayList;
import java.util.List;

// represent a collection of dishes. Can add/remove dishes, get a
// list of dish added to this collection.
public abstract class DishCollection {
    protected List<Dish> dishList;

    // EFFECTS: initialize a dish collection by creating an empty list of dishes
    public DishCollection() {
        dishList = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: add the given dish to the dishList. if already exist
    //          return false, otherwise true. If successfully added,
    //          log this event.
    public boolean addDish(Dish dish) {
        if (! dishList.contains(dish)) {
            dishList.add(dish);
            EventLog.getInstance().logEvent(new Event("Added a new dish: "
                    + dish.getName() + " $" + dish.getPrice()));
            return true;
        } else {
            return false;
        }
    }

    // MODIFIES: this
    // EFFECTS: add a dish by the given name and price to the dishList.
    //          if already exist, return false, otherwise true.
    //          If successfully added, log this event.
    public boolean addDish(String name, double price) {
        Dish dish = new Dish(name, price);
        return addDish(dish);
    }


    // REQUIRES: dishList.size >= 1
    // MODIFIES: this
    // EFFECTS: remove a dish from the dishList by its name. if already exist
    //          return true, otherwise false. If successfully removed,
    //          log this event.
    public boolean removeDish(String dishName) {
        for (Dish d : dishList) {
            if (d.getName().equals(dishName)) {
                dishList.remove(d);
                EventLog.getInstance().logEvent(new Event("Removed dish: "
                        + dishName + " $" + d.getPrice()));
                return true;
            }
        }
        return false;
    }


    public List<Dish> getDishList() {
        return dishList;
    }
}
