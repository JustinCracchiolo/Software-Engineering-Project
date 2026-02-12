 
Software Requirements Specification for
Vehicular Cloud Real Time System (VCRTS)
Prepared by Justin Cracchiolo, Lauren Rodriguez, David Choi, Tristan Huertas, Ivan Lin, Anthony Vallejo, and Sebastian Villavicencio
Software Engineering – CUS 1166
Sunday, February 8, 2026

Table of Contents

1. Introduction

1.1	Purpose	
1.2	Objectives of the Application	
1.3	Stakeholders and Users

2. User Requirements

2.1	Provider Requirements	
2.2	Consumer Requirements	
2.3 Controller Requirements	

3. System Requirements
 
3.1 General System Functional Requirements
3.2	Provider Functional Requirements	
3.3	Consumer Functional Requirements
3.4 Controller Functional Requirements
3.5 Provider Non-functional Requirements
3.6 Consumer Non-functional Requirements
3.7 Controller Non-functional Requirements
3.8 Additional Non-functional Requirements

4. Software Evolution	

4.1	Payment and Billing Integration 	
4.2	Enhanced Authentication and Access Security	
4.3	Multi-Platform and Mobile Support	
4.4	Advanced Scheduling and Prioritization	
4.5	Security Reinforcement and Compliance Controls	
	
-- 

1. Introduction

1.1 Purpose of VCRTS
The purpose of the Vehicular Cloud Real-Time System (VCRTS) is to utilize the unused computing power of parked vehicles by pooling their computational resources and renting them out to consumers who require computing services. Vehicles parked in a dedicated parking lot serve as possible computing nodes. VCRTS manages job execution, resource allocation, real-time monitoring, and reporting to ensure everything is running smoothly. The VCRTS attempts to optimize resources by pulling computational power from unused vehicles. This helps maximize computing power and minimize load on other systems.

1.2 Objectives of VCRTS 

1.2.1  Efficiently utilize idle vehicle computing power
1.2.2  Allow providers to register their vehicles for VCRTS
1.2.3  Allow consumers to register specific jobs that utilize computational power
1.2.4  Execute consumer-submitted computational jobs
1.2.5  Provide real-time job status monitoring of jobs
1.2.6  Support multiple stakeholders with appropriate reporting
1.2.7  Ensure scalability and future extensibility to more vehicles and jobs


1.3 Stakeholders and Users

VCRTS supports three primary stakeholders:
1.3.1  Provider (Vehicle Owner): Supplies a vehicle and its computing power in exchange for incentives, including complimentary fuel as well as a free parking voucher and/or gift card(s). 
1.3.2  Consumer: Submits computational jobs and monitors their execution.
1.3.3  Controller (Administrator): Oversees system operations and monitors system-wide job status.


2. User Requirements

2.1 Provider Requirements

2.1.1 The provider must be able to create and access their accounts and register one or more vehicles.
2.1.2 The provider must be able to register their vehicles for usage on any day of the week for any hours of the day.
2.1.3 The provider must be able to update the hours their vehicle is available and cancel if necessary at any time of the day.
2.1.4 The provider must have access to an end-of-day report of what their vehicle(s) were used for and for how long.
2.1.5 The provider must have access to their incentives page.
2.1.6 A user can see if their vehicles are being used on any given day because there is no guarantee their vehicle will be used any given day.

2.2 Consumer Requirements

2.2.1 The consumer must be able to register, with their account, to use the computing power for any given vehicle for a specific timeframe, on any day of the week.  
2.2.2 A consumer cannot register for more than one vehicle at a time.
2.2.3 The consumer must be able to specify what they are using the computational power for.
2.2.4 The consumer can see how long they have to wait until it is their turn for their job to be run on a vehicle.
2.2.5 The consumer must be able to cancel a job before their time has started. 
2.2.6 Consumers shall receive daily reports related to all of their jobs completed for the day.
2.2.7 Consumers must be notified if their job is canceled or the time is changed. 

2.3 Controller Requirements

2.3.1 The controller gets a report showing all jobs currently being processed by vehicles, to whom those jobs belong, and how long until completion.
2.3.2 The controller has access to the list of all jobs pending, who they belong to, and what time and day they are scheduled for.
2.3.3 Controllers shall be able to cancel a currently processing job for a given vehicle.
2.3.4 Controllers will be able to remove or add a job to a queue for a certain vehicle.
2.3.5 Controllers can mark a vehicle as offline and prohibit its use.
2.3.6 Controllers must be able to view producer and consumer profiles.
2.3.7 Controllers shall be able to submit the respective end-of-day reports to both consumers and producers.

3. System Requirements

3.1 General System Functional Requirements

3.1.1 The system shall allow all users to create accounts with usernames and passwords.
3.1.2 The system shall keep, store, and update all user information in a database.
3.1.3 The system allows each user to log in with their username and password.
3.1.4 The system identifies each user by their unique ID.
3.1.5 The system identifies each vehicle with its VIN number.

3.2 Provider Functional Requirements

3.2.1 The system shall allow a provider to register as many vehicles as they want.
3.2.2 The system shall receive and verify the vehicle's number, license plate, make, and model.
3.2.3 The system shall allow the provider to schedule their vehicle for use on any day of the week for any span of time.
3.2.4 The system shall keep track of the slots a provider's vehicle is scheduled for use and report it to the provider.
3.2.5 The system shall verify that a vehicle is present during its expected schedule by having the producer verify it into the system. If it is not, the system will remove the vehicle from the schedule.
3.2.6 The system shall keep track of how much time the vehicles’ computers are being used for once parked.
3.2.7 The system shall produce an end-of-day report delivered to each provider, including the total amount of time their vehicle was used.

3.3 Consumer Functional Requirements

3.3.1 The system shall allow a consumer to register for only one vehicle within a given timeframe. 
3.3.2 The system retrieves the job from a consumer to be verified by a controller.
3.3.3 If the job is approved, the consumer will be placed into a queue for that vehicle.
3.3.4 The system shall calculate how long until a given consumer's turn for the job to start and will display it to them.
3.3.5 Once a job is in progress, it details to the consumer how much time is left until their job is completed.
3.3.6 If a job is canceled, the consumer will get a notification from the system that their time has changed. They will be removed from the vehicle queue.
3.3.7 A consumer does not get a higher priority in a queue for a canceled vehicle. 
3.3.8 The system shall produce an end-of-day report delivered to each consumer detailing each job completed that day and what time they were completed. 
3.3.9 In the event that two consumers register for the same vehicle at the same time, the system will register one of them for a vehicle of the same computing power or higher for the same time. If the same time is not available, a message will be sent to the consumer notifying them of the time change. 

3.4 Controller Functional Requirements

3.4.1 The system shall allow a view of a database holding all potential providers, vehicles, and consumers.
3.4.2 The system shall allow a controller to view the vehicle schedule for all days of the week.
3.4.3 The system shall allow a controller to edit the schedule and the queue for a given vehicle.
3.4.4 The system shall prompt a controller when a new job is being requested. If a job is accepted, it will be added to the schedule.
3.4.5 If a controller rejects a job, it will notify the consumer.
3.4.6 The system shall verify that a vehicle is present during its expected schedule. If it is not, it will remove the vehicle from the schedule.
3.4.7 The system shall give a report of all vehicle jobs in progress or pending.
3.4.8 The system shall give a view of all jobs scheduled in a queue for a given vehicle.
3.4.9 If the queue for a vehicle is lengthy, it will spread out the jobs over different vehicles.
3.4.10 If the time of all jobs in a queue is longer than the time the vehicle is there, the jobs will be spread out to different vehicles. A consumer will no longer be allowed to pick that vehicle.

3.5 Provider Non-functional Requirements

3.5.1 The system shall verify vehicle details within 10 seconds of the data being received.
3.5.2 End-of-day reports shall be generated and delivered to providers within 30 minutes of the day’s end.
3.5.3 The system shall be able to handle at least 500 registered vehicles concurrently without ruining system performance.
3.5.4 The scheduling and registration account shall be available 99.9% of the time to ensure providers can manage their vehicles when needed. 
3.5.5 Only authenticated providers shall be able to modify their vehicle registration or view their reports.

3.6 Consumer Non-functional Requirements

3.6.1 A first-time user should be able to create an account and register a job in under 5 minutes.
3.6.2 The system shall ensure that a consumer’s job data and results are isolated from all other consumers and the vehicle provider.
3.6.3 End-of-day reports shall be generated within 30 minutes of the final job completion of the day.

3.7 Controller Non-functional Requirements

3.7.1 The system shall retain logs (not the data) for 6 months.
3.7.2 The system shall update job status and progress every 1 second.
3.7.3 The controller dashboard shall be available 99.9% of the time during system operating hours.
​3.7.4 In case of a component failure, the controller interface shall recover and become operational again within 60 seconds.
3.7.5 The controller will be able to use the system efficiently after 2 hours of training. Afterwards, the number of mistakes made will not exceed 10 per day. 

3.8 Additional Non-functional Requirements

3.8.1 The system will be up 24 hours a day, 7 days a week. 
3.8.2 The system will provide legal notifications to providers about how their vehicles will be used.
3.8.3 If the system crashes, it should be up and running again in 5 minutes. 
​3.8.4 If a queue exceeds 10 jobs or exceeds the amount of time a vehicle is there, the spread out of the jobs will begin.

4. Software Evolution

4.1 Payment and Billing Integration

4.1.1 The system shall support the integration of payment and billing mechanisms.
4.1.2 A secure payment gateway, such as Stripe or PayPal, will be implemented.
4.1.3 Automated invoicing and billing schedules may be added.
4.1.4 Multiple payment methods, such as credit/debit cards and digital wallets, will be allowed.
4.1.5 Integration with accounting software for financial reporting will be developed.

4.2 Enhanced Authentication and Access Security

4.2.1 The system shall provide two-factor authentication (2FA).
4.2.2 Using SMS or email-based one-time passwords (OTPs) will be supported.
4.2.3 Authenticator apps like Google Authenticator or Authy will be supported.
4.2.4 Biometric options, such as fingerprint or face recognition, may be developed.
4.2.5 The system shall provide backup codes for account recovery.

4.3 Multi-Platform and Mobile Support

4.3.1 The system shall support additional platforms, including mobile applications such as iOS and Android apps.
4.3.2 A responsive web framework for tablets and smartphones will be used.
4.3.3 Push notifications for alerts to the given user will be implemented.
4.3.4 User experience across devices shall be consistent.

4.4 Advanced Scheduling and Prioritization

4.4.1 The system shall implement advanced scheduling and prioritization algorithms.
4.4.2 AI or machine learning algorithms may be used in order to optimize task assignments.
4.4.3 Conflict detection and resolution will be implemented to prevent overlapping schedules.
4.4.4 Dynamic re-prioritization for high-priority tasks will be developed.
4.4.5 A visual calendar interface with drag-and-drop functionality may be created.

4.5 Security Reinforcement and Compliance Controls

4.5.1 The system shall include expanded security features and compliance controls.
4.5.2 All sensitive data in storage and transit will be encrypted.
4.5.3 Role-based access control for different user types will be implemented.
4.5.4 Audit logs will be maintained to track user actions.

