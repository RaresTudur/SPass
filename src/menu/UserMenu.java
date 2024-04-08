package menu;

import model.Admin;
import model.Student;
import model.User;
import service.*;
import utils.ErrorCodes;

import java.util.Scanner;

public class UserMenu
{
    public static void main(AuthentificationService authentificationService, User current_user, Scanner in, PassService service, PayService payService, UserService userService)
    {
        switch (current_user)
        {
            case Admin admin -> adminMenu(authentificationService,admin,in);
            case Student student -> studentMenu(authentificationService, student, in, service, payService);
            default -> throw new IllegalStateException("Unexpected role: " + current_user);
        }
    }

    public static void adminMenu(AuthentificationService authentificationService,Admin admin,Scanner in)
    {
        AdminService adminService = new AdminService();
        while (authentificationService.isLoggedIn())
        {
            System.out.println("Welcome, Admin " + admin.getNume() + "!");
            System.out.println("1. Add New Student");
            System.out.println("2. View All Students");
            System.out.println("3. View Student Details");
            System.out.println("4. Add New Admin");
            System.out.println("5. View All Admins");
            System.out.println("6. View Admin Details");
            System.out.println("7. Add New University");
            System.out.println("8. View all universities");
            System.out.println("9. Logout");
            System.out.print("Enter your choice: ");

            int choice = in.nextInt();
            in.nextLine();
            switch (choice)
            {
                case 1:
                    adminService.addStudent(in);
                    break;
                case 2:
                    adminService.viewallStudents(admin.getAdminRole());
                    break;
                case 3:
                    adminService.viewStudentDetails(admin.getAdminRole(), in);
                    break;
                case 4:
                    adminService.addAdmin(in);
                    break;
                case 5:
                    adminService.viewallAdmins(admin.getAdminRole());
                    break;
                case 6:
                    adminService.viewAdminDetails(admin.getAdminRole(), in);
                    break;
                case 7:
                    adminService.addUniversity(in);
                    break;
                case 8:
                    adminService.viewallUniversity();
                    break;
                case 9:
                    System.out.println("Logging out...");
                    authentificationService.logout();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }


    public static void studentMenu(AuthentificationService authentificationService, Student current_user, Scanner in, PassService passService, PayService payService)
    {
        while (authentificationService.isLoggedIn())
        {
            System.out.println("Welcome, Student!");
            System.out.println("1. View Abonnements");
            System.out.println("2. Buy Abonnement");
            System.out.println("3. Pay Abonnement");
            System.out.println("4. Change Payment Option");
            System.out.println("5. Logout");
            System.out.print("Enter your choice: ");
            int choice = in.nextInt();
            switch (choice)
            {
                case 1:
                    passService.viewAllPasses(current_user.getStudent_number());
                    break;
                case 2:
                    int errorCode = payService.makePayment(current_user, in);
                    switch (errorCode)
                    {
                        case ErrorCodes.SUCCESS:
                            System.out.println("Plata a fost efectuată cu succes!");
                            break;
                        case ErrorCodes.CARD_NOT_FOUND:
                            System.out.println("Nu ai niciun card adăugat în aplicație.");
                            break;
                        case ErrorCodes.EXISTING_PASS:
                            System.out.println("Ai deja un abonament de acest tip.");
                            break;
                        case ErrorCodes.TRANSACTION_FAILED:
                            System.out.println("Nu s-a putut efectua plata.");
                            break;
                        default:
                            System.out.println("Eroare necunoscută.");
                    }
                    break;
                case 3:
                    errorCode = payService.payPass(current_user, in);
                    switch (errorCode)
                    {
                        case ErrorCodes.SUCCESS:
                            System.out.println("Plata a fost efectuată cu succes!");
                            break;
                        case ErrorCodes.CARD_NOT_FOUND:
                            System.out.println("Nu ai niciun card adăugat în aplicație.");
                            break;
                        case ErrorCodes.NO_EXPIRED_PASS:
                            System.out.println("Nu ai niciun abonament expirat disponibil pentru plată.");
                            break;
                        case ErrorCodes.TRANSACTION_FAILED:
                            System.out.println("Nu s-a putut efectua plata.");
                            break;
                        default:
                            System.out.println("Eroare necunoscută.");
                    }
                    break;
                case 4:
                    payService.changePaymentOption(current_user, in);
                    break;
                case 5:
                    authentificationService.logout();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

