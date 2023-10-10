package ui;

import model.Dish;
import model.Menu;

public class Main {
    public static void main(String[] args) {
//        Dish d1 = new Dish("Chicken Sandwich", 6.75);
//        Dish d2 = new Dish("Avocado Toast", 4.85);
//        Dish d3 = new Dish("Salmon Salad", 7.15);
//        Dish d4 = new Dish("Beef Sandwich", 108.15);

//        Order oneOrder = new Order(3);
//        oneOrder.addDish(d1);
//        oneOrder.addDish(d2);
//        oneOrder.addDish(d3);
//        oneOrder.addDish(d4);
//        oneOrder.changeNoteTo("No Spicy");
//        System.out.println(oneOrder.view());

        Dish d1 = new Dish("egg", 1.5);
        Dish d2 = new Dish("bagel", 1.8);
        Dish d3 = new Dish("salad", 5.9);

        Menu myMenu = new Menu();
        myMenu.addDish(d1);
        myMenu.addDish(d2);
        myMenu.addDish(d3);
        System.out.println(myMenu.view());
    }
}
