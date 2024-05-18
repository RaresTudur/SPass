package utils;

import dao.InitializeDataDao;
import daoservice.AbonamentMetrorexDAOService;
import daoservice.FacultyDAOService;
import daoservice.UniversityDAOService;
import daoservice.UserDAOService;
import model.*;
import security.PasswordHash;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class InitializeDataService
{
    private InitializeDataDao initializeDataDao;
    private UniversityDAOService universityDAOService;
    private FacultyDAOService facultyDAOService;
    private UserDAOService userDAOService;
    private AbonamentMetrorexDAOService abonamentMetrorexDAO;
    public InitializeDataService() throws SQLException
    {
        this.facultyDAOService = new FacultyDAOService();
        this.universityDAOService = new UniversityDAOService();
        this.userDAOService = new UserDAOService();
        this.abonamentMetrorexDAO = new AbonamentMetrorexDAOService();
        this.initializeDataDao = InitializeDataDao.getInstance();
    }
    public void addUser() throws SQLException, NoSuchAlgorithmException
    {
        if(!initializeDataDao.existaInregistrari("Studenti"))
        {
            Facultate fac_dreptID = universityDAOService.getFacultyByName("Facultatea de Drept","Universitatea din Bucuresti");

            Student student_Test = new Student(1,"Tudur","Rares",210,"rares@gmail.com",fac_dreptID.getId_facultate(), PasswordHash.generateHashedPassword("parola"), fac_dreptID.getId_Universitate());
            userDAOService.addUser(student_Test);
            addAbonament(student_Test);
        }
        if(!initializeDataDao.existaInregistrari("Admini"))
        {
            Admin test_admin = new Admin(1,"admin@gmail.com",PasswordHash.generateHashedPassword("adminpass"),"Admin","Mihai");
            userDAOService.addUser(test_admin);
        }
    }
    public void adaugaUniversitatiSiFacultati() throws SQLException
    {
        if(!initializeDataDao.existaInregistrari("Universitati"))
        {
            Universitate universitate1 = new Universitate("Universitatea din Bucuresti", "Adresa Universitatea din Bucuresti", "universitate1@universitate.ro");
            universityDAOService.addUniversity(universitate1);

            Universitate universitate2 = new Universitate("Universitatea Politehnica din Bucuresti", "Adresa Universitatea Politehnica din Bucuresti", "universitate2@universitate.ro");
            universityDAOService.addUniversity(universitate2);

            Universitate universitate3 = new Universitate("Universitatea de Medicina si Farmacie Carol Davila", "Adresa Universitatea de Medicina si Farmacie Carol Davila", "universitate3@universitate.ro");
            universityDAOService.addUniversity(universitate3);

            Facultate facultate1 = new Facultate("Facultatea de Matematica si Informatica", "Adresa facultatii 1", "facultate1@universitate.ro", universitate1.getId_Universitate());
            facultyDAOService.addFaculty(facultate1,universitate1);

            Facultate facultate2 = new Facultate("Facultatea de Drept", "Adresa facultatii 2", "facultate2@universitate.ro", universitate1.getId_Universitate());
            facultyDAOService.addFaculty(facultate2,universitate1);

            Facultate facultate3 = new Facultate("Facultatea de Automatica si Calculatoare", "Adresa facultatii 3", "facultate3@universitate.ro", universitate2.getId_Universitate());
            facultyDAOService.addFaculty(facultate3,universitate2);

            Facultate facultate4 = new Facultate("Facultatea de Inginerie Electrica", "Adresa facultatii 4", "facultate4@universitate.ro", universitate2.getId_Universitate());
            facultyDAOService.addFaculty(facultate4,universitate2);

            Facultate facultate5 = new Facultate("Facultatea de Medicina Generala", "Adresa facultatii 5", "facultate5@universitate.ro", universitate3.getId_Universitate());
            facultyDAOService.addFaculty(facultate5,universitate3);

            Facultate facultate6 = new Facultate("Facultatea de Farmacie", "Adresa facultatii 6", "facultate6@universitate.ro", universitate3.getId_Universitate());
            facultyDAOService.addFaculty(facultate6,universitate3);
        }
    }
    public void addAbonament(Student student_Test) throws SQLException
    {
        Abonament_Metrorex abonamentMetrorex = new Abonament_Metrorex(student_Test.getId(),true,student_Test.getUniversitateID(),student_Test.getFacultateID());
        abonamentMetrorex.setExpirat(true);
        abonamentMetrorexDAO.addAbonament(abonamentMetrorex);
    }

}
