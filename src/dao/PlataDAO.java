package dao;

import model.Plata;

import java.util.ArrayList;
import java.util.List;

public class PlataDAO
{
    private List<Plata> platiList;
    public static PlataDAO instance;
    private PlataDAO()
    {
        this.platiList = new ArrayList<>();
    }
    public static PlataDAO getInstance()
    {
        if(instance == null)
        {
            instance = new PlataDAO();
        }
        return instance;
    }
    public void create(Plata var_plata)
    {
        platiList.add(var_plata);
    }
    public void delete(Plata var_plata)
    {
        platiList.remove(var_plata);
    }
    public Plata read(int id_Plata)
    {
        for(Plata var_plata : platiList)
        {
            if(var_plata.getId_plata() == id_Plata)
            {
                return var_plata;
            }
        }
        return null;
    }
    public List<Plata> getPayments()
    {
        return platiList;
    }
}
