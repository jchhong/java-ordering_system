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
    // EFFECTS: add a dish to the dishList. if already exist
    //          return false, otherwise true.
    public boolean addDish(Dish dish) {
        if (! dishList.contains(dish)) {
            dishList.add(dish);
            return true;
        } else {
            return false;
        }
    }


    // REQUIRES: dishList.size >= 1
    // MODIFIES: this
    // EFFECTS: add a dish to the dishList. if already exist
    //          return false, otherwise true.
    public boolean removeDish(Dish dish) {
        if (dishList.contains(dish)) {
            dishList.remove(dish);
            return true;
        } else {
            return false;
        }
    }


    public List<Dish> getDishList() {
        return dishList;
    }
}
