package org.example;

import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {

    public static  String [][] studentArray = new String[100][6];
    public static String[][] subjectArray = new String[100][3];
    public static int subjectCount = 0;
    public static int[][] marksArray = new int[100][100];
    public static int studentCount = 0;
    public static Scanner scanner = new Scanner( System.in);

    public static void main(String[] args) {
        login();
    }

    public static void login(){
        String user = "tharindu";
        String pass = "2005";

        System.out.println("Enter Username :");
        String username  = scanner.next();
        System.out.println("Enter Password :");
        String password = scanner.next();

        if (user.equals(user)&& pass.equals(password)){
            clearData();
            homepage();

        }else {
            System.out.println("incorrect username or password");
        }

    }
    private static void clearData() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void homepage(){
        System.out.println("1.Manage Stdudent");
        System.out.println("2.Manage Subject Marks");
        System.out.println("3.Exit");
        System.out.print(" Enter Any Option to Continue : ");
        String opt = scanner.next();

        if (opt.equals("1")){
            clearData();
            manageStudent();
        }
      if (opt.equals("3")){
          clearData();
          login();
      }
     if (opt.equals("2")){
     clearData();
     manageSubjectMarks();
 }

    }

    private static void manageSubjectMarks() {
        System.out.println("1.Add new Subjects");
        System.out.println("2.Add Marks");
        System.out.println("3.Update Marks");
        System.out.println("4.View Subjects");
        System.out.println("5.Main");
        System.out.println("6.Exit");

        System.out.println();
        System.out.println("Enter Any Option to Continue : ");
        String opt = scanner.next();

        if (opt.equals("1")){
            clearData();
            addNewSubjects();
        }

        if (opt.equals("2")){
            clearData();
            addMarks();
        }

        if (opt.equals("3")){
            clearData();
            updateMarks();

        }
        if (opt.equals("4")){
            clearData();
            viewSubjects();

        }
        if (opt.equals("6")){
            clearData();
            login();
        }
        if (opt.equals("5")){
            clearData();
            homepage();
        }
    }

    private static void viewSubjects() {

        System.out.println("---------------------- Subject List -----------------------");
        System.out.println("SID\tName\tBatch\tSubIDs\tSubNames\tMarks\tTotal\tAvg\tPlace");
        System.out.println("----------------------------------------------------------");

        for (int i = 0; i < studentCount; i++) {
            System.out.print(studentArray[i][0] + "\t");
            System.out.print(studentArray[i][1] + "\t");
            System.out.print(studentArray[i][5] + "\t");

            int totalMarks = 0;
            int subjectCounter = 0;
            for (int j = 0; j < subjectCount; j++) {
                if (marksArray[i][j] != 0) {
                    System.out.print(subjectArray[j][0] + " ");
                    System.out.print(subjectArray[j][1] + " ");
                    System.out.print(marksArray[i][j] + " ");
                    totalMarks += marksArray[i][j];
                    subjectCounter++;
                }
            }

            double avgMarks = subjectCounter == 0 ? 0 : (double) totalMarks / subjectCounter;
            System.out.print(totalMarks + "\t");
            System.out.print(avgMarks + "\t");

            System.out.println();
        }

        System.out.println("1. Main Menu");
        System.out.println("2. Exit");
        String opt = scanner.next();
        if (opt.equals("1")) {
            clearData();
            manageSubjectMarks();
        } else if (opt.equals("2")) {
            clearData();
            login();
        }
    }
    
    private static void updateMarks() {
        System.out.println("------------------ Update Subject Marks ------------------");
        System.out.print("Enter Student ID: ");
        String sid = scanner.next();

        int studentIndex = findStudentIndex(sid);
        if (studentIndex == -1) {
            System.out.println("Student with SID " + sid + " not found.");
            return;
        }

        System.out.print("Enter Subject ID: ");
        String subId = scanner.next();

        int subjectIndex = findSubjectIndex(subId);
        if (subjectIndex == -1) {
            System.out.println("Subject with ID " + subId + " not found.");
            return;
        }

        System.out.print("Enter New Marks: ");
        int marks = scanner.nextInt();

        marksArray[studentIndex][subjectIndex] = marks;

        System.out.println("Marks updated successfully.");
        System.out.println("1. Main Menu");
        System.out.println("2. Exit");
        String opt = scanner.next();
        if (opt.equals("1")) {
            clearData();
            manageSubjectMarks();
        } else if (opt.equals("2")) {
            clearData();
            login();
        }
    }

    public static int findSubjectIndex(String subId) {
        for (int i = 0; i < subjectCount; i++) {
            if (subjectArray[i][0].equals(subId)) {
                return i;
            }
        }
        return -1;
    }
    public static boolean subjectExists(String subId) {
        return findSubjectIndex(subId) != -1;
    }
    private static void addMarks() {
        System.out.println("------------------ Add Subject Marks ------------------");
        System.out.print("Enter Student ID: ");
        String sid = scanner.next();

        int studentIndex = findStudentIndex(sid);
        if (studentIndex == -1) {
            System.out.println("Student with SID " + sid + " not found.");
            return;
        }

        System.out.print("Enter Subject ID: ");
        String subId = scanner.next();

        int subjectIndex = findSubjectIndex(subId);
        if (subjectIndex == -1) {
            System.out.println("Subject with ID " + subId + " not found.");
            return;
        }

        System.out.print("Enter Marks: ");
        int marks = scanner.nextInt();

        marksArray[studentIndex][subjectIndex] = marks;

        System.out.println("Marks added successfully.");
        System.out.println("1. Main Menu");
        System.out.println("2. Exit");
        String opt = scanner.next();
        if (opt.equals("1")) {
            clearData();
            manageSubjectMarks();
        } else if (opt.equals("2")) {
            clearData();
            login();
        }


    }

    private static void addNewSubjects() {
        System.out.println("------------------ Add New Subjects ------------------");
        System.out.print("Enter Subject ID: ");
        String subId = scanner.next();
        System.out.print("Subject Name: ");
        String subName = scanner.next();

        if (subjectCount < subjectArray.length) {
            subjectArray[subjectCount][0] = subId;
            subjectArray[subjectCount][1] = subName;
            subjectCount++;
            System.out.println("Subject added successfully.");
        } else {
            System.out.println("Subject array is full.");
        }

        System.out.println("1. Main Menu");
        System.out.println("2. Exit");
        String opt = scanner.next();
        if (opt.equals("1")) {
            clearData();
            manageSubjectMarks();
        } else if (opt.equals("2")) {
            clearData();
            login();
        }

    }



    public static void manageStudent (){
        System.out.println("1.Add new student :");
        System.out.println("2.Update Student :");
        System.out.println("3.View Student :");
        System.out.println("4.Main");
        System.out.println("5.Exit");
        String opt = scanner.next();


        if (opt.equals("1")){
            clearData();
            AddStudent();
        }

        if (opt.equals("2")){
            clearData();
            updateStudent();
        }

        if (opt.equals("3")){
            clearData();
            viewStudents();
        }
        
        
        if (opt.equals("5")){
            clearData();
            login();
        }
        if (opt.equals("4")){
            clearData();
            homepage();
        }

    }

    public static void viewStudents() {
        System.out.println("---------------------- Student List -----------------------");
        System.out.println("SID\tName\tAge\tContact\tAddress\tBatch");
        System.out.println("----------------------------------------------------------");

        for (int i = 0; i < studentCount; i++) {
            for (int j = 0; j < studentArray[i].length; j++) {
                System.out.print(studentArray[i][j] + "\t");
            }
            System.out.println();
        }

        System.out.println("1.Main Menu");
        System.out.println("2.Exit");
        String opt = scanner.next();
        if(opt.equals("1")){
            clearData();
            manageStudent();
        }
        if(opt.equals("2")){
            clearData();
            login();
        }

    }
    public static void updateStudent() {
        System.out.println("---------------------- Update Student -----------------------");
        System.out.println();

        System.out.print("Enter SID of the student to update: ");
        String sid = scanner.next();

        int studentIndex = findStudentIndex(sid);
        if (studentIndex == -1) {
            System.out.println("Student with SID " + sid + " not found.");
            clearData();
            updateStudent();
        }

        System.out.print("Name: ");
        String name = scanner.next();
        System.out.print("Age: ");
        String age = scanner.next();
        System.out.print("Contact: ");
        String contact = scanner.next();
        System.out.print("Address: ");
        String address = scanner.next();
        System.out.print("Batch: ");
        String batch = scanner.next();

        studentArray[studentIndex][1] = name;
        studentArray[studentIndex][2] = age;
        studentArray[studentIndex][3] = contact;
        studentArray[studentIndex][4] = address;
        studentArray[studentIndex][5] = batch;

        System.out.println("Student details updated successfully.");
        System.out.println();
        System.out.println();

        System.out.println("1.Main Menu");
        System.out.println("2.Exit");
        System.out.println("3.Update Another Student");
        System.out.print(" Enter Any Option to Continue : ");

        String opt = scanner.next();
        if(opt.equals("1")){
            clearData();
            manageStudent();
        }
        if(opt.equals("2")){
            clearData();
            login();
        }

        if(opt.equals("3")){
            clearData();
            updateStudent();
        }


    }

    public static int findStudentIndex(String sid) {
        for (int i = 0; i < studentCount; i++) {
            if (studentArray[i][0].equals(sid)) {
                return i;
            }
        }
        return -1;
    }

    public static void AddStudent (){

        System.out.println("---------------------- Add Student-----------------------");
        System.out.println();
        System.out.println();

        System.out.println("SID");
        String sid = scanner.next();

        if (studentExists(sid)){
            System.out.println();
            System.out.println("Student with SID " + sid + " already exists.");
            return;
        }
        System.out.println("Name :");
        String name = scanner.next();
        System.out.println("Age :");
        String age = scanner.next();
        System.out.println("Contact");
        String contact = scanner.next();
        System.out.println("Addres");
        String address = scanner.next();
        System.out.println("Batch");
        String batch = scanner.next();

        if (studentCount < studentArray.length) {
            studentArray[studentCount][0] = sid;
            studentArray[studentCount][1] = name;
            studentArray[studentCount][2] = age;
            studentArray[studentCount][3] = contact;
            studentArray[studentCount][4] = address;
            studentArray[studentCount][5] = batch;
            studentCount++;
            System.out.println("Student added successfully.");
            System.out.println("1.Main Menu");
            System.out.println("2.Exit");
            String opt = scanner.next();
            if(opt.equals("1")){
                clearData();
                manageStudent();
            }
            if(opt.equals("2")){
                clearData();
                login();
            }
        } else {
            System.out.println("Student array is full.");
        }
    }

    public static boolean studentExists(String sid) {
        for (int i = 0; i < studentCount; i++) {
            if (studentArray[i][0].equals(sid)) {
                return true;
            }
        }
        return false;
    }
}