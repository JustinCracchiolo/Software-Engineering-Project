package classes;

import java.time.LocalDateTime; // allows to see the job deadline and approximate job duration of the client

public class Client extends User { // utilizes the User class to set client username and password

    private String clientId;
    private int approximateJobDuration; // in minutes or hours
    private LocalDateTime jobDeadline;


    //constructor for the client class; utilizes the constructor of the User class to set the username and password, and also sets the clientId, approximateJobDuration, and jobDeadline.
    public Client(String username, String password, String clientId,
        int approximateJobDuration, LocalDateTime jobDeadline) {
        super(username, password, "");//email is not needed for clients, so we can set it to an empty string
        this.clientId = clientId;
        this.approximateJobDuration = approximateJobDuration;
        this.jobDeadline = jobDeadline;
    }

    public String getClientId() {
        return clientId;
    }

    public int getApproximateJobDuration() {
        return approximateJobDuration;
    }

    public LocalDateTime getJobDeadline() {
        return jobDeadline;
    }

    public void setApproximateJobDuration(int approximateJobDuration) {
        this.approximateJobDuration = approximateJobDuration;
    }

    public void setJobDeadline(LocalDateTime jobDeadline) {
        this.jobDeadline = jobDeadline;
    }
}
