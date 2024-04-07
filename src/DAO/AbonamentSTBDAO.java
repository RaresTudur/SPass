package DAO;

import model.Abonament_STB;

import java.util.ArrayList;
import java.util.List;

public class AbonamentSTBDAO
{
    List<Abonament_STB> abonamentSTBList;
    private static AbonamentSTBDAO instance;
    private AbonamentSTBDAO()
    {
        this.abonamentSTBList = new ArrayList<>();
    }
    public static AbonamentSTBDAO getInstance()
    {
        if(instance == null)
        {
            instance = new AbonamentSTBDAO();
        }
        return instance;
    }
    public void create(Abonament_STB abMetro)
    {
        this.abonamentSTBList.add(abMetro);
    }
    public void remove(Abonament_STB abMetro)
    {
        this.abonamentSTBList.add(abMetro);
    }
    public Abonament_STB read(int id_student)
    {
        for(Abonament_STB abSTB : abonamentSTBList)
        {
            if(abSTB.getStudent().getId_student() == id_student)
            {
                return abSTB;
            }
        }
        return null;
    }
}
