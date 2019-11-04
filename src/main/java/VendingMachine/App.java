/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package VendingMachine;

import VendingMachine.config.VendingMachineConfig;
import VendingMachine.model.VendingMachine;
import VendingMachine.view.CustomerInterface;




public class App {



    public static void main(String[] args) {
        //Only keep the three lines below
        VendingMachineConfig vendingConfig = ConfigReader.readFoodNCashConfigs("src/main/resources/config.json");
        VendingMachine vendingMachine = new VendingMachine(vendingConfig);
        new CustomerInterface(vendingMachine);

    }
}
