package daoservice;

import model.Facultate;
import model.Universitate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.*;
public class UniversityDAOService
{
    private UniversityDAO universityDAO;
    private FacultyDAOService facultyDAOService;
    public UniversityDAOService() throws SQLException
    {
        this.universityDAO = UniversityDAO.getInstance();
        this.facultyDAOService = new FacultyDAOService();
    }
    public void addUniversity(Universitate var_uni) throws SQLException
    {
        if(var_uni != null)
        {
            universityDAO.create(var_uni);
        }
    }
    public void remove(String name_university) throws SQLException
    {
        Universitate var_university = getUniversitybyName(name_university);
        if(var_university != null)
        {
            universityDAO.delete(var_university);
        }
    }
    public Universitate getUniversitybyName(String name)
    {
        return universityDAO.read(name);
    }
    public Facultate getFacultyByName(String nume_facultate, String nume_univ) throws SQLException
    {
        List<Facultate> faculties = this.getallFaculties(getUniversitybyName(nume_univ)) ;
        for(Facultate faculty : faculties)
        {
            if (faculty.getNume_facultate().equals(nume_facultate))
            {
                return faculty;
            }
        }
        return null;
    }
    public List<Facultate> getallFaculties(Universitate universitate) throws SQLException
    {
        List<Facultate> lista_facultati = facultyDAOService.getallFaculties();
        List<Facultate> facultatiUniversitate = new ArrayList<>();
        for(Facultate fac : lista_facultati)
        {
            if(fac.getId_Universitate() == universitate.getId_Universitate())
            {
                facultatiUniversitate.add(fac);
            }
        }
        return facultatiUniversitate;
    }
    public List<Universitate> getUniversities()
    {
        return universityDAO.getUniversitates();
    }
}
