package VendingMachine;

import VendingMachine.config.CashConfig;
import VendingMachine.config.FoodConfig;
import VendingMachine.config.VendingMachineConfig;
import VendingMachine.model.CashEnum;
import VendingMachine.model.FoodEnum;
import org.apache.commons.lang3.EnumUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

@SuppressWarnings("unchecked")
public class ConfigReader {
    public static VendingMachineConfig readFoodNCashConfigs (String relPathToConfigFile) throws IllegalArgumentException {
        /* Create a new jsonParser */
        JSONParser jsonParser = new JSONParser();
        JSONObject settingsJSON = null;
        try (FileReader reader = new FileReader(relPathToConfigFile)) {
            Object obj = jsonParser.parse(reader);
            //Read JSON file into a JSON object
            settingsJSON = (JSONObject) obj;
        } catch (ParseException | IOException e) {
            throw new IllegalArgumentException("File could not be found. Make sure your file path is correct");
        }
        /* If settingsJSON stays null then we have a problem  */
        if (settingsJSON == null) {
            throw new IllegalArgumentException("JSON could not be parsed");
        }

        /* Initialise our platform array list */
        ArrayList<FoodConfig> foodConfigs = new ArrayList<>();
        /* Get array of platforms from JSON file */
        JSONArray foodArr = (JSONArray) settingsJSON.get("food");
        /* Iterate through each platform */
        Iterator<JSONObject> iterator = foodArr.iterator();
        while (iterator.hasNext()) {
            JSONObject foodJSON = iterator.next();
            /* Obtain all platform values from JSON object and cast into relevant types */
            int id = (int) (long) foodJSON.get("id");
            String name = (String) foodJSON.get("name");
            String type = (String) foodJSON.get("type");
            double price = (double) foodJSON.get("price");
            int quantity = (int) (long)  foodJSON.get("quantity");
            if (!EnumUtils.isValidEnum(FoodEnum.class, type.toUpperCase())) {
                throw new IllegalArgumentException("Invalid enemy color provided.");
            }
            /* Add to our array list of platforms. */
            foodConfigs.add(new FoodConfig(
                    id,
                    name,
                    FoodEnum.valueOf(type.toUpperCase()),
                    price,
                    quantity
            ));
        }

        /* Initialise our platform array list */
        ArrayList<CashConfig> cashConfigs = new ArrayList<>();
        /* Get array of platforms from JSON file */
        JSONArray cashArr = (JSONArray) settingsJSON.get("cash");
        /* Iterate through each platform */
        Iterator<JSONObject> cashIterator = cashArr.iterator();
        while (cashIterator.hasNext()) {
            JSONObject cashJSON = cashIterator.next();
            /* Obtain all platform values from JSON object and cast into relevant types */
            int id = (int) (long) cashJSON.get("id");
            String name = (String) cashJSON.get("name");
            String type = (String) cashJSON.get("type");
            double value = (double) cashJSON.get("value");
            int quantity = (int) (long)  cashJSON.get("quantity");
            if (!EnumUtils.isValidEnum(CashEnum.class, type.toUpperCase())) {
                throw new IllegalArgumentException("Invalid enemy color provided.");
            }
            /* Add to our array list of platforms. */
            cashConfigs.add(new CashConfig(
                    id,
                    name,
                    CashEnum.valueOf(type.toUpperCase()),
                    value,
                    quantity
            ));
        }

        return new VendingMachineConfig(foodConfigs, cashConfigs);
    }

    /**
     * Generate the HashMap which store the currency
     * @param filePath the file path of rate json file
     * @return
     */
    public static HashMap<String, Double> readRateConfigs(String filePath) {
        HashMap<String, Double> fxRates = null;
        try {
            Object obj = new JSONParser().parse(new FileReader(filePath));
            JSONObject jo = (JSONObject) obj;
            fxRates = (HashMap) jo.get("rate");
        }catch(ParseException | IOException e){
            e.printStackTrace();
            System.exit(1);
        }

        // If settingsJSON stays null then we have a problem and we should
        if (fxRates == null) {
            System.out.println("There was a problem parsing JSON file");
            System.exit(1);
        }
        return fxRates;
    }
}
