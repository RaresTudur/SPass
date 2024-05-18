package service;

import dao.UserCleaner;
import model.Admin;
import model.Student;
import utils.Constants;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Scanner;

public class AdminService
{
    private  AuthentificationService authentificationService;
    private PassService passService;
    private PayService payService;
    private UserService userService;
    private UniversityService universityService;
    public AdminService() throws SQLException
    {
        this.authentificationService = new AuthentificationService();
        this.passService = new PassService();
        this.payService = new PayService();
        this.userService = new UserService();
        this.universityService = new UniversityService();
    }
    public void addStudent(Scanner in) throws SQLException, NoSuchAlgorithmException
    {
        authentificationService.register(in);
    }
    public HashSet<Student> getAllStudents(String adminRole)
    {
        return userService.returnallStudents(adminRole);
    }
    public HashSet<Admin> getAllAdmins(String adminRole) throws SQLException
    {
        return userService.returnallAdmin(adminRole);
    }
    public void viewallAdmins(String adminRole) throws SQLException
    {
        HashSet<Admin> admins = getAllAdmins(adminRole);
        if(admins != null)
        {
            for(Admin admin : admins)
            {
                System.out.println(admin);
            }
        }
    }
    public void viewallStudents(String adminRole)
    {
        HashSet<Student> students = getAllStudents(adminRole);
        if(students != null)
        {
            for(Student student : students)
            {
                System.out.println(student);
            }
        }
    }
    public void addAdmin(Scanner in) throws SQLException
    {
        System.out.println("Email:");
        String email = in.nextLine();
        System.out.println("Password:");
        String password = in.nextLine();
        userService.personInit(in, Constants.Admin,email,password);
    }
    public void viewStudentDetails(String adminRole,Scanner in) throws SQLException
    {
        System.out.println("Introdu emailul studentului cautat!");
        String email = in.nextLine();
        userService.viewStudent(adminRole,email);
    }
    public void viewAdminDetails(String adminRole,Scanner in) throws SQLException
    {
        System.out.println("Introdu emailul adminului cautat!");
        String email = in.nextLine();
        userService.viewAdmin(adminRole, email);
    }
    public void addUniversity(Scanner in) throws SQLException
    {
        universityService.create(in);
    }
    public void viewallUniversity()
    {
        universityService.viewAllUniversities();;
    }
    public void deleteUser(String adminRole, Scanner in) throws SQLException
    {
        System.out.println("Introdu emailul utilizatorului pe care vrei sa il elimini");
        String email = in.nextLine();
       userService.deleteUser(email);
    }
    public void deleteUniversity(Scanner in) throws SQLException
    {
        System.out.println("Introdu numele universitatii pe care vrei sa o stergi");
        String nume = in.nextLine();
        universityService.removeUniversity(nume);
        UserCleaner.main();
    }
}
