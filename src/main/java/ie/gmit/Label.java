/* This class generates a label as a JSON Object which
   includes the customer's details (which is received as a Customer object)
   plus a randomly generated unique order ID
 */

package ie.gmit;

import org.json.simple.JSONObject;

import java.util.ArrayList;

public class Label {
    private long uniqueID;
    static private ArrayList<Long> orderIDList = new ArrayList<>();

    public Label(Customer customer) {
        this.uniqueID = generateUniqueID();
        generateLabel(customer);
    }

    public long generateUniqueID() {
        // Generating random number between 100000 and 200000
        long min = 100000;
        long max = 200000;
        boolean alreadyExists = false;

        long ID = (long)(Math.random() * (max - min + 1) + min);

        for (long id : orderIDList) {
            if(id == ID) {
                alreadyExists = true;
            }
            else {
                alreadyExists = false;
            }
        }

        if(alreadyExists == false) {
            orderIDList.add(ID);
        } else {
            // ALREADY EXISTS
            System.out.println("ID already exists - regenerating ID");
            ID = (long)(Math.random() * (max - min + 1) + min);
            // ...
        }

        return ID;
    }

    public void generateLabel(Customer customer) {
        // create JSON object
        JSONObject label = new JSONObject();
        label.put("firstName", customer.getFirstName());
        label.put("lastName", customer.getLastName());
        label.put("email", customer.getEmail());
        label.put("address", customer.getAddress());
        label.put("eircode", customer.getEircode());
        label.put("orderID", this.uniqueID);

        System.out.println(label);
    }
}