package service;

import daoservice.UniversityDAOService;
import model.Universitate;
import model.Facultate;
import utils.UniversitiesCompare;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class UniversityService
{
    private UniversityDAOService dao;
    public UniversityService()
    {
        this.dao = new UniversityDAOService();
    }
    public void create(Scanner in)
    {
        System.out.println("Name: ");
        String name = in.nextLine();
        System.out.println("Address: ");
        String address = in.nextLine();
        System.out.println("Email address: ");
        String email = in.nextLine();
        Universitate univ = new Universitate(name,address,email);
        addFacultati(in,univ);
        dao.addUniversity(univ);
    }
    public void addFacultati(Scanner in, Universitate univ)
    {
        System.out.println("Number of faculties: ");
        int nrf = in.nextInt();
        for(int i = 0; i < nrf; i++)
        {
            univ.addFacultate(facultyInit(in,univ));
        }
    }
    public Facultate facultyInit(Scanner in, Universitate univ)
    {
        in.nextLine();
        System.out.println("Name: ");
        String name = in.nextLine();
        System.out.println("Address: ");
        String address = in.nextLine();
        System.out.println("Email Address: ");
        String email = in.nextLine();
        return new Facultate(name,address,email,univ.getId_Universitate());
    }
    public List<Universitate> getAllUniversities()
    {
       return dao.getUniversities();
    }
    public void viewAllUniversities()
    {
        List<Universitate> universities = getAllUniversities();
        Collections.sort(universities, new UniversitiesCompare());
        for(Universitate university : universities)
        {
            System.out.println(university);
        }
    }
}
