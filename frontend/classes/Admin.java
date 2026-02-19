/* Project: Vehicular Cloud Real Time System (VCRTS)
 * Class: Admin.java
 * Authors: Group 2 (Justin Cracchiolo, Lauren Rodriguez, David Choi, Tristan Huertas, Ivan Lin, Anthony Vallejo, and Sebastian Villavicencio)
 * Date: February 2026
 * The Admin class represents an administrator in the VCRTS system who manages the system and approves or rejects jobs and vehicles.
 */
package classes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Admin extends User {
    private String AdminId;
    private static Map<String, ArrayList<Vehicle>> pendingVehicles= new HashMap<>();;
    private static Map<String, ArrayList<Job>> pendingJobs = new HashMap<>();

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

    public void addPendingJob(User u, Job j) {
        if(pendingJobs.containsKey(u.getUserId())) {
            pendingJobs.get(u.getUserId()).add(j);
        }
        else {
            ArrayList<Job> list = new ArrayList<>(); 
            list.add(j); 
            pendingJobs.put(u.getUserId(), list);
        }
        allowJob(u, j);

    }

    public void allowJob(User u, Job j) { 
        String userId = u.getUserId();
        for(int i = 0; i < pendingJobs.get(userId).size(); i++) {
            if (pendingJobs.get(userId).get(i).getJob().equals(j.getJob())) {
                ((Owner) u).addJob(pendingJobs.get(userId).get(i));
                pendingJobs.get(userId).remove(i);
                break;
            }
        }
        
    }

}
