package persistence;

import model.Dish;
import model.Menu;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Cited from: ubc CPSC210 2023W1 JsonSerializationDemo
public class JsonReaderTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Menu menu = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyMenu() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyMenu.json");
        try {
            Menu menu = reader.read();
            assertEquals(0, menu.getDishList().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralMenu() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralMenu.json");
        try {
            Menu menu = reader.read();
            List<Dish> dishList = menu.getDishList();
            assertEquals(2, dishList.size());
            assertEquals("Bread", dishList.get(0).getName());
            assertEquals(0.99, dishList.get(0).getPrice());
            assertEquals("Egg", dishList.get(1).getName());
            assertEquals(1.15, dishList.get(1).getPrice());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

}
