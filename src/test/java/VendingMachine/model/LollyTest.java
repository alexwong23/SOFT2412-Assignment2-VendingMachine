package VendingMachine.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LollyTest {
    Food lolly;

    @Before
    public void setUp() throws Exception {
        lolly = new Drink(2,"lollies",5.0);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getType() {
        assertEquals(FoodEnum.DRINK, lolly.getType());
    }

    @Test
    public void getName() {
        assertEquals("lollies", lolly.getName());
    }

    @Test
    public void getPrice() {
        assertEquals(5.0, lolly.getPrice(),0.1);
    }

    @Test
    public void testClone() {
        Food clone = lolly.clone();
        assertEquals(lolly.getId(),clone.getId());
        assertEquals(lolly.getName(),clone.getName());
        assertEquals(lolly.getPrice(),clone.getPrice(),0.1);
        assertEquals(lolly.getType(),clone.getType());
        assertNotNull(clone);
    }

    @Test
    public void getId() {
        assertEquals(2, lolly.getId());
    }
}