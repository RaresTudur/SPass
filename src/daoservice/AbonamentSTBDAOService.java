package daoservice;

import DAO.AbonamentSTBDAO;
import model.Abonament_STB;

public class AbonamentSTBDAOService
{
    private AbonamentSTBDAO abonamentSTBDAO;
    public AbonamentSTBDAOService()
    {
        this.abonamentSTBDAO = AbonamentSTBDAO.getInstance();
    }
    public void addAbonament(Abonament_STB abonament)
    {
        if(abonament != null && this.abonamentSTBDAO.read(abonament.getStudent().getStudent_number()) == null)
        {
            this.abonamentSTBDAO.create(abonament);
        }
    }
    public void removeAbonament(Abonament_STB abonament)
    {
        if(abonament != null && this.abonamentSTBDAO.read(abonament.getStudent().getStudent_number()) != null)
        {
            this.abonamentSTBDAO.remove(abonament);
        }
    }
    public Abonament_STB getAbonamentSTB(int student_number )
    {
        Abonament_STB abSearched = this.abonamentSTBDAO.read(student_number);
        if(abSearched != null)
        {
            return abSearched;
        }
        return null;
    }
}
