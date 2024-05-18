package daoservice;

import model.Admin;
import model.Student;
import model.User;
import dao.*;

import java.sql.SQLException;
import java.util.HashSet;

public class UserDAOService
{
    private UserDAO userDAO;
    private StudentDAO studentDAO;
    private AdminDAO adminDAO;

    public UserDAOService() throws SQLException
    {
        this.userDAO = UserDAO.getInstance();
        this.studentDAO = StudentDAO.getInstance();
        this.adminDAO = AdminDAO.getInstance();
    }

    public User getUserbyEmail(String email) throws SQLException
    {
        User user = userDAO.read(email);
        if(user == null)
        {
            return null;
        }
        if(user.get_Role().equalsIgnoreCase("student"))
        {
            Student s_user = studentDAO.read(user.getId());
            s_user.setPassword(user.getPassword());
            s_user.setRole(user.get_Role());
            user = s_user;
        }
        else if(user.get_Role().equalsIgnoreCase("admin"))
        {
            Admin admin = adminDAO.read(user.getId());
            admin.setEmail_address(user.getEmail_address());
            admin.setRole(user.get_Role());
            admin.setPassword(user.getPassword());
            user = admin;
        }
        return user;
    }
    public void addUser(User user) throws SQLException
    {
        if(user != null)
        {
            userDAO.create(user);
            if(user instanceof Student)
            {
                studentDAO.create((Student) user);
            } else if (user instanceof Admin)
            {
                adminDAO.create((Admin) user);
            }
        }
    }
    public void  updateUser(User user) throws SQLException
    {
        if(user != null)
        {
            userDAO.update(user);
            if(user instanceof Student)
            {
                studentDAO.update((Student) user);
            } else if (user instanceof Admin)
            {
                adminDAO.update((Admin) user);
            }
        }
    }
    public void removeUser(User user) throws SQLException
    {
        if(user != null)
        {
            if(user != null)
            {
                userDAO.update(user);
                if(user instanceof Student)
                {
                    studentDAO.delete(user.getId());
                } else if (user instanceof Admin)
                {
                    adminDAO.delete(user.getId());
                }
            }
            userDAO.delete(user.getEmail_address());
        }
    }
    public HashSet<User> getallUsers()
    {
        return userDAO.getUsers();
    }
    public HashSet<Student>getallStudents()
    {
        return studentDAO.getAllStudents();
    }
    public HashSet<Admin>getallAdmin() throws SQLException
    {
        return adminDAO.getAllAdmins();
    }
}
