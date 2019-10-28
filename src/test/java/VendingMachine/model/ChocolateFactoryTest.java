package VendingMachine.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ChocolateFactoryTest {
    FoodFactory factory;


    @Before
    public void setUp() throws Exception {
        factory = new ChocolateFactory();
    }

    @Test
    public void makeFood(){
        Food choco = new Chocolate(1,"choco",10.0);
        Food test = factory.makeFood(1,"choco",10.0);

        assertEquals(choco.getId(),test.getId());
        assertEquals(choco.getName(),test.getName());
        assertEquals(choco.getPrice(),test.getPrice(),0.1);
        assertEquals(choco.getType(),test.getType());
    }
}