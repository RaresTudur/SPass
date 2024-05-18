package dao;

import daoservice.DatabaseConnection;
import model.Abonament_Metrorex;
import model.Abonament_Metrorex;
import service.AuditService;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class AbonamentMetrorexDAO
{
    private static AbonamentMetrorexDAO instance;
    private Connection connection = DatabaseConnection.getConnection();
    private AbonamentMetrorexDAO() throws SQLException {}
    public static AbonamentMetrorexDAO getInstance() throws SQLException
    {
        if(instance == null)
        {
            instance = new AbonamentMetrorexDAO();
        }
        return instance;
    }
    public void create(Abonament_Metrorex abonament_Metrorex) throws SQLException
    {
        String sql = "INSERT INTO testprojectpao.Abonamente(student_id, plata_recurenta, universitate_id, facultate_id, data_inceput, expirat, tip_abonament) VALUES (?, ?, ?, ?, ?, ?,?);";

        try(PreparedStatement statement = connection.prepareStatement(sql);)
        {
            statement.setInt(1, abonament_Metrorex.getStudentId());
            statement.setBoolean(2, abonament_Metrorex.isPlataRecurenta());
            statement.setInt(3, abonament_Metrorex.getId_universitate());
            statement.setInt(4, abonament_Metrorex.getid_facultate());
            statement.setDate(5, Date.valueOf(abonament_Metrorex.getData_inceput()));
            statement.setBoolean(6, abonament_Metrorex.isExpirat());
            statement.setString(7, "Metrorex");
            statement.executeUpdate();
            AuditService.logAction("Created Metrorex pass");
        }
    }

    public Abonament_Metrorex read(int student_number) throws SQLException
    {
        String sql = "SELECT * FROM testprojectpao.Abonamente a WHERE a.student_id = ? and upper(a.tip_abonament) = 'METROREX'";
        ResultSet resultSet = null;
        try(PreparedStatement statement = connection.prepareStatement(sql);)
        {
            statement.setInt(1, student_number);
            resultSet = statement.executeQuery();

            while (resultSet.next())
            {
                Abonament_Metrorex abonament_metrorex = new Abonament_Metrorex();
                abonament_metrorex.setId_abonament(resultSet.getInt("id"));
                abonament_metrorex.setStudentId(resultSet.getInt("student_id"));
                abonament_metrorex.setPlata_recurenta(resultSet.getBoolean("plata_recurenta"));
                abonament_metrorex.setId_facultate(resultSet.getInt("universitate_id"));
                abonament_metrorex.setId_universitate(resultSet.getInt("facultate_id"));
                abonament_metrorex.setData_inceput(resultSet.getDate("data_inceput").toLocalDate());
                abonament_metrorex.setExpirat(resultSet.getBoolean("expirat"));
                AuditService.logAction("Search Metrorex pass");
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
    public void delete(Abonament_Metrorex abonament_metrorex) throws SQLException
    {
        String sql = "DELETE FROM testprojectpao.Abonamente a WHERE a.id = ?";

        try(PreparedStatement statement = connection.prepareStatement(sql);)
        {
            statement.setInt(1, abonament_metrorex.getId_abonament());
            statement.executeUpdate();
            AuditService.logAction("Deleted a Metrorex pass");
        }
    }

    public void update(Abonament_Metrorex abonament) throws SQLException
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
            preparedStatement.setString(5, "METROREX");
            preparedStatement.setInt(6, abonament.getId_abonament());
            AuditService.logAction("Updated Metrorex pass");
            preparedStatement.executeUpdate();
        }
    }
}
