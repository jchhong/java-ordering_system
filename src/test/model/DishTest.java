package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DishTest {
    Dish d1;

    @BeforeEach
    void runBefore() {
        d1 = new Dish("Bagel Belt", 6.75);
    }

    @Test
    void testConstructor() {
        assertEquals("Bagel Belt", d1.getName());
        assertEquals(6.75, d1.getPrice());
    }

    @Test
    void testSetName() {
        d1.setName("Chicken Sandwich");
        assertEquals("Chicken Sandwich", d1.getName());
    }

    @Test
    void testSetPrice() {
        d1.setPrice(7.15);
        assertEquals(7.15, d1.getPrice());
    }

}