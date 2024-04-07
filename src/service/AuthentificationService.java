package service;

import daoservice.UserDAOService;
import model.Admin;
import model.User;
import utils.Constants;

import java.util.Scanner;

public class AuthentificationService
{
    private User currentUser;
    private final UserDAOService userDAOService;
    private final UserService service = new UserService();

    public AuthentificationService()
    {
        this.userDAOService = new UserDAOService();
    }
    public User login(String email, String parola) {
        User user = userDAOService.getUserbyEmail(email);
        if (user != null) {
            String parolaStocata = user.getPassword();
            if(parolaStocata.equals(parola))
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

    public void register(Scanner in)
    {
        System.out.println("Email:");
        String email = in.nextLine();
        System.out.println("Password:");
        String password = in.nextLine();
        service.personInit(in, Constants.USER,email,password);
    }
    public void logout()
    {
        currentUser = null;
    }
}
