
/**
 * This class represents a car. 
 * It stores information about the car's registration number, make, model, and year.
 *
 * @author (Aditya Roy)
 * @version (13/05/2024)
 */   
public class Car
{
    private String registrationNumber; // stores car registration number
    private String make;  // stores car make
    private String model; // stores car model
    private String year; // stores car year

    /**
    Constructor for objects of class Car
    **/
    public Car(String registration, String make, String model, String year)
    {
        this.registrationNumber = registration;
        this.make = make;
        this.model = model;
        this.year = year;
    }
    
    /**
    Getter and setter methods for the private variable 'Registration'
    **/
    public String getRegistration(){
        return this.registrationNumber;
    }
    public void setRegistration(String registration){
        this.registrationNumber = registration;
    }
    
    /**
    Getter and setter methods for the private variable 'Make'
    **/
    public String getMake(){
        return this.make;
    }
    public void setMake(String make){
        this.make = make;
    }
    
    /**
    Getter and setter methods for the private variable 'Model'
    **/
    public String getModel(){
        return this.model;
    }
    public void setModel(String model){
        this.model = model;
    }
    
    /**
    Getter and setter methods for the private variable 'Year'
    **/
    public String getYear(){
     return this.year;
    }
    public void setYear(String year){
        this.year = year;
    }
}
