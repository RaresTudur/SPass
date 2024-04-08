package daoservice;

import model.Facultate;
import model.Universitate;

import java.util.List;

import dao.*;
public class UniversityDAOService
{
    private UniversityDAO universityDAO;
    private FacultyDAOService facultyDAOService;
    public UniversityDAOService()
    {
        this.universityDAO = UniversityDAO.getInstance();
        this.facultyDAOService = new FacultyDAOService();
    }
    public void addUniversity(Universitate var_uni)
    {
        if(var_uni != null)
        {
            universityDAO.create(var_uni);
        }
    }
    public void remove(String name_university)
    {
        Universitate var_university = getUniversitybyName(name_university);
        if(var_university != null)
        {
            universityDAO.delete(var_university);
        }
    }
    public void addFacultytoUniversity(Universitate var_uni, Facultate var_fac)
    {
        if(var_uni != null)
        {
            if(var_fac != null)
            {
                if(universityDAO.read(var_uni.getNume_Universitate()) == null)
                {
                    addUniversity(var_uni);
                }
                if(facultyDAOService.getFacultybyName(var_fac.getNume_facultate()) == null)
                {
                    facultyDAOService.addFaculty(var_fac);
                }
                var_uni.addFacultate(var_fac);
            }
        }
    }
    public Universitate getUniversitybyName(String name)
    {
        Universitate uni = universityDAO.read(name);
        if(uni != null)
        {
            return uni;
        }
        return null;
    }
    public Facultate getFacultyByName(String nume_facultate, String nume_univ)
    {
        List<Facultate> faculties = this.getUniversitybyName(nume_univ).getFacultati();
        for(Facultate faculty : faculties)
        {
            if (faculty.getNume_facultate().equals(nume_facultate))
            {
                return faculty;
            }
        }
        return null;
    }
    public List<Universitate> getUniversities()
    {
        return universityDAO.getUniversitates();
    }
}
