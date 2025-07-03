import java.sql.*;
import java.util.Scanner;

public class EmployeeApp {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/employeesdb";
    private static final String USER = "root";
    private static final String PASS = "password";

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("Connected to the database.");

            boolean running = true;
            while (running) {
                System.out.println("\nMenu:");
                System.out.println("1. Add Employee");
                System.out.println("2. View All Employees");
                System.out.println("3. Update Employee");
                System.out.println("4. Delete Employee");
                System.out.println("5. Exit");
                System.out.print("Choose an option: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // consume newline

                switch (choice) {
                    case 1:
                        System.out.print("Enter Name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter Department: ");
                        String dept = scanner.nextLine();
                        System.out.print("Enter Salary: ");
                        double salary = scanner.nextDouble();
                        addEmployee(conn, name, dept, salary);
                        break;
                    case 2:
                        viewEmployees(conn);
                        break;
                    case 3:
                        System.out.print("Enter Employee ID to Update: ");
                        int updateId = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Enter New Name: ");
                        String newName = scanner.nextLine();
                        System.out.print("Enter New Department: ");
                        String newDept = scanner.nextLine();
                        System.out.print("Enter New Salary: ");
                        double newSalary = scanner.nextDouble();
                        updateEmployee(conn, updateId, newName, newDept, newSalary);
                        break;
                    case 4:
                        System.out.print("Enter Employee ID to Delete: ");
                        int deleteId = scanner.nextInt();
                        deleteEmployee(conn, deleteId);
                        break;
                    case 5:
                        running = false;
                        System.out.println("Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void addEmployee(Connection conn, String name, String dept, double salary) throws SQLException {
        String sql = "INSERT INTO employee (name, department, salary) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setString(2, dept);
            stmt.setDouble(3, salary);
            stmt.executeUpdate();
            System.out.println("Employee added successfully.");
        }
    }

    private static void viewEmployees(Connection conn) throws SQLException {
        String sql = "SELECT * FROM employee";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("ID | Name | Department | Salary");
            while (rs.next()) {
                System.out.printf("%d | %s | %s | %.2f%n",
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("department"),
                        rs.getDouble("salary"));
            }
        }
    }

    private static void updateEmployee(Connection conn, int id, String name, String dept, double salary) throws SQLException {
        String sql = "UPDATE employee SET name=?, department=?, salary=? WHERE id=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setString(2, dept);
            stmt.setDouble(3, salary);
            stmt.setInt(4, id);
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Employee updated successfully.");
            } else {
                System.out.println("Employee not found.");
            }
        }
    }

    private static void deleteEmployee(Connection conn, int id) throws SQLException {
        String sql = "DELETE FROM employee WHERE id=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Employee deleted successfully.");
            } else {
                System.out.println("Employee not found.");
            }
        }
    }
}
