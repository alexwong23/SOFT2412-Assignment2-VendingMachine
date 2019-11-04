package VendingMachine.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ChocolateTest {
    Food choco;

    @Before
    public void setUp() throws Exception {
        choco = new Drink(2,"choco",5.0);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getType() {
        assertEquals(FoodEnum.DRINK, choco.getType());
    }

    @Test
    public void getName() {
        assertEquals("choco", choco.getName());
    }

    @Test
    public void getPrice() {
        assertEquals(5.0, choco.getPrice(),0.1);
    }

    @Test
    public void testClone() {
        Food clone = choco.clone();
        assertEquals(choco.getId(),clone.getId());
        assertEquals(choco.getName(),clone.getName());
        assertEquals(choco.getPrice(),clone.getPrice(),0.1);
        assertEquals(choco.getType(),clone.getType());
        assertNotNull(clone);
    }

    @Test
    public void getId() {
        assertEquals(2, choco.getId());
    }
}