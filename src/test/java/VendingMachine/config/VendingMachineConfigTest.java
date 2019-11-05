package VendingMachine.config;

import VendingMachine.ConfigReader;
import org.junit.Before;
import org.junit.Test;


import java.util.ArrayList;

import static org.junit.Assert.*;

public class VendingMachineConfigTest {
    private VendingMachineConfig vmc;

    @Before
    public void setUp() {
        vmc = ConfigReader.readFoodNCashConfigs("src/test/resources/config.json");
    }

    @Test
    public void getFoodConfigs() {
        ArrayList fcs = vmc.getFoodConfigs();
        assertNotNull(fcs);
        assertEquals(7,fcs.size());
    }

    @Test
    public void getCashConfigs() {
        ArrayList ccs = vmc.getFoodConfigs();
        assertNotNull(ccs);
        assertEquals(7,ccs.size());
    }
}