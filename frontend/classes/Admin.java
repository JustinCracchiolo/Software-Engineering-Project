package classes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Admin extends User {
    private String AdminId;
    private static Map<String, ArrayList<Vehicle>> pendingVehicles= new HashMap<>();;

    public Admin(String username, String password) {
        this(username, password, "");
    }

    public Admin(String username, String password, String email) {
        super(username, password, email, "Admin");

    }

    public void addPendingVehicle(User u, Vehicle v) {
        if(pendingVehicles.containsKey(u.getUserId())) {
            pendingVehicles.get(u.getUserId()).add(v);
        }
        else {
            ArrayList<Vehicle> list = new ArrayList<>(); 
            list.add(v); 
            pendingVehicles.put(u.getUserId(), list);
        }
        allowVehicle(u, v);

    }

    public void allowVehicle(User u, Vehicle v) { 
        String userId = u.getUserId();
        for(int i = 0; i < pendingVehicles.get(userId).size(); i++) {
            if(pendingVehicles.get(userId).get(i).getNumber().equals(v.getNumber())) {
                ((Owner) u).addVehicle(pendingVehicles.get(userId).get(i));
                pendingVehicles.get(userId).remove(i);
                break;
            }
        }
        
    }

}
