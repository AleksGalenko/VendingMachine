# Vending Machine

This project is a Vending Machine developed in Java, showcasing the implementation of a fully functional vending machine. It utilizes Spring dependency injection and follows the Model-View-Controller (MVC) design pattern.

## Features

- **Item Display**: Upon startup, the program displays all available items and their respective prices, providing users with a clear view of the vending machine inventory.
- **Funds Input**: Users must input the desired amount of money before making a selection. This ensures a seamless purchasing experience.
- **Purchase Functionality**: The program handles item purchases, checking for sufficient funds and updating the inventory accordingly. It also calculates and returns change if the user's deposited amount exceeds the item's price.
- **Change Calculation**: If the selected item costs less than or equal to the amount of money provided, the program calculates the optimal number of quarters, dimes, nickels, and pennies to return as change.
- **Inventory Management**: Vending machine items are stored in a file, with inventory levels read from and written to this file upon program startup and exit. The program tracks essential properties such as item name, cost, and quantity.
- **Graceful Failure**: The application gracefully handles exceptions, ensuring that error messages are displayed to users instead of stack traces, leading to a better user experience.
- **Exception Handling**: The application throws application-specific exceptions, such as `InsufficientFundsException` and `NoItemInventoryException`, to handle scenarios where users don't deposit enough money or when an item is out of stock.
- **Unit Tests**: Comprehensive unit tests ensure the correctness of the Data Access Object (DAO) and Service Layer components, guaranteeing the reliability of the implemented functionalities.
- **BigDecimal for Monetary Calculations**: Monetary calculations within the application utilize the `BigDecimal` class to ensure accurate handling of monetary values, preventing precision errors.
- **Audit Logging**: An Audit DAO logs events and timestamps, providing a record of important actions within the application.

## Getting Started

To run the Vending Machine Simulator locally, follow these steps:

1. Clone the repository: `git clone https://github.com/AleksGalenko/VendingMachine.git`
2. Open the project in your preferred Java IDE.
3. Build and run the project using the IDE's tools or by running the main application class.

Make sure to have Java and the necessary dependencies (such as Spring) installed before running the project.

## Usage

Upon starting the application, you will see the list of available items and their prices. Follow the prompts to input the desired amount of money and select an item. The program will display the change (if any) and adjust the inventory accordingly.

## Acknowledgements

- This project was completed as part of an assignment for the Java Fundamentals course by Wiley Edge..
- I would like to thank my mentors for their guidance and support throughout the development process.
