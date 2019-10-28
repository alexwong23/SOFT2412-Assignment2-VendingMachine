package VendingMachine.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LollyFactoryTest {

    FoodFactory factory;

    @Before
    public void setUp() throws Exception {
        factory = new LollyFactory();
    }

    @Test
    public void makeFood(){
        Food lolly = new Lolly(1,"lolly",10.0);
        Food test = factory.makeFood(1,"lolly",10.0);

        assertEquals(lolly.getId(),test.getId());
        assertEquals(lolly.getName(),test.getName());
        assertEquals(lolly.getPrice(),test.getPrice(),0.1);
        assertEquals(lolly.getType(),test.getType());
    }
}