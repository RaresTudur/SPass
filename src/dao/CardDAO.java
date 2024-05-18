package dao;

import daoservice.DatabaseConnection;
import model.CreditCard;
import model.DebitCard;
import service.AuditService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CardDAO {
    private static CardDAO instance;
    private Connection connection = DatabaseConnection.getConnection();

    private CardDAO() throws SQLException {
    }

    public static CardDAO getInstance() throws SQLException {
        if (instance == null) {
            instance = new CardDAO();
        }
        return instance;
    }

    public void createDebitCard(DebitCard debitCard) throws SQLException {
        String sql = "INSERT INTO Carduri (numar_card, data_expirare, detinator, cvv, user_id,typeofCard) VALUES (?, ?, ?, ?, ?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, Long.toString(debitCard.getCardNumber()));
            preparedStatement.setDate(2, java.sql.Date.valueOf(debitCard.getDataExpirare()));
            preparedStatement.setString(3, debitCard.getDetinator());
            preparedStatement.setInt(4, debitCard.getCVV());
            preparedStatement.setInt(5, debitCard.getUser_id());
            preparedStatement.setString(6,"Credit");
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int cardId = generatedKeys.getInt(1);
                debitCard.setCardID(cardId);
                createDebitCardEntry(debitCard, cardId);
            }
            AuditService.logAction("Created DebitCard in Carduri");
        }
    }

    private void createDebitCardEntry(DebitCard debitCard, int cardId) throws SQLException {
        String sql = "INSERT INTO DebitCard (id_card, sold_limit, ammount_used) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, cardId);
            preparedStatement.setDouble(2, debitCard.getSoldLimit());
            preparedStatement.setDouble(3, debitCard.getAmmountused());
            preparedStatement.executeUpdate();
            AuditService.logAction("Created DebitCard in DebitCarduri");
        }
    }

    public void createCreditCard(CreditCard creditCard) throws SQLException {
        String sql = "INSERT INTO Carduri (numar_card, data_expirare, detinator, cvv, user_id,typeofCard) VALUES (?, ?, ?, ?, ?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, Long.toString(creditCard.getCardNumber()));
            preparedStatement.setDate(2, java.sql.Date.valueOf(creditCard.getDataExpirare()));
            preparedStatement.setString(3, creditCard.getDetinator());
            preparedStatement.setInt(4, creditCard.getCVV());
            preparedStatement.setInt(5, creditCard.getUser_id());
            preparedStatement.setString(6,"Credit");
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int cardId = generatedKeys.getInt(1);
                creditCard.setCardID(cardId);
                createCreditCardEntry(creditCard, cardId);
            }
            AuditService.logAction("Created CreditCard in Carduri");
        }
    }

    private void createCreditCardEntry(CreditCard creditCard, int cardId) throws SQLException {
        String sql = "INSERT INTO CreditCard (id_card, limita_credit, datorie_curenta) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, cardId);
            preparedStatement.setDouble(2, creditCard.getLimitaCredit());
            preparedStatement.setDouble(3, creditCard.getDatorieCurenta());
            preparedStatement.executeUpdate();
            AuditService.logAction("Created CreditCard in CreditCarduri");
        }
    }


    public List<DebitCard> getAllDebitCards() throws SQLException {
        List<DebitCard> debitCards = new ArrayList<>();
        String sql = "SELECT c.id_card, c.numar_card, c.data_expirare, c.detinator, c.cvv, c.user_id, dc.sold_limit, dc.ammount_used " +
                "FROM Carduri c " +
                "JOIN DebitCard dc ON c.id_card = dc.id_card";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                DebitCard debitCard = new DebitCard();
                debitCard.setCardID(resultSet.getInt("id_card"));
                debitCard.setCardNumber(Long.parseLong(resultSet.getString("numar_card")));
                debitCard.setDataExpirare(resultSet.getDate("data_expirare").toLocalDate());
                debitCard.setDetinator(resultSet.getString("detinator"));
                debitCard.setCVV(resultSet.getInt("cvv"));
                debitCard.setUser_id(resultSet.getInt("user_id"));
                debitCard.setSoldLimit(resultSet.getDouble("sold_limit"));
                debitCard.setAmmountused(resultSet.getDouble("ammount_used"));
                debitCards.add(debitCard);
            }
        }
        return debitCards;
    }

    public List<CreditCard> getAllCreditCards() throws SQLException {
        List<CreditCard> creditCards = new ArrayList<>();
        String sql = "SELECT c.id_card, c.numar_card, c.data_expirare, c.detinator, c.cvv, c.user_id, cc.limita_credit, cc.datorie_curenta " +
                "FROM Carduri c " +
                "JOIN CreditCard cc ON c.id_card = cc.id_card";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                CreditCard creditCard = new CreditCard();
                creditCard.setCardID(resultSet.getInt("id_card"));
                creditCard.setCardNumber(Long.parseLong(resultSet.getString("numar_card")));
                creditCard.setDataExpirare(resultSet.getDate("data_expirare").toLocalDate());
                creditCard.setDetinator(resultSet.getString("detinator"));
                creditCard.setCVV(resultSet.getInt("cvv"));
                creditCard.setUser_id(resultSet.getInt("user_id"));
                creditCard.setLimitaCredit(resultSet.getDouble("limita_credit"));
                creditCard.setDatorieCurenta(resultSet.getDouble("datorie_curenta"));
                creditCards.add(creditCard);
            }
        }
        return creditCards;
    }
    public DebitCard readDebitCard(int userid) throws SQLException {
        String sql = "SELECT c.id_card, c.numar_card, c.data_expirare, c.detinator, c.cvv, c.user_id, dc.sold_limit, dc.ammount_used " +
                "FROM Carduri c " +
                "JOIN DebitCard dc ON c.id_card = dc.id_card " +
                "WHERE c.user_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, userid);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    DebitCard debitCard = new DebitCard();
                    debitCard.setCardID(resultSet.getInt("id_card"));
                    debitCard.setCardNumber(Long.parseLong(resultSet.getString("numar_card")));
                    debitCard.setDataExpirare(resultSet.getDate("data_expirare").toLocalDate());
                    debitCard.setDetinator(resultSet.getString("detinator"));
                    debitCard.setCVV(resultSet.getInt("cvv"));
                    debitCard.setUser_id(resultSet.getInt("user_id"));
                    debitCard.setSoldLimit(resultSet.getDouble("sold_limit"));
                    debitCard.setAmmountused(resultSet.getDouble("ammount_used"));
                    AuditService.logAction("Searched DebitCard");
                    return debitCard;
                }
            }
        }
        return null;
    }

    public CreditCard readCreditCard(int userid) throws SQLException {
        String sql = "SELECT c.id_card, c.numar_card, c.data_expirare, c.detinator, c.cvv, c.user_id, cc.limita_credit, cc.datorie_curenta " +
                "FROM Carduri c " +
                "JOIN CreditCard cc ON c.id_card = cc.id_card " +
                "WHERE c.user_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, userid);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    CreditCard creditCard = new CreditCard();
                    creditCard.setCardID(resultSet.getInt("id_card"));
                    creditCard.setCardNumber(Long.parseLong(resultSet.getString("numar_card")));
                    creditCard.setDataExpirare(resultSet.getDate("data_expirare").toLocalDate());
                    creditCard.setDetinator(resultSet.getString("detinator"));
                    creditCard.setCVV(resultSet.getInt("cvv"));
                    creditCard.setUser_id(resultSet.getInt("user_id"));
                    creditCard.setLimitaCredit(resultSet.getDouble("limita_credit"));
                    creditCard.setDatorieCurenta(resultSet.getDouble("datorie_curenta"));
                    AuditService.logAction("Searched CreditCard");
                    return creditCard;
                }
            }
        }
        return null;
    }

    public void updateDebitCard(DebitCard debitCard) throws SQLException {
        String sql = "UPDATE Carduri c " +
                "JOIN DebitCard dc ON c.id_card = dc.id_card " +
                "SET c.numar_card = ?, c.data_expirare = ?, c.detinator = ?, c.cvv = ?, c.user_id = ?, dc.sold_limit = ?, dc.ammount_used = ? " +
                "WHERE c.id_card = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, Long.toString(debitCard.getCardNumber()));
            preparedStatement.setDate(2, java.sql.Date.valueOf(debitCard.getDataExpirare()));
            preparedStatement.setString(3, debitCard.getDetinator());
            preparedStatement.setInt(4, debitCard.getCVV());
            preparedStatement.setInt(5, debitCard.getUser_id());
            preparedStatement.setDouble(6, debitCard.getSoldLimit());
            preparedStatement.setDouble(7, debitCard.getAmmountused());
            preparedStatement.setInt(8, debitCard.getCardID());
            AuditService.logAction("Updated DebitCard");
            preparedStatement.executeUpdate();
        }
    }

    public void updateCreditCard(CreditCard creditCard) throws SQLException
    {
        String sql = "UPDATE Carduri c " +
                "JOIN CreditCard cc ON c.id_card = cc.id_card " +
                "SET c.numar_card = ?, c.data_expirare = ?, c.detinator = ?, c.cvv = ?, c.user_id = ?, cc.limita_credit = ?, cc.datorie_curenta = ? " +
                "WHERE c.id_card = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, Long.toString(creditCard.getCardNumber()));
            preparedStatement.setDate(2, java.sql.Date.valueOf(creditCard.getDataExpirare()));
            preparedStatement.setString(3, creditCard.getDetinator());
            preparedStatement.setInt(4, creditCard.getCVV());
            preparedStatement.setInt(5, creditCard.getUser_id());
            preparedStatement.setDouble(6, creditCard.getLimitaCredit());
            preparedStatement.setDouble(7, creditCard.getDatorieCurenta());
            preparedStatement.setInt(8, creditCard.getCardID());
            preparedStatement.executeUpdate();
            AuditService.logAction("Updated CreditCard");
        }
    }

    public void deleteDebitCard(int cardId) throws SQLException
    {    String sqlUpdateStudents = "UPDATE Studenti SET id_card = NULL WHERE id_card = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlUpdateStudents)) {
            preparedStatement.setInt(1, cardId);
            preparedStatement.executeUpdate();
            AuditService.logAction("Deleted DebitCard from a student");

        }
        String sql = "DELETE c, dc " +
                "FROM Carduri c " +
                "JOIN DebitCard dc ON c.id_card = dc.id_card " +
                "WHERE c.id_card = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, cardId);
            preparedStatement.executeUpdate();
            AuditService.logAction("Deleted DebitCard");
        }
    }

    public void deleteCreditCard(int cardId) throws SQLException
    {
        String sqlUpdateStudents = "UPDATE Studenti SET id_card = NULL WHERE id_card = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlUpdateStudents)) {
            preparedStatement.setInt(1, cardId);
            preparedStatement.executeUpdate();
            AuditService.logAction("Deleted CreditCard from a student");
        }
        String sql = "DELETE c, cc " +
                "FROM Carduri c " +
                "JOIN CreditCard cc ON c.id_card = cc.id_card " +
                "WHERE c.id_card = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, cardId);
            preparedStatement.executeUpdate();
            AuditService.logAction("Deleted CreditCard");
        }
    }
}