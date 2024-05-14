/**
 * This class represents a car park. 
 * It manages a collection of parking spots and provides functionalities 
 * for adding, removing, viewing, and searching for parking spots and parked cars.
 *
 * @author (Aditya Roy)
 * @version (13/05/2024)
 */
import java.util.ArrayList;

public class CarPark
{
    private ArrayList<ParkingSpot> parkingSpots;
    
    /**
    Constructor that initializes an empty ArrayList to store parking spots.
    **/
    public CarPark()
    {
        this.parkingSpots = new ArrayList<ParkingSpot>();
    }
    
    /**
    Validates a parking slot identifier.
    A valid identifier is a string of length 4, starting with an uppercase letter followed by 3 digits.
    @param identifier the string to be validated.
    @return true if the identifier is valid, false otherwise.
    **/
    public boolean validateIdentifier(String identifier){
        if(identifier == null ) return false;
        if(identifier.length() == 4){
            char firstCharacter = identifier.charAt(0);
            String rest = identifier.substring(1);
            if(Character.isUpperCase(firstCharacter)){
                try{
                    Integer.parseInt(rest);
                    return true;
                }
                catch(Exception e){
                    return false;                
                }
            }else{
                return false;
            }
        }else{
            return false;
        }
    }
    
    /**
    Validates a car registration number.
    A valid registration number is a string of length 5, starting with an uppercase letter followed by 4 digits.
    @param registration the string to be validated.
    @return true if the registration number is valid, false otherwise.
    **/
    public boolean validateRegistration(String registration){
        if(registration.length() == 5){
            char firstCharacter = registration.charAt(0);
            String rest = registration.substring(1);
            if(Character.isUpperCase(firstCharacter)){
                try{
                    Integer.parseInt(rest);
                    return true;
                }
                catch(Exception e){
                    return false;                
                }
            }else{
                return false;
            }
        }else{
            return false;
        }
    }
    
    /**
    Validates a car year.
    A valid car year is an integer between 2004 (inclusive) and 2024 (inclusive).
    @param year the string to be validated.
    @return true if the year is valid, false otherwise.
    **/
    public boolean validateYear(String year){
        int year_integer;
        try{
            year_integer = Integer.parseInt(year);
        }catch(Exception e){
            return false;
        }
        return (year_integer > 2003 && year_integer < 2025);
    }
    
    /**
    Adds a new parking spot to the car park with the given identifier.
    @param identifier a unique identifier for the parking spot.
    @return false if a parking spot with the same identifier already exists, true otherwise.
    **/
    public boolean addSpot(String identifier){
        boolean exists = false;
        for(int i = 0; i < parkingSpots.size(); i++){
            if(parkingSpots.get(i).getIdentifier().equals(identifier)){
                exists = true;
                break;
            }
        }
        
        if(exists){
            return false;
        }
        else{
            ParkingSpot parkingSpot = new ParkingSpot(identifier);
            parkingSpots.add(parkingSpot);
            return true;
        }
    }
    
    /**
    Deletes a parking spot from the car park.
    @param spotIdentifier the identifier of the parking spot to be deleted.
    @return 0 if slot doesn't exist, 1 if slot is not vacant, 2 othewise.
    **/
    public int deleteSpot(String spotIdentifier){
        int index = findSpot(spotIdentifier);
        if(index < 0){
            return 0; // slot doesn't exist
        }else if(!parkingSpots.get(index).getStatus()){
            return 1; // slot is not empty
        }else{
            parkingSpots.remove(index);
            return 2; // success
        }
    }
    
    /**
    Get method for ArrayList<ParkingSpot>.
    @returns ArrayList of ParkingSpot objects containing information about all parking spots including 
    details of each parking spot (identifier, occupied status, car information if occupied etc.).
    **/
    public ArrayList<ParkingSpot> viewAllSpots(){
        return this.parkingSpots;
    }
    
    /**
    Parks a car in a specified parking spot.
    @param identifier the identifier of the parking spot
    @param registration the registration number of the car
    @param make the make of the car
    @param model the model of the car
    @param year the year of the car
    @return 0 if slot doesn't exist, 1 if slot not vacant, 2 if car found in another slot, 3 otherwise.
    **/
    public int parkCar(String identifier, String registration, String make, String model, int year){
        Car car = new Car(registration, make, model, Integer.toString(year));
        
        int index = findSpot(identifier);
        
        if(index < 0){
            return 0; // slot doesn't exist
        }else if(!parkingSpots.get(index).getStatus()){
            return 1; // slot occupied already
        }else{
            int carIndex = findCar(registration);
            if(carIndex != -1) return 2; // car in another slot
            else{
                parkingSpots.get(index).addCar(car);
                return 3; // success
            }
        }
    }
    
    /**
    Finds the index of a parking spot in the list based on its identifier.
    @param identifier the identifier of the parking spot
    @return the index of the parking spot in the list, or -1 if not found
    **/
    public int findSpot(String identifier){
        int index = -1;
        for(int i = 0; i < parkingSpots.size(); i++){
            if(parkingSpots.get(i).getIdentifier().equals(identifier)){
                index = i;
                break;
            }
        }
        return index;
    }
    
    /**
    Finds the index of a parking spot in the list based on its parked car registration.
    @param registration of the car
    @return the index of the parking spot in the list, or -1 if not found
    **/
    public int findCar(String registration){
        int index = -1;
        for(int i = 0; i < parkingSpots.size(); i++){
            if(!parkingSpots.get(i).getStatus()){
                if(parkingSpots.get(i).getCar().getRegistration().equals(registration)){
                    index = i;
                    break;
                }
            }
        }
        return index;
    }
    
    /**
    Uses findCar(String registration) function to find a car and sets highlight variable of Parking Spot to true.
    @param registration of the car
    @return true if car found, false otherwise.
    **/
    public boolean findCarByRegistration(String registration){
        int index = findCar(registration);
        
        if(index < 0){
            return false; // car not found || doesn't exist
        }else{
            parkingSpots.get(index).setHighlightSpot(true);
            return true; // success
        }
    }
    
    /**
    Uses findSpot(String identifier) function to find a identifier and sets highlight variable of Parking Spot to true.
    @param identifier of the Spot
    @return true if identifier found, false otherwise.
    **/
    public boolean findIdentifier(String identifier){
        int index = findSpot(identifier);
        
        if(index < 0){
            return false; // identifier not found || doesn't exist
        }else{
            parkingSpots.get(index).setHighlightSpot(true);
            return true; // success
        }
    }
    
    /**
    Uses findCar(String registration) function to find a car.
    Removes the car from parking if found.
    @param registration of the car
    @return false if car doesnt't exist, true othewise.
    **/
    public boolean removeCarByRegistration(String registration){
        int index = findCar(registration);
        if(index < 0){
            return false; // car doesnt exist
        }else{
            parkingSpots.get(index).removeCar();
            return true; // success
        }
    }
    
    /**
    Searches for a car by its make in the car park, sets highlight variable of Parking Spot to true if found.
    @param the make of the car to search for (e.g., Toyota, Honda)
    @return true if found, false otherwise.
    **/
    public boolean findCarByMake(String make){
        int index = -1;
        for(int i = 0; i < parkingSpots.size(); i++){
            if(!parkingSpots.get(i).getStatus()){
                if(parkingSpots.get(i).getCar().getMake().toLowerCase().equals(make.toLowerCase())){
                    index = i;
                    parkingSpots.get(i).setHighlightSpot(true);
                }
            }
            
        }
        
        if(index < 0){
            return false;
        }else{
            return true;    
        }
    }
    
    /**
    Searches for a car by its model in the car park, sets highlight variable of Parking Spot to true if found.
    @param the model of the car to search for (e.g., Corolla)
    @return true if found, false otherwise.
    **/
    public boolean findCarByModel(String model){
        int index = -1;
        for(int i = 0; i < parkingSpots.size(); i++){
            if(!parkingSpots.get(i).getStatus()){
                if(parkingSpots.get(i).getCar().getModel().toLowerCase().equals(model.toLowerCase())){
                    index = i;
                    parkingSpots.get(i).setHighlightSpot(true);
                }
            }
            
        }
        
        if(index < 0){
            return false; // car doesn't exist || not found
        }else{
            return true; // success
        }
    }
    
    /**
    Removes all the car from all objects in parkingSpots arraylist.
    **/
    public void reset(){
        parkingSpots.forEach((e) -> {
            e.removeCar();
        });
    }
    
    /**
    Resets highlight spot to false from all objects in parkingSpots arraylist.
    **/
    public void resetSearch(){
        parkingSpots.forEach((e) -> {
            e.setHighlightSpot(false);
        });
    }
}
