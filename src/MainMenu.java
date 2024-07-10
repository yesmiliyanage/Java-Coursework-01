import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MainMenu{
    static Student[] students = new Student[100]; //initialize an array of Student objects which can contain 100 students

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
    //Creation of main menu and what methods to be called when different options are selected

        initialize(students);
        while(true){
            System.out.println();
            System.out.println("1. Check Available Seats");
            System.out.println("2. Register a Student (With ID)");
            System.out.println("3. Delete a Student's details");
            System.out.println("4. Find a student (With ID)");
            System.out.println("5. Save student details");
            System.out.println("6. Load Student Details");
            System.out.println("7. View Student List Based on Names");
            System.out.println("8. Module Details Management");
            System.out.println("9. Exit");

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
                                moduleDetailsManagement(input);
                                break;
                            case 9:
                                input.close();
                                System.out.println("Exiting the program....");
                                return;
                            default:
                                System.err.println("Select a number between 1 to 9");
                                continue;

                        }
                        break;


                }
                catch (InputMismatchException e) {
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
    private static void initialize(Student[] students){
        //Initialize the student name and id as vacant and module marks as -1
        for(int i = 0; i < students.length;i++){
            students[i] = new Student();

        }
    }

    public static boolean idPattern(String id){
        Pattern pattern = Pattern.compile("^w\\d{7}$"); //Compiles the regular expression
        Matcher matcher = pattern.matcher(id); //Creates a Matcher object that will match the user input with the compiled pattern
        return matcher.find();
    }

    public static boolean wishToContinue(Scanner input){
        while(true) {
            System.out.println("Do you wish to continue: ");
            String response = input.nextLine().trim().toLowerCase();

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



    public static void checkSeats(){
        int count = 0; //Initialize the student count as 0

        for(int i = 0; i < 100;i++){
            if(students[i].getStudentId().equals("vacant")){
                System.out.println("Number "+(i+1)+" is vacant");
                count++; //If vacant, increment the count by 1
            }
            else{
                System.out.println("Number "+(i+1)+" is occupied by "+ students[i].getStudentName() +" ("+ students[i].getStudentId() +")");
            }

            }
            System.out.println("There are "+count+" vacancies available for new students");

        }

    public static void registerStudent(Scanner input){
        int count = 0;
        for(Student student : students){
            if(student.getStudentId().equals("vacant")){ // Count the number of registered students
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
                System.err.println("Select a number between 1 to 100");
            }
            else{
                int i = number - 1;
                if(students[i].getStudentId().equals("vacant")) {
                    System.out.println("Number " + (number) + " is vacant");
                        while(true) {
                            try {
                                boolean uniqueId = true;
                                System.out.println("Enter the student ID: ");
                                String id = input.nextLine();
                                for(Student student : students){
                                    if (student.getStudentId().equals(id)) {
                                        uniqueId = false;
                                        break;
                                    }
                                }
                                if(uniqueId) { //If the entered id is not an existing id
                                    try {
                                        students[i].setStudentId(id);
                                        System.out.println("Correct Format");
                                        break;
                                    }
                                    catch(IllegalArgumentException e){
                                        System.err.println(e.getMessage());
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
                                input.nextLine();
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
                        name = name.substring(0,1).toUpperCase() + name.substring(1); //Capitalize the first letter of the first name
                        students[i].setStudentName(name);
                        System.out.println("Student registration is successful!!!");
                        break;
                    }
                    catch(StringIndexOutOfBoundsException e){
                        System.err.println("Invalid input. Please try again");
                    }
                    catch (Exception e) {
                        System.err.println("Error Occurred:- " + e);
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
                boolean idExist = false;
                System.out.println("Enter the Student ID to delete the details: ");
                String id = input.nextLine();
                boolean pattern = idPattern(id);
                if(pattern){ //If the user input matches with the compiled pattern, returns true
                    for(int i = 0;i < 100;i++){
                        if(students[i].getStudentId().equals(id)){
                            idExist = true;
                            students[i] = new Student(); // Replace the deleted student with a new Student object
                            System.out.println("Details for Student ID " +id+ " was deleted successfully...");
                            break;
                        }
                    }
                    if(!idExist){ //If there is no student registered with the entered id
                        System.out.println("This Student ID does not exist!!!");
                    }
                }
                else{
                    System.err.println("Please enter a valid ID (Ex: w1234568)");
                }
            }
            catch(Exception e){
                System.err.println("Error Occurred:- " + e);
                input.nextLine();
            }
            boolean response = wishToContinue(input);
            if(!response){
                return;
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
                if(pattern){
                    for(int i = 0;i < 100;i++){
                        if(students[i].getStudentId().equals(id)){
                            idExist = true; //If the id exists, the boolean becomes true
                            System.out.println("Number: " + (i+1));
                            System.out.println("Name: " + students[i].getStudentName());
                            System.out.println("Student ID: " + students[i].getStudentId());
                            System.out.println();
                            break;
                        }
                    }
                    if(!idExist) { //If the id is not found, returns true
                        System.out.println("This Student ID does not exist!!!");
                    }
                    boolean response = wishToContinue(input);
                    if(!response){
                        return;
                    }
                }
                else{
                    System.err.println("Please enter a valid ID (Ex: w1234568)");
                }
            }
            catch(Exception e){
                System.err.println("Error Occurred:- " + e);
                input.nextLine();
            }
        }

    }

    public static void saveStudentDetails() {
        try {
            //Creates a bufferedWriter object and gives a FileWriter object as the parameter
            BufferedWriter writer = new BufferedWriter(new FileWriter("studentsDetails.txt", false)); //Write the data to the file in overwrite mode
            for (Student student : students) {
                writer.write(student.fullDetailsForStudent());
                writer.newLine();
            }
            writer.close(); //Release the resources and ensure that all the data is written to the file
            System.out.println("All the details saved successfully");
        }
        catch(FileNotFoundException e){
            System.err.println("This file cannot be found");
        }
        catch(IOException e){
            System.err.println("IOException:- " + e);
        }
        catch (Exception e){
            System.err.println("Error Occurred:- " + e);
        }


    }

    public static void loadStudentDetails(){
        try {
            BufferedReader reader = new BufferedReader(new FileReader("studentsDetails.txt"));
            String studentDetails;
            int index = 0;
            while((studentDetails = reader.readLine()) != null){ //While the file has not reached the end
                String[] details = studentDetails.split(","); //Splits the current line of string and add the split parts into details array
                if(details.length == 2) { //Only if the student name and id is recorded without module marks
                    students[index].setStudentName(details[0]);
                    students[index].setStudentId(details[1]);
                }
                else { //If all the information including module marks are recorded
                    students[index].setStudentName(details[0]);
                    students[index].setStudentId(details[1]);
                    students[index].setModule1(Double.parseDouble(details[2]));
                    students[index].setModule2(Double.parseDouble(details[3]));
                    students[index].setModule3(Double.parseDouble(details[4]));
                }
                index++;
            }
            System.out.println("Data has been loaded. You can view and edit the details");
        }
        catch(FileNotFoundException e){
            System.err.println("File cannot be found");
        }
        catch(IOException e){
            System.err.println("Data cannot be loaded from the file");
        }
        catch(Exception e){
            System.err.println("Error Occurred:- "+ e);
        }

    }


    private static void viewNames(){
       try{
           // Step 01 - Count the number of registered students
           Student[] registeredStudents = returnRegisteredStudents();

           // Step 02 - Add the registered students to a new array
           int index = 0;
           for(Student student : students){
               if(!student.getStudentId().equals("vacant")){
                   registeredStudents[index] = student;
                   index++;
               }
           }

           if(registeredStudents.length == 0){
               System.out.println("No students to show. Load the student details or register a student");
               return;
           }

           // Step 03 - Sort the names in the alphabetical order with bubble sort
           boolean swapOccurred = true;
           while(swapOccurred) {
               swapOccurred = false;
               for (int i = 0; i < registeredStudents.length - 1; i++) {
                   if (registeredStudents[i].getStudentName().compareTo(registeredStudents[i + 1].getStudentName()) > 0) {
                       swapOccurred = true;
                       Student temp = registeredStudents[i];
                       registeredStudents[i] = registeredStudents[i + 1];
                       registeredStudents[i + 1] = temp;
                   }
               }
           }

           // Step 04 - Display the names and the ids of the registered students
           for (int i = 0; i < registeredStudents.length;i++) {
               System.out.print(i+1 + ")");
               System.out.printf("%-20s %10s%n", (registeredStudents[i].getStudentName()), (registeredStudents[i].getStudentId()));
           }

       }
       catch(Exception e){
           System.err.println("Error Occurred:- "+ e);
       }
    }

    public static Student[] returnRegisteredStudents(){
        int nonVacant = 0; // Initialize a variable to store the registered student count
        for(Student student : students){
            if(!student.getStudentId().equals("vacant")){
                nonVacant++; //Count the registered students
            }
        }

        return new Student[nonVacant]; //Initialize an array based on the number of registered students
    }

    public static void moduleDetailsManagement(Scanner input){
        //Creation of sub menu for option 8
        while(true) {
            System.out.println();
            System.out.println("Welcome to the Module Management System");
            System.out.println("a. Add Student Name");
            System.out.println("b. Add Module Marks");
            System.out.println("c. Get a summary");
            System.out.println("d. Get a full report");
            System.out.println("e. Exit Module Details Management");

            while (true) {
                try {
                    System.out.println("Select an Option : ");
                    String choice = input.nextLine();
                    switch (choice) {
                        case "a":
                            addStudentName(input);
                            break;
                        case "b":
                            addModuleMarks(input);
                            break;
                        case "c":
                            getSummary();
                            break;
                        case "d":
                            getFullReport();
                            break;
                        case "e":
                            System.out.println("Back to Main Menu");
                            return;
                        default:
                            System.err.println("Invalid input! Enter a,b,c or d");
                            continue;
                    }
                    break;

                }

                catch(Exception e){
                    System.out.println("Error Occurred:- " + e);
                }

            }

        }
    }

    public static void addMarksForModule(Student student, String moduleName, Scanner input){

        while(true) {
            try{
                System.out.println("Enter " + moduleName +" Marks: ");
                double mark = input.nextDouble();
                input.nextLine();
                switch (moduleName){ // Set the module mark for the student according to the module name
                    case "Module 01":
                        student.setModule1(mark);
                        break;
                    case "Module 02":
                        student.setModule2(mark);
                        break;
                    case "Module 03":
                        student.setModule3(mark);
                        break;
                }
                break;
            }
            catch(IllegalArgumentException e){
                System.err.println(e.getMessage());
            }
            catch(InputMismatchException e){
                System.err.println("Invalid input. Enter the marks again");
                input.nextLine();
            }

        }
    }


    public static void addStudentName(Scanner input){
        while(true) {
            try {
                boolean idExist = false;
                System.out.println("Enter the Student ID: ");
                String id = input.nextLine();
                boolean pattern = idPattern(id);
                if(pattern){
                    for (Student student : students) {
                        if (student.getStudentId().equals(id)) {
                            idExist = true;
                            System.out.println("Enter the Student Name: ");
                            String name = input.nextLine().trim();
                            name = name.substring(0,1).toUpperCase() + name.substring(1); //Capitalize the first letter of the first name
                            student.setStudentName(name);
                            System.out.println("Student name has been recorded successfully");
                        }

                    }
                }
                else{
                    System.err.println("Please enter a valid ID (Ex: w1234568)");
                    continue;
                }

                if(!idExist){
                    System.out.println("This Student ID does not exist");
                }
            }
            catch(StringIndexOutOfBoundsException e){
                System.err.println("Invalid input. Please try again");
            }
            catch(Exception e){
                System.err.println("Error Occurred:- " + e);
                input.nextLine();
            }
            boolean response = wishToContinue(input);
            if(!response){
                return;
            }
        }
    }

    public static void addModuleMarks(Scanner input){
        while(true){
            try{
                boolean idExist = false;
                System.out.println("Enter the StudentID: ");
                String id = input.nextLine();
                boolean pattern = idPattern(id);
                if(pattern){
                    for(Student student : students){
                        if(student.getStudentId().equals(id)){
                            idExist = true;

                            addMarksForModule(student, "Module 01", input);

                            addMarksForModule(student, "Module 02", input);

                            addMarksForModule(student, "Module 03", input);

                            System.out.println("Module marks for Student ID " + student.getStudentId() + " was Recorded Successfully...");
                            break;

                        }
                    }
                }

                else{
                    System.err.println("Please enter a valid ID (Ex: w1234568)");
                    continue;
                }

                if(!idExist){
                    System.out.println("This student ID does not exist");
                }
            }
            catch(Exception e){
                System.err.println("Error Occurred:- " + e);
                input.nextLine();
                continue;
            }

            boolean response = wishToContinue(input);
            if(!response){
                return;
            }
        }
    }

    public static void getSummary() {
        try{
            int count = 0;

            for (int i = 0; i < 100; i++) {
                if (!students[i].getStudentId().equals("vacant")) { // Count the registered number of students
                    count += 1;
                }
            }
            System.out.println("Total Number of Student Registrations:  " + count);

            int highMarksModule1 = 0;
            int highMarksModule2 = 0;
            int highMarksModule3 = 0;
            if(count != 0) {
                for (Student student : students) {
                    if (student.getModule1() != -1 && student.getModule2() != -1 && student.getModule3() != -1){ //Ensures that the student in the current iteration holds a student not vacant and have all three module marks entered
                        if (student.getModule1() > 40) {
                            highMarksModule1++;
                        }
                        if (student.getModule2() > 40) {
                            highMarksModule2++;
                        }
                        if (student.getModule3() > 40) {
                            highMarksModule3++;
                        }
                    }
                }

                System.out.println("Students will be counted only if their all the three module marks have been entered");
                System.out.println("Number of Students who Scored Higher than 40 in : ");
                System.out.println("Module 01 : " + highMarksModule1);
                System.out.println("Module 02 : " + highMarksModule2);
                System.out.println("Module 03 : " + highMarksModule3);

            }
            else{
                System.out.println("No Registered students found");
            }
        }
        catch(Exception e){
            System.err.println("Error Occurred:- " + e);
        }

    }

    public static void getFullReport(){
        try{
            // Step 01 - Count the number of students with marks
            Student[] studentsWithMarks = returnStudentsWithMarks();

            // Step 02 - Add the students with marks to a new array
            int index = 0;
            for (Student student : students) {
                if (student.getTotalMark() != -1) {
                    studentsWithMarks[index] = student;
                    index++;
                }
            }

            if(studentsWithMarks.length == 0){
                System.out.println("Load student details and add marks or register a new student");
                return;
            }

            // Step 03 - Sort the averages in the descending order with bubble sort
            boolean swapOccurred = true;
            while(swapOccurred){
                swapOccurred = false;
                for(int i = 0; i < studentsWithMarks.length - 1; i++) { //Loop stops one index before the length of the array because no more students to be compared after the last student
                    if (studentsWithMarks[i].getAverage() < studentsWithMarks[i+1].getAverage()) { //Swaps the places if the first average is smaller than the next average to make the list in descending order
                        swapOccurred = true;
                        Student highAvg = studentsWithMarks[i+1];
                        studentsWithMarks[i+1] = studentsWithMarks[i];
                        studentsWithMarks[i] = highAvg;
                    }
                }
            }

            // Step 04 - Displays a full report for each student with marks
            System.out.printf("%-20s %-20s %-20s %-20s %-20s %-10s %-20s %-20s%n", "Student Name", "Student ID", "Module 01", "Module 02", "Module 03" , "Total", "Average", "Grade"); //format string
            for(Student student : studentsWithMarks){
                student.fullReport();
            }
        }
        catch(Exception e){
            System.err.println("Error Occurred:- " + e);
        }
    }

    public static Student[] returnStudentsWithMarks(){
        int count = 0;
        for(Student student : students){
            if(student.getTotalMark() != -1){
                count++;
            }
        }
        return new Student[count]; //Create new Array of Student class to store the students with marks for all three modules
    }



}
