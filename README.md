Test Project Spec Sheet

Create reservation using following required data:
  1. name
  2. Phone number
  3. email address
  4. reservation date
  5. reservation time
  6. number of guests
  7. preferred contact method
  8. POST method
     
  Assumptions:
  1. all data are mandatory
  2. phone number should be 11 characters
  3. number of guests should be between 1 to 5

Cancel reservation:
  1. have a status for reservation to manage the cancelled state
  2. PUT method
     
  Assumptions:
  1. we dont delete data. just change the state to cancelled

View reservations:
  1. enable to view active (not cancelled) and upcoming reservations
  2. GET method
     
  Assumptions:
  1. cancelled reservations are not to be displayed
  2. Sorted by reservation date and time ASCENDING

Update reservations:
  1. enable to update the time and number of guests
  2. PUT method
     
  Assumptions:
  1. only allow to update reservation time and number of guests

Scheduler feature:
  1. Allow notification feature that will send through the preferred contact method of the user
  2. Receive notification 4 hours before reservation time
     
  Assumptions:
  1. User will be notified once only. Implemented by having Boolean state for notified
  2. currently set to run every 10 seconds

*SWAGGER INDEX: http://localhost:8080/swagger-ui/index.html

*H2 Console: 
  URL: jdbc:h2:mem:reservationdb
  user: sa
  password: password
  
**ADDED Postman collection named: Reservation.postman_collection.json
