package dao;
import daoservice.DatabaseConnection;
import model.User;
import service.AuditService;

import java.sql.*;
import java.util.HashSet;

public class UserDAO
{
    private static UserDAO instance;
    private final Connection connection = DatabaseConnection.getConnection();
    private UserDAO() throws SQLException {}
    public static UserDAO getInstance() throws SQLException
    {
        if(instance == null)
        {
            instance = new UserDAO();
        }
        return instance;
    }

    public void create(User user) throws SQLException
    {
        String sql = "INSERT INTO testprojectpao.Useri (email_address, password_hash, role) VALUES (?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, user.getEmail_address());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.get_Role());
            int modificari = statement.executeUpdate();
            if (modificari == 0)
            {
                throw new SQLException("Nu s-a putut creea utilizatorul.");
            }
            try (ResultSet generatedKeys = statement.getGeneratedKeys())
            {
                if (generatedKeys.next())
                {
                    user.setId(generatedKeys.getInt(1));
                } else
                {
                    throw new SQLException("Nu s-a putut creea utilizatorul.");
                }
            }
            AuditService.logAction("Created User");
        }


    }

    public User read(String email) throws SQLException {
        String sql = "SELECT * FROM testprojectpao.Useri WHERE email_address = ?";
        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                User user = new User();
                user.setEmail_address(resultSet.getString("email_address"));
                user.setPassword(resultSet.getString("password_hash"));
                user.setRole(resultSet.getString("role"));
                user.setId(resultSet.getInt("id"));
                AuditService.logAction("Searched User");
                return user;
            }
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
        }
        return null;
    }

    public void update(User user) throws SQLException {
        String sql = "UPDATE testprojectpao.Useri SET password_hash = ?, role = ? WHERE email_address = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, user.getPassword());
            statement.setString(2, user.get_Role());
            statement.setString(3, user.getEmail_address());
            statement.executeUpdate();
            AuditService.logAction("Updated User");
        }
    }

    public void delete(String email) throws SQLException
    {
        String sql = "DELETE FROM testprojectpao.Useri WHERE email_address = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);
            statement.executeUpdate();
            AuditService.logAction("Deleted User");

        }
    }
    public HashSet<User> getUsers()
    {
        HashSet<User> users = new HashSet<>();
        String query = "SELECT * FROM Useri";
        try (Statement statement = connection.createStatement())
        {
            ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next())
                {
                    String email = resultSet.getString("email_address");
                    String passwordHash = resultSet.getString("password_hash");
                    String role = resultSet.getString("role");
                    User user = new User(email, passwordHash, role);
                    users.add(user);
                }
            }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }

        return users;
    }
}
