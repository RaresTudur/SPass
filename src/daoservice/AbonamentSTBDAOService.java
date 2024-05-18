package daoservice;

import dao.AbonamentSTBDAO;
import model.Abonament_Metrorex;
import model.Abonament_STB;

import java.sql.SQLException;

public class AbonamentSTBDAOService
{
    private AbonamentSTBDAO abonamentSTBDAO;
    public AbonamentSTBDAOService() throws SQLException
    {
        this.abonamentSTBDAO = AbonamentSTBDAO.getInstance();
    }
    public void addAbonament(Abonament_STB abonament) throws SQLException
    {
        if(abonament != null && this.abonamentSTBDAO.read(abonament.getStudentId()) == null)
        {
            this.abonamentSTBDAO.create(abonament);
        }
    }
    public void removeAbonament(Abonament_STB abonament) throws SQLException
    {
        if(abonament != null && this.abonamentSTBDAO.read(abonament.getStudentId()) != null)
        {
            this.abonamentSTBDAO.delete(abonament);
        }
    }
    public void updateAbonament(Abonament_STB abonamentStb) throws SQLException
    {
        if(abonamentStb != null && this.abonamentSTBDAO.read(abonamentStb.getStudentId()) != null)
        {
            this.abonamentSTBDAO.update(abonamentStb);
        }
    }
    public Abonament_STB getAbonamentSTB(int student_number ) throws SQLException
    {
        Abonament_STB abSearched = this.abonamentSTBDAO.read(student_number);
        if(abSearched != null)
        {
            return abSearched;
        }
        return null;
    }
}
