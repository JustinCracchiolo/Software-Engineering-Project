/* Project: Vehicular Cloud Real Time System (VCRTS)
 * Class: Job.java
 * Authors: Group 2 (Justin Cracchiolo, Lauren Rodriguez, David Choi, Tristan Huertas, Ivan Lin, Anthony Vallejo, Sebastian Villavicencio)
 * Date: February 2026
 * The Job class represents a Job submitted into the VCRTS system. It stores job information including job ID,
 * estimated job duration, and job deadline. The class validates that all required job information is provided during object creation.
 */

package classes;

// ---------------------------------------------------------------
// allows to see the job deadline and approximate job duration of the client
import java.time.LocalDateTime;
// ---------------------------------------------------------------
// job class
//The system shall receive and verify the vehicle's jobId, approximateJobDuration, and jobDeadline.

public class Job {
    private final String JOB_ID;
    private int approximateJobDuration; // in minutes or hours
    private LocalDateTime jobDeadline;

    // ---------------------------------------------------------------
    // constructor
    public Job(String JOB_ID, int approximateJobDuration, LocalDateTime jobDeadline) {
        this.JOB_ID = JOB_ID;
        this.approximateJobDuration = approximateJobDuration;
        this.jobDeadline = jobDeadline;

        // make sure job has all the necessary information
        if (JOB_ID.equals("") || approximateJobDuration <= 0 || jobDeadline == null) {
            throw new IllegalArgumentException("Job information is incomplete");
        }
    }

    // implementing the getters and setters in order to access the private variables

    // ---------------------------------------------------------------
    // This method returns the job ID for a specific job.
    public String getJob() {
        return JOB_ID;
    }

    // ---------------------------------------------------------------
    // This method returns the approximate job duration for a specific job.
    public int getApproximateJobDuration() {
        return approximateJobDuration;
    }

    // ---------------------------------------------------------------
    // This method returns the deadline for a specific job.
    public LocalDateTime getJobDeadline() {
        return jobDeadline;
    }

    // ---------------------------------------------------------------
    // This method updates the approximate job duration for a specific job.
    public void setApproximateJobDuration(int approximateJobDuration) {
        this.approximateJobDuration = approximateJobDuration;
    }

    // ---------------------------------------------------------------
    // This method updates the deadline for a specific job.
    public void setJobDeadline(LocalDateTime jobDeadline) {
        this.jobDeadline = jobDeadline;
    }

}
