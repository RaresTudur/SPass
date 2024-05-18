package model;

import java.util.ArrayList;
import java.util.List;

public class Facultate
{
    private static int num_of_ids = 0;
    public int id_facultate;
    private String nume_facultate;
    private String adresa;
    private String email_address;
    private int id_Universitate;

    public Facultate(String var_nume, String var_adresa, String var_email, int id_U)
    {
        this.id_facultate = ++num_of_ids;
        this.nume_facultate = var_nume;
        this.adresa = var_adresa;
        this.email_address = var_email;
        this.id_Universitate = id_U;
    }

    public Facultate()
    {

    }

    public  int getId_facultate()
    {
        return this.id_facultate;
    }

    public  void setId_facultate(int id_facultate)
    {
        this.id_facultate = id_facultate;
    }

    public String getNume_facultate()
    {
        return nume_facultate;
    }

    public void setNume_facultate(String nume_facultate)
    {
        this.nume_facultate = nume_facultate;
    }

    public String getAdresa()
    {
        return adresa;
    }

    public void setAdresa(String adresa)
    {
        this.adresa = adresa;
    }

    public String getEmail_address()
    {
        return email_address;
    }

    public void setEmail_address(String email_address)
    {
        this.email_address = email_address;
    }

    public int getId_Universitate()
    {
        return id_Universitate;
    }

    public void setId_Universitate(int id_Universitate)
    {
        this.id_Universitate = id_Universitate;
    }


    @Override
    public String toString()
    {
        return "Facultatea: " + this.nume_facultate + "\n";
    }
}
