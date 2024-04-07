package service;

import model.Admin;
import model.Student;
import utils.Constants;

import java.util.HashSet;
import java.util.Scanner;

public class AdminService
{
    private  AuthentificationService authentificationService;
    private PassService passService;
    private PayService payService;
    private UserService userService;
    private UniversityService universityService;
    public AdminService()
    {
        this.authentificationService = new AuthentificationService();
        this.passService = new PassService();
        this.payService = new PayService();
        this.userService = new UserService();
        this.universityService = new UniversityService();
    }
    public void addStudent(Scanner in)
    {
        authentificationService.register(in);
    }
    public HashSet<Student> getAllStudents(String adminRole)
    {
        return userService.returnallStudents(adminRole);
    }
    public HashSet<Admin> getAllAdmins(String adminRole)
    {
        return userService.returnallAdmin(adminRole);
    }
    public void viewallAdmins(String adminRole)
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
    public void addAdmin(Scanner in)
    {
        System.out.println("Email:");
        String email = in.nextLine();
        System.out.println("Password:");
        String password = in.nextLine();
        userService.personInit(in, Constants.Admin,email,password);
    }
    public void viewStudentDetails(String adminRole,Scanner in)
    {
        System.out.println("Introdu emailul studentului cautat!");
        String email = in.nextLine();
        userService.viewStudent(adminRole,email);
    }
    public void viewAdminDetails(String adminRole,Scanner in)
    {
        System.out.println("Introdu emailul adminului cautat!");
        String email = in.nextLine();
        userService.viewAdmin(adminRole, email);
    }
    public void addUniversity(Scanner in)
    {
        universityService.create(in);
    }
    public void viewallUniversity()
    {
        universityService.viewAllUniversities();;
    }
}
