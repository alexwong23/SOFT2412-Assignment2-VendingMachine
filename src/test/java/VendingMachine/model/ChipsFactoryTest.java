package VendingMachine.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ChipsFactoryTest {
    FoodFactory factory;


    @Before
    public void setUp() throws Exception {
        factory = new ChipsFactory();
    }

    @Test
    public void makeFood(){
        Food chips = new Chips(1,"chips",10.0);
        Food test = factory.makeFood(1,"chips",10.0);

        assertEquals(chips.getId(),test.getId());
        assertEquals(chips.getName(),test.getName());
        assertEquals(chips.getPrice(),test.getPrice(),0.1);
        assertEquals(chips.getType(),test.getType());
    }
}