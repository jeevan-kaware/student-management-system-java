package Projects;

import java.util.ArrayList;
import java.util.Scanner;

class Student {

    int id;
    String name;
    int age;
    String course;

    Student(int id, String name, int age, String course) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.course = course;
    }
}

public class StudentManagementSystem {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ArrayList<Student> list = new ArrayList<>();

        boolean exit = false;

        System.out.println("===== Welcome to Student Management System =====");

        while (!exit) {

            System.out.println("\n1 Add Student");
            System.out.println("2 View Students");
            System.out.println("3 Update Student");
            System.out.println("4 Delete Student");
            System.out.println("5 Exit");

            System.out.print("\nEnter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {

                case 1:

                    System.out.print("Enter Student ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Student Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter Student Age: ");
                    int age = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Course: ");
                    String course = sc.nextLine();

                    list.add(new Student(id, name, age, course));

                    System.out.println("Student Added Successfully!");
                    break;

                case 2:

                    if (list.isEmpty()) {
                        System.out.println("No students found.");
                    } else {

                        System.out.println("\n----- Student List -----");

                        for (Student s : list) {
                            System.out.println("ID: " + s.id);
                            System.out.println("Name: " + s.name);
                            System.out.println("Age: " + s.age);
                            System.out.println("Course: " + s.course);
                            System.out.println("-----------------------");
                        }
                    }

                    break;

                case 3:

                    System.out.print("Enter Student ID to Update: ");
                    int updateId = sc.nextInt();

                    boolean found = false;

                    for (Student s : list) {

                        if (s.id == updateId) {

                            sc.nextLine();

                            System.out.print("Enter New Name: ");
                            s.name = sc.nextLine();

                            System.out.print("Enter New Age: ");
                            s.age = sc.nextInt();
                            sc.nextLine();

                            System.out.print("Enter New Course: ");
                            s.course = sc.nextLine();

                            System.out.println("Student Updated Successfully!");
                            found = true;
                            break;
                        }
                    }

                    if (!found) {
                        System.out.println("Student not found!");
                    }

                    break;

                case 4:

                    System.out.print("Enter Student ID to Delete: ");
                    int removeId = sc.nextInt();

                    boolean deleted = false;

                    for (int i = 0; i < list.size(); i++) {

                        if (list.get(i).id == removeId) {

                            list.remove(i);
                            System.out.println("Student Deleted Successfully!");
                            deleted = true;
                            break;
                        }
                    }

                    if (!deleted) {
                        System.out.println("Student not found!");
                    }

                    break;

                case 5:

                    exit = true;
                    System.out.println("Thank you for using the system!");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}