package VendingMachine;


import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.lang.IllegalArgumentException;
import org.javatuples.Pair;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class CurrencyConverter {

    private HashMap<String, Double> fxRates;

    /**
     * Consturctor for the CurrencyConverter Object
     * @param fxRates
     */
    public CurrencyConverter(HashMap<String, Double> fxRates) {
        this.fxRates = fxRates;
    }

    /**
     * method that converts the currencies, based on the pre-configured exchange rates.
     * @param fromCurr
     * @param toCurr
     * @param fromAmount
     * @return if sucessful - converted currency values
     * @throws IllegalArgumentException - simple error messages that inform the user that invalid values have been entered.
     */

    public double convertCurrency(String fromCurr, String toCurr, Double fromAmount) throws IllegalArgumentException {

        if (fromAmount < 0) {

            throw new IllegalArgumentException("Please pass a valid amount greater than 0.");
        }

        if (isValidCurrency(fromCurr) && isValidCurrency(toCurr)) {

            return (fxRates.get(fromCurr) / fxRates.get(toCurr)) * fromAmount;
        }

        throw new IllegalArgumentException("Please provide a valid currency. Currencies offered include: " + fxRates.keySet().toString());
    }

    /**
     * Method to verify user inputted currency is valid (i.e. that it exist in the JSON config file)
     * @param curr
     * @return true if is valid, false otherwise.
     */
    private boolean isValidCurrency(String curr) {

        for (HashMap.Entry<String, Double> entry : fxRates.entrySet()) {

            if (entry.getKey().equals(curr)) {

                return true;
            }
        }

        return false;
    }

}
