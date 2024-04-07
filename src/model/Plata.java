package model;

import java.time.LocalDate;

public class Plata
{
    private static int num_id = 0;
    
    private int id_plata;
    private int id_Student;
    private int id_Abonament;
    private String tip_Abonament;
    private double value;
    LocalDate data_plata;

    public Plata(int var_id_Student, int var_id_Abonament, String var_tip,double value)
    {
        ++num_id;
        this.id_plata = num_id;
        this.id_Student = var_id_Student;
        this.id_Abonament = var_id_Abonament;
        this.tip_Abonament = var_tip;
        this.value = value;
        this.data_plata = LocalDate.now();
    }


    public int getId_Student()
    {
        return id_Student;
    }

    public void setId_Student(int id_Student)
    {
        this.id_Student = id_Student;
    }

    public int getId_Abonament()
    {
        return id_Abonament;
    }

    public void setId_Abonament(int id_Abonament)
    {
        this.id_Abonament = id_Abonament;
    }

    public String getTip_Abonament()
    {
        return tip_Abonament;
    }

    public void setTip_Abonament(String tip_Abonament)
    {
        this.tip_Abonament = tip_Abonament;
    }

    public LocalDate getData_plata()
    {
        return data_plata;
    }

    public void setData_plata(LocalDate data_plata)
    {
        this.data_plata = data_plata;
    }

    public int getId_plata()
    {
        return id_plata;
    }

    public void setId_plata(int id_plata)
    {
        this.id_plata = id_plata;
    }
}
