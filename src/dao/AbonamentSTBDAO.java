package dao;

import daoservice.DatabaseConnection;
import model.Abonament_STB;
import model.Abonament_STB;
import service.AuditService;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class AbonamentSTBDAO
{
    private static AbonamentSTBDAO instance;
    private Connection connection = DatabaseConnection.getConnection();
    private AbonamentSTBDAO() throws SQLException {}
    public static AbonamentSTBDAO getInstance() throws SQLException
    {
        if(instance == null)
        {
            instance = new AbonamentSTBDAO();
        }
        return instance;
    }
    public void create(Abonament_STB abonamentSTB) throws SQLException
    {
        String sql = "INSERT INTO testprojectpao.Abonamente(student_id, plata_recurenta, universitate_id, facultate_id, data_inceput, expirat, tip_abonament) VALUES (?, ?, ?, ?, ?, ?,?);";

        try(PreparedStatement statement = connection.prepareStatement(sql);)
        {
            statement.setInt(1, abonamentSTB.getStudentId());
            statement.setBoolean(2, abonamentSTB.isPlataRecurenta());
            statement.setInt(3, abonamentSTB.getId_universitate());
            statement.setInt(4, abonamentSTB.getid_facultate());
            statement.setDate(5, Date.valueOf(abonamentSTB.getData_inceput()));
            statement.setBoolean(6, abonamentSTB.isExpirat());
            statement.setString(7,"STB");
            statement.executeUpdate();
            AuditService.logAction("Created STB pass");

        }
    }

    public Abonament_STB read(int studentID) throws SQLException
    {
        String sql = "SELECT * FROM testprojectpao.Abonamente a WHERE a.student_id = ? and upper(a.tip_abonament) = 'STB'";
        ResultSet resultSet = null;
        try(PreparedStatement statement = connection.prepareStatement(sql);)
        {
            statement.setInt(1, studentID);

            resultSet = statement.executeQuery();

            while (resultSet.next())
            {
                Abonament_STB abonament_metrorex = new Abonament_STB();
                abonament_metrorex.setId_abonament(resultSet.getInt("id"));
                abonament_metrorex.setStudentId(resultSet.getInt("student_id"));
                abonament_metrorex.setPlata_recurenta(resultSet.getBoolean("plata_recurenta"));
                abonament_metrorex.setId_facultate(resultSet.getInt("universitate_id"));
                abonament_metrorex.setId_universitate(resultSet.getInt("facultate_id"));
                abonament_metrorex.setData_inceput(resultSet.getDate("data_inceput").toLocalDate());
                abonament_metrorex.setExpirat(resultSet.getBoolean("expirat"));
                AuditService.logAction("Searched STB pass");
                return abonament_metrorex;
            }
        }
        finally
        {
            if(resultSet != null)
            {
                resultSet.close();
            }
        }
        return null;
    }
    public void delete(Abonament_STB abonament_metrorex) throws SQLException
    {
        String sql = "DELETE FROM testprojectpao.Abonamente a WHERE a.id = ?";

        try(PreparedStatement statement = connection.prepareStatement(sql);)
        {
            statement.setInt(1, abonament_metrorex.getId_abonament());
            statement.executeUpdate();
            AuditService.logAction("Deleted STB pass");
        }
    }

    public void update(Abonament_STB abonament) throws SQLException
    {
        String sql = "UPDATE Abonamente " +
                "SET student_id = ?, " +
                "plata_recurenta = ?, " +
                "data_inceput = ?, " +
                "expirat = ?, " +
                "tip_abonament = ? " +
                "WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql))
        {
            preparedStatement.setInt(1, abonament.getStudentId());
            preparedStatement.setBoolean(2, abonament.isPlataRecurenta());
            preparedStatement.setDate(3, Date.valueOf(abonament.getData_inceput()));
            preparedStatement.setBoolean(4, abonament.isExpirat());
            preparedStatement.setString(5, "STB");
            preparedStatement.setInt(6, abonament.getId_abonament());
            AuditService.logAction("Updated STB pass");
            preparedStatement.executeUpdate();
        }
    }
}
