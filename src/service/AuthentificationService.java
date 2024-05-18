package service;

import daoservice.UserDAOService;
import model.Admin;
import model.User;
import security.PasswordHash;
import utils.Constants;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Scanner;

public class AuthentificationService
{
    private User currentUser;
    private final UserDAOService userDAOService;
    private final UserService service = new UserService();

    public AuthentificationService() throws SQLException
    {
        this.userDAOService = new UserDAOService();
    }
    public User login(String email, String parola) throws SQLException, NoSuchAlgorithmException
    {
        User user = userDAOService.getUserbyEmail(email);
        if (user != null) {
            String parolaStocata = user.getPassword();
            if( PasswordHash.verifyPassword(parola,parolaStocata) )
            {
                currentUser = user;
                return currentUser;
            }
        }
        return null;
    }
    public boolean isLoggedIn() {
        return currentUser != null;
    }

    public void register(Scanner in) throws SQLException, NoSuchAlgorithmException
    {
        System.out.println("Email:");
        String email = in.nextLine();
        System.out.println("Password:");
        String password = in.nextLine();
        String passwordHash = PasswordHash.generateHashedPassword(password);
        service.personInit(in, Constants.USER,email,passwordHash);
    }
    public void logout()
    {
        currentUser = null;
    }
}
