import java.util.Scanner;

import Menu.UserMenu;
import model.User;
import service.*;
import utils.InitializeDataService;

public class Application
{
    private static User user_curent;
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        UserService dtb = new UserService();
        AuthentificationService authentificationService = new AuthentificationService();
        PassService passService = new PassService();
        PayService payService = new PayService();
        InitializeDataService init = new InitializeDataService();
        init.adaugaUniversitatiSiFacultati();
        init.addUser();
        while(true)
        {
            menu();
            int cmd = in.nextInt();
            in.nextLine();
            switch (cmd)
            {
                case 1:
                {
                    System.out.println("Email:");
                    String email = in.nextLine();
                    System.out.println("Password:");
                    String parola = in.nextLine();
                    user_curent = authentificationService.login(email,parola);
                    if(user_curent != null)
                    {
                        System.out.println("You are logged in");
                        UserMenu.main(authentificationService,user_curent,in,passService,payService,dtb);
                    }
                    break;
                }
                case 2:
                {
                    authentificationService.register(in);
                }
            }
        }
    }
    public static void menu()
    {
        System.out.println("Available commands: ");
        System.out.println("1.Login");
        System.out.println("2.Register");
    }

}
