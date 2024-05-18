package model;

import utils.Constants;

public class Admin extends User
{
    private String adminRole;
    private String nume;
    public Admin(int id,String email, String password, String adminRole,String nume)
    {
        super(email,password,"Admin");
        this.setId(id);
        this.adminRole = adminRole;
        this.nume = nume;
    }
    public Admin(User user)
    {
        super(user.getEmail_address(), user.getPassword(),"Admin");
        adminRole = Constants.AdminRole;
    }
    public Admin()
    {
    }
    public Admin(int id,String adminRole, String nume)
    {
        this.setId(id);
        this.adminRole = adminRole;
        this.nume = nume;
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
                "Email Address:" + email_address + '\n';
    }
}
