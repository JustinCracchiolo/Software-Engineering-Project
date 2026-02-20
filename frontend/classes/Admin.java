/* Project: Vehicular Cloud Real Time System (VCRTS)
 * Class: Admin.java
 * Authors: Group 2 (Justin Cracchiolo, Lauren Rodriguez, David Choi, Tristan Huertas, Ivan Lin, Anthony Vallejo, and Sebastian Villavicencio)
 * Date: February 2026
 * The Admin class represents an administrator in the VCRTS system who manages the system and approves or rejects jobs and vehicles.
 */
package classes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Admin extends User {
    private String adminId;
    private static int increment = 0;
    private static Map<User, ArrayList<Vehicle>> pendingVehicles= new HashMap<>();;
    private static Map<User, ArrayList<Job>> pendingJobs = new HashMap<>();

    public Admin(String username, String password) {
        this(username, password, "");
    }

    public Admin(String username, String password, String email) {
        super(username, password, email, "Admin");
        increment++;
        adminId = Integer.toString(increment);
        
    }

    public static Map<User, ArrayList<Vehicle>> getPendingVehicles() {
        return pendingVehicles;
    }

    public static Map<User, ArrayList<Job>> getPendingJobs() {
        return pendingJobs;
    }

    public String getAdminId() {
        return adminId;
    }

    public static void addPendingVehicle(User u, Vehicle v, boolean add_to_pending_file) {
        if(pendingVehicles.containsKey(u)) {
            pendingVehicles.get(u).add(v);
        }
        else {
            ArrayList<Vehicle> list = new ArrayList<>(); 
            list.add(v); 
            pendingVehicles.put(u, list);
        }
        if(add_to_pending_file) {
            UserManager.updatePendingFile(u, v);
        }
        
        //allowVehicle(u, v);

    }

    public static void allowVehicle(User u, Vehicle v) { 
        for(int i = 0; i < pendingVehicles.get(u).size(); i++) {
            if(pendingVehicles.get(u).get(i).getNumber().equals(v.getNumber())) {
                ((Owner) u).addVehicle(pendingVehicles.get(u).get(i));
                pendingVehicles.get(u).remove(i);
                
                UserManager.updateVehiclesFile(u);

                //add call to move it from pending to reserved as well
                try { 
                    UserManager.transactionUpdate(v.getNumber(), true); 
                } catch (IOException ex) { 

                }

                break;
            }
        }
        
    }

    public static void rejectVehicle(User u, Vehicle v) { 
        for(int i = 0; i < pendingVehicles.get(u).size(); i++) {
            if(pendingVehicles.get(u).get(i).getNumber().equals(v.getNumber())) {
                pendingVehicles.get(u).remove(i);
                
                //add call to move it from pending to reserved as well
                try { 
                    UserManager.transactionUpdate(v.getNumber(), false); 
                } catch (IOException ex) { 

                }

                break;
            }
        }
        
    }

    public static void addPendingJob(User u, Job j, boolean add_to_pending_file) {
        if(pendingJobs.containsKey(u)) {
            pendingJobs.get(u).add(j);
        }
        else {
            ArrayList<Job> list = new ArrayList<>(); 
            list.add(j); 
            pendingJobs.put(u, list);
        }

        if(add_to_pending_file) {
            UserManager.updatePendingFile(u, j);
        }
        
        // allowJob(u, j);

    }

    public static void allowJob(User u, Job j) { 
        for(int i = 0; i < pendingJobs.get(u).size(); i++) {
            if (pendingJobs.get(u).get(i).getJobId().equals(j.getJobId())) {
                ((Client) u).addJob(pendingJobs.get(u).get(i));
                pendingJobs.get(u).remove(i);

                UserManager.updateJobFile(u);

                try { 
                    UserManager.transactionUpdate(j.getJobId(), true); 
                } catch (IOException ex) { 

                }

                break;
            }
        }
        
    }

    public static void rejectJob(User u, Job j) { 
        for(int i = 0; i < pendingJobs.get(u).size(); i++) {
            if (pendingJobs.get(u).get(i).getJobId().equals(j.getJobId())) {
                pendingJobs.get(u).remove(i);

                try { 
                    UserManager.transactionUpdate(j.getJobId(), false); 
                } catch (IOException ex) { 

                }
                
                break;
            }
        }
        
    }


}
