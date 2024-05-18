package dao;

import daoservice.DatabaseConnection;
import model.Facultate;
import model.Universitate;
import service.AuditService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FacultyDAO {
    private static FacultyDAO instance;
    private Connection connection = DatabaseConnection.getConnection();

    private FacultyDAO() throws SQLException
    {
    }

    public static FacultyDAO getInstance() throws SQLException
    {
        if(instance == null)
        {
            instance = new FacultyDAO();
        }
        return instance;
    }

    public void create(Facultate facultate, Universitate universitate) throws SQLException {
        String sql = "INSERT INTO Facultati (nume, adresa, email, universitate_id) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, facultate.getNume_facultate());
            preparedStatement.setString(2, facultate.getAdresa());
            preparedStatement.setString(3, facultate.getEmail_address());
            preparedStatement.setInt(4, universitate.getId_Universitate());
            preparedStatement.executeUpdate();
            AuditService.logAction("Created faculty");
        }
    }

    public void update(Facultate facultate) throws SQLException {
        String sql = "UPDATE Facultati SET nume = ?, adresa = ?, email = ?, universitate_id = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, facultate.getNume_facultate());
            preparedStatement.setString(2, facultate.getAdresa());
            preparedStatement.setString(3, facultate.getEmail_address());
            preparedStatement.setInt(4, facultate.getId_Universitate());
            preparedStatement.setInt(5, facultate.getId_facultate());
            preparedStatement.executeUpdate();
            AuditService.logAction("Updated faculty");

        }
    }

    public void delete(int facultateId) throws SQLException {
        String sql = "DELETE FROM Facultati WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, facultateId);
            preparedStatement.executeUpdate();
            AuditService.logAction("Deleted faculty");

        }
    }

    public List<Facultate> getAll() throws SQLException {
        List<Facultate> facultati = new ArrayList<>();
        String sql = "SELECT * FROM Facultati";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Facultate facultate = new Facultate();
                facultate.setId_facultate(resultSet.getInt("id"));
                facultate.setNume_facultate(resultSet.getString("nume"));
                facultate.setAdresa(resultSet.getString("adresa"));
                facultate.setEmail_address(resultSet.getString("email"));
                facultate.setId_Universitate(resultSet.getInt("universitate_id"));
                facultati.add(facultate);
            }
        }
        return facultati;
    }

    public Facultate read(String name) throws SQLException {
        String sql = "SELECT * FROM Facultati WHERE nume = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql))
        {
            statement.setString(1, name);
            try (ResultSet resultSet = statement.executeQuery())
            {
                if (resultSet.next())
                {
                    String nume = resultSet.getString("nume");
                    String adresa = resultSet.getString("adresa");
                    String email = resultSet.getString("email");
                    int universitateId = resultSet.getInt("universitate_id");
                    AuditService.logAction("Searched faculty");
                    return new Facultate(nume, adresa, email, universitateId);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
