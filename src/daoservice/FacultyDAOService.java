package daoservice;

import dao.FacultyDAO;
import model.Facultate;
import model.Universitate;

import java.sql.SQLException;
import java.util.List;

public class FacultyDAOService
{
    FacultyDAO facultyDAO;
    public FacultyDAOService() throws SQLException
    {
        this.facultyDAO = FacultyDAO.getInstance();
    }
    public void addFaculty(Facultate facultate, Universitate universitate) throws SQLException
    {
        if(facultate != null)
        {
            facultyDAO.create(facultate,universitate);
        }
    }
    public Facultate getFacultybyName(String name) throws SQLException
    {
        Facultate searched_fac = this.facultyDAO.read(name);
        if(searched_fac == null)
        {
            System.out.println("This faculty does not exists!");
            return null;
        }
        return searched_fac;
    }
    public List<Facultate> getallFaculties() throws SQLException
    {
        return facultyDAO.getAll();
    }
    public void removeFaculty(String nume_fac) throws SQLException
    {
        Facultate searched_fac = getFacultybyName(nume_fac);
        if(searched_fac != null)
        {
            this.facultyDAO.delete(searched_fac.getId_facultate());
            System.out.println("The faculty has been deleted!\n");
            return;
        }
        System.out.println("This faculty does not exists or has been already deleted");
    }
}
