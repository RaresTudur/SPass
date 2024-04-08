package daoservice;

import dao.FacultyDAO;
import model.Facultate;

public class FacultyDAOService
{
    FacultyDAO facultyDAO;
    public FacultyDAOService()
    {
        this.facultyDAO = FacultyDAO.getInstance();
    }
    public void addFaculty(Facultate facultate)
    {
        if(facultate != null)
        {
            facultyDAO.create(facultate);
        }
    }
    public Facultate getFacultybyName(String name)
    {
        Facultate searched_fac = this.facultyDAO.read(name);
        if(searched_fac == null)
        {
            System.out.println("This faculty does not exists!");
            return null;
        }
        return searched_fac;
    }
    public void removeFaculty(String nume_fac)
    {
        Facultate searched_fac = getFacultybyName(nume_fac);
        if(searched_fac != null)
        {
            this.facultyDAO.remove(searched_fac);
            System.out.println("The faculty has been deleted!\n");
            return;
        }
        System.out.println("This faculty does not exists or has been already deleted");
    }
}
