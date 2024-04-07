package utils;

import DAO.AbonamentMetrorexDAO;
import DAO.FacultyDAO;
import daoservice.AbonamentMetrorexDAOService;
import daoservice.FacultyDAOService;
import daoservice.UniversityDAOService;
import daoservice.UserDAOService;
import model.*;

public class InitializeDataService
{
    private UniversityDAOService universityDAOService;
    private FacultyDAOService facultyDAOService;
    private UserDAOService userDAOService;
    private AbonamentMetrorexDAOService abonamentMetrorexDAO;
    public InitializeDataService()
    {
        this.facultyDAOService = new FacultyDAOService();
        this.universityDAOService = new UniversityDAOService();
        this.userDAOService = new UserDAOService();
        this.abonamentMetrorexDAO = new AbonamentMetrorexDAOService();
    }
    public void addUser()
    {
        //Exemplu de useri
        Student student_Test = new Student(1,"Tudur","Rares",210,"rares@gmail.com","Facultatea de Drept","parola","Universitatea din Bucuresti");
        userDAOService.addUser(student_Test);
        addAbonament(student_Test);
        Admin test_admin = new Admin("admin@gmail.com","adminpass","Admin","Mihai");
        userDAOService.addUser(test_admin);
    }
    public void adaugaUniversitatiSiFacultati()
    {
        Universitate universitate1 = new Universitate("Universitatea din Bucuresti", "Adresa Universitatea din Bucuresti", "universitate1@universitate.ro");
        universityDAOService.addUniversity(universitate1);

        Universitate universitate2 = new Universitate("Universitatea Politehnica din Bucuresti", "Adresa Universitatea Politehnica din Bucuresti", "universitate2@universitate.ro");
        universityDAOService.addUniversity(universitate2);

        Universitate universitate3 = new Universitate("Universitatea de Medicina si Farmacie Carol Davila", "Adresa Universitatea de Medicina si Farmacie Carol Davila", "universitate3@universitate.ro");
        universityDAOService.addUniversity(universitate3);

        Facultate facultate1 = new Facultate("Facultatea de Matematica si Informatica", "Adresa facultatii 1", "facultate1@universitate.ro", universitate1.getId_Universitate());
        facultyDAOService.addFaculty(facultate1);
        universityDAOService.addFacultytoUniversity(universitate1,facultate1);

        Facultate facultate2 = new Facultate("Facultatea de Drept", "Adresa facultatii 2", "facultate2@universitate.ro", universitate1.getId_Universitate());
        facultyDAOService.addFaculty(facultate2);
        universityDAOService.addFacultytoUniversity(universitate1,facultate2);

        Facultate facultate3 = new Facultate("Facultatea de Automatica si Calculatoare", "Adresa facultatii 3", "facultate3@universitate.ro", universitate2.getId_Universitate());
        facultyDAOService.addFaculty(facultate3);
        universityDAOService.addFacultytoUniversity(universitate2,facultate3);

        Facultate facultate4 = new Facultate("Facultatea de Inginerie Electrica", "Adresa facultatii 4", "facultate4@universitate.ro", universitate2.getId_Universitate());
        facultyDAOService.addFaculty(facultate4);
        universityDAOService.addFacultytoUniversity(universitate2,facultate4);

        Facultate facultate5 = new Facultate("Facultatea de Medicina Generala", "Adresa facultatii 5", "facultate5@universitate.ro", universitate3.getId_Universitate());
        facultyDAOService.addFaculty(facultate5);
        universityDAOService.addFacultytoUniversity(universitate3,facultate5);

        Facultate facultate6 = new Facultate("Facultatea de Farmacie", "Adresa facultatii 6", "facultate6@universitate.ro", universitate3.getId_Universitate());
        facultyDAOService.addFaculty(facultate6);
        universityDAOService.addFacultytoUniversity(universitate3,facultate6);
    }
    public void addAbonament(Student student_Test)
    {
        Abonament_Metrorex abonamentMetrorex = new Abonament_Metrorex(student_Test,true,student_Test.getUniversitate(),student_Test.getFacultate());
        abonamentMetrorex.setExpirat(true);
        abonamentMetrorexDAO.addAbonament(abonamentMetrorex);
    }

}
