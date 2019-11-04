package VendingMachine.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DrinkTest {
    Food drink;

    @Before
    public void setUp() throws Exception {
        drink = new Drink(1,"Pepsi",10.0);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getType() {
        assertEquals(FoodEnum.DRINK,drink.getType());
    }

    @Test
    public void getName() {
        assertEquals("Pepsi",drink.getName());
    }

    @Test
    public void getPrice() {
        assertEquals(10.0, drink.getPrice(),0.1);
    }

    @Test
    public void testClone() {
        Food clone = drink.clone();
        assertEquals(drink.getId(),clone.getId());
        assertEquals(drink.getName(),clone.getName());
        assertEquals(drink.getPrice(),clone.getPrice(),0.1);
        assertEquals(drink.getType(),clone.getType());
        assertNotNull(clone);
    }

    @Test
    public void getId() {
        assertEquals(1,drink.getId());
    }
}