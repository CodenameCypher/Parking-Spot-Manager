/**
 * This class create the graphical interface for the Parking Spot application.
 * Manages all necessary GUI components.
 * Contains a constructor, main, refreshSpotPanel, addPanels, addButtons, addButtonActionListeners method.
 *
 * @author (Aditya Roy)
 * @version (13/05/2024)
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GUI
{
    private JFrame frame; // frame instance used in this application
    private JPanel headerPanel, buttonPanel, spotPanel; // panel instances used in this application
    private JButton 
    addButton, 
    removeButton, 
    parkSpot, 
    removeCarByReg, 
    reset, 
    exit, 
    resetSearch, 
    findIdentifier, 
    findCar; // button instances used in this application
    private CarPark cp = new CarPark(); // CarPark instance
    
    // colors used in the Application
    final private Color buttonPanelColor = new Color(47,79,79);
    final private Color buttonColor = new Color(77, 128, 128);
    final private Color availableSpotColor = new Color(179, 255, 179);
    final private Color occupiedSpotColor = new Color(255, 179, 179);
    final private Color highlightedSpotColor = new Color(255, 255, 179);
    
    /**
    Constructor for GUI class.
    Initializes frame object, sets layout.
    Calls addPanels(), addButtons(), addButtonActionListeners() to add necessary panels, buttons and action listeners to them.
    Sets frame defaults.
    */
    public GUI(){
        frame = new JFrame(); // initializing new Frame
        frame.setLayout(new BorderLayout()); // setting frame layout
        
        addPanels(); // add all panels to the frame
        addButtons(); // add all buttons to buttonPanel
        addButtonActionListeners(); // add action listeners to the buttons
        
        // Show the Frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // frame default close operation
        frame.setTitle("Parking Spot Manager - 104671426"); // setting frame title
        frame.pack(); // frame resizability to contain contents
        frame.setLocationRelativeTo(null); // force frame to start in the center
        frame.setVisible(true); // frame visibility
    }
    
    /**
    Removes everything from the spot panel.
    Adds JButton instances utilizing information fromrefreshed ArrayList for CarPark instance.
    Sets JButton UI Configuration and Label.
    Sets JButton action listener.
    Revalidates and repaints spot panel.
    **/
    private void refreshSpotPanel(){
        spotPanel.removeAll(); // removes all components from the panel
        ArrayList<ParkingSpot> parkingSpots = cp.viewAllSpots(); // parking spots arraylist
        parkingSpots.forEach((parkingSpot) -> {
            JButton jb = new JButton(parkingSpot.getIdentifier()); // creates a new JButton instance
            jb.setFocusPainted(false); // sets not to focus on the JButton texts
            jb.setFont(new Font("Courier", Font.PLAIN, 15)); // font configuration for the JButton
            if(parkingSpot.getStatus()){ // spot empty
                jb.setBackground(parkingSpot.getHighlightSpot() ? this.highlightedSpotColor : this.availableSpotColor); // sets JButton Background Color
                jb.setText( // sets JButton Text
                    "<html><center><b>"
                    +"Spot ID:</b> "+parkingSpot.getIdentifier()+" (Vacant)<br>"
                    +"</center></html>"
                );
                jb.addActionListener(new ActionListener(){ // sets JButton Action Listener
                    public void actionPerformed(ActionEvent e){
                        JOptionPane.showMessageDialog(frame,
                        "Parking spot: " + parkingSpot.getIdentifier() + " is vacant at the moment!",
                        "Car Details",
                        JOptionPane.INFORMATION_MESSAGE);
                    }
                });
            }else{ // spot occupied
                jb.setBackground(parkingSpot.getHighlightSpot() ? this.highlightedSpotColor : this.occupiedSpotColor); // sets JButton Background Color
                jb.setText( // sets JButton Text
                    "<html><center><b>"
                    +"Spot ID:</b> "+parkingSpot.getIdentifier()+" (Occupied)<br>"
                    +"<b><i>Car Registration:</b></i> "+parkingSpot.getCar().getRegistration()+"<br>"
                    +"<small>See Details</small>"
                    +"</center></html>"
                );
                jb.addActionListener(new ActionListener(){ // sets JButton Action Listener
                    public void actionPerformed(ActionEvent e){
                        JOptionPane.showMessageDialog(frame,
                        "<html><center><b><i>Car Registration:</b></i> "+parkingSpot.getCar().getRegistration()+"<br>"
                        +"<b><i>Make:</b></i> "+ parkingSpot.getCar().getMake() +"<br>"
                        +"<b><i>Model:</b></i> "+ parkingSpot.getCar().getModel() +"<br>"
                        +"<b><i>Year:</b></i> "+ parkingSpot.getCar().getYear() +"<br>"
                        +"<b><i>Parked at:</b></i> "+parkingSpot.getDateTime()
                        +"</html></center>",
                        "Car Details",
                        JOptionPane.INFORMATION_MESSAGE);
                    }
                });
            }
            spotPanel.add(jb); // adds the button to spot panel
        });
        // revalidate and repaint spotPanel
        spotPanel.revalidate(); 
        spotPanel.repaint();
    }

    /**
    Initializes and adds headerPanel, buttonPanel & spotPanel to the frame.
    Sets all the necessary UI configuration to the panels.
    Adds a label to the headerPanel with necessary UI configurations.
    **/
    private void addPanels(){
        // header panel
        headerPanel = new JPanel();
        headerPanel.setPreferredSize(new Dimension(300, 80));
        headerPanel.setLayout(new BorderLayout());
        headerPanel.setBackground(buttonPanelColor);
        frame.add(headerPanel, BorderLayout.PAGE_START);
        
        // label in header panel
        JLabel centerLabel = new JLabel("Parking Spot Manager", SwingConstants.CENTER);
        centerLabel.setFont(new Font("Monospaced", Font.BOLD | Font.ITALIC, 40));
        centerLabel.setForeground(Color.white);
        headerPanel.add(centerLabel, BorderLayout.CENTER);
        
        // button panel
        buttonPanel = new JPanel();
        buttonPanel.setPreferredSize(new Dimension(400, 600));
        buttonPanel.setBackground(buttonPanelColor);
        buttonPanel.setLayout(new GridLayout(5,2,10,10));
        frame.add(buttonPanel, BorderLayout.LINE_START);
               
        // spot panel
        spotPanel = new JPanel(new BorderLayout());
        spotPanel.setPreferredSize(new Dimension(500, 100));
        spotPanel.setBackground(Color.white);
        spotPanel.setLayout(new GridLayout(5,5,10,10));
        frame.add(spotPanel, BorderLayout.CENTER);
    }
    
    /**
    Initializes and adds all the buttons to buttonsPanel.
    Sets all the necessary UI configuration to the buttons.
    **/
    private void addButtons(){
        // add button
        addButton = new JButton();
        addButton.setText("<html><center>"+"Add"+"<br>"+"Spot"+"</center></html>");
        addButton.setBackground(buttonColor);
        addButton.setForeground(Color.white);
        addButton.setFont(new Font("Monospaced", Font.BOLD, 20));
        addButton.setFocusPainted(false);
        buttonPanel.add(addButton);
        
        // remove button
        removeButton = new JButton();
        removeButton.setText("<html><center>"+"Remove"+"<br>"+"Spot"+"</center></html>");
        removeButton.setBackground(buttonColor);
        removeButton.setForeground(Color.white);
        removeButton.setFont(new Font("Monospaced", Font.BOLD, 20));
        removeButton.setFocusPainted(false);
        buttonPanel.add(removeButton);
        
        // park spot button
        parkSpot = new JButton();
        parkSpot.setText("<html><center>"+"Park"+"<br>"+"Car"+"</center></html>");
        parkSpot.setBackground(buttonColor);
        parkSpot.setForeground(Color.white);
        parkSpot.setFont(new Font("Monospaced", Font.BOLD, 20));
        parkSpot.setFocusPainted(false);
        buttonPanel.add(parkSpot);
        
        // remove car button
        removeCarByReg = new JButton();
        removeCarByReg.setText("<html><center>"+"Remove<br>Car"+"</center></html>");
        removeCarByReg.setBackground(buttonColor);
        removeCarByReg.setForeground(Color.white);
        removeCarByReg.setFont(new Font("Monospaced", Font.BOLD, 20));
        removeCarByReg.setFocusPainted(false);
        buttonPanel.add(removeCarByReg);
        
        // find identifier button
        findIdentifier = new JButton();
        findIdentifier.setText("<html><center>"+"Find<br>Identifier"+"</center></html>");
        findIdentifier.setBackground(buttonColor);
        findIdentifier.setForeground(Color.white);
        findIdentifier.setFont(new Font("Monospaced", Font.BOLD, 20));
        findIdentifier.setFocusPainted(false);
        buttonPanel.add(findIdentifier);
        
        // find car button
        findCar = new JButton();
        findCar.setText("<html><center>"+"Find<br>Car"+"</center></html>");
        findCar.setBackground(buttonColor);
        findCar.setForeground(Color.white);
        findCar.setFont(new Font("Monospaced", Font.BOLD, 20));
        findCar.setFocusPainted(false);
        buttonPanel.add(findCar);
                
        // reset search button
        resetSearch = new JButton();
        resetSearch.setText("<html><center>"+"Reset<br>Search"+"</center></html>");
        resetSearch.setBackground(buttonColor);
        resetSearch.setForeground(Color.white);
        resetSearch.setFont(new Font("Monospaced", Font.BOLD, 20));
        resetSearch.setFocusPainted(false);
        buttonPanel.add(resetSearch);
        
        // reset button
        reset = new JButton("<html><center>"+"Reset<br>Parking Spot"+"</center></html>");
        reset.setBackground(buttonColor);
        reset.setForeground(Color.white);
        reset.setFont(new Font("Monospaced", Font.BOLD, 20));
        reset.setFocusPainted(false);
        buttonPanel.add(reset);
        
        // exit button
        exit = new JButton("Exit");
        exit.setBackground(buttonColor);
        exit.setForeground(Color.white);
        exit.setFont(new Font("Monospaced", Font.BOLD, 20));
        exit.setFocusPainted(false);
        buttonPanel.add(exit);
    }
    
    /**
    Adds all the action listeners to all the buttons in buttonsPanel.
    **/
    private void addButtonActionListeners(){
        addButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                cp.resetSearch(); // resets search highlights in the spotPanel
                String identifier = JOptionPane.showInputDialog("Enter Spot Identifier: "); // input dialog
                if(identifier != null){ // null check for the input
                    if(!cp.validateIdentifier(identifier)){ // invalid identifier format
                        JOptionPane.showMessageDialog(frame,"Identifier "+ identifier +" is in invalid format! Correct format example: P123","Invalid Identifier",JOptionPane.ERROR_MESSAGE);
                    }
                    else{ // valid identifier format
                        boolean result = cp.addSpot(identifier);
                    
                        if(result){ // successful
                            refreshSpotPanel();
                            JOptionPane.showMessageDialog(frame,"Successfully added parking spot: "+identifier,"Success", JOptionPane.INFORMATION_MESSAGE);
                        }
                        else{ // failed
                            JOptionPane.showMessageDialog(frame,"Parking Spot "+identifier+" already exists!","Duplicate Parking Spot",JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        });
        
        removeButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                cp.resetSearch(); // resets search highlights in the spotPanel/
                String identifier = JOptionPane.showInputDialog("Enter Spot Identifier: "); // input dialog
                if(identifier != null){ // null check for the input
                    if(!cp.validateIdentifier(identifier)){ // invalid identifier format
                        JOptionPane.showMessageDialog(frame,"Identifier "+ identifier +" is in invalid format! Correct format example: P123","Invalid Identifier",JOptionPane.ERROR_MESSAGE);
                    }else{ // valid identifier format
                        int result = cp.deleteSpot(identifier);
                    
                        if(result == 2){ // successful
                            refreshSpotPanel(); // refreshes spot panel
                            JOptionPane.showMessageDialog(frame,"Successfully removed parking spot: "+identifier,"Success", JOptionPane.INFORMATION_MESSAGE);
                        }
                        else if (result == 1){ // spot isn't vacant
                            JOptionPane.showMessageDialog(frame,"Parking Spot "+identifier+" is not vacant!","Parking Spot Occupied",JOptionPane.ERROR_MESSAGE);
                        }else{ // spot doesn't exist
                            JOptionPane.showMessageDialog(frame,"Parking Spot "+identifier+" does not exist!","No Parking Spot",JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        });
        
        parkSpot.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                cp.resetSearch(); // resets search highlights in the spotPanel
                String[] choices = new String[cp.viewAllSpots().size()]; // string array for choices in identifiers
                for(int i = 0; i < choices.length ; i++){
                    choices[i] = cp.viewAllSpots().get(i).getIdentifier();
                }
                         
                // input fields
                JComboBox identifier = new JComboBox(choices);
                JTextField registration = new JTextField();
                JTextField make = new JTextField();
                JTextField model = new JTextField();
                JTextField year = new JTextField();
                
                Object[] message = {
                    "Spot ID:", identifier,
                    "Registration:", registration, 
                    "Make:", make,
                    "Model:", model,
                    "Year:", year
                };
                int option = JOptionPane.showConfirmDialog(null, message, "Car Details", JOptionPane.OK_CANCEL_OPTION); // input dialog
                
                if (option == JOptionPane.OK_OPTION) {
                    String identifierText = (String)identifier.getSelectedItem();
                    String registrationText = registration.getText();
                    String makeText = make.getText();
                    String modelText = model.getText();
                    String yearText = year.getText();
                    
                    if(identifierText != ""){ // null check for identifier
                        if(cp.validateRegistration(registrationText)){ // validating registration input
                            if(cp.validateYear(yearText)){ // validating year input
                                if(!make.getText().isEmpty()){ // null check for make input
                                    if(model.getText().isEmpty()){ // null check for model input
                                        JOptionPane.showMessageDialog(frame,"You must enter car model!","Invalid Car Model",JOptionPane.ERROR_MESSAGE);
                                    }else{ // success
                                        int result = cp.parkCar(identifierText, registrationText, makeText, modelText, Integer.parseInt(yearText));
                                        if(result == 1){ // slot already occupied
                                            JOptionPane.showMessageDialog(frame,"Spot is not vacant!","Occupied Spot",JOptionPane.ERROR_MESSAGE);
                                        }
                                        else if(result == 2){ // car in another slot
                                            JOptionPane.showMessageDialog(frame,"The car registration: "+ registrationText +" is already in another spot!","Duplicate Car",JOptionPane.ERROR_MESSAGE);
                                        }
                                        else{ // success
                                            refreshSpotPanel();
                                            JOptionPane.showMessageDialog(frame,"Successfully parked car registration "+ registrationText +" at: "+ identifierText,"Success", JOptionPane.INFORMATION_MESSAGE);
                                        }
                                    }
                                }else{ // invalid make input
                                    JOptionPane.showMessageDialog(frame,"You must enter car make!","Invalid Car Make",JOptionPane.ERROR_MESSAGE);
                                }
                            }else{ // invalid year input
                                JOptionPane.showMessageDialog(frame,"Year "+ yearText +" is not between 2004 and 2024!","Invalid Year",JOptionPane.ERROR_MESSAGE);
                            }
                        }else{ // invalid registration format
                            JOptionPane.showMessageDialog(frame,"Registration "+ registrationText +" is in invalid format! Correct format example: T1234","Invalid Registration",JOptionPane.ERROR_MESSAGE);
                        }
                    }else{ // null identifier - nothing selected
                        JOptionPane.showMessageDialog(frame,"You must select an identifier to park a car!","Invalid Identifier",JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        
        removeCarByReg.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                cp.resetSearch(); // resets search highlights in the spotPanel
                String registration = JOptionPane.showInputDialog("Enter Car Registration: "); // input dialog
                if(registration != null){ // null check for the input
                    if(!cp.validateRegistration(registration)){ // validate registration format
                        JOptionPane.showMessageDialog(frame,"Registration "+ registration +" is in invalid format! Correct format example: T1234","Invalid Registration",JOptionPane.ERROR_MESSAGE);
                    }else{ // valid registration
                        boolean result = cp.removeCarByRegistration(registration);
                        if(result){ // success
                            refreshSpotPanel();
                            JOptionPane.showMessageDialog(frame,"Successfully removed car registration: "+ registration ,"Success", JOptionPane.INFORMATION_MESSAGE);
                        }else{ // failed
                            JOptionPane.showMessageDialog(frame,"Car doesn't exist in Parking Spots!","Car Not Found",JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        });
        
        reset.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                cp.reset(); // removes car from all parking spot instances
                cp.resetSearch(); // sets highlight variable to false for all parking spot instances
                refreshSpotPanel(); // refreshes spot panel
                JOptionPane.showMessageDialog(frame,"Successfully reset parking spot!","Success", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        
        exit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                JOptionPane.showMessageDialog(frame,"Thank you for using Parking Spot Manager!","Close Application", JOptionPane.INFORMATION_MESSAGE);
                System.exit(0); // exits the application
            }
        });
        
        findIdentifier.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                cp.resetSearch(); // resets search highlights in the spotPanel
                String identifier = JOptionPane.showInputDialog("Enter Spot Identifier: ");
                if(identifier != null){
                    if(!cp.validateIdentifier(identifier)){
                        JOptionPane.showMessageDialog(frame,"Registration "+ identifier +" is in invalid format! Correct format example: T1234","Invalid Registration",JOptionPane.ERROR_MESSAGE);
                    }else{
                        boolean result = cp.findIdentifier(identifier);
                        if(result){
                            refreshSpotPanel();
                            JOptionPane.showMessageDialog(frame,"Found spot : "+ identifier ,"Success", JOptionPane.INFORMATION_MESSAGE);
                        }else{
                            JOptionPane.showMessageDialog(frame,"Spot doesn't exist in Parking Spots!","Car Not Found",JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } 
            }
        });
        
        findCar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                cp.resetSearch(); // resets search highlights in the spotPanel
                String choices[] = {"Registration", "Make", "Model"}; // string array of choices for search category
                 
                // create checkbox
                JComboBox searchCategory = new JComboBox(choices);
                Object[] message = {
                    "Select Search Category:", searchCategory
                };
                int option = JOptionPane.showConfirmDialog(null, message, "Search Category", JOptionPane.OK_CANCEL_OPTION); // search category input dialog
                
                if (option == JOptionPane.OK_OPTION) {
                    if(((String)searchCategory.getSelectedItem()).equals("Registration")){ // find car by registration
                        String registration = JOptionPane.showInputDialog("Enter Car Registration: "); // registraion input dialog
                        if(registration != null){ // null check for registraion input
                            if(!cp.validateRegistration(registration)){ // validate registraion input
                                JOptionPane.showMessageDialog(frame,"Registration "+ registration +" is in invalid format! Correct format example: T1234","Invalid Registration",JOptionPane.ERROR_MESSAGE);
                            }else{ // valid registration input
                                boolean result = cp.findCarByRegistration(registration); // finds car and sets highlight variable to true
                                if(result){ // success
                                    refreshSpotPanel();
                                    JOptionPane.showMessageDialog(frame,"Found car registration: "+ registration ,"Success", JOptionPane.INFORMATION_MESSAGE);
                                }else{ // failed
                                    JOptionPane.showMessageDialog(frame,"Car doesn't exist in Parking Spots!","Car Not Found",JOptionPane.ERROR_MESSAGE);
                                }
                            }
                        } 
                    }else if(((String)searchCategory.getSelectedItem()).equals("Make")){ // find car by make
                        String make = JOptionPane.showInputDialog("Enter Car Make: "); // make input dialog
                        if(make != null){ // null check for make input
                            if(make.isEmpty()){
                                JOptionPane.showMessageDialog(frame,"You must enter a Car Make!","Empty Car Make",JOptionPane.ERROR_MESSAGE);
                            }else{
                                boolean result = cp.findCarByMake(make); // finds car and sets highlight variable to true
                                if(result){ // success
                                    refreshSpotPanel();
                                    JOptionPane.showMessageDialog(frame,"Found car make: "+ make ,"Success", JOptionPane.INFORMATION_MESSAGE);
                                }else{ // failed
                                    JOptionPane.showMessageDialog(frame,"No such car make exist in Parking Spots!","Car Not Found",JOptionPane.ERROR_MESSAGE);
                                }
                            }
                        }
                    }else if(((String)searchCategory.getSelectedItem()).equals("Model")){ // find car by model
                        String model = JOptionPane.showInputDialog("Enter Car Model: "); // model input dialog
                        if(model != null){ // null check for model input
                            if(model.isEmpty()){
                                JOptionPane.showMessageDialog(frame,"You must enter a Car Model!","Empty Car Model",JOptionPane.ERROR_MESSAGE);
                            }else{
                                boolean result = cp.findCarByModel(model); // finds car and sets highlight variable to true
                                if(result){ // success
                                    refreshSpotPanel();
                                    JOptionPane.showMessageDialog(frame,"Found car model: "+ model ,"Success", JOptionPane.INFORMATION_MESSAGE);
                                }else{ // failed
                                    JOptionPane.showMessageDialog(frame,"No such car model exist in Parking Spots!","Car Not Found",JOptionPane.ERROR_MESSAGE);
                                }
                            }
                        }
                    }
                }
            }
        });
        
        resetSearch.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                cp.resetSearch(); // resets search highlights in the spotPanel
                refreshSpotPanel(); // refreshes spot panel
                JOptionPane.showMessageDialog(frame,"Successfully reset search highlights!","Success", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        
    }
    
    /**
     * Main Method
     */
    public static void main(String[] args){
        new GUI(); // makes a new GUI object
    }
}
