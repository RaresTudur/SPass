package dao;

import daoservice.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InitializeDataDao
{
    private static InitializeDataDao instance;
    private final Connection connection = DatabaseConnection.getConnection();

    private InitializeDataDao()  throws  SQLException{
    }

    public static synchronized InitializeDataDao getInstance() throws SQLException
    {
        if (instance == null)
        {
            instance = new InitializeDataDao();
        }
        return instance;
    }
    public boolean existaInregistrari(String tabel) throws SQLException
    {
        String query = "SELECT COUNT(*) FROM " + tabel;
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        int count = resultSet.getInt(1);
        return count > 0;
    }
}
