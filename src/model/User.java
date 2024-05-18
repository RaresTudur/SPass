package model;

public class User
{
    private static int num_of_ids = 0;
    protected int id;
    protected String email_address;
    protected String password;
    private String role;
    public User(String email, String password, String role)
    {
        this.id = ++num_of_ids;
        this.email_address = email;
        this.password = password;
        this.role = role;
    }

    public User()
    {
        this.id = ++num_of_ids;
    }

    public String getEmail_address()
    {
        return email_address;
    }

    public void setEmail_address(String email_address)
    {
        this.email_address = email_address;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String get_Role()
    {
        return role;
    }

    public void setRole(String role)
    {
        this.role = role;
    }

}
