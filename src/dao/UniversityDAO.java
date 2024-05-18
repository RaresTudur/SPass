package dao;

import daoservice.DatabaseConnection;
import model.Universitate;
import service.AuditService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UniversityDAO {
    private static UniversityDAO instance;
    private final Connection connection = DatabaseConnection.getConnection();

    private UniversityDAO() throws SQLException
    {
    }

    public static synchronized UniversityDAO getInstance() throws SQLException
    {
        if (instance == null) {
            instance = new UniversityDAO();
        }
        return instance;
    }

    public void create(Universitate universitate) throws SQLException {
        String sql = "INSERT INTO Universitati (nume, adresa, email) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, universitate.getNume_Universitate());
            preparedStatement.setString(2, universitate.getAdresa());
            preparedStatement.setString(3, universitate.getEmail_address());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                int generatedId = resultSet.getInt(1);
                universitate.setId_Universitate(generatedId);
            }
            AuditService.logAction("Created University");
        }
    }

    public void update(Universitate universitate) throws SQLException {
        String sql = "UPDATE Universitati SET nume = ?, adresa = ?, email = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, universitate.getNume_Universitate());
            preparedStatement.setString(2, universitate.getAdresa());
            preparedStatement.setString(3, universitate.getEmail_address());
            preparedStatement.setInt(4, universitate.getId_Universitate());
            preparedStatement.executeUpdate();
            AuditService.logAction("Updated University");
        }
    }


    public void delete(Universitate universitate) throws SQLException
    {
//        deleteAbonamenteByUniversitateId(universitate.getId_Universitate());
        String sql = "DELETE FROM Universitati WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, universitate.getId_Universitate());
            preparedStatement.executeUpdate();
            AuditService.logAction("Deleted University");
        }
    }

    public Universitate getById(int universitateId) throws SQLException {
        Universitate universitate = null;
        String sql = "SELECT * FROM Universitati WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, universitateId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    universitate = new Universitate();
                    universitate.setId_Universitate(resultSet.getInt("id"));
                    universitate.setNume_Universitate(resultSet.getString("nume"));
                    universitate.setAdresa(resultSet.getString("adresa"));
                    universitate.setEmail_address(resultSet.getString("email"));
                }
            }
        }
        return universitate;
    }

    public List<Universitate> getUniversitates()
    {
        List<Universitate> universitates = new ArrayList<>();
        String sql = "SELECT * FROM Universitati";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nume = resultSet.getString("nume");
                String adresa = resultSet.getString("adresa");
                String email = resultSet.getString("email");
                Universitate universitate = new Universitate(id,nume, adresa, email);
                universitates.add(universitate);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return universitates;
    }

    public Universitate read(String name) {
        String sql = "SELECT * FROM Universitati WHERE nume = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String nume = resultSet.getString("nume");
                    String adresa = resultSet.getString("adresa");
                    String email = resultSet.getString("email");
                    AuditService.logAction("Searched University");
                    return new Universitate(id,nume, adresa, email);

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
