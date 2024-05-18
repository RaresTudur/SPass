package dao;
import daoservice.DatabaseConnection;
import model.Student;
import service.AuditService;

import java.sql.*;
import java.util.HashSet;

public class StudentDAO {
    private static StudentDAO instance;
    private final Connection connection = DatabaseConnection.getConnection();

    private StudentDAO()  throws  SQLException{
    }

    public static synchronized StudentDAO getInstance() throws SQLException
    {
        if (instance == null)
        {
            instance = new StudentDAO();
        }
        return instance;
    }

    public void create(Student student) {
        String sql = "INSERT INTO Studenti (id, email, student_number, nume, prenume, grupa, id_universitate, id_facultate, id_card) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, student.getId());
            preparedStatement.setString(2, student.getEmail_address());
            preparedStatement.setInt(3, student.getStudent_number());
            preparedStatement.setString(4, student.getNume());
            preparedStatement.setString(5, student.getPrenume());
            preparedStatement.setInt(6, student.getGrupa());
            preparedStatement.setInt(7, student.getUniversitateID());
            preparedStatement.setInt(8, student.getFacultateID());
            if(student.getCard() == 0)
            {
                preparedStatement.setNull(9,0);
            }
            else
            {
                preparedStatement.setInt(9, student.getCard());
            }


            preparedStatement.executeUpdate();
            AuditService.logAction("Created Student");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Student read(int studentId) throws SQLException
    {
        String sql = "SELECT s.*, f.nume AS nume_facultate, u.nume AS nume_universitate " +
                "FROM Studenti s " +
                "JOIN Facultati f ON s.id_facultate = f.id " +
                "JOIN Universitati u ON s.id_universitate = u.id " +
                "WHERE s.id = ?";
        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, studentId);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Student student = new Student();
                student.setId(resultSet.getInt("id"));
                student.setEmail_address(resultSet.getString("email"));
                student.setStudent_number(resultSet.getInt("student_number"));
                student.setNume(resultSet.getString("nume"));
                student.setPrenume(resultSet.getString("prenume"));
                student.setGrupa(resultSet.getInt("grupa"));
                student.setUniversitateID(resultSet.getInt("id_universitate"));
                student.setFacultateID(resultSet.getInt("id_facultate"));
                student.setCard(resultSet.getInt("id_card"));
                student.setNume_facultate(resultSet.getString("nume_facultate"));
                student.setNume_universitate(resultSet.getString("nume_universitate"));
                AuditService.logAction("Searched Student");
                return student;
            }
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
        }
        return null;
    }

    public HashSet<Student> getAllStudents()
    {
        HashSet<Student> students = new HashSet<>();
        String sql = "SELECT s.id, s.email, s.student_number, s.nume, s.prenume, s.grupa, s.id_universitate, s.id_facultate, s.id_card, f.nume AS nume_facultate, u.nume AS nume_universitate " +
                "FROM Studenti s " +
                "JOIN Facultati f ON s.id_facultate = f.id " +
                "JOIN Universitati u ON s.id_universitate = u.id";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql))
        {
            while (resultSet.next())
            {
                Student student = new Student();
                student.setId(resultSet.getInt("id"));
                student.setEmail_address(resultSet.getString("email"));
                student.setStudent_number(resultSet.getInt("student_number"));
                student.setNume(resultSet.getString("nume"));
                student.setPrenume(resultSet.getString("prenume"));
                student.setGrupa(resultSet.getInt("grupa"));
                student.setUniversitateID(resultSet.getInt("id_universitate"));
                student.setFacultateID(resultSet.getInt("id_facultate"));
                student.setCard(resultSet.getInt("id_card"));
                student.setNume_facultate(resultSet.getString("nume_facultate"));
                student.setNume_universitate(resultSet.getString("nume_universitate"));

                students.add(student);
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return students;
    }

    public void update(Student student) {
        String sql = "UPDATE Studenti SET id = ?, email = ?, student_number = ?, nume = ?, prenume = ?, grupa = ?, id_universitate = ?, id_facultate = ?, id_card = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, student.getId());
            preparedStatement.setString(2, student.getEmail_address());
            preparedStatement.setInt(3, student.getStudent_number());
            preparedStatement.setString(4, student.getNume());
            preparedStatement.setString(5, student.getPrenume());
            preparedStatement.setInt(6, student.getGrupa());
            preparedStatement.setInt(7, student.getUniversitateID());
            preparedStatement.setInt(8, student.getFacultateID());
            preparedStatement.setInt(9, student.getCard());
            preparedStatement.setInt(10, student.getId());

            preparedStatement.executeUpdate();
            AuditService.logAction("Updated Student");
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void delete(int studentId)
    {
        String sql = "DELETE FROM Studenti WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql))
        {
            preparedStatement.setInt(1, studentId);
            preparedStatement.executeUpdate();
            AuditService.logAction("Deleted Student");
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
