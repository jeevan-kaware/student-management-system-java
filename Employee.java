package Project4;

public class Employee {
    private int id;
    private String name;
    private double salary;
    private int d_id;
        Employee(int id,String name,double salary,int d_id){
            this.id = id;
            this.name = name;
            this.salary = salary;
            this.d_id = d_id;
        }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public int getD_id() {
        return d_id;
    }
}
