package dao;
import model.User;

import java.util.HashSet;

public class UserDAO
{
    private HashSet<User> users;
    private static UserDAO instance;
    private UserDAO()
    {
        users = new HashSet<>();
    }
    public static UserDAO getInstance()
    {
        if(instance == null)
        {
            instance = new UserDAO();
        }
        return instance;
    }
    public void create(User user)
    {
        users.add(user);
    }
    public void delete(User user)
    {
        if(!users.isEmpty())
        {
            users.remove(user);
        }
    }
    public User read(String email)
    {
        if(!users.isEmpty())
        {
            for(User u : users)
            {
                if(u.getEmail_address().equals(email))
                {
                    return u;
                }
            }
        }
        return null;
    }
    public HashSet<User> getUsers()
    {
        return this.users;
    }
}
