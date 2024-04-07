package DAO;

import model.Abonament_Metrorex;

import java.util.ArrayList;
import java.util.List;

public class AbonamentMetrorexDAO
{
    List<Abonament_Metrorex> abonamentMetrorexList;
    private static AbonamentMetrorexDAO instance;
    private AbonamentMetrorexDAO()
    {
        this.abonamentMetrorexList = new ArrayList<>();
    }
    public static AbonamentMetrorexDAO getInstance()
    {
        if(instance == null)
        {
            instance = new AbonamentMetrorexDAO();
        }
        return instance;
    }
    public void create(Abonament_Metrorex abMetro)
    {
        this.abonamentMetrorexList.add(abMetro);
    }
    public void remove(Abonament_Metrorex abMetro)
    {
        this.abonamentMetrorexList.add(abMetro);
    }
    public Abonament_Metrorex read(int id_student)
    {
        for(Abonament_Metrorex abMetro : abonamentMetrorexList)
        {
            if(abMetro.getStudent().getId_student() == id_student)
            {
                return abMetro;
            }
        }
        return null;
    }
}
