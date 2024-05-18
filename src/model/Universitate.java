package model;

import java.util.ArrayList;
import java.util.List;

public class Universitate
{
    private int id_Universitate;
    private String nume_Universitate;
    private String adresa;
    private String email_address;
    private List<Facultate> facultati;

    public Universitate(int id,String var_nume, String var_adresa, String var_email)
    {
        this.id_Universitate = id;
        this.nume_Universitate = var_nume;
        this.adresa = var_adresa;
        this.email_address = var_email;
        this.facultati = new ArrayList<>();
    }
    public Universitate(String var_nume, String var_adresa, String var_email)
    {
        this.id_Universitate = 0;
        this.nume_Universitate = var_nume;
        this.adresa = var_adresa;
        this.email_address = var_email;
        this.facultati = new ArrayList<>();
    }

    public Universitate()
    {

    }

    public int getId_Universitate()
    {
        return this.id_Universitate;
    }

    public void setId_Universitate(int id_Universitate)
    {
        this.id_Universitate = id_Universitate;
    }

    public String getNume_Universitate()
    {
        return nume_Universitate;
    }

    public void setNume_Universitate(String nume_Universitate)
    {
        this.nume_Universitate = nume_Universitate;
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

    public List<Facultate> getFacultati()
    {
        return facultati;
    }

    public void addFacultate(Facultate var_Fac)
    {
        facultati.add(var_Fac);
    }
    public void setFacultati(List<Facultate> facultati)
    {
        this.facultati = facultati;
    }

    @Override
    public String toString()
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Universitatea ").append(nume_Universitate).append(": \n");

        for (Facultate facultate : facultati) {
            stringBuilder.append(facultate.getNume_facultate()).append(", \n");
        }

        if (stringBuilder.length() > 2) {
            stringBuilder.setLength(stringBuilder.length() - 2);
        }

        return stringBuilder.toString();
    }

}
