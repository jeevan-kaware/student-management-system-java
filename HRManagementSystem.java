package Project4;

import java.sql.*;
import java.util.Scanner;

public class HRManagementSystem {
    public static final String url = "jdbc:postgresql://localhost:5432/Project4";
    public static final String user = "postgres";
    public static  final String pass = "jeevan123";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean found = false;
        System.out.println("------- Welcome To HR Management System ---------");
        while(!found){
            System.out.println("\n========== Welcome To HR Management System ==========");
            System.out.println("\n 1. Add Department");
            System.out.println("2. Add Employee");
            System.out.println("3. View All Employees");
            System.out.println("4. Department Wise Employee");
            System.out.println("5. Highest Salary Employee");
            System.out.println("6. Search Employee");
            System.out.println("7. Update Employee");
            System.out.println("8. Remove Employee");
            System.out.println("9. Exit");
            System.out.println("Enter your choice ");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice){
                case 1:
                    addDepartment(sc);
                    break;
                case 2:
                    addEmployee(sc);
                    break;
                case 3:
                    viewAllEmployee();
                    break;
                case 4:
                    departmentWiseEmployee(sc);
                    break;
                case 5:
                    highestSalary();
                    break;
                case 6:
                    searchEmployee(sc);
                    break;
                case 7:
                    updateEmployee(sc);
                    break;
                case 8:
                    deleteEmployee(sc);
                    break;
                case 9:
                    found = true;
                    System.out.println("Thank you for using HR System!");
                    break;
                default:
                    System.out.println("Invalid choice ");
            }
        }
    }
    public static void addDepartment(Scanner sc){
        try(Connection con = DriverManager.getConnection(url,user,pass);
            PreparedStatement ps = con.prepareStatement("INSERT INTO department(dep_id,dep_name) VALUES(?,?)")
        ){
            System.out.println("Enter Department Id :");
            int id = sc.nextInt();
            sc.nextLine();
            System.out.println("Enter Department name :");
            String name = sc.nextLine();
            Department d = new Department(id,name);
            ps.setInt(1,d.getId());
            ps.setString(2,d.getName());
            int row = ps.executeUpdate();
            if(row>0){
                System.out.println("Department Added Successfully !");
            }else{
                System.out.println("Department Not Added !");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public static void addEmployee(Scanner sc) {
        try (Connection con = DriverManager.getConnection(url, user, pass);
             PreparedStatement ps = con.prepareStatement("INSERT INTO employee(emp_id,emp_name,emp_salary,dep_id) VALUES(?,?,?,?)")
        ) {
            System.out.print("Enter Employee ID: ");
            int id = sc.nextInt();
            sc.nextLine();
            System.out.print("Enter Employee Name: ");
            String name = sc.nextLine();
            System.out.print("Enter Employee Salary: ");
            double salary = sc.nextDouble();
            sc.nextLine();
            System.out.print("Enter Employee Department Id: ");
            int d_id = sc.nextInt();
            sc.nextLine();
            Employee e = new Employee(id, name, salary, d_id);
            ps.setInt(1, e.getId());
            ps.setString(2, e.getName());
            ps.setDouble(3, e.getSalary());
            ps.setInt(4, e.getD_id());
            int row = ps.executeUpdate();
            if (row > 0) {
                System.out.println("Employee Added Successfully !");
            } else {
                System.out.println("Employee Not Added !");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void viewAllEmployee(){
        try(Connection con = DriverManager.getConnection(url,user,pass);
        PreparedStatement ps = con.prepareStatement("SELECT e.emp_id,e.emp_name,e.emp_salary,d.dep_name "+"FROM employee e "+"JOIN department d ON e.dep_id = d.dep_id");
            ResultSet rs = ps.executeQuery();
        ){
            while(rs.next()){
                System.out.println("--------------------------------------------");
                System.out.println("Employee ID :" + rs.getInt("emp_id"));
                System.out.println("Employee Name :" + rs.getString("emp_name"));
                System.out.println("Employee Salary :" + rs.getDouble("emp_salary"));
                System.out.println("Department Name :" + rs.getString("dep_name"));
                System.out.println("--------------------------------------------");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public static void departmentWiseEmployee(Scanner sc){
        try(Connection con = DriverManager.getConnection(url,user,pass);
        PreparedStatement ps = con.prepareStatement("SELECT e.emp_id,e.emp_name,e.emp_salary,d.dep_name "+"FROM employee e "+"JOIN department d ON e.dep_id = d.dep_id "+"WHERE d.dep_name=?")
        ){
            System.out.println("Enter Department Name :");
            String name = sc.nextLine();
            ps.setString(1,name);
            ResultSet rs = ps.executeQuery();
            boolean found = false;
            while(rs.next()){
                found = true;
                System.out.println("--------------------------------------------");
                System.out.println("Employee ID :" + rs.getInt("emp_id"));
                System.out.println("Employee Name :" + rs.getString("emp_name"));
                System.out.println("Employee Salary :" + rs.getDouble("emp_salary"));
                System.out.println("Department Name :" + rs.getString("dep_name"));
                System.out.println("--------------------------------------------");
            }
            if(!found){
                System.out.println("Employee Not found ");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public static void highestSalary(){
        try(Connection con = DriverManager.getConnection(url,user,pass);
        PreparedStatement ps = con.prepareStatement("SELECT e.emp_id,e.emp_name,e.emp_salary,d.dep_name "+"FROM employee e "+"JOIN department d ON e.dep_id = d.dep_id "+"ORDER BY emp_salary DESC LIMIT 1");
        ResultSet rs = ps.executeQuery();
        ){
            System.out.println("Highest Salary Employee");
            while (rs.next()) {
                System.out.println("--------------------------------------------");
                System.out.println("Employee ID :" + rs.getInt("emp_id"));
                System.out.println("Employee Name :" + rs.getString("emp_name"));
                System.out.println("Employee Salary :" + rs.getDouble("emp_salary"));
                System.out.println("Department Name :" + rs.getString("dep_name"));
                System.out.println("--------------------------------------------");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public static void searchEmployee(Scanner sc){
        try(Connection con = DriverManager.getConnection(url,user,pass);
            PreparedStatement ps = con.prepareStatement("SELECT e.emp_id,e.emp_name,e.emp_salary,d.dep_name "+"FROM employee e "+"JOIN department d ON e.dep_id = d.dep_id "+"WHERE emp_name LIKE ?")

        ){
            boolean found = false;
            System.out.println("Enter Name :");
            String name = sc.nextLine();
            ps.setString(1,name + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                found = true;
                System.out.println("--------------------------------------------");
                System.out.println("Employee ID :" + rs.getInt("emp_id"));
                System.out.println("Employee Name :" + rs.getString("emp_name"));
                System.out.println("Employee Salary :" + rs.getDouble("emp_salary"));
                System.out.println("Department Name :" + rs.getString("dep_name"));
                System.out.println("--------------------------------------------");
            }
            if (!found){
                System.out.println("Employee NoT Found ");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public static void updateEmployee(Scanner sc){
        try(Connection con = DriverManager.getConnection(url,user,pass);
            PreparedStatement ps = con.prepareStatement("UPDATE employee SET emp_name=?,emp_salary=?,dep_id=? WHERE emp_id = ?")
        ){

            System.out.print("Enter Employee ID to Update: ");
            int id = sc.nextInt();
            sc.nextLine();
            System.out.print("Enter new Employee Name: ");
            String name = sc.nextLine();
            System.out.print("Enter new Employee Salary: ");
            double salary = sc.nextDouble();
            sc.nextLine();
            System.out.print("Enter new Employee Department Id: ");
            int d_id = sc.nextInt();
            sc.nextLine();
            ps.setString(1,name);
            ps.setDouble(2,salary);
            ps.setInt(3,d_id);
            ps.setInt(4,id);
            int row = ps.executeUpdate();
            if (row > 0) {
                System.out.println("Employee Update Successfully !");
            } else {
                System.out.println("Employee Not Updated !");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public static void deleteEmployee(Scanner sc){
        try(Connection con = DriverManager.getConnection(url,user,pass);
           PreparedStatement ps = con.prepareStatement("DELETE FROM employee WHERE emp_id=?")
            ){
            System.out.println("Enter Employee ID :");
            int id = sc.nextInt();
            ps.setInt(1,id);
            int row = ps.executeUpdate();
            if(row>0){
                System.out.println("Delete Employee Successfully !");
            }else{
                System.out.println("Employee not Delete !");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

}