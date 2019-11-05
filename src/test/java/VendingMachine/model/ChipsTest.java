package VendingMachine.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ChipsTest {
    Food chips;

    @Before
    public void setUp() throws Exception {
        chips = new Drink(2,"chips",5.0);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getType() {
        assertEquals(FoodEnum.DRINK,chips.getType());
    }

    @Test
    public void getName() {
        assertEquals("chips",chips.getName());
    }

    @Test
    public void getPrice() {
        assertEquals(5.0, chips.getPrice(),0.1);
    }

    @Test
    public void testClone() {
        Food clone = chips.clone();
        assertEquals(chips.getId(),clone.getId());
        assertEquals(chips.getName(),clone.getName());
        assertEquals(chips.getPrice(),clone.getPrice(),0.1);
        assertEquals(chips.getType(),clone.getType());
        assertNotNull(clone);
    }

    @Test
    public void getId() {
        assertEquals(2,chips.getId());
    }
}