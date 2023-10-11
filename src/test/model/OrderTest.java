package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class OrderTest {
    Order testOrder;
    Dish d1;
    Dish d2;
    Dish d3;

    @BeforeEach
    void runBefore() {
        testOrder = new Order(3);
        d1 = new Dish("Chicken Sandwich", 6.75);
        d2 = new Dish("Avocado Toast", 4.85);
        d3 = new Dish("Salmon Salad", 7.15);
    }

    @Test
    void testConstructor() {
        assertEquals(0, testOrder.getDishList().size());
        assertEquals(3, testOrder.getTableNumber());
        assertEquals("", testOrder.getNote());
        assertFalse(testOrder.getIsComplete());
    }

    @Test
    void testAddDishInMenu() {
        Menu testMenu = new Menu();
        testMenu.addDish(d1);
        testMenu.addDish(d2);
        assertTrue(testOrder.addDish(testMenu, "Chicken Sandwich"));
        assertEquals(1, testOrder.getDishList().size());
    }

    @Test
    void testAddDishNotInMenu() {
        Menu testMenu = new Menu();
        testMenu.addDish(d2);
        testMenu.addDish(d3);
        assertFalse(testOrder.addDish(testMenu, "Chicken Sandwich"));
        assertEquals(0, testOrder.getDishList().size());
    }

    @Test
    void testEligibleForDiscount() {
        Dish d4 = new Dish("beef sandwich", 93.25);
        testOrder.addDish(d1);
        testOrder.addDish(d4);
        assertTrue(testOrder.eligibleForDiscount());
    }

    @Test
    void testNotEligibleForDiscount() {
        testOrder.addDish(d1);
        testOrder.addDish(d2);
        assertFalse(testOrder.eligibleForDiscount());
    }

    @Test
    void testChangeNote() {
        testOrder.changeNoteTo("No spicy");
        testOrder.changeNoteTo("vegetarian");
        assertEquals("vegetarian", testOrder.getNote());
    }

    @Test
    void testOrderCompleted() {
        testOrder.orderCompleted();
        assertTrue(testOrder.getIsComplete());
    }

    @Test
    void testGetSubTotal() {
        testOrder.addDish(d1);
        testOrder.addDish(d2);
        testOrder.addDish(d3);
        assertEquals(18.75, testOrder.getSubTotal());
    }

    @Test
    void testGetSubTotalAfterDiscountEligibleCase() {
        Dish d4 = new Dish("beef sandwich", 107.45);
        testOrder.addDish(d1);
        testOrder.addDish(d4);
        assertEquals(104.2, testOrder.getSubTotalAfterDiscount());
    }

    @Test
    void testGetSubTotalAfterDiscountNotEligibleCase() {
        Dish d4 = new Dish("beef sandwich", 47.45);
        testOrder.addDish(d4);
        assertEquals(47.45, testOrder.getSubTotalAfterDiscount());
    }

    @Test
    void testGetTotalPrice() {
        Dish d4 = new Dish("beef sandwich", 107.45);
        testOrder.addDish(d1);
        testOrder.addDish(d4);
        assertEquals(116.704, testOrder.getTotalPrice(), 0.0001);
    }

    @Test
    void testViewNoDiscount() {
        testOrder.addDish(d1);
        testOrder.addDish(d2);
        testOrder.addDish(d3);
        testOrder.changeNoteTo("vegetarian");
        String expectedString = "***** Order for table 3*****\n" +
                "------------\n" +
                "Summary of dishes:\n" +
                "\tChicken Sandwich\t$6.75\n" +
                "\tAvocado Toast\t$4.85\n" +
                "\tSalmon Salad\t$7.15\n" +
                "------------\n" +
                "Note:\n" +
                "\tvegetarian\n" +
                "------------\n" +
                "SUB TOTAL: $18.75\n" +
                "------------\n" +
                "GST: $0.94\n" +
                "PST: $1.31\n" +
                "------------\n" +
                "TOTAL: $21.00";
        assertEquals(expectedString, testOrder.view());
    }

    @Test
    void testViewWithDiscount() {
        testOrder.addDish(d1);
        testOrder.addDish(d2);
        testOrder.addDish(d3);
        Dish d4 = new Dish("Beef Sandwich", 108.15);
        testOrder.addDish(d4);
        testOrder.changeNoteTo("No Spicy");
        String expectedString = "***** Order for table 3*****\n" +
                "------------\n" +
                "Summary of dishes:\n" +
                "\tChicken Sandwich\t$6.75\n" +
                "\tAvocado Toast\t$4.85\n" +
                "\tSalmon Salad\t$7.15\n" +
                "\tBeef Sandwich\t$108.15\n" +
                "------------\n" +
                "Note:\n" +
                "\tNo Spicy\n" +
                "------------\n" +
                "SUB TOTAL: $126.90\n" +
                "DISCOUNT: $-10.0\n" +
                "------------\n" +
                "GST: $5.85\n" +
                "PST: $8.18\n" +
                "------------\n" +
                "TOTAL: $130.93";
        assertEquals(expectedString, testOrder.view());
    }




}
