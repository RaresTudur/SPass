package service;

import daoservice.UniversityDAOService;
import daoservice.UserDAOService;
import model.Admin;
import model.Student;
import model.User;
import utils.Constants;

import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class UserService
{
    private UserDAOService userDAOService;
    private UniversityDAOService universityDAOService;
    public UserService()
    {
        this.userDAOService = new UserDAOService();
        this.universityDAOService = new UniversityDAOService();
    }
    public void personInit(Scanner in, String typeOfUser, String email, String password)
    {
        if(typeOfUser.equals(Constants.USER) && userDAOService.getUserbyEmail(email) != null )
        {
            return;
        }
        if(typeOfUser.equals(Constants.Admin) && userDAOService.getUserbyEmail(email) != null )
        {
            return;
        }
        User user = new User(email,password);
        if(typeOfUser.equals(Constants.USER))
        {
            Student var_student = new Student(user);
            studentInit(in,var_student);
            user = var_student;
        }
        else if(typeOfUser.equals(Constants.Admin))
        {
            Admin var_admin = new Admin(user);
            adminInit(in,var_admin);
            user = var_admin;
        }
        userDAOService.addUser(user);
    }
    private void adminInit(Scanner in, Admin var_admin)
    {
        System.out.println("Introdu ce rol are acest admin");
        String adminRole = in.nextLine();
        var_admin.setAdminRole(adminRole);
    }
    public void studentInit(Scanner in, Student var_student)
    {
        System.out.println("Student number:");
        int var_std_num = in.nextInt();
        System.out.println("Nume:");
        String nume = in.nextLine();
        in.nextLine();
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

        var_student.setUniversitate(universitate);
        setFacultate(in,var_student,universitate);
        var_student.setStudent_number(var_std_num);
        var_student.setNume(nume);
        var_student.setPrenume(prenume);
        var_student.setGrupa(grupa);
    }
    public void setFacultate(Scanner in, Student var_student, String universitate)
    {
        System.out.println("Facultate: ");
        String facultate = in.nextLine();
        while (universityDAOService.getFacultyByName(facultate,universitate) == null)
        {
            System.out.println("Nu exista aceasta facultate\n");
            System.out.println("Incearca din nou");
            facultate = in.nextLine();
        }
        var_student.setFacultate(facultate);
    }

    public HashSet<Admin> returnallAdmin(String adminRole)
    {
        HashSet<User> users = returnallUsers(adminRole);
        if(users == null)
        {
            return null;
        }
        return users.stream().filter(var_user -> var_user instanceof Admin)
            .map(var_user -> (Admin) var_user)
                .collect(Collectors.toCollection(HashSet::new));
    }
    public HashSet<Student> returnallStudents(String adminRole)
    {
        HashSet<User> users = returnallUsers(adminRole);
        if(users == null)
        {
            return null;
        }
        return users.stream().filter(var_user -> var_user instanceof Student)
                .map(var_user -> (Student) var_user)
                .collect(Collectors.toCollection(HashSet::new));
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
    public void viewStudent(String adminRole, String email)
    {
        if(isAdmin(adminRole))
        {
            Student searhed_user = (Student) userDAOService.getUserbyEmail(email);
            if(searhed_user != null)
            {
                System.out.println(searhed_user);
            }
        }
    }
    public void viewAdmin(String adminRole, String email)
    {
        if(isAdmin(adminRole))
        {
            Admin searhed_user = (Admin) userDAOService.getUserbyEmail(email);
            if(searhed_user != null)
            {
                System.out.println(searhed_user);
            }
        }
    }
}
