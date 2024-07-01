import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Arrays;


public class MainMenu {
    static Scanner input = new Scanner(System.in);
    static Student[] students = new Student[100]; //initialize an array of student class which can contain 100 students

    public static void main(String[] args) {
    //Creation of main menu and what methods to be called when different option are selected
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
                                System.out.println("Select number between 1 to 9");
                                continue;

                        }
                        break;


                }
                catch (Exception e) {
                    System.out.println("Enter a valid input");
                    input.next();
                }

            }

        }

    }

    // Define the methods
    private static void initialize(Student[] students){
        //Initialize the student name and id as vacant and module marks as null
        for(int i = 0; i < students.length;i++){
            students[i] = new Student("vacant", "vacant", new Module(null,null,null));
        }
        System.out.println();
    }

    public static boolean isMarkInvalid(double mark){
        //Checks if a mark is between 0 and 100
        return mark > 100 || mark < 0;
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
                System.out.println("Enter a valid input");
                input.next();
                continue;
            }
            if(number < 1 || number > 100){
                System.out.println("Select a number between 1 to 100");
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
                                    Pattern pattern = Pattern.compile("w\\d{7}$");
                                    Matcher matcher = pattern.matcher(id);
                                    if (matcher.find()) {
                                        System.out.println("Correct Format");
                                        students[i].setStudentId(id);
                                        break;
                                    } else {
                                        System.out.println("Incorrect Format. Please enter the Student ID again (Ex: w4513490)");
                                    }
                                }
                                else{
                                    System.out.println("This Student ID already exists!!!");
                                    while(true) {
                                        System.out.println("Do you wish to continue: ");
                                        String response = input.next();

                                        if (response.equals("yes")) {
                                            break; //Goes back to the beginning of the loop
                                        }
                                        else if (response.equals("no")) {
                                            System.out.println("Back to Main Menu");
                                            System.out.println();
                                            return; //Exits the current menu option and goes back ot the main menu
                                        }
                                        else {
                                            System.out.println("Enter yes/no");
                                        }
                                    }
                                }
                            }
                            catch (Exception e) {
                                System.out.println("Enter a valid input");
                            }
                        }
                }

                else{
                    System.out.println("Number "+ number +" is occupied");
                    while(true) {
                        System.out.println("Do you wish to continue: ");
                        String response = input.next();

                        if (response.equals("yes")) {
                            break;
                        }
                        else if (response.equals("no")) {
                            System.out.println("Back to Main Menu");
                            return;
                        }
                        else {
                            System.out.println("Enter yes/no");
                        }
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
                        System.out.println("Enter a valid input");
                        input.next();
                    }
                }



                while(true) {
                System.out.println("Do you wish to continue: ");
                String response = input.next();

                    if (response.equals("yes")) {
                        break;
                    }
                    else if (response.equals("no")) {
                        System.out.println("Back to Main Menu");
                        return;
                    }
                    else {
                        System.out.println("Enter yes/no");
                    }
                }
            }
        }
    }
    public static void deleteStudent(){
        while(true) {
            try {
                System.out.println("Enter the Student ID to delete the details: ");
                String id = input.next();
                Pattern pattern = Pattern.compile("^w\\d{7}$"); //Compiles the regular expression
                Matcher matcher = pattern.matcher(id); //Creates a Matcher object that will match the user input with the compiled pattern
                if(matcher.find()){ //if the user input matches with the compiled pattern returns true
                    for(int i = 0;i < 100;i++){
                        if(students[i].getStudentId().equals(id)){
                            students[i].setStudentName("vacant");
                            students[i].setStudentId("vacant");
                            System.out.println("Details for Student ID " +id+ " was deleted successfully...");
                            return;
                        }
                    }
                    System.out.println("This Student ID does not exist!!!");
                    while(true) {
                        System.out.println("Do you wish to continue: ");
                        String response = input.next();

                        if (response.equals("yes")) {
                            break;
                        }
                        else if (response.equals("no")) {
                            System.out.println("Back to Main Menu");
                            return;
                        }
                        else {
                            System.out.println("Enter yes/no");
                        }
                    }
                }
                else{
                    System.out.println("Please enter a valid ID (Ex: w1234568)");
                    while(true) {
                        System.out.println("Do you wish to continue: ");
                        String response = input.next();

                        if (response.equals("yes")) {
                            break;
                        }
                        else if (response.equals("no")) {
                            System.out.println("Back to Main Menu");
                            return;
                        }
                        else {
                            System.out.println("Enter yes/no");
                        }
                    }

                }
            }
            catch(InputMismatchException e){
                System.out.println("Enter a valid input");
                input.next();
            }
        }

    }

    public static void findStudent(){
        while(true) {
            try {
                boolean idExist = false;
                System.out.println("Enter the Student ID to find the details: ");
                String id = input.next();
                Pattern pattern = Pattern.compile("w\\d{7}$");
                Matcher matcher = pattern.matcher(id);
                if(matcher.find()){
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
                    if(!idExist) { //if the id if not found returns true
                        System.out.println("This Student ID does not exist!!!");
                    }
                    while(true) {
                        System.out.println("Do you wish to continue: ");
                        String response = input.next();

                        if (response.equals("yes")) {
                            break;
                        }
                        else if (response.equals("no")) {
                            System.out.println("Back to Main Menu");
                            System.out.println();
                            return;
                        }
                        else {
                            System.out.println("Enter yes/no");
                        }
                    }

                }
                else{
                    System.out.println("Please enter a valid ID (Ex: w1234568)");

                }
            }
            catch(InputMismatchException e){
                System.out.println("Enter a valid input");
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
            System.out.println("This file cannot be found");
        }
        catch(IOException e){
            System.out.println("Error occurred: "+e);
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
                    students[index].setModule(new Module(Integer.parseInt(details[2]), (Integer.parseInt(details[3])), (Integer.parseInt(details[4]))));
                }
                index++;
            }
            System.out.println("Data has been loaded. You can view and edit the details");
            System.out.println();
        }
        catch(FileNotFoundException e){
            System.out.println("Data cannot be loaded from the file");
        }
        catch(IOException e){
            System.out.println("Error Occurred: " + e);
        }
        catch(Exception e){
            System.out.println("Error Occurred"+ e);
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
                System.out.printf("%-20s %20s%n", (name.getStudentName()), (name.getStudentId()) );
                order++;
            }
        }

        System.out.println();
    }

    public static void moduleDetailsManagement(){
        //Creation if sub menu for option 8
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
                    String choice = input.next();
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
                            System.out.println("Invalid input! Enter a,b,c or d");
                            continue;
                    }
                    break;

                }
                catch (InputMismatchException e) {
                    System.out.println("Please enter a valid input");
                    input.next();
                    continue;
                }

            }

        }
    }

    public static void addStudentName(){
        while(true) {
            try {
                boolean idExist = false;
                System.out.println("Enter the Student ID: ");
                String id = input.next();
                for (Student student : students) {
                    if (student.getStudentId().equals(id)) {
                        idExist = true;
                        System.out.println("Enter the Student Name: ");
                        student.setStudentName(input.nextLine());
                        System.out.println("Student name has been recorded successfully");
                    }

                    }
                if(!idExist){
                    System.out.println("This Student ID does not exist");
                }
            }
            catch(InputMismatchException e){
                System.out.println("Invalid Input");
                input.next();
            }
            boolean exit = true;
            while(true) {
                System.out.println("Do you wish to continue: ");
                String response = input.next();

                if (response.equals("yes")) {
                    exit = false;
                    break;
                }
                else if (response.equals("no")) {
                    System.out.println("Back to Main Menu");
                    break;
                }
                else {
                    System.out.println("Enter yes/no");
                }
            }
            if(exit){
                break;
            }
        }
    }

    public static void addModuleMarks(){
        while(true){
            try{
                boolean found = false;
                System.out.println("Enter the StudentID: ");
                String id = input.next();
                for(Student student : students){
                    if(student.getStudentId().equals(id)){
                        found = true;
                        while(true) {
                            System.out.println("Enter Module 01 Marks: ");
                            int mark1 = input.nextInt();

                            if (isMarkInvalid(mark1)) { //Check if the input is between 0 and 100
                                System.out.println("Invalid Marks. Please enter a mark between 1 to 100");
                                continue;
                            }

                            System.out.println("Enter Module 02 Marks: ");
                            int mark2 = input.nextInt();
                            if (isMarkInvalid(mark2)) {
                                System.out.println("Invalid Marks. Please enter a mark between 1 to 100");
                                continue;
                            }

                            System.out.println("Enter Module 03 Marks: ");
                            int mark3 = input.nextInt();
                            if (isMarkInvalid(mark3)) {
                                System.out.println("Invalid Marks. Please enter a mark between 1 to 100");
                                continue;
                            }

                            student.setModule(new Module(mark1, mark2, mark3));
                            System.out.println("Module marks for Student ID " + student.getStudentId() + " was Recorded Successfully...");
                            break;
                        }
                    }

                }
                if(!found){
                    System.out.println("This student ID does not exist");
                }
            }
            catch(InputMismatchException e){
                System.out.println("Enter a valid input");
                continue;
            }

            while(true) {

                System.out.println("Do you wish to continue: ");
                String response = input.next();

                if (response.equals("yes")) {
                    break;
                }
                else if (response.equals("no")) {
                    System.out.println("Back to Module Management Details Menu");
                    return;
                }
                else {
                    System.out.println("Enter yes/no");
                }
            }


        }

    }

    public static void getSummary() {
        int count = 0;

        for (int i = 0; i < 100; i++) {
            if (!students[i].getStudentName().equals("vacant")) {
                count += 1;
            }
        }
        System.out.println("Total Number of Student Registrations:  " + count);

        int highMarksModule1 = 0;
        int highMarksModule2 = 0;
        int highMarksModule3 = 0;
        if(count != 0) {
            for (Student student : students) {
                if (!student.getStudentId().equals("vacant") && student.getMark1() != null && student.getMark2() != null && student.getMark3() != null){ //Ensures that the student in the current iteration holds a student not vacant and have all three module marks entered
                    if (student.getMark1() > 40) {
                        highMarksModule1++;
                    }
                    if (student.getMark2() > 40) {
                        highMarksModule2++;
                    }
                    if (student.getMark3() > 40) {
                        highMarksModule3++;
                    }
                }

            }

            System.out.println("Number of Students who Scored Higher than 40 in : ");
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
            if(student.module.getAverage() != null){
                studentsWithMarks.add(student); //Adds the students to the ArrayList whose marks are not null(If the marks are not null, average is also not null)
            }
        }



        boolean swapOccurred = true;
        while(swapOccurred){
            swapOccurred = false;
        for(int i = 0; i < studentsWithMarks.size() - 1; i++) { //Loop stops one index before the size of the ArrayList because no more students to be compared after the last student
                if (studentsWithMarks.get(i).module.getAverage() < studentsWithMarks.get(i + 1).module.getAverage()) { //Swaps the places if the first average is smaller than the next average to make the list in descending order
                    swapOccurred = true;
                    Student highAvg = studentsWithMarks.get(i+1);
                    studentsWithMarks.set(i+1 , studentsWithMarks.get(i));
                    studentsWithMarks.set(i,highAvg);
                }

        }

        }
        System.out.printf("%-20s %-20s %-10s %-10s %-10s %-10s %-20s %-20s%n", "Student Name", "Student ID", "Mark 01", "Mark 02", "Mark 03" , "Total", "Average", "Grade");
        for(Student student : studentsWithMarks){
            student.fullReport(student);
        }
    }


    }