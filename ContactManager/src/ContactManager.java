import java.sql.*;
import java.util.Scanner;

public class ContactManager {
    static final String URL = "jdbc:mysql://localhost:3306/contactdb";
    static final String USER = "root";
    static final String PASS = "iSAUR@2004";  
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
            int choice;
            do {
                System.out.println("\n--- Contact Management System ---");
                System.out.println("1. Add Contact");
                System.out.println("2. View All Contacts");
                System.out.println("3. Search Contact by Name");
                System.out.println("4. Update Contact");
                System.out.println("5. Delete Contact");
                System.out.println("0. Exit");
                System.out.print("Enter your choice: ");
                choice = Integer.parseInt(sc.nextLine());

                switch (choice) {
                    case 1 -> addContact(conn);
                    case 2 -> viewContacts(conn);
                    case 3 -> searchContact(conn);
                    case 4 -> updateContact(conn);
                    case 5 -> deleteContact(conn);
                    case 0 -> System.out.println("Exiting...");
                    default -> System.out.println("Invalid choice!");
                }
            } while (choice != 0);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static void addContact(Connection conn) throws SQLException {
        System.out.print("Enter name: ");
        String name = sc.nextLine();
        System.out.print("Enter phone: ");
        String phone = sc.nextLine();
        System.out.print("Enter email: ");
        String email = sc.nextLine();

        String sql = "INSERT INTO contacts (name, phone, email) VALUES (?, ?, ?)";
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, name);
            pst.setString(2, phone);
            pst.setString(3, email);
            pst.executeUpdate();
            System.out.println("✅ Contact added.");
        }
    }

    static void viewContacts(Connection conn) throws SQLException {
        String sql = "SELECT * FROM contacts";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("\n--- All Contacts ---");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id")
                        + ", Name: " + rs.getString("name")
                        + ", Phone: " + rs.getString("phone")
                        + ", Email: " + rs.getString("email"));
            }
        }
    }

    static void searchContact(Connection conn) throws SQLException {
        System.out.print("Enter name to search: ");
        String name = sc.nextLine();
        String sql = "SELECT * FROM contacts WHERE name LIKE ?";
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, "%" + name + "%");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id")
                        + ", Name: " + rs.getString("name")
                        + ", Phone: " + rs.getString("phone")
                        + ", Email: " + rs.getString("email"));
            }
        }
    }

    static void updateContact(Connection conn) throws SQLException {
        System.out.print("Enter contact ID to update: ");
        int id = Integer.parseInt(sc.nextLine());

        System.out.print("Enter new name: ");
        String name = sc.nextLine();
        System.out.print("Enter new phone: ");
        String phone = sc.nextLine();
        System.out.print("Enter new email: ");
        String email = sc.nextLine();

        String sql = "UPDATE contacts SET name=?, phone=?, email=? WHERE id=?";
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, name);
            pst.setString(2, phone);
            pst.setString(3, email);
            pst.setInt(4, id);
            int rowsUpdated = pst.executeUpdate();
            System.out.println(rowsUpdated > 0 ? "✅ Contact updated." : "❌ Contact not found.");
        }
    }

    static void deleteContact(Connection conn) throws SQLException {
        System.out.print("Enter contact ID to delete: ");
        int id = Integer.parseInt(sc.nextLine());

        String sql = "DELETE FROM contacts WHERE id=?";
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, id);
            int rowsDeleted = pst.executeUpdate();
            System.out.println(rowsDeleted > 0 ? "🗑️ Contact deleted." : "❌ Contact not found.");
        }
    }
}
