package daoservice;

import dao.AbonamentMetrorexDAO;
import model.Abonament_Metrorex;

public class AbonamentMetrorexDAOService
{
    private AbonamentMetrorexDAO abonamentMetrorexDAO;
    public AbonamentMetrorexDAOService()
    {
        this.abonamentMetrorexDAO = AbonamentMetrorexDAO.getInstance();
    }
    public void addAbonament(Abonament_Metrorex abonament)
    {
        if(abonament != null && this.abonamentMetrorexDAO.read(abonament.getStudent().getStudent_number()) == null)
        {
            this.abonamentMetrorexDAO.create(abonament);
        }
    }
    public void removeAbonament(Abonament_Metrorex abonament)
    {
        if(abonament != null && this.abonamentMetrorexDAO.read(abonament.getStudent().getStudent_number()) != null)
        {
            this.abonamentMetrorexDAO.remove(abonament);
        }
    }
    public Abonament_Metrorex getAbonamentMetrorex(int student_number)
    {
        Abonament_Metrorex abSearched = this.abonamentMetrorexDAO.read(student_number);
        if(abSearched != null)
        {
            return abSearched;
        }
        return null;
    }
}
