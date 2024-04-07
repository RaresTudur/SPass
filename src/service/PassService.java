package service;

import daoservice.AbonamentMetrorexDAOService;
import daoservice.AbonamentSTBDAOService;
import model.Abonament;
import model.Abonament_Metrorex;
import model.Abonament_STB;
import model.Student;
import utils.Constants;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PassService
{
    private AbonamentMetrorexDAOService abonamentMetrorexDAOService;
    private AbonamentSTBDAOService abonamentSTBDAOService;
    public PassService()
    {
        this.abonamentSTBDAOService = new AbonamentSTBDAOService();
        this.abonamentMetrorexDAOService = new AbonamentMetrorexDAOService();
    }
    private boolean checkpassSTB(Abonament_STB abonamentSTB)
    {
        if(abonamentSTB == null)
        {
            return false;
        }
        Abonament check_pass = abonamentSTBDAOService.getAbonamentSTB(abonamentSTB.getStudent().getStudent_number());
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
    private boolean checkpassMetrorex(Abonament_Metrorex abonamentMetrorex)
    {
        if(abonamentMetrorex == null)
        {
            return false;
        }
        Abonament_Metrorex abonamentMetrorex1 = abonamentMetrorexDAOService.getAbonamentMetrorex(abonamentMetrorex.getStudent().getStudent_number());
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
    private boolean checkPassExists(Abonament var_abonament)
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
    public void createMetrorexPass(Student var_student, boolean typeOfPay)
    {
        Abonament_Metrorex abonamentMetrorex = new Abonament_Metrorex(var_student,typeOfPay,var_student.getUniversitate(),var_student.getFacultate());
        if(checkPassExists(abonamentMetrorex))
        {
            System.out.println("Ai deja acest tip de abonament");
            return;
        }
        abonamentMetrorexDAOService.addAbonament(abonamentMetrorex);
        System.out.println("Abonamenul a fost creat");
    }
    public void createSTBPass(Student var_student, boolean typeOfPay)
    {
        Abonament_STB abonamentSTB = new Abonament_STB(var_student,typeOfPay,var_student.getUniversitate(),var_student.getFacultate());
        if(checkPassExists(abonamentSTB))
        {
            System.out.println("Ai deja acest tip de abonament");
            return;
        }
        abonamentSTBDAOService.addAbonament(abonamentSTB);
        System.out.println("Abonamenul a fost creat");
    }
    public void createPass(Student var_student,String typeofPass, boolean typeofPay)
    {
        switch (typeofPass)
        {
            case Constants.STB -> createSTBPass(var_student,typeofPay);
            case Constants.METROREX -> createMetrorexPass(var_student,typeofPay);
            default -> throw new IllegalStateException("Unexpected value" + typeofPass);
        }
    }
    public Abonament_STB getPasstSTBbySID(int student_number)
    {
        Abonament_STB abonamentStb = abonamentSTBDAOService.getAbonamentSTB(student_number);
        if(abonamentStb != null)
        {
            return abonamentStb;
        }
        System.out.println("Nu ai un abonament de tip STB");
        return null;
    }
    public Abonament_Metrorex getPassMetrorexbySID(int student_number)
    {
        Abonament_Metrorex abonamentMetrorex = abonamentMetrorexDAOService.getAbonamentMetrorex(student_number);
        if(abonamentMetrorex != null)
        {
            return abonamentMetrorex;
        }
        System.out.println("Nu ai un abonament de tip Metrorex");
        return null;
    }
    public Abonament getPass(int student_number, String typeofPass)
    {
        Abonament searched_pass;
        switch(typeofPass.toLowerCase())
        {
            case Constants.STB:
                searched_pass = getPasstSTBbySID(student_number);
                break;
            case Constants.METROREX:
                searched_pass = getPassMetrorexbySID(student_number);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + typeofPass);
        }
        return searched_pass;
    }
    public List<Abonament> getAllPasses(int student_number)
    {
        List<Abonament> passes = new ArrayList<>();
        Abonament_STB abonamentStb = abonamentSTBDAOService.getAbonamentSTB(student_number);
        if(abonamentStb != null)
        {
            passes.add(abonamentStb);
        }
        Abonament_Metrorex abonamentMetrorex = abonamentMetrorexDAOService.getAbonamentMetrorex(student_number);
        if(abonamentMetrorex != null)
        {
            passes.add(abonamentMetrorex);
        }
        return passes;
    }
    public List<Abonament> getAllExpiredPasses(int student_number)
    {
        List<Abonament> passes = new ArrayList<>();
        Abonament_STB abonamentStb = abonamentSTBDAOService.getAbonamentSTB(student_number);
        if(abonamentStb != null)
        {
            if(abonamentStb.isExpirat())
            {
                passes.add(abonamentStb);
            }
        }
        Abonament_Metrorex abonamentMetrorex = abonamentMetrorexDAOService.getAbonamentMetrorex(student_number);
        if(abonamentMetrorex != null)
        {
            if(abonamentMetrorex.isExpirat())
            {
                passes.add(abonamentMetrorex);
            }
        }
        return passes;
    }
    public void viewAllPasses(int student_number)
    {
        List<Abonament> passes = getAllPasses(student_number);
        if(passes.isEmpty())
        {
            return;
        }
        for(Abonament pass : passes)
        {
            System.out.println(pass);
        }
    }
    public void cancelSTBPass(int student_number)
    {
        Abonament_STB abonamentStb = getPasstSTBbySID(student_number);
        abonamentSTBDAOService.removeAbonament(abonamentStb);
        System.out.println("Abonamentul a fost anulat");
    }
    public void cancelMetrorexPass(int student_number)
    {
        Abonament_Metrorex abonamentMetrorex = getPassMetrorexbySID(student_number);
        abonamentMetrorexDAOService.removeAbonament(abonamentMetrorex);
        System.out.println("Abonamentul a fost anulat");
    }
    public void cancelPass(int student_number, String typeofPass)
    {
        switch(typeofPass.toLowerCase())
        {
            case Constants.STB -> cancelSTBPass(student_number);
            case Constants.METROREX -> cancelMetrorexPass(student_number);
            default -> throw new IllegalStateException("Unexpected value: " + typeofPass);
        }
    }
    public void updateMetrorexPassDuration(Abonament_Metrorex abonamentMetrorex)
    {
        abonamentMetrorex.setData_inceput(LocalDate.now());
        abonamentMetrorex.setExpirat(false);
    }
    public void updateSTBPassDuration(Abonament_STB abonamentSTB)
    {
        abonamentSTB.setData_inceput(LocalDate.now());
        abonamentSTB.setExpirat(false);
    }
    public void updatePass(Abonament abonament)
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
    public void viewAllExpiredPasses(int student_number)
    {
        List<Abonament> passes = getAllExpiredPasses(student_number);
        if(passes.isEmpty())
        {
            return;
        }
        for(Abonament pass : passes)
        {
            System.out.println(pass);
        }
    }
    public Abonament getExpiredAbonament(int student_number,Scanner in)
    {
        List<Abonament> passes = getAllExpiredPasses(student_number);
        if(passes.isEmpty())
        {
            return null;
        }
        viewAllExpiredPasses(student_number);
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
    public boolean checkExistingPassSTB(Student user)
    {
        return(abonamentSTBDAOService.getAbonamentSTB(user.getId_student()) != null);
    }
    public boolean checkExistingPassMetrorex(Student user)
    {
        return(abonamentMetrorexDAOService.getAbonamentMetrorex(user.getStudent_number()) != null);
    }
    public boolean checkExistingPass(Student user, String typeofPass)
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
