package service;

import daoservice.AbonamentMetrorexDAOService;
import daoservice.AbonamentSTBDAOService;
import model.Abonament;
import model.Abonament_Metrorex;
import model.Abonament_STB;
import model.Student;
import utils.Constants;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PassService
{
    private AbonamentMetrorexDAOService abonamentMetrorexDAOService;
    private AbonamentSTBDAOService abonamentSTBDAOService;
    public PassService() throws SQLException
    {
        this.abonamentSTBDAOService = new AbonamentSTBDAOService();
        this.abonamentMetrorexDAOService = new AbonamentMetrorexDAOService();
    }
    private boolean checkpassSTB(Abonament_STB abonamentSTB) throws SQLException
    {
        if(abonamentSTB == null)
        {
            return false;
        }
        Abonament check_pass = abonamentSTBDAOService.getAbonamentSTB(abonamentSTB.getStudentId());
        if(check_pass ==null)
        {
            return false;
        }
        if(check_pass.equals(abonamentSTB))
        {
            return true;
        }
        return false;
    }
    private boolean checkpassMetrorex(Abonament_Metrorex abonamentMetrorex) throws SQLException
    {
        if(abonamentMetrorex == null)
        {
            return false;
        }
        Abonament_Metrorex abonamentMetrorex1 = abonamentMetrorexDAOService.getAbonamentMetrorex(abonamentMetrorex.getStudentId());
        if(abonamentMetrorex1 == null)
        {
            return false;
        }
        if(abonamentMetrorex1.equals(abonamentMetrorex))
        {
            return true;
        }
        return false;
    }
    private boolean checkPassExists(Abonament var_abonament) throws SQLException
    {
        boolean exists = false;
        switch(var_abonament)
        {
            case Abonament_STB abonamentStb -> exists = checkpassSTB(abonamentStb);
            case Abonament_Metrorex abonamentMetrorex -> exists = checkpassMetrorex(abonamentMetrorex);
            default -> throw new IllegalStateException("Unexpected value: " + var_abonament);
        }
        return exists;
    }
    public void createMetrorexPass(Student var_student, boolean typeOfPay) throws SQLException
    {
        Abonament_Metrorex abonamentMetrorex = new Abonament_Metrorex(var_student.getId(),typeOfPay,var_student.getUniversitateID(),var_student.getFacultateID());
        if(checkPassExists(abonamentMetrorex))
        {
            System.out.println("Ai deja acest tip de abonament");
            return;
        }
        abonamentMetrorexDAOService.addAbonament(abonamentMetrorex);
        System.out.println("Abonamenul a fost creat");
    }
    public void createSTBPass(Student var_student, boolean typeOfPay) throws SQLException
    {
        Abonament_STB abonamentSTB = new Abonament_STB(var_student.getId(),typeOfPay,var_student.getUniversitateID(),var_student.getFacultateID());
        if(checkPassExists(abonamentSTB))
        {
            System.out.println("Ai deja acest tip de abonament");
            return;
        }
        abonamentSTBDAOService.addAbonament(abonamentSTB);
        System.out.println("Abonamenul a fost creat");
    }
    public void createPass(Student var_student,String typeofPass, boolean typeofPay) throws SQLException
    {
        switch (typeofPass)
        {
            case Constants.STB -> createSTBPass(var_student,typeofPay);
            case Constants.METROREX -> createMetrorexPass(var_student,typeofPay);
            default -> throw new IllegalStateException("Unexpected value" + typeofPass);
        }
    }
    public Abonament_STB getPasstSTBbySID(int studentID) throws SQLException
    {
        Abonament_STB abonamentStb = abonamentSTBDAOService.getAbonamentSTB(studentID);
        if(abonamentStb != null)
        {
            return abonamentStb;
        }
        System.out.println("Nu ai un abonament de tip STB");
        return null;
    }
    public Abonament_Metrorex getPassMetrorexbySID(int studentID) throws SQLException
    {
        Abonament_Metrorex abonamentMetrorex = abonamentMetrorexDAOService.getAbonamentMetrorex(studentID);
        if(abonamentMetrorex != null)
        {
            return abonamentMetrorex;
        }
        System.out.println("Nu ai un abonament de tip Metrorex");
        return null;
    }
    public Abonament getPass(int studentID, String typeofPass) throws SQLException
    {
        Abonament searched_pass;
        switch(typeofPass.toLowerCase())
        {
            case Constants.STB:
                searched_pass = getPasstSTBbySID(studentID);
                break;
            case Constants.METROREX:
                searched_pass = getPassMetrorexbySID(studentID);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + typeofPass);
        }
        return searched_pass;
    }
    public List<Abonament> getAllPasses(int studentID) throws SQLException
    {
        List<Abonament> passes = new ArrayList<>();
        Abonament_STB abonamentStb = abonamentSTBDAOService.getAbonamentSTB(studentID);
        if(abonamentStb != null)
        {
            passes.add(abonamentStb);
        }
        Abonament_Metrorex abonamentMetrorex = abonamentMetrorexDAOService.getAbonamentMetrorex(studentID);
        if(abonamentMetrorex != null)
        {
            passes.add(abonamentMetrorex);
        }
        return passes;
    }
    public List<Abonament> getAllExpiredPasses(int studentID) throws SQLException
    {
        List<Abonament> passes = new ArrayList<>();
        Abonament_STB abonamentStb = abonamentSTBDAOService.getAbonamentSTB(studentID);
        if(abonamentStb != null)
        {
            if(abonamentStb.isExpirat())
            {
                passes.add(abonamentStb);
            }
        }
        Abonament_Metrorex abonamentMetrorex = abonamentMetrorexDAOService.getAbonamentMetrorex(studentID);
        if(abonamentMetrorex != null)
        {
            if(abonamentMetrorex.isExpirat())
            {
                passes.add(abonamentMetrorex);
            }
        }
        return passes;
    }
    public void viewAllPasses(int studentID) throws SQLException
    {
        List<Abonament> passes = getAllPasses(studentID);
        if(passes.isEmpty())
        {
            return;
        }
        for(Abonament pass : passes)
        {
            System.out.println(pass);
        }
    }
    public void cancelSTBPass(int studentID) throws SQLException
    {
        Abonament_STB abonamentStb = getPasstSTBbySID(studentID);
        abonamentSTBDAOService.removeAbonament(abonamentStb);
        System.out.println("Abonamentul a fost anulat");
    }
    public void cancelMetrorexPass(int studentID) throws SQLException
    {
        Abonament_Metrorex abonamentMetrorex = getPassMetrorexbySID(studentID);
        abonamentMetrorexDAOService.removeAbonament(abonamentMetrorex);
        System.out.println("Abonamentul a fost anulat");
    }
    public void cancelPass(int studentID, String typeofPass) throws SQLException
    {
        switch(typeofPass.toLowerCase())
        {
            case Constants.STB -> cancelSTBPass(studentID);
            case Constants.METROREX -> cancelMetrorexPass(studentID);
            default -> throw new IllegalStateException("Unexpected value: " + typeofPass);
        }
    }
    public void updateMetrorexPassDuration(Abonament_Metrorex abonamentMetrorex) throws SQLException
    {
        abonamentMetrorex.setData_inceput(LocalDate.now());
        abonamentMetrorex.setExpirat(false);
        abonamentMetrorexDAOService.updateAbonament(abonamentMetrorex);
    }
    public void updateSTBPassDuration(Abonament_STB abonamentSTB) throws SQLException
    {
        abonamentSTB.setData_inceput(LocalDate.now());
        abonamentSTB.setExpirat(false);
        abonamentSTBDAOService.updateAbonament(abonamentSTB);
    }
    public void updatePass(Abonament abonament) throws SQLException
    {
        switch (abonament)
        {
            case Abonament_STB abonamentStb -> updateSTBPassDuration(abonamentStb);
            case Abonament_Metrorex abonamentMetrorex -> updateMetrorexPassDuration(abonamentMetrorex);
            default -> throw new IllegalStateException("Unexpected value: " + abonament);
        }
    }
    public double getValue(String typeOfPass)
    {
        if(typeOfPass.toLowerCase().equals(Constants.STB))
        {
            return 80.0;
        }
        return 8.0;
    }
    public String getTypeofPass(Scanner in)
    {
        String typeofPass;
        System.out.println("Ce tip de abonament doresti");
        System.out.println("1. Autobuz");
        System.out.println("2. Metrou");
        int choice = in.nextInt();
        switch (choice)
        {
            case 1:
                typeofPass = "autobuz";
                break;
            case 2:
                typeofPass = "metrou";
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + choice);
        }
        return typeofPass;
    }
    public void viewAllExpiredPasses(int studentID) throws SQLException
    {
        List<Abonament> passes = getAllExpiredPasses(studentID);
        if(passes.isEmpty())
        {
            return;
        }
        for(Abonament pass : passes)
        {
            System.out.println(pass);
        }
    }
    public Abonament getExpiredAbonament(int studentID,Scanner in) throws SQLException
    {
        List<Abonament> passes = getAllExpiredPasses(studentID);
        if(passes.isEmpty())
        {
            return null;
        }
        viewAllExpiredPasses(studentID);
        int choice = in.nextInt();
        Abonament abonament = passes.get(choice-1);
        if(abonament != null)
        {
            return abonament;
        }
        else
        {
            return null;
        }
    }
    public boolean checkExistingPassSTB(Student user) throws SQLException
    {
        return(abonamentSTBDAOService.getAbonamentSTB(user.getId()) != null);
    }
    public boolean checkExistingPassMetrorex(Student user) throws SQLException
    {
        return(abonamentMetrorexDAOService.getAbonamentMetrorex(user.getStudent_number()) != null);
    }
    public boolean checkExistingPass(Student user, String typeofPass) throws SQLException
    {
        boolean existingPass = false;
        switch (typeofPass)
        {
            case Constants.STB ->  existingPass = checkExistingPassSTB(user);
            case Constants.METROREX -> existingPass = checkExistingPassMetrorex(user);
        }
        return existingPass;
    }
    public String getTypeofPass(Abonament abonament)
    {
        String typeofPass;
        switch (abonament)
        {
            case Abonament_STB abonamentStb -> typeofPass = Constants.STB;
            case Abonament_Metrorex abonamentMetrorex -> typeofPass = Constants.METROREX;
            default -> throw new IllegalStateException("Unexpected value: " + abonament);
        }
        return typeofPass;
    }
}
