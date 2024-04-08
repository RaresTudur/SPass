package daoservice;

import model.User;
import dao.*;

import java.util.HashSet;

public class UserDAOService
{
    private UserDAO userDAO;

    public UserDAOService()
    {
        this.userDAO = UserDAO.getInstance();
    }

    public User getUserbyEmail(String email)
    {
        User user = userDAO.read(email);
        if(user != null)
        {
            return user;
        }
        return null;
    }
    public void addUser(User user)
    {
        if(user != null)
        {
            userDAO.create(user);
        }
    }
    public void removeUser(User user)
    {
        if(user != null)
        {
            userDAO.delete(user);
        }
    }
    public HashSet<User> getallUsers()
    {
        return userDAO.getUsers();
    }
}
