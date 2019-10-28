package VendingMachine.config;

import VendingMachine.model.Cash;
import VendingMachine.model.CashEnum;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CashConfigTest {
    CashConfig cc;

    @Before
    public void setUp() throws Exception {
        cc = new CashConfig(1, "10c", CashEnum.COIN,0.1,1);
    }

    @Test
    public void getName() {
        assertEquals("10c",cc.getName());
    }

    @Test
    public void getType() {
        assertEquals(CashEnum.COIN,cc.getType());
    }

    @Test
    public void getValue() {
        assertEquals(0.1,cc.getValue(),0);
    }

    @Test
    public void getQuantity() {
        assertEquals(1,cc.getQuantity());
    }

    @Test
    public void getId() {
        assertEquals(1,cc.getId());
    }
}