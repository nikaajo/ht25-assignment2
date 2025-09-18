//Main
//Assignment 2, SYSA21 Mjukvaruutveckling
//Annika Johansson

import java.util.Scanner;

public class Main {
    //Initiate variables
    Scanner scanner = new Scanner(System.in);
    
    class Passenger {
        String name;
        int age;
    
        Passenger(String name, int age) {
            this.name = name;
            this.age = age;
        } 
    }
    
    Passenger[] passManifest = new Passenger[12];

    

    public static void main(String[] args) {
        Main flightBookingSystem = new Main();
        boolean currentlyRunning = true;
        while (currentlyRunning) {
            flightBookingSystem.chooseFunction();
        } 
        //If option 7 is chosen, exit the program, currentlyRunning becomes false and exits the loop.  
        System.exit(0);
    }

    //Ask the user which function they'd like to call
    public void chooseFunction(){
        System.out.println("Welcome to SkyBox Ltd.'s flight booking system. Please choose a function by entering the corresponding number:");
        System.out.println("1. Add a passenger");
        System.out.println("2. Remove a passenger");
        System.out.println("3. Switch seats between two passengers");
        System.out.println("4. Rename a passenger");
        System.out.println("5. Count total number of passengers");
        System.out.println("6. Print the passenger manifest");
        System.out.println("7. Exit the program");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        switch (choice) {
            case 1:
                insertPassenger();
                break;
            case 2:
                removePassenger();
                break;
            case 3:
                switchSeats();
                break;
            case 4:
                renamePassenger();
                break;
            case 5:
                countPassengers();
                break;
            case 6:
                printManifest();
                break;
            case 7:
                System.out.println("Exiting the program. Thank you for using SkyBox Ltd.'s flight booking system.");
                boolean currentlyRunning = false;
                System.exit(0);
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }
    }

    //Add a passenger
    public void insertPassenger(){
        //In debugging it told me to remove this.name and this.age- why?
        System.out.print("Enter the name of the passenger you'd like to add: ");
        String name = scanner.nextLine();
        System.out.print("Enter the age of the passenger: ");
        Integer age = scanner.nextInt();
        Passenger newPassenger = new Passenger(name, age);
        for (int i = 0; i < passManifest.length; i++) {
            if (passManifest[i] == null) {
                passManifest[i] = newPassenger;
                i++; // To show the user the seat number starting from 1 instead of 0
                System.out.println("Passenger " + name + ", age "+ age +" added successfully. They are seated at place "+ i); 
                break;
                  
            } else if (i == passManifest.length - 1) {
                System.out.println("Sorry, the flight is full. Cannot add more passengers.");
            }
        }
    }    

    //Remove a passenger
    public void removePassenger(){
        System.out.print("Enter the name of the passenger you'd like to remove: ");
        String nameToRemove = scanner.nextLine();
        System.out.print("Enter the age of the passenger you'd like to remove: ");
        int ageToRemove = scanner.nextInt();
        boolean found = false;
        for (int i = 0; i < passManifest.length; i++) {
            if (passManifest[i] != null && passManifest[i].name.equals(nameToRemove) && passManifest[i].age == ageToRemove) {
                passManifest[i] = null;
                found = true;
                System.out.println("Passenger " + nameToRemove + ", age "+ ageToRemove +" removed successfully from seat "+ i);
                break;
            } else if (i == passManifest.length - 1 && !found) {
                System.out.println("Passenger not found. Please check the name and age and try again.");
            }
        }
    }

    //Switch seats between two passengers
    public void switchSeats(){
        System.out.print("To switch seats, first enter the seat you'd like to switch from, then the seat you'd like to switch to.");
        System.out.print("Enter the seat number of the seat you'd like to switch a passenger from (1-12): ");
        int fromSeat = scanner.nextInt() - 1; 
        System.out.print("Enter the seat number of the seat you'd like to switch a passenger to (1-12): ");
        int toSeat = scanner.nextInt() - 1; 
        if (fromSeat < 0 || fromSeat >= passManifest.length || toSeat < 0 || toSeat >= passManifest.length) {
            //If the seat numbers are invalid, exit. 
            System.out.println("Invalid seat numbers. Please enter numbers between 1 and 12.");
            return;
        } else if (passManifest[fromSeat] == null && passManifest[toSeat] == null) {
            //If neither seat has a passenger. exit. 
            System.out.println("Both seats are empty. You cannot switch empty seats. Returning to main menu.");
            return;
        } else if (fromSeat == toSeat) {
            //If the user enters the same seat for both fromSeat and toSeat, exit. 
            System.out.println("You have chosen the same seat twice. No changes are able to be made. Returning to main menu.");
            return;
        } else if (passManifest[fromSeat] == null) {
            //If the the fromSeat does not have a passenger, exit.
            System.out.println("There is no passenger at seat " + (fromSeat + 1) + ". Thus, cannot switch them with another passenger. Returning to the main menu");
            return;
        } else if (passManifest[toSeat] == null) {
            //If the toSeat does not have a passenger, can complete the switch. 
            System.out.println("You have chosen to switch the passenger "+ passManifest[fromSeat].name + " age, " + passManifest[fromSeat].age + "  at seat " + (fromSeat + 1) + " to " + (toSeat + 1) + ", which is currently empty.");
            System.out.print("To confirm that you'd like to move this passenger to the empty seat, please type 'yes'. Otherwise, no changes will be made and this request will end.");
            scanner.nextLine(); 
            String confirmation = scanner.nextLine();
            if (confirmation.equalsIgnoreCase("yes")) { 
                passManifest[toSeat] = passManifest[fromSeat];
                passManifest[fromSeat] = null;
                System.out.println("Passenger " + passManifest[toSeat].name + ", age "+ passManifest[toSeat].age +" moved successfully to seat "+ (toSeat + 1) +". Seat "+ (fromSeat + 1) +" is now empty.");
            } 
            else {
                System.out.println("No changes made. Returning to main menu.");
                return;
            } 
            return;
        } else 
            //If both seats have passengers, can complete the switch. 
            System.out.println("You have chosen to switch the passenger "+ passManifest[fromSeat].name + " age, " + passManifest[fromSeat].age + "  at seat " + (fromSeat + 1) + " with the passenger "+ passManifest[toSeat].name + " age, " + passManifest[toSeat].age + "  at seat " + (toSeat + 1) + ".");
            System.out.print("To confirm that you'd like to switch these seats, please type 'yes'. Otherwise, no changes will be made and this request will end.");
            scanner.nextLine(); 
            String confirmation = scanner.nextLine();
            if (confirmation.equalsIgnoreCase("yes")) {
                Passenger temp = passManifest[fromSeat];
                passManifest[fromSeat] = passManifest[toSeat];
                passManifest[toSeat] = temp;
                System.out.println("Seats switched successfully between seat " + (fromSeat + 1) + " and seat " + (toSeat + 1) + ".");
            } 
            else {
                System.out.println("No changes made. Returning to main menu.");
            } return;
        }
    
    
    //Rename Passenger given a name and seat number
    public void renamePassenger(){
        System.out.print("Enter the seat number of the passenger you'd like to rename (1-12): ");
        int seatNumber = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        //Will this stop the function if the seat is empty?
        if (seatNumber < 0 || seatNumber >= passManifest.length || passManifest[seatNumber] == null) {
            System.out.println("Invalid seat number or there no passenger found at that seat.");
            return;
        }
        System.out.println("Current name of passenger at seat " + seatNumber + " is " + passManifest[seatNumber].name + " age " + passManifest[seatNumber].age + ".");
        System.out.print("Enter the new name for the passenger: ");
        String newName = scanner.nextLine();
        passManifest[seatNumber].name = newName;
        System.out.println("Passenger at seat " + seatNumber + " renamed successfully to " + newName + ".");
    }

    //Count total number of passengers
    public void countPassengers(){
        int count = 0;
        for (Passenger passenger : passManifest) {
            if (passenger != null) {
                count++;
            }
        }
        System.out.println("Total number of passengers: " + count);
    }

    //Print the passenger manifest
    public void printManifest() {
    System.out.println("##### PASSENGER MANIFEST ######");
    System.out.println("SkyBox Ltd.");
    System.out.printf("%-5s %-10s %-5s%n", "Seat", "Name", "Age");
    for (int i = 0; i < passManifest.length; i++) {
        if (passManifest[i] != null) {
            System.out.printf("%-5d %-10s %-5d%n", i + 1, passManifest[i].name, passManifest[i].age);
        } else {
            System.out.printf("%-5d %-10s %-5s%n", i + 1, "", "");
        }
    }
    System.out.println("##### ##### ##### ##### ##### ###");    
    }

    }

