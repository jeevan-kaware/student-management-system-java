package Projects;

import java.sql.*;
import java.util.Scanner;

class Employee {
    private int id;
    private String name;
    private double salary;
    private String department;

    Employee(int id, String name, double salary, String department) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.department = department;
    }
    public int getId() { return id; }
    public String getName() { return name; }
    public double getSalary() { return salary; }
    public String getDepartment() { return department; }
}

public class EmployeeManagementSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean exit = false;

        String url = "jdbc:postgresql://localhost:5432/Students";
        String user = "postgres";
        String pass = "jeevan123";

        System.out.println("===== Welcome to Employee Management System =====");

        while (!exit) {
            System.out.println("\n====== Employee Management Menu ======");
            System.out.println("1. Add Employee");
            System.out.println("2. View Employees");
            System.out.println("3. Update Employee");
            System.out.println("4. Delete Employee");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    try (Connection con = DriverManager.getConnection(url, user, pass);
                         PreparedStatement ps = con.prepareStatement(
                                 "INSERT INTO employee(emp_id, emp_name, emp_salary, department) VALUES (?, ?, ?, ?)")) {

                        System.out.print("Enter Employee ID: ");
                        int id = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter Employee Name: ");
                        String name = sc.nextLine();
                        System.out.print("Enter Employee Salary: ");
                        double salary = sc.nextDouble();
                        sc.nextLine();
                        System.out.print("Enter Employee Department: ");
                        String dept = sc.nextLine();

                        Employee emp = new Employee(id, name, salary, dept);

                        ps.setInt(1, emp.getId());
                        ps.setString(2, emp.getName());
                        ps.setDouble(3, emp.getSalary());
                        ps.setString(4, emp.getDepartment());

                        int rows = ps.executeUpdate();
                        if (rows > 0) System.out.println("Employee added successfully!");
                        else System.out.println("Failed to add employee.");

                    } catch (SQLException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 2:
                    try (Connection con = DriverManager.getConnection(url, user, pass);
                         PreparedStatement ps = con.prepareStatement("SELECT * FROM employee");
                         ResultSet rs = ps.executeQuery()) {

                        System.out.println("------ Employee List ------");
                        while (rs.next()) {
                            System.out.println("ID: " + rs.getInt("emp_id"));
                            System.out.println("Name: " + rs.getString("emp_name"));
                            System.out.println("Salary: " + rs.getDouble("emp_salary"));
                            System.out.println("Department: " + rs.getString("department"));
                            System.out.println("---------------------------");
                        }

                    } catch (SQLException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 3:
                    try (Connection con = DriverManager.getConnection(url, user, pass);
                         PreparedStatement ps = con.prepareStatement(
                                 "UPDATE employee SET emp_name=?, emp_salary=?, department=? WHERE emp_id=?")) {

                        System.out.print("Enter Employee ID to update: ");
                        int id = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter new Name: ");
                        String name = sc.nextLine();
                        System.out.print("Enter new Salary: ");
                        double salary = sc.nextDouble();
                        sc.nextLine();
                        System.out.print("Enter new Department: ");
                        String dept = sc.nextLine();

                        ps.setString(1, name);
                        ps.setDouble(2, salary);
                        ps.setString(3, dept);
                        ps.setInt(4, id);

                        int rows = ps.executeUpdate();
                        if (rows > 0) System.out.println("Employee updated successfully!");
                        else System.out.println("No employee found with this ID.");

                    } catch (SQLException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 4:
                    try (Connection con = DriverManager.getConnection(url, user, pass);
                         PreparedStatement ps = con.prepareStatement(
                                 "DELETE FROM employee WHERE emp_id=?")) {

                        System.out.print("Enter Employee ID to delete: ");
                        int id = sc.nextInt();
                        sc.nextLine();

                        ps.setInt(1, id);
                        int rows = ps.executeUpdate();
                        if (rows > 0) System.out.println("Employee deleted successfully!");
                        else System.out.println("No employee found with this ID.");

                    } catch (SQLException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 5:
                    exit = true;
                    System.out.println("Thank you for using Employee Management System!");
                    break;

                default:
                    System.out.println("Invalid choice! Please enter 1-5.");
            }
        }
        sc.close();
    }
}