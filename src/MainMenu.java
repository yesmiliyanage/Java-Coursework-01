import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Arrays;


public class MainMenu{
    static Scanner input = new Scanner(System.in);
    static Student[] students = new Student[100]; //initialize an array of student class which can contain 100 students

    public static void main(String[] args) {
    //Creation of main menu and what methods to be called when different options are selected
        int choice = 0;
        initialize(students);
        while(true){
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
                    choice = input.nextInt();
                    input.nextLine();

                        switch (choice) {
                            case 1:
                                checkSeats();
                                break;
                            case 2:
                                registerStudent();
                                break;
                            case 3:
                                deleteStudent();
                                break;
                            case 4:
                                findStudent();
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
                                moduleDetailsManagement();
                                break;
                            case 9:
                                System.out.println("Exiting the program....");
                                return;
                            default:
                                System.err.println("Select a number between 1 to 9");
                                continue;

                        }
                        break;


                }
                catch (Exception e) {
                    System.err.println("Enter a valid input");
                    input.next();
                }

            }

        }

    }

    // Define the methods
    private static void initialize(Student[] students){
        //Initialize the student name and id as vacant and module marks as null
        for(int i = 0; i < students.length;i++){
            students[i] = new Student();

        }
        System.out.println();
    }

    public static boolean idPattern(String id){
        Pattern pattern = Pattern.compile("^w\\d{7}$"); //Compiles the regular expression
        Matcher matcher = pattern.matcher(id); //Creates a Matcher object that will match the user input with the compiled pattern
        return matcher.find();
    }

    public static boolean wishToContinue(){
        while(true) {
            System.out.println("Do you wish to continue: ");
            String response = input.nextLine().toLowerCase();

            if (response.equals("yes")) {
                return true;
            }
            else if (response.equals("no")) {
                System.out.println("Back to Main Menu");
                return false;
            }
            else {
                System.err.println("Enter yes/no");
            }
        }
    }



    public static void checkSeats(){
        int count = 0; //Initialize the student count to 0

        for(int i = 0; i < 100;i++){
            if(students[i].getStudentName().equals("vacant")){
                System.out.println("Number "+(i+1)+" is vacant");
                count++; //If vacant, increment the count by 1
            }
            else{
                System.out.println("Number "+(i+1)+" is occupied by "+ students[i].getStudentName() +" ("+ students[i].getStudentId() +")");
            }

            }
            System.out.println("There are "+count+" vacancies available for new students");

        }
    public static void registerStudent(){
        int number;
        while(true){
            try{
            System.out.println("Enter a number to register a student: ");
            number = input.nextInt();
            input.nextLine();
            }
            catch(Exception e){
                System.err.println("Enter a valid input");
                input.next();
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
                                if(uniqueId) {
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
                                    boolean response = wishToContinue();
                                    if(!response){
                                        return;
                                    }
                                }
                            }
                            catch (Exception e) {
                                System.err.println("Enter a valid input");
                            }
                        }
                }

                else{
                    System.out.println("Number "+ number +" is occupied");
                    boolean response = wishToContinue();
                    if(!response){
                        return;
                    }
                    continue;
                }


                while(true) {
                    try {
                        System.out.println("Enter the student's name: ");
                        students[i].setStudentName(input.nextLine());
                        System.out.println("Student registration is successful!!!");
                        break;
                    } catch (Exception e) {
                        System.err.println("Enter a valid input");
                        input.next();
                    }
                }

                boolean response = wishToContinue();
                if(!response){
                    return;
                }
            }
        }
    }
    public static void deleteStudent(){
        while(true) {
            try {
                System.out.println("Enter the Student ID to delete the details: ");
                String id = input.nextLine();
                boolean pattern = idPattern(id);
                if(pattern){ //if the user input matches with the compiled pattern returns true
                    for(int i = 0;i < 100;i++){
                        if(students[i].getStudentId().equals(id)){
                            students[i].setStudentName("vacant");
                            students[i].setStudentId("vacant");
                            System.out.println("Details for Student ID " +id+ " was deleted successfully...");
                            return;
                        }
                    }
                    System.out.println("This Student ID does not exist!!!");
                    boolean response = wishToContinue();
                    if(!response){
                        return;
                    }
                }
                else{
                    System.err.println("Please enter a valid ID (Ex: w1234568)");
                }
            }
            catch(Exception e){
                System.err.println("Enter a valid input");
                input.next();
            }
        }

    }

    public static void findStudent(){
        while(true) {
            try {
                boolean idExist = false;
                System.out.println("Enter the Student ID to find the details: ");
                String id = input.nextLine();
                boolean pattern = idPattern(id);
                if(pattern){
                    for(int i = 0;i < 100;i++){
                        if(students[i].getStudentId().equals(id)){
                            idExist = true; //if the id exists the boolean becomes true
                            System.out.println("Number: " + (i+1));
                            System.out.println("Name: " + students[i].getStudentName());
                            System.out.println("Student ID: " + students[i].getStudentId());
                            System.out.println();
                            break;
                        }
                    }
                    if(!idExist) { //if the id is not found returns true
                        System.out.println("This Student ID does not exist!!!");
                    }
                    boolean response = wishToContinue();
                    if(!response){
                        return;
                    }

                }
                else{
                    System.err.println("Please enter a valid ID (Ex: w1234568)");

                }
            }
            catch(Exception e){
                System.err.println("Enter a valid input");
                input.next();
            }

        }

    }

    public static void saveStudentDetails() {
        try {
            //creates a bufferedWriter object and gives a FileWriter object as the parameter
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
            System.err.println("Error occurred: "+e);
        }


    }

    public static void loadStudentDetails(){
        try {
            BufferedReader reader = new BufferedReader(new FileReader("studentsDetails.txt"));
            String studentDetails;
            int index = 0;
            while((studentDetails = reader.readLine()) != null){ //while the file has not reached the end
                String[] details = studentDetails.split(","); //Splits the current line of string and add the split parts to details array
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
            System.out.println();
        }
        catch(FileNotFoundException e){
            System.err.println("File cannot be found. Data cannot be loaded from the file");
        }
        catch(IOException e){
            System.err.println("Error Occurred: " + e);
        }
        catch(Exception e){
            System.err.println("Error Occurred"+ e);
        }

    }


    private static void viewNames(){
        Student[] studentsCopy = students.clone();
        Arrays.sort(studentsCopy, (s1,s2) -> s1.getStudentName().compareTo(s2.getStudentName()));
        int order = 1;
        boolean studentsExist = false;
        for(Student name : studentsCopy){
            if(!name.getStudentName().equals("vacant")){
                studentsExist = true;
                break;
            }
        }
        if(!studentsExist){ //If the student names equal to vacant
            System.out.println("No students to show. Register a student or load the student details");
        }

        for (Student name : studentsCopy) {
            if (!name.getStudentName().equals("vacant")) {
                System.out.print(order + ")");
                System.out.printf("%-20s %10s%n", (name.getStudentName()), (name.getStudentId()) );
                order++;
            }
        }

        System.out.println();
    }

    public static void moduleDetailsManagement(){
        //Creation of sub menu for option 8
        while(true) {
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
                            addStudentName();
                            break;
                        case "b":
                            addModuleMarks();
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
                catch (Exception e) {
                    System.err.println("Please enter a valid input");
                    input.next();
                }

            }

        }
    }

    public static void addStudentName(){
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
                            String name = input.nextLine();
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
            catch(Exception e){
                System.err.println("Invalid Input");
                input.next();
            }
            boolean response = wishToContinue();
            if(!response){
                return;
            }
        }
    }

    public static void addModuleMarks(){
        while(true){
            try{
                boolean found = false;
                System.out.println("Enter the StudentID: ");
                String id = input.nextLine();
                boolean pattern = idPattern(id);
                if(pattern){
                    for(Student student : students){
                        if(student.getStudentId().equals(id)){
                            found = true;
                            while(true) {
                                try{
                                    System.out.println("Enter Module 01 Marks: ");
                                    double mark1 = input.nextDouble();
                                    student.setModule1(mark1);
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

                            while(true) {
                                try{
                                    System.out.println("Enter Module 02 Marks: ");
                                    double mark2 = input.nextDouble();
                                    student.setModule2(mark2);
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

                            while(true) {
                                try{
                                    System.out.println("Enter Module 03 Marks: ");
                                    double mark3 = input.nextDouble();
                                    student.setModule3(mark3);
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
                            input.nextLine();
                            System.out.println("Module marks for Student ID " + student.getStudentId() + " was Recorded Successfully...");
                            break;

                        }

                    }
                }
                else{
                    System.err.println("Please enter a valid ID (Ex: w1234568)");
                    continue;
                }

                if(!found){
                    System.out.println("This student ID does not exist");
                }
            }
            catch(Exception e){
                System.err.println("Enter a valid input " + e);
                input.nextLine();
                continue;
            }

            boolean response = wishToContinue();
            if(!response){
                return;
            }

        }

    }

    public static void getSummary() {
        int count = 0;

        for (int i = 0; i < 100; i++) {
            if (!students[i].getStudentId().equals("vacant")) {
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

            System.out.println("Number of Students who Scored Higher than 40 in : ");
            System.out.println("Marks will be counted only for the students whose all the three module marks are entered");
            System.out.println("Module 01 : " + highMarksModule1);
            System.out.println("Module 02 : " + highMarksModule2);
            System.out.println("Module 03 : " + highMarksModule3);

        }
        else{
            System.out.println("No Registered students found");
        }
        System.out.println();

    }

    public static void getFullReport(){
        ArrayList<Student> studentsWithMarks = new ArrayList<>(); //Create new ArrayList of Student class
        for(Student student : students){
            if(student.getAverage() != -1){
                studentsWithMarks.add(student); //Adds the students to the ArrayList whose marks are not null(If the marks are not null, average is also not null)
            }
        }


        boolean swapOccurred = true;
        while(swapOccurred){
            swapOccurred = false;
        for(int i = 0; i < studentsWithMarks.size() - 1; i++) { //Loop stops one index before the size of the ArrayList because no more students to be compared after the last student
                if (studentsWithMarks.get(i).getAverage() < studentsWithMarks.get(i + 1).getAverage()) { //Swaps the places if the first average is smaller than the next average to make the list in descending order
                    swapOccurred = true;
                    Student highAvg = studentsWithMarks.get(i+1);
                    studentsWithMarks.set(i+1 , studentsWithMarks.get(i));
                    studentsWithMarks.set(i,highAvg);
                }

        }

        }
        System.out.printf("%-20s %-20s %-20s %-20s %-20s %-10s %-20s %-20s%n", "Student Name", "Student ID", "Module 01", "Module 02", "Module 03" , "Total", "Average", "Grade"); //format string
        for(Student student : studentsWithMarks){
            student.fullReport();
        }
    }

}
