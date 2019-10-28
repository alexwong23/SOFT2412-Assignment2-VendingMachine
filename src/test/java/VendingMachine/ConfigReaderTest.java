package VendingMachine;

import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class ConfigReaderTest {

    @Test
    public void readRateConfigs() {
        HashMap<String, Double> expected = new HashMap<>();
        expected.put("USD", 1.00);
        expected.put("AUD", 0.67);
        expected.put("CNY", 0.14);
        expected.put("JPY", 0.0094);
        expected.put("CAD",0.75);

        HashMap<String, Double> test = ConfigReader.readRateConfigs("src/test/resources/config.json");

        assertEquals(expected.get("USD"), test.get("USD"), 0.01);
        assertEquals(expected.get("AUD"), test.get("AUD"), 0.01);
        assertEquals(expected.get("CNY"), test.get("CNY"), 0.01);
        assertEquals(expected.get("JPY"), test.get("JPY"), 0.01);
        assertEquals(expected.get("CAD"), test.get("CAD"), 0.01);
    }
}