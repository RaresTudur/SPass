package dao;

import daoservice.DatabaseConnection;
import service.AuditService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserCleaner {

    // Database connection detail

    public static void main() {
        try {
            Connection connection = DatabaseConnection.getConnection();

            String findStudentsQuery =
                    "SELECT id FROM Useri WHERE role = 'Student' AND id NOT IN (SELECT id FROM Studenti)";
            PreparedStatement findStudentsStmt = connection.prepareStatement(findStudentsQuery);
            ResultSet rs = findStudentsStmt.executeQuery();

            String deleteUserQuery = "DELETE FROM Useri WHERE id = ?";
            PreparedStatement deleteUserStmt = connection.prepareStatement(deleteUserQuery);

            while (rs.next())
            {
                int userId = rs.getInt("id");
                deleteUserStmt.setInt(1, userId);
                deleteUserStmt.executeUpdate();
                AuditService.logAction("Deleted user with user_id: " + userId);
            }

            rs.close();
            findStudentsStmt.close();
            deleteUserStmt.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}