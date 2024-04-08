package dao;

import model.Universitate;

import java.util.ArrayList;
import java.util.List;

public class UniversityDAO
{
    private List <Universitate> universitates;
    private static UniversityDAO instance;
    private UniversityDAO()
    {
        universitates = new ArrayList<>();
    }
    public static UniversityDAO getInstance()
    {
        if(instance == null)
        {
            instance = new UniversityDAO();
        }
        return instance;
    }
    public Universitate read(String name)
    {
        if(!universitates.isEmpty())
        {
            for(Universitate uni: universitates)
            {
                if(uni.getNume_Universitate().equals(name))
                {
                    return uni;
                }
            }
        }
        return null;
    }
    public void delete(Universitate uni)
    {
        universitates.remove(uni);
    }
    public void create(Universitate uni)
    {
        universitates.add(uni);
    }

    public List<Universitate> getUniversitates()
    {
        return universitates;
    }
}
