package persistence;

import model.Dish;
import model.Menu;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


// Cited from: ubc CPSC210 2023W1 JsonSerializationDemo
public class JsonWriterTest {
    //NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter is to
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.

    @Test
    void testWriterInvalidFile() {
        try {
            Menu menu = new Menu();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyMenu() {
        try {
            Menu menu = new Menu();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyMenu.json");
            writer.open();
            writer.write(menu);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyMenu.json");
            Menu menuRead = reader.read();
            assertEquals(0, menuRead.getDishList().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralMenu() {
        try {
            Menu testMenu = new Menu();
            testMenu.addDish("Noodles", 7.99);
            testMenu.addDish("Sushi",9.99);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralMenu.json");
            writer.open();
            writer.write(testMenu);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralMenu.json");
            Menu menuRead = reader.read();
            List<Dish> dishList = menuRead.getDishList();
            assertEquals(2, dishList.size());
            assertEquals("Noodles", dishList.get(0).getName());
            assertEquals(7.99, dishList.get(0).getPrice());
            assertEquals("Sushi", dishList.get(1).getName());
            assertEquals(9.99, dishList.get(1).getPrice());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
