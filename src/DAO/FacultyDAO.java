package DAO;

import model.Facultate;

import java.util.ArrayList;
import java.util.List;

public class FacultyDAO
{
    private List<Facultate> facultatiesList;
    private static FacultyDAO instance;
    private FacultyDAO()
    {
        this.facultatiesList = new ArrayList<>();
    }
    public static FacultyDAO getInstance()
    {
        if(instance == null)
        {
            instance = new FacultyDAO();
        }
        return instance;
    }
    public void create(Facultate fac)
    {
        this.facultatiesList.add(fac);
    }
    public void remove(Facultate fac)
    {
        this.facultatiesList.remove(fac);
    }
    public Facultate read(String name)
    {
        for(Facultate fac : this.facultatiesList)
        {
            if(fac.getNume_facultate().equals(name))
            {
                return fac;
            }
        }
        return null;
    }
}
