package VendingMachine.config;

import VendingMachine.model.FoodEnum;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FoodConfigTest {

    FoodConfig fc;

    @Before
    public void setUp() throws Exception {
        fc = new FoodConfig(6,"Mars bar", FoodEnum.CHOCOLATE, 5.0,10);
    }

    @Test
    public void getName() {
        assertEquals("Mars bar", fc.getName());
    }

    @Test
    public void getType() {
        assertEquals(FoodEnum.CHOCOLATE,fc.getType());
    }

    @Test
    public void getPrice() {
        assertEquals(5.0,fc.getPrice(),0.1);
    }

    @Test
    public void getQuantity() {
        assertEquals(10, fc.getQuantity());
    }

    @Test
    public void getId() {
        assertEquals(6,fc.getId());
    }
}