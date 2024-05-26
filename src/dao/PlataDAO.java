package dao;

import daoservice.DatabaseConnection;
import model.Plata;
import service.AuditService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlataDAO {
    private final Connection connection = DatabaseConnection.getConnection();
    private static PlataDAO instance;

    private PlataDAO() throws SQLException {
    }
    public static PlataDAO getInstance() throws SQLException
    {
        if(instance == null)
        {
            instance = new PlataDAO();
        }
        return instance;
    }

    public void create(Plata plata) throws SQLException {
        String sql = "INSERT INTO Plati (id_utilizator, id_abonament, tip_abonament, suma) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, plata.getId_Student());
            preparedStatement.setInt(2, plata.getId_Abonament());
            preparedStatement.setString(3, plata.getTip_Abonament());
            preparedStatement.setDouble(4, plata.getValue());
            preparedStatement.executeUpdate();
            AuditService.logAction("Created pay");
        }
    }

    public List<Plata> getAll() throws SQLException {
        List<Plata> platiList = new ArrayList<>();
        String sql = "SELECT * FROM Plati";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Plata plata = new Plata();
                    plata.setId_plata(resultSet.getInt("id_plata"));
                    plata.setId_Student(resultSet.getInt("id_utilizator"));
                    plata.setId_Abonament(resultSet.getInt("id_abonament"));
                    plata.setTip_Abonament(resultSet.getString("tip_abonament"));
                    plata.setValue(resultSet.getDouble("suma"));
                    platiList.add(plata);
                }
            }
        }
        return platiList;
    }

    public Plata read(int payId) throws SQLException
    {
        String sql = "SELECT * FROM Plati WHERE id_plata = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql))
        {
            statement.setInt(1, payId);
            try (ResultSet resultSet = statement.executeQuery())
            {
                if (resultSet.next())
                {
                    int idPlata = resultSet.getInt("id_plata");
                    int idUtilizator = resultSet.getInt("id_utilizator");
                    int idAbonament = resultSet.getInt("id_abonament");
                    String tipAbonament = resultSet.getString("tip_abonament");
                    double suma = resultSet.getDouble("suma");
                    AuditService.logAction("Searched pay");
                    return new Plata(idUtilizator, idAbonament, tipAbonament, suma);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void delete(Plata pay) throws SQLException
    {
        String sql = "DELETE FROM Plati WHERE id_plata = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql))
        {
            statement.setInt(1, pay.getId_plata());
            statement.executeUpdate();
            AuditService.logAction("Deleted pay");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
