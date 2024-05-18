package daoservice;

import dao.AbonamentMetrorexDAO;
import model.Abonament_Metrorex;

import java.sql.SQLException;

public class AbonamentMetrorexDAOService
{
    private AbonamentMetrorexDAO abonamentMetrorexDAO;
    public AbonamentMetrorexDAOService() throws SQLException
    {
        this.abonamentMetrorexDAO = AbonamentMetrorexDAO.getInstance();
    }
    public void addAbonament(Abonament_Metrorex abonament) throws SQLException
    {
        if(abonament != null && this.abonamentMetrorexDAO.read(abonament.getStudentId()) == null)
        {
            this.abonamentMetrorexDAO.create(abonament);
        }
    }
    public void removeAbonament(Abonament_Metrorex abonament) throws SQLException
    {
        if(abonament != null && this.abonamentMetrorexDAO.read(abonament.getStudentId()) != null)
        {
            this.abonamentMetrorexDAO.delete(abonament);
        }
    }
    public void updateAbonament(Abonament_Metrorex abonamentMetrorex) throws SQLException
    {
        if(abonamentMetrorex != null && this.abonamentMetrorexDAO.read(abonamentMetrorex.getStudentId()) != null)
        {
            this.abonamentMetrorexDAO.update(abonamentMetrorex);
        }
    }
    public Abonament_Metrorex getAbonamentMetrorex(int student_number) throws SQLException
    {
        Abonament_Metrorex abSearched = this.abonamentMetrorexDAO.read(student_number);
        if(abSearched != null)
        {
            return abSearched;
        }
        return null;
    }
}
