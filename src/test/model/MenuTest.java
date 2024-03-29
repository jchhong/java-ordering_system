package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MenuTest {

    Menu testMenu;
    Dish d1;
    Dish d2;
    Dish d3;

    @BeforeEach
    void runBefore() {
        testMenu = new Menu();
        d1 = new Dish("egg", 1.5);
        d2 = new Dish("bagel", 1.8);
        d3 = new Dish("salad", 5.9);
    }

    @Test
    void testConstructor() {
        assertEquals(0, testMenu.getDishList().size());
    }

    @Test
    void addOneDish() {
        assertTrue(testMenu.addDish(d1));
        assertEquals(1, testMenu.getDishList().size());
    }

    @Test
    void addMultipleDishes() {
        assertTrue(testMenu.addDish(d1));
        assertTrue(testMenu.addDish(d2));
        List<Dish> rst = testMenu.getDishList();
        assertEquals(2, rst.size());
        assertEquals(d1, rst.get(0));
        assertEquals(d2, rst.get(1));
    }

    @Test
    void addExistedDish() {
        assertTrue(testMenu.addDish(d1));
        assertFalse(testMenu.addDish(d1));
        assertEquals(1, testMenu.getDishList().size());
    }

    @Test
    void removeOneDish() {
        assertTrue(testMenu.addDish(d1));
        assertTrue(testMenu.addDish(d2));
        assertEquals(2, testMenu.getDishList().size());
        assertTrue(testMenu.removeDish("egg"));
        assertEquals(d2, testMenu.getDishList().get(0));
    }

    @Test
    void removeNonExistingDish() {
        assertTrue(testMenu.addDish(d1));
        assertTrue(testMenu.addDish(d2));
        assertEquals(2, testMenu.getDishList().size());
        assertFalse(testMenu.removeDish("beef"));
        assertEquals(d1, testMenu.getDishList().get(0));
        assertEquals(d2, testMenu.getDishList().get(1));
    }

    @Test
    void testViewMenu() {
        testMenu.addDish(d1);
        testMenu.addDish(d2);
        testMenu.addDish(d3);

        String expectedString = "****** MENU ******\n" +
                "egg\t$1.5\n" +
                "bagel\t$1.8\n" +
                "salad\t$5.9\n";
        assertEquals(expectedString, testMenu.view());
    }



}
