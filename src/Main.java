import java.sql.*;
import java.util.Scanner;

public class Main {

    private static final String URL = "jdbc:mysql://localhost:3306/hotel_db";
    private static final String USER = "root";
    private static final String PASSWORD = "root1234"; // 👉 change if needed

    public static void main(String[] args) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("\n===== HOTEL MANAGEMENT SYSTEM =====");
                System.out.println("1. Reserve Room");
                System.out.println("2. View Reservations");
                System.out.println("3. Get Room Number");
                System.out.println("4. Update Reservation");
                System.out.println("5. Delete Reservation");
                System.out.println("0. Exit");
                System.out.print("Enter choice: ");

                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        reserveRoom(connection, scanner);
                        break;
                    case 2:
                        viewReservations(connection);
                        break;
                    case 3:
                        getRoomNumber(connection, scanner);
                        break;
                    case 4:
                        updateReservation(connection, scanner);
                        break;
                    case 5:
                        deleteReservation(connection, scanner);
                        break;
                    case 0:
                        System.out.println("Exiting...");
                        scanner.close();
                        connection.close();
                        return;
                    default:
                        System.out.println("Invalid choice!");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void reserveRoom(Connection conn, Scanner sc) {
        try {
            sc.nextLine(); // clear buffer
            System.out.print("Enter guest name: ");
            String name = sc.nextLine();

            System.out.print("Enter room number: ");
            int room = sc.nextInt();

            System.out.print("Enter contact number: ");
            String contact = sc.next();

            String sql = "INSERT INTO reservations (guest_name, room_number, contact_number) VALUES ('"
                    + name + "', " + room + ", '" + contact + "')";

            Statement stmt = conn.createStatement();
            int rows = stmt.executeUpdate(sql);

            System.out.println(rows > 0 ? "Reservation successful!" : "Failed!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void viewReservations(Connection conn) {
        try {
            String sql = "SELECT * FROM reservations";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            System.out.println("\n--- All Reservations ---");
            while (rs.next()) {
                System.out.println(
                        rs.getInt("reservation_id") + " | " +
                                rs.getString("guest_name") + " | " +
                                rs.getInt("room_number") + " | " +
                                rs.getString("contact_number") + " | " +
                                rs.getTimestamp("reservation_date")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void getRoomNumber(Connection conn, Scanner sc) {
        try {
            System.out.print("Enter reservation ID: ");
            int id = sc.nextInt();

            String sql = "SELECT room_number FROM reservations WHERE reservation_id = " + id;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                System.out.println("Room Number: " + rs.getInt("room_number"));
            } else {
                System.out.println("Not found!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void updateReservation(Connection conn, Scanner sc) {
        try {
            System.out.print("Enter reservation ID: ");
            int id = sc.nextInt();

            sc.nextLine();
            System.out.print("Enter new name: ");
            String name = sc.nextLine();

            System.out.print("Enter new room number: ");
            int room = sc.nextInt();

            System.out.print("Enter new contact: ");
            String contact = sc.next();

            String sql = "UPDATE reservations SET guest_name='" + name +
                    "', room_number=" + room +
                    ", contact_number='" + contact +
                    "' WHERE reservation_id=" + id;

            Statement stmt = conn.createStatement();
            int rows = stmt.executeUpdate(sql);

            System.out.println(rows > 0 ? "Updated!" : "Failed!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void deleteReservation(Connection conn, Scanner sc) {
        try {
            System.out.print("Enter reservation ID: ");
            int id = sc.nextInt();

            String sql = "DELETE FROM reservations WHERE reservation_id=" + id;
            Statement stmt = conn.createStatement();
            int rows = stmt.executeUpdate(sql);

            System.out.println(rows > 0 ? "Deleted!" : "Failed!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}