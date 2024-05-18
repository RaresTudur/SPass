package dao;

import daoservice.DatabaseConnection;
import model.Admin;
import service.AuditService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class AdminDAO
{
    private static AdminDAO instance;
    private Connection connection = DatabaseConnection.getConnection();

    private AdminDAO() throws SQLException
    {

    }
    public static AdminDAO getInstance() throws SQLException
    {
        if(instance == null)
        {
            instance = new AdminDAO();
        }
        return  instance;
    }

    public void create(Admin admin) throws SQLException
    {
        String sql = "INSERT INTO Admini (id,adminRole, nume) VALUES (?,?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql))
        {
            statement.setInt(1, admin.getId());
            statement.setString(2, admin.getAdminRole());
            statement.setString(3, admin.getNume());
            statement.executeUpdate();
            AuditService.logAction("Created Admin");
        }
    }

    public Admin read(int id) throws SQLException
    {
        String sql = "SELECT a.id, a.adminRole, a.nume, u.email_address " +
                "FROM Admini a " +
                "JOIN Useri u ON a.id = u.id " + "WHERE a.id = ? ";
        try (PreparedStatement statement = connection.prepareStatement(sql))
        {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery())
            {
                if (resultSet.next())
                {
                    String adminRole = resultSet.getString("adminRole");
                    String nume = resultSet.getString("nume");
                    String email = resultSet.getString("email_address");
                    Admin admin = new Admin();
                    admin.setId(id);
                    admin.setNume(nume);
                    admin.setAdminRole(adminRole);
                    admin.setEmail_address(email);
                    AuditService.logAction("Searched Admin");
                    return admin;
                }
            }
        }
        return null;
    }

    public HashSet<Admin> getAllAdmins() throws SQLException {
        HashSet<Admin> admins = new HashSet<>();
        String sql = "SELECT a.id, a.adminRole, a.nume, u.email_address " +
                "FROM Admini a " +
                "JOIN Useri u ON a.id = u.id";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String adminRole = resultSet.getString("adminRole");
                String nume = resultSet.getString("nume");
                String email = resultSet.getString("email_address");
                Admin admin = new Admin();
                admin.setId(id);
                admin.setNume(nume);
                admin.setAdminRole(adminRole);
                admin.setEmail_address(email);
                admins.add(admin);
            }
        }
        return admins;
    }

    public void update(Admin admin) throws SQLException {
        String sql = "UPDATE Admini SET adminRole = ?, nume = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, admin.getAdminRole());
            statement.setString(2, admin.getNume());
            statement.setInt(3, admin.getId());
            statement.executeUpdate();
            AuditService.logAction("Updated Admin");
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM Admini WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
            AuditService.logAction("Deleted Admin");
        }
    }
}
