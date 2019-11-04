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
     * Method to handle the conversion of multiple currency, up to 3.
     * @param currencies
     * @param toCurr
     * @return the converted value for the choosen currency exchange rate.
     * @throws IllegalArgumentException - simple error message to inform the user of an incorrect input.
     */
    public double convertMultipleCurrencies(ArrayList<Pair<String, Double>> currencies, String toCurr) {

        if (currencies.size() > 3) {

            throw new IllegalArgumentException("We can only convert up to three currencies.");
        }

        if (isValidCurrency(toCurr)) {

            double accum = 0.0;

            for (Pair<String, Double> currency : currencies) {

                accum += convertCurrency(currency.getValue0(), toCurr, currency.getValue1());
            }

            return accum;
        }

        throw new IllegalArgumentException("Please provide a valid toCurr currency.");
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

    /**
     * Setter method to change the currency rates (during runtime only)
     * @param type - the currency (e.g. AUD)
     * @param rate - the conversion rate (some multiplier)
     */
    public void setRate(String type, double rate){

        JSONParser jsonParser = new JSONParser();


        JSONObject settingsJSON = null;

        try (FileReader reader = new FileReader("src/main/resources/rates.json")) {

            Object obj = jsonParser.parse(reader);

            //Read JSON file into a JSON object

            settingsJSON = (JSONObject) obj;

        } catch (ParseException | IOException e) {

            e.printStackTrace();
            System.exit(1);

        }

        // If settingsJSON stays null then we have a problem and we should

        if (settingsJSON == null) {

            System.out.println("There was a problem parsing JSON file");

            System.exit(1);
        }

        settingsJSON.replace(type,settingsJSON.get(type),rate);

        try (FileWriter file = new FileWriter("src/main/resources/rates.json")) {

            file.write(settingsJSON.toJSONString());

            file.flush();

        } catch (IOException e) {

            e.printStackTrace();

        }
        fxRates.put(type, rate);



    }

    public HashMap<String, Double> getFxRates(){

        return this.fxRates;
    }

    public String show(){
        String output = "USD is : " + this.getFxRates().get("USD") + "\n";
        output = output + "AUD is : " +this.getFxRates().get("AUD") + "\n";
        output = output + "CNY is : " +this.getFxRates().get("CNY") + "\n";
        output = output + "JPY is : " +this.getFxRates().get("JPY") + "\n";
        output = output + "CAD is : " +this.getFxRates().get("CAD") + "\n";
        return output;
    }

    public void reset(){
        /*
        "USD": 1.00,
        "AUD": 0.67,
        "CNY": 0.14,
        "JPY": 0.0094,
        "CAD": 0.75
        */

        fxRates.put("USD",1.00);
        fxRates.put("AUD",0.67);
        fxRates.put("CNY",0.14);
        fxRates.put("JPY", 0.0094);
        fxRates.put("CAD",0.75);

        JSONParser jsonParser = new JSONParser();

        JSONObject settingsJSON = null;

        try (FileReader reader = new FileReader("src/main/resources/rates.json")) {

            Object obj = jsonParser.parse(reader);

            //Read JSON file into a JSON object
            settingsJSON = (JSONObject) obj;

        } catch (ParseException | IOException e) {

            e.printStackTrace();
            System.exit(1);
        }

        // If settingsJSON stays null then we have a problem and we should
        if (settingsJSON == null) {

            System.out.println("There was a problem parsing JSON file");

            System.exit(1);
        }

        settingsJSON.replace("USD",settingsJSON.get("USD"),1.00);
        settingsJSON.replace("AUD",settingsJSON.get("AUD"),0.67);
        settingsJSON.replace("CNY",settingsJSON.get("CNY"),0.14);
        settingsJSON.replace("JPY",settingsJSON.get("JPY"),0.0094);
        settingsJSON.replace("CAD",settingsJSON.get("CAD"),0.75);

        try (FileWriter file = new FileWriter("src/main/resources/rates.json")) {

            file.write(settingsJSON.toJSONString());

            file.flush();

        } catch (IOException e) {

            e.printStackTrace();
        }
    }

}
