/**
 * This class represents a parking spot in a car park.
 * It stores information about the parking spot identifier, its occupied status, and the parked car (if any).
 *
 * @author (Aditya Roy)
 * @version (13/05/2024)
 */
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;    
public class ParkingSpot
{
    private String identifier; // stored spot identifier
    private boolean isEmpty; // stores if the spot is empty or not
    private Car car; // stores car object in the spot
    private boolean highlight; // used for search highlight functionalities
    private String parkedTime; // used to store to keep track of parking time
    private DateTimeFormatter dtf; // used to format the stored parking time
    
    /**
    Constructor that initializes the parking spot identifier and sets the initial status to empty.
    @param identifier a unique identifier for the parking spot
    **/
    public ParkingSpot(String identifier)
    {
        this.identifier = identifier;
        this.isEmpty = true;
        this.highlight = false;
        dtf = DateTimeFormatter.ofPattern("MMM dd, yyyy || hh:mm a"); 
    }
    
    /**
    Set & get methods for the variable named "highlight".
    **/
    public void setHighlightSpot(boolean status){
        this.highlight = status;
    }
    public boolean getHighlightSpot(){
        return this.highlight;
    }
    
    /**
    Returns the value of parkedTime variable.
    **/    
    public String getDateTime(){
        return this.parkedTime;
    }
    
    /**
    Parks a car in this parking spot and updates parkedTime to current time.
    Sets the occupied status to true and stores the car object.
    @param car the car to be parked
    **/
    public void addCar(Car car){
        this.isEmpty = false;
        this.car = car;
        this.parkedTime = dtf.format(LocalDateTime.now());
    }
    
    /**
    Retrieves the car parked in this spot.
    @return the parked car object, or null if the spot is empty
    **/
    public Car getCar(){
        return this.car;
    }
    
    /**
    Removes a car from this parking spot.
    Sets the occupied status to empty and removes the car object. 
    **/
    public void removeCar(){
        this.isEmpty = true;
        this.car = null;
    }
    
    /**
    Checks if the parking spot is empty.
    @return true if the spot is empty, false otherwise
    **/
    public boolean getStatus(){
        return this.isEmpty;
    }
    
    /**
    Gets the identifier of the parking spot.
    @return the parking spot identifier
    **/
    public String getIdentifier(){
        return this.identifier;
    }
}
