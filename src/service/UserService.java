package service;

import daoservice.UniversityDAOService;
import daoservice.UserDAOService;
import model.Admin;
import model.Student;
import model.User;
import utils.Constants;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Scanner;

public class UserService
{
    private UserDAOService userDAOService;
    private UniversityDAOService universityDAOService;
    public UserService() throws SQLException
    {
        this.userDAOService = new UserDAOService();
        this.universityDAOService = new UniversityDAOService();
    }
    public void personInit(Scanner in, String typeOfUser, String email, String password) throws SQLException
    {
        if(typeOfUser.equals(Constants.USER) && userDAOService.getUserbyEmail(email) != null )
        {
            return;
        }
        if(typeOfUser.equals(Constants.Admin) && userDAOService.getUserbyEmail(email) != null )
        {
            return;
        }
        User user = new User(email,password,"");
        if(typeOfUser.equals(Constants.USER))
        {
            Student var_student = new Student(user);
            studentInit(in,var_student);
            user = var_student;
            user.setRole("Student");
        }
        else if(typeOfUser.equals(Constants.Admin))
        {
            Admin var_admin = new Admin(user);
            adminInit(in,var_admin);
            user = var_admin;
            user.setRole("Admin");
        }
        userDAOService.addUser(user);
    }
    private void adminInit(Scanner in, Admin var_admin)
    {
        System.out.println("Introdu numele acestui admin: ");
        String numeAdmin = in.nextLine();
        System.out.println("Introdu ce rol are acest admin");
        String adminRole = in.nextLine();
        var_admin.setAdminRole(adminRole);
        var_admin.setNume(numeAdmin);
    }
    public void studentInit(Scanner in, Student var_student) throws SQLException
    {
        System.out.println("Student number:");
        int var_std_num = in.nextInt();
        in.nextLine();
        System.out.println("Nume:");
        String nume = in.nextLine();
        System.out.println("Prenume:");
        String prenume = in.nextLine();
        System.out.println("Grupa:");
        int grupa = in.nextInt();
        in.nextLine();
        System.out.println("Universitate: ");
        String universitate = in.nextLine();
        while(universityDAOService.getUniversitybyName(universitate) == null)
        {
            System.out.println("Nu exista aceasta universitate\n");
            System.out.println("Incearca din nou");
            universitate = in.nextLine();
        }
        var_student.setUniversitateID(universityDAOService.getUniversitybyName(universitate).getId_Universitate());
        setFacultate(in,var_student,universitate);
        var_student.setStudent_number(var_std_num);
        var_student.setNume(nume);
        var_student.setPrenume(prenume);
        var_student.setGrupa(grupa);
    }
    public void setFacultate(Scanner in, Student var_student, String universitate) throws SQLException
    {
        System.out.println("Facultate: ");
        String facultate = in.nextLine();
        while (universityDAOService.getFacultyByName(facultate,universitate) == null)
        {
            System.out.println("Nu exista aceasta facultate\n");
            System.out.println("Incearca din nou");
            facultate = in.nextLine();
        }
        var_student.setFacultateID(universityDAOService.getFacultyByName(facultate,universitate).getId_facultate());
    }

    public HashSet<Admin> returnallAdmin(String adminRole) throws SQLException
    {
        return userDAOService.getallAdmin();
    }
    public HashSet<Student> returnallStudents(String adminRole)
    {
        return userDAOService.getallStudents();
    }
    private boolean isAdmin(String adminRole)
    {
        return adminRole.equals(Constants.Admin);
    }
    private HashSet<User> returnallUsers(String adminRole)
    {
        boolean accessRight = isAdmin(adminRole);
        if(accessRight)
        {
            return userDAOService.getallUsers();
        }
        return null;
    }
    public void viewStudent(String adminRole, String email) throws SQLException
    {
        if (isAdmin(adminRole))
        {
            User searchedUser = userDAOService.getUserbyEmail(email);
            if (searchedUser != null) {
                if (searchedUser instanceof Student)
                {
                    Student searchedStudent = (Student) searchedUser;
                    System.out.println(searchedStudent);
                }
                else
                {
                    System.out.println("Adresa de e-mail corespunde unui cont de administrator. Nu se pot afișa detalii pentru conturi de administrator.");
                }
            }
            else {
                System.out.println("Nu a fost găsit niciun utilizator cu adresa de e-mail: " + email);
            }
        }
        else {
            System.out.println("Permisiunile insuficiente pentru a vizualiza detaliile studentului.");
        }
    }
    public void viewAdmin(String adminRole, String email) throws SQLException {
        if (isAdmin(adminRole))
        {
            User searchedUser = userDAOService.getUserbyEmail(email);
            if (searchedUser != null)
            {
                if (searchedUser instanceof Admin)
                {
                    Admin searchedAdmin = (Admin) searchedUser;
                    System.out.println(searchedAdmin);
                }
                else
                {
                    System.out.println("Adresa de e-mail corespunde unui cont de student. Nu se pot afișa detalii pentru conturi de student.");
                }
            }
            else
            {
                System.out.println("Nu a fost găsit niciun utilizator cu adresa de e-mail: " + email);
            }
        }
        else
        {
            System.out.println("Permisiunile insuficiente pentru a vizualiza detaliile administratorului.");
        }
    }
    public void deleteUser(String email) throws SQLException
    {
        userDAOService.removeUser(userDAOService.getUserbyEmail(email));
    }
}
