# Parking Spot Manager

## Brief Description

A basic parking spot management system developed using Java Swing and AWT libraries, featuring functionalities to add and remove parking spots, park and remove cars, and find cars by registration, make, model, or spot identifier. Additional functionalities include resetting searches and the entire parking spot system.

## Button Functionalities

- **Add Spot**: Prompts for an identifier and adds a spot to the parking spots.
- **Remove Spot**: Prompts for an identifier and removes the spot from the parking spots if it exists.
- **Park Car**: Prompts to select a spot identifier, car registration, make, model, and year, then parks the car in the specified spot.
- **Remove Car**: Prompts for a car registration number and removes the car if found.
- **Find Car**: Prompts for search categories (registration, make, model) and highlights the spot if the car is found.
- **Find Identifier**: Prompts for an identifier number and highlights the spot if the car is found.
- **Reset**: Removes all cars from all the parking spots.
- **Reset Search**: Removes all the search highlight colors from all the spots.
- **Exit**: Exits the application.

## Running the Application

To run the application:
1. Open BlueJ.
2. Right-click on the `GUI` class.
3. Select and run the `main` function.

## GUI Components Used

### Libraries
- `Java Swing`
- `Java AWT`

### Classes
- `JFrame`: Used to create the frame. Initialized in the `GUI` class’s constructor.
- `JPanel`: Used to create header, button, and spot panels. Initialized in the `GUI` class’s `addPanels()` function.
- `JButton`: Used to create `addSpot`, `removeSpot`, `parkCar`, `removeCar`, `findCar`, `findIdentifier`, `resetSearch`, `reset`, and `exit` buttons. Initialized in the `GUI` class’s `addButtons()` function.
- `Color`: Used to create the colors of the application. Initialized as class variables.
- `BorderLayout`: Used in setting frame layout in the `GUI` class’s constructor.
- `GridLayout`: Used in setting layout for button and spot panels in the `GUI` class’s `addPanels()` function.
- `Font`: Used to format different fonts (e.g., Monospaced & Courier with different sizes and weights) for the application.
- `JOptionPane`: Used to show dialog boxes for taking inputs or showing responses to the user.
- `JLabel`: Used to create the header label, which stays in the header panel. Initialized in the `GUI` class’s `addPanels()` function.
- `JTextField`: Used to take inputs for registration, make, model, and year in the park car button’s action listener.
- `JComboBox`: Used to take inputs for the identifier in the park car button’s action listener.
- `ActionListener`: Used to add interaction to all the buttons in the application.

### Author
- Aditya Roy
