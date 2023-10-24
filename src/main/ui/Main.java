package ui;

import model.Menu;
import persistence.JsonWriter;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
//        new OrderApp();

        JsonWriter jsonWriter = new JsonWriter("./data/testReaderGeneralMenu.json");
        Menu testMenu = new Menu();
        testMenu.addDish("Bread", 0.99);
        testMenu.addDish("Egg",1.15);
        try {
            jsonWriter.open();
            jsonWriter.write(testMenu);
            jsonWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("cannot find file");
        }
    }
}
