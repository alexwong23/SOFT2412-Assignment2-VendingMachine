package VendingMachine.User;

import VendingMachine.model.Inventory;

public interface Staff extends User{

    //Specs stated: A staff can fill items in the vending machine to the maximum capacity. This should be implemented as Fill command

   Inventory fill();


}
