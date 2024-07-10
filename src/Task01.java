import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task01 {
    static String[][] students = new String[100][2];

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        //Creation of main menu and what methods to be called when different option are selected
        initialize(students);
        while(true){
            System.out.println("1. Check Available Seats");
            System.out.println("2. Register a Student (With ID)");
            System.out.println("3. Delete a Student's details");
            System.out.println("4. Find a student (With ID)");
            System.out.println("5. Save student details");
            System.out.println("6. Load Student Details");
            System.out.println("7. View Student List Based on Names");
            System.out.println("8. Exit");

            while (true) {
                try {
                    System.out.println("Select an option : ");
                    int choice = input.nextInt();
                    input.nextLine();

                    switch (choice) {
                        case 1:
                            checkSeats();
                            break;
                        case 2:
                            registerStudent(input);
                            break;
                        case 3:
                            deleteStudent(input);
                            break;
                        case 4:
                            findStudent(input);
                            break;
                        case 5:
                            saveStudentDetails();
                            break;
                        case 6:
                            loadStudentDetails();
                            break;
                        case 7:
                            viewNames();
                            break;
                        case 8:
                            input.close();
                            System.out.println("Exiting the program....");
                            return;
                        default:
                            System.out.println("Select a number between 1 to 8");
                            continue;

                    }
                    break;


                }
                catch(InputMismatchException e){
                    System.err.println("Invalid input. Please try again");
                    input.nextLine();
                }
                catch (Exception e) {
                    System.err.println("Error Occurred:- " + e);
                    input.nextLine();
                }

            }

        }

    }

    // Define the methods
    private static void initialize(String[][] array) {
        //Initialize the student name and id as vacant (array[i][0] for name and array[i][1] for ID)
        for(int i = 0; i < array.length; i++) {
            for(int j = 0; j < 2; j++) {
                array[i][j] = "vacant";
            }
        }
    }

    public static boolean wishToContinue(Scanner input){
        while(true) {
            System.out.println("Do you wish to continue: ");
            String response = input.nextLine().trim().toLowerCase(); // Convert the input to lower case

            if (response.equals("yes")) {
                return true;
            }
            else if (response.equals("no")) {
                System.out.println("Back to Main Menu");
                return false;
            }
            else {
                System.err.println("Please enter yes/no");
            }

        }
    }

    public static boolean idPattern(String id){
        Pattern pattern = Pattern.compile("^w\\d{7}$"); //Compiles the regular expression
        Matcher matcher = pattern.matcher(id); //Creates a Matcher object that will match the user input with the compiled pattern
        return matcher.find();
    }


    public static void checkSeats(){
        try{
            int count = 0; //Initialize the student count to 0

            for(int i = 0; i < 100;i++){
                if(students[i][1].equals("vacant")){
                    System.out.println("Number "+(i+1)+" is vacant");
                    count++; //If vacant, increment the count by 1
                }
                else{
                    System.out.println("Number "+(i+1)+" is occupied by "+ students[i][0] +" ("+ students[i][1] +")");
                }

            }
            System.out.println("There are "+count+" vacancies available for new students");
        }
        catch(Exception e){
            System.err.println("Error Occurred:- " + e);
        }


    }
    public static void registerStudent(Scanner input){
        int count = 0;
        for(String[] student : students){
            if(student[1].equals("vacant")){ // Count the number of registered students
                count++;
            }
        }
        if(count == 0){ // If all the seats are occupied
            System.out.println("No vacancies available for new students.");
            return;
        }

        int number;
        while(true){
            try{
                System.out.println("Enter a number to register a student: ");
                number = input.nextInt();
                input.nextLine();
            }
            catch(InputMismatchException e){
                System.err.println("Invalid input. Please try again");
                input.nextLine();
                continue;
            }
            catch(Exception e){
                System.err.println("Error Occurred:- " + e);
                input.nextLine();
                continue;
            }
            if(number < 1 || number > 100){
                System.out.println("Select a number between 1 to 100");
            }
            else{
                int i = number - 1;
                if(students[i][1].equals("vacant")) {
                    System.out.println("Number " + (number) + " is vacant");
                    while(true) {
                        try {
                            boolean uniqueId = true;
                            System.out.println("Enter the student ID: ");
                            String id = input.nextLine();
                            for(String[] student : students){
                                if (student[1].equals(id)) {
                                    uniqueId = false;
                                    break;
                                }
                            }

                            if(uniqueId) {
                                boolean pattern = idPattern(id);
                                if (pattern) {
                                    System.out.println("Correct Format");
                                    students[i][1] = id;
                                    break;
                                }
                                else {
                                    System.err.println("Incorrect Format. Please enter the Student ID again (Ex: w4513490)");
                                }
                            }
                            else{
                                System.out.println("This Student ID already exists!!!");
                                boolean response = wishToContinue(input);
                                if(!response){
                                    return;
                                }
                            }
                        }
                        catch (Exception e) {
                            System.err.println("Error Occurred:- " + e);
                        }
                    }
                }

                else{
                    System.out.println("Number "+ number +" is occupied");
                    boolean response = wishToContinue(input);
                    if(!response){
                        return;
                    }
                    continue;
                }


                while(true) {
                    try {
                        System.out.println("Enter the student's name: ");
                        String name = input.nextLine().trim();
                        name = name.substring(0,1).toUpperCase() + name.substring(1);
                        students[i][0] = name;
                        System.out.println("Student registration is successful!!!");
                        break;
                    }
                    catch(StringIndexOutOfBoundsException e){
                        System.err.println("Invalid input. Please try again");
                    }
                    catch (Exception e) {
                        System.out.println("Error Occurred:- " + e);
                        input.nextLine();
                    }
                }

                boolean response = wishToContinue(input);
                if(!response){
                    return;
                }
            }
        }
    }
    public static void deleteStudent(Scanner input){
        while(true) {
            try {
                System.out.println("Enter the Student ID to delete the details: ");
                String id = input.nextLine();
                boolean pattern = idPattern(id);
                if (pattern){ //if the user input matches with the compiled pattern returns true
                    for(int i = 0;i < 100;i++){
                        if(students[i][1].equals(id)){
                            for(int j = 0;j < 2;j++) {
                                students[i][j] = "vacant";
                            }
                            System.out.println("Details for Student ID " +id+ " was deleted successfully...");
                            return;
                        }
                    }
                    System.out.println("This Student ID does not exist!!!");
                    boolean response = wishToContinue(input);
                    if(!response){
                        return;
                    }
                }
                else{
                    System.out.println("Please enter a valid ID (Ex: w1234568)");
                    boolean response = wishToContinue(input);
                    if(!response){
                        return;
                    }

                }
            }
            catch(Exception e){
                System.out.println("Error Occurred:- " + e);
                input.nextLine();
            }
        }

    }

    public static void findStudent(Scanner input){
        while(true) {
            try {
                boolean idExist = false;
                System.out.println("Enter the Student ID to find the details: ");
                String id = input.nextLine();
                boolean pattern = idPattern(id);
                if (pattern){
                    for(int i = 0;i < 100;i++){
                        if(students[i][1].equals(id)){
                            idExist = true; //if the id exists the boolean becomes true
                            System.out.println("Number: " + (i+1));
                            System.out.println("Name: " + students[i][0]);
                            System.out.println("Student ID: " + students[i][1]);
                            System.out.println();
                            break;
                        }
                    }
                    if(!idExist) { //if the id is not found returns true
                        System.out.println("This Student ID does not exist!!!");
                    }
                    boolean response = wishToContinue(input);
                    if(!response){
                        return;
                    }
                }

                else{
                    System.out.println("Please enter a valid ID (Ex: w1234568)");
                }
            }
            catch(Exception e){
                System.out.println("Error Occurred:- " + e);
                input.next();
            }

        }

    }

    public static void saveStudentDetails() {
        try {
            //creates a bufferedWriter object and gives a FileWriter object as the parameter
            BufferedWriter writer = new BufferedWriter(new FileWriter("studentNamesAndIds.txt", false)); //Write the data to the file in overwrite mode
            for (int i = 0; i < 100; i++) {
                writer.write(students[i][0] + "," + students[i][1]);
                writer.newLine();
            }
            writer.close(); //Release the resources and ensure that all the data is written to the file
            System.out.println("All the details saved successfully");
        }
        catch(FileNotFoundException e){
            System.out.println("This file cannot be found");
        }
        catch(IOException e){
            System.out.println("IOException Occurred: " + e);
        }
        catch(Exception e){
            System.out.println("Error Occurred: "+ e);
        }


    }

    public static void loadStudentDetails(){
        try {
            BufferedReader reader = new BufferedReader(new FileReader("studentNamesAndIds.txt"));
            String studentDetails;
            int index = 0;
            while((studentDetails = reader.readLine()) != null){ //while the file has not reached the end
                String[] details = studentDetails.split(","); //Splits the current line of string and add the split parts to details array
                students[index][0] = details[0];
                students[index][1] = details[1];
                index++;
            }
            System.out.println("Data has been loaded. You can view and edit the details");
            System.out.println();
        }
        catch(FileNotFoundException e){
            System.out.println("File cannot be found");
        }
        catch(IOException e){
            System.out.println("Data cannot be loaded from the file");
        }
        catch(Exception e){
            System.out.println("Error Occurred: "+ e);
        }

    }


    private static void viewNames(){
        int nonVacant = 0;
        for(String[] student : students){
            if(!student[1].equals("vacant")){
                nonVacant++; //Count the registered students
            }
        }
        if(nonVacant == 0){
            System.out.println("No students to show. Register a student or load student details");
        }

        String[][] names = new String[nonVacant][2]; //Create an array based on the number of registered students
        int index = 0;
        for(int i = 0;i < 100;i++){
            if(!students[i][0].equals("vacant")){
                names[index][0] = students[i][0]; //Add to the name of the registered student to the names array
                names[index][1] = students[i][1];
                index++;
            }
        }


        // Sort the names in the alphabetical order
        for(int i = 0; i <names.length-1; i++){
            for(int j = i+1; j <names.length; j++){
                if(names[i][0].compareTo(names[j][0]) > 0){
                    String[] temp = names[i];
                    names[i] = names[j];
                    names[j] = temp;
                }
            }
        }

        for (int i = 0;i<names.length;i++) {
            System.out.println(i+1 + ") " + names[i][0] +"  " +names[i][1]);
        }
        System.out.println();
    }
}
