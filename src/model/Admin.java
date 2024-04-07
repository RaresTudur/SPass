package model;

import utils.Constants;

public class Admin extends User
{
    private String adminRole;
    private String nume;
    public Admin(String email, String password, String adminRole,String nume)
    {
        super(email,password);
        this.adminRole = adminRole;
        this.nume = nume;
    }
    public Admin(User user)
    {
        super(user.getEmail_address(), user.getPassword());
        adminRole = Constants.AdminRole;
    }
    public String getNume()
    {
        return nume;
    }

    public void setNume(String nume)
    {
        this.nume = nume;
    }

    public void setAdminRole(String adminRole)
    {
        this.adminRole = adminRole;
    }

    public String getAdminRole()
    {
        return adminRole;
    }

    @Override
    public String toString()
    {
        return "Admin cu rolul :" + adminRole + '\n' +
                "Nume: " + nume + '\n' +
                "Email Address:" + email_address + '\n' +
                "Password: " + password +  '\n';
    }
}
