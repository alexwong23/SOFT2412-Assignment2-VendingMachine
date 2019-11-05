package VendingMachine.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DrinkFactoryTest {
    FoodFactory factory;

    @Before
    public void setUp() throws Exception {
        factory = new DrinkFactory();
    }

    @Test
    public void makeFood(){
        Food drink = new Drink(1,"drink",10.0);
        Food test = factory.makeFood(1,"drink",10.0);

        assertEquals(drink.getId(),test.getId());
        assertEquals(drink.getName(),test.getName());
        assertEquals(drink.getPrice(),test.getPrice(),0.1);
        assertEquals(drink.getType(),test.getType());
    }
}