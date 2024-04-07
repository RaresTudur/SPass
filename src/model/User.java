package model;

public class User
{
    protected String email_address;
    protected String password;
    public User(String email, String password)
    {
        this.email_address = email;
        this.password = password;
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
}
